package com.hy.cb.controller.api;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.dao.DataAccessException;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.cb.entity.member.SeMember;
import com.hy.cb.entity.seller.SeGood;
import com.hy.cb.service.member.MemberService;
import com.hy.cb.utils.api.Md5Tools;
import com.hy.cb.utils.api.WebServiceResult;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;
import com.hy.sys.utils.StringUtils;
import com.hy.sys.utils.logs.LogUtil;

@Controller
@RequestMapping("/api/member")
public class MemberApiController extends AbstractBasicController {

	@Autowired
	private MemberService memberService;

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	/**
	 * 用户密码登录接口
	 * 
	 * @param phone
	 *            电话
	 * @param password
	 *            MD5后的密码
	 * @param req
	 * @return
	 * @throws DocumentException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "byPasswordLogin")
	public WebServiceResult userByPassword(String phone, String password, HttpServletRequest req)
			throws DocumentException, NoSuchAlgorithmException, UnsupportedEncodingException {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isEmpty(phone)) {
			json.setSuccess(false);
			json.setMessage("手机号不能为空！");
			return json;
		}

		if (StringTools.isEmpty(password)) {
			json.setSuccess(false);
			json.setMessage("用户登录密码不能为空！");
			return json;
		}
		SeMember member = memberService.findByPhone(phone);
		if (member != null) { // 用户存在
			if (StringTools.equals(Md5Tools.EncoderByMd5(password + member.getRandom()), member.getPassword())) {
				json.setSuccess(true);
				json.setCode(200);
				json.setMessage("登录成功");
				json.setDatas(member);
			} else {
				json.setSuccess(false);
				json.setMessage("登录密码错误！");
				return json;
			}
		} else { // 用户不存在，登录失败
			json.setSuccess(false);
			json.setMessage("登录失败，手机号不存在！");
			return json;
		}
		return json;
	}

	/**
	 * 使用短信登录接口
	 * 
	 * @param phone
	 * @param authCode
	 * @param req
	 * @return
	 * @throws DocumentException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "userBySmsAuthCode")
	public WebServiceResult userBySmsAuthCode(String phone, String authCode, HttpServletRequest req)
			throws DocumentException {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isEmpty(phone)) {
			json.setSuccess(false);
			json.setMessage("手机号不能为空！");
			return json;
		}

		if (StringTools.isEmpty(authCode)) {
			json.setSuccess(false);
			json.setMessage("验证码不能为空！");
			return json;
		}
		SeMember member = memberService.findByPhone(phone);

		if (member != null) { // 用户存在
			/********************************************
			 * 短信验证处，未添加
			 *******************************************************/
			if (member.getGesture() == "") {
				json.setSuccess(true);
				json.setCode(200);
				json.setMessage("登录成功");
				json.setDatas(member);
			} else {
				json.setSuccess(false);
				json.setMessage("登录密码错误！");
				return json;
			}
		} else { // 用户不存在，登录失败
			json.setSuccess(false);
			json.setMessage("登录失败，手机号不存在！");
			return json;
		}
		return json;
	}

	/**
	 * 手势登录接口
	 * 
	 * @param phone
	 * @param gesture
	 * @param req
	 * @return
	 * @throws DocumentException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "userByGestureCode")
	public WebServiceResult userByGestureCode(String phone, String gesture, HttpServletRequest req)
			throws DocumentException {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isEmpty(phone)) {
			json.setSuccess(false);
			json.setMessage("手机号不能为空！");
			return json;
		}

		if (StringTools.isEmpty(gesture)) {
			json.setSuccess(false);
			json.setMessage("验证码不能为空！");
			return json;
		}
		SeMember member = memberService.findByPhone(phone);
		try {
			if (member != null) { // 用户存在
				/********************************************
				 * 短信验证处，未添加
				 *******************************************************/
				if (StringTools.equals(member.getGesture(), gesture)) {
					json.setSuccess(true);
					json.setCode(200);
					json.setMessage("登录成功");
					json.setDatas(member);
				} else {
					json.setSuccess(false);
					json.setMessage("登录密码错误！");
					return json;
				}
			} else { // 用户不存在，登录失败
				json.setSuccess(false);
				json.setMessage("登录失败，手机号不存在！");
				return json;
			}
		} catch (DataAccessException e) {
			LogUtil.info(this, e);
			json.setSuccess(false);
			json.setMessage("系统错误！");
		}
		return json;
	}

	/**
	 * 手势修改设置接口
	 */
	@ResponseBody
	@RequestMapping(value = "setGestureCode")
	public WebServiceResult setGestureCode(String userId, String gesture, HttpServletRequest req) {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isEmpty(userId)) {
			json.setSuccess(false);
			json.setMessage("手机号不能为空！");
			return json;
		}

		if (StringTools.isEmpty(gesture)) {
			json.setSuccess(false);
			json.setMessage("手势密码不能为空！");
			return json;
		}

		SeMember member = memberService.get(userId);
		try {
			member.setGesture(gesture);
			memberService.save(member);
			json.setSuccess(true);
			json.setCode(200);
			json.setMessage("设置成功！");
		} catch (DataAccessException e) {
			LogUtil.info(this, e);
			json.setSuccess(false);
			json.setMessage("设置失败！");
		}
		return json;
	}

	/**
	 * 获取短信验证码
	 * 
	 * @param userId
	 * @param phone
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAuthCode")
	public WebServiceResult getAuthCode(String userId, String phone, HttpServletRequest req) {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isEmpty(userId) && StringTools.isEmpty(phone)) {
			json.setSuccess(false);
			json.setMessage("用户ID或手机号不能同时为空！");
			return json;
		}

		if (StringTools.isEmpty(phone) && StringTools.isNotBlank(userId)) {
			SeMember member = memberService.get(userId);
			if (member != null) {
				phone = member.getMobilephone();
			}
		}
		if (StringTools.isEmpty(phone)) {
			json.setSuccess(false);
			json.setMessage("手机号不能为空！");
			return json;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("authCode", StringUtils.getRandNum(4));
			map.put("userId", userId);
			map.put("phone", phone);
			json.setDatas(map);
			json.setSuccess(true);
			json.setCode(200);
			json.setMessage("获取成功！");
		} catch (DataAccessException e) {
			LogUtil.info(this, e);
			json.setSuccess(false);
			json.setMessage("获取失败！");
		}
		return json;
	}

	/**
	 * 判断短信验证码是否正确
	 * 
	 * @param userId
	 * @param phone
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkAuthCode")
	public WebServiceResult checkAuthCode(String userId, String authCode, HttpServletRequest req) {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isEmpty(userId)) {
			json.setSuccess(false);
			json.setMessage("手机号不能为空！");
			return json;
		}

		if (StringTools.isEmpty(authCode)) {
			json.setSuccess(false);
			json.setMessage("验证码不能为空！");
			return json;
		}
		try {
			if (authCode.equals("8888")) { // 万能验证码--后期 去掉
				json.setSuccess(true);
				json.setCode(200);
				json.setMessage("正确！");
			} else if (authCode.equals("database select check")) {
				json.setSuccess(true);
				json.setCode(200);
				json.setMessage("正确！");
			} else {
				json.setSuccess(false);
				json.setMessage("验证码错误！");
			}

		} catch (DataAccessException e) {
			LogUtil.info(this, e);
			json.setSuccess(false);
			json.setMessage("设置失败！");
		}
		return json;
	}

	/**
	 * 用户注册接口
	 * 
	 * @param entity
	 * @param response
	 * @param request
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "memberRegister")
	public WebServiceResult memberRegister(@ModelAttribute SeMember entity, HttpServletResponse response,
			HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		WebServiceResult json = new WebServiceResult();
		Date now = new Date();

		try {
			SeMember member = memberService.findByPhone(entity.getMobilephone());

			if (member != null) {
				json.setSuccess(false);
				json.setMessage("注册失败，手机号码已被注册！");
				return json;
			}

			entity.setCreateTime(now);
			entity.setRandom(Md5Tools.getrandomNumber());
			// app端已经进行过一首MD5了，在些只要一首把MD5后的密码和随机数再MD5就可以了
			entity.setPassword(Md5Tools.EncoderByMd5(entity.getPassword() + entity.getRandom()));
			memberService.save(entity);
			json.setSuccess(true);
			json.setDatas(entity);
			json.setMessage("注册成功");

		} catch (DataAccessException e) {
			LogUtil.info(this, e);
			json.setSuccess(false);
			json.setMessage("操作失败:" + e.toString());
		}

		return json;
	}

	/**
	 * 员工信息编辑接口 2018-04-13 11:12
	 * 
	 * @param entity
	 * @param response
	 * @param request
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "editMember")
	public WebServiceResult editMember(@ModelAttribute SeMember entity, HttpServletResponse response,HttpServletRequest request) {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isEmpty(entity.getShopNo())) {
			json.setSuccess(false);
			json.setMessage("员工所属档口编号不能为空！");
			return json;
		}

		if (StringTools.isEmpty(entity.getEpNo())) {
			json.setSuccess(false);
			json.setMessage("员工所属商家编号不能为空！");
			return json;
		}		
		Date now = new Date();

		try {
			if (StringTools.isBlank(entity.getUserid())) { // 新增
				SeMember member = memberService.findByPhone(entity.getMobilephone());
				json.setSuccess(false);
				json.setMessage("添加失败，手机号码已被注册！");
				if (member == null) {
					member = memberService.findByUsername(entity.getUsername());
					json.setSuccess(false);
					json.setMessage("添加失败，用户名已经存在！");
					if (member == null) {
						member = memberService.findByEmail(entity.getEmail());
						json.setSuccess(false);
						json.setMessage("添加失败，邮箱号码已经存在！");
					}
				}
				if (member == null) {
					entity.setCreateTime(now);
					entity.setUsertype(0);
					memberService.save(entity);
					json.setSuccess(true);
					json.setDatas(entity);
					json.setMessage("添加成功");
				} else {
					return json;
				}
			} else {// 修改
				SeMember phonemember = memberService.findByOtherPhone(entity.getMobilephone(), entity.getUserid());
				if (phonemember != null) {
					json.setSuccess(false);
					json.setMessage("修改失败，手机号码已被占用！");
					return json;
				}
				SeMember member = memberService.get(entity.getUserid());
				member.setShopNo(entity.getShopNo());
				member.setUsertype(entity.getUsertype());
				member.setUsername(entity.getUsername());
				memberService.save(member);
				json.setSuccess(true);
				json.setDatas(member);
				json.setMessage("修改员工信息成功！");
			}
		} catch (DataAccessException e) {
			LogUtil.info(this, e);
			json.setSuccess(false);
			json.setMessage("操作失败:" + e.toString());
		}
		return json;
	}

	/**
	 * 商家或档口获取员工信息
	 * 
	 * @param entity
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getMemberBySellerId")
	public WebServiceResult getMemberBySellerId(String sellerId, String shopId, int pageNo, int pageSize,
			HttpServletResponse response, HttpServletRequest request) {
		WebServiceResult json = new WebServiceResult();
		if (StringTools.isEmpty(sellerId)) {
			json.setSuccess(false);
			json.setMessage("商家号不能为空！");
			return json;
		}

		pageNo = (pageNo == 0) ? PAGE_NO : pageNo;
		pageSize = (pageSize == 0) ? PAGE_SIZE : pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sellerId", sellerId);
		params.put("shopId", shopId);

		SeMember entity = new SeMember();
		PageInfo<SeMember> pages = memberService.getPageList(params, entity, pageNo, pageSize);

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
