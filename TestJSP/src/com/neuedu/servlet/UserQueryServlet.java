package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;
import com.neuedu.vo.UserPage;

public class UserQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���������������
		response.setContentType("text/html;charset=utf-8");

		// ��ȡout�������
		PrintWriter out = response.getWriter();

		// ��ȡsession����
		HttpSession session = request.getSession();

		// ��ȡapplication����
		ServletContext application = this.getServletContext();

		// �������ļ��ж�ȡ�ַ�����
		String charSet = this.getServletContext().getInitParameter("charSet");

		// �����ַ�����
		request.setCharacterEncoding(charSet);

		// �����û���
		String username = request.getParameter("username");
		if (username == null) {
			username = "";
		}

		String gender = request.getParameter("gender");
		if (gender == null) {
			gender = "";
		}

		String job = request.getParameter("job");
		if (job == null) {
			job = "";
		}

		String regtime = request.getParameter("regtime");
		if (regtime == null) {
			regtime = "ȫ��ʱ��";
		}

		String begintime = "";
		String endtime = "";
		if ("ָ��ʱ��".equals(regtime)) {
			begintime = request.getParameter("begintime");
			endtime = request.getParameter("endtime");
		}

		UserService userService = new UserServiceImpl();

		// ����Ҫ�޸ĵ�userid

		String userid = "";
		userid = request.getParameter("userid");

		if (userid != null) {
			User user = userService.findUser(Integer.parseInt(userid));
			session.setAttribute("user", user);
			request.getRequestDispatcher("user_update_self.jsp").forward(request, response);
		}

		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		int pageSize = Integer.parseInt(this.getInitParameter("pageSize"));

		UserPage userPage = userService.getUserPage(username,gender,job,begintime,endtime,currentPage, pageSize);

		request.setAttribute("userPage", userPage);

		request.setAttribute("username", username);

		request.setAttribute("gender", gender);

		request.setAttribute("job", job);

		request.setAttribute("regtime", regtime);
		request.setAttribute("begintime", begintime);
		request.setAttribute("endtime", endtime);

		request.getRequestDispatcher("user_query.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
