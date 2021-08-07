# Java SE学习

java 特性 封装、继承、多态

封装：隐藏一些细节，高内聚、低耦合

继承：继承父类的属性和方法

多态： 父类的引用指向子类的类型     e.g:  Object object = new Student();   

1、多态是方法的多态，属性没有多态

2、父类和子类，有联系，类型转换异常！ClassCastException!

3、多态存在条件 : 继承关系，方法需要重写，父亲的引用指向子类的对象

不能重写： 1、static 方法，属于类，它不属于实例 	2、final 常量  3、private方法

```java
public class Student extends Person {
    @Override
    public void run() {
        System.out.println("son");
    }
}
```

```java
public class Person {

   public void run(){
       System.out.println("run");
   }
}
```

```java
//子类重写父类的方法，执行子类的方法
 //可以指向的引用类型就不确定  ；父类的引用指向子类的类型
//对象能执行哪些方法，主要看对象左边的类型，和右边关系不大
        Object s1 = new Student();
		//Person 父类型，可以指向子类，但是不能调用子类独有的方法
        Person s2 = new Student(); //子类重写父类的方法，执行子类的方法
		//Student 能调用的方法都是自己的或者继承父类的
        Student s3 = new Student();
        s2.run(); //输出son
        s3.run(); //输出son

```

**java类编译、运行的执行的流程**

![image-20210419105937625](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210419105937625.png)

#### 反射

**Reflection（反射)是被视为动态语言的关键，反射机制允许程序在执行期借助于Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及方法。**

框架 = 反射 + 注解 + 设计模式。

![image-20210419102343001](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210419102343001.png)

##### 反射应用一： 创建运行时类的对象  

```java
Class<Person> clazz = Person.class;

Person obj = clazz.newInstance();
System.out.println(obj);
```

##### 反射应用二：获取运行时类的完整结构

```java
//获取属性结构
//getFields():获取当前运行时类及其父类中声明为public访问权限的属性
Class clazz4 = Person.class;
        Field[] fields = clazz.getFields(); //获取属性结构
        for(Field f: fields){
            System.out.println(f);
        }
//getDeclaredFields():获取当前运行时类中声明的所属性。(不包含父类中声明的属性)
    Field[] declaredFields = clazz.getDeclaredFields();
    for(Field f : declaredFields){
        System.out.println(f);
    }

```



可以通过反射，获取对应的运行时类中所有的属性、方法、构造器、父类、接口、父类的泛型、包、注解、异常等

##### 反射应用三：调用运行时类的指定结构

调用指定的属性：

```java
@Test
public void testField1() throws Exception {
    Class clazz = Person.class;

    //创建运行时类的对象
    Person p = (Person) clazz.newInstance();

    //1. getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
    Field name = clazz.getDeclaredField("name");

    //2.保证当前属性是可访问的
    name.setAccessible(true);
    //3.获取、设置指定对象的此属性值
    name.set(p,"Tom");

    System.out.println(name.get(p));
}
```

调用指定的方法：



##### 反射应用四：动态代理

1.代理模式的原理

使用一个代理将对象包装起来, 然后用该代理对象取代原始对象。任何对原始对象的调用都要通过代理。代理对象决定是否以及何时将方法调用转到原始对象上。 

```java
/*2.1 举例：
实现Runnable接口的方法创建多线程。静态代理*/
Class MyThread implements Runnable{} //相当于被代理类
Class Thread implements Runnable{} //相当于代理类
main(){
MyThread t = new MyThread(); // 被代理类
Thread thread = new Thread(t); //代理类
thread.start();//启动线程；调用线程的run()，start是将线程交给线程池运行
}
```

2.2 静态代理的缺点：
① 代理类和目标对象的类都是在编译期间确定下来，不利于程序的扩展。
② 每一个代理类只能为一个接口服务，这样一来程序开发中必然产生过多的代理。

3.动态代理的特点：
动态代理是指客户通过代理类来调用其它对象的方法，并且是在程序运行时根据需要动态创建目标类的代理对
象。







## Java8

### **lambda**

#### lambda定义

Lambda 表达式的语法格式如下：

(parameters) -> expression 或 (parameters) ->{ statements; }

将 "一块代码"赋给一个Java变量

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\b1ab916e093c47b28c5d3b045ef49fcc\clipboard.png)

这样将一个函数赋给一个变量，就是一个Lambda表达式

Java 8中，所有Lambda的类型都是一个接口，而Lambda表达式本身，也就是

就是实现了一个接口，用lambda更加简洁，下面的s就 函数传参的变量，是接口里需要实现的函数变量

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\4406427fa17f482ba17021b29cf3f2c7\clipboard.png)

#### lambda使用

java 8中有一个函数式接口的包，里面定义了大量可能用到的函数式接口[java.util.function (Java Platform SE 8 )](https://link.zhihu.com/?target=https%3A//docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)

Java8中引入Optional类，该类主要解决空指针异常NPE NullPointerException 

里面方法  isPresent() 为了判断查询的类对象是否存在，采用此方法

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\eb3c3a46c9cc45ad8b5cd8f7cf43e46d\clipboard.png)

#### 流运算

filter  、 sort、collect 

Stream  流编程--Java8   进行去重

- Java8中在Collection中增加了一个stream()方法，该方法返回一个Stream类型。我们就是用该Stream来进行流编程的；

- 流与集合不同，流是只有在按需计算的，而集合是已经创建完毕并存在缓存中的；

- 流与迭代器一样都只能被遍历一次，如果想要再遍历一遍，则必须重新从数据源获取数据；

- 外部迭代就是指需要用户去做迭代，内部迭代在库内完成的，无需用户实现；

- 可以连接起来的流操作称为中间操作，关闭流的操作称为终端操作（从形式上看，就是用.连起来的操作中，中间的那些叫中间操作，最终的那个操作叫终端操作）。

### Java关键字

![img](https://img-blog.csdnimg.cn/20190407171115204.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3oxd2Vi,size_16,color_FFFFFF,t_70)

#### 访问控制

private:表示私有的   public:表示公有的    protected:对于子女，朋友来说是公有的

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\5dece2321e5544c7963ec0be79fdf833\clipboard.png)

#### instanceof

​	instanceof: 用来测试一个对象是否是指定类型的实例对象 

```java
//Object > String
//Object > Person > Student
//Object > Person > Teacher
System.out.println(X instanceof Y); //若X和Y存在父子关系，则返回true,否则返回false
```
#### 类型转换

1. 父类引用指向子类的对象
2. 把子类转换为父类，想上转型  Object ob = new Student();
3. 把父类转化为子类，向下转型  需要强制转换
4. 作用：方便方法的调用，减少重复的代码！

### **String类**

- String类   不可变性

- StringBuffer	可变长  append()  多线程数据量较大    效率低，安全

- StringBuilder  可变长  单线程数据量较大   效率高，不安全

  

### 内部类

内部类就是在一个类的内部再定义一个类

- 成员内部类
- 静态内部类
- 局部内部类
- 匿名内部类

### 数据结构——集合类

![img](https://pics0.baidu.com/feed/f603918fa0ec08fa3e7e5abdf372a66b54fbdad5.jpeg?token=1db6eaad9406aaf3b0fc3a4c6129d062)

#### 数据结构简介

ArrayList类实现了可变的数组，允许保存所有元素，包括null，优点：可以根据索引位置对集合进行快速的随机访问；缺点是向指定的索引位置插入对象或删除对象的速度较慢     

LinkedList类采用链表结构保存对象，优点插入删除容易，随机访问效率低

ArrayList和LinkedList就是数组和链表之前的优缺点，**add、remove**

```java
List<Integer> path1 = new ArrayList<>();
List<Integer> path2 = new ArrayList<>(path1); //可以这样子直接构造函数
```



jdk 1.8  hash表 = 数组+链表+红黑树

HashMap：HashMap 是线程不安全，效率高

HashTable：Hashtable 线程安全，效率低 

LinkedHashMap:LinkedHashMap是HashMap的一个子类，保存了记录的插入顺序，用Iterator遍历LinkedHashMap时，先得到的记录一定是先插入的 

TreeMap:TreeMap实现SortedMap接口，能够把它保存的记录根据键排序，默认按照键值升序排序，也可以指定排序的比较器，当用Iterator遍历TreeMao时，得到的记录是排过序的

#### Collections.sort的两种用法

```java
public static <T extends Comparable<? super T>> void sort(List<T> list) {
        list.sort(null);
    }
```
```java
public static <T> void sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);
    }
```

```java
Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                if (bit[x] != bit[y]) {
                    return bit[x] - bit[y];
                } else {
                    return x - y;
                }
            }
        });

```
如果表达式为true则交换x,y否则不交换

```java
Collections.sort( taegrt, new Comparator<Type>(){
   public int compare(Type x, Type y){
   if(x - y)
    return x-y; //升序
})
```



#### map和list线程安全

说明：ArrrayList和HashMap都是线程不安全的，如果线程要求线程安全，我们可以将ArrayList、HashMap转换为线程的。 使用synchronizedList(List list) 和 synchronizedMap(Map map)

concurrentHashMap 是线程安全的 hashMap

collections.synchronizedList(new ArrayList) 是线程安全的集合

#### 异常学习

**ClassCastException**：是JVM在检测到两个类型间转换不兼容时引发的运行时异常。

#### **泛型学习**

通配符：？ 类A是类B的父类，G<A>和G<B>是没关系的，二者共同的父类是：G<?>



    限制条件的通配符的使用。


```java
? extends A: //上限extends 使用时候必须继承某个类，或者实现某个接口，既<=
                G<? extends A> 可以作为G<A>和G<B>的子类，其中B是A的子类
                
? super A: //使用时的指定类型不能小于操作的类，>=
            G<? super A> 可以作为G<A>和G<B>的父类，其中B是A的父类
举例：//Student > Person > Object
List<? extends Person> list1 = null; //<=  Student > Person 
List<? super Person> list2 = null; //>=  Person > Object
```

#### 字节流

![image-20210418181650096](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210418181650096.png)

红框中是四个基本流中的4个抽象基类。蓝框是需要重点关注的





### java线程

Java的并发采用的是共享内存模型（而非消息传递模型），线程之间共享程序的公共状态，线程之间通过写-读内存中的公共状态来**隐式**进行**通信**。

多个线程之间是不能直接传递数据交互，它们之间的交互是通过共享变量的方式来实现的。

#### 多线程

##### 内存模型  JMM: java Memory Model

Java线程之间的通信由Java内存模型（JMM）控制，JMM决定一个线程对共享变量的写入何时对另一个线程可见。

JMM定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存中，每个线程都有一个私有的本地内存。本地内存中存储了该线程以读/写共享变量的副本。

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\dfa2d1b79dfd494db5605ac0760d2b4f\clipboard.png)

java的可见性： 当一个对象在多个内存中都存在副本时，如果一个内存修改了这个变量，其他线程也应该能够看到被修改后的值，此为可见性

有序性： **要保证A线程和B线程有序执行**，先取款后汇款或者先汇款后取款，**此为有序性**。

##### synchronized和volatile 实现多线程同步

一个线程执行互斥的过程：

1、获得同步锁 2、清空工作内存 3、从主存中拷贝对象副本到工作内存 4、执行相关代码 5、刷新主内存数据  6、释放同步锁

volatile是第二种Java多线程同步的方法，一个变量可以被volatile修饰，在这种情况下内存模型确保所有线程可以看到一致的变量值

##### 线程的大致使用

线程的 start和run， run直接运行，start是将线程交给线程池运行

线程创建的方式   Thread start0本地方法：java无权调用，交给底层的c处理 、Runnable 函数式接口 lambda

常用的方法  join 插队、sleep、yield礼让、 isLive、start、

线程同步     多个对象操作同一个资源，并发、 队列 + 锁

Synchronized  同步方法、同步代码块、第一个线程进来拿到锁，后面就要排队了，直到这个人释放锁，后面拿到锁才能进去

死锁：DeadLock  互斥、请求与保持、不剥夺条件、循环等待条件

优先级  Lock > 同步代码块>同步方法

线程通信： 缓冲区 消息队列      标志位：红绿灯   wait() 和  notifyAll()



##### 四个主要线程池

池化技术、池的大小，最大连接数、保持时间

newSingleThreadExecutor：单线程池，同时只有一个线程池在跑

newCachedThreadPool() ：回收型线程池，可以重复利用之前创建过的线程，运行线程最大数是Integer.MAX_VALUE。

newFixedThreadPool(): 固定大小的线程池，根回收型线程池类似，可以限制同时运行的线程数量

ScheduledExecutorService  可以实现循环或延迟任务  在对延迟任务和循环任务要求严格的时候

#### 网络编程

端口号：正在计算机上运行的进程

在Java中使用InetAddress类代表IP

端口号和IP地址的组合得出一个网络套接字：Socket



![image-20210419095310137](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210419095310137.png)

![image-20210419095416911](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210419095416911.png)

TCP面向连接（如打电话要先拨号建立连接）。

UDP是无连接的，即发送数据之前不需要建立连接。

##### TCP网络编程

##### UDP网络编程

##### URL编程



### java拷贝

java的深拷贝和浅拷贝：之前的区别是是否真正获取了一个对象的复制实体，而不是引用

浅拷贝：就是添加一个指向这个内存的指针，所以当该复制B改变时 也会改变A

深拷贝：是添加一个指针和一块内存，将这个新指针指向这个内存

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\f11a18cf45c24eb6afe6be60c2462a22\clipboard.png)

```java
package core.java.deeporshoawcopy;

/**
 * @author DGW-PC
 * @date 2018年6月7日
 * @see 验证   浅拷贝  一般要求实体类使用包装类型 对于深拷贝 类中存在对其他类的引用，也需要实现cloneable接口
 */

class Person implements Cloneable{
    private String name;
    private Integer age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException  {
        /*Person p=null;
        try{
            p=(Person) super.clone();
        }catch (CloneNotSupportedException e) {
        }*/
        return super.clone();
    }
}

public class Base {

     public static void main(String[] args) throws CloneNotSupportedException {
        Person person = new Person();
        person.setName("a");
        person.setAge(12);
        Person per1=(Person) person.clone();
        per1.setName("b");
        per1.setAge(14);;
        System.out.println(person.getName()+" "+person.getAge().hashCode(0));
        System.out.println(per1.getName()+" "+per1.getAge());
    }

}
```



# javaWeb

## 1、基本概念

#### 1.1前言

- web网页的意思
- 情态web
  - html、css
- 动态web
  - 淘宝
  - 技术栈:Servlet/JSP、ASP、PHP

#### 1.2web应用程序

web应用程序：可以提供浏览器访问的程序

- a.html、b.html

- URL： U resource location
- 这个统一的web资源会被放在同一文件夹下，web应用程序--》Tomcat:服务器
- 一个web应用由多部分组成（静态web、动态web）
  - html、css、js
  - jsp、servlet
  - java程序
  - jar包
  - 配置文件（Properties）

war应用程序编写完毕后，若想提供给外界访问：需要一个服务器同一管理

### 1.3静态web

![image-20210802180841954](G:\技术积累\Java SE学习.assets\image-20210802180841954.png)

### 1.4、动态web

页面会动态展示：“web效果因人而异”

![image-20210802200411930](G:\技术积累\Java SE学习.assets\image-20210802200411930.png)

缺点：

- 假如服务器的动态web资源出现了错误，需要重新编写后台程序，重新发布
  - 停机维护

优点

- 可以和数据动态交互（数据持久化）
- Web页面可以动态跟新，所有用户看到的不是同一页面

![image-20210802200538819](G:\技术积累\Java SE学习.assets\image-20210802200538819.png)

## 2、web服务器

### 2.1技术讲解

JSP/Servlet

B/S:浏览和服务器

C/S:客户端和服务器

- sun公司主推的B/S框架

### 2.2 web服务器

服务器是一种被动的操作，用来处理用户的一些请求和给用户一些响应信息；

**Tomcat**

Tomcat是Apache 软件基金会（Apache Software Foundation）的Jakarta 项目中的一个核心项目，由[Apache](https://baike.baidu.com/item/Apache/6265)、Sun 和其他一些公司及个人共同开发而成。由于有了Sun 的参与和支持，最新的Servlet 和JSP 规范总是能在Tomcat 中得到体现，Tomcat 5支持最新的Servlet 2.4 和JSP 2.0 规范。因为Tomcat 技术先进、性能稳定，而且免费，因而深受Java 爱好者的喜爱并得到了部分软件开发商的认可，成为目前比较流行的Web 应用服务器。

Tomcat 服务器是一个免费的开放源代码的Web 应用服务器，属于轻量级应用[服务器](https://baike.baidu.com/item/服务器)，在中小型系统和并发访问用户不是很多的场合下被普遍使用，是开发和调试JSP 程序的首选。对于一个初学者来说，最佳选择

Tomcat 实际上运行JSP 页面和Servlet

## 3、Tomcat

#### 面试题

```xml
<Host name="localhost"  appBase="webapps"
      unpackWARs="true" autoDeploy="true">
 <!--修改localhost为www.xxx.com，再去访问www.xxx.com无法访问>    
```

改进：进入C:\Windows\System32\drivers\etc\hosts

修改下面hosts，可以通过tomcat.imooc.com，访问本地

```
# localhost name resolution is handled within DNS itself.
#	127.0.0.1       localhost
#	::1             localhost
127.0.0.1 image.imooc.com
127.0.0.1 tomcat.imooc.com
127.0.0.1       activate.navicat.com
114.116.228.59 test.public.mongodb
114.115.240.14 test.public.mysql
114.116.228.59 test.public.redis
114.116.228.59 test.public.activemq

```

可以配置启动的端口

- tomcat 8080
- mysql 3306
- https 443
- http 80

```xml
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" URLEncoding="UTF-8"/>
```


#### 面试题

谈谈网站如何进行访问的！

1、输入一个域名

2、检查本机的C:\Windows\System32\drivers\etc\hosts配置文件下有没有这个域名映射

​	1、有，直接范围对应的ip地址，这个地址中，有我们需要访问的web程序，可以直接访问

	127.0.0.1       localhost
	
	127.0.0.1 image.imooc.com
	127.0.0.1 tomcat.imooc.com
	127.0.0.1       activate.navicat.com
​	2、没有，去DNS服务器找，找到就返回，找不到就返回找不到

![image-20210802213600810](G:\技术积累\Java SE学习.assets\image-20210802213600810.png)

网站该有的结构

```java
--webapps : tomcat服务器的web目录
    -ROOT
    -kuangstudy：网站目录名字
    	- WEB_INF
            - class: java程序
            - lib :web引用依赖的jar包
            - web.xml：网站配置文件
     	- index.html 默认首页
        - static
            - css
                -style.css
            -js
            -img
               
```



## 4、Http请求

### 4.1、什么是Http

超文本传输协议（Hyper Text Transfer Protocol，HTTP）是一个简单的请求-响应协议，它通常运行在[TCP](https://baike.baidu.com/item/TCP/33012)之上。

- 文本：html
- 超文本：图片、音乐

### 4.2 两个时代

- http1.0
  - HTTP/1.0 客户端可以与web服务器连接后，只能获取一个web资源，断开连接
- http2.0
  - HTTP/1.1 客户端与web服务器连接后，可以获得多个web资源

### 4.3 Http请求

```
Request URL: https://www.baidu.com/    请求地址
Request Method: GET	请求方法
Status Code: 200 OK	状态码
Remote Address: 14.215.177.39:443		远程地址

```



### 4.4Http响应

```java
Cache-Control: private
Connection: keep-alive
Content-Encoding: gzip
Content-Length: 78
Content-Type: text/html;charset=utf-8
Date: Mon, 02 Aug 2021 13:49:00 GMT
Expires: Mon, 02 Aug 2021 13:49:00 GMT
Server: BWS/1.0
Vary: Accept-Encoding
```

```java
Accept: text/plain, */*; q=0.01
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9
Connection: keep-alive
Cookie: BAIDUID=8598B539E86F626882E9930AFAB9E264:FG=1; BIDUPSID=8598B539E86F626882E9930AFAB9E264; PSTM=1532694845; BD_UPN=12314753; BDUSS=QwQlFJaXdWSHBjMmFSbC1kdU9pMDJZN0VWYy1TZEFTcUVMZ1F3aFFrVlNVcnhmRVFBQUFBJCQAAAAAAAAAAAEAAADhjTKvudi8~LTKaG8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFLFlF9SxZRfT0; BDUSS_BFESS=QwQlFJaXdWSHBjMmFSbC1kdU9pMDJZN0VWYy1TZEFTcUVMZ1F3aFFrVlNVcnhmRVFBQUFBJCQAAAAAAAAAAAEAAADhjTKvudi8~LTKaG8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFLFlF9SxZRfT0; MCITY=-%3A; __yjs_duid=1_cf78591b030cd1a975e272d4d9407fee1619836876554; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; H_PS_PSSID=34303_33763_34336_34364_31253_34377_34004_34072_34283_26350_34214_34367; delPer=0; BD_CK_SAM=1; PSINO=7; BAIDUID_BFESS=8598B539E86F626882E9930AFAB9E264:FG=1; shifen[268325072003_50791]=1627895100; BCLID=10805245477328860775; BDSFRCVID=DEAOJeC624rVYSJH3zkkhXUjSTrTu33TH6aoYtGBL4PRP4WYU0raEG0PEU8g0KA-S2EqogKKy2OTH9DF_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF=tRAOoC8atDvHjjrP-trf5DCShUFs0UQCB2Q-5KL-JPo4ofO_y5b0MPPJWboBQh3HQTIeoMbdJJjootbjLxkM0hDrjbJkL6OHKmTxoUJNQCnJhhvqqq-KQJ_ebPRih6j9Qg-8KpQ7tt5W8ncFbT7l5hKpbt-q0x-jLTnhVn0MBCK0HPonHj_aD5bW3f; BCLID_BFESS=10805245477328860775; BDSFRCVID_BFESS=DEAOJeC624rVYSJH3zkkhXUjSTrTu33TH6aoYtGBL4PRP4WYU0raEG0PEU8g0KA-S2EqogKKy2OTH9DF_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF_BFESS=tRAOoC8atDvHjjrP-trf5DCShUFs0UQCB2Q-5KL-JPo4ofO_y5b0MPPJWboBQh3HQTIeoMbdJJjootbjLxkM0hDrjbJkL6OHKmTxoUJNQCnJhhvqqq-KQJ_ebPRih6j9Qg-8KpQ7tt5W8ncFbT7l5hKpbt-q0x-jLTnhVn0MBCK0HPonHj_aD5bW3f; __yjs_st=2_NWE2NTc1MTIwZmFjOTRjMThhYTM2MjEwMTE0YTZhYmRkZmRmY2JkZTY3OWFlZTk4NjM1ZmI2YjhjMWZiNWE2NGFjMjNhNWRlNTYyYTJiZjE3MDI0OTA5YzEzYWQ5OGNkMTBhMzg5MTNmM2IzYmRhZmI4ZGM2YjNkZDVlY2E4ZWNkYzA3MTM1ZDNlOGIwNDAxYTE1NGJkZTlhYTJhZjAxNTViZWNhZWJhMGQ1ODAwNGMwZjk0ZGRjZjYyZmZjNGFkZWM2NjdmMGJmZTBlMmYyMDhkZmU0NzI5MTg0ZjQ4ZjBjOWVhZTRkMjliMzczM2NjMjQ5M2RhYzQ5MmIyNmJiMl83X2VjN2M4OTIw; BD_HOME=1; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; COOKIE_SESSION=10716_2_6_6_7_9_0_0_4_5_5_0_13463_0_3_3_1627905818_1627895102_1627905815%7C7%230_1_1627895099%7C1; H_PS_645EC=c383piVVwluhP0iGwJFNZYQMvi8v%2Bv3da5PGcrTwSXng%2BJ8qdADVWXMeW1kvlpGcwWeI; BA_HECTOR=210laka08l80a52kvi1ggftuc0q
Host: www.baidu.com
Referer: https://www.baidu.com/
sec-ch-ua: "Chromium";v="92", " Not A;Brand";v="99", "Google Chrome";v="92"
sec-ch-ua-mobile: ?0
Sec-Fetch-Dest: empty
Sec-Fetch-Mode: cors
Sec-Fetch-Site: same-origin
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36
X-Requested-With: XMLHttpRequest
```

1、请求行

2、消息头

```java
Accept: text/plain, */*; q=0.01
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9  
Cache-Control :缓存控制
Connection: keep-alive ：告诉主机是请求是断开还是保持连接
Host: www.baidu.com

```



1、响应体：

2、响应状态码

200： 成功

3xx:请求重定向

- 重定向：你重新到我给你的新位置中去

4xx: 找不到资源 404

- 资源不存在

5xx:服务器代码错误 500 502：网关错误

设计思想：

适配器：

https://www.cnblogs.com/lingshang/p/10708800.html

回调函数：

定时器： 

Swagger: 接口函数



面试题：请你聊聊重定向和转发的区别？

相同点：

- 页面都会实现跳转

不同点

- 请求转发的时候，url不会发生变化 307
- 重定向的时候，url地址栏会发生变化 302



## Cookie、Session

保存会话的两种技术Cookie、session

Session是服务器建立的连接的

- 服务器技术，利用这个技术，可以保存用户的会话信息？我们可以把信息数据保存在Session中

Cookie是客户端保存的

- 客户端技术（响应、请求）



## 6、Servlet

### Servlet简介

- Servlet就是sun公司开发动态web的一门技术
- Sun在这些API中提供一个接口叫做Servlet，如果想要开发一个Servlet程序，需要完成2Steps
  - 编写一个类，实现Servlet接口
  - 把开发好的java类部署到web服务器
- 实现Servlet接口，默认HttpServlet



```java
package com.kuang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wbw
 * @date 2021/8/2 16:35
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getInputStream();
        System.out.println("进入doGet");
        PrintWriter writer = resp.getWriter();//response streamd
        writer.print("Hello Servlet");
        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

```



编写Servlet的映射

为什么需要映射：写的是java程序，但是浏览器需要连接web服务器，因此需要在web服务中注册我们写的Servlet，还需给他一个浏览器可以访问的路径

```xml

<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <servlet>
    <servlet-name>hello</servlet-name>
    <servlet-class>com.kuang.servlet.HelloServlet</>
  </servlet>
<!-- 注册Servlet -->
  <servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/hello</url-pattern>
<!--Servlet的请求路径-->
  </servlet-mapping>

</web-app>

```

![image-20210803122149941](G:\技术积累\Java SE学习.assets\image-20210803122149941.png)

### 6.4 Mapping

1、一个Servlet可以指定一个映射路径

```xnl
  <servlet>
    <servlet-name>hello</servlet-name>
    <servlet-class>com.kuang.servlet.HelloServlet</servlet-class>
  </servlet>
  <!-- 注册Servlet -->
  <servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/hello</url-pattern>
    <!--Servlet的请求路径-->
  </servlet-mapping>
```

2、一个Servlet可以指定多个映射路径

3、一个Servlet可以指定通用的路径



### 6.5、ServletContext

![image-20210807163057040](G:\技术积累\Java SE学习.assets\image-20210807163057040.png)

web容器启动的时候，它会为每个web程序都创一个ServletContext对象，它代表了当前的web应用；

- 共享数据

  我在这个Servlet中保存的数据，可以共享到其他Servlet

#### 1、共享数据

```java
package com.kuang.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wbw
 * @date 2021/8/7 14:52
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //this.getInitParameter(); //初始化参数
//        this.getServletConfig(); //Servlet配置
//        this.getServletContext(); //上下文
        ServletContext context = this.getServletContext();
        String username = "wbw";//数据
        context.setAttribute("username", username); //将一个数据保存在ServletContext中，名字为username,值username

        System.out.println("Hello World!!!");
    }
}
```

取数据

```java
package com.kuang.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wbw
 * @date 2021/8/7 15:18
 */
public class GetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        String username = (String)context.getAttribute("username");
        System.out.println("TT");
        resp.setContentType("test/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print("名字" + username);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

```xml
  <servlet>
    <servlet-name>hello</servlet-name>
    <servlet-class>com.kuang.servlet.HelloServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/hello</url-pattern>
  </servlet-mapping>

  <servlet>
    <servlet-name>geta</servlet-name>
    <servlet-class>com.kuang.servlet.GetServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>geta</servlet-name>
    <url-pattern>/geta</url-pattern>
  </servlet-mapping>
```

测试访问结果





#### 2、获取初始化参数

```xml
<context-param>
  <param-name>url</param-name>
  <param-value>jdbc:mysql</param-value>
</context-param>
```

```java
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        String url = context.getInitParameter("url");
        resp.getWriter().print(url);
//        context.getInitParameter();

    }
```



#### 3、请求转发

**请求转发和重定向的区别？**

![image-20210807163948813](G:\技术积累\Java SE学习.assets\image-20210807163948813.png)

#### 4、读取资源文件

Properties

- 在java目录下新建立properties
- 在resources目录下新建properties

发现：都被打包到同一路径下：classes，我们俗称这个路径为classpath

思路：需要一个文件流

```properties
username=root
password=123456
```



```java
public class ServletDemo05 extends HelloServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入Demo05");
        ServletContext context = this.getServletContext();
        InputStream is = context.getResourceAsStream("/WEB-INF/classes/db.properties");

        Properties prop = new Properties();
        prop.load(is);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        resp.getWriter().print(username +":"+password);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

```

### 6.6、HttpServletRequest

web服务器收到客户端的http请求，针对这个请求，分别创建以一个代表请求HttpServletRequest对象，代表响应的一个HttpServletResponse

- 客户端请求参数，HttpServletRequest
- 客户端响应消息：HttpServletResponse