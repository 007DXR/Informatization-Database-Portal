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
			System.out.print("Connecting...");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/showdata?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai",
					"java21a", "1234");

			System.out.println("finished");
		} // 15行
		catch (ClassNotFoundException ex)
		{// 找不到数据库驱动程序
			System.out.println("\nNo JDBC Driver");
		}
		catch (SQLException ex)
		{// 不能连接到数据库
			System.out.println("\nFailed Connecting"); // 20行
		}
	}

	public void close()
	{ // 关闭数据库连接
		try
		{
			System.out.print("Database Connection Closing..."); 
			connection.close();
			System.out.println("Closed.");
		}
		catch (Exception e)
		{
			System.out.println("\nFailed Closing"); // 30行
		}
	}
}