# java前端代码解释

data.json 教师提供的数据

filter.js 对data.json进行预处理，转化为我想使用的数据格式

form.js 服务于form.html 是表单的下拉框显示内容的计算构造函数

line.js 服务于project.html 提供折线图模块

treemap.js 服务于project.html 提供层次结构图模块

form.html 提交“增删查改”表单向服务器发送以下信息

document.getElementById("level1").value

document.getElementById("level2").value

document.getElementById("level3").value

document.getElementById("country").value

document.getElementById("year").value

document.getElementById("value").value

document.getElementById("submit")

document.getElementById("addition")

document.getElementById("delete")

document.getElementById("query")

project.html 主页面

包含三个子模块，分别是层次结构图，折线图，表单。其中表单以内嵌HTML的形式(form.html内嵌)展现在project.html上

# Data-MYSQL

src文件下的dbConnecton做数据库的链接，包括用户名和密码

createMYSQL.java 进行数据库的建立

insertData中包括了data.json和insertData.java用来做初始的数据的导入和数据插入

function.java中包括了增删查改（指标和数据）的函数：

addIndex(String s1, String s2, String s3) 插入指标下的数据

addRecord(String s1, String s2, String s3, int id) 插入给定指标下的一条数据

deleteRecord(int id) 删除记录id的一条记录

modifyRecord(int id, String country, String year, double value) 修改一条记录内容。如果不修改country和year则传入""，如果不修改value则传入-1

modifyIndex(String before, String after) 修改指标名称（不包括编号）

inquireRecord(int id) 查询给定id的具体的一条记录 返回一个json数据文件src/query_a_data.json，包括id,国家，年份，指标名称不含编号（一级二级三级）和得分

inquireIndex(String s1, String s2, String s3) 查询给定指标名称下的数据，返回json数据文件 src/query_Index.json
