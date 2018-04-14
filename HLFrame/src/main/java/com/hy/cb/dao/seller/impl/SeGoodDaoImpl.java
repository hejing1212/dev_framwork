package com.hy.cb.dao.seller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.cb.dao.seller.SeGoodsDao;
import com.hy.cb.entity.seller.SeGood;
import com.hy.cb.entity.seller.SeGoodsCategory;
import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

@Repository("seGoods")
public class SeGoodDaoImpl extends  BasicDaoImpl<SeGood> implements SeGoodsDao{

	@Override
	public Class<SeGood> getEntityClass() {
		// TODO Auto-generated method stub
		return SeGood.class;
	}

	@Override
	public SeGood findByName(String goodsName) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SeGood ");
		sql.append(" WHERE 1=1 ");

		if (goodsName != "") {
			sql.append(" AND ( goods_name = ?)");
			values.add(goodsName);
		}
		List<SeGood> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SeGood) list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public PageInfo<SeGood> getPageList(Map<String, Object> params, SeGood entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  a.*,b.category_name ");
		sql.append(" FROM se_goods a ,se_goods_category b   ");
		sql.append(" WHERE a.category_id=b.category_id ");
        //多个关键字查询
		if(StringTools.mapGetKeyIsEmpty(params, "queryKey"))  {
			sql.append(" AND ( goods_name like ? )");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
		}	 
		if(StringTools.mapGetKeyIsEmpty(params, "categoryId"))  {
			sql.append(" AND ( category_id = ? )");
			values.add(params.get("categoryId").toString().trim());
		}	
		 
		sql.append(" ORDER BY create_date DESC");
		return (PageInfo<SeGood>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SeGood.class);
	}

}
