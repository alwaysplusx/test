package org.harmony.test.framework.freemarker;

import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * @author wuxii@foxmail.com
 */
public class IndexOf implements TemplateMethodModelEx {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        int size = arguments.size();
        if (size != 2) {
            return new TemplateModelException();
        }
        return arguments.get(0).toString().indexOf(arguments.get(1).toString());
    }

}
