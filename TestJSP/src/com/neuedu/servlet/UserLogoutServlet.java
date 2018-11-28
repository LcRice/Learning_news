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

public class UserLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate(); 
		/*// 获取session对象
		HttpSession session = request.getSession();

		// 获取application对象
		ServletContext application = this.getServletContext();

		session.removeAttribute("user");
		application.setAttribute("onlineCount", (Integer) application.getAttribute("onlineCount") - 1);
		response.sendRedirect("user_login.jsp");
*/
		List<String> online = (List<String>)getServletContext().getAttribute("online"); 
	    
		  response.setContentType("text/html;charset=utf-8"); 
		  PrintWriter out = response.getWriter(); 
		  out.println("<HTML>"); 
		  out.println(" <HEAD><TITLE>用户列表</TITLE></HEAD>"); 
		  out.println(" <BODY>"); 
		  out.print(" <h3>在线用户列表</h3>"); 
		  
		  int size = online == null ? 0 : online.size(); 
		  for (int i = 0; i < size; i++) { 
		   if(i > 0){ 
		    out.println("<br/>"); 
		   } 
		   out.println(i + 1 + "." + online.get(i)); 
		  } 
		    
		  out.println(" </BODY>"); 
		  out.println("</HTML>"); 
		  out.flush(); 
		  out.close(); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
