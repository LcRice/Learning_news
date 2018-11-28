<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <h3> --------协议信息------------ </h3>

 Protocol = <%=request.getProtocol() %>  <br/>

 Scheme = <%=request.getScheme() %>  <br/>


 <h3> --------服务器信息------------ </h3>

 LocalName = <%=request.getLocalName() %>  <br/>

 LocalAddr = <%=request.getLocalAddr() %>  <br/>

 LocalPort = <%=request.getLocalPort() %>  <br/>


 ServerName = <%=request.getServerName() %>  <br/>

 ServerPort = <%=request.getServerPort() %>  <br/>


 <h3> --------客户端信息------------ </h3>

 RemoteHost = <%=request.getRemoteHost() %>  <br/>
 
 RemoteAddr = <%=request.getRemoteAddr() %>  <br/>

 RemotePort = <%=request.getRemotePort() %>  <br/>


 <h3> --------请求路径URL信息------------ </h3>

 ContextPath = <%=request.getContextPath() %>  <font color="red" size="5"><b>常用</b></font> <br/>

 ServletPath = <%=request.getServletPath() %>  <br/>

 RequestURI = <%=request.getRequestURI() %>  <br/>
 
 RequestURL = <%=request.getRequestURL() %>  <br/>

 QueryString = <%=request.getQueryString() %>  <br/>

 ContextPath = <%=new java.io.File(application.getRealPath(request.getRequestURI())).getParent()%>

 RealPath = <%=this.getServletContext().getRealPath("/image/photo") %>  <font color="red" size="5"><b>常用</b></font> <br/>
 <%
 	String name = this.getServletContext().getRealPath("/image/photo");
 	name = name.replaceAll("\\\\", "/");
 	System.out.print("name="+name);
 %>

 <h3> --------请求头信息------------ </h3>
  
 Method = <%=request.getMethod() %>  <font color="red" size="5"><b>常用</b></font> <br/>
  
 ContentLength = <%=request.getContentLength() %>  <br/>
 
 ContentType = <%=request.getContentType() %>  <br/>
 
 <h3> 请求头名称=请求头内容 </h3>
 
 <%
	 Enumeration headers = request.getHeaderNames();
	
	 while(headers.hasMoreElements()){
		 
		 String headerName = (String)headers.nextElement();
		 
		 out.println(headerName + " = " + request.getHeader(headerName) + "<br/>");
		 
	 }
		 
 %>
  
</body>
</html>