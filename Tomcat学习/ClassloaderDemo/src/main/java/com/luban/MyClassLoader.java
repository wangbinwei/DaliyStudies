package com.luban;

import java.io.*;

public class MyClassLoader extends ClassLoader {
    private String name;

    public MyClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

      @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data1 = getBytes("D:\\IdeaProjects\\ClassloaderDemo\\com\\luban\\Test1.class");
        Class clazz = this.defineClass(name, data1, 0, data1.length);
        return clazz;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        ClassLoader system = getParent(); // appclass.parent=ext

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

    public static void main(String[] args) {
        MyClassLoader loader1 = new MyClassLoader(MyClassLoader.class.getClassLoader(), "MyClassLoader");
        MyClassLoader loader2 = new MyClassLoader(MyClassLoader.class.getClassLoader(), "MyClassLoader");
        try {
            Class clazz1 = loader1.loadClass("com.luban.Test1");
            Class clazz2 = loader2.loadClass("com.luban.Test1");
            System.out.println(clazz1.equals(clazz2)); //
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private byte[] getBytes(String filePath) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            is = new FileInputStream(new File(filePath));
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
        return data;
    }
}
