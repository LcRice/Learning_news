<%@page import="com.neuedu.util.StringUtil"%>
<%@page import="java.util.List"%>

<%@page import="com.neuedu.dbutil.DBManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.neuedu.dao.impl.UserDAOImpl"%>
<%@page import="com.neuedu.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Query</title>

<script type="text/javascript">
//指定时间显示
function showTimeRange() {
	var regtime = document.getElementById("regtime");
	var timerange = document.getElementById("timerange");
	
	if(regtime.value == "全部时间"){
		timerange.style.visibility = "hidden";
	}else{
		timerange.style.visibility = "visible";
	}
	
}

//全选
function selectAll() {
	var all = document.getElementById("all");
	var userids = document.getElementsByName("userids");
	
	for(var i = 0;i<userids.length;i++){
		userids[i].checked = all.checked; //checked 选中为true  没选中为false;
	}
}

//反选
function selectReverse(){
	var userids = document.getElementsByName("userids");
	
	for(var i = 0;i<userids.length;i++){
		userids[i].checked = !userids[i].checked;
	}
	
	checkSelect();
}

//全选框判断
function checkSelect() {
	var all = document.getElementById("all");
	var userids = document.getElementsByName("userids");
	
	var flag = true;
	for(var i = 0;i<userids.length;i++){
		if(!userids[i].checked){
			flag = false;
			break;
		}
	}
	
	all.checked = flag;
}

//至少选一个用户
function checkCount(){
	var userids = document.getElementsByName("userids");
	
	var flag = false;
	for(var i = 0;i<userids.length;i++){
		if(userids[i].checked){
			flag = true;
			break;
		}
	}
	
	if(!flag){
		alert("至少选择一个用户");
		return false;
	}
	
	if(flag){
		return confirm("是否确认删除用户？");
	}
	
}
</script>

<script src="js/My97DatePicker/WdatePicker.js"></script>

<script src="js/laydate/laydate/laydate.js"></script>

</head>
<body>

<c:if test="${sessionScope.user==null }">
<%
	String URL = request.getRequestURI();
	String prevURL = URL.substring(URL.lastIndexOf("/")+1);
	session.setAttribute("prevURL", prevURL);
%>
<script type="text/javascript">
	alert("尚未登录，请先登录！");
	location="user_login.jsp";
</script>
</c:if>
<c:if test="${sessionScope.user!=null }">
	<c:if test="${requestScope.userPage==null }">
	<%
		 	response.sendRedirect("UserQueryServlet");
	%>
	</c:if>
		
	<c:if test="${requestScope.userPage!=null }">
	<form action="UserQueryServlet" method="post" >

		用户名<input type="text" name="username" value="${requestScope.username }" />
		性别<select name = "gender">
			<option value="" ${ ""==gender ? "selected":"" }>全部</option>
			<option value="男" ${ "男"==gender ? "selected":"" }>男</option>
			<option value="女" ${ "女"==gender ? "selected":"" }>女</option>
		</select>
		职业<select name = "job">
			<option value="" ${ ""==job ? "selected":"" }>全部</option>
			<option value="程序员" ${"程序员"==job ? "selected":""}>程序员</option>
			<option value="美工" ${"美工"==job ? "selected":"" }>美工</option>
			<option value="项目经理" ${"项目经理"==job ? "selected":"" }>项目经理</option>
		</select>
		 <input type="submit" value="查询" />

		<br/>
		注册时间<select name = "regtime" id="regtime" onchange="showTimeRange()">
				<option value="全部时间" ${"全部时间"==regtime ? "selected" : "" }>全部时间</option>
				<option value="指定时间" ${"指定时间"==regtime ? "selected" : "" }>指定时间</option>
			</select>
			
			<span id="timerange" style = "visibility:${ '全部时间'==regtime ? 'hidden' : 'visible' };">
			从<input type = "text" name = "begintime" id = "begintime" value = "${ requestScope.begintime}" readonly="readonly" class="Wdate" onfocus="WdatePicker()"/>
			到<input type = "text" name = "endtime" id = "endtime" value = "${ requestScope.endtime}" readonly="readonly" class="Wdate" onfocus="WdatePicker()"/>
			<%-- 从<input type = "text" name = "begintime" id = "begintime" value = "<%=begintime%>" class="laydate-icon" onfocus="laydate()"/>
			到<input type = "text" name = "endtime" id = "endtime" value = "<%=endtime%>" class="laydate-icon" onfocus="laydate()"/> --%>
			</span>
	</form>
	<br />
	<form action="UserDeleteBatchServlet" method = "post" onsubmit = "return checkCount()">
	<table border="1" cellpadding="1" cellspacing="0">

		<tr>
			<th>选择</th>
			<th>用户头像</th>
			<th>用户名</th>
			<th>积分</th>
			<th>性别</th>
			<th>职业</th>
			<th>注册时间</th>
			<th>操作</th>
		</tr>

		<c:set var="row" value="0"/>
		<c:forEach var="user" items="${requestScope.userPage }">
		
			<tr bgcolor='${ row == 0 ? "#d0d0d0" : "#ffffff"}'>
			
				<td><input type = "checkbox" name = "userids" value="${user.userid }"  onclick = "checkSelect()"></td>
			
				<td><img src="image/photo/${user.photo }" width="30px" height="30px"/></td>
			
				<td><a href="UserQueryServlet?userid=${user.userid }">${myfn:convertKeyword(user.username,username)}</a></td>
			
			 	<%-- <td>${user.username }</td> --%>
			 
				<td>${user.score }</td>
			
				<td>${user.gender }</td>
			
				<td>${user.job }</td>
				
				<%-- <td>${user.regtime }</td> --%>
			
				<td>${myfn:convertDatetime(user.regtime)}</td>
			
				<td><a href="UserDeleteServlet?userid=${user.userid }" onclick = "return confirm('是否删除用户');">删除</a></td>
			</tr>

			<c:set var="row" value="${1-row }" />
		</c:forEach>
		<tr>
			<td colspan="9" align="center">
				<c:if test="${ userPage.currentPage==1 }">
					首页&nbsp;&nbsp;&nbsp;上一页&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test="${userPage.currentPage!=1 }">
					<a href="NewsQueryServlet?currentPage=1">首页</a>&nbsp;&nbsp;&nbsp;
					<a href="NewsQueryServlet?currentPage=${userPage.currentPage-1 }">上一页</a>&nbsp;&nbsp;&nbsp;
				</c:if>
				
				<!-- 数字分页  -->
				<c:forEach var="i" begin="${userPage.begin }" end="${userPage.end}" step="1">
					<c:if test="${i==userPage.currentPage}">
						${i }
					</c:if>
					<c:if test="${i!=userPage.currentPage}">
						<a href="NewsQueryServlet?currentPage=${i }" }>
							${i }
						</a>
					</c:if>
				</c:forEach>
				
				
				<c:if test="${userPage.currentPage==userPage.totalPage }">
					下一页&nbsp;&nbsp;&nbsp;尾页
				</c:if>
				<c:if test="${userPage.currentPage!=newsPage.totalPage }">
					<a href="NewsQueryServlet?currentPage=${userPage.currentPage+1 }">下一页</a>&nbsp;&nbsp;&nbsp;
					<a href="NewsQueryServlet?currentPage=${userPage.totalPage}">尾页</a>
				</c:if>
				
				&nbsp;&nbsp;&nbsp;到
				<select name="currentPage" onchange="location='NewsQueryServlet?currentPage='+this.value">
					<c:forEach var="i" begin="1" end="${userPage.totalPage}" step="1">
						<option value="${i }" ${i==userPage.currentPage ? "selected" : "" }>${i }</option>
					</c:forEach>
				</select>
				页
			</td>
		</tr>
		
		<tr>
			<td colspan="9" align="center">
				共${userPage.totalCount } 条记录&nbsp;&nbsp;&nbsp;
				每页${userPage.pageSize } 条记录&nbsp;&nbsp;&nbsp;
				第【${userPage.currentPage }】页/共${userPage.totalPage }页
			</td>
		</tr>
		<tr>
			<td><input type = "checkbox" id = "all" onclick = "selectAll()">全选</td>
			<td colspan="6" align = "center"><input type = "submit" value = "批量删除"></td>
			<td><input type = "button" value = "反选" onclick = "selectReverse()"></td>
		</tr>

	</table>

</form>
	<a href = "index.jsp">返回主頁</a>
	</c:if>
</c:if>
</body>
</html>