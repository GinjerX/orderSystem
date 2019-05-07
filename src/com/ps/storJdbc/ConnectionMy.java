package com.ps.storJdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class ConnectionMy {

	private static Connection conn = null;
//	private static BasicDataSource datasource = getBasicDataSource();
	
	private ConnectionMy() {
		
	}
	/**
	 * 
	 * initialSize=5
		maxTotal=4
		maxIdle=5
		minIdle=3
	 * @return
	 */
	
	public static BasicDataSource getBasicDataSource() {
		String path = "com\\ps\\storJdbc\\mysql.properties";
		Properties p = new Properties();
		BasicDataSource datasource = null;
		try {
			p.load( ConnectionMy.class.getClassLoader().getResourceAsStream(path) );
			datasource = BasicDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datasource;
	}
	
/*	public static synchronized Connection getConnection() {
		try {
			conn = datasource.getConnection();
		} catch (Exception e1) {
			System.out.println("链接获取失败...");
		}
		
		return conn;
	}*/
	
	
	

	/**
		 * 连接本地数据库的方法
		 * 可传入数据库名
		 * @return
		 * @throws Exception
		 */
		public static Connection getConnection() {
			
			try {
				if(conn==null) {
					Class.forName("com.mysql.jdbc.Driver");
					Properties p =new Properties();
					InputStream is = ConnectionMy.class.getClassLoader().getResourceAsStream("\\mysql.properties");
					p.load(is);
					String url=p.getProperty("url");
					String user=p.getProperty("username");
					String password=p.getProperty("password");
					conn = DriverManager.getConnection(url,user,password);
					is.close();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
		}
}


