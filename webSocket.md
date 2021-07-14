### 实时通讯方法

1、Ajax 轮询

2、Long pull

3、websocket  一种协议，其于http协议。持久化连接，

不同http，基本都是一个request 就对应一个response

websocket 只需要建立一个连接，相当于长连接，服务器就可以源源不断的向Client推送消息

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\b8825cc1fd8b4fe6914547734bf846fa\clipboard.png)

### **过滤器与拦截器的区别**

  过滤器可以简单的理解为“取你所想取”，过滤器关注的是web请求；拦截器可以简单的理解为“拒你所想拒”，拦截器关注的是方法调用，比如拦截
敏感词汇。

- 4.1，拦截器是基于java反射机制来实现的，而过滤器是基于函数回调来实现的。（有人说，拦截器是基于动态代理来实现的）

- 4.2，拦截器不依赖servlet容器，过滤器依赖于servlet容器。

- 4.3，拦截器只对Action起作用，过滤器可以对所有请求起作用。
- 4.4，拦截器可以访问Action上下文和值栈中的对象，过滤器不能。
- 4.5，在Action的生命周期中，拦截器可以多次调用，而过滤器只能在容器初始化时调用一次。

#### 总结：

  **1.过滤器（Filter）**：所谓过滤器顾名思义是用来过滤的，Java的过滤器能够为我们提供系统级别的过滤，也就是说，能过滤所有的web请求，
这一点，是拦截器无法做到的。在Java Web中，你传入的request,response提前过滤掉一些信息，或者提前设置一些参数，然后再传入servlet或
者struts的action进行业务逻辑，比如过滤掉非法url（不是login.do的地址请求，如果用户没有登陆都过滤掉）,或者在传入servlet或者struts
的action前统一设置字符集，或者去除掉一些非法字符（聊天室经常用到的，一些骂人的话）。filter 流程是线性的，url传来之后，检查之后，
可保持原来的流程继续向下执行，被下一个filter, servlet接收。
  **2.监听器（Listener）**：Java的监听器，也是系统级别的监听。监听器随web应用的启动而启动。Java的监听器在c/s模式里面经常用到，它
会对特定的事件产生产生一个处理。监听在很多模式下用到，比如说观察者模式，就是一个使用监听器来实现的，在比如统计网站的在线人数。
又比如struts2可以用监听来启动。Servlet监听器用于监听一些重要事件的发生，监听器对象可以在事情发生前、发生后可以做一些必要的处理。
  **3.拦截器（Interceptor）**：java里的拦截器提供的是非系统级别的拦截，也就是说，就覆盖面来说，拦截器不如过滤器强大，但是更有针对性。
Java中的拦截器是基于Java反射机制实现的，更准确的划分，应该是基于JDK实现的动态代理。它依赖于具体的接口，在运行期间动态生成字节码。
拦截器是动态拦截Action调用的对象，它提供了一种机制可以使开发者在一个Action执行的前后执行一段代码，也可以在一个Action执行前阻止其
执行，同时也提供了一种可以提取Action中可重用部分代码的方式。在AOP中，拦截器用于在某个方法或者字段被访问之前，进行拦截然后再之前或
者之后加入某些操作。java的拦截器主要是用在插件上，扩展件上比如 Hibernate Spring Struts2等，有点类似面向切片的技术，在用之前先要在
配置文件即xml，文件里声明一段的那个东西。

![img](https://images2017.cnblogs.com/blog/883340/201708/883340-20170830152048640-620252394.png)



#### 注解postConstruct：

spring中Constructor、@Autowired、@PostConstruct的顺序

- 其实从依赖注入的字面意思就可以知道，要将对象p注入到对象a，那么首先就必须得生成对象a和对象p，才能执行注入。所以，如果一个类A中有个成员变量p被@Autowried注解，那么@Autowired注入是发生在A的构造方法执行完之后的。

- 如果想在生成对象时完成某些**初始化操作**，而偏偏这些初始化操作又依赖于依赖注入，那么就无法在构造函数中实现。为此，可以使用@PostConstruct注解一个方法来完成初始化，@PostConstruct注解的方法将会在依赖注入完成后被自动调用。

Constructor >> @Autowired >> @PostConstruct

```java
public Class AAA {

    @Autowired

    private BBB b;



    public AAA() {

        System.out.println("此时b还未被注入: b = " + b);

    }

    @PostConstruct

    private void init() {

        System.out.println("@PostConstruct将在依赖注入完成后被自动调用: b = " + b);

    }

}

```

