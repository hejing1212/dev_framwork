package com.hy.sys.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoader;

public class  InitDicListener implements ServletContextListener{

	/**
	 * 系统启动初始化数据
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
		DictUtils.setCacheDic();
		ServletContext context=sce.getServletContext();
		
		
		String basePath =context.getContextPath(); //ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
		context.setAttribute("basePath",basePath);
		}catch (Exception e) {
			System.out.println("初始化数据失败："+e.toString());
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub		
	}


	
}


