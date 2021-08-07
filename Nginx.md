# Nginx简介

 Nginx是一款轻量级的Web服务器、也是一款反向代理服务器，域名转发

1、可直接支持Rails和PHP的程序

2、Nginx可作为HTTP反向代理服务器

3、作为负载均衡服务器

4、作为邮件代理服务器

5、动静分离

## **反向代理**：

==反向代理服务器位于用户和目标服务器之间，对用户而言反向代理服务器就相当于目标服务器，即用户直接访问反向代理服务器就可以获得目标服务器的资源。== 

> 客户端不需要任何配置就能访问，只需要将请求发送到反向代理服务器，由反向代理服务器去选择目标服务器，获取数据后再返回给客户端。对外就一个服务器，暴露的是反向代理服务器地址，隐藏了真实服务器IP地址。代理对象是服务端，不知道客户端是谁。

![img](https://pics0.baidu.com/feed/e850352ac65c1038bde48bd30e67d414b17e8983.jpeg?token=20a2abacbbbec0466f75ae5537cf144d)

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\04f44a41457e4981a5c61a50b11a217f\clipboard.png)



### 正向代理 （VPN）

**正向代理**，意思是一个位于客户端和原始服务器(origin server)之间的服务器，为了从原始服务器取得内容，客户端向代理发送一个请求并指定目标(原始服务器)，然后代理向原始服务器转交请求并将获得的内容返回给客户端。

类似VPN，你要翻墙，你就要想访问香港的服务器，香港服务器在去访问国外的资源，香港服务器再返回资源

**正向代理和反向代理的区别：**

**正向代理**就是相当于一个路由器的功能

**反向代理**则相当于京东的本地仓库，用户向目标服务器请求资源的时候，反向代理查看一下自己本地仓库是否有该资源，有的话就直接返回给用户。



## 负载均衡;

 负载均衡的意思就是分摊到多个操作单元上进行执行，例如Web服务器、FTP服务器。 就是当有两个以上的服务器时，根据规矩随机的将请求分发到指定的服务器上处理

### 负载均衡的策略

- 1、RR Rounting Robin 中文翻译就是轮询调度，每个请求按时间顺序逐一分配到不同的后端服务器

- 2、权重

- 3、ip_hash 上面的2种方式都有一个问题，那就是下一个请求来的时候请求可能分发到另外一个服务器，当我们的程序不是无状态的时候（采用了session保存数据），这时候就有一个很大的很问题了，比如把登录信息保存到了session中，那么跳转到另外一台服务器的时候就需要重新登录了，所以很多时候我们需要一个客户只访问一个服务器，那么就需要用iphash了，iphash的每个请求按访问ip的hash结果分配，这样每个访客固定访问一个后端服务器，可以解决session的问题。

## 动静分离

静态资源用nginx，静态资源进行缓存，这就是网站静态化的核心思路。

所有的动态请求都交给Tomcat处理，tomcat负责处理jsp和请求，动态资源，

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\ffb08e8d189549d2ac276138c01066e8\clipboard.png)



# Nginx安装



```shell
tar -zxvf xx.tar
./configure
sudo apt-get update
sudo apt-get install libpcre3 libpcre3-dev
make
make install
```





# Nginx的配置

![img](G:\有道云\qq2D1D5CB92B2C0FF061B3D3F82DA32CD1\45f486684ea34911b335ac7094dffda1\clipboard.png)

Nginx默认的配置文件主要有 main、events、http、server、location 五个块组成。其中 http 、server、location 属于嵌套关系。

- main：主要控制Nginx子进程所属的用户和用户组、派生子进程数、错误日志位置与级别、pid位置、子进程优先级、进程对应cpu、进程能够打开的文件描述符数目等。
- events：控制Nginx处理连接的方式。
- http：Nginx处理http请求的主要配置块。
- server：Nginx中主机配置块，可用于配置多个虚拟主机。
- location：server中对应目录级别的控制块，可以有多个。

proxy--代理的意思，这是Nginx的配置文件，当我们访问localhost的时候，就相当于访问localhost:8080





## Nginx 常用命令

```
./nginx 启动
./nginx -s stop
./nginx -s quit 安全退出
./nginx -s reload 重新加载配置文件
ps aux|grep nginx 查看nginx进程
```



## Nginx配置文件

楼宇配置文件

```nginx
server {
    listen       8089;
    server_name  127.0.0.1;
	#负载均衡	
    upstream kuangstudy{
        server 127.0.0.1:8080 weight = 1;
        server 127.0.0.1:8081 weight = 1;
    }
    location / {
        root   /usr/share/nginx/html/dist/;
        index  index.html index.htm;
        try_files $uri $uri/ /index.html =404;
    }

    location /api/ {
        #反向代理配置
        proxy_pass http://serv:8088/;
    }

    location /websocket {
	
	proxy_http_version 1.1;
	proxy_set_header Upgrade $http_upgrade;
	proxy_set_header Connection "Upgrade";
	
	proxy_pass http://serv:8088;
    }

    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   html;
    }
}

```



## Nginx通配符

= 严格匹配。如果这个查询匹配，那么将停止搜索并立即处理此请求。

~ 为区分大小写匹配(可用正则表达式)

!~为区分大小写不匹配

~* 为不区分大小写匹配(可用正则表达式)

!~*为不区分大小写不匹配

^~ 如果把这个前缀用于一个常规字符串,那么告诉nginx 如果路径匹配那么不测试正则表达式。

```
location ^~ /p_w_picpaths/ {

# 匹配任何已 /p_w_picpaths/ 开头的任何查询并且停止搜索。任何正则表达式将不会被测试。

}
```



## Nginx服务器上放地图模型