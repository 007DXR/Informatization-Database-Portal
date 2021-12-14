package mysql.createStmt;//1行	

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mysql.dbConnection.JDBCConnection;

public class CreateStatement
{
	public Statement stmt;// 5行

	public static void main(String[] args)
	{
		JDBCConnection dc = new JDBCConnection();// 建立数据库连接
		CreateStatement cst = new CreateStatement(dc);// 创建语句对象
		cst.close();// 关闭语句对象
		dc.close();// 关闭数据库连接//10行
	}

	public CreateStatement(JDBCConnection dc)
	{
		try
		{
			stmt = dc.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);// 15行
			// System.out.println("语句对象创建成功");
		}
		catch (SQLException e)
		{
			System.out.println("不能建立语句对象");
		}
	} // 20行

	public void close()
	{ // 关闭语句对象
		try
		{
			// System.out.println("正在关闭语句对象……");
			stmt.close();
			// System.out.println("语句对象关闭成功");// 25行
		}
		catch (Exception e)
		{
			System.out.println("语句对象关闭失败");
		}
	}// 20行
}