# 安装依赖
需要的软件apache、php、mysql
```linux
sudo apt-get install apache2
sudo apt-get install php7.0
sudo apt install mysql-server
sudo apt install mysql-client
sudo apt-get install libapache2-mod-php7.0
sudo apt-get install php7.0-mysql
sudo apt install php-xml
```
# 环境配置
更改000-default.conf文件内容，主要有
- DocumentRoot值变为/var/www/

**更改apache网站目录权限，方便之后安装Typecho时可以直接在浏览器上操作**
```linux
sudo chmod 777 /var/www
```
# 安装typecho
将typecho文件目录放入var/www
在浏览器中打开，进行安装

# 添加主题路径
将主题根目录扔入
`var/www/usr/themes`
里面有个默认主题default
