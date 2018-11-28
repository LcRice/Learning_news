<%@page import="javax.sound.midi.SysexMessage"%>
<%@page import="com.neuedu.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
<style type="text/css">
.table_th{
	width: 200px;
	height: 50px;
	text-align: center;
}
.table_td{
	width: 400px;
	height: 50px;
	text-align: center;
}
</style>
</head>
<body>
	<%
		User user =(User)session.getAttribute("user");
		
		String photo = user.getPhoto();
		String gender = user.getGender();
		String job = user.getJob();
	%>
	<h2>个人信息</h2>
	<form action="UserUpdateServlet_Self" method="post">
		<table border="1" cellpadding="1" cellspacing="0">
			<tr>
				<th class="table_th">用户姓名</th>
				<td class="table_td">
						<input type="text" name="username" id="username" value="<%=user.getUsername() %>" >
				</td>
			</tr>
			<tr>
				<th class="table_th">用户头像</th>
				<td class="table_td">
				<input type="radio" name="photo" value="1.gif" <%="1.gif".equals(photo)?"checked":"" %>>
					<img alt="" src="image/photo/1.gif">
				<input type="radio" name="photo" value="2.gif" <%="2.gif".equals(photo)?"checked":"" %>>
					<img alt="" src="image/photo/2.gif"> 
				<input type="radio" name="photo" value="3.gif" <%="3.gif".equals(photo)?"checked":"" %>>
					<img alt="" src="image/photo/3.gif"> 
				<input type="radio" name="photo" value="4.gif" <%="4.gif".equals(photo)?"checked":"" %>>
					<img alt="" src="image/photo/4.gif"> 
				<input type="radio" name="photo" value="5.gif" <%="5.gif".equals(photo)?"checked":"" %>>
					<img alt="" src="image/photo/5.gif">
				</td>
			</tr>
			<tr>
				<th class="table_th">用户性别</th>
				<td class="table_td"><input type="radio" name="gender" value="男" <%="男".equals(gender) ? "checked":"" %> />男 <input type="radio" name="gender" value="女" <%="女".equals(gender) ? "checked":"" %>/>女</td>
			</tr>
			<tr>
				<th class="table_th">用户工作</th>
				<td class="table_td">
					<select name="job">
							<option value="程序员" <%="程序员".equals(job) ? "selected":"" %>>程序员</option>
							<option value="美工" <%="美工".equals(job) ? "selected":"" %>>美工</option>
							<option value="项目经理" <%="项目经理".equals(job) ? "selected":"" %>>项目经理</option>
					</select></td>
			</tr>
			<%
				String interest = user.getInterest();
				System.out.print(interest);
				String[] interests = {""};
				if(interest!=null){
					interests = interest.split(" ");
				}
				for(int i = 0;i<interests.length;i++){
					System.out.print(interests[i]);
				}
			%>
			<tr>
				<th class="table_th">兴趣爱好</th>
				<td class="table_td">
					<input type="checkbox" name="interest" value="唱歌" 
							<%
								for(int i = 0;i<interests.length;i++){
									if("唱歌".equals(interests[i])){
										%>
										checked="checked"
										<%
									}
								} 
							%>
					/>唱歌 
					<input type="checkbox" name="interest" value="跳舞" 
						<%
								for(int i = 0;i<interests.length;i++){
									if("跳舞".equals(interests[i])){
										%>
										checked="checked"
										<%
									}
								} 
							%>
					/>跳舞 
					<input type="checkbox" name="interest" value="跑步" 
						<%
								for(int i = 0;i<interests.length;i++){
									if("跑步".equals(interests[i])){
										%>
										checked="checked"
										<%
									}
								} 
							%>
					/>跑步 
					<input type="checkbox" name="interest" value="游泳" 
						<%
								for(int i = 0;i<interests.length;i++){
									if("游泳".equals(interests[i])){
										%>
										checked="checked"
										<%
									}
								} 
							%>
					/>游泳
				</td>
			</tr>
			<tr>
				<td colspan="2" class="table_td"><input type = "submit" value="修改" onclick="return confirm('是否确定修改用户信息');"></td>
			</tr>
		</table>
	</form>
		<a href = "index.jsp">返回主頁</a>
</body>
</html>