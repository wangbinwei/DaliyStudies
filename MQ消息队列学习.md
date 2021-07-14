## 消息队列的学习

消息队列基本有4个部分组成：Producer、Exchange、Queues、Consumer

都是服务JMS ：java Message Service 

Server: 又称Broker,接受客户端的连接，实现AMQP实体服务

Connection:连接，应用程序与Broker的网络连接

Channel:网络信道，Channel进行消息读写的通道，客户端可以建立多个Channel，每个Channel代表一个会话任务

Message:消息，由properties和Body组成。Properties可以对消息进行修饰，比如消息的优先级、延迟等高级特性；Body就是消息体内容。

Virtual Host：虚拟地址，用于进行逻辑隔离，最上层的消息路由。一个Virtual Host里面可以有诺干个Exchange和Queue,同一个VirtualHost不能有相同的Exchange和Queue

Exchange:交换机，接收消息，根据路由键转发消息道绑定的队列

Binding:Exchange和Queue之间的虚拟连接，binding中可以包含routing Key

RabbitMQ四种模式 fanout、direct、topic、headers

+ **direct** : exchange和queues之间是通过routing key之间来进行消息转发，需要routing key 和 BindingKey相同才能转发消息， 例如 Routing Key: "green"，则只能发送到Binding key： “green”的队列上

  ![img](https://pic2.zhimg.com/80/v2-c439643980a67bade215dc99a694c69d_720w.jpg)

  只考虑把重要的日志消息写入磁盘文件,例如把error级别的消息写入磁盘文件的Queue

+ **topic**: exchange和queues之间是通过routing key之间来进行消息转发，不同的是routing key可以通过通配符来进行匹配相应的Queues，*代表匹配一个单词，#代表匹配空和多个单词

  ![img](https://pic4.zhimg.com/80/v2-c735dc7d3e0ea74acc7f933abeaa40b7_720w.jpg)

  应用场景：RoutingKey 可以定义为消息来源.级别如 order.info、user.error等。处理所有来源为user的Queue就可以通过user.*绑定到Topic Exchange上

+ **fanout**: 就是广播的形式，全部广播出去。

  ![image-20201030135406019](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20201030135406019.png)

应用场景：就是需要一个queue来输出，一个queue进行消息记录写入磁盘文件的日志消息



### ActiveMq的学习





其中的测试都是用的是Docker运行的消息队列的服务器，其中有消息队列的使用端口和web页面管理端口（对外映射端口）

