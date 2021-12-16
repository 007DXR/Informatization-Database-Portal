package mysql;
import java.sql.SQLException;
public class SQLMain {
    public static void main(String[] args) throws SQLException {
		// test:
		// addIndex("信息基础设施_A-1", "带宽水平_A-11", "拥有宽带无线网接入的公共场所比例_A-113");
		// addRecord("信息基础设施_A-1", "通信和网络接入成本_A-12",
		// "固网宽带互联网接入资费占人均收入的比重_A-123", 25);
		// deleteRecord(25);
		// modifyRecord(0, "法国", "2020", 1);
		// modifyIndex("信息基础设施", "xinxijichusheshi");
		// inquireRecord(0);
		mysql.function.inquireIndex("信息基础设施", "带宽水平", "互联网用户的平均上网带宽");
	}
}
