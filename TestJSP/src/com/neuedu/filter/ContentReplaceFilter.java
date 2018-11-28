package com.neuedu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ContentReplaceFilter implements Filter {

	private String wordItemList;
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		//转型
		HttpServletRequest req  = (HttpServletRequest)request;
		
		//创建wrapper对象
		ContentReplaceWrapper wrapper = new ContentReplaceWrapper(req, wordItemList);
		
		// pass the request along the filter chain
		chain.doFilter(wrapper, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		wordItemList = fConfig.getInitParameter("wordItemList");
	}

}