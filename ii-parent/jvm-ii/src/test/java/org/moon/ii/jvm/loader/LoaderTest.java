package org.moon.ii.jvm.loader;

public class LoaderTest {

	/**
	 * 只有在类被主动加载时候才会导致类的初始化
	 */
	public static void main(String[] args) {
		/*
		 * 通过子类引用父类的常量,子类将不会初始化
		 */
		// System.out.println(SubClass.a);
		/*
		 * 引用final常量将不会导致类的初始化
		 */
		// System.out.println(SubClass.b);
	}

}

class SupperClass {
	public static String a = "Hi";
	public static final String b = "FINAL HELLO";
	static {
		System.out.println("SupperClass init");
	}
}

class SubClass extends SupperClass {
	static {
		System.out.println("SubClass init");
	}
}
