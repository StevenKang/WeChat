package net.timetown.wechat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MySQLHelper implements ServletContextListener {
	
	private static String url = "jdbc:mysql://mysql.4kb.cn:3306/wechat?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8";
	private static String username = "wechat", password = "SEpTEB2gZ2Ko";
	
	
	private Connection connection;
	
	private PreparedStatement statement;
	private ResultSet resultSet;

	private static MySQLHelper instance;
	
	public static MySQLHelper get() {
		return instance;
	}
	
	private void checkConnection() {
		try {
			if (!connection.isValid(5)) {
				System.out.println("重新连接MySQL");
				connection = DriverManager.getConnection(url, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private PreparedStatement getPreparedStatement(Connection con, String sql, Object...params) throws SQLException {
		checkConnection();
		
		PreparedStatement ps = con.prepareStatement(sql);
		if (params !=null) {
			for (int i=0; i<params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
		}
		return ps;
	}
	
	public int executeUpdate(String sql, List<Object> params){
		
		return executeUpdate(sql, params==null? null : params.toArray());
	}
	
	public int executeUpdate(String sql, Object...params){
		try {
			statement = getPreparedStatement(connection, sql, params);
			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public List<Map<String, Object>> executeQuery(String sql, List<Object> params) {
		return executeQuery(sql, params==null? null : params.toArray());
	}
	
	public List<Map<String, Object>> executeQuery(String sql, Object...params) {
		try {
			statement = getPreparedStatement(connection, sql, params);
			resultSet = statement.executeQuery();
			int columnCount = resultSet.getMetaData().getColumnCount();
			List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
			List<String> columnLabels = new ArrayList<String>();
			for (int i=0; i<columnCount; i++) {
				columnLabels.add(resultSet.getMetaData().getColumnLabel(i+1));
			}
			while(resultSet.next()) {
				Map<String, Object> data = new HashMap<String, Object>();
				for (String label : columnLabels) {
					Object val = resultSet.getObject(label);
					data.put(label, val);
				}
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		try {
			instance = this;
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("加载MySQL成功");
		} catch (Exception e) {
			System.out.println("加载MySQL失败");
			e.printStackTrace();
		}
		
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		instance = null;
		System.out.println("MySQL销毁成功");
	}
	
	
}
