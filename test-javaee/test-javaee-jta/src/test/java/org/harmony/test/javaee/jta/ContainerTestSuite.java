package org.harmony.test.javaee.jta;

import org.harmony.test.javaee.jta.service.UserServiceTransactionAttributeTest;
import org.harmony.test.javaee.jta.service.UserServiceTransactionTimeoutTest;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Ignore
@RunWith(Suite.class)
@Suite.SuiteClasses(value = { UserServiceTransactionAttributeTest.class, UserServiceTransactionTimeoutTest.class })
public class ContainerTestSuite {

}
