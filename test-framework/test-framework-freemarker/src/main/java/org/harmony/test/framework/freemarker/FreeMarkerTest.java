package org.harmony.test.framework.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * @author wuxii@foxmail.com
 */
public class FreeMarkerTest {

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);

        Map root = new HashMap();
        root.put("user", "wuxii");
        Template temp = cfg.getTemplate("hello.ftl");
        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);
    }

}
