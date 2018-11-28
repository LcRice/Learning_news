package com.neuedu.Listener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.neuedu.entity.User;

public class OnlineListListener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener,
		HttpSessionAttributeListener, ServletRequestListener, ServletRequestAttributeListener {

	private ServletContext application = null;

	public OnlineListListener() {
		// TODO Auto-generated constructor stub
	}

	// requests创建销毁
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("request 创建");
	}

	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		System.out.println("request 销毁");
	}

	// request修改
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
	}

	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
	}

	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
	}

	// session创建，销毁
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println("session 创建");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		List<String> online = (List<String>) this.application.getAttribute("online");
		// 取得当前用户名
		String username = (String) se.getSession().getAttribute("username");
		// 将此用户名从列表中删除
		online.remove(username);
		// 将删除后的列表重新设置到application属性中
		this.application.setAttribute("online", online);
		System.out.println("session 销毁");
	}

	// session修改
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		List<String> online = (List<String>) this.application.getAttribute("online");
		if ("username".equals(se.getName())) {
			// 将当前用户名添加到列表中
			online.add((String) se.getValue());
		}
		// 将添加后的列表重新设置到application属性中
		this.application.setAttribute("online", online);
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
	}

	// application创建，销毁
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		this.application = sce.getServletContext();
		// 设置一个列表属性，用于保存在想用户名
		this.application.setAttribute("online", new LinkedList<String>());
		System.out.println("application 创建");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("application 销毁");
	}

	// application修改
	public void attributeAdded(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
	}

	public void attributeReplaced(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
	}

	public void attributeRemoved(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
	}
}
