package com.hy.sys.utils;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.alibaba.fastjson.JSON;
import com.hy.sys.entity.SysDataDict;
                                        
public class InitDicListener implements ServletContextListener{

	/**
	 * 系统启动初始化数据。可以设置值直接在JSP页面中调用显示
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext context=sce.getServletContext();
		List<SysDataDict> list=DictUtils.getAllList();
		String distJsonStr=JSON.toJSONString(list);
		context.setAttribute("dictJson", distJsonStr);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	
	 
	 

}
