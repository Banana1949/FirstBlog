package com.banana.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.banana.dao.ArticleDao;

public class listInfoAction extends HttpServlet{
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置响应格式，返回json字符串时
		resp.setContentType("utf-8");
		resp.setHeader("Content-Type", "application/json;charset=utf-8");
		String json = "";
		//传入sql语句，得到处理的结果
		String sql="select article_id,title from t_article order by article_id desc";
		List<Map<String,Object>> result = ArticleDao.ListInfo(sql);
		//下面是拼接json串
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(Map<String,Object> m:result){
			StringBuilder sb1 = new StringBuilder();
			sb1.append("{");
			Set<String> keys = m.keySet();
			Iterator<String> iterator = keys.iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				Object value = m.get(key);
				sb1.append("\""+key+"\":");
				if (value instanceof String) {
					sb1.append("\""+value+"\",");
				}else{
					sb1.append(value+",");
				}
			}
			String s1 = sb1.toString();
			if(s1!=null && s1.endsWith(",")){ //删除句末的最后一个逗号
				sb.append(s1.substring(0, s1.length()-1)+"},");
			}
		}
		String s = sb.toString();
		if(s!=null && s.endsWith(",")){  //删除句末的最后一个逗号
			s = s.substring(0, s.length()-1);
		}
		json = s+"]";
		resp.getWriter().write("{\"success\":true,\"data\":"+json+"}");
	}

	
}
