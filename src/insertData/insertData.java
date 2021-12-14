package insertData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import createStmt.CreateStatement;
import dbConnection.JDBCConnection;

public class insertData
{
	@JSONField(name = "记录ID")
	public int id;
	@JSONField(name = "国家")
	public String country;
	@JSONField(name = "值")
	public double value;
	@JSONField(name = "年份")
	public String year;

	public static void main(String[] args) throws SQLException
	{
		Statement stmt; // 连接一波数据库
		JDBCConnection dc = new JDBCConnection();// 建立数据库连接
		CreateStatement cst = new CreateStatement(dc);// 创建语句对象
		stmt = cst.stmt;

		String str = ReadFile("src/insertData/data.json"); // 注意路径的问题
		JSONObject jsonObject = JSONObject.parseObject(str);
		String first[] = new String[] { "信息基础设施_A-1", "信息化环境_B-1" };
		String second[] = new String[] { "带宽水平_A-11", "通信和网络接入成本_A-12", "居民素质_B-11", "经济环境_B-13", };
		String third[] = new String[] { "互联网用户的平均上网带宽_A-111", "每千名互联网用户的平均国际互联网出口宽带_A-112", "固定电话资费占人均收入的比重_A-121",
				"移动电话资费占人均收入的比重_A-122", "高中教育毛入学率_B-111", "高等教育毛入学率_B-112", "人均GDP_B-131",
				"信息传播与服务业的增加值占GDP的比重_B-132" };// 8个
		int i = 0, j = 0, k = 0; // 自增编号从1开始
		for (i = 0; i < 2; i++)
		{
			String strA = jsonObject.getString(first[i]); // 得到一级指标的json值
			while (j < 2 * (i + 1))// 刚好一个指标下有两个指标
			{
				JSONObject jsonObjectB = JSONObject.parseObject(strA);
				String strB = jsonObjectB.getString(second[j]);// 获取二级指标的jsonvalue
				while (k < 2 * (j + 1))
				{
					JSONObject jsonObjectC = JSONObject.parseObject(strB);
					String strC = jsonObjectC.getString(third[k]); // 获取三级指标的jsonvalue-json数组

					// 接下来要处理C级指标下的数据
					List<insertData> list = JSONObject.parseArray(strC, insertData.class);

					for (int u = 0; u < list.size(); u++)
					{
						insertData data = list.get(u);
						String s = "INSERT IGNORE INTO records (dataID, Country, Year,FirstIndexID,SecondIndexID,ThirdIndexID,IndexValue) Values ('"
								+ data.id + "','" + data.country + "','" + data.year + "','" + (i + 1) + "','" + (j + 1)
								+ "','" + (k + 1) + "','" + data.value + "')";
						stmt.executeUpdate(s);

					}
					String[] str3 = third[k].split("_");
					String sss = "INSERT IGNORE INTO thirdindex (IndexID,IndexName,IndexNumeration) Values ('" + (k + 1)
							+ "','" + str3[0] + "','" + str3[1] + "')";
					stmt.executeUpdate(sss);// 发送SQL语句
					k++;
				}

				String[] str2 = second[j].split("_");
				String ss = "INSERT IGNORE INTO secondindex (IndexID,IndexName,IndexNumeration) Values ('" + (j + 1)
						+ "','" + str2[0] + "','" + str2[1] + "')";
				stmt.executeUpdate(ss);// 发送SQL语句
				j++;
			}
			String[] str1 = first[i].split("_");
			String s1 = "INSERT IGNORE INTO firstindex (IndexID,IndexName,IndexNumeration) Values ('" + (i + 1) + "','"
					+ str1[0] + "','" + str1[1] + "')";
			stmt.executeUpdate(s1);// 发送SQL语句
		}
		// String s11 = "INSERT INTO firstindex
		// (IndexID,IndexName,IndexNumeration) Values (null,'www','wwww')";
		// stmt.executeUpdate(s11);// 发送SQL语句
		cst.close();// 关闭语句对象
		dc.close();// 关闭数据库连接
	}

	// 读取json文件 返回string对象
	public static String ReadFile(String Path)
	{
		BufferedReader reader = null;
		String laststr = "";
		try
		{
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null)
			{
				laststr += tempString;
			}
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}
}
