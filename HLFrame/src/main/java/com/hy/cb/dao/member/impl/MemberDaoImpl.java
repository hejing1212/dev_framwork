package com.hy.cb.dao.member.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.cb.dao.member.MemberDao;
import com.hy.cb.entity.member.SeMember;
import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.entity.SysUser;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

@Repository("memberDao")
public class MemberDaoImpl extends BasicDaoImpl<SeMember>  implements  MemberDao{

	@Override
	public Class<SeMember> getEntityClass() {
		// TODO Auto-generated method stub
		return SeMember.class;
	}

	/**
	 * 获取分布列表数据
	 */
	@Override
	public PageInfo<SeMember> getPageList(Map<String, Object> params, SeMember entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM se_member   ");
		sql.append(" WHERE 1=1 ");
        //多个关键字查询
		if(StringTools.mapGetKeyIsEmpty(params, "queryKey"))  {
			sql.append(" AND ( username like ? OR mobilephone like ? OR realname like ? )");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
			values.add("%" + key + "%");
			values.add("%" + key + "%");
		}	 
		sql.append(" ORDER BY create_time DESC");
		return (PageInfo<SeMember>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SeMember.class);
	}
	
	/**
	 * 查询用户名是否存在
	 * @param username
	 * @return
	 */
	@Override
	public SeMember findByUsername(String username) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SeMember ");
		sql.append(" WHERE 1=1 ");

		if (username != "") {
			sql.append(" AND ( username = ?)");
			values.add(username);
		}
		sql.append(" ORDER BY createTime DESC ");
		List<SeMember> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SeMember) list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 查询邮箱是否存在
	 * @param email
	 * @return
	 */
	@Override
	public SeMember findByEmail(String email) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SeMember   ");
		sql.append(" WHERE 1=1 ");

		if (email != "") {
			sql.append(" AND ( email = ?)");
			values.add(email);
		}
		sql.append(" ORDER BY createTime DESC");

		List<SeMember> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SeMember) list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 查询手机号是否存在
	 * @param phone
	 * @return
	 */
	@Override
	public SeMember findByPhone(String phone) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SeMember   ");
		sql.append(" WHERE 1=1 ");

		if (phone != "") {
			sql.append(" AND ( mobilephone = ?)");
			values.add(phone);
		}
		sql.append(" ORDER BY createTime DESC");

		List<SeMember> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SeMember) list.get(0);
		} else {
			return null;
		}
	}
}
