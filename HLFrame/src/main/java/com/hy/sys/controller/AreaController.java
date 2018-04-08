package com.hy.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.entity.SysArea;
import com.hy.sys.entity.TreeNode;
import com.hy.sys.service.SysAreaService;
import com.hy.sys.utils.IntegerTools;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

@Controller
@RequestMapping("/sys/area")
public class AreaController extends AbstractBasicController {

	@Autowired
	private SysAreaService sysAreaService;

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	/**
	 * 显示区域列表树
	 * 
	 * @return
	 */
	@RequestMapping("/areaList")
	public ModelAndView addUser() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	/**
	 * 显示添加界面
	 * 
	 * @return
	 */
	@RequestMapping("/areaAdd")
	public ModelAndView areaAdd() {
		ModelAndView view = new ModelAndView();
		return view;
	}
	/**
	 * 显示列表
	 * @param entity
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAreaList")
	public PageInfo<SysArea> getAreaList(@ModelAttribute SysArea entity, HttpServletResponse response,
			HttpServletRequest request) {
		int pageNo = (request.getParameter("page") == null) ? PAGE_NO
				: IntegerTools.parseInt(request.getParameter("page"));
		int pageSize = (request.getParameter("rows") == null) ? PAGE_SIZE
				: IntegerTools.parseInt(request.getParameter("rows"));

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("queryKey", request.getParameter("queryKey"));
		params.put("parent_id", 0);

		PageInfo<SysArea> pages = sysAreaService.getPageList(params, entity, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		String jsonStr = JSON.toJSONString(map);
		writeResult(jsonStr, response);

		return pages;
	}

	/**
	 * 获取子列表
	 * @param entity
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAreaChildrenList")
	public List<SysArea> getAreaChildrenList(@ModelAttribute SysArea entity, HttpServletResponse response,
			HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parent_id",request.getParameter("parentId"));
		List<SysArea> list = sysAreaService.getAreaList(params, entity);
		String jsonStr = JSON.toJSONString(list);
		writeResult(jsonStr, response);
		return list;
	}
	
	
	
	/**
	 * 生成下拉选择数据
	 * @param entity
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAreaTreeList")
	public List<TreeNode> getAreaTreeList(@RequestParam(value="parentId",required=false) String parentId, HttpServletResponse response,
			HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringTools.isNotBlank(parentId)){
			params.put("parent_id",parentId);
		}else {
			params.put("parent_id",0);
		}
		SysArea area=new SysArea();
		List<SysArea> list = sysAreaService.getAreaList(params, area);
		List<TreeNode> treelist=new ArrayList<TreeNode>();
		for(SysArea areali:list) {
			TreeNode node=new TreeNode();
			node.setText(areali.getName());
			node.setId(areali.getId());
			node.setState(areali.getState());
			treelist.add(node);
		}
		
		String jsonStr = JSON.toJSONString(treelist);
		writeResult(jsonStr, response);
		return null;
	}
	
	/**
	 * 查询所有菜单，遍历后返回
	 */
	/*public List<SysArea> getAreaTree(Map<String, Object> params, List<SysArea> li, SysArea entity) {
		List<SysArea> list = new ArrayList<SysArea>();
		List<SysArea> retlist = new ArrayList<SysArea>();

		for (SysArea area : li) {
			area.setChildenArea(CreateAreaTree(params, area.getId(), retlist, entity));
			list.add(area);
		}
		return list;
	}*/

	/**
	 * 根据菜单的子父级关系生成上下级关系的数据
	 * 
	 * @param menu
	 * @return
	 */
	/*public List<SysArea> CreateAreaTree(Map<String, Object> params, String id, List<SysArea> li,
			SysArea entity) {
		params.put("parent_id", id);
		List<SysArea> list = sysAreaService.getAreaList(params, entity);
		if (list != null) {
			for (SysArea area : list) {
				area.setChildenArea(CreateAreaTree(params, area.getId(), li, entity));
				li.add(area);
			}
		}
		return li;
	}*/

}
