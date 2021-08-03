# JVM类加载机制

运行 main函数的过程，先将把.class从磁盘中读取出来，加载到内存中。

![image-20210730195431128](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210730195431128.png)

加载---》验证--》准备---》解析--》初始化

加载：

验证：

准备：先静态变量分配内存，并赋予初始值

解析：符号引用转为直接引用， 有两种链接： 动态链接 、静态链接



## 要点

类加载器  ：AppClassLoder、EXTClassLoder、自定义Classloader

双亲委派机制

![image-20210731201621885](G:\技术积累\JVM调优.assets\image-20210731201621885.png)

## Tomcat打破双亲委派机制

tomcat的几个主要类加载器： 

commonLoader：Tomcat最基本的类加载器，加载路径中的class可以被Tomcat容 器本身以及各个Webapp访问； 

catalinaLoader：Tomcat容器私有的类加载器，加载路径中的class对于Webapp不 可见；

sharedLoader：各个Webapp共享的类加载器，加载路径中的class对于所有 Webapp可见，但是对于Tomcat容器不可见； 

WebappClassLoader：各个Webapp私有的类加载器，加载路径中的class只对当前 Webapp可见，比如加载war包里相关的类，每个war包应用都有自己的WebappClassLoader，实现相互隔离，比如不同war包应用引入了不同的spring版本， 这样实现就能加载各自的spring版本； 



JSP:热加载，会在加载器中起一个线程，监听文件夹的变动，如果变动了，就会重新加载。





![image-20210731205937236](G:\技术积累\JVM调优.assets\image-20210731205937236.png)







# JVM内存模型深度分析和优化



![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\08563092893543608aac4d689a0e0e5f\clipboard.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\ea84e2602096465ab2e63fa736f7c022\clipboard.png)



程序计数器：1、线程内部 存放的下一句要运行的代码  2、main线程，存放的线程

栈：存的是局部变量

java每当有一个程序运行，会从栈中挖一块到栈中运行，因此会叫这个栈叫线程栈中比较好

运行，main的函数会创建一个栈帧，运行到compute函数会创建一个==栈帧== 。 compute先结束，会先释放资源

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\35561c67c7834302a03cfb6008cc16b2\clipboard.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\022633120a9a4920bec0ec88bdd94340\clipboard.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\ab9e1c3d3b6e490ba91933fcdf75a997\clipboard.png)

**栈中有内存地址指向堆中，对象放在堆中**

**堆: 实际对象放在堆中,**

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\e68a7eff861e40f1b1e1c1f0156969aa\clipboard.png)

方法区：存的是常量 + 情态变量

```java
public static User user = new User(); 
//方法名User存放在方法区中，它值是new出来的对象放在堆中，也是指针的关系  方法区---》 堆
```

本地方法栈：

堆和方法区是公有的

栈、本地方法栈、程序计数器是每个线程公有的

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\5fddc70f52a34c39960140c4e8f6503d\clipboard.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\78f00bdb184249fd967a606b4c1fca2c\jvm内存模型.png)

执行引擎去执行垃圾收集线程gc

**堆**：  **老年代、年轻代8:1:1**，gc: garbage collect垃圾收集器 。每次执行minor gc，每个对象都会+1， survivor放不下会进入老年代  or 只要分代年龄15 就会进入老年代	

**minor gc**:  当Eden区垃圾放满了， 就会执行minor gc ，回收堆和方法区，会将Eden+survivor中的对象都去做gc。

**什么样的对象会被放到老年代？**

静态变量、对象值、缓存对象、Spring容器中的对象

当老年代满了就会执行 **full gc**: 会对全部进行垃圾回收。



gc Root: gc就是会找gcRoot ,然后依次把这条链条直接干掉

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\4f6eee4d9e5e4d64a8eadfe505e3f523\clipboard.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\f268dd27d0234044a577f6bf49b593df\clipboard.png)

减少GC, 每次GC，垃圾收集器 底层会 STW: Stop The World 

### 面试题

#### **面试题： 为什么STW不好，jvm设计师会设计这个？**

因为不STW，堆中的对象状态可能会改变（可能是垃圾or不是垃圾），所以开始gc要STW，让线程资源全部去做gc，**gcRoot这条链会变化**

#### 面试题：能否对JVM调优，让其几乎不发生Full Gc

可以，将年轻代的大小放大

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\ed62c129f44e43c0bc064e6bca1a07fc\clipboard.png)

大内存，提高年轻代的大小，但是执行GC的 STW会比较久。

解决方法：使用G1,每次垃圾收集器一部分，每次收集一部分内存，这样gc效率快

细节：就是新生成的一批对象大小大于年轻代的50%

JVM调优——ARTHAS 工具调优



# JVM对象创建与内存分配机制深度剥析

分配内存

- 指针碰撞
- 空闲列表

###  指针压缩



### 对象内存分配

![image-20210801212652899](G:\技术积累\JVM调优.assets\image-20210801212652899.png)

堆对应的EDEN区 +老年代

解决并发问题

- CAS: compare-and-swap

- 本地线程分配缓冲（Thread Local Allocation Buffer，TLAB）



**栈帧**：栈帧运行完就会销毁

**对象逃逸分析**：就是分析对象动态作用域，当一个对象在方法中被定义后，它可能被外部方法所引用，例如作为调用参 数传递到其他地方中（or Return xxx）。

非对象逃逸：可以将对象分到栈帧空间

**标量替换**： 就栈帧可能没有一块完整的内存空间可以分配一个完成的对象，使用标量替换可以将一个大对象中的成员变量拆开来分别存放。

### 什么样的对象进入老年代

- 大对象直接放入老年代。
- 长期存活的对象将进入老年代，分代年龄到15岁就进入老年代



### 案例分析





![image-20210801214048045](G:\技术积累\JVM调优.assets\image-20210801214048045.png)

因为大约13s后，eden区快放满了，就执行minor gc。但是还有一批正在运行的60M对象，按理会放到survivor区，但是survival有限制，如果新对象大于survivor区域的50%的大小会直接进入老年代。因此每几分钟就会将老年代放满，然后会执行fullgc， 会执行STW，因此会很卡顿。



### 老年代担保机制

![image-20210801214938584](G:\技术积累\JVM调优.assets\image-20210801214938584.png)



### 对象内存回收

- 引用计数器：这个对象每被引用一次，计数会+1,当计数==0的时候，就会被回收
- 可达性分析算法
- ![image-20210801215731447](G:\技术积累\JVM调优.assets\image-20210801215731447.png)



元空间做完 full gc也回收不了多大的内存