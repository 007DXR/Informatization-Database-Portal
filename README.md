# Java web database portal 信息化程度数据库门户网站
>本项目是2021秋季学期北京大学信息管理系课程《面向对象程序设计JAVA》的课程设计作业。

This is a Java web application project of a undergraduate grade-2 course for java.

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
            │   ├── templates                      # web page templates
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