package com.neuedu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.NewsType;
import com.neuedu.service.NewsTypeService;
import com.neuedu.service.impl.NewsTypeServiceImpl;

public class NewsTypeQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		
		NewsTypeService newsTypeService = new NewsTypeServiceImpl();

		// 获取application对象
		ServletContext application = this.getServletContext();

		int count = newsTypeService.findAllLine();

		List<NewsType> list = newsTypeService.findAllType();

		application.setAttribute("list", list);

		// request.getRequestDispatcher("news_add.jsp").forward(request,
		// response);

		System.out.println("NewsInitServlet被创建,新闻类型列表读取成功");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
