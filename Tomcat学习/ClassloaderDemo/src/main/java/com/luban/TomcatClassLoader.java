package com.luban;

import java.io.*;

public class TomcatClassLoader extends ClassLoader {
    private String name;

    public TomcatClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        ClassLoader system = getSystemClassLoader();

        Class<?> clazz = null;
        try {
            clazz = system.loadClass(name);
        } catch (Exception e) {

        }

        if (clazz != null) {
            return clazz;
        }

        clazz = findClass(name);
        return clazz;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            is = new FileInputStream(new File("D:\\IdeaProjects\\ClassloaderDemo\\com\\luban\\Test1.class"));
            int c=0;
            while ((c=is.read()) != -1) {
                baos.write(c);
            }
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return this.defineClass(name, data, 0, data.length);
    }

    public static void main(String[] args) {
        TomcatClassLoader loader = new TomcatClassLoader(TomcatClassLoader.class.getClassLoader(), "MyClassLoader");

        try {
            Class clazz = loader.loadClass("com.luban.Test1");
            System.out.println(clazz.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
