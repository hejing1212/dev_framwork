package com.hy.cb.service.member;

import com.hy.cb.entity.member.SeMember;
import com.hy.sys.core.service.BasicService;

public interface MemberService extends BasicService<SeMember>{

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
	 * 查询电话号码是否存在
	 * @param phone
	 * @return
	 */
	public SeMember findByPhone(String phone);

	/**
	 * 查询检查其它用户是否使用过该手机号
	 * @param mobilephone
	 * @param userid
	 * @return
	 */
	public SeMember findByOtherPhone(String mobilephone, String userid);

}
