<%@page import="java.util.LinkedList"%>
<%@page import="com.neuedu.util.CookieUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>浏览前五</title>
</head>
<body>
	<%
		String bookname = request.getParameter("bookname");

		String bookInfo = CookieUtil.findCookie(request, "bookInfo");

		LinkedList<String> list = new LinkedList<String>();

		if (bookInfo == null) {
			list.add(bookname);
		} else {

			String[] bookInfos = bookInfo.split(":");

			for (String s : bookInfos) {
				list.add(s);
			}

			if (list.size() == 5) {

				if (list.contains(bookname)) {
					list.remove(bookname);
					list.add(0, bookname);
					//list.addFirst(bookname);
				} else {
					list.remove(list.size() - 1);
					list.add(0, bookname);
				}
			} else {
				if (list.contains(bookname)) {
					list.remove(bookname);
					list.add(0, bookname);
				} else {
					list.add(0, bookname);
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		for (String name : list) {
	%>
	<a href="#"><%=name%></a>
	<br />
	<%
		sb.append(name + ":");
		}

		bookInfo = sb.substring(0, sb.length() - 1);

		CookieUtil.addCookie(response, "bookInfo", bookInfo, 3);
	%>

	<br />

	<a href="book_list.jsp">返回</a>

</body>
</html>