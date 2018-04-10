package com.hy.cb.service.seller.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.cb.dao.seller.SellerDao;
import com.hy.cb.entity.seller.SeSeller;
import com.hy.cb.service.seller.SellerService;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.logs.LogUtil;

@Service("sellerService")
public class SellerServiceImpl extends BasicServiceImpl<SeSeller> implements SellerService{

	@Autowired
	private SellerDao seSellerDao;
		
	@Override
	public PageInfo<SeSeller> getPageList(Map<String, Object> params, SeSeller entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return seSellerDao.getPageList(params, entity, pageNo, pageSize);
	}

	@Override
	protected BasicDao<SeSeller> getBasicDao() {
		// TODO Auto-generated method stub
		return seSellerDao;
	}
	
	/**
	 * 查询商户名是否存在
	 * @param username
	 * @return
	 */
	@Override
	public SeSeller findByName(String name) {
		return seSellerDao.findByName(name);
	}
	/**
	 * 添加商家信息
	 * @param entity
	 * @return
	 */
	@Override
	public Map<String,Object> saveSeller(SeSeller entity){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
		Date now = new Date();
		entity.setCreateTime(now);
		entity.setStatus(1);
		this.save(entity);
		map.put("code","1");
		map.put("cellerId",entity.getSeller_id());
		}catch (DataAccessException e) {
			LogUtil.info(this, e);
			map.put("code","0");
		}
		return map;
		
	}
}
