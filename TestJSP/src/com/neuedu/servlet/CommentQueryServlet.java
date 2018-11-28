package com.neuedu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.Comment;
import com.neuedu.service.NewsService;
import com.neuedu.service.impl.NewsServiceImpl;

public class CommentQueryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//接收数据
		int newsid = Integer.parseInt(request.getParameter("newsid"));
		
		//组合业务层对象
		NewsService newsService = new NewsServiceImpl();
		
		//调用业务层方法
		List<Comment> commentList = newsService.getCommentList(newsid);
		
		//在request属性范围中保存新闻评论列表
		request.setAttribute("commentList", commentList);

		//在request属性范围中保存当前新闻编号---添加新闻时使用
		request.setAttribute("newsid", newsid);  
		 
		//转发到新闻评论列表查询页面
		request.getRequestDispatcher("comment_query.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}