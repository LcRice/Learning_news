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
		String s = request.getParameter("postive");
		//out.print(s);
		int count = 0;
		String username = request.getParameter("count");
		if ( !"".equals(username) && username != null) {
			try {
				count = Integer.parseInt(username);
				//out.print(count);
			} catch (Exception e) {
				out.print("输入不为整数，请<a href='draw_line_input.jsp'>重新输入</a>");
			}
		}else{
			out.print("输入为空，请<a href='draw_line_input.jsp'>重新输入</a>");
		}
	%>

	<%
		if ("postive".equals(s)) {
			for (int i = 0; i < count; i++) {
				if (i % 2 == 0) {
					out.print("<hr color = 'red' width = '" + i + "%'></hr>");
				} else if (i % 3 == 0) {
					out.print("<hr color = 'green' width = '" + i + "%'></hr>");
				} else {
					out.print("<hr color = 'blue' width = '" + i + "%'></hr>");
				}
			}
		} else {
			for (int i = count; i > 0; i--) {
				if (i % 2 == 0) {
					out.print("<hr color = 'red' width = '" + i + "%'></hr>");
				} else if (i % 3 == 0) {
					out.print("<hr color = 'green' width = '" + i + "%'></hr>");
				} else {
					out.print("<hr color = 'blue' width = '" + i + "%'></hr>");
				}
			}
		}
	%>
</body>
</html>