package com.banana.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banana.dao.ArticleDao;

public class ArticleInfoAction extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("utf-8");
		resp.setHeader("Content-Type", "application/json;charset=utf-8");
		String article_id=req.getParameter("aid");
		//创建sql语句
		String sql="select title,content from t_article where article_id=?";
		//得到返回的结果
		Map<String,Object> articleMap=ArticleDao.query(sql, article_id);
		req.setAttribute("title", articleMap.get("title"));
		req.setAttribute("content", articleMap.get("content"));
		
		
		
		
		
		req.getRequestDispatcher("WEB-INF/articleInfo.jsp").forward(req, resp);
	}

	

	
}
