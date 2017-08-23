package com.banana.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.banana.util.DBUtil;

/**
 * 与用户有关的所有数据库处理，包括：用户登陆，用户注册，用户退出，新增用户。。。
 * @author lenovo
 *
 */
public class UserDao {

	/**
	 * 查询数据库中是否存在该用户，验证登陆
	 * @param sql  传入的查询sql语句，
	 * @param username  用户名
	 * @param password  密码
	 * @return
	 */
	public static boolean query(String sql,String username,String password){
		Connection conn=DBUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);//sql语句预处理
			//设置参数
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			//执行
			rs=pstmt.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			System.out.println("sql登陆查询语句处理出错");

		}finally{
			DBUtil.close(conn, pstmt,rs);
		}
		return false;
	}
	
	/**
	 * 用户注册时，验证用户名是否已存在
	 * @param sql
	 * @param username
	 * @return
	 */
	public static boolean check(String sql,String username){
		
		//建立数据库的连接
		Connection conn=DBUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs=pstmt.executeQuery();//执行
			if(rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("数据库查询用户名是否存在失败");
		}finally{
			DBUtil.close(conn, pstmt,rs);
		}
		return false;
	}
	
	/**
	 * 新用户注册时，将用户信息存入数据库
	 * @param sql
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean Insert(String sql,String username,String password){
		//获取数据库的连接
		Connection conn=DBUtil.getConn();
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			//设置值
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			//执行
			int row=pstmt.executeUpdate();//执行成功返回1，失败返回0
			//处理结果集
			return row>0;
			
		} catch (SQLException e) {
			System.out.println("数据库插入数据失败");
		}
		
		return false;
	}
	
	/**
	 * 将用户信息以Map集合的方式放入session中
	 * @param sql
	 * @param args
	 * @return
	 */
	public static Map<String,Object> querySession(String sql,Object...args){
		Connection conn=DBUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			//设置值
			for(int i=0;i<args.length;i++){
				if(args[i]!=null){
					pstmt.setObject(i+1, args[i]);
				}
			}
			//执行
			rs=pstmt.executeQuery();
			if(rs.next()){
				return rsToMap(rs);
			}
			
		} catch (SQLException e) {
			System.out.println("设置session出错");
		}finally{
			DBUtil.close(conn, pstmt, rs);
		}
		return null;
	}
	
	
	/**
	 * 将结果集转成Map集合
	 * @param rs
	 * @return
	 */
	public static Map<String,Object> rsToMap(ResultSet rs){
		Map<String,Object> map=new HashMap<String,Object>();
		ResultSetMetaData rsmd=null;
		try {
			rsmd=rs.getMetaData();
			int count=rsmd.getColumnCount();
			for(int i=1;i<=count;i++){
				String columnName=rsmd.getColumnName(i);
				Object value=rs.getObject(i);  //？？？
				map.put(columnName, value);
			}
					
		} catch (SQLException e) {
			System.out.println("结果集转成map集合出错");
		}
		return map;
	}
	
	
}
