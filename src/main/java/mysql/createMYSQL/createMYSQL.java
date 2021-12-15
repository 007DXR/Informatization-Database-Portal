package mysql.createMYSQL;

import java.sql.SQLException;
import java.sql.Statement;

import mysql.createStmt.CreateStatement;
import mysql.dbConnection.JDBCConnection;

public class createMYSQL
{
	// 这个程序是用来创建数据表 并将数据插入到表中
	private static Statement stmt;

	public static void main(String[] args)
	{
		JDBCConnection dc = new JDBCConnection();// 建立数据库连接
		CreateStatement cst = new CreateStatement(dc);// 创建语句对象 //10行
		stmt = cst.stmt;
		createTable();
		cst.close();// 关闭语句对象
		dc.close();// 关闭数据库连接
	}

	// 然后创建数据表
	public static void createTable()
	{
		String s1 = "CREATE TABLE IF NOT EXISTS records(dataID integer not null PRIMARY key, "
				+ "Country varchar(10), Year char(4), FirstIndexID integer, SecondIndexID integer, ThirdIndexID integer, IndexValue double(20,16))";
		// 表1的结构 dataID(主键),国别,年份,一级指标ID,二级指标ID,三级指标ID,指标值
		String s2 = "CREATE TABLE IF NOT EXISTS firstindex(IndexID integer AUTO_INCREMENT PRIMARY key, "
				+ "IndexName varchar(30), IndexNumeration varchar(10))"; // id(指标id)，指标名称，指标编号
		String s3 = "CREATE TABLE IF NOT EXISTS secondindex(IndexID integer AUTO_INCREMENT PRIMARY key, "
				+ "IndexName varchar(30), IndexNumeration varchar(10))";
		String s4 = "CREATE TABLE IF NOT EXISTS thirdindex(IndexID integer AUTO_INCREMENT PRIMARY key, "
				+ "IndexName varchar(30), IndexNumeration varchar(10))";
		try
		{
			stmt.executeUpdate(s1);
			stmt.executeUpdate(s2);
			stmt.executeUpdate(s3);
			stmt.executeUpdate(s4);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
