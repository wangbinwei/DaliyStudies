package com.luban;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderTest {

    public static void main(String[] args) {

        try {
            URL url = new URL("https://repo1.maven.org/maven2/org/apache/dubbo/dubbo-rpc-api/2.7.4/dubbo-rpc-api-2.7.4.jar");
//            URL url = new File("C:\\Users\\周瑜\\IdeaProjects\\ClassloaderDemo\\").toURL();

            URL[] urls = new URL[]{url};

            URLClassLoader urlClassLoader = new URLClassLoader(urls); //

            Class c = urlClassLoader.loadClass("org.apache.dubbo.rpc.Filter");
//            Class c = urlClassLoader.loadClass("com.luban.Test1");

            System.out.println(c.getClassLoader().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
