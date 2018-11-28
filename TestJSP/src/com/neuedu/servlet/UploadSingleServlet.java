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
		// ���������������
		response.setContentType("text/html;charset=utf-8");

		// ��ȡout�������
		PrintWriter out = response.getWriter();

		try {
			// ����1---�����ϴ����---ʵ����SmartUpload����
			SmartUpload smartUpload = new SmartUpload("utf-8");

			// ����2---��ʼ���ϴ����---����initialize()����
			smartUpload.initialize(getServletConfig(), request, response);
			
			//���2---����ļ���С
			smartUpload.setMaxFileSize(100*1024);
			
			//���3---����ļ���ʽ
			smartUpload.setAllowedFilesList("jpg,txt");//������ļ�����
		
			//smartUpload.setDeniedFilesList("html");//��������ļ�����
			
			
			// ����3---�ϴ��ļ�������������ʱ�ڴ���---����upload()����
			smartUpload.upload();

			// ����4---��ȡ�ϴ��ļ�
			SmartFile smartFile = smartUpload.getFiles().getFile(0);

			String username = smartFile.getFileName();
			System.out.println(username);
			//����Ƿ�Ϊ��
			if(smartFile.isMissing()){
				out.print("<script>alert('server : ����ѡ���ļ�');history.back();</script>");
			}
			
			// ����5---׼���ϴ��ļ��Ĵ洢·�����ļ���---��֤�ļ���Ψһ
			String serverFilePath = "E:/�����ʵ��/����ʵϰ/1/upload";

			File dir = new File(serverFilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			String serverFilename = StringUtil.convertFilename(smartFile.getFileName());

			// ����6---�����ϴ��ļ�
			smartFile.saveAs(serverFilePath + "/" + serverFilename);

			out.print("<script>alert('�ļ��ϴ��ɹ�');location='upload_single.jsp';</script>");
		} catch (SmartUploadException e) {
			e.printStackTrace();
			out.print("<script>alert('�ļ��ϴ�ʧ��');location='upload_single.jsp';</script>");
		}catch (SecurityException e) {
			e.printStackTrace();
			out.print("<script>alert('�ļ���С���ܳ���100k���ļ����ͱ���Ϊjpg����');location='upload_single.jsp';</script>");
		}/*catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("<script>alert('�ļ����Ͳ���Ϊhtml����');location='upload_single.jsp';</script>");
		}*/

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
