package com.hy.cb.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.cb.entity.seller.SeEnterpriseGood;
import com.hy.cb.entity.seller.SeGood;
import com.hy.cb.service.seller.SeEnterpriseGoodService;
import com.hy.cb.service.seller.SeGoodService;
import com.hy.cb.service.seller.SeGoodsCategoryService;
import com.hy.cb.utils.api.WebServiceResult;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;
import com.hy.sys.utils.logs.LogUtil;

@Controller
@RequestMapping("/api/goods")
public class GoodsApiController extends AbstractBasicController {

	// 系统商品
	@Autowired
	private SeGoodService seGoodService;

	// 商品分类
	@Autowired
	private SeGoodsCategoryService seGoodsCategoryService;

	// 企业商品
	@Autowired
	private SeEnterpriseGoodService seEnterpriseGoodService;

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	/**
	 * 系统商家商品分类获取
	 * 
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getSysGoodsCategory")
	public WebServiceResult getSysGoodsCategory(int pageNo, int pageSize, HttpServletRequest request) {
		WebServiceResult json = new WebServiceResult();

		return json;

	}

	/**
	 * 获取系统设定的商品
	 * 
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getSysGoods")
	public WebServiceResult getSysGoods(String sellerId,String shopId,int pageNo, int pageSize, HttpServletRequest request) {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isEmpty(shopId)) {
			json.setSuccess(false);
			json.setMessage("档口编号不能为空！");
			return json;
		}
		if (StringTools.isEmpty(sellerId)) {
			json.setSuccess(false);
			json.setMessage("商家编号不能为空！");
			return json;
		}
		pageNo = (pageNo == 0) ? PAGE_NO : pageNo;
		pageSize = (pageSize == 0) ? PAGE_SIZE : pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("sellerId", sellerId);
        params.put("shopId", shopId);
		SeGood entity = new SeGood();
		PageInfo<SeGood> pages = seGoodService.getPageList(params, entity, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		if (pages.getTotalrecond() > 0) {
			json.setMessage("操作成功！");
			json.setDatas(map);
			json.setSuccess(true);
			json.setCode(200);
		} else {
			json.setMessage("未获取到数据！");
			json.setSuccess(false);
		}

		return json;

	}
	
	/**
	 * 商家设置商品信息接口
	 * @param entity
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "setSellereGoods")
	public WebServiceResult setSellereGoods(SeEnterpriseGood entity, HttpServletRequest request) {
		WebServiceResult json = new WebServiceResult();

		if (StringTools.isEmpty(entity.getShopNo())) {
			json.setSuccess(false);
			json.setMessage("档口编号不能为空！");
			return json;
		}
		if (StringTools.isEmpty(entity.getSupplierNo())) {
			json.setSuccess(false);
			json.setMessage("商家编号不能为空！");
			return json;
		}
		if (StringTools.isEmpty(entity.getGoodsId())) {
			json.setSuccess(false);
			json.setMessage("系统商品编号不能为空！");
			return json;
		}
		if (StringTools.isEmpty(entity.getEpGoodsName())) {
			json.setSuccess(false);
			json.setMessage("商品名称不能为空！");
			return json;
		}
		try {
			seEnterpriseGoodService.save(entity);
			json.setDatas(entity);
			json.setMessage("操作成功！");
			json.setSuccess(true);
			json.setCode(200);
		} catch (DataAccessException e) {
			LogUtil.info(this, e);
			json.setMessage("操作失败:" + e.toString());
			json.setSuccess(false);
		}
		return json;

	}
	
	/**
	 * 获取商家设定的商品
	 * 
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getSellerShopGoods")
	public WebServiceResult getSellerShopGoods(String sellerId,String shopId,int pageNo, int pageSize, HttpServletRequest request) {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isEmpty(shopId)) {
			json.setSuccess(false);
			json.setMessage("档口编号不能为空！");
			return json;
		}
		if (StringTools.isEmpty(sellerId)) {
			json.setSuccess(false);
			json.setMessage("商家编号不能为空！");
			return json;
		}
		pageNo = (pageNo == 0) ? PAGE_NO : pageNo;
		pageSize = (pageSize == 0) ? PAGE_SIZE : pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("sellerId", sellerId);
        params.put("shopId", shopId);
        SeEnterpriseGood entity = new SeEnterpriseGood();
		PageInfo<SeEnterpriseGood> pages = seEnterpriseGoodService.getPageList(params, entity, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		if (pages.getTotalrecond() > 0) {
			json.setMessage("操作成功！");
			json.setDatas(map);
			json.setSuccess(true);
			json.setCode(200);
		} else {
			json.setMessage("未获取到数据！");
			json.setSuccess(false);
		}

		return json;

	}
}
