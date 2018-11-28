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
		// 设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象
		PrintWriter out = response.getWriter();

		// 获取session对象
		HttpSession session = request.getSession();

		// 获取application对象
		ServletContext application = this.getServletContext();

		// 从配置文件中读取字符编码
		String charSet = this.getServletContext().getInitParameter("charSet");

		// 设置字符编码
		request.setCharacterEncoding(charSet);

		// 接收用户名
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
			regtime = "全部时间";
		}

		String begintime = "";
		String endtime = "";
		if ("指定时间".equals(regtime)) {
			begintime = request.getParameter("begintime");
			endtime = request.getParameter("endtime");
		}

		UserService userService = new UserServiceImpl();

		// 接收要修改的userid

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
