<%@page import="com.neuedu.entity.User"%>
<%@page import="com.neuedu.dao.UserDAO"%>
<%@page import="com.neuedu.dao.impl.UserDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String username = "";
		String password = "";
		try {
			username = request.getParameter("username");
			password = request.getParameter("password");
		} catch (Exception e) {
			out.print("密码为空，请<a href='user_login.jsp'>重新输入</a>");
		}
	%>

	<%
		UserDAOImpl userDAO = new UserDAOImpl();
		User user = userDAO.findUser(username, password);
		if ((userDAO.findUser(username, password)) != null) {
			out.print("登录成功,欢迎   " + userDAO.findUser(username, password).getUsername()+"：<br/>");
			out.print("<a href = 'user_query.jsp'>查询用户</a>");
		} else {
			out.print("登录失败，请<a href='user_login.jsp'>重新输入</a>");
		} 
	%>

</body>
</html>