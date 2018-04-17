package com.hy.cb.dao.seller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.cb.dao.seller.SeEnterpriseGoodDao;
import com.hy.cb.entity.seller.SeEnterpriseGood;
import com.hy.cb.entity.seller.SeGood;
import com.hy.cb.entity.seller.SeSeller;
import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

@Repository("seEnterpriseGood")
public class SeEnterpriseGoodDaoImpl extends BasicDaoImpl<SeEnterpriseGood> implements SeEnterpriseGoodDao{

	@Override
	public Class<SeEnterpriseGood> getEntityClass() {
		// TODO Auto-generated method stub
		return SeEnterpriseGood.class;
	}
	@Override
	public PageInfo<SeEnterpriseGood> getPageList(Map<String, Object> params, SeEnterpriseGood entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  *  ");
		sql.append(" FROM se_enterprise_goods ");
		sql.append(" WHERE 1=1 ");
        //多个关键字查询
		if(StringTools.mapGetKeyIsEmpty(params, "queryKey"))  {
			sql.append(" AND ( goods_name like ? )");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
		}	 
		if(StringTools.mapGetKeyIsEmpty(params, "sellerId"))  {
			sql.append(" AND ( supplier_no = ? )");
			values.add(params.get("sellerId").toString().trim());
		}
		if(StringTools.mapGetKeyIsEmpty(params, "shopId"))  {
			sql.append(" AND ( shop_no = ? )");
			values.add(params.get("shopId").toString().trim());
		}	
		 
		sql.append(" ORDER BY ep_create_date DESC");
		return (PageInfo<SeEnterpriseGood>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SeEnterpriseGood.class);
	}
	
	/**
	 * 查询该分类下的商品
	 * @param username
	 * @return
	 */
	@Override
	public SeEnterpriseGood findGoodsByClassId(String ClassId) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SeEnterpriseGood ");
		sql.append(" WHERE 1=1 ");

		if (ClassId != "") {
			sql.append(" AND ( classId = ?)");
			values.add(ClassId);
		}
		
		List<SeEnterpriseGood> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SeEnterpriseGood) list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 查询商家商品中除当前商品外，是否还有其它 商品存在此分类Id,
	 * @param username
	 * @return
	 */
	@Override
	public SeEnterpriseGood findGoodsByClassIdNotGoodsId(String ClassId,String goodsId) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SeEnterpriseGood ");
		sql.append(" WHERE 1=1 ");

		if (ClassId != "") {
			sql.append(" AND ( classId = ?)");
			values.add(ClassId);
		}
		if (goodsId != "") {
			sql.append(" AND ( ep_goods_id != ?)");
			values.add(goodsId);
		}
		
		List<SeEnterpriseGood> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SeEnterpriseGood) list.get(0);
		} else {
			return null;
		}
	}
}
