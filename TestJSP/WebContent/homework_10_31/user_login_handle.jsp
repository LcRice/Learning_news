<%@page import="java.sql.ResultSet"%>
<%@page import="com.neuedu.dbutil.DBManager"%>
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
	request.setCharacterEncoding("utf-8");
	
	
	DBManager dbManager = DBManager.getInstance();
	
	String sql = "select * from user where username = ? and password = ?";
	
	ResultSet rs = dbManager.execQuery(sql, username, password);
	
	if(rs.next()){   
		int score = rs.getInt(4);
		
		String photo = rs.getString(5);
		
		User user = new User();
		
		user.setUserid(rs.getInt(1));
		user.setUsername(username);
		user.setPassword(password);
		user.setScore(score);
		user.setPhoto(photo);
		user.setGender(rs.getString(6));
		user.setJob(rs.getString(7));
		user.setInterest(rs.getString(8));
		user.setRegtime(rs.getDate(9));

		/* session.setAttribute("username", username);
		session.setAttribute("score", score);
		session.setAttribute("photo", photo); */
		session.setAttribute("user", user);

		if(application.getAttribute("onlineCount")==null){  
			application.setAttribute("onlineCount", 1);
		}else{
			application.setAttribute("onlineCount", (Integer)application.getAttribute("onlineCount")+1);
		}
		
		response.sendRedirect("index.jsp");
		
	}else{
		out.println("<script> alert('用戶名密碼錯誤，請重新登陸');history.back();</script>");
	}
	
	dbManager.closeConnection();
	%>

</body>
</html>