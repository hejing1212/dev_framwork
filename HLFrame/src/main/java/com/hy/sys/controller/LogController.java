package com.hy.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.entity.SysLog;
import com.hy.sys.entity.SysRole;
import com.hy.sys.service.LogService;
import com.hy.sys.utils.ConvertJson;
import com.hy.sys.utils.IntegerTools;
import com.hy.sys.utils.PageInfo;

@Controller
@RequestMapping("/sys/log")
public class LogController extends AbstractBasicController{

	@Autowired
	protected LogService sysLogService;
	
	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}
	

	/**
	 * 显示角色选择列表
	 * 
	 * @return
	 */
	@RequestMapping("/logList")
	public ModelAndView Role(HttpServletResponse response, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/getLogList")
	public PageInfo<SysLog> getList(@ModelAttribute SysLog entity, HttpServletResponse response,
			HttpServletRequest request) {
		int pageNo = (request.getParameter("page") == null) ? PAGE_NO
				: IntegerTools.parseInt(request.getParameter("page"));
		int pageSize = (request.getParameter("rows") == null) ? PAGE_SIZE
				: IntegerTools.parseInt(request.getParameter("rows"));

		Map<String, Object> params = new HashMap<String, Object>();
        params.put("queryKey", request.getParameter("queryKey"));
        
		PageInfo<SysLog> pages = sysLogService.getPageList(params, entity, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		String jsonStr = ConvertJson.map2json(map);
		writeResult(jsonStr, response);

		return pages;
	}

}
