package com.neuedu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.service.NewsService;
import com.neuedu.service.impl.NewsServiceImpl;
import com.neuedu.vo.NewsPage;

public class NewsQueryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//���յ�ǰҳ��
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		//��ȡ��ҳ��С
		int pageSize = Integer.parseInt(this.getInitParameter("pageSize"));
		
		//���ҵ�����
		NewsService newsService = new NewsServiceImpl();
		
		//����ҵ�񷽷�---��ѯ���ŵ��б�
		NewsPage newsPage = newsService.getNewsPage(currentPage, pageSize);
		
		//��request���Է�Χ�б����б�
		request.setAttribute("newsPage", newsPage);
		
		//ת�������Ų�ѯҳ��
		request.getRequestDispatcher("news_query.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}