//package org.moon.test.aop.aspectj;
//
//public aspect AuthorizationAspect {
//
//    pointcut authMethods(Account account) : call(float Bank.*(..)) && args(account, *);
//
//    float around(Account account) : authMethods(account){
//        if ("abcd1234".equals(account.getPassword())) {
//            return proceed(account);
//        }
//        throw new RuntimeException("account not right");
//    }
//
//}
