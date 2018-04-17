package com.hy.cb.controller.member;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hy.cb.entity.member.SeMember;
import com.hy.cb.utils.api.Md5Tools;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.utils.IntegerTools;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;
import com.hy.sys.utils.FileUpload;

@Controller
@RequestMapping("/cb/member")
public class MemberController extends AbstractBasicController {

	@Autowired
	private com.hy.cb.service.member.MemberService memberService;

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	/**
	 * 显示用户列表
	 * 
	 * @return
	 */
	@RequestMapping("/memberList")
	public ModelAndView memberList() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	/**
	 * 显示添加模板
	 * 
	 * @return
	 */
	@RequestMapping("/addMember")
	public ModelAndView addMember() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	/**
	 * 添加数据
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@ResponseBody
	@RequestMapping("/saveMember")
	public Map<String, Object> saveMember(@ModelAttribute SeMember entity, HttpServletResponse response,
			HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Date now = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (StringTools.isBlank(entity.getUserid())) { // 新增
				SeMember member=memberService.findByPhone(entity.getMobilephone());
				map.put("code", "2");
				map.put("msg", "手机号码已被注册！");
				if(member==null) {
					member=memberService.findByUsername(entity.getUsername());
					map.put("code", "4");
					map.put("msg", "用户名已经存在！");
					if(member==null) {
						member=memberService.findByEmail(entity.getEmail());
						map.put("code", "6");
						map.put("msg", "邮箱号码已经存在！");
					}
				}
				if(member==null) {
				entity.setCreateTime(now);
				entity.setRandom(Md5Tools.getrandomNumber());
				entity.setPassword(Md5Tools.EncoderByMd5(Md5Tools.EncoderByMd5(entity.getPassword())+entity.getRandom()));
				memberService.save(entity);
				map.put("code", "1");
				map.put("msg", "添加会员信息成功");
				}
			} else {// 修改

				map.put("code", "1");
				map.put("msg", "修改会员信息成功！");
			}
		} catch (DataAccessException e) {
			map.put("code", "0");
			map.put("msg", "操作失败:" + e.toString());
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/getMemberList")
	public PageInfo<SeMember> getMemberList(@ModelAttribute SeMember entity, HttpServletResponse response,HttpServletRequest request) {
		int pageNo = (request.getParameter("page") == null) ? PAGE_NO
				: IntegerTools.parseInt(request.getParameter("page"));
		int pageSize = (request.getParameter("rows") == null) ? PAGE_SIZE
				: IntegerTools.parseInt(request.getParameter("rows"));

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("queryKey", request.getParameter("queryKey"));
		
		PageInfo<SeMember> pages = memberService.getPageList(params, entity, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		String jsonStr = JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteMapNullValue);
		writeResult(jsonStr, response);

		return pages;
	
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
	public Map<String, Object> fileUpload(@RequestParam(value="uploadFile") MultipartFile multipart,HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String, Object> map = FileUpload.upload(request);
		return map;
	}
}
