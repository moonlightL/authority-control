## 简单介绍

本套系统在 authority-control 系统的基础上整合了 Shiro 框架

### 后端
springmvc4.3.7 + spring4.3.7 + mybatis3.4.2 + shiro1.4.0

### 前端

jsp + ace 主题管理模板 + jQuery + Layer + Bootstrap-Table

### 数据库

MySQL5.6

### 权限控制思路

* 前端使用 shiro 标签控制权限按钮的显示和隐藏
* 后端使用自定义 Realm 进行认证和授权操作

## 使用说明

1) 下载源码

2) 创建名为 authority-control 的数据库

3) 通过工具或命令执行 src/main/resources 目录下的 authority-control.sql 文件

4) 运行项目，浏览器访问 <http://localhost:8080>，账号和密码都为 admin。　

补充：db.properties 中 mysql 用户名和密码进行 DES 加密， 其明文用户名：root 密码：tiger

## 效果图

![](http://images.extlight.com/authority-control-01.jpg)

![](http://images.extlight.com/authority-control-02.jpg)

![](http://images.extlight.com/authority-control-03.jpg)

![](http://images.extlight.com/authority-control-04.jpg)
