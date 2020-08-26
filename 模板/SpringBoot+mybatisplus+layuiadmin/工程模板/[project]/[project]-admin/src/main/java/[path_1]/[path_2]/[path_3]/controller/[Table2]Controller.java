package [package].controller;

import [package].entity.[Table2];
import [package].service.I[Table2]Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * [comment]控制器层
 * @author [author]
 */
@RestController
@CrossOrigin
@RequestMapping("/[table]")
public class [Table2]Controller extends BaseController<[Table2],[[keyType]],I[Table2]Service>{

}
