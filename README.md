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
