package com.hy.cb.controller.api;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hy.cb.entity.seller.SeSeller;
import com.hy.cb.service.seller.SellerService;
import com.hy.cb.utils.api.WebServiceResult;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.utils.StringTools;
import com.hy.sys.utils.logs.LogUtil;

@Controller
@RequestMapping("/api/seller")
public class SellerApicontroller extends AbstractBasicController {

	@Autowired
	private SellerService sellerService;

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	public WebServiceResult addSeller(@ModelAttribute SeSeller entity, String userId, HttpServletRequest request)
			throws ParseException {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isBlank(userId)) {
			json.setSuccess(false);
			json.setMessage("用户ID 不能为空！");
			return json;
		}
		if (StringTools.isBlank(entity.getName())) {
			json.setSuccess(false);
			json.setMessage("名称不能为空！");
			return json;
		}
		try {
			Map<String,Object> map=sellerService.saveSeller(entity);
			if(map.get("code").equals("1")) {
				String sellerId=(String) map.get("cellerId");
				
				//增加商家与用户关联表
			}
			json.setMessage("添加成功！");
			json.setCode(200);
			return json;
		} catch (DataAccessException e) {
			LogUtil.info(this, e);
			json.setSuccess(false);
			json.setMessage("系统错误！");
		}
		return null;
	}

}
