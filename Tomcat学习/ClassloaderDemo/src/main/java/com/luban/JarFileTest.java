package com.luban;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarFileTest {
    public static void main(String[] args) {
        try {
            JarFile jarFile = new JarFile("D:\\IdeaProjects\\ServletDemo\\src\\main\\webapp\\WEB-INF\\lib\\javax.servlet-api-3.0.1.jar");

            JarEntry jarEntry = jarFile.getJarEntry("javax/servlet/AsyncEvent1.class");
            System.out.println(jarEntry.getName());

//            Enumeration entries = jarFile.entries();
//            while (entries.hasMoreElements()) {
//                System.out.println(entries.nextElement());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
