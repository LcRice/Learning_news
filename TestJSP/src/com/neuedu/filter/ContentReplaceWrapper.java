package com.neuedu.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ContentReplaceWrapper extends HttpServletRequestWrapper {

	private String wordItemList;  //组合
	
	public ContentReplaceWrapper(HttpServletRequest request, String wordItemList) {
		super(request);
		this.wordItemList = wordItemList;  //注入
	}


	@Override
	public String getParameter(String name) {
		
		if("content".equals(name)){   //接收的是content参数
			
			//接收评论内容的数据
			String content = super.getParameter(name);
			
			//解析wordItemList，并完成替换
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