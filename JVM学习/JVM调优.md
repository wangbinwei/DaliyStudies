![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\08563092893543608aac4d689a0e0e5f\clipboard.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\ea84e2602096465ab2e63fa736f7c022\clipboard.png)

栈：存的是局部变量

程序计数器：1、线程内部 存放的下一句要运行的代码  2、main线程，存放的线程

java每当有一个程序运行，会从栈中挖一块到栈中运行

运行，main的函数会创建一个栈帧，运行到compute函数会创建一个栈帧。 compute先结束，会先释放资源

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\35561c67c7834302a03cfb6008cc16b2\clipboard.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\022633120a9a4920bec0ec88bdd94340\clipboard.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\ab9e1c3d3b6e490ba91933fcdf75a997\clipboard.png)

栈中有很多引用地址指向堆中，对象放在堆中

堆: 实际对象放在堆中

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\e68a7eff861e40f1b1e1c1f0156969aa\clipboard.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\5fddc70f52a34c39960140c4e8f6503d\clipboard.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\78f00bdb184249fd967a606b4c1fca2c\jvm内存模型.png)

执行引擎去执行垃圾收集线程gc

堆：老年代、年轻代8:1:1，gc: garbage collect垃圾收集器 。每次执行minor gc，每个对象都会+1， survivor放不下会进入老年代  or 只要分代年龄15 就会进入老年代	

minor gc: 堆年轻代进行垃圾回收。

当老年代满了就会执行 full gc: 会对全部进行垃圾回收。

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\4f6eee4d9e5e4d64a8eadfe505e3f523\clipboard.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\f268dd27d0234044a577f6bf49b593df\clipboard.png)

减少GC, 每次GC，垃圾收集器 底层会 STW: Stop The World 

面试题： 为什么STW不好，jvm设计师会设计这个？

因为不STW，堆中的对象状态可能会改变（可能是垃圾or不是垃圾），所以开始gc要STW，让线程资源全部去做gc

面试题：能否对JVM调优，让其几乎不发生Full Gc

可以，将年轻代的大小放大

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\ed62c129f44e43c0bc064e6bca1a07fc\clipboard.png)

大内存，提高年轻代的大小，但是执行GC的 STW会比较久。

解决方法：使用G1,每次垃圾收集器一部分，每次收集一部分内存，这样gc效率快

细节：就是新生成的一批对象大小大于年轻代的50%

JVM调优——ARTHAS 工具调优