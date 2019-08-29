package com.myQrScanner.db.getDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	//数据库信息
	private static final String URL = "jdbc:mysql://localhost:3306/qrproject";
	private static final String USER = "root";
	private static final String PASSWORD = "yy550432";
	private static Connection conn = null;

	static {
		try {
			// 加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 获得数据库的连接			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static Connection getConnection() {
		if(conn==null) {
			// 加载驱动程序
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 获得数据库的连接			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(conn.toString());
		return conn;
	}
//基本版本1.0
//	public static void main(String[] args) throws Exception {
//
//		// 加载驱动程序
//		Class.forName("com.mysql.jdbc.Driver");
//		// 获得数据库的连接
//		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//		// 操纵数据库，实现增删改查
//		Statement stmt = conn.createStatement();
//		ResultSet rs = stmt.executeQuery("select username from qrscanner");
//		while (rs.next()) {
//			System.out.println(rs.getString("username"));
//		}
//	}
}
