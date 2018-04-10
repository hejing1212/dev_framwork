package com.hy.cb.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.cb.utils.api.WebServiceResult;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.entity.SysArea;
import com.hy.sys.service.SysAreaService;
import com.hy.sys.utils.StringTools;
import com.hy.sys.utils.logs.LogUtil;

@Controller
@RequestMapping("/api/area")
public class AreaApiController extends AbstractBasicController{

	@Autowired
	protected SysAreaService sysAreaService;
	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 递归查询子节点地区列表
	 * @param userId
	 * @param phone
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getRecursionAreaList")
	public WebServiceResult getRecursionAreaList(String pareatId) {
		WebServiceResult json = new WebServiceResult();
        if(StringTools.isBlank(pareatId)) {
        	pareatId="0";
        }
		try {
			List<SysArea> areaList=sysAreaService.getAllAreaList(pareatId);
			if(areaList.size()>0) {
				json.setSuccess(true);
				json.setCode(200);
				json.setDatas(areaList);
				json.setMessage("获取成功！");
			}else {
				json.setSuccess(false);
				json.setMessage("获取失败！");
			}
			 		
		}catch (DataAccessException e) {
			LogUtil.info(this, e);
			json.setSuccess(false);
			json.setMessage("获取失败！");
		}
		return json;
	}

	/**
	 * 根据父ID查询子节点
	 * @param pareatId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAreaList")
	public WebServiceResult getAreaList(String pareatId) {
		WebServiceResult json = new WebServiceResult();
        if(StringTools.isBlank(pareatId)) {
        	pareatId="0";
        }
		try {
			List<SysArea> areaList=sysAreaService.getAreaList(pareatId);
			if(areaList.size()>0) {
				json.setSuccess(true);
				json.setCode(200);
				json.setDatas(areaList);
				json.setMessage("获取成功！");
			}else {
				json.setSuccess(false);
				json.setMessage("获取失败！");
			}
			 		
		}catch (DataAccessException e) {
			LogUtil.info(this, e);
			json.setSuccess(false);
			json.setMessage("获取失败！");
		}
		return json;
	}
}
