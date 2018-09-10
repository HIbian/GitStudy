# 安装mysql并使用windows连接
## 安装命令
```
sudo apt-get install mysql-server
sudo apt-get install mysql-client
sudo apt-get install libmsqlclient-dev
```
若安装过程中出现缺失包的错误，应跟新当前系统的包，使用

`sudo apt-get updata`

安装完成后执行`sudo netstat -tap|grep mysql`查看是否安装成功，若mysql的socket为listen状态则表示成功

使用`mysql -u root -p`登陆mysql

其他命令

`service mysql start`

`service mysql stop`开启和关闭服务

`service mysql status`查看服务状态，出现绿色的小圆点表示服务正常运行

mysql安装完成后默认不能进行远程连接，有很多种原因
* mysql配置文件不允许远程连接
* windows未开启mysql端口入点（3306）
* 阿里云未添加3306端口的安全组规则

[修改mysql配置文件](https://blog.csdn.net/tiger_shl/article/details/77453358)

[windows开放端口](https://blog.csdn.net/spt_dream/article/details/75014619)

[阿里云添加安全组规则](https://help.aliyun.com/document_detail/25471.html)

若还是连接不上服务器，需要进入mysql修改一下远程连接的密码
```sql
#localhost表示远程端的地址，可以用%代替表示所有远程端
set password for 'root'@'localhost'=password('新密码');

#example
set password for 'root'@'%'=password('654321');
```

这样就可愉快地在本地连接远程数据库了
