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
	Cookie cookies = new Cookie("username","value");

	cookies.setMaxAge(0);
	
	response.addCookie(cookies);
	
	out.print("cookie删除成功！"+"<br/>");
%>

<a href = "cookie_query.jsp">转</a>
</body>
</html>