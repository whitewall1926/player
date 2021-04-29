package FourTeam.player.Main.List;

import java.awt.BorderLayout;
import java.awt.Container;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import FourTeam.player.Main.Utils.DBHelper;
import FourTeam.player.Main.Utils.historyControl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class HistoryList extends JDialog {
	
	 JTable table;
	public HistoryList() throws SQLException {
		this.setTitle("历史记录");
		
		this.setSize (500, 561);
		this.setLocationRelativeTo (null);
		this.setDefaultCloseOperation (DISPOSE_ON_CLOSE);
		//显示所有历史记录
		
		String []columnNames = {"id", "name", "type", "address", "time"};
		//得到行数，并且得到每行每列的数据
		int row = DBHelper.count();
		Object[][] obj = new Object[row][5] ;
	//	 System.out.println (row);
		 ResultSet rset =   DBHelper.resultSet ("select * from history");
		 
		 row = 0;
		 ResultSetMetaData rsmd = rset.getMetaData();
		 while (rset.next()) {
			
			// System.out.println (rsmd.getColumnCount());
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					String columnName = rsmd.getColumnName(i + 1);//
					Object columnValue = rset.getObject(i + 1);
					obj[row][i] = columnValue;
				}
				row ++;
			}
		getContentPane().setLayout(new BorderLayout(0, 0));
		table = new JTable(obj, columnNames);
		//单元表更改后
		
		 TableColumn column ;
	        int colunms = table.getColumnCount();  
	        for(int i = 0; i < 5; i++)  
	        {  
	            column = table.getColumnModel().getColumn(i);  
	            /*将每一列的默认宽度设置为100*/  
	            column.setPreferredWidth(100);  
	        }  
		table.setBounds(1000, 81, 1000, 399);
		//table.setPreferredSize(new Dimension (1000, 1000));
		JScrollPane scroll = new JScrollPane (table);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getContentPane().add(scroll);
		JPanel jp = new JPanel ();
		jp.setPreferredSize (new Dimension (4, 40));
		getContentPane().add (jp, BorderLayout.SOUTH);
		jp.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		jp.add(panel);
	
		JSpinner spinner = new JSpinner();
		spinner.setPreferredSize(new Dimension(50, 20));
		panel.add(spinner);
		
		//删除某个记录
		JButton btnNewButton = new JButton("删除");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "delete * from history where id = ?";
				int id =  (int) spinner.getValue();
				System.out.println (id);
				try {
					new historyControl().remove (Integer.valueOf (id));
					int row = DBHelper.count();
					Object[][] obj = new Object[row][5] ;
				//	 System.out.println (row);
					 ResultSet rset =   DBHelper.resultSet ("select * from history");
					 
					 row = 0;
					 ResultSetMetaData rsmd = rset.getMetaData();
					 while (rset.next()) {
						
						// System.out.println (rsmd.getColumnCount());
							for (int i = 0; i < rsmd.getColumnCount(); i++) {
								String columnName = rsmd.getColumnName(i + 1);//
								Object columnValue = rset.getObject(i + 1);
								obj[row][i] = columnValue;
							}
							row ++;
						}
					 
					 //动态更新
					 DefaultTableModel tabModel = new DefaultTableModel(obj, columnNames);
					 table.setModel(tabModel);
					 table.setEnabled(true);
					 
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("清除");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	String sql = "delete * from history where id >= ?";
				int id =  1;
				System.out.println (id);
				try {
					new historyControl().removeAll (Integer.valueOf (id));
					int row = DBHelper.count();
					Object[][] obj = new Object[row][5] ;
				//	 System.out.println (row);
					 ResultSet rset =   DBHelper.resultSet ("select * from history");
					 
					 row = 0;
					 ResultSetMetaData rsmd = rset.getMetaData();
					 while (rset.next()) {
						
						// System.out.println (rsmd.getColumnCount());
							for (int i = 0; i < rsmd.getColumnCount(); i++) {
								String columnName = rsmd.getColumnName(i + 1);//
								Object columnValue = rset.getObject(i + 1);
								obj[row][i] = columnValue;
							}
							row ++;
						}
					 
					 //动态更新
					 DefaultTableModel tabModel = new DefaultTableModel(obj, columnNames);
					 table.setModel(tabModel);
					 table.setEnabled(true);
					 
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton_1);
		
		this.setVisible(true);
		
	}
}
