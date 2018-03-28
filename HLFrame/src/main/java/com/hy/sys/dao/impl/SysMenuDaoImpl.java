package com.hy.sys.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysMenuDao;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.SysRole;
import com.hy.sys.entity.SysUser;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;
import com.hy.sys.utils.logs.LogUtil;

@Repository("sysMenuDao")
public class SysMenuDaoImpl extends BasicDaoImpl<SysMenu> implements SysMenuDao {

	@Override
	public Class<SysMenu> getEntityClass() {
		// TODO Auto-generated method stub
		return SysMenu.class;
	}

	@Override
	public SysMenu findByName(String menuname) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SysMenu ");
		sql.append(" WHERE 1=1 ");
		if (menuname != "") {
			sql.append(" AND (name = ?)");
			values.add(menuname);
		}
		sql.append(" ORDER BY create_date DESC");
		List<SysMenu> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SysMenu) list.get(0);
		} else {
			return null;
		}
	}
	/**
	 * 获取所有显示菜单内容
	 * @return
	 */
	@Override
	public List<SysMenu> getAllList(){
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SysMenu ");
		sql.append(" WHERE 1=1 ");
				 
		sql.append(" AND (del_flag = ?)"); //只查询 isshow=0的
		values.add("0");
		 		
		sql.append(" ORDER BY create_date DESC");
		List<SysMenu> list = this.findByHql(sql.toString(), values.toArray());

		return list;		
	}
	
	/**
	 * 查询该菜单下的子工菜单数量
	 * 
	 * @param menuid
	 * @return
	 */
	@Override
	public long getChildCountByMenuid(String menuid) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append("SELECT count(*) FROM SysMenu ");
		sql.append(" WHERE 1=1 ");
		if (menuid != "") {
			sql.append(" AND (parent_id = ?)");
			values.add(menuid);
		}
		sql.append(" ORDER BY create_date DESC");
		return this.getTotalCount(sql.toString(), values.toArray());

	}

	/**
	 * 删除菜单
	 * 
	 * @param funids
	 */
	@Override
	public void deleteMenu(String menuid) {
		String sql=" DELETE FROM sys_menu WHERE menuid = '"+menuid+"' ";	 
	    this.getJdbcTemplate().execute(sql.toString());
	    
		SysUser user = UserUtils.getUser();
		LogUtil.info("菜单删除：被删除菜单ID," + menuid + "删除人：" + user.getUserid());

	}
	
	
	
	
	/**
	 * 根据用户ID获取用户拥有的功能菜单
	 * @param userId
	 * @return
	 */
	@Override
	public List<SysMenu> findMenuByUserId(String userId){
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append("SELECT a ");
		sql.append(" FROM SysMenu a, SysUserRole b,SysRoleMenu c ");
		sql.append(" WHERE a.menuid=c.menu_id and b.role_id=c.role_id ");
				 
		sql.append(" AND (b.user_id = ?)");  
		values.add(userId);
		 		
		sql.append(" ORDER BY a.sort DESC");
		List<SysMenu> list = this.findByHql(sql.toString(), values.toArray());

		return list;		
	}

	/**
	 * 设置同一个父菜单下的所有菜单只能有一个是默认展开的
	 * @param parentId
	 * @return
	 */
	@Override
	public void updataMenuCurrent(String parentId) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append("UPDATE  sys_menu SET current=0");
		sql.append(" WHERE 1=1 ");
		if (parentId != ""&&!StringTools.isEmpty(parentId)) {
			sql.append(" AND (parent_id = ?)");
			values.add(parentId);
			this.getJdbcTemplate().batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, parentId);
				}

				@Override
				public int getBatchSize() {
					return 1;
				}
				
			});
		}
	}
	
	/**
	 * 查询菜单列表，带查询参数 
	 * @param params
	 * @param entity
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SysMenu> getList(Map<String, Object> params, SysMenu entity) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM sys_menu   ");
		sql.append(" WHERE 1=1 ");

		if (StringTools.mapGetKeyIsEmpty(params, "queryKey")) {
			sql.append(" AND ( name like ? OR url like ?)");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
			values.add("%" + key + "%");
		}

		sql.append(" ORDER BY create_date DESC");
		//return (List<SysMenu>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				//values.toArray(), SysRole.class);
		return (List<SysMenu>)this.findListJdbc(sql.toString(), values.toArray(), SysMenu.class);
	}
	
}
