package com.hy.cb.dao.seller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.cb.dao.seller.SeSellerGoodsCategoryDao;
import com.hy.cb.entity.seller.SeGoodsCategory;
import com.hy.cb.entity.seller.SeSellerGoodsCategory;
import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

/**
 * 商家商品分类管理
 * 
 * @author hejing
 *
 */
@Repository("seSellerGoodsCategoryDao")
public class SeSellerGoodsCategoryDaoImpl extends BasicDaoImpl<SeSellerGoodsCategory>
		implements SeSellerGoodsCategoryDao {

	@Override
	public Class<SeSellerGoodsCategory> getEntityClass() {
		// TODO Auto-generated method stub
		return SeSellerGoodsCategory.class;
	}

	/**
	 * 得到商家自己的分类列表
	 */
	@Override
	public PageInfo<SeGoodsCategory> getPageList(Map<String, Object> params, SeSellerGoodsCategory entity,
			int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  b.* ");
		sql.append(" FROM se_seller_goods_category a, se_goods_category b");
		sql.append(" WHERE a.category_id=b.category_id ");
		// 多个关键字查询
		if (StringTools.mapGetKeyIsEmpty(params, "queryKey")) {
			sql.append(" AND ( b.category_name like ? )");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
		}
		// 多个关键字查询
		if (StringTools.mapGetKeyIsEmpty(params, "sellerId")) {
			sql.append(" AND ( a.seller_id = ? )");
			values.add(params.get("sellerId").toString().trim());
		}
		sql.append(" ORDER BY sort DESC");
		return (PageInfo<SeGoodsCategory>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SeGoodsCategory.class);
	}

	/**
	 * 根据系统商品编号查询商家商品编号中是否存在
	 * 
	 * @param classId
	 * @return
	 */
	@Override
	public SeSellerGoodsCategory GetSellerGoodsCategoryByClassId(String classId) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT * ");
		sql.append(" FROM se_seller_goods_category");
		sql.append(" WHERE 1=1 ");

		// 多个关键字查询
		if (StringTools.isNotEmpty(classId)) {
			sql.append(" AND ( category_id = ? )");
			values.add(classId);
		}
		List<SeSellerGoodsCategory> list=(List<SeSellerGoodsCategory>) this.findListJdbc(sql.toString(), values.toArray(), SeSellerGoodsCategory.class);
		if(list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	/**
	 * 删除商家自己的商品分类
	 * 
	 * @param classId
	 * @return
	 */
	@Override
	public void delSellerCategoryByCategoryId(String categoryId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete ");
		sql.append(" FROM se_seller_goods_category");

		// 多个关键字查询
		if (StringTools.isNotEmpty(categoryId)) {
			sql.append(" WHERE ( category_id = '"+categoryId+"' )");
			this.getJdbcTemplate().execute(sql.toString());			
		}

	}
}
