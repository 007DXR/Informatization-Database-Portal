package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection
{
	public Connection connection;// 数据库连接对象

	public static void main(String[] args)
	{
		JDBCConnection dc = new JDBCConnection();// 建立数据库连接
		dc.close();// 关闭数据库连接
	}

	public JDBCConnection()
	{ // 建立数据库连接
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("正在建立数据库连接……");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/showdata?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai",
					"root", "whl200228");

			// System.out.println("数据库连接成功");
		} // 15行
		catch (ClassNotFoundException ex)
		{// 找不到数据库驱动程序
			System.out.println("找不到数据库驱动程序");
		}
		catch (SQLException ex)
		{// 不能连接到数据库
			System.out.println("不能建立与数据库的连接"); // 20行
		}
	}

	public void close()
	{ // 关闭数据库连接
		try
		{
			// System.out.println("正在关闭数据库连接……"); // 25行
			connection.close();
			// System.out.println("数据库连接关闭成功");
		}
		catch (Exception e)
		{
			System.out.println("数据库连接关闭失败"); // 30行
		}
	}
}