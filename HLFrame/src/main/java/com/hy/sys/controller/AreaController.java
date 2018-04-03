package com.hy.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.entity.SysArea;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.service.SysAreaService;

public class AreaController extends AbstractBasicController{

	@Autowired
	private SysAreaService sysAreaService;

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}
	
	public Map<String,Object> getAreaList(@ModelAttribute SysArea entity, HttpServletResponse response,
			HttpServletRequest request){
		
		/*Map<String, Object> params = new HashMap<String, Object>();
		params.put("queryKey", request.getParameter("queryKey"));
		
		ArrayList<SysMenu> list = sysAreaService.getMenuList(params,entity);
		String jsonStr = JSON.toJSONString(list);
		writeResult(jsonStr, response);
		return list;*/
		return null;
	}
}
