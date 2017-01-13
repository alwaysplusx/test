package org.harmony.tes.test.runner;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.harmony.tes.test.ContainerConfiguration;
import org.harmony.tes.test.Naming;
import org.harmony.tes.test.OpenEJBConfiguration;
import org.harmony.tes.test.Property;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * @author wuxii@foxmail.com
 */
public class OpenEJBRunner extends BlockJUnit4ClassRunner {

    private EJBContainer container;

    public OpenEJBRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected Statement withBeforeClasses(Statement statement) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Class<?> testClass = getTestClass().getJavaClass();
                Map properties = new LinkedHashMap<>();
                ContainerConfiguration ann = testClass.getAnnotation(ContainerConfiguration.class);
                if (ann != null) {
                    List<String> list = new ArrayList<>();
                    list.addAll(Arrays.asList(ann.value()));
                    list.addAll(Arrays.asList(ann.locations()));
                    properties.putAll(loadProperties(list.toArray(new String[list.size()])));

                    Property[] aps = ann.properties();
                    if (aps.length != 0) {
                        for (Property ap : aps) {
                            properties.put(ap.name(), ap.value());
                        }
                    }
                }
                OpenEJBConfiguration openejbAnn = testClass.getAnnotation(OpenEJBConfiguration.class);
                if (openejbAnn != null) {
                    Property[] aps = openejbAnn.properties();
                    if (aps.length != 0) {
                        for (Property ap : aps) {
                            properties.put(ap.name(), ap.value());
                        }
                    }
                    properties.put("openejb.conf.file", openejbAnn.location());
                }
                // OpenEjbContainer(L324)
                properties.put(org.apache.openejb.OpenEjbContainer.Provider.OPENEJB_ADDITIONNAL_CALLERS_KEY,
                        testClass.getName());
                container = EJBContainer.createEJBContainer(properties);
                statement.evaluate();
            }
        };
    }

    private Properties loadProperties(String[] array) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        for (String s : array) {
            try (InputStream is = loader.getResourceAsStream(s)) {
                props.load(is);
            } catch (IOException e) {
                throw new IllegalArgumentException(s + " config file not found", e);
            }
        }
        return props;
    }

    @Override
    protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
        Statement next = super.withBefores(method, target, statement);
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                Context context = container.getContext();
                context.bind("inject", target);
                Field[] fields = target.getClass().getDeclaredFields();
                for (Field field : fields) {
                    Naming ann = field.getAnnotation(Naming.class);
                    if (ann != null) {
                        Object obj = null;
                        if (!ann.value().trim().equals("")) {
                            obj = context.lookup(ann.value().trim());
                        } else {
                            obj = context;
                        }
                        field.setAccessible(true);
                        field.set(target, obj);
                    }
                }
                next.evaluate();
            }
        };
    }

    @Override
    protected Statement withAfterClasses(final Statement statement) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                statement.evaluate();
                container.close();
            }
        };
    }

}
