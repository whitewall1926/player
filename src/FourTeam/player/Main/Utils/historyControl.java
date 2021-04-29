package FourTeam.player.Main.Utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.swing.text.BadLocationException;


public class historyControl {
	public void remove (int id) throws Exception {
		if (id <= 0)
			{throw new Exception("编号必须大于0");
			}
		String sql = "select * from history where id = ?";
		List<Map<String, Object>> list = DBHelper.select (sql, id);
		if(list.isEmpty() == true) {
			throw new Exception ("系统没有该编号对应得记录");
		}
		sql = "delete from history where id = ? ";
		DBHelper.update (sql, id);
	} 
	public void removeAll (int id) throws Exception {
		if (id <= 0)
			{throw new Exception("编号必须大于0");
			}
		String sql = "select * from history where id >= ?";
		List<Map<String, Object>> list = DBHelper.select (sql, id);
		if(list.isEmpty() == true) {
			throw new Exception ("系统没有该编号对应得记录");
		}
		sql = "delete from history where id >= ? ";
		DBHelper.update (sql, id);
	} 
}
