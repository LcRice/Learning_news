package com.neuedu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.neuedu.entity.Attachment;
import com.neuedu.entity.News;
import com.neuedu.entity.NewsType;
import com.neuedu.entity.User;
import com.neuedu.service.NewsService;
import com.neuedu.service.impl.NewsServiceImpl;
import com.neuedu.util.StringUtil;

public class NewsAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();

		// 获取session对象---需要session对象时加此句
		HttpSession session = request.getSession();

		/*
		 * //从配置文件中读取字符编码 String charSet =
		 * this.getServletContext().getInitParameter("charSet");
		 * 
		 * //设置字符编码 //request.setCharacterEncoding(charSet);
		 */

		// ****************文件上传开始********************************

		// 从配置文件中读取文件大小和允许的类型
		int maxFileSize = Integer.parseInt(this.getInitParameter("maxFileSize"));
		String deniedFileList = this.getInitParameter("deniedFileList");

		// 初始化附件列表*********
		List<Attachment> attachments = new ArrayList<>();

		// 步骤1---创建上传组件---实例化SmartUpload对象
		SmartUpload smartUpload = new SmartUpload("utf-8");

		// 步骤2---初始化上传组件---调用initialize()方法
		smartUpload.initialize(getServletConfig(), request, response);

		/*// 检查2---文件大小
		smartUpload.setMaxFileSize(maxFileSize * 1024 * 1024);

		// 检查3---文件类型
		try {
			smartUpload.setDeniedFilesList(deniedFileList);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

		// 步骤3---上传文件到服务器的临时内存中---调用upload()方法
		try {
			smartUpload.upload();
		} catch (SmartUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 下载的扣除积分*********
		String[] downloadscores = smartUpload.getRequest().getParameterValues("downloadscore");

		boolean flag = true;

		// 步骤4---提取每一个上传文件 *********
		for (int i = 0; i < smartUpload.getFiles().getCount(); i++) {

			SmartFile smartFile = smartUpload.getFiles().getFile(i);

			// 检查1---文件非空
			if (!smartFile.isMissing()) {

				// 步骤5---准备上传文件的存储路径和文件名---保证文件名唯一
				String serverFilePath = this.getServletContext().getRealPath("/attachment");

				// 自动创建目录
				File dir = new File(serverFilePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				if (smartFile.getSize() > maxFileSize * 1024 * 1024) {
					out.print("<script>alert('第" + (i + 1) + "个文件大小不能超过" + maxFileSize * 1024 * 1024 + "k');</script>");
					flag = false;
					continue;
				}

				if (deniedFileList.contains(smartFile.getFileExt())) {
					out.println("<script>alert('第" + (i + 1) + "个文件类型不能是" + deniedFileList + "');</script>");
					flag = false;
					continue;
				}

				String serverFilename = StringUtil.convertFilename(smartFile.getFileName());

				// 步骤6---保存上传文件
				try {
					smartFile.saveAs(serverFilePath + "/" + serverFilename);
				} catch (SmartUploadException e) {
					e.printStackTrace();
					out.println("<script>alert('第" + (i + 1) + "个文件上传失败');</script>");
				}

				// 创建并填充附件对象***************
				Attachment attachment = new Attachment();
				attachment.setAttachment(serverFilename); // 更新attachment变量的值为新的文件名
				attachment.setTruename(smartFile.getFileName()); // 原始文件名
				attachment.setDownload(Integer.parseInt(downloadscores[i]));// 下载的扣除积分
				attachment.setDownloadcount(0);

				// 添加到附件列表中***************
				attachments.add(attachment);
			}

		}
		if (flag) {
			out.println("<script>alert('全部文件上传成功');</script>");
		} else {
			out.println("<script>alert('部分文件上传成功');</script>");
		}

		// ****************文件上传结束********************************

		// 接收数据
		int typeid = Integer.parseInt(smartUpload.getRequest().getParameter("typeid"));
		String title = smartUpload.getRequest().getParameter("title");
		String content = smartUpload.getRequest().getParameter("content");

		// 读取添加新闻成功的加分
		int addNewsScore = Integer.parseInt(this.getInitParameter("addNewsScore"));

		// 从session属性范围中获取当前用户对象
		User user = (User) session.getAttribute("user");

		// 创建并填充news实体类
		News news = new News();
		news.setTitle(title);
		news.setContent(content);
		news.setPubtime(new Date());

		// news.setTypeid(typeid);

		// news对象中组合了newstype对象
		NewsType newsType = new NewsType();
		newsType.setTypeid(typeid);
		news.setNewstype(newsType);

		// news.setUserid(user.getUserid());
		news.setUser(user);

		// 填充附件列表********
		news.setAttachment(attachments);

		/*
		 * news.setAttachment(attachment); news.setTruename(truename);
		 * news.setDownloadscore(downloadscore);
		 * 
		 * //下载次数默认为0 news.setDownloadcount(0);
		 */

		// 组合业务对象
		NewsService newsService = new NewsServiceImpl();

		// 调用业务方法---添加新闻
		if (newsService.addNews(news, user, addNewsScore)) {
			out.println(
					"<script>if(confirm('新闻添加成功，是否继续添加')){location='news_add.jsp'}else{location='news_query.jsp'}</script>");
		} else {
			out.println("<script>alert('新闻添加失败，请重新输入');history.back()</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}