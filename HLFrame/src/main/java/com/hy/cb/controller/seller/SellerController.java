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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hy.cb.entity.seller.SeSeller;
import com.hy.cb.entity.seller.SeShop;
import com.hy.cb.service.seller.SeShopService;
import com.hy.cb.service.seller.SellerService;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.utils.IntegerTools;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;
import com.hy.sys.utils.FileUpload;

@Controller
@RequestMapping("/cb/seller")
public class SellerController extends AbstractBasicController {

	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SeShopService seShopService;

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
				if (StringTools.isNotBlank(entity.getProvince())) {
					String areaCode = entity.getProvince();
					String[] areaCodeArr = areaCode.split("-");
					if (areaCodeArr.length == 1) {
						entity.setProvince(areaCodeArr[0]);
					} else if (areaCodeArr.length == 2) {
						entity.setProvince(areaCodeArr[0]);
						entity.setCity(areaCodeArr[1]);
					} else if (areaCodeArr.length == 3) {
						entity.setProvince(areaCodeArr[0]);
						entity.setCity(areaCodeArr[1]);
						entity.setRegion(areaCodeArr[2]);
					} else if (areaCodeArr.length == 4) {
						entity.setProvince(areaCodeArr[0]);
						entity.setCity(areaCodeArr[1]);
						entity.setRegion(areaCodeArr[2]);
						entity.setTown(areaCodeArr[3]);
					}
				}

				map = sellerService.saveSeller(entity);
				map.put("msg", "添加成功！");
			} else {
				map.put("msg", "商家名称已经存在！");
			}
		} else { // 修改

		}
		return map;

	}

	/**********************************************
	 * 档口管理
	 **************************************************/
	@RequestMapping("/shopAdd")
	public ModelAndView shopAdd() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	@RequestMapping("/shopEdit")
	public ModelAndView shopEdit(HttpServletResponse response, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		String shopid = request.getParameter("shopid");
		if (StringTools.isNotBlank(shopid)) {
			SeShop entity = seShopService.get(shopid);
			if (entity != null && StringTools.isNotBlank(entity.getEpNo())) {
				SeSeller seller = sellerService.get(entity.getEpNo());
				if (seller != null) {
					entity.setEpName(seller.getName());
				}
			}
			view.addObject("shop", entity);
		}
		
		return view;
	}

	@RequestMapping("/shopList")
	public ModelAndView shopList() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	/**
	 * 商家选择列表
	 * 
	 * @return
	 */
	@RequestMapping("/sellerSelect")
	public ModelAndView sellerSelect() {
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
	@RequestMapping("/getShopList")
	public PageInfo<SeShop> getShopList(@ModelAttribute SeShop entity, HttpServletResponse response,
			HttpServletRequest request) {
		int pageNo = (request.getParameter("page") == null) ? PAGE_NO
				: IntegerTools.parseInt(request.getParameter("page"));
		int pageSize = (request.getParameter("rows") == null) ? PAGE_SIZE
				: IntegerTools.parseInt(request.getParameter("rows"));

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("queryKey", request.getParameter("queryKey"));

		PageInfo<SeShop> pages = seShopService.getPageList(params, entity, pageNo, pageSize);
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
	@RequestMapping("/saveShop")
	public Map<String, Object> saveCategory(@ModelAttribute SeShop entity, HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date now = new Date();

		if (StringTools.isNotBlank(entity.getProvince())) {
			String areaCode = entity.getProvince();
			String[] areaCodeArr = areaCode.split("-");
			if (areaCodeArr.length == 1) {
				entity.setProvince(areaCodeArr[0]);
			} else if (areaCodeArr.length == 2) {
				entity.setProvince(areaCodeArr[0]);
				entity.setCity(areaCodeArr[1]);
			} else if (areaCodeArr.length == 3) {
				entity.setProvince(areaCodeArr[0]);
				entity.setCity(areaCodeArr[1]);
				entity.setRegion(areaCodeArr[2]);
			} else if (areaCodeArr.length == 4) {
				entity.setProvince(areaCodeArr[0]);
				entity.setCity(areaCodeArr[1]);
				entity.setRegion(areaCodeArr[2]);
				entity.setTown(areaCodeArr[3]);
			}
		}

		if (StringTools.isBlank(entity.getShopid())) {
			entity.setCreateDate(now);
			seShopService.save(entity);
			map.put("msg", "添加成功！");
			map.put("code", "1");

		} else { // 修改
			SeShop shop = seShopService.get(entity.getShopid());
			shop.setAddress(entity.getAddress());
			shop.setProvince(entity.getProvince());
			shop.setCity(entity.getCity());
			shop.setRegion(entity.getRegion());
			shop.setContacts(entity.getContacts());
			shop.setName(entity.getName());
			shop.setEpNo(entity.getEpNo());
			shop.setTelephone(entity.getTelephone());
			shop.setTel(entity.getTel());
			shop.setIntroduce(entity.getIntroduce());
			seShopService.save(shop);
			map.put("msg", "修改成功！");
			map.put("code", "1");
		}
		return map;

	}

	@RequestMapping("/delShop")
	public Map<String, Object> delShop(@RequestParam(required = true) String shopId, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isNotBlank(shopId)) {
			seShopService.delete(shopId, true);
			map.put("msg", "删除成功！");
			map.put("code", "1");
		} else {
			map.put("msg", "删除失败，ID不能为空！");
			map.put("code", "0");
		}
		return map;
	}

	/**********************************************
	 * 档口管理
	 **************************************************/
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
		Map<String, Object> map = FileUpload.upload(request);
		return map;
	}
}
