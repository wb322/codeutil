package [package].controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;

/**
 * @author [author]
 */
@RestController
public class CommonController {
    /**
     * 跳转到主页
     */
    @GetMapping(value = {"/","/index"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView ("index");
        return modelAndView;
    }
    /**
     * 接口文档
     */
    @GetMapping("/swagger")
    public void swagger2(HttpServletResponse response)throws Exception{
        response.sendRedirect ("swagger-ui.html#/");
    }

    @GetMapping("/console")
    public ModelAndView console(){
        ModelAndView modelAndView = new ModelAndView ("console");
        return modelAndView;
    }
    /**
     * 通用跳转方法
     */
    @GetMapping(value = {"/menu/{module}/{view}","/menu/{module}/{view}/{key}"})
    public ModelAndView redirect(@PathVariable String module, @PathVariable String view, @PathVariable(required = false) Object key){
        ModelAndView modelAndView = new ModelAndView (module + "/" + view);
        if (key != null){
            modelAndView.addObject ("key",key);
        }
        return modelAndView;
    }
}
