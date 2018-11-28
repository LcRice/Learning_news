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
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		String col = request.getParameter("col");

		int beginYear = 0;
		if (!"".equals(begin) && begin != null) {
			try {
				beginYear = Integer.parseInt(begin);
				//out.print(count);
			} catch (Exception e) {
				out.print("输入不为整数，请<a href='draw_year.jsp'>重新输入</a>");
			}
		} else {
			out.print("输入为空，请<a href='draw_year.jsp'>重新输入</a>");
		}

		int endYear = 0;
		if (!"".equals(end) && end != null) {
			try {
				endYear = Integer.parseInt(end);
				//out.print(count);
			} catch (Exception e) {
				out.print("输入不为整数，请<a href='draw_year.jsp'>重新输入</a>");
			}
		} else {
			out.print("输入为空，请<a href='draw_year.jsp'>重新输入</a>");
		}

		int count = 1;
		if (!"".equals(col) && col != null) {
			try {
				count = Integer.parseInt(col);
				//out.print(count);
			} catch (Exception e) {
				out.print("输入不为整数，请<a href='draw_year.jsp'>重新输入</a>");
			}
		} else {
			out.print("输入为空，请<a href='draw_year.jsp'>重新输入</a>");
		}
	%>


	<%!boolean isLeapYear(int year) {
		if (year % 4 == 0 || (year % 4 == 0 && year % 100 != 0)) {
			return true;
		} else {
			return false;
		}
	}%>

	<form action="draw_year.jsp" method="post">
		<center>
			从<input type="text" name="begin" value="<%=beginYear%>">年到 <input
				type="text" name="end" value="<%=endYear%>">年<br> 每行显示<input
				type="text" name="col" value="<%=count%>">列数据<br /> <input
				type="submit" value="提交" style="magin-right: 50px"> <input
				type="reset" value="重置">
		</center>
	</form>

	<br />
	<center>
		<table border="1">
			<%
				int row = 0;
				if ((endYear - beginYear) % count == 0) {
					row = (endYear - beginYear) / count;
				} else {
					row = ((endYear - beginYear) / count) + 1;
				}
				for (int j = 0; j < row; j++) {
			%>
			<tr bgcolor=<%=j % 2 == 0 ? "#66FF66" : "#66FFFF"%>>
				<%
					for (int i = 0; i < count; i++) {
				%>
				<td width='50' height='50' style="text-align: center">
					<%
						if (beginYear < endYear) {
									if (isLeapYear(beginYear)) {
										out.print("<font color = 'red' size = '3'><b>" + beginYear + "</b></font>");
									} else {
										out.print(beginYear);
									}
									beginYear++;
								}
					%>
				</td>
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