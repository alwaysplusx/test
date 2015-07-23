/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.moon.test.ee;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;

/**
 * @author wuxii@foxmail.com
 */
public class CreateContainerTest {

    public static EJBContainer container;

    public static void createJDBC() {
        Properties props = new Properties();
        props.put("jdbc.moon", "new://Resource?type=DataSource");
        props.put("jdbc.moon.JdbcDriver", "org.h2.Driver");
        props.put("jdbc.moon.JdbcUrl", "jdbc:h2:file:./target/data");
        props.put("jdbc.moon.UserName", "sa");
        props.put("jdbc.moon.Password", "");
        container = EJBContainer.createEJBContainer();
    }

}
