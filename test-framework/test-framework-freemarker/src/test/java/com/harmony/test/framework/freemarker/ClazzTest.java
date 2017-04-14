package com.harmony.test.framework.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * @author wuxii@foxmail.com
 */
public class ClazzTest {

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setDirectoryForTemplateLoading(new File("src/test/resources/clazz"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);

        TClass o = new TClass();
        o.pkg = "com.huiju.test";
        o.comment = "/**\n"
                  + " * test class\n"
                  + " * \n"
                  + " * @author wuxii@foxmail.com\n"
                  + " */";
        o.name = "TestEntity";
        o.imports = Arrays.asList("com.huiju.test.A", "com.huiju.test.B");
        o.fields = Arrays.asList(new TField("String", "name"), new TField("int", "age"));
        
        Template temp = cfg.getTemplate("entity.ftl");
        Writer out = new OutputStreamWriter(System.out);

        temp.process(o, out);
    }

    public static class TField {

        private String type;
        private String name;

        public TField() {
        }

        public TField(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public static class TClass {

        private String pkg;
        private List<String> imports;
        private String comment;
        private String name;
        private List<TField> fields;

        public String getPackage() {
            return pkg;
        }

        public void setPackage(String pkg) {
            this.pkg = pkg;
        }

        public List<String> getImports() {
            return imports;
        }

        public void setImports(List<String> imports) {
            this.imports = imports;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TField> getFields() {
            return fields;
        }

        public void setFields(List<TField> fields) {
            this.fields = fields;
        }

    }

}
