package com.hy.cb.controller.api;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.cb.entity.api.VersionsEntity;
import com.hy.cb.utils.api.WebServiceResult;

@Controller
@RequestMapping("/api/appversion")
public class VersionUpdateApiController {

	/**
	 * APP版本更新
	 * @param req
	 * @param response
	 * @return
	 * @throws DocumentException
	 */	
	@ResponseBody
	@RequestMapping(value = "getVersion")
	public WebServiceResult getVersion(HttpServletRequest req,
			HttpServletResponse response) throws DocumentException {
		WebServiceResult jsonResult = new WebServiceResult();
		try {
			VersionsEntity entity = new VersionsEntity();
			ResourceBundle bundle = PropertyResourceBundle
					.getBundle("api-config");
			entity.setVersioncode(bundle.getString("versioncode"));
			entity.setVersionname(bundle.getString("versionname"));
			entity.setVersionurl(bundle.getString("versionurl"));
			entity.setDescription(bundle.getString("description"));
			jsonResult.setCode(200);
			jsonResult.setMessage("成功！");
			jsonResult.setDatas(entity);
			jsonResult.setSuccess(true);

		} catch (Exception e) {
			jsonResult.setCode(1);
			jsonResult.setMessage("失败！");
			jsonResult.setSuccess(true);
		}

		return jsonResult;
	}

}
