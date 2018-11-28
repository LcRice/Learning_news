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
		int row = Integer.parseInt(request.getParameter("row"));
		int line = Integer.parseInt(request.getParameter("line"));
	%>
	
	<center>
		<table border="1">
			<%
				int count = 1;
				for (int j = 0; j < row; j++) {
			%>
			<tr bgcolor=<%=j % 2 == 0 ? "#66FF66" : "#66FFFF"%>>
				<%
					for (int i = 0; i < line; i++) {
				%>
				<td width='50' height='50' style="text-align: center"><%=count++%></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
	</center>
</body>
</html>