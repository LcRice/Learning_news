package com.neuedu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.neuedu.util.StringUtil;

/**
 * Servlet implementation class UploalMultiServlet
 */
public class UploalMultiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象
		PrintWriter out = response.getWriter();

			// 步骤1---创建上传组件---实例化SmartUpload对象
			SmartUpload smartUpload = new SmartUpload("utf-8");

			// 步骤2---初始化上传组件---调用initialize()方法
			smartUpload.initialize(getServletConfig(), request, response);
			// smartUpload.setDeniedFilesList("html");//不允许的文件类型

			// 步骤3---上传文件到服务器的临时内存中---调用upload()方法
			try {
				smartUpload.upload();
			} catch (SmartUploadException e1) {
				e1.printStackTrace();
			}

			// 步骤5---准备上传文件的存储路径和文件名---保证文件名唯一
			String serverFilePath = "E:/报告和实验/东软实习/1/upload";

			File dir = new File(serverFilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			boolean flag = true;

			for (int i = 0; i < smartUpload.getFiles().getCount(); i++) {

				// 步骤4---提取上传文件
				SmartFile smartFile = smartUpload.getFiles().getFile(i);
				// 检查是否为空
				if (smartFile.isMissing()) {
					out.print("<script>alert('server : 必须选择文件');history.back();</script>");
				}

				int MaxFileSize = 100;

				if (smartFile.getSize() > MaxFileSize * 1024) {
					out.print("<script>alert('第" + (i + 1) + "个文件大小不能超过" + MaxFileSize + "k');</script>");
					flag = false;
					continue;
				}

				String AllowedFile = "jpg,txt";

				if (!AllowedFile.contains(smartFile.getFileExt())) {
					out.println("<script>alert('第" + (i + 1) + "个文件类型必须是" + AllowedFile + "');</script>");
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

			}
			if(flag){
				out.println("<script>alert('全部文件上传成功');</script>");
			}else{
				out.println("<script>alert('部分文件上传成功');</script>");
			}
			
			out.println("<script>location='upload_multi.jsp'</script>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
