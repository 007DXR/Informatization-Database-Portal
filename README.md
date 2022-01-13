# java前端代码解释

新增了前端功能
设想的界面：首先进入主页，主页可以显示概览情况。概览情况由所有数据构成，用treemap+line可视化，还可在此基础上再加组件
从主页可以进入查询界面，进入查询界面需要注册，登录（可以弹窗提示注册）。
查询界面由两部分组成，左侧上方是表单，用于增删查改。左侧下方显示相应的数据。右侧是根据左侧的信息进行可视化的饼图（还可在此基础上再加组件）
目前存在的问题是：如果查询的条件非常详细，那么左侧给出的信息可变量太少，难以可视化。大家帮忙想想主意（或者查询详情的时候减少可视化）

# Data-MYSQL

src文件下的dbConnecton做数据库的链接，其中包括用户名和密码

createMYSQL.java 进行数据库的建立

insertData中包括了data.json和insertData.java用来做初始的数据的导入和数据插入

function.java中包括了增删查改（指标和数据）的**函数**：

addIndex(String s1, String s2, String s3) 插入指标下的数据

addRecord(String s1, String s2, String s3, int id) 插入给定指标下的一条数据

deleteRecord(int id) 删除记录id的一条记录

modifyRecord(int id, String country, String year, double value) 修改一条记录内容。如果不修改country和year则传入""，如果不修改value则传入-1

modifyIndex(String before, String after) 修改指标名称（不包括编号）

inquireRecord(int id) 查询给定id的具体的一条记录 返回一个json数据文件src/query_a_data.json，包括id,国家，年份，指标名称不含编号（一级二级三级）和得分

inquireIndex(String s1, String s2, String s3) 查询给定指标名称下的数据，返回json数据文件 src/query_Index.json
