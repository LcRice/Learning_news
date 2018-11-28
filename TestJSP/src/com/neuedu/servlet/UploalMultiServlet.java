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
		// ���������������
		response.setContentType("text/html;charset=utf-8");

		// ��ȡout�������
		PrintWriter out = response.getWriter();

			// ����1---�����ϴ����---ʵ����SmartUpload����
			SmartUpload smartUpload = new SmartUpload("utf-8");

			// ����2---��ʼ���ϴ����---����initialize()����
			smartUpload.initialize(getServletConfig(), request, response);
			// smartUpload.setDeniedFilesList("html");//��������ļ�����

			// ����3---�ϴ��ļ�������������ʱ�ڴ���---����upload()����
			try {
				smartUpload.upload();
			} catch (SmartUploadException e1) {
				e1.printStackTrace();
			}

			// ����5---׼���ϴ��ļ��Ĵ洢·�����ļ���---��֤�ļ���Ψһ
			String serverFilePath = "E:/�����ʵ��/����ʵϰ/1/upload";

			File dir = new File(serverFilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			boolean flag = true;

			for (int i = 0; i < smartUpload.getFiles().getCount(); i++) {

				// ����4---��ȡ�ϴ��ļ�
				SmartFile smartFile = smartUpload.getFiles().getFile(i);
				// ����Ƿ�Ϊ��
				if (smartFile.isMissing()) {
					out.print("<script>alert('server : ����ѡ���ļ�');history.back();</script>");
				}

				int MaxFileSize = 100;

				if (smartFile.getSize() > MaxFileSize * 1024) {
					out.print("<script>alert('��" + (i + 1) + "���ļ���С���ܳ���" + MaxFileSize + "k');</script>");
					flag = false;
					continue;
				}

				String AllowedFile = "jpg,txt";

				if (!AllowedFile.contains(smartFile.getFileExt())) {
					out.println("<script>alert('��" + (i + 1) + "���ļ����ͱ�����" + AllowedFile + "');</script>");
					flag = false;
					continue;
				}

				String serverFilename = StringUtil.convertFilename(smartFile.getFileName());

				// ����6---�����ϴ��ļ�
				try {
					smartFile.saveAs(serverFilePath + "/" + serverFilename);
				} catch (SmartUploadException e) {
					e.printStackTrace();
					out.println("<script>alert('��" + (i + 1) + "���ļ��ϴ�ʧ��');</script>");
				}

			}
			if(flag){
				out.println("<script>alert('ȫ���ļ��ϴ��ɹ�');</script>");
			}else{
				out.println("<script>alert('�����ļ��ϴ��ɹ�');</script>");
			}
			
			out.println("<script>location='upload_multi.jsp'</script>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
