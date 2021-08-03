## Mysql学习

登陆 mysql -u root -p  以root用户登陆，以密码登陆

#### Linux   启动mysql

- mysql -u root -p   用密码登陆mysql数据库

- mysql -h localhost -u root -p 

- source xxx.sql  直接运行sql文件，直接创建表。

- 复制数据操作

  1、通过Navicat将数据库转化为结构表sql和数据表插入sql文件

  2、表与表之前是关联的，要先运行创建所有的表的sql文件

  3、删除结构和数据表的sql文件，删除里面创建表的sql文件，再运行插入数据的sql文件

  

#### 管理MQSQL的命令

##### 创建数据库 

```mysql
CREATE DATABASE IF NOT EXISTS `mybatis`;
```

##### 创建表

```mysql
CREATE TABLE IF NOT EXISTS `user` (
	`id` INT(20) NOT NULL,
	`name` VARCHAR(30) DEFAULT NULL,
	`pwd` VARCHAR(30) DEFAULT NULL,
	PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
```

创建外键

```mysql
FOREIGN KEY(`police_id`) REFERENCES police(`police_id`)
```



创建表中有 ENGINE=INNODB 引擎INNODB  

学习引擎 INNDOB

##### **USE 数据库名 ;**

```mysql
mysql> use RUNOOB;
Database changed
```

##### **SHOW DATABASES:**

列出 MySQL 数据库管理系统的数据库列表。

```mysql
mysql> SHOW DATABASES;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| RUNOOB             |
| cdcol              |
| mysql              |
| onethink           |
| performance_schema |
| phpmyadmin         |
| test               |
| wecenter           |
| wordpress          |
+--------------------+
10 rows in set (0.02 sec)
```

##### **SHOW TABLES:**

显示指定数据库的所有表，使用该命令前需要使用 use 命令来选择要操作的数据库。

```mysql
mysql> use RUNOOB;
Database changed
mysql> SHOW TABLES;
+------------------+
| Tables_in_runoob |
+------------------+
| employee_tbl     |
| runoob_tbl       |
| tcount_tbl       |
+------------------+
3 rows in set (0.00 sec)
```

##### **SHOW COLUMNS FROM  *数据表*:**

显示数据表的属性，属性类型，主键信息 ，是否为 NULL，默认值等其他信息。

```mysql
mysql> SHOW COLUMNS FROM runoob_tbl;
+-----------------+--------------+------+-----+---------+-------+
| Field           | Type         | Null | Key | Default | Extra |
+-----------------+--------------+------+-----+---------+-------+
| runoob_id       | int(11)      | NO   | PRI | NULL    |       |
| runoob_title    | varchar(255) | YES  |     | NULL    |       |
| runoob_author   | varchar(255) | YES  |     | NULL    |       |
| submission_date | date         | YES  |     | NULL    |       |
+-----------------+--------------+------+-----+---------+-------+
4 rows in set (0.01 sec)
```

**SHOW INDEX FROM *数据表*:**
显示数据表的详细索引信息，包括PRIMARY KEY（主键）。

```mysql
mysql> SHOW INDEX FROM runoob_tbl;
+------------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table      | Non_unique | Key_name | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+------------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| runoob_tbl |          0 | PRIMARY  |            1 | runoob_id   | A         |           2 |     NULL | NULL   |      | BTREE      |         |               |
+------------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
1 row in set (0.00 sec)
```

#### 数据类型

##### 日期和时间类型

表示时间值的日期和时间类型为DATETIME、DATE、TIMESTAMP、TIME和YEAR。

每个时间类型有一个有效值范围和一个"零"值，当指定不合法的MySQL不能表示的值时使用"零"值。

TIMESTAMP类型有专有的自动更新特性，将在后面描述。

| 类型      | 大小 ( bytes) | 范围                                                         | 格式                | 用途                     |
| :-------- | :------------ | :----------------------------------------------------------- | :------------------ | :----------------------- |
| DATE      | 3             | 1000-01-01/9999-12-31                                        | YYYY-MM-DD          | 日期值                   |
| TIME      | 3             | '-838:59:59'/'838:59:59'                                     | HH:MM:SS            | 时间值或持续时间         |
| YEAR      | 1             | 1901/2155                                                    | YYYY                | 年份值                   |
| DATETIME  | 8             | 1000-01-01 00:00:00/9999-12-31 23:59:59                      | YYYY-MM-DD HH:MM:SS | 混合日期和时间值         |
| TIMESTAMP | 4             | 1970-01-01 00:00:00/2038结束时间是第 **2147483647** 秒，北京时间 **2038-1-19 11:14:07**，格林尼治时间 2038年1月19日 凌晨 03:14:07 | YYYYMMDD HHMMSS     | 混合日期和时间值，时间戳 |

##### 字符串类型

字符串类型指CHAR、VARCHAR、BINARY、VARBINARY、BLOB、TEXT、ENUM和SET。该节描述了这些类型如何工作以及如何在查询中使用这些类型。

| 类型       | 大小                  | 用途                            |
| :--------- | :-------------------- | :------------------------------ |
| CHAR       | 0-255 bytes           | 定长字符串                      |
| VARCHAR    | 0-65535 bytes         | 变长字符串                      |
| TINYBLOB   | 0-255 bytes           | 不超过 255 个字符的二进制字符串 |
| TINYTEXT   | 0-255 bytes           | 短文本字符串                    |
| BLOB       | 0-65 535 bytes        | 二进制形式的长文本数据          |
| TEXT       | 0-65 535 bytes        | 长文本数据                      |
| MEDIUMBLOB | 0-16 777 215 bytes    | 二进制形式的中等长度文本数据    |
| MEDIUMTEXT | 0-16 777 215 bytes    | 中等长度文本数据                |
| LONGBLOB   | 0-4 294 967 295 bytes | 二进制形式的极大文本数据        |
| LONGTEXT   | 0-4 294 967 295 bytes | 极大文本数据                    |

**注意**：char(n) 和 varchar(n) 中括号中 n 代表字符的个数，并不代表字节个数，比如 CHAR(30) 就可以存储 30 个字符。

CHAR 和 VARCHAR 类型类似，但它们保存和检索的方式不同。它们的最大长度和是否尾部空格被保留等方面也不同。在存储或检索过程中不进行大小写转换。

BINARY 和 VARBINARY 类似于 CHAR 和 VARCHAR，不同的是它们包含二进制字符串而不要非二进制字符串。也就是说，它们包含字节字符串而不是字符字符串。这说明它们没有字符集，并且排序和比较基于列值字节的数值值。

BLOB 是一个二进制大对象，可以容纳可变数量的数据。有 4 种 BLOB 类型：TINYBLOB、BLOB、MEDIUMBLOB 和 LONGBLOB。它们区别在于可容纳存储范围不同。

有 4 种 TEXT 类型：TINYTEXT、TEXT、MEDIUMTEXT 和 LONGTEXT。对应的这 4 种 BLOB 类型，可存储的最大长度不同，可根据实际情况选择。

##### 整数类型

![img](https://img-blog.csdnimg.cn/20200414134627713.png)

## 导出数据库sql文件

### 1、导出数据和表结构：



``` shell
mysqldump -u用户名 -p密码 数据库名 > 数据库名.sql
\#/usr/local/mysql/bin/  mysqldump -uroot -p abc > abc.sql
敲回车后会提示输入密码 ```

### 
```

### 2、只导出表结构

```shell
mysqldump -u用户名 -p密码 -d 数据库名 > 数据库名.sql
\#/usr/local/mysql/bin/  mysqldump -uroot -p -d abc > abc.sql

```



docker 导出文件 docker cp

```shell
docker cp [OPTIONS] container:src_path dest_path

mysqldump -u dbuser -p -d --add-drop-table dbname >d:/dbname_db.sql

```



## SQL CRUD

删除

```sql
DELETE FROM 表名称 WHERE 列名称 = 值
```

更新

```sql
UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
```

插入

```sql
INSERT INTO 表名称 VALUES (值1, 值2,....)
```

指定

```sql
INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
```



## 索引数据结构

索引：就排好序的数组

- 二叉树
- 红黑树
- Hash表
- B-Tree

Mysql底层使用的B+Tree，B树

### 红黑树

**红黑树的特性**:
**（1）每个节点或者是黑色，或者是红色。**
**（2）根节点是黑色。**
**（3）每个叶子节点（NIL）是黑色。 [注意：这里叶子节点，是指为空(NIL或NULL)的叶子节点！]**
**（4）如果一个节点是红色的，则它的子节点必须是黑色的。**
**（5）从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。**

**注意**：
(01) 特性(3)中的叶子节点，是只为空(NIL或null)的节点。
(02) 特性(5)，确保没有一条路径会比其他路径长出俩倍。因而，**红黑树是相对是接近平衡的二叉树**。

红黑树就平衡二叉树的操作，**左旋、右旋**，比较耗时

![img](https://images0.cnblogs.com/i/497634/201403/251730074203156.jpg)

### B+树

比较适合范围查找，而且叶子节点会存储邻界的节点。提升区间访问的性能

![image-20210726220356521](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210726220356521.png)

![image-20210726203045627](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210726203045627.png)

![image-20210726203643504](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210726203643504.png)

存储引擎是以表单位

**MyISAM**   一个表中文件下表 firm表结构、myi索引、myd数据，MYISAM是非聚集索引，索引和数据是分开

**Innodb**， 底层文件 ibd(索引+数据)、firm（表结构），是聚集索引，索引和数据是结合的

![image-20210726205100546](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210726205100546.png)

#### 面试题：为什么建议InnoDB表必须建立主键，并且推荐使用整形的自增主键？

innodb是B+树，因此直接用主键做为B+树作为索引比较好，主键不重复好区分。如果不建主键，mysql会遍历一列，看是否重复然后用这一列数据作为表的主键。

B+树，用整形去比较大小，比字符串比较大小方便多了，字符串比较大小是逐位比较大小。自增主键，只会向尾巴后扩展，只会向后分裂。如果使用uuid，可能会在中间分裂，因此会效率不好。

### 面试题：B+树和B树的区别

同样的数据量，B树的深度会更加深，B+树的深度会比较小。

因为B+树中有空白的地方放更多的范围。因此使用B+树会让数据查询的深度更小，去磁盘查询的次数更少。

### Hash

![image-20210726210305170](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210726210305170.png)

很多时候Hash索引要比B+树索引更高效

缺点：仅能满足“=”，“IN”，不支持范围查询， hash冲突问题



## 联合索引是最长见的索引

最左前缀法则: 联合索引就从左到右先，先排序,第一个索引相同的适合，排序第二个元素

![image-20210726212348351](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20210726212348351.png)



## Mysql性能调优：

普罗米修斯工具



超过三个表禁止join，90%的场景用单表查询

## 问题：

程序运行的时候，jdbc版本可能过低，mysql版本过高。需要添加useSSL=false字段

```properties
jdbc.url=jdbc:mysql://172.17.16.12:3306/monitor?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&useSSL=false

#&、 &amp;
```