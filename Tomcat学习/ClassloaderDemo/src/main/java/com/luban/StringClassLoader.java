package com.luban;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class StringClassLoader extends URLClassLoader {

    public StringClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return findClass(name);
    }

    public static void main(String[] args) {
        try {
            URL url = new File("C:\\Program Files\\Java\\jdk1.8.0_212\\jre\\lib\\rt.jar").toURL();
            StringClassLoader loader = new StringClassLoader(new URL[]{url});
            System.out.println(loader.toString());

            Class clazz = loader.loadClass("java.lang.String");
            System.out.println(clazz.getClassLoader());

            System.out.println(String.class.getClassLoader());
        } catch (ClassNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
