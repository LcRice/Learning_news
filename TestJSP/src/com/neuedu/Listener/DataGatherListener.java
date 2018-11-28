package com.neuedu.Listener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.neuedu.util.StringUtil;

public class DataGatherListener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener,
		HttpSessionAttributeListener, ServletRequestListener, ServletRequestAttributeListener {

	private static String url;
	private static Map<String, Integer> map = new HashMap<>();

	public DataGatherListener() {
		// TODO Auto-generated constructor stub
	}

	// requests创建销毁
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)sre.getServletRequest();;
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
		System.out.println("session 销毁");
	}

	// session修改
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
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
		System.out.println("application 创建");
		
		Date date = new Date();
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.DATE, 1);
    	Date datetime = c.getTime();
    	String time = StringUtil.convertDatetime(datetime, "yyyyMMdd")+"020000";
    	DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    	try { 
			Date d = df.parse(time);
			 Timer timer = new Timer();
			 timer.schedule(new MyTimerTask(), d.getTime()-(new Date().getTime()), 86400000);
		} catch (ParseException e) {
			
		}
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
