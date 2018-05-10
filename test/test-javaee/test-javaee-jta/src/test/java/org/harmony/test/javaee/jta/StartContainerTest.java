package org.harmony.test.javaee.jta;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.embeddable.EJBContainer;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StartContainerTest {

    private static EJBContainer container;

    @Resource
    private DataSource datasource;

    @BeforeClass
    public static void beforeClass() {
        Properties props = new Properties();
        props.put("jdbc.harmony", "new://Resource?type=DataSource");
        props.put("jdbc.harmony.JdbcDriver", "org.h2.Driver");
        props.put("jdbc.harmony.JdbcUrl", "jdbc:h2:file:~/.h2/harmony");
        props.put("jdbc.harmony.UserName", "sa");
        props.put("jdbc.harmony.Password", "");
        // props.put("jta.harmony", "new://TransactionManager?type=TransactionManager");
        // props.put("jta.harmony.defaultTransactionTimeout", "10 seconds");
        container = EJBContainer.createEJBContainer(props);
    }

    @Before
    public void before() throws Exception {
        container.getContext().bind("inject", this);
        assertNotNull(datasource);
    }

    @Test
    public void testStart() throws SQLException {
        assertNotNull(datasource.getConnection().getMetaData().getDatabaseProductName());
    }

    @After
    public void tearDown() throws Exception {
        container.close();
    }

}
