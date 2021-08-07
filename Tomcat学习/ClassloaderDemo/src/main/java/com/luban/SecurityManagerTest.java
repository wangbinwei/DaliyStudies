package com.luban;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SecurityManagerTest {

    public static void main(String[] args) throws FileNotFoundException {
//        FileInputStream fis = new FileInputStream("D:\\IdeaProjects\\ClassloaderDemo\\password.txt");
//        System.out.println(System.getProperty("file.encoding"));  //

        // 当前java进程直接指向test，是没有读取ttt权限的
//        new Person().test();

        // 当前java进程执行man方法，Man
        Man.man();  // Man是拥有权限的，一个没有权限的类去调用一个拥有权限的方法时，如果拥有权限的类的那个方法是使用AccessController.doPrivileged调用的，那就可以绕过权限了


    }
}
