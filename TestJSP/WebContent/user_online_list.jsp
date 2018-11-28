<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
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
		List<String> online = (List<String>) getServletContext().getAttribute("online");

		response.setContentType("text/html;charset=utf-8");
	%>
	<h3>在线用户列表</h3>
	<%
		int size = online == null ? 0 : online.size();
		for (int i = 0; i < size; i++) {
			if (i > 0) {
				out.println("<br/>");
			}
			out.println(i + 1 + "." + online.get(i));
		}
	%>
	<%
		out.flush();
		out.close();
	%>

</body>
</html>