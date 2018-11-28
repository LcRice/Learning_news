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

		// ��ȡout�������
		PrintWriter out = response.getWriter();

		// ��ȡsession����
		HttpSession session = request.getSession();

		// ��ȡapplication����
		ServletContext application = this.getServletContext();

	/*	// �������ļ��ж�ȡ�ַ�����
		String charSet = this.getServletContext().getInitParameter("charSet");

		// �����ַ�����
		request.setCharacterEncoding(charSet);*/

		String username = "";
		String password = "";
		try {
			username = request.getParameter("username");
			password = request.getParameter("password");
		} catch (Exception e) {
			out.print("����Ϊ�գ���<a href='user_login.jsp'>��������</a>");
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
		
		
		
		// ���������������
		if (user != null) {

			// ��¼�ɹ�����ѡ�и�ѡ��ʱ������һ���־û�cookie

			// if(member!=null){ //ѡ�и�ѡ��
			if (autoLogin != null) { // ѡ�и�ѡ��
				// ����һ���־û�cookie
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
			out.println("<script> alert('�Ñ����ܴa�e�`��Ո���µ��');history.back();</script>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
