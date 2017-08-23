package com.banana.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banana.util.DBUtil;

/**
 * 处理与文章有关的，与数据库操作有关的，文章的添加，修改，删除等。。。。
 * 
 * @author Banana on 2017/07/17
 *
 */
public class ArticleDao {

	public static boolean Insert(String sql,String title,String content){
		//建立数据库的连接
		Connection conn=DBUtil.getConn();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			//设置值
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			//执行
			int row=pstmt.executeUpdate();//执行成功，返回为1；失败为0
			 return row>0;
		} catch (SQLException e) {
			System.out.println("数据库中文章插入操作出错");
		}finally{
			DBUtil.close(conn, pstmt, null);
		}
		
		return false;
	}
	
	
	/**
	 * 获取文章列表
	 * @param sql
	 * @return
	 */
	public static List<Map<String,Object>> ListInfo(String sql){
		//连接数据库
		Connection conn=DBUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		//
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ResultSetMetaData rData = rs.getMetaData();
				int count = rData.getColumnCount();//每一条记录中有几列，对于我的例子，count=2,字段artical_id和title
				Map<String,Object> map = new HashMap<String,Object>();//每一条数据，放在一个map集合中
				for(int i=1;i<=count;i++){
					String cName = rData.getColumnName(i);//第一次循环，得到article_id，作为key
					Object cValue = rs.getObject(i);//article_id的值作为值
					map.put(cName, cValue);//放在map集合中
				}
				result.add(map);//什么时候加入集合中很重要，还有上述集合的定义在什么位置也很重要
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, pstmt, rs);
		}
		return result;
	}
	
	/**
	 * 根据id查出文章的详细信息，将其存入map集合中
	 * @param sql
	 * @param article_id
	 * @return
	 */
	public static Map<String,Object> query(String sql,String article_id){
		//建立连接
		Connection conn=DBUtil.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Map<String,Object> map=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, article_id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ResultSetMetaData rsmd=rs.getMetaData();
				int columnCount=rsmd.getColumnCount();//得到列的总数
				map=new HashMap<String, Object>();
				for(int i=1;i<=columnCount;i++){
					String columnName=rsmd.getColumnName(i);
					Object value=rs.getObject(columnName);
					map.put(columnName,value);
				}
			}
		} catch (SQLException e) {
			System.out.println("从数据库中获得文章的详细信息出错");
		}finally{
			DBUtil.close(conn, pstmt, rs);
		}
		return map;
	}
	
	
}
