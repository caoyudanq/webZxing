package com.myQrScanner.db.getDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	//���ݿ���Ϣ
	private static final String URL = "jdbc:mysql://localhost:3306/qrproject";
	private static final String USER = "root";
	private static final String PASSWORD = "yy550432";
	private static Connection conn = null;

	static {
		try {
			// ������������
			Class.forName("com.mysql.jdbc.Driver");
			// ������ݿ������			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static Connection getConnection() {
		if(conn==null) {
			// ������������
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// ������ݿ������			
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
//�����汾1.0
//	public static void main(String[] args) throws Exception {
//
//		// ������������
//		Class.forName("com.mysql.jdbc.Driver");
//		// ������ݿ������
//		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//		// �������ݿ⣬ʵ����ɾ�Ĳ�
//		Statement stmt = conn.createStatement();
//		ResultSet rs = stmt.executeQuery("select username from qrscanner");
//		while (rs.next()) {
//			System.out.println(rs.getString("username"));
//		}
//	}
}
