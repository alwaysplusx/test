package org.moon.test.ee;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.moon.test.ee.service.UserServiceTransactionAttributeTest;
import org.moon.test.ee.service.UserServiceTransactionTimeoutTest;

@Ignore
@RunWith(Suite.class)
@Suite.SuiteClasses(value = { UserServiceTransactionAttributeTest.class, UserServiceTransactionTimeoutTest.class })
public class ContainerTestSuite {

}
