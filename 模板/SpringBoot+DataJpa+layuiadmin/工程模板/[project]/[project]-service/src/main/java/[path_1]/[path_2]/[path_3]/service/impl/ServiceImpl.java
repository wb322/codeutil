package [package].service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import [package].dao.BaseDao;
import [package].resp.Criteria;
import [package].service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@Transactional
public class ServiceImpl<T,ID extends Serializable,R extends BaseDao> implements IService<T,ID> {

    @Autowired
    private R dao;

    public R getDao(){
        return dao;
    }

    /** 获取T的class类 */
    public Class<T> getTClass() {
        Class<T> TClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return TClass;
    }
    /** 获取ID的class类 */
    public Class<ID> getIDClass() {
        Class<ID> IDClass = (Class<ID>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return IDClass;
    }

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    @Override
    public T findById(ID id) {
        return (T) dao.findById(id).get();
    }

    @Override
    public List<T> findByCriteria(Criteria<T> criteria) {
        Specification<T> specification = createSpecification(criteria);
        return dao.findAll(specification);
    }

    @Override
    public Page<T> findPageByCriteria(Criteria<T> criteria) {
        Specification<T> specification = createSpecification(criteria);
        PageRequest pageRequest =  PageRequest.of(criteria.getPage() - 1,criteria.getLimit());
        return dao.findAll(specification,pageRequest);
    }

    @Override
    public Boolean exist(T t){
        return getDao().exists(Example.of(t));
    }

    @Override
    public void save(T t) {
        dao.save(t);
    }

    @PersistenceContext
    private EntityManager em;

    /** 根据ID更新,只能有一个ID,无则更新全部 */
    @Override
    public void update(T t) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        Class<T> c = (Class<T>)t.getClass();
        CriteriaUpdate<T> update = cb.createCriteriaUpdate(c);
        Root root = update.from(c);
        EntityType model = root.getModel();
        String idName = model.getId(getIDClass()).getName();
        Map map = new HashMap();
        BeanUtil.beanToMap(t, map, false, true);
        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (StrUtil.isNotEmpty(idName) && idName.equals(key)){
                update.where(cb.equal(root.get(key), value));
            }else{
                update.set(key,value);
            }
        }
        Query query = em.createQuery(update);
        query.executeUpdate();
    }

    /** 根据属性删除,原生的需要有ID属性,所以自己实现 */
    @Override
    public Integer deleteByCriteria(T t) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        Class<T> c = (Class<T>)t.getClass();
        CriteriaDelete<T> delete = cb.createCriteriaDelete(c);
        Root<T> root = delete.from(c);
        Map map = new HashMap();
        BeanUtil.beanToMap(t, map, false, true);
        Set<Map.Entry<String, Object>> set = map.entrySet();
        List<Predicate> predicateList = new ArrayList<Predicate>();
        for (Map.Entry<String, Object> entry : set) {
            String key = entry.getKey();
            Object value = entry.getValue();
            predicateList.add(cb.equal(root.get(key),value));
        }
        Predicate[] p = new Predicate[predicateList.size()];
        delete.where(cb.and(predicateList.toArray(p)));
        Query query = em.createQuery(delete);
        int i = query.executeUpdate();
        return i;
    }

    @Override
    public void deleteById(ID id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteByIds(List<ID> ids) {
        dao.deleteInBatch(ids);
    }

    /** 动态条件构建 */
    private Specification<T> createSpecification(Criteria<T> criteria) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                Map<String, Object> map = new HashMap<>();
                if (criteria.getParams() != null){
                    map = BeanUtil.beanToMap(criteria.getParams(),false,true);
                }
                Set<Map.Entry<String, Object>> entries = map.entrySet();
                if (entries != null){
                    for (Map.Entry<String, Object> entry : entries) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (value instanceof String){
                            predicateList.add(cb.like(root.get(key).as(String.class), "%"+entry.getValue()+"%"));
                        }else{
                            predicateList.add(cb.equal(root.get(key).as(String.class), entry.getValue()));
                        }
                    }
                }
                Predicate[] p = new Predicate[predicateList.size()];
                query.where(cb.and(predicateList.toArray(p)));
                if (criteria.getAsc() != null){
                    query.orderBy(cb.asc(root.get(criteria.getAsc())));
                }
                if (criteria.getDesc() != null){
                    query.orderBy(cb.desc(root.get(criteria.getDesc())));
                }
                return query.getRestriction();
            }
        };
    }
}
