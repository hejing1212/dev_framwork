package com.hy.cb.dao.seller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.cb.dao.seller.SeGoodsCategoryDao;
import com.hy.cb.entity.seller.SeGoodsCategory;
import com.hy.cb.entity.seller.SeSeller;
import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

@Repository("seGoodsCategory")
public class SeGoodsCategoryDaoImpl extends BasicDaoImpl<SeGoodsCategory>  implements SeGoodsCategoryDao{

	@Override
	public Class<SeGoodsCategory> getEntityClass() {
		// TODO Auto-generated method stub
		return SeGoodsCategory.class;
	}

	@Override
	public SeGoodsCategory findByName(String categoryName) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SeGoodsCategory ");
		sql.append(" WHERE 1=1 ");

		if (categoryName != "") {
			sql.append(" AND ( category_name = ?)");
			values.add(categoryName);
		}
		List<SeGoodsCategory> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SeGoodsCategory) list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public PageInfo<SeGoodsCategory> getPageList(Map<String, Object> params, SeGoodsCategory entity, int pageNo,
			int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM se_goods_category   ");
		sql.append(" WHERE 1=1 ");
        //多个关键字查询
		if(StringTools.mapGetKeyIsEmpty(params, "queryKey"))  {
			sql.append(" AND ( category_name like ? )");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
		}	 
		return (PageInfo<SeGoodsCategory>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SeGoodsCategory.class);
	}

}
