package [package].service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import [package].dao.BaseMapper;
import [package].service.IBaseService;

/**
 * 公共service类实现类
 * @author [author]
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> implements IBaseService<T> {


}
