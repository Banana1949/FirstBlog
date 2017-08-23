package com.banana.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banana.constant.Constant;
import com.banana.dao.UserDao;

public class LoginAction extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//通过session值，检查当前用户是否已登陆
		if(req.getSession().getAttribute(Constant.SESSION_USER)==null){
			req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
		}else{//已登陆
			req.getRequestDispatcher("WEB-INF/main.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-Type", "application/json;charset=utf-8");
		
		String username=req.getParameter("userName");
		String password=req.getParameter("passWord");
		//创建sql语句
		String sql="select * from t_userInfo where username=? and password=?";
		boolean result=UserDao.query(sql,username,password);
		if(result){
			Map<String,Object> userMap=UserDao.querySession(sql, username,password);
			req.getSession().setAttribute(Constant.SESSION_USER, userMap);
			resp.getWriter().write("{\"success\":true}");
		}else{
			resp.getWriter().write("{\"success\":false,\"msg\":\"用户名或密码错误\"}");
		}
		
	}

	
}
