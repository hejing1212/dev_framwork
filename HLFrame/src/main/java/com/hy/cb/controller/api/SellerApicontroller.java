package com.hy.cb.controller.api;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.cb.entity.member.SeMember;
import com.hy.cb.entity.seller.SeSeller;
import com.hy.cb.service.member.MemberService;
import com.hy.cb.service.seller.SellerService;
import com.hy.cb.utils.api.WebServiceResult;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.utils.StringTools;
import com.hy.sys.utils.logs.LogUtil;

@Controller
@RequestMapping("/api/seller")
public class SellerApicontroller extends AbstractBasicController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private MemberService memberService;

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	/**
	 * 添加商家 信息
	 * 
	 * @param entity
	 * @param userId
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "addSeller")
	public WebServiceResult addSeller(@ModelAttribute SeSeller entity, String userId, HttpServletRequest request)
			throws ParseException {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isBlank(userId)) {
			json.setSuccess(false);
			json.setMessage("用户ID 不能为空！");
			return json;
		}
		if (StringTools.isBlank(entity.getName())) {
			json.setSuccess(false);
			json.setMessage("名称不能为空！");
			return json;
		}
		Date now = new Date();
		if (StringTools.isEmpty(entity.getSeller_id())) { // 商家编号 为空则新增
			try {
				entity.setCreateTime(now);
				Map<String, Object> map = sellerService.saveSeller(entity);
				if (map.get("code").equals("1")) {
					SeMember member = memberService.get(userId);
					member.setEpNo(map.get("cellerId").toString());
					member.setUsertype(0); // 通过手机注册的人默认为老板
					memberService.save(member);
					json.setMessage("操作成功！");
					json.setSuccess(true);
					json.setCode(200);
				} else {
					json.setSuccess(false);
					json.setMessage("操作成功！");
				}
			} catch (DataAccessException e) {
				LogUtil.info(this, e);
				json.setSuccess(false);
				json.setMessage("系统错误！");
			}
		} else { // 商家编号 不为空则修改
			SeSeller seller = sellerService.get(entity.getSeller_id());
			seller.setName(entity.getName());
			seller.setContacts(entity.getContacts());
			seller.setTelephone(entity.getTelephone());
			seller.setTel(entity.getTel());
			seller.setIdNo(entity.getIdNo());
			seller.setIntroduce(entity.getIntroduce());
			seller.setLongitude(entity.getLongitude());
			seller.setLatitude(entity.getLatitude());
			seller.setPurchase(entity.getPurchase());
			seller.setWholesale(entity.getWholesale());
			seller.setRetail(entity.getRetail());
			seller.setProvince(entity.getProvince());
			seller.setCity(entity.getCity());
			seller.setRegion(entity.getRegion());
			seller.setTown(entity.getTown());
			seller.setAddress(entity.getAddress());
			sellerService.save(seller);
			
			json.setMessage("操作成功！");
			json.setSuccess(true);
			json.setCode(200);
		}
		return json;
	}

	/**
	 * 获取企业信息
	 * @param sellerId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getSellerBySellerId")
	public WebServiceResult getSellerBySellerId(String sellerId,HttpServletRequest req) {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isBlank(sellerId)) {
			json.setSuccess(false);
			json.setMessage("商家ID 不能为空！");
			return json;
		}
		SeSeller seller=sellerService.get(sellerId);
		if(seller!=null) {
		json.setMessage("操作成功！");
		json.setSuccess(true);
		json.setDatas(seller);
		json.setCode(200);
		}else {
			json.setSuccess(false);
			json.setMessage("未获取到商家信息！");
		}
		return json;
	}
	
	/**
	 * 获取企业信息
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getSellerByUserId")
	public WebServiceResult getSellerByUserId(String userId,HttpServletRequest req) {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isBlank(userId)) {
			json.setSuccess(false);
			json.setMessage("用户ID 不能为空！");
			return json;
		}
		
		SeMember member=memberService.get(userId);
		if(member!=null) {
			json.setMessage("操作成功！");
			json.setSuccess(true);
			json.setDatas(member);
			json.setCode(200);
			}else {
				json.setSuccess(false);
				json.setMessage("未获取到用户信息！");
			}
			return json;
	}
}
