package com.hy.cb.dao.seller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.cb.dao.seller.SellerDao;
import com.hy.cb.entity.seller.SeSeller;
import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

@Repository("seSellerDao")
public class SeSellerDaoImpl extends BasicDaoImpl<SeSeller> implements SellerDao{

	@Override
	public Class<SeSeller> getEntityClass() {
		// TODO Auto-generated method stub
		return SeSeller.class;
	}
	
	/**
	 * 获取分布列表数据
	 */
	@Override
	public PageInfo<SeSeller> getPageList(Map<String, Object> params, SeSeller entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM se_member   ");
		sql.append(" WHERE 1=1 ");
        //多个关键字查询
		if(StringTools.mapGetKeyIsEmpty(params, "queryKey"))  {
			sql.append(" AND ( name like ? OR contacts like ? OR telephone like ? )");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
			values.add("%" + key + "%");
			values.add("%" + key + "%");
		}	 
		sql.append(" ORDER BY create_time DESC");
		return (PageInfo<SeSeller>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SeSeller.class);
	}
	
	/**
	 * 查询商户名是否存在
	 * @param username
	 * @return
	 */
	@Override
	public SeSeller findByName(String name) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SeSeller ");
		sql.append(" WHERE 1=1 ");

		if (name != "") {
			sql.append(" AND ( name = ?)");
			values.add(name);
		}
		sql.append(" ORDER BY createTime DESC ");
		List<SeSeller> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SeSeller) list.get(0);
		} else {
			return null;
		}
	}

}
