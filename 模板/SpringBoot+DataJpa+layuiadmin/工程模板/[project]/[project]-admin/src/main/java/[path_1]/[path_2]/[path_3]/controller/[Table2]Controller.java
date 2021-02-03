package [package].controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import [package].entity.[Table2];
import [package].service.I[Table2]Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * [comment]控制器层
 * @author [author]
 */
@RestController
@CrossOrigin
@RequestMapping("/[table]")
public class [Table2]Controller extends BaseController<[Table2],[keyType],I[Table2]Service>{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("[table]/list");
        return modelAndView;
    }
    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("[table]/form");
        return modelAndView;
    }

}
