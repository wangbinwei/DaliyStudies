# Redis

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



