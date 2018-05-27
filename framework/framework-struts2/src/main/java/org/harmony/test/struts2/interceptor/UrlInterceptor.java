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
package org.harmony.test.struts2.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author wuxii@foxmail.com
 */
public class UrlInterceptor implements Interceptor {

    private static final long serialVersionUID = 3589616154721582182L;

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        invocation.getInvocationContext();
        ActionProxy proxy = invocation.getProxy();
        proxy.getAction();
        proxy.getConfig().getMethodName();
        proxy.getMethod();
        proxy.getConfig().getAllowedMethods();
        proxy.getConfig().getClassName();
        proxy.getConfig();
        return invocation.invoke();
    }

    @Override
    public void destroy() {
    }

}
