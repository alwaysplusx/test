package org.moon.test.aop.aspectj;

public aspect TransactionAspect {

	pointcut transactionMethods() : call(* Bank.deposit(Account,float)) ||call(* Bank.withdraw(Account,float));

	before() : transactionMethods(){
		log("begin transaction");
	}

	after() : transactionMethods(){
		log("commit transaction");
	}
	
	protected static void log(String message) {
		System.out.println(message);
	}
	
}
