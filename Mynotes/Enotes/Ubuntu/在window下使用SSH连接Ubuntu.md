# 在windows下使用SSH连接Ubuntu
##### ubuntu
1. 安装ssh
`sudo apt-get upate`
`sudo apt-get install openssh-server`
2. 查看ssh服务是否启动成功
`sudo ps -e|grep shh`
3. 没有的话手动启动服务
`sudo service ssh start`
4. 查看ip地址，需要公网ip
`sudo ifconfig`
##### windows
 * 下载putty
 * 配置ip，ssh默认端口为22
 * 连接
