package com.hy.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.entity.SysDataDict;
import com.hy.sys.entity.SysDataDictItem;
import com.hy.sys.entity.SysRole;
import com.hy.sys.service.SysDictItemService;
import com.hy.sys.service.SysDictService;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.DictUtils;
import com.hy.sys.utils.IntegerTools;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

/***
 * 数据字典管理
 * 
 * @author hejing
 *
 */
@Controller
@RequestMapping("/sys/dict")
public class DictController extends AbstractBasicController {

	@Autowired
	private SysDictService dataDictService;

	@Autowired
	private SysDictItemService sysDictItemService;

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	@RequestMapping("/dictList")
	public ModelAndView dictList(ModelMap mode, HttpServletRequest req) {
		ModelAndView view = new ModelAndView();
		return view;
	}

	/**
	 * 数据字典添加
	 * 
	 * @param mode
	 * @param req
	 * @return
	 */
	@RequestMapping("/dictAdd")
	public ModelAndView dictAdd(ModelMap mode, HttpServletRequest req) {
		ModelAndView view = new ModelAndView();
		return view;
	}

	/**
	 * 数据字典修改
	 * 
	 * @param mode
	 * @param req
	 * @return
	 */
	@RequestMapping("/dictEdit")
	public ModelAndView dictEdit(@RequestParam(value = "dictId") String dictId, ModelMap mode, HttpServletRequest req) {
		ModelAndView view = new ModelAndView();
		if (StringTools.isNotEmpty(dictId)) {
			SysDataDict dict = dataDictService.get(dictId);
			view.addObject("dict", dict);
		}
		return view;
	}

	/**
	 * 添加键值项
	 * 
	 * @param dictId
	 * @param mode
	 * @param req
	 * @return
	 */
	@RequestMapping("/dictItemAdd")
	public ModelAndView dictItemAdd(@RequestParam(value = "dictId", required = false) String dictId, ModelMap mode,
			HttpServletRequest req) {
		ModelAndView view = new ModelAndView();
		if (StringTools.isNotEmpty(dictId)) {
			view.addObject("dictId", dictId);
		} else {
			// 错误提示页面
		}
		return view;
	}

	/**
	 * 修改键值项
	 * 
	 * @param itemId
	 * @param req
	 * @return
	 */
	@RequestMapping("/dictItemEdit")
	public ModelAndView dictItemEdit(@RequestParam(value = "itemId", required = false) String itemId,
			HttpServletRequest req) {
		ModelAndView view = new ModelAndView();
		if (StringTools.isNotEmpty(itemId)) {
			SysDataDictItem item = sysDictItemService.get(itemId);
			view.addObject("item", item);
		} else {
			// 错误提示页面
		}
		return view;
	}

	/**
	 * 获取数据字典列表
	 * 
	 * @param entity
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDictList")
	public PageInfo<SysDataDict> getList(@ModelAttribute SysDataDict entity, HttpServletResponse response,
			HttpServletRequest request) {
		int pageNo = (request.getParameter("page") == null) ? PAGE_NO
				: IntegerTools.parseInt(request.getParameter("page"));
		int pageSize = (request.getParameter("rows") == null) ? PAGE_SIZE
				: IntegerTools.parseInt(request.getParameter("rows"));

		Map<String, Object> params = new HashMap<String, Object>();
		if (entity.getDictName() != null) {
			params.put("dict_name", entity.getDictName());
		}
		PageInfo<SysDataDict> pages = dataDictService.getPageList(params, entity, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		String jsonStr = JSON.toJSONString(map);
		writeResult(jsonStr, response);

		return pages;
	}

	/**
	 * 获取数据字典对应项
	 * 
	 * @param entity
	 * @param dictId
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDictItemList")
	public PageInfo<SysDataDictItem> getDictItemList(@ModelAttribute SysDataDictItem entity,
			HttpServletResponse response, HttpServletRequest request) {
		int pageNo = (request.getParameter("page") == null) ? PAGE_NO
				: IntegerTools.parseInt(request.getParameter("page"));
		int pageSize = (request.getParameter("rows") == null) ? PAGE_SIZE
				: IntegerTools.parseInt(request.getParameter("rows"));
		String dictId = request.getParameter("dictId");
		// 装载查询条件
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringTools.isNotEmpty(dictId)) {
			params.put("dict_code", dictId);
		}
		if (entity.getItemName() != null) {
			params.put("item_name", entity.getItemName());
		}

		PageInfo<SysDataDictItem> pages = sysDictItemService.getPageList(params, entity, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		String jsonStr = JSON.toJSONString(map);
		writeResult(jsonStr, response);

		return pages;
	}

	/****
	 * 保存字典
	 */
	@ResponseBody
	@RequestMapping("/saveDict")
	public Map<String, Object> saveDict(@ModelAttribute SysDataDict dict, HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date now = new Date();
		if (StringTools.isBlank(dict.getId())) {
			SysDataDict tmpdict = dataDictService.getDictName(dict.getDictName());
			if (tmpdict == null) {
				dict.setCreate_date(now);
				dict.setCreate_by(UserUtils.getUser().getUserid());
				dataDictService.save(dict);
				map.put("code", "1");
				map.put("msg", "添加成功！");
			} else {
				map.put("code", "0");
				map.put("msg", "数据字典名称已经存在，添加失败！");
			}
		} else {
			try {
				SysDataDict dicts = dataDictService.get(dict.getId());
				dicts.setDictName(dict.getDictName());
				dicts.setDictCode(dict.getDictCode());
				dicts.setRemarks(dict.getRemarks());
				dicts.setUpdate_by(UserUtils.getUser().getUserid());
				dicts.setUpdate_date(now);
				dataDictService.save(dicts);
				map.put("code", 1);
				map.put("msg", "修改数据字典信息成功！");
			} catch (DataAccessException e) {
				map.put("code", 1);
				map.put("msg", e.toString());
			}
		}
		return map;
	}

	/****
	 * 保存字典项
	 */
	@ResponseBody
	@RequestMapping("/saveDictItem")
	public Map<String, Object> saveDictItem(@ModelAttribute SysDataDictItem item, HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date now = new Date();
		if (StringTools.isNotEmpty(item.getDictCode())) {
			if (StringTools.isBlank(item.getId())) {
				SysDataDictItem items = sysDictItemService.getDictItemName(item.getItemName());
				if (items == null) {
					item.setCreate_date(now);
					item.setCreate_by(UserUtils.getUser().getUserid());

					sysDictItemService.save(item);
					map.put("code", "1");
					map.put("msg", "添加成功！");
				} else {
					map.put("code", "0");
					map.put("msg", "字典名称已经存在，添加失败！");
				}
			} else {
				try {
					SysDataDictItem items = sysDictItemService.get(item.getId());
					items.setItemName(item.getItemName());
					items.setItemValue(item.getItemValue());
					items.setDictCode(item.getDictCode());
					items.setSort(item.getSort());
					items.setRemarks(item.getRemarks());
					items.setUpdate_by(UserUtils.getUser().getUserid());
					items.setUpdate_date(now);
					sysDictItemService.save(items);
					map.put("code", 1);
					map.put("msg", "修改数据字典成功！");
				} catch (DataAccessException e) {
					map.put("code", 1);
					map.put("msg", e.toString());
				}
			}
		} else {
			map.put("code", 0);
			map.put("msg", "非法操作 ，未获取到数据字典编码！");
		}
		return map;
	}

	/**
	 * 删除数据字典
	 * 
	 * @param dictId
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delDict")
	public Map<String, Object> deleteDict(@RequestParam(required = true) String dictId, HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isNotBlank(dictId)) {
			if (sysDictItemService.getDictItemByDictId(dictId) == null) {
				dataDictService.delete(dictId, true);
				map.put("code", "1");
				map.put("msg", "删除成功");
			} else {
				map.put("code", "0");
				map.put("msg", "当前字典存在键值项，不能删除！");
			}
		} else {
			map.put("code", "0");
			map.put("msg", "参数错误");
		}
		return map;
	}

	/**
	 * 删除数据字典键值
	 * 
	 * @param dictId
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delDictItem")
	public Map<String, Object> delDictItem(@RequestParam(required = true) String itemId, HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isNotBlank(itemId)) {
			sysDictItemService.delete(itemId, true);
			map.put("code", "1");
			map.put("msg", "删除成功");
		} else {
			map.put("code", "0");
			map.put("msg", "参数错误");
		}
		return map;
	}

	/**
	 * 返回SON格式的 所有数据字典
	 * @param itemValue
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDitcJson")
	public String getDitcJson(HttpServletResponse response, HttpServletRequest request) {
		List<SysDataDict> list=DictUtils.getCacheDic();
		if(list!=null) {
			return JSON.toJSONString(list);
		}
		 return null;
	}
	
	@RequestMapping("/test")
	public ModelAndView test() {
		ModelAndView view = new ModelAndView();
		return view;
	}
	
	
}
