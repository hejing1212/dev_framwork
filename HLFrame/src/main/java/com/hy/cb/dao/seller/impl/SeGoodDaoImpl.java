package com.hy.cb.dao.seller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hy.cb.dao.seller.SeGoodsDao;
import com.hy.cb.entity.seller.SeGood;
import com.hy.cb.entity.seller.SeGoodsCategory;
import com.hy.sys.core.dao.impl.BasicDaoImpl;

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

}
