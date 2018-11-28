<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.neuedu.dbutil.DBManager"%>
<%@page import="com.neuedu.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user_register_handle</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String username = "";
		String password = "";
		String photo = "";
		String gender = "";
		String job = "";
		String[] interests = null;
		String interest = "";
		try {
			username = request.getParameter("username");
			password = request.getParameter("password");
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
			out.print("填寫为空，请<a href='user_register.jsp'>重新输入</a>");
		}
		DBManager dbManager = DBManager.getInstance();
		
		String sql = "select * from user where username = ?";
		ResultSet rs = dbManager.execQuery(sql, username);
		if(rs.next()){
			out.print("<script>alert('server:用户已存在！');history.back();</script>");
			dbManager.closeConnection();
			return;
		}
	%>

	<%

		sql = "insert into user values(null,?,?,10,?,?,?,?,?)";

		if (dbManager.execUpdate(sql, username, password, photo, gender, job, interest, new Date())) {
			sql = "select * from user where username = ? and password = ?";

			rs = dbManager.execQuery(sql, username, password);

			if (rs.next()) {

				User user = new User();
				user.setUserid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setScore(rs.getInt(4));
				user.setPhoto(rs.getString(5));
				user.setGender(rs.getString(6));
				user.setJob(rs.getString(7));
				user.setInterest(rs.getString(8));
				user.setRegtime(rs.getDate(9));

				session.setAttribute("user", user);

				if (application.getAttribute("onlineCount") == null) {
					application.setAttribute("onlineCount", 1);
				} else {
					application.setAttribute("onlineCount", (Integer) application.getAttribute("onlineCount") + 1);
				}

				response.sendRedirect("user_register_result.jsp");
			}
		} else {
			out.println("<script> alert('用戶名密碼錯誤，請重新登陸');history.back();</script>");
		}

		dbManager.closeConnection();
	%>
</body>
</html>