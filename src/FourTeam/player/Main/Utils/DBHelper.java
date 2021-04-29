package FourTeam.player.Main.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

public class DBHelper {
	public static int update (String sql, Object...params) throws SQLException {
		System.out.println ("SQL:" + sql);
		System.out.println ("参数:" + Arrays.toString(params));
		
		String url = "jdbc:mysql://127.0.0.1:3306/books";
		String user = "root";
		String pwd = "a";
		Connection con = DriverManager.getConnection (url, user, pwd);
		PreparedStatement ps = con.prepareStatement (sql);
		
		for (int i = 0; i < params.length; i ++) {
			ps.setObject(i + 1, params[i]);
		}
		int ret = ps.executeUpdate();
		con.close();
		return ret;
	}
	public static int count () throws SQLException {
		String url = "jdbc:mysql://127.0.0.1:3306/books";
		String user = "root";
		String pwd = "a";
		Connection con = DriverManager.getConnection (url, user, pwd);
		Statement stmt = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet rset = stmt.executeQuery("select * from history");
		int rowCount = 0;
		while(rset.next()) {
		rowCount++;
		}
		return rowCount;
	}
	public static List<Map<String, Object>> select(String sql, Object... params) throws SQLException {
		// 便于调试打印sql语句的参�?
		System.out.println("SQL:" + sql);
		System.out.println("参数�?" + Arrays.toString(params));

		String pwd = "a";
		String user = "root";
		String url = "jdbc:mysql://127.0.0.1:3306/books?zeroDateTimeBehavior=convertToNull";
		// 加载驱动
		// 获取连接
		Connection con = DriverManager.getConnection(url, user, pwd);

		// 创建语句
		PreparedStatement ps = con.prepareStatement(sql);
		// 执行语句
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i + 1, params[i]);
		}

		// int ret = ps.executeUpdate();

		// 执行查询语句返回查询结果
		ResultSet rs = ps.executeQuery();

		// 将查询结果封装到List集合�?

		// 获取查询结果元数据对�? Mate �? ，元数据
		ResultSetMetaData rsmd = rs.getMetaData();

		List<Map<String, Object>> ret = new ArrayList<>();
		// rs.next用于判断是否读到�?行数据，如果读到了就返回true
		while (rs.next()) {
			Map<String, Object> row = new LinkedHashMap<>();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				String columnName = rsmd.getColumnName(i + 1);//
				Object columnValue = rs.getObject(i + 1);
				row.put(columnName, columnValue);
			}
			ret.add(row);
		}

		// 关闭连接
		con.close();
		return ret;
	}
	public static ResultSet resultSet (String sql) throws SQLException {
		String pwd = "a";
		String user = "root";
		String url = "jdbc:mysql://127.0.0.1:3306/books?zeroDateTimeBehavior=convertToNull";
		// 加载驱动
		// 获取连接
		Connection con = DriverManager.getConnection(url, user, pwd);

		// 创建语句
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	/**
	 * 
	 * List 集合：有�? 可重复单列（单元素）集合 Map 集合：键值对集合的双列集�? 无序不重�?
	 * 
	 * 集合 + 范型
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */


	

}
