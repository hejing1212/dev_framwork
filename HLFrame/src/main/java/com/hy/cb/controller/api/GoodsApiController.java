package com.hy.cb.controller.api;

import java.util.Date;
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
import com.hy.cb.entity.seller.SeGoodsCategory;
import com.hy.cb.entity.seller.SeSellerGoodsCategory;
import com.hy.cb.service.seller.SeEnterpriseGoodService;
import com.hy.cb.service.seller.SeGoodService;
import com.hy.cb.service.seller.SeGoodsCategoryService;
import com.hy.cb.service.seller.SeSellerGoodsCategoryService;
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

	// 商家自己的商品分类
	@Autowired
	private SeSellerGoodsCategoryService seSellerGoodsCategoryService;

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
	public WebServiceResult getSysGoodsCategory(String sellerId,int pageNo, int pageSize, HttpServletRequest request) {
		WebServiceResult json = new WebServiceResult();	
		pageNo = (pageNo == 0) ? PAGE_NO : pageNo;
		pageSize = (pageSize == 0) ? PAGE_SIZE : pageSize;

		Map<String, Object> params = new HashMap<String, Object>();
		if (StringTools.isNotEmpty(sellerId)) {
			params.put("sellerId", sellerId);
		}
		SeSellerGoodsCategory entity = new SeSellerGoodsCategory();
		PageInfo<SeGoodsCategory> pages = seSellerGoodsCategoryService.getSellerCategoryPageList(params, entity, pageNo, pageSize);
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
	 * 获取系统设定的商品
	 * 
	 * @param sort
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getSysGoods")
	public WebServiceResult getSysGoods(String categoryId, int pageNo, int pageSize, HttpServletRequest request) {
		WebServiceResult json = new WebServiceResult();
		pageNo = (pageNo == 0) ? PAGE_NO : pageNo;
		pageSize = (pageSize == 0) ? PAGE_SIZE : pageSize;

		Map<String, Object> params = new HashMap<String, Object>();
		if (StringTools.isNotEmpty(categoryId)) {
			params.put("categoryId", categoryId);
		}
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
	 * 
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
		Date now = new Date();
		if (StringTools.isEmpty(entity.getEpGoodsId())) {
			try {
				entity.setEpCreateDate(now);
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
		} else {
			SeEnterpriseGood epGoods = seEnterpriseGoodService.get(entity.getEpGoodsId());
			if (epGoods != null) {
				epGoods.setActivityPrice(entity.getActivityPrice());
				epGoods.setEpAlias(entity.getEpAlias());
				epGoods.setUnitPrice(entity.getUnitPrice());
				epGoods.setPurchasePrice(entity.getPurchasePrice());
				epGoods.setShopNo(entity.getShopNo());
				epGoods.setWrapperType(entity.getWrapperType());
				epGoods.setEpGoodsName(entity.getEpGoodsName());
				epGoods.setSupplierNo(entity.getSupplierNo());
				epGoods.setClassId(entity.getClassId());
				seEnterpriseGoodService.save(epGoods);
				json.setMessage("操作成功！");
				json.setSuccess(true);
				json.setCode(200);
			} else {
				json.setMessage("操作失败,未查询到该商品信息！");
				json.setSuccess(false);
			}

		}
		if (StringTools.isNotEmpty(entity.getClassId()) && StringTools.isNotEmpty(entity.getSupplierNo())) {
			SeSellerGoodsCategory sellerGoodsCat = seSellerGoodsCategoryService
					.GetSellerGoodsCategoryByClassId(entity.getClassId());
			if (sellerGoodsCat == null) {
				sellerGoodsCat = new SeSellerGoodsCategory();
				sellerGoodsCat.setCategoryId(entity.getClassId());
				sellerGoodsCat.setSellerId(entity.getSupplierNo());
				seSellerGoodsCategoryService.save(sellerGoodsCat);
			}
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
	public WebServiceResult getSellerShopGoods(String sellerId, String shopId, int pageNo, int pageSize,
			HttpServletRequest request) {
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

	/**
	 * 商家商品删除接口
	 * 
	 * @param goodsId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delSellerShopGoods")
	public WebServiceResult delSellerShopGoods(String goodsId, HttpServletRequest request) {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isNotBlank(goodsId)) {

			SeEnterpriseGood epGoods = seEnterpriseGoodService.get(goodsId);
			if (epGoods != null) {
				String classId = epGoods.getClassId();
				if (classId != null) {
					SeEnterpriseGood otherEpGoods = seEnterpriseGoodService.findGoodsByClassIdNotGoodsId(classId,
							goodsId);
					seEnterpriseGoodService.delete(goodsId, true);

					if (otherEpGoods == null) { // 如果该分类下不有其它 商品了，就将该 分类删除
						seSellerGoodsCategoryService.delSellerCategoryByCategoryId(classId);
					}
				} else {
					LogUtil.info("系统异常,商品分类ID存在为空再现");
				}
				json.setMessage("操作成功！");
				json.setSuccess(true);
				json.setCode(200);
			} else {
				json.setMessage("操作失败！");
				json.setSuccess(false);
			}
		} else {
			json.setMessage("操作失败！");
			json.setSuccess(false);
		}

		return json;
	}
}
