package com.neuedu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.neuedu.util.StringUtil;

public class UploadSingleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象
		PrintWriter out = response.getWriter();

		try {
			// 步骤1---创建上传组件---实例化SmartUpload对象
			SmartUpload smartUpload = new SmartUpload("utf-8");

			// 步骤2---初始化上传组件---调用initialize()方法
			smartUpload.initialize(getServletConfig(), request, response);
			
			//检查2---检查文件大小
			smartUpload.setMaxFileSize(100*1024);
			
			//检查3---检查文件格式
			smartUpload.setAllowedFilesList("jpg,txt");//允许的文件类型
		
			//smartUpload.setDeniedFilesList("html");//不允许的文件类型
			
			
			// 步骤3---上传文件到服务器的临时内存中---调用upload()方法
			smartUpload.upload();

			// 步骤4---提取上传文件
			SmartFile smartFile = smartUpload.getFiles().getFile(0);

			String username = smartFile.getFileName();
			System.out.println(username);
			//检查是否为空
			if(smartFile.isMissing()){
				out.print("<script>alert('server : 必须选择文件');history.back();</script>");
			}
			
			// 步骤5---准备上传文件的存储路径和文件名---保证文件名唯一
			String serverFilePath = "E:/报告和实验/东软实习/1/upload";

			File dir = new File(serverFilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			String serverFilename = StringUtil.convertFilename(smartFile.getFileName());

			// 步骤6---保存上传文件
			smartFile.saveAs(serverFilePath + "/" + serverFilename);

			out.print("<script>alert('文件上传成功');location='upload_single.jsp';</script>");
		} catch (SmartUploadException e) {
			e.printStackTrace();
			out.print("<script>alert('文件上传失败');location='upload_single.jsp';</script>");
		}catch (SecurityException e) {
			e.printStackTrace();
			out.print("<script>alert('文件大小不能超过100k，文件类型必须为jpg类型');location='upload_single.jsp';</script>");
		}/*catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("<script>alert('文件类型不能为html类型');location='upload_single.jsp';</script>");
		}*/

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
