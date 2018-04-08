package com.hy.cb.service.member.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.cb.dao.member.MemberDao;
import com.hy.cb.entity.member.SeMember;
import com.hy.cb.service.member.MemberService;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.utils.PageInfo;

@Service("memberService")
public class MemberServiceImpl extends BasicServiceImpl<SeMember> implements MemberService{

	@Autowired
	private MemberDao memberDao;

	@Override
	public PageInfo<SeMember> getPageList(Map<String, Object> params, SeMember entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return memberDao.getPageList(params, entity, pageNo, pageSize);
	}

	@Override
	protected BasicDao<SeMember> getBasicDao() {
		// TODO Auto-generated method stub
		return this.memberDao;
	}
	
	/**
	 * 查询用户名是否存在
	 * @param username
	 * @return
	 */
	@Override
	public SeMember findByUsername(String username) {
		return memberDao.findByUsername(username);
	}
	
	/**
	 * 查询邮箱是否存在
	 * @param email
	 * @return
	 */
	@Override
	public SeMember findByEmail(String email) {
		return memberDao.findByEmail(email);
	}
	
	/**
	 * 查询电话号码是否存在
	 * @param email
	 * @return
	 */
	@Override
	public SeMember findByPhone(String phone) {
		return memberDao.findByPhone(phone);
	}
}
