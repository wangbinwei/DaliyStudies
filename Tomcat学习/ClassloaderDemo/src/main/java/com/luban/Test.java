package com.luban;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();  // AppClassLoader

        Class c = classLoader.loadClass("com.luban.Demo"); //
//
//        System.out.println(classLoader);

        // ext.parent() ==
        System.out.println(classLoader.getParent().getParent());
    }
}
