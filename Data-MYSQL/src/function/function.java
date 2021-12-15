package function;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import createStmt.CreateStatement;
import dbConnection.JDBCConnection;
import insertData.insertData;

public class function
{
	public static void main(String[] args) throws SQLException
	{
		// test:
		// addIndex("信息基础设施_A-1", "带宽水平_A-11", "拥有宽带无线网接入的公共场所比例_A-113");
		// addRecord("信息基础设施_A-1", "通信和网络接入成本_A-12",
		// "固网宽带互联网接入资费占人均收入的比重_A-123", 25);
		// deleteRecord(25);
		// modifyRecord(0, "法国", "2020", 1);
		// modifyIndex("信息基础设施", "xinxijichusheshi");
		// inquireRecord(0);
		// inquireRecord("信息基础设施", "带宽水平", "互联网用户的平均上网带宽", "中国", "2019");
		// deleteRecord(25);
		// deleteRecord("信息基础设施", "带宽水平", "互联网用户的平均上网带宽", "法国", "2019");
		// inquireIndex("信息基础设施", "带宽水平", "互联网用户的平均上网带宽");
	}

	/**
	 * 插入一个指标下的所有数据（格式是名称+编号的格式）
	 */
	public static void addIndex(String s1, String s2, String s3) throws SQLException
	{
		Statement stmt;
		JDBCConnection dc = new JDBCConnection();// 建立数据库连接
		CreateStatement cst = new CreateStatement(dc);// 创建语句对象
		stmt = cst.stmt;

		String str = insertData.ReadFile("src/insertData/data.json"); // 注意路径的问题
		JSONObject jsonObject = JSONObject.parseObject(str);
		String strA = jsonObject.getString(s1); // 得到一级指标的json值
		JSONObject jsonObjectB = JSONObject.parseObject(strA);

		String strB = jsonObjectB.getString(s2);// 获取二级指标的jsonvalue
		JSONObject jsonObjectC = JSONObject.parseObject(strB);
		String strC = jsonObjectC.getString(s3); // 获取三级指标的jsonvalue-json数组
		List<insertData> list = JSONObject.parseArray(strC, insertData.class);

		// 插入指标之前先判断是否已经存在
		int[] indexid = new int[3];
		String[] str1 = s1.split("_");
		String sql1 = "SELECT IndexID FROM firstindex WHERE IndexName = '" + str1[0] + "'";// 10行
		ResultSet rs1 = stmt.executeQuery(sql1);
		if (rs1.next()) // 已经存在
			indexid[0] = rs1.getInt("IndexID");
		else
		{// 不存在则进行插入并进行id的查询
			String s = "INSERT IGNORE INTO firstindex (IndexID,IndexName,IndexNumeration) Values (null,'" + str1[0]
					+ "','" + str1[1] + "')";
			stmt.executeUpdate(s);// 发送SQL语句
			ResultSet rs11 = stmt.executeQuery(sql1);
			if (rs11.next()) // 有结果
				indexid[0] = rs11.getInt("IndexID");
			rs11.close();
		}
		rs1.close();

		String[] str2 = s2.split("_");
		String sql2 = "SELECT IndexID FROM secondindex WHERE IndexName = '" + str2[0] + "'";// 10行
		ResultSet rs2 = stmt.executeQuery(sql2);
		if (rs2.next()) // 已经存在
			indexid[1] = rs2.getInt("IndexID");
		else
		{
			String ss = "INSERT IGNORE INTO secondindex (IndexID,IndexName,IndexNumeration) Values (null,'" + str2[0]
					+ "','" + str2[1] + "')";
			stmt.executeUpdate(ss);// 发送SQL语句
			ResultSet rs22 = stmt.executeQuery(sql2);
			if (rs22.next()) // 有结果
				indexid[1] = rs22.getInt("IndexID");
			rs22.close();
		}
		rs2.close();

		String[] str3 = s3.split("_");
		String sql3 = "SELECT IndexID FROM thirdindex WHERE IndexName = '" + str3[0] + "'";// 10行
		ResultSet rs3 = stmt.executeQuery(sql3);
		if (rs3.next()) // 已经存在
			indexid[2] = rs3.getInt("IndexID");
		else
		{
			String sss = "INSERT IGNORE INTO thirdindex (IndexID,IndexName,IndexNumeration) Values (null,'" + str3[0]
					+ "','" + str3[1] + "')";
			stmt.executeUpdate(sss);// 发送SQL语句
			ResultSet rs33 = stmt.executeQuery(sql3);
			if (rs33.next()) // 有结果
				indexid[2] = rs33.getInt("IndexID");
			rs33.close();
		}
		rs3.close();

		// 对数据的插入
		for (int u = 0; u < list.size(); u++)
		{
			insertData data = list.get(u);
			String lists = "INSERT IGNORE INTO records (dataID, Country, Year,FirstIndexID,SecondIndexID,ThirdIndexID,IndexValue) Values ('"
					+ data.id + "','" + data.country + "','" + data.year + "','" + indexid[0] + "','" + indexid[1]
					+ "','" + indexid[2] + "','" + data.value + "')";
			stmt.executeUpdate(lists);
		}
		cst.close();// 关闭语句对象
		dc.close();// 关闭数据库连接
	}

	/**
	 * 插入已在数据库中的指标下的特定一条记录id的数据 （格式是名称+编号的格式）
	 */
	public static void addRecord(String s1, String s2, String s3, int id) throws SQLException
	{
		Statement stmt;
		JDBCConnection dc = new JDBCConnection();// 建立数据库连接
		CreateStatement cst = new CreateStatement(dc);// 创建语句对象
		stmt = cst.stmt;

		String str = insertData.ReadFile("src/insertData/data.json"); // 注意路径的问题
		JSONObject jsonObject = JSONObject.parseObject(str);
		String strA = jsonObject.getString(s1); // 得到一级指标的json值
		JSONObject jsonObjectB = JSONObject.parseObject(strA);

		String strB = jsonObjectB.getString(s2);// 获取二级指标的jsonvalue
		JSONObject jsonObjectC = JSONObject.parseObject(strB);
		String strC = jsonObjectC.getString(s3); // 获取三级指标的jsonvalue-json数组
		List<insertData> list = JSONObject.parseArray(strC, insertData.class);

		int[] indexid = new int[3];
		String[] str1 = s1.split("_");
		String sql1 = "SELECT IndexID FROM firstindex WHERE IndexName = '" + str1[0] + "'";// 10行
		ResultSet rs1 = stmt.executeQuery(sql1);
		if (rs1.next()) // 已经存在
			indexid[0] = rs1.getInt("IndexID");
		rs1.close();

		String[] str2 = s2.split("_");
		String sql2 = "SELECT IndexID FROM secondindex WHERE IndexName = '" + str2[0] + "'";// 10行
		ResultSet rs2 = stmt.executeQuery(sql2);
		if (rs2.next()) // 已经存在
			indexid[1] = rs2.getInt("IndexID");
		rs2.close();

		String[] str3 = s3.split("_");
		String sql3 = "SELECT IndexID FROM thirdindex WHERE IndexName = '" + str3[0] + "'";// 10行
		ResultSet rs3 = stmt.executeQuery(sql3);
		if (rs3.next()) // 已经存在
			indexid[2] = rs3.getInt("IndexID");
		rs3.close();

		// 对数据的插入
		for (int u = 0; u < list.size(); u++)
		{
			insertData data = list.get(u);
			if (data.id != id)
				continue;
			// 找到需要的id的记录数据内容进行插入
			String lists = "INSERT IGNORE INTO records (dataID, Country, Year,FirstIndexID,SecondIndexID,ThirdIndexID,IndexValue) Values ('"
					+ data.id + "','" + data.country + "','" + data.year + "','" + indexid[0] + "','" + indexid[1]
					+ "','" + indexid[2] + "','" + data.value + "')";
			stmt.executeUpdate(lists);
		}
		cst.close();// 关闭语句对象
		dc.close();// 关闭数据库连接
	}

	/**
	 * 删除一条记录
	 */
	public static void deleteRecord(int id) throws SQLException
	{
		Statement stmt;
		JDBCConnection dc = new JDBCConnection();// 建立数据库连接
		CreateStatement cst = new CreateStatement(dc);// 创建语句对象
		stmt = cst.stmt;

		String sql = "DELETE FROM records WHERE dataID=" + id;// 10行
		stmt.executeUpdate(sql);
		cst.close();// 关闭语句对象
		dc.close();// 关闭数据库连接
	}

	public static void deleteRecord(String s1, String s2, String s3, String country, String year) throws SQLException
	{
		Statement stmt;
		JDBCConnection dc = new JDBCConnection();// 建立数据库连接
		CreateStatement cst = new CreateStatement(dc);// 创建语句对象
		stmt = cst.stmt;
		// 获得指标id
		int[] indexid = new int[3];
		String sql1 = "SELECT IndexID FROM firstindex WHERE IndexName = '" + s1 + "'";// 10行
		ResultSet rs1 = stmt.executeQuery(sql1);
		if (rs1.next()) // 已经存在
			indexid[0] = rs1.getInt("IndexID");
		rs1.close();

		String sql2 = "SELECT IndexID FROM secondindex WHERE IndexName = '" + s2 + "'";// 10行
		ResultSet rs2 = stmt.executeQuery(sql2);
		if (rs2.next()) // 已经存在
			indexid[1] = rs2.getInt("IndexID");
		rs2.close();

		String sql3 = "SELECT IndexID FROM thirdindex WHERE IndexName = '" + s3 + "'";// 10行
		ResultSet rs3 = stmt.executeQuery(sql3);
		if (rs3.next()) // 已经存在
			indexid[2] = rs3.getInt("IndexID");
		rs3.close();

		String sql = "DELETE FROM records WHERE FirstIndexID=" + indexid[0] + " AND SecondIndexID=" + indexid[1]
				+ " AND ThirdIndexID=" + indexid[2] + " AND Country='" + country + "' AND Year='" + year + "'";
		stmt.executeUpdate(sql);
		cst.close();// 关闭语句对象
		dc.close();// 关闭数据库连接
	}

	/**
	 * 修改指定记录id的记录信息,因为java不支持设置参数默认值，所以如果不修改country和year则传入""，如果不修改value则传入-1
	 */
	public static void modifyRecord(int id, String country, String year, double value) throws SQLException
	{
		Statement stmt;
		JDBCConnection dc = new JDBCConnection();// 建立数据库连接
		CreateStatement cst = new CreateStatement(dc);// 创建语句对象
		stmt = cst.stmt;

		String s[] = { "", "", "" };
		int cnt = 0;
		if (country.equals(""))
			s[0] = "";
		else
		{
			s[0] = "Country='" + country + "'";
			cnt++;
		}
		if (year.equals(""))
			s[1] = "";
		else
		{
			if (cnt != 0)
				s[1] += ",";
			s[1] += "Year='" + year + "'";
			cnt++;
		}
		if (value == -1)
			s[2] = "";
		else
		{
			if (cnt != 0)
				s[2] += ",";
			s[2] += "IndexValue=" + value;
			cnt++;
		}

		String sql = "UPDATE records SET " + s[0] + s[1] + s[2] + " WHERE dataID=" + id;
		stmt.executeUpdate(sql);
		cst.close();// 关闭语句对象
		dc.close();// 关闭数据库连接
	}

	/**
	 * 改指标名称 before->after （这初步是一个不带编号的单纯的指标名称的修改）
	 */
	public static void modifyIndex(String before, String after) throws SQLException
	{
		// 先在指标的表中查到这个指标 再进行更改
		Statement stmt;
		JDBCConnection dc = new JDBCConnection();// 建立数据库连接
		CreateStatement cst = new CreateStatement(dc);// 创建语句对象
		stmt = cst.stmt;

		String searchsql1 = "SELECT IndexID FROM firstindex WHERE IndexName = '" + before + "'";// 10行
		ResultSet rs1 = stmt.executeQuery(searchsql1);
		if (rs1.next()) // 说明这个指标是在这个表里面的
		{
			int id = rs1.getInt("IndexID");
			String sql1 = "UPDATE firstindex SET IndexName='" + after + "' WHERE IndexID=" + id;// 10行";
			stmt.executeUpdate(sql1);
		}
		rs1.close();

		String searchsql2 = "SELECT IndexID FROM secondindex WHERE IndexName = '" + before + "'";// 10行
		ResultSet rs2 = stmt.executeQuery(searchsql2);
		if (rs2.next()) // 说明这个指标是在这个表里面的
		{
			int id = rs2.getInt("IndexID");
			String sql2 = "UPDATE secondindex SET IndexName='" + after + "' WHERE IndexID=" + id;// 10行";
			stmt.executeUpdate(sql2);
		}
		rs2.close();

		String searchsql3 = "SELECT IndexID FROM thirdindex WHERE IndexName = '" + before + "'";// 10行
		ResultSet rs3 = stmt.executeQuery(searchsql3);
		if (rs3.next()) // 说明这个指标是在这个表里面的
		{
			int id = rs3.getInt("IndexID");
			String sql3 = "UPDATE thirdindex SET IndexName='" + after + "' WHERE IndexID=" + id;// 10行";
			stmt.executeUpdate(sql3);
		}
		rs3.close();
		cst.close();// 关闭语句对象
		dc.close();// 关闭数据库连接
	}

	/**
	 * 查询给定id的具体的一条记录 返回一个json数据 包括id,国家，年份，指标名称不含编号（一级二级三级）和得分
	 */
	public static void inquireRecord(int id) throws SQLException
	{
		Statement stmt;
		JDBCConnection dc = new JDBCConnection();// 建立数据库连接
		CreateStatement cst = new CreateStatement(dc);// 创建语句对象
		stmt = cst.stmt;

		String sql = "SELECT * FROM records WHERE dataID=" + id;// 10行
		ResultSet rs0 = stmt.executeQuery(sql);
		int[] indexID = new int[3];

		List<Data> listOfData = new ArrayList<Data>();
		if (rs0.next()) // 已经存在
		{
			indexID[0] = rs0.getInt("FirstIndexID");
			indexID[1] = rs0.getInt("SecondIndexID");
			indexID[2] = rs0.getInt("ThirdIndexID");

			// 根据指标id查找指标的名称
			String[] IndexName = new String[3];
			String sql1 = "SELECT IndexName FROM firstindex WHERE IndexID = " + indexID[0];// 10行
			ResultSet rs1 = stmt.executeQuery(sql1);
			if (rs1.next()) // 已经存在
				IndexName[0] = rs1.getString("IndexName");
			rs1.close();

			String sql2 = "SELECT IndexName FROM secondindex WHERE IndexID = " + indexID[1];// 10行
			ResultSet rs2 = stmt.executeQuery(sql2);
			if (rs2.next()) // 已经存在
				IndexName[1] = rs2.getString("IndexName");
			rs2.close();

			String sql3 = "SELECT IndexName FROM thirdindex WHERE IndexID = " + indexID[2];// 10行
			ResultSet rs3 = stmt.executeQuery(sql3);
			if (rs3.next()) // 已经存在
				IndexName[2] = rs3.getString("IndexName");
			rs3.close();

			ResultSet rs = stmt.executeQuery(sql);
			// 插入需要的json数据内容
			rs.next();
			listOfData.add(new Data(id, rs.getString("Country"), rs.getString("Year"), IndexName[0], IndexName[1],
					IndexName[2], rs.getDouble("IndexValue")));
			rs.close();
		}
		String jsonOutput = JSON.toJSONString(listOfData);
		writeToFile(jsonOutput, "src/query_id_data.json");
		cst.close();// 关闭语句对象
		dc.close();// 关闭数据库连接
	}

	/**
	 * 给定指标、国家、年份查询具体的一条记录 返回一个json数据 包括id,国家，年份，指标名称不含编号（一级二级三级）和得分
	 */
	public static void inquireRecord(String s1, String s2, String s3, String country, String year) throws SQLException
	{
		Statement stmt;
		JDBCConnection dc = new JDBCConnection();// 建立数据库连接
		CreateStatement cst = new CreateStatement(dc);// 创建语句对象
		stmt = cst.stmt;

		// 根据指标名称获得指标id
		int[] indexid = new int[3];
		String sql1 = "SELECT IndexID FROM firstindex WHERE IndexName = '" + s1 + "'";// 10行
		ResultSet rs1 = stmt.executeQuery(sql1);
		if (rs1.next()) // 已经存在
			indexid[0] = rs1.getInt("IndexID");
		rs1.close();

		String sql2 = "SELECT IndexID FROM secondindex WHERE IndexName = '" + s2 + "'";// 10行
		ResultSet rs2 = stmt.executeQuery(sql2);
		if (rs2.next()) // 已经存在
			indexid[1] = rs2.getInt("IndexID");
		rs2.close();

		String sql3 = "SELECT IndexID FROM thirdindex WHERE IndexName = '" + s3 + "'";// 10行
		ResultSet rs3 = stmt.executeQuery(sql3);
		if (rs3.next()) // 已经存在
			indexid[2] = rs3.getInt("IndexID");
		rs3.close();

		String sql = "SELECT * FROM records WHERE FirstIndexID=" + indexid[0] + " AND SecondIndexID=" + indexid[1]
				+ " AND ThirdIndexID=" + indexid[2] + " AND Country='" + country + "' AND Year='" + year + "'";
		List<Data> listOfData = new ArrayList<Data>();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		listOfData.add(new Data(rs.getInt("dataID"), country, year, s1, s2, s3, rs.getDouble("IndexValue")));
		rs.close();

		String jsonOutput = JSON.toJSONString(listOfData);
		writeToFile(jsonOutput, "src/query_a_data.json");
		cst.close();// 关闭语句对象
		dc.close();// 关闭数据库连接
	}

	/**
	 * 查询给定指标名称下的数据，返回json数据文件
	 */
	public static void inquireIndex(String s1, String s2, String s3) throws SQLException
	{
		Statement stmt;
		JDBCConnection dc = new JDBCConnection();// 建立数据库连接
		CreateStatement cst = new CreateStatement(dc);// 创建语句对象
		stmt = cst.stmt;

		// 知道指标名称查找指标的id
		int[] indexid = new int[3];
		String sql1 = "SELECT IndexID FROM firstindex WHERE IndexName = '" + s1 + "'";// 10行
		ResultSet rs1 = stmt.executeQuery(sql1);
		if (rs1.next()) // 已经存在
			indexid[0] = rs1.getInt("IndexID");
		rs1.close();

		String sql2 = "SELECT IndexID FROM secondindex WHERE IndexName = '" + s2 + "'";// 10行
		ResultSet rs2 = stmt.executeQuery(sql2);
		if (rs2.next()) // 已经存在
			indexid[1] = rs2.getInt("IndexID");
		rs2.close();

		String sql3 = "SELECT IndexID FROM thirdindex WHERE IndexName = '" + s3 + "'";// 10行
		ResultSet rs3 = stmt.executeQuery(sql3);
		if (rs3.next()) // 已经存在
			indexid[2] = rs3.getInt("IndexID");
		rs3.close();

		// 根据指标id进行select
		String sqlQuery = "SELECT * FROM records WHERE FirstIndexID=" + indexid[0] + " AND SecondIndexID=" + indexid[1]
				+ " AND ThirdIndexID=" + indexid[2];
		ResultSet rs = stmt.executeQuery(sqlQuery);
		List<Data> listOfData = new ArrayList<Data>();
		while (rs.next())
			listOfData.add(new Data(rs.getInt("dataID"), rs.getString("Country"), rs.getString("Year"), s1, s2, s3,
					rs.getDouble("IndexValue")));

		String jsonOutput = JSON.toJSONString(listOfData);
		writeToFile(jsonOutput, "src/query_Index.json");
		rs.close();
		cst.close();// 关闭语句对象
		dc.close();// 关闭数据库连接
	}

	/**
	 * 将字符串str写入文件的操作
	 */
	public static void writeToFile(String str, String fileName)
	{
		File file = new File(fileName);
		try
		{
			if (!file.exists())
			{
				file.createNewFile();
			}
			else
			{
				file.delete();
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file, false);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(str);
			bufferWritter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

class Data
{
	public int record_id;
	public String country_name;
	public String year;
	public String first_index;
	public String second_index;
	public String third_index;
	public double data_value;

	public Data(int record_id, String country_name, String year, String first_index, String second_index,
			String third_index, double data_value)
	{
		this.record_id = record_id;
		this.country_name = country_name;
		this.year = year;
		this.first_index = first_index;
		this.second_index = second_index;
		this.third_index = third_index;
		this.data_value = data_value;
	}
}
