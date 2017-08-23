package com.banana.action;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banana.dao.UserDao;
import com.banana.util.DBUtil;

public class CheckUserNameAction extends HttpServlet{

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Type", "application/json;charset=utf-8");
        
		//resp.setContentType("text/html;charset=utf-8");
		String username=req.getParameter("username");
		
		//创建sql语句
		String sql="select username from t_userInfo where username=?";
		
		boolean result=UserDao.check(sql,username);
		if(result){
			resp.getWriter().write("{\"success\":true}");
		}else{
			resp.getWriter().write("{\"success\":false}");
		}
		
		
		
	}
}
