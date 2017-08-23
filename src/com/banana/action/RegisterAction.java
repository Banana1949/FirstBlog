package com.banana.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banana.constant.Constant;
import com.banana.dao.UserDao;

public class RegisterAction extends HttpServlet{

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("utf-8");
		resp.setHeader("Content-Type", "application/json;charset=utf-8");
		//取值
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		//创建sql语句
		String sql="insert into t_userInfo(username,password) values(?,?)";
		
		boolean result=UserDao.Insert(sql, username, password);
		//处理数据库返回的结果
		if(result){
			sql="select * from t_userInfo where username=? and password=?";
			Map<String,Object> userMap=UserDao.querySession(sql,username,password);
			req.getSession().setAttribute(Constant.SESSION_USER, userMap);
			resp.getWriter().write("{\"success\":true}");
		}else{
			resp.getWriter().write("{\"success\":false}");
		}
	}

	
}
