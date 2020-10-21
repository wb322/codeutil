package [package].service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import [package].entity.[Table2];
import [package].dao.[Table2]Mapper;
import [package].service.I[Table2]Service;
/**
 * [comment]服务层实现类
 * @author [author]
 */
@Service("[table2]Service")
@Transactional
public class [Table2]ServiceImpl extends ServiceImpl<[Table2]Mapper,[Table2]> implements I[Table2]Service {

}
