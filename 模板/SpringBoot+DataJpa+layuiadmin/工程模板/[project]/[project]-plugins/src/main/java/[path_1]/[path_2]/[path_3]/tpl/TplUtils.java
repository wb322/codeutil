package [package].tpl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.engine.thymeleaf.ThymeleafEngine;
import [package].Config;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TplUtils {

    /**
     * 生成文件
     */
    public static void renderFile(String tplName,String dest, Map data) throws Exception{
        //thymeleaf Engine
        TemplateEngine templateEngine = new TemplateEngine();

        File tplFile = new File(Config.custom_path_tpl,tplName);
        if (tplFile.exists()){
            final FileTemplateResolver fileTemplateResolver = new FileTemplateResolver();
            fileTemplateResolver.setCharacterEncoding("UTF-8");
            fileTemplateResolver.setTemplateMode(TemplateMode.TEXT);
            fileTemplateResolver.setPrefix(StrUtil.addSuffixIfNot(Config.custom_path_tpl, "/"));
            templateEngine.setTemplateResolver(fileTemplateResolver);
        }else{
            final ClassLoaderTemplateResolver classLoaderResolver = new ClassLoaderTemplateResolver();
            classLoaderResolver.setCharacterEncoding("UTF-8");
            classLoaderResolver.setTemplateMode(TemplateMode.TEXT);
            classLoaderResolver.setPrefix("tpl/");
            templateEngine.setTemplateResolver(classLoaderResolver);
        }
        ThymeleafEngine thymeleafEngine = new ThymeleafEngine(templateEngine);
        Template template = thymeleafEngine.getTemplate(tplName);
        File file = new File(dest);
        if (file.exists()){
            file.delete();
        }
        template.render(data,file);
    }
}
