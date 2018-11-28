package com.neuedu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CommentAddFilter2 implements Filter {

	String wordItemList;
	public void init(FilterConfig fConfig) throws ServletException {
		wordItemList = fConfig.getInitParameter("wordItemList");
	}
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		// 转型
		HttpServletRequest req = (HttpServletRequest) request;
		
		String content = req.getParameter("content");
		
		String[] words = wordItemList.split(",");
		
		for(int i = 0;i<words.length;i++){
			String[] oneWord = words[i].split(":");
			if(content.contains(oneWord[0])){
				content = content.replace(oneWord[0], oneWord[1]);
			}
		}
		System.out.println(content);
		req.setAttribute("content", content);
		chain.doFilter(request, response);
	}
	
}
