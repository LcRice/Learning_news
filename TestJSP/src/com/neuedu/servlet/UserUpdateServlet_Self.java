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

public class UserUpdateServlet_Self extends HttpServlet {
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

		User user = (User) session.getAttribute("user");

		int userid = user.getUserid();
		System.out.println(user);

		String username = "";
		String photo = "";
		String gender = "";
		String job = "";
		String[] interests = null;
		String interest = "";
		try {
			username = request.getParameter("username");
			System.out.println(username + "a");
			if (username == null || "".equals(username)) {
				username = user.getUsername();
			}
			photo = request.getParameter("photo");
			gender = request.getParameter("gender");
			job = request.getParameter("job");
			interests = request.getParameterValues("interest");

			if (interests != null) {
				for (String s : interests) {
					interest += s + " ";
				}
				interest.trim();
			}
		} catch (Exception e) {
			out.print("error for input!");
		}

		UserService userService = new UserServiceImpl();
		if (userService.checkUsername(username,userid)) {
			if (userService.updateUser(userid, username, photo, gender, job, interest)) {
				user = new User(username,user.getPassword(),user.getScore(),photo,gender,job,interest,user.getRegtime());
				session.setAttribute("user", user);
				out.print("<script>alert('修改成功！');location='user_update_self.jsp';</script>");
			} else {
				out.print("<script>alert('修改失败！');history.back();</script>");
			}
		}else {
			out.print("<script>alert('用户名已存在！');history.back();</script>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
