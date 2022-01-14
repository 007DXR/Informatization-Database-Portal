# Java web database portal 信息化程度数据库门户网站
>本项目是2021秋季学期北京大学信息管理系课程《面向对象程序设计JAVA》的课程设计作业。中文版附于英文简版后。

This is a Java web application project of an undergraduate grade-2 course for java.

## Installation

### Download and import

Based on maven, the project is easy to install. After cloning, just import existing maven project.

### Database preparations

The project use a very naive pattern to control the database, so you may need to store your username and passwords of your own MySQL database in the `src/main/java/mysql/JDBCConnection.java`(line:25). It may cause potential safety problem.

So, I personally recommend you to create a temporary user. Here, we show an example in `data/test_user.sql`, to add a test_user with only minimal privileges.

After that, run `data/preparation.sql` to create database and tables.

Test data are stored in `data/data.json`. Run `src/main/java/mysql/insertData.java` to insert part of them into your connection.

### Just run as application

In this project, we embed Tomcat container into source code. So you needn't zip it as .jar and run it in tomcat web server.

Just run as an application.

## Structure

```
├── README.md
├── data
│   ├── data.json                    # dataset for test
│   ├── preparation.sql              # create database and tables
│   ├── query_a_data.json            # data cache when query for one record
│   ├── query_index.json             # also data cache, used for debugging
│   └── test_user.sql                # an example to create test temporary user
├── doc
│   ├── api.json                     # previous idea of api using openAPI
│   ├── assets                       # stored pics for design doc
│   │   └── *.png
│   └── design.md
├── pom.xml                          # maven configuration
└── src
    └── main
        ├── java                              # java code
        │   ├── Main.java
        │   ├── bean                          # model of MVC
        │   │   ├── SignInBean.java                # signin info
        │   │   ├── User.java                      # user profile
        │   │   └── VisualInfoBean.java            # record info
        │   ├── controller                    # controller of MVC
        │   │   ├── IndexController.java           # index page
        │   │   ├── UserController.java            # signin related
        │   │   └── VisualController.java          # CRUD logics of deail pages
        │   ├── framework                     # MVC framework
        │   │   ├── DispatcherServlet.java         # most important servelet
        │   │   ├── FileServlet.java               # transform static webapp files
        │   │   ├── GetMapping.java                # annotation to deal http get request
        │   │   ├── ModelAndView.java              # used for pebble template to instantiation
        │   │   ├── PostMapping.java               # annotation to deal http post request
        │   │   └── ViewEngine.java                # import pebble template engine
        │   └── mysql                         # database front
        │       ├── CreateStatement.java           # create jdbc statement
        │       ├── JDBCConnection.java            # create jdbc connection
        │       ├── SQLMain.java                   # some tests
        │       ├── createMYSQL.java               # script to create tables 
        │       ├── function.java                  # functions of mysql, to be called
        │       └── insertData.java                # insert basic data into database
        └── webapp
            ├── WEB-INF
            │   ├── templates                      # web page templates, view of MVC
            │   │   ├── _base.html                 # base page
            │   │   ├── form.html                  # (deprecated)
            │   │   ├── hello.html                 # (deprecated)
            │   │   ├── index.html                 # index page
            │   │   ├── overview.html              # (deprecated) similar to index
            │   │   ├── profile.html               # user profile
            │   │   ├── project.html               # detail page
            │   │   └── signin.html                # signin page
            │   └── web.xml
            ├── favicon.ico
            └── static
                ├── css
                │   ├── bootstrap-grid.css
                │   ├── bootstrap-grid.min.css
                │   ├── bootstrap.css
                │   └── bootstrap.min.css
                └── js
                    ├── ajax.js                     # ajax logic
                    ├── bootstrap.bundle.js
                    ├── bootstrap.bundle.min.js
                    ├── bootstrap.js
                    ├── bootstrap.min.js
                    ├── data.js                     # store data, for dropbox listening               
                    ├── echarts.min.js
                    ├── filter.js                   # filter example data to usable data for echarts
                    ├── form.js                     # bootstrap-style control form 
                    ├── jquery.js
                    ├── line.js                     # line chart
                    ├── pie.js                      # pie chart
                    ├── prefilter.js                # filter record data to example data
                    └── treemap.js                  # treemap chart for hierachical presentation
```

## Thanks

The project cannot finally come into existence without Java Course of [liaoxuefeng.com](https://www.liaoxuefeng.com/wiki/1252599548343744/1266264917931808). Based on the [Retro edition of Spring MVC](https://gitee.com/liaoxuefeng/learn-java/blob/master/practices/Java%E6%95%99%E7%A8%8B/200.Web%E5%BC%80%E5%8F%91.1255945497738400/60.MVC%E5%BC%80%E5%8F%91.1266264917931808/web-servlet-jsp.zip?utm_source=blog_lxf), we can finally struggle to free ourselves to focus more on main functions.

Also thanks to Tomcat, Pebble, Fastjson, Jackson, commons-io and so on. Recent news about opensource(about Faker.js) lead to my deep gratitude to them.

# 中文版

## 安装

### 下载与导入

基于maven，该项目易于安装。clone之后，只需导入现有的maven项目。在eclipse中选择File > import > existing maven project 即可。

### 数据库准备

该项目使用了一个非常简单的方式来操作数据库，所以您可能需要将您自己的MySQL数据库的用户名和密码存储在`src/main/java/MySQL/JDBCConnection.java`(第25行)中。这可能会导致潜在的安全问题。

因此，我个人建议您创建一个临时用户。这里，我们展示了`data/test_user.sql`中的示例。以添加一个只有最少权限的test_user。

运行`data/preparation.sql`创建数据库和表。

测试数据存储在`data/data.json`中。运行`src/main/java/mysql/insertData.java`将其中一部分数据插入到您的数据库链接中。

### 以应用程序运行

在这个项目中，我们将Tomcat容器嵌入到源代码中。所以您不需要把它压缩成jar包，然后在tomcat web服务器上运行。

比如在eclipse上运行时，只需作为应用程序运行即可。

main函数位置在`src/main/java/[default/]Main.java`

## 项目结构

```
├── README.md
├── data
│   ├── data.json                    # 用于测试的数据
│   ├── preparation.sql              # 创建数据库和表
│   ├── query_a_data.json            # 查询一条记录时的数据缓存
│   ├── query_index.json             # 查询多条记录时的数据缓存，同样用于debug
│   └── test_user.sql                # 个创建测试用临时用户的示例
├── doc
│   ├── api.json                     # 先前的设想，用openAPI工具创建的系统API
│   ├── assets                       # 存储文档用的图片
│   │   └── *.png
│   └── design.md
├── pom.xml                          # maven 配置
└── src
    └── main
        ├── java                              # Java代码
        │   ├── Main.java
        │   ├── bean                          # MVC架构的M
        │   │   ├── SignInBean.java                # 登陆信息（是用户信息的子集）
        │   │   ├── User.java                      # 用户信息
        │   │   └── VisualInfoBean.java            # 一条记录的信息
        │   ├── controller                    # MVC架构的C
        │   │   ├── IndexController.java           # index页
        │   │   ├── UserController.java            # 登陆相关的操作
        │   │   └── VisualController.java          # 详情页的CRUD操作
        │   ├── framework                     # MVC框架
        │   │   ├── DispatcherServlet.java         # 最核心的servlet
        │   │   ├── FileServlet.java               # 传输webapp中的文件
        │   │   ├── GetMapping.java                # 注解，和dispatcher配合实现get请求的域名映射
        │   │   ├── ModelAndView.java              # 用于pebble模板引擎的实例化
        │   │   ├── PostMapping.java               # 注解，和dispatcher配合实现post请求的域名映射
        │   │   └── ViewEngine.java                # 导入pebble模板引擎
        │   └── mysql                         # 数据库端
        │       ├── CreateStatement.java           # 创建 jdbc statement
        │       ├── JDBCConnection.java            # 创建 jdbc connection
        │       ├── SQLMain.java                   # 功能测试
        │       ├── createMYSQL.java               # 用于建表
        │       ├── function.java                  # mysql的功能模块
        │       └── insertData.java                # 将基本数据插入数据库中
        └── webapp
            ├── WEB-INF                       # MVC的V
            │   ├── templates                      # 网页模板
            │   │   ├── _base.html                 # 基本模板
            │   │   ├── form.html                  # (deprecated) 表单
            │   │   ├── hello.html                 # (deprecated) 亦为欢迎页
            │   │   ├── index.html                 # index page 欢迎
            │   │   ├── overview.html              # (deprecated) 概览页，类似欢迎页中的普遍展示
            │   │   ├── profile.html               # user profile 用户信息
            │   │   ├── project.html               # detail page 详情页
            │   │   └── signin.html                # signin page 登陆页
            │   └── web.xml
            ├── favicon.ico
            └── static
                ├── css
                │   ├── bootstrap-grid.css
                │   ├── bootstrap-grid.min.css
                │   ├── bootstrap.css
                │   └── bootstrap.min.css
                └── js
                    ├── ajax.js                     # ajax 相关逻辑
                    ├── bootstrap.bundle.js
                    ├── bootstrap.bundle.min.js
                    ├── bootstrap.js
                    ├── bootstrap.min.js
                    ├── data.js                     # 用于存储数据，使得listener可以更新
                    ├── echarts.min.js
                    ├── filter.js                   # 将示例数据格式转换为绘制所需的格式
                    ├── form.js                     # bootstrap风格的表单，用于访问者的控制 
                    ├── jquery.js
                    ├── line.js                     # 折线图
                    ├── pie.js                      # 饼图
                    ├── prefilter.js                # 将记录格式改成示例数据
                    └── treemap.js                  # 用于展示层次结构的树图
```

## 前端使用

### 登录

暂时没有注册系统，通过在`/src/main/controller/UserController.java`(line:24-27)的Stream中增添userBean可以实现注册。

不注册可以直接使用`Email:tom@example.com`和`Password:tomcat`登录。

在进入详情页时会提醒登录，或点击右上角Sign in可以直接登录。

### 概览页

在未登录时可以看到树图和其对应变换的柱状图。点击树图对应图块，下方折线图的数据集会变为对应子集。

### 详情页

登录后，在detail页可以使用部分权限进行数据库访问控制。主要功能如下：

1. 查询（read）：支持不同模式查询，但至少填入一级指标、国家或年份，否则提示错误；支持ID查询。
2. 增加（create）：必须给出完整的数据，但可以不包括ID，系统会为其自动分配ID并提示分配结果。
3. 改动（update）：为了实现指定改动，只支持先查询，后改动的模式，在网页端操作必须给出ID。
4. 删除（delete）：支持不同模式删除，但至少填入一级指标、国家或年份，否则提示错误；支持ID查询。

## 鸣谢

项目的成型和迭代离不开[廖雪峰的Java教程](https://www.liaoxuefeng.com/wiki/1252599548343744)和其[复刻版的Spring MVC框架](https://gitee.com/liaoxuefeng/learn-java/blob/master/practices/Java%E6%95%99%E7%A8%8B/200.Web%E5%BC%80%E5%8F%91.1255945497738400/60.MVC%E5%BC%80%E5%8F%91.1266264917931808/web-servlet-jsp.zip?utm_source=blog_lxf)作为参考。
