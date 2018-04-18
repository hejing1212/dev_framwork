package com.hy.cb.controller.api;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hy.cb.entity.order.OrderDetailed;
import com.hy.cb.entity.order.OrderEntity;
import com.hy.cb.entity.seller.SeGoodsCategory;
import com.hy.cb.entity.seller.SeSellerGoodsCategory;
import com.hy.cb.service.order.OrderService;
import com.hy.cb.utils.api.WebServiceResult;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;
import com.hy.sys.utils.StringUtils;
import com.hy.sys.utils.logs.LogUtil;

@Controller
@RequestMapping("/api/order")
public class OrderApiController extends AbstractBasicController {

	@Autowired
	protected OrderService orderService;

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	@ResponseBody
	@RequestMapping(value = "createOrder")
	public WebServiceResult createOrder(String orderJsonStr, HttpServletRequest request) throws ParseException {
		WebServiceResult json = new WebServiceResult();

		OrderEntity entity;
		try {
			entity = (OrderEntity) JSON.parseObject(orderJsonStr, OrderEntity.class);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMessage("参数错误！");
			return json;
		}

		if (StringTools.isBlank(entity.getShopNo())) {
			json.setSuccess(false);
			json.setMessage("档口编号 不能为空！");
			return json;
		}

		if (StringTools.isBlank(entity.getSupplierNo())) {
			json.setSuccess(false);
			json.setMessage("供应方编号 不能为空！");
			return json;
		}
		if (StringTools.isBlank(entity.getPurchaseNo())) {
			json.setSuccess(false);
			json.setMessage("采购方不能为空！");
			return json;
		}
		if (entity.getOrderType() == 0) {
			json.setSuccess(false);
			json.setMessage("订单类型不能为空！");
			return json;
		}
		if (entity.getOrderType() == 2) {
			if (entity.getConsignment() == 0) {
				json.setSuccess(false);
				json.setMessage("发货订单必须传销售类型！");
				return json;
			}

		}
		String orderTypeStr = "";
		switch (entity.getOrderType()) {
		case 2:
			orderTypeStr = "CN";
			break;
		case 4:
			orderTypeStr = "SL";
			break;
		case 6:
			orderTypeStr = "RE";
			break;
		case 8:
			orderTypeStr = "PH";
			break;
		default:
			break;
		}
		Date now = new Date();
		try {
			Set<OrderDetailed> list = entity.getOrderDetailed();
			entity.setOrderNo(orderTypeStr + StringUtils.getYMDhmsRand());
			entity.setCreateTime(now);
			entity.setStauts(2); //开单
			orderService.save(entity);

			for (OrderDetailed orderDetailed : list) {
				orderDetailed.setOrderNo(entity.getOrderNo());
			}
			orderService.saveBatch(list);

			json.setSuccess(true);
			json.setCode(200);
			json.setDatas(entity);
			json.setMessage("操作成功！");
		} catch (DataAccessException e) {
			LogUtil.info(this, e);
			json.setSuccess(false);
			json.setMessage("添加失败！");
			return json;
		}
		return json;
	}

	/**
	 * 获取各种状态下的订单
	 * @param supplierNo 供应商编号
	 * @param purchaseNo 采购商编号
	 * @param shopNo  档口编号
	 * @param orderType 订单类型
	 * @param stauts  订单状态 （完成支付:18,支付信息已确证:16,支付确证:14,扎帐已确定:12,已扎账:10,销售中:8,已收货:6,运输中:4,已开单:2）
	 * @param balance 是否扎帐
	 * @param payStatus 支付状态
	 * @param consignment 销售方式{1合伙,2代销}
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "getOrderPageList")
	public WebServiceResult getOrderPageList(String supplierNo, String purchaseNo, String shopNo, String orderType,
			String stauts, String balance, String payStatus, String consignment, int pageNo, int pageSize,
			HttpServletRequest request) throws ParseException {
		WebServiceResult json = new WebServiceResult();
		pageNo = (pageNo == 0) ? PAGE_NO : pageNo;
		pageSize = (pageSize == 0) ? PAGE_SIZE : pageSize;

		Map<String, Object> params = new HashMap<String, Object>();
		if (StringTools.isNotEmpty(supplierNo)) {
			params.put("supplierNo", supplierNo);
		}

		if (StringTools.isNotEmpty(purchaseNo)) {
			params.put("purchaseNo", purchaseNo);
		}
		if (StringTools.isNotEmpty(shopNo)) {
			params.put("shopNo", shopNo);
		}
		if (StringTools.isNotEmpty(orderType)) {
			params.put("orderType", orderType);
		}
		if (StringTools.isNotEmpty(stauts)) {
			params.put("stauts", stauts);
		}
		if (StringTools.isNotEmpty(balance)) {
			params.put("balance", balance);
		}
		if (StringTools.isNotEmpty(payStatus)) {
			params.put("payStatus", payStatus);
		}
		if (StringTools.isNotEmpty(consignment)) {
			params.put("consignment", consignment);
		}
		OrderEntity entity = new OrderEntity();
		
		PageInfo<OrderEntity> pages = orderService.getPageList(params, entity, pageNo, pageSize);
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

	@ResponseBody
	@RequestMapping(value = "test")
	public WebServiceResult test() {
		WebServiceResult json = new WebServiceResult();
		OrderEntity entity = new OrderEntity();
		String josnStr = JSON.toJSONString(entity, SerializerFeature.WriteMapNullValue);
		json.setDatas(josnStr);
		return json;
	}

}
