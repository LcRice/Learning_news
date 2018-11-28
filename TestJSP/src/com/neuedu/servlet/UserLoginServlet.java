package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;
import com.neuedu.util.CookieUtil;

public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象
		PrintWriter out = response.getWriter();

		// 获取session对象
		HttpSession session = request.getSession();

		// 获取application对象
		ServletContext application = this.getServletContext();

	/*	// 从配置文件中读取字符编码
		String charSet = this.getServletContext().getInitParameter("charSet");

		// 设置字符编码
		request.setCharacterEncoding(charSet);*/

		String username = "";
		String password = "";
		try {
			username = request.getParameter("username");
			password = request.getParameter("password");
		} catch (Exception e) {
			out.print("密码为空，请<a href='user_login.jsp'>重新输入</a>");
		}
		
		String member = request.getParameter("member");
		String autoLogin = request.getParameter("autoLogin");

		int loginScore = Integer.parseInt(this.getInitParameter("loginScore"));

		UserService userService = new UserServiceImpl();

		User user = userService.login(username, password, loginScore);
		
		
		
		if(username != null && !username.equals("")) {  
			request.getSession().setAttribute("username",username);
		} 
		List<String> online = (List<String>)getServletContext().getAttribute("online");
		application.setAttribute("online", online);
		
		
		
		// 设置输出内容类型
		if (user != null) {

			// 登录成功并且选中复选框时，发送一个持久化cookie

			// if(member!=null){ //选中复选框
			if (autoLogin != null) { // 选中复选框
				// 发送一个持久化cookie
				CookieUtil.addCookie(response, "userInfo", username + "#" + password, 7);
			}

			session.setAttribute("user", user);

			if (application.getAttribute("onlineCount") == null) {
				application.setAttribute("onlineCount", 1);
			} else {
				application.setAttribute("onlineCount", (Integer) application.getAttribute("onlineCount") + 1);
			}
			String prevURL = "index.jsp";
			if(session.getAttribute("prevURL")!=null){
				prevURL = (String) session.getAttribute("prevURL");
			}
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/" + prevURL));
		} else {
			out.println("<script> alert('用戶名密碼錯誤，請重新登陸');history.back();</script>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
