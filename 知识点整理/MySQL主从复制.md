[TOC]

主数据库：152.32.221.2
从数据库：106.75.189.75

## 1、创建用户并授权
用户名：cashvn/cashvn_backup
```sql
-- 创建
create user "cashvn"@"106.75.189.75" identified by "cashvn_backup";
-- 授权
GRANT REPLICATION SLAVE,FILE ON *.* TO 'cashvn'@'106.75.189.75' IDENTIFIED BY 'cashvn_backup';
-- 刷新权限
FLUSH PRIVILEGES;
```

## 2、主库
修改配置：vim /etc/my.cnf
```shell
[mysqld]
server-id=1
binlog-do-db=app #这个是需要同步的数据库，app是一个数据库，自行先创建
```
```sql
show master status;
```

## 3、从库
修改配置：vim /etc/my.cnf
```shell
[mysqld]
server-id=2
master-host=152.32.221.2
master-user=cashvn
master-password=cashvn_backup
master-port=4040
master-connect-retry=60
replicate-do-db=bvay
replicate-do-db=bvay_manage
```
版本低于5.5，除了server-id，其他配置通过命令执行：
```sql
change master to master_host='152.32.221.2', master_user='cashvn', master_password='cashvn_backup', master_log_file='mysql-bin.000001', master_log_pos=514;
```

启动从库：
```sql
start slave;
-- 查看状态
show slave status\G;
```