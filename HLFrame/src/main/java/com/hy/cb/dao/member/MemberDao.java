package com.hy.cb.dao.member;

import java.util.Map;

import com.hy.cb.entity.member.SeMember;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.utils.PageInfo;

public interface MemberDao extends BasicDao<SeMember>{

	/***
	 * 获取分布列表数据
	 * @param params
	 * @param entity
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<SeMember> getPageList(Map<String, Object> params, SeMember entity, int pageNo, int pageSize);

	/**
	 * 查询手机号是否存在
	 * @param phone
	 * @return
	 */
	public SeMember findByPhone(String phone);

	/**
	 * 查询用户名是否存在
	 * @param username
	 * @return
	 */
	public SeMember findByUsername(String username);

	/**
	 * 查询邮箱是否存在
	 * @param email
	 * @return
	 */
	public SeMember findByEmail(String email);

	/**
	 * 查询检查其它用户是否使用过该手机号
	 * @param mobilephone
	 * @param userid
	 * @return
	 */
	public SeMember findByOtherPhone(String mobilephone, String userid);

}
