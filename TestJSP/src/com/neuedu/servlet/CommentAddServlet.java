package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Comment;
import com.neuedu.entity.News;
import com.neuedu.entity.User;
import com.neuedu.service.NewsService;
import com.neuedu.service.impl.NewsServiceImpl;

public class CommentAddServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");	

		//获取out输出对象
		PrintWriter out = response.getWriter();		

		//获取session对象
	    HttpSession session = request.getSession();	

	   /*	//读取字符编码
	   	String charSet = this.getServletContext().getInitParameter("charSet");
	   	
	   	//设置字符编码
	  	request.setCharacterEncoding(charSet); */
	  
	  	//接收数据
	  	//String content = request.getParameter("content");
	  	String content = request.getParameter("content");
	  	//String content = CKEditorUtil.getTextFromHtml((String)request.getAttribute("content"));
	  	
  		int score = Integer.parseInt(request.getParameter("score"));
  		int newsid = Integer.parseInt(request.getParameter("newsid"));
  		
  		//读取评论添加成功后的加分
  		int addCommentScore = Integer.parseInt(this.getInitParameter("addCommentScore"));
  		
  		//创建并填充实体类
  		Comment comment = new Comment();

  		comment.setContent(content);
  		comment.setScore(score);
  		comment.setPubtime(new Date());

  		//填充news对象
  		News news = new News();
  		news.setNewsid(newsid);
  		comment.setNews(news);
  		
  		//从session属性范围中取出当前登录的用户
  		User user = (User)session.getAttribute("user");
  		
  		//填充user对象
  		comment.setUser(user);
  		
  		//组合业务层对象
  		NewsService newsService = new NewsServiceImpl();
  		
  		//调用业务层方法
  		if(newsService.addComment(comment, user, addCommentScore)){
  			out.println("<script>alert('新闻评论添加成功');location='CommentQueryServlet?newsid=" + newsid + "'</script>");   
  		}else{
  			out.println("<script>alert('新闻评论添加失败');history.back()</script>");			
  		}
	  		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}