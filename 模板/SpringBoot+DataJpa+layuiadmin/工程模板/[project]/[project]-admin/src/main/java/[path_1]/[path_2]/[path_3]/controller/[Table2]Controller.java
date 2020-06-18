package [package].controller;

import [package].entity.[Table2];
import [package].service.I[Table2]Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * [comment]控制器层
 * @author [author]
 */
@RestController
@CrossOrigin
@RequestMapping("/[table]")
public class [Table2]Controller extends BaseController<[Table2],[keyType],I[Table2]Service>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("[table]/form");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("[table]/form");
        return modelAndView;
    }
}
