package com.banana.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 建立数据库的连接
 * @author Banana on 2017/07/13
 *
 */
public class DBUtil {

	private static final String DB_URL="jdbc:mysql://localhost:3306/db_blog";
	private static final String DB_ACCT="root";
	private static final String DB_PWD="root";
	private static final String DB_DRIVER="com.mysql.jdbc.Driver";
	
	/*
	 * 连接数据库：
	 * 注册驱动和获得连接
	 */
	static {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("注册驱动失败");
		}
	}
	public static Connection getConn(){
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(DB_URL, DB_ACCT, DB_PWD);
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
		}
		return conn;
	}
	
	/**
	 * 关闭数据库
	 * @param conn
	 */
	public static void close(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
