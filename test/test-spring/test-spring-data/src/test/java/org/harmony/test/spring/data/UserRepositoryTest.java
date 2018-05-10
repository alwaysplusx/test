package org.harmony.test.spring.data;

import java.util.Properties;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.harmony.test.spring.data.UserRepositoryTest.AppConfiguration;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * FIXME transaction
 * 
 * @author wuxii@foxmail.com
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        userRepository.save(new User("wuxii", "abc123"));
        User user = userRepository.findOne("wuxii");
        System.err.println(user);
    }

    @Configuration
    @EnableJpaRepositories(basePackages = "org.harmony")
    public static class AppConfiguration {

        @Bean
        DataSource datasource() {
            return JdbcConnectionPool.create("jdbc:h2:file:~/.harmony/test", "sa", "");
        }

        @Bean
        LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
            entityManagerFactoryBean.setDataSource(datasource());
            entityManagerFactoryBean.setPackagesToScan("org.harmony");
            entityManagerFactoryBean.setJpaDialect(new HibernateJpaDialect());
            Properties jpaProperties = new Properties();
            jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
            jpaProperties.setProperty("hibernate.show_sql", "true");
            entityManagerFactoryBean.setPersistenceUnitName("test");
            entityManagerFactoryBean.setJpaProperties(jpaProperties);
            entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            return entityManagerFactoryBean;
        }

        @Bean
        JpaTransactionManager transactionManager() {
            JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
            jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getNativeEntityManagerFactory());
            return jpaTransactionManager;
        }

    }

}
