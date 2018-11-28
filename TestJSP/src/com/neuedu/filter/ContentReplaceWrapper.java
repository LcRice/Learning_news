package com.neuedu.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ContentReplaceWrapper extends HttpServletRequestWrapper {

	private String wordItemList;  //���
	
	public ContentReplaceWrapper(HttpServletRequest request, String wordItemList) {
		super(request);
		this.wordItemList = wordItemList;  //ע��
	}


	@Override
	public String getParameter(String name) {
		
		if("content".equals(name)){   //���յ���content����
			
			//�����������ݵ�����
			String content = super.getParameter(name);
			
			//����wordItemList��������滻
			String[] wordItems = wordItemList.split(",");
			
			for(String wordItem : wordItems){
				
				String[] words = wordItem.split(":");
				
				String badWord = words[0];
				String goodWord = words[1];
				
				content = content.replace(badWord, goodWord);
				
			}
			
			return content;
		}
		
		return super.getParameter(name);
	}
}