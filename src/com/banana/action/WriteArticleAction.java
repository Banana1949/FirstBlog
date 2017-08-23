package com.banana.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banana.constant.Constant;
import com.banana.dao.ArticleDao;

public class WriteArticleAction extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getSession().getAttribute(Constant.SESSION_USER)==null){//未登录，需要先登录
			req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
		}else{//如果已经登录，则直接跳到写文章页面
			req.getRequestDispatcher("/WEB-INF/writeArticle.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("utf-8");//指定http响应的编码  和浏览器的显示的编码
		resp.setHeader("Content-Type", "application/json;charset=utf-8");//响应的头信息的格式为 json串，编码格式为utf-8
		//得到页面传来的信息
		String title=req.getParameter("Title");
		String content=req.getParameter("Content");
		//设置sql语句
		String sql="insert into t_article(title,content) values (?,?)";
		//得到数据库处理的结果
		boolean result=ArticleDao.Insert(sql, title, content);
		if(result){
			//sql="selete title,content from t_article where title=? and content=?";
			resp.getWriter().write("{\"success\":true}");
		}else{
			resp.getWriter().write("{\"success\":false}");
		}
		
		
	}
	
	

	
}
