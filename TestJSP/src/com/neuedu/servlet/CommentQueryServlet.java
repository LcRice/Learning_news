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
	
		//��������
		int newsid = Integer.parseInt(request.getParameter("newsid"));
		
		//���ҵ������
		NewsService newsService = new NewsServiceImpl();
		
		//����ҵ��㷽��
		List<Comment> commentList = newsService.getCommentList(newsid);
		
		//��request���Է�Χ�б������������б�
		request.setAttribute("commentList", commentList);

		//��request���Է�Χ�б��浱ǰ���ű��---�������ʱʹ��
		request.setAttribute("newsid", newsid);  
		 
		//ת�������������б��ѯҳ��
		request.getRequestDispatcher("comment_query.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}