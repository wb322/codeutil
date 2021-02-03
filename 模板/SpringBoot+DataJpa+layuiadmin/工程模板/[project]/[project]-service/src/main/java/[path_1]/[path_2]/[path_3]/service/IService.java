package [package].service;

import [package].resp.Criteria;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public interface IService<T,ID extends Serializable>{
    /**
     * 查询全部
     * @return
     */
    List<T> findAll();

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    T findById(ID id);

    /**
     * 根据条件查询
     */
    List<T> findByCriteria(Criteria<T> criteria);

    /**
     * 根据条件分页查询
     */
    Page<T> findPageByCriteria(Criteria<T> criteria);

    /**
     * 判断实体是否存在
     */
    Boolean exist(T t);

    /**
     * 添加
     */
    void save(T t);

    /**
     * 批量插入
     * @param list
     */
    void saveAll(List<T> list);

    /**
     * 根据主键修改
     */
    void update(T t);

    /**
     * @param t
     */
    Integer deleteByCriteria(T t);

    /**
     * 根据主键删除
     * @param id
     */
    void deleteById(ID id);

    /**
     * 根据主键批量删除
     * @param ids
     */
    void deleteByIds(List<ID> ids);

}
