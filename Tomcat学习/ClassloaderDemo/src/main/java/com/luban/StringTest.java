package com.luban;

public class StringTest {

    public static void main(String[] args) {
//        String s = new String("111");
//        System.out.println(String.class.getClassLoader());


        try {
//            System.out.println(StringTest.class.getClassLoader());

            Class s = StringTest.class.getClassLoader().loadClass("java.lang.String");
            System.out.println(s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
