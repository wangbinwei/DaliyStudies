# Redis

数据结构服务器,redis是一个key-value 存储系统。非关系数据库

### 非关系型数据库的分类

##### 面向高性能并发读写的key-value数据库：

**`key-value`数据库的主要特点是具有极高的并发读写性能**
 `Key-value`数据库是一种以键值对存储数据的一种数据库，类似`Java`中的`map`。可以将整个数据库理解为一个大的`map`，每个键都会对应一个唯一的值。

主流代表为 Redis， Amazon DynamoDB， Memcached，Microsoft Azure Cosmos DB和Hazelcast

##### 面向海量数据访问的面向文档数据库：

**这类数据库的主要特点是在海量的数据中可以快速的查询数据**
 文档存储通常使用内部表示法，可以直接在应用程序中处理，主要是`JSON`。`JSON`文档也可以作为纯文本存储在键值存储或关系数据库系统中。
 **主流代表为[MongoDB](https://www.mongodb.com/)，[Amazon DynamoDB](https://aws.amazon.com/cn/dynamodb/)，[Couchbase](https://www.couchbase.com/)，
 [Microsoft Azure Cosmos DB](https://azure.microsoft.com/en-us/services/cosmos-db/)和[CouchDB](https://couchdb.apache.org/)**

##### 面向搜索数据内容的搜索引擎：

**搜索引擎是专门用于搜索数据内容的NoSQL数据库管理系统。**
 主要是用于对海量数据进行近实时的处理和分析处理，可用于机器学习和数据挖掘
 **主流代表为[Elasticsearch](https://www.elastic.co/products/elasticsearch)，[Splunk](https://www.splunk.com/zh-hans_cn)，[Solr](https://lucene.apache.org/solr/)，[MarkLogic](https://www.marklogic.com/)和[Sphinx](http://sphinxsearch.com/)**

CAP: Consistency、Availability、Partition tolerance ，**一个分布式系统不可能同时满足C(一致性)、A(可用性)、P(分区容错性)**三个基本需求，并且最多只能满足其中的两项。



##### 当今十大主流的关系型数据库

**[Oracle](https://www.oracle.com/database/index.html)，[Microsoft SQL Server](https://www.microsoft.com/en-us/sql-server/)，[MySQL](https://www.mysql.com/)，[PostgreSQL](https://www.postgresql.org/)，[DB2](https://www.ibm.com/analytics/us/en/db2/)，
 [Microsoft Access](https://products.office.com/zh-cn/access)， [SQLite](https://www.sqlite.org/)，[Teradata](https://www.teradata.com.cn/)，[MariaDB](https://mariadb.org/)(MySQL的一个分支)，[SAP](https://www.sap.com/)**



## 1、redis的安装和启动

### 1.1 redis的安装



### 1.2 redis的启动

windows环境下进入安装的redis的安装包中，输入以下命令

```shell
redis-server redis.windows.conf
```

设置服务命令,

```dos
redis-server --service-install redis.windows-service.conf --loglevel verbose
```





卸载服务：redis-server --service-uninstall

开启服务：redis-server --service-start

停止服务：redis-server --service-stop



### 1.3 测试连接



```
redis-cli.exe -h 127.0.0.1 -p 6379
```



## 2、Redis的数据结构及使⽤场景 

### Redis的数据结构有： 

- 1. 字符串：可以⽤来做最简单的数据缓存，可以缓存某个简单的字符串，也可以缓存某个json格式的字符 

串，Redis分布式锁的实现就利⽤了这种数据结构，还包括可以实现计数器、Session共享、分布式ID 

- 2. 哈希表：可以⽤来存储⼀些key-value对，更适合⽤来存储对象 

- 3. 列表：Redis的列表通过命令的组合，既可以当做栈，也可以当做队列来使⽤，可以⽤来缓存类似微信 

公众号、微博等消息流数据 

- 4. 集合：和列表类似，也可以存储多个元素，但是不能重复，集合可以进⾏交集、并集、差集操作，从⽽ 

可以实现类似，我和某⼈共同关注的⼈、朋友圈点赞等功能 

- 5. 有序集合：集合是⽆序的，有序集合可以设置顺序，可以⽤来实现排⾏榜功能 





### **redis使用了两种[文件格式](https://baike.baidu.com/item/文件格式)：全量数据和增量请求。**

全量数据格式是把内存中的数据写入磁盘，便于下次读取文件进行加载；

增量请求文件则是把内存中的数据序列化为操作请求，用于读取文件进行replay得到数据，序列化的操作包括SET、RPUSH、SADD、ZADD。