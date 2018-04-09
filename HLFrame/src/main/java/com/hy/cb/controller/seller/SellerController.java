package com.hy.cb.controller.seller;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hy.cb.entity.seller.SeSeller;
import com.hy.cb.service.seller.SellerService;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.utils.FileUploads;
import com.hy.sys.utils.IntegerTools;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

@Controller
@RequestMapping("/cb/seller")
public class SellerController extends AbstractBasicController {

	@Autowired
	private SellerService sellerService;

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	@RequestMapping("/sellerList")
	public ModelAndView sellerList() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	@RequestMapping("/addSeller")
	public ModelAndView addSeller() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	/**
	 * 显示商家列表
	 * 
	 * @param entity
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSellerList")
	public PageInfo<SeSeller> getMemberList(@ModelAttribute SeSeller entity, HttpServletResponse response,
			HttpServletRequest request) {
		int pageNo = (request.getParameter("page") == null) ? PAGE_NO
				: IntegerTools.parseInt(request.getParameter("page"));
		int pageSize = (request.getParameter("rows") == null) ? PAGE_SIZE
				: IntegerTools.parseInt(request.getParameter("rows"));

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("queryKey", request.getParameter("queryKey"));

		PageInfo<SeSeller> pages = sellerService.getPageList(params, entity, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		String jsonStr = JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteMapNullValue);
		writeResult(jsonStr, response);

		return pages;

	}

	/**
	 * 添加数据
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveSeller")
	public Map<String, Object> saveSeller(@ModelAttribute SeSeller entity, HttpServletResponse response,
			HttpServletRequest request) {

		Date now = new Date();
		Map<String, Object> map = new HashMap<String, Object>();

		if (StringTools.isBlank(entity.getSeller_id())) {
			SeSeller seller = sellerService.findByName(entity.getName());
			if (seller == null) {
				if(StringTools.isNotBlank(entity.getProvince())) {
					String areaCode=entity.getProvince();
					String[] areaCodeArr=areaCode.split("-");
					if(areaCodeArr.length==1) {
						entity.setProvince(areaCodeArr[0]);
					}else if(areaCodeArr.length==2) {
						entity.setProvince(areaCodeArr[0]);
						entity.setCity(areaCodeArr[1]);
					}else if(areaCodeArr.length==3) {
						entity.setProvince(areaCodeArr[0]);
						entity.setCity(areaCodeArr[1]);
						entity.setRegion(areaCodeArr[2]);
					}else if(areaCodeArr.length==4) {
						entity.setProvince(areaCodeArr[0]);
						entity.setCity(areaCodeArr[1]);
						entity.setRegion(areaCodeArr[2]);
						entity.setTown(areaCodeArr[3]);
					}
				}
				entity.setCreateTime(now);
				sellerService.save(entity);
				map.put("code", "1");
				map.put("msg", "添加成功！");
			} else {
				map.put("code", "0");
				map.put("msg", "商家名称已经存在！");
			}
		}else { //修改
			
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
