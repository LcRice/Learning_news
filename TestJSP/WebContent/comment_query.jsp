<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comment</title>
<script>
	function checkWordCount(){
		var content = document.getElementById("content");
		var restWordCount = document.getElementById("restWordCount");
		
		if(content.value.length>20){
			content.value = content.value.substring(0, content.value.length-1);
		}
		
		restWordCount.innerText = 20 - content.value.length;
	}
	
	var stars = [0,0,0,0,0];
	
	function enter(index){
	
		 for(var i=1; i<=5; i++){
			
			var star = document.getElementById("star" + i);
			if(i<=index){
				star.src ="image/star_yellow.gif";
			}else{
				star.src ="image/star_white.gif";
			}
		} 
	}
	
	function leave(index){
		
		for(var i=1; i<=5; i++){
			
			var star = document.getElementById("star" + i);
			
			if(stars[i-1]==0){
				star.src ="image/star_white.gif";
			}
		}	
	}
	
	function select(index){
		
		 for(var i=1; i<=5; i++){
				
			var star = document.getElementById("star" + i);
			
			if(i<=index){
				star.src ="image/star_yellow.gif";
				stars[i-1] = 1;  
			}else{
				star.src ="image/star_white.gif";
				stars[i-1] = 0;
			}
			
			var score = document.getElementById("score");
			
			score.value = index;
			
		} 
	}
	
</script>
</head>
<body>
	<c:if test="${requestScope.newsQuery==null }">
		<%
			response.sendRedirect("CommentQueryServlet?attachment=''");
		%>
	</c:if>
	<c:if test="${requestScope.newsQuery!=null }">
		<h2>新闻报告</h2>
		<hr />
		<table>
			<tr>
				<td align="center"><img alt=""
					src="image/photo/${requestScope.userQuery.photo }" width="40"
					height="40"><br /> ${requestScope.userQuery.username }</td>
				<td align="center">${requestScope.newsQuery.title }&nbsp;&nbsp;&nbsp;${requestScope.newsQuery.pubtime }</td>
			</tr>
			<tr>
				<th align="center">新闻内容</th>
				<td align="center">${requestScope.newsQuery.content }</td>
			</tr>
		</table>
	</c:if>

<br/>
<hr>


	<c:if test="${requestScope.commentList!=null }">

		<table border="1" cellpadding="1" cellspacing="0">

			<tr>
				<th>用户</th>
				<th>评论内容</th>
				<th>发表时间</th>
				<th>评分</th>
			</tr>

			<c:set var="row" value="0" />

			<c:forEach var="comment" items="${requestScope.commentList}">

				<tr bgcolor="${ row==0 ? '#d0d0d0' : '#ffffff' }">

					<td align="center"><img
						src="image/photo/${comment.user.photo}" width="50" height="50"/> <br />
						${comment.user.username}</td>

					<td>${comment.content}</td>

					<td>${comment.pubtime}</td>

					<%--  <td>${comment.score}鍒�</td>
				 --%>
					<td><c:forEach var="i" begin="1" end="${comment.score}">
							<img src="image/star_yellow.gif" />
						</c:forEach> <c:forEach var="i" begin="1" end="${5 - comment.score}">
							<img src="image/star_white.gif" />
						</c:forEach></td>
				</tr>

				<c:set var="row" value="${1 - row}" />

			</c:forEach>

		</table>

	</c:if>
<br/>
	<c:if test="${sessionScope.user!=null }">

		<%-- <form action="CommentAddServlet?newsid=${requestScope.newsid}" method="post"> --%>

		<form action="CommentAddServlet" method="post">

			评论内容
			<textarea name="content" rows="5" cols="30" id="content" onkeyup="checkWordCount()" class="ckeditor"></textarea><br/>
			<br /> 
			还能输入<span id="restWordCount">20</span>个字
			<br/>
			评论打分<!-- <input type="radio" name="score" value="5" checked />5分
			<input type="radio" name="score" value="4" />4分 <input type="radio"
				name="score" value="3" />3分<input type="radio" name="score"
				value="2" />2分 <input type="radio" name="score" value="1" />1分 -->
  				<c:forEach var="i" begin="1" end="5">
				 <img src="image/star_white.gif" id="star${i}" 
				 		onmousemove="enter(${i})" 
				 		onmouseout="leave(${i})"
				 		onclick="select(${i})"/>
			  </c:forEach>
			  
			  <!-- 閫氳繃闅愯棌鍩熶紶閫掕瘎鍒嗗€� -->
			  <input type="hidden" name="score" id="score" value="1"/>
 <br />
			<input type="hidden" name="newsid" value="${requestScope.newsid}" />

			<input type="submit" value="提交" />
		</form>
	</c:if>

	<c:if test="${sessionScope.user==null }">

		<%
				session.setAttribute("prevURL", "CommentQueryServlet?newsid=" + request.getAttribute("newsid"));
		%>
	
	您还未登录，请<a href="user_login.jsp">登录</a>
	</c:if>
</body>
</html>