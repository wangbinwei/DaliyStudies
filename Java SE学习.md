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

ArrayList和LinkedList就是数组和链表之前的优缺点

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

