package com.hy.cb.controller.seller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hy.cb.entity.seller.SeGood;
import com.hy.cb.entity.seller.SeGoodsCategory;
import com.hy.cb.service.seller.SeGoodService;
import com.hy.cb.service.seller.SeGoodsCategoryService;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.FileUploads;
import com.hy.sys.utils.IntegerTools;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

@Controller
@RequestMapping("/cb/goods")
public class SeGoodController  extends AbstractBasicController {

	@Autowired
	private SeGoodsCategoryService seGoodsCategoryService;

	
	@Autowired
	private SeGoodService seGoodService;
	
	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}
	/************************************商品分类管理部分*************************************************/
	@RequestMapping("/categoryAdd")
	public ModelAndView categoryAdd() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	@RequestMapping("/categoryEdit")
	public ModelAndView categoryEdit(HttpServletResponse response, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		String category_id = request.getParameter("categoryId");
		SeGoodsCategory entity = seGoodsCategoryService.get(category_id);
		view.addObject("cat", entity);
		return view;
	}
	
	@RequestMapping("/categoryList")
	public ModelAndView categoryList() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	/**
	 * 显示商家列表
	 * @param entity
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCategoryList")
	public PageInfo<SeGoodsCategory> getCategoryList(@ModelAttribute SeGoodsCategory entity, HttpServletResponse response,
			HttpServletRequest request) {
		int pageNo = (request.getParameter("page") == null) ? PAGE_NO
				: IntegerTools.parseInt(request.getParameter("page"));
		int pageSize = (request.getParameter("rows") == null) ? PAGE_SIZE
				: IntegerTools.parseInt(request.getParameter("rows"));

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("queryKey", request.getParameter("queryKey"));

		PageInfo<SeGoodsCategory> pages = seGoodsCategoryService.getPageList(params, entity, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		String jsonStr = JSON.toJSONString(map);
		writeResult(jsonStr, response);

		return pages;

	}

	/**
	 * 添加数据 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveCategory")
	public Map<String, Object> saveCategory(@ModelAttribute SeGoodsCategory entity, HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (StringTools.isBlank(entity.getCategoryId())) {
			SeGoodsCategory category = seGoodsCategoryService.findByName(entity.getCategoryName());
			if (category == null) {
				seGoodsCategoryService.save(entity);
				map.put("msg", "添加成功！");
				map.put("code", "1");
			} else {
				map.put("msg", "分类名称已经存在！");
				map.put("code", "0");
			}
		} else { // 修改
			SeGoodsCategory category = seGoodsCategoryService.get(entity.getCategoryId());
			category.setCategoryName(entity.getCategoryName());
			category.setPicture(entity.getPicture());
			category.setSort(entity.getSort());
			category.setRemarks(entity.getRemarks());
			seGoodsCategoryService.save(category);
			map.put("msg", "修改成功！");
			map.put("code", "1");
		}
		return map;

	}
	
	public Map<String, Object> delCategory(@RequestParam(required = true) String categoryId, HttpServletResponse response, HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringTools.isNotBlank(categoryId)) {
		seGoodsCategoryService.delete(categoryId, true);
		map.put("msg", "删除成功！");
		map.put("code", "1");
		}else {
			map.put("msg", "删除失败，类型不能为空！");
			map.put("code", "0");
		}
		return map;
	}
	
	/************************************系统商品管理部分*************************************************/
	@RequestMapping("/goodsAdd")
	public ModelAndView goodsAdd() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	@RequestMapping("/goodsEdit")
	public ModelAndView goodsEdit(HttpServletResponse response, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		String goodsId = request.getParameter("goodsId");
		SeGood entity = seGoodService.get(goodsId);
		if(entity!=null&&StringTools.isNotEmpty(entity.getCategoryId())) {
			SeGoodsCategory category=seGoodsCategoryService.get(entity.getCategoryId());
					if(category!=null) {
						entity.setCategoryName(category.getCategoryName());
					}
		}
		
		view.addObject("goods", entity);
		return view;
	}
	
	@RequestMapping("/goodsList")
	public ModelAndView goodsList() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	
	@RequestMapping("/categorySelect")
	public ModelAndView categorySelect() {
		ModelAndView view = new ModelAndView();
		return view;
	}
	/**
	 * 显示商家列表
	 * @param entity
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getGoodsList")
	public PageInfo<SeGood> getGoodsList(@ModelAttribute SeGood entity, HttpServletResponse response,
			HttpServletRequest request) {
		int pageNo = (request.getParameter("page") == null) ? PAGE_NO
				: IntegerTools.parseInt(request.getParameter("page"));
		int pageSize = (request.getParameter("rows") == null) ? PAGE_SIZE
				: IntegerTools.parseInt(request.getParameter("rows"));

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("queryKey", request.getParameter("queryKey"));

		PageInfo<SeGood> pages = seGoodService.getPageList(params, entity, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		String jsonStr = JSON.toJSONString(map);
		writeResult(jsonStr, response);

		return pages;

	}

	/**
	 * 添加数据 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveGoods")
	public Map<String, Object> saveGoods(@ModelAttribute SeGood entity, HttpServletResponse response,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
        Date now=new Date();
		if (StringTools.isBlank(entity.getGoodsId())) {
			try {
				entity.setCreateDate(now);
				entity.setCreateBy(UserUtils.getUser().getUserid());
				seGoodService.save(entity);
				map.put("msg", "添加成功！");
				map.put("code", "1");
			}catch (DataAccessException e) {				
				map.put("msg", "添加失败！");
				map.put("code", "0");
			}
		} else { // 修改
			SeGood goods = seGoodService.get(entity.getGoodsId());
		    goods.setGoodsName(entity.getGoodsName());
		    goods.setCategoryId(entity.getCategoryId());
		    goods.setPicture(entity.getPicture());
		    goods.setUpdateBy(UserUtils.getUser().getUserid());  
		    goods.setUpdateDate(now);
			seGoodService.save(goods);
			map.put("msg", "修改成功！");
			map.put("code", "1");
		}
		return map;

	}
	
	public Map<String, Object> delGoods(@RequestParam(required = true) String goodsId, HttpServletResponse response, HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringTools.isNotBlank(goodsId)) {
			seGoodService.delete(goodsId, true);
		map.put("msg", "删除成功！");
		map.put("code", "1");
		}else {
			map.put("msg", "删除失败，ID不能为空！");
			map.put("code", "0");
		}
		return map;
	}
	
	
	
	
	/**
	 * 图片上传
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/fileUpload")
	public Map<String, Object> fileUpload(HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String, Object> map = FileUploads.upload(request);
		return map;
	}
	

}
