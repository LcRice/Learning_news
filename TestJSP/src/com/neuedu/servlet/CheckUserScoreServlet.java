package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.News;
import com.neuedu.service.NewsService;
import com.neuedu.service.impl.NewsServiceImpl;

public class CheckUserScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象
		PrintWriter out = response.getWriter();
		
		String attachment = request.getParameter("attachment");
		
		NewsService newsService = new NewsServiceImpl();
		
	/*	News news = newsService.findTrueName(attachment);
		
		if(news.getDownloadcount()<news.getUser().getScore()){
			out.print("success");
		}else{
			out.print("fail");
		}
*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
