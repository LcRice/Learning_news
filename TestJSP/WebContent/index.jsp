<%@page import="com.neuedu.util.CookieUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.neuedu.dao.impl.UserDAOImpl"%>
<%@page import="com.neuedu.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
<style type="text/css">
body {
	margin: 0 0 0 0;
}
</style>
</head>
<body>

<c:if test="${sessionScope.user==null }">
	<%
			String userInfo = CookieUtil.findCookie(request, "userInfo");    //user1#1
			if(userInfo!=null){
				
				String[] userInfos = userInfo.split("#");
				
				String username = userInfos[0];
				String password = userInfos[1];
				
				response.sendRedirect("UserLoginServlet?username=" + username + "&password=" + password);
				return;
			}
	%>
	
	<!-- <h2>
		您尚未登陸，請先<a href="user_login.jsp">登陸</a>
	</h2> -->
	
	<script type="text/javascript">
		alert("您尚未登陸，請先登陸");				//彈框
		location.href = "user_login.jsp";	//跳轉  href是默認屬性 
		history.back();						//回退
	</script>
</c:if>
<c:if test="${sessionScope.user!=null }">
	<%
		/* String username = (String) session.getAttribute("username");
		int score = (Integer) session.getAttribute("score");
		String photo = (String) session.getAttribute("photo");*/
		int onlineCount = (Integer) application.getAttribute("onlineCount");
		User user = (User) session.getAttribute("user");
	%>
	

	<div
		style="width: 100%; height: 50px; background: linear-gradient(to right, #00FFFF 50%, #00FF00)">
		<div>
			<div
				style="float: right; text-align: center; margin-top: 13px; margin-right: 10px">
				<a href="UserLogoutServlet">注銷</a>
			</div>
			<div
				style="float: right; text-align: center; margin-top: 13px; margin-right: 10px">
				歡迎用戶【${sessionScope.user.username}】，您當前積分為【${sessionScope.user.score}】
			</div>
			<div style="float: right; margin-right: 10px; margin-top: 10px">
				<img alt="" src="image/photo/${sessionScope.user.photo }" width="30px" height="30px"/>
			</div>
		</div>
	</div>

<a href="user_query.jsp">用戶查詢</a><br/>
<a href="news_add.jsp">添加新闻</a><br/>
<a href="NewsQueryServlet?currentPage=1">新闻列表查询</a><br/>
<a href="user_online_list.jsp">在线用户</a>

	<div style="position: absolute; bottom: 10px; right: 10px;">
		您是第${applicationScope.onlineCount }位訪客
	</div>
</c:if>
</body>
</html>