## NIO通信   会问Netty实现，优势是什么？

1、并发高 2、传输快 3、封装好

### **1、Netty为什么高并发**

原因是Netty的Selector，在NIO中，当一个Socket建立好了，Thread不会阻塞这个去接受这个Socket，而是将这个请求交给Selector，selector会不断的遍历所有的Socket一旦有一个Socket建立完成，他会通知Thread，然后Thread处理完数据再返回给客户端——这个过程是不阻塞的，这样就能让一个Thread处理更多的请求了。

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\adfd80e56956469f87a8138e05888352\clipboard.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\5be7139cdd2c468cbdabacfaf94be624\clipboard.png)

## NIO的处理流程

### **2、Netty为什么传输快？**

Netty的传输快其实也是依赖了NIO的一个特性——*零拷贝*。我们知道，Java的内存有堆内存、栈内存和字符串常量池等等，其中堆内存是占用内存空间最大的一块，也是Java对象存放的地方，一般我们的数据如果需要从IO读取到堆内存，中间需要经过Socket缓冲区，也就是说一个数据会被拷贝两次才能到达他的的终点，如果数据量大，就会造成不必要的资源浪费。

Netty针对这种情况，使用了NIO中的另一大特性——零拷贝，当他需要接收数据的时候，他会在堆内存之外开辟一块内存，数据就直接从IO读到了那块内存中去，在netty里面通过ByteBuf可以直接对这些数据进行直接操作，从而加快了传输速度。

传统的数据拷贝：，平时写一个服务端程序，文件发送，一般先查询自己缓存里面有没有，如果没有则到内存上找这一过程一般通过DMA来完成，将 内核缓存区的内容拷贝到应用程序缓冲区中去，接下来write系统调用用户程序缓存区上的内容拷贝网络堆栈相关的内核缓冲区中，最后socket再把内核缓冲区的内容发送到网卡。

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\dfaf39f82dbf47a79b49d3c9de12524e\clipboard.png)

传统数据拷贝

从上图中可以看出，共产生了四次数据拷贝，即使使用了DMA来处理了与硬件的通讯，CPU仍然需要处理两次数据拷贝，与此同时，在用户态与内核态也发生了多次上下文切换，无疑也加重了CPU负担。

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\04abfa5b273443a98718ec6a79b8d129\clipboard.png)

零拷贝

应用程序调用mmap()，磁盘上的数据会通过DMA被拷贝的内核缓冲区，接着操作系统会把这段内核缓冲区与应用程序共享，这样就不需要把内核缓冲区的内容往用户空间拷贝。应用程序再调用write(),操作系统直接将内核缓冲区的内容拷贝到socket缓冲区中，这一切都发生在内核态，最后，socket缓冲区再把数据发到网卡去。

### 3、Netty封装好

缓冲区Buffer

通道Channel

多路复用器Selector  不断轮询Channel,如果Channel以就绪则就可以读or写其中消息，SelectorKey	

Netty   NioEventLoop  ---》thread  建立连接、读写客户端的数据

两个线程：监听客户端的连接、客户端的读写

Curl:用于请求Web服务器。他的名字是Client 的URL工具的意思

Channel 简单对一条连接的封装

Channel  ---有个Pinpline ，依次处理其中的Handler  

![image-20210725235014430](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210725235014430.png)

![img](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210726082620537.png)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\5a042ec0bd4b49a58638ec97c8fee7af\clipboard.png)

  

Pipeline 逻辑处理链

ChannelHandler 处理逻辑

ByteBuf 基于BuyeBuf来操作

伪异步I/O通信 

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\1ec070d74b0842e7af94cb4e3b94a762\clipboard.png)

AIO 连接注册读写事件和回调函数

读写方法异步

主动通知程序

BIO  Blocking I/O

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\4d8236a7f046450da724575004851b20\clipboard.png)

### 4、Netty的生命周期

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\80bc35c4f78241a59eca389407d877e0\clipboard.png)



# Netty的Reactor线程模型

![image-20210719131932565](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210719131932565.png)

```java
EventLoop mianGroup = new NioEventLoop //主线程，负责连接。 subGroup负责处理hanle建立服务器  
ServerBootStrap server = new ServerBootStrap();

server.group(mianGroup, subGroup)

.channel(NioServerChannel.class)

.chileHandler(自己定于) ;

//启动server,并设置8080的端口号

ChannelFuture  future = server.bind(8080).sync();

future.channel().closeFuture().sync();

ServerInitializer ChannelPipeline pipeline = socketChannel.pipiline();// 初始化

pipeline.addLast();
```





```java
EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)EventLoopGroup workerGroup = new NioEventLoopGroup();try {
    ServerBootstrap b = new ServerBootstrap(); // (2)
    b.group(bossGroup, workerGroup)　　// (3)
     .channel(NioServerSocketChannel.class) // (4)     .handler(new LoggingHandler())    // (5)
     .childHandler(new ChannelInitializer<SocketChannel>() { // (6)
         @Override
         public void initChannel(SocketChannel ch) throws Exception {
             ch.pipeline().addLast(new DiscardServerHandler());
         }
     })
     .option(ChannelOption.SO_BACKLOG, 128)          // (7)
     .childOption(ChannelOption.SO_KEEPALIVE, true); // (8)
    
     // Bind and start to accept incoming connections.
     ChannelFuture f = b.bind(port).sync(); // (9)
    
     // Wait until the server socket is closed.
     // In this example, this does not happen, but you can do that to gracefully
     // shut down your server.
     f.channel().closeFuture().sync();
} finally {
    workerGroup.shutdownGracefully();
    bossGroup.shutdownGracefully();
}
```



上面这段代码展示了服务端的一个基本步骤：

(1)、 初始化用于Acceptor的主"线程池"以及用于I/O工作的从"线程池"；
(2)、 初始化ServerBootstrap实例， 此实例是netty服务端应用开发的入口，也是本篇介绍的重点， 下面我们会深入分析；
(3)、 通过ServerBootstrap的group方法，设置（1）中初始化的主从"线程池"；
(4)、 指定通道channel的类型，由于是服务端，故而是NioServerSocketChannel；
(5)、 设置ServerSocketChannel的处理器（此处不详述，后面的系列会进行深入分析）
(6)、 设置子通道也就是SocketChannel的处理器， 其内部是实际业务开发的"主战场"（此处不详述，后面的系列会进行深入分析）
(7)、 配置ServerSocketChannel的选项
(8)、 配置子通道也就是SocketChannel的选项
(9)、 绑定并侦听某个端口

客户端

```java
public class TimeClient {
    public static void main(String[] args) throws Exception {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // (1)
        
        try {
            Bootstrap b = new Bootstrap(); // (2)
            b.group(workerGroup); // (3)
            b.channel(NioSocketChannel.class); // (4)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (5)
            b.handler(new ChannelInitializer<SocketChannel>() { // (6)
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });
            
            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (7)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
```

(1)、 初始化用于连接及I/O工作的"线程池"；
(2)、 初始化Bootstrap实例， 此实例是netty客户端应用开发的入口，也是本篇介绍的重点， 下面我们会深入分析；
(3)、 通过Bootstrap的group方法，设置（1）中初始化的"线程池"；
(4)、 指定通道channel的类型，由于是客户端，故而是NioSocketChannel；
(5)、 设置SocketChannel的选项（此处不详述，后面的系列会进行深入分析）；
(6)、 设置SocketChannel的处理器， 其内部是实际业务开发的"主战场"（此处不详述，后面的系列会进行深入分析）；
(7)、 连接指定的服务地址；





## Netty注解

- netty的channelHandler @ChannelHandler.Sharable 添加这个注解，他在生命周期中就是以单例模式中存在

计算机组成原理学的差不多

psk: Public Sharing Key

refactor 重构 

maven是一个项目管理工具，包含pom: Project Object Model项目对象模型