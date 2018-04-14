package com.hy.cb.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.cb.utils.api.WebServiceResult;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.entity.SysDataDictItem;
import com.hy.sys.service.SysDictItemService;

@Controller
@RequestMapping("/api/dict")
public class DictApiController extends AbstractBasicController {

	@Autowired
	protected SysDictItemService sysDictItemService;

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	/**
	 * 获取数据字典项
	 * 
	 * @param dictCode
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getDictOption")
	public WebServiceResult getDictOption(String dictCode, HttpServletRequest request) {
		WebServiceResult json = new WebServiceResult();
		List<SysDataDictItem> dictItem = sysDictItemService.getDictItemOption(dictCode);
		if (dictItem.size() > 0) {
			json.setSuccess(true);
			json.setCode(200);
			json.setDatas(dictItem);
			json.setMessage("获取成功！");
		} else {
			json.setSuccess(false);
			json.setMessage("未获取相应的项！");
		}
		return json;

	}

}
