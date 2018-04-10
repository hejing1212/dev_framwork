package com.hy.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysAreaDao;
import com.hy.sys.entity.SysArea;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.SysUser;
import com.hy.sys.entity.TreeNode;
import com.hy.sys.service.SysAreaService;
import com.hy.sys.utils.CacheUtils;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;
import com.hy.sys.utils.SysFunctions;

@Service("sysAreaService")
public class SysAreaServiceImpl extends BasicServiceImpl<SysArea> implements SysAreaService {
	public static final String AREA_CACHE = "userCache";

	@Autowired
	private SysAreaDao sysAreaDao;

	@Override
	public PageInfo<SysArea> getPageList(Map<String, Object> params, SysArea entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return sysAreaDao.getList(params, entity, pageNo, pageSize);
	}

	/**
	 * 查询带分页区域列表
	 */
	@Override
	protected BasicDao<SysArea> getBasicDao() {
		// TODO Auto-generated method stub
		return this.sysAreaDao;
	}

	/**
	 * 查询区域列表
	 * 
	 * @param params
	 * @param entity
	 * @return
	 */
	@Override
	public List<SysArea> getAreaList(Map<String, Object> params, SysArea entity) {
		return sysAreaDao.getAreaList(params, entity);
	}

	/**
	 * 查询各个省的地区子节点列表
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public List<SysArea> getAreaListByTopPareatId(String pareatId) {
		if (StringTools.isNotBlank(pareatId)) {
			List<SysArea> arealist = (List<SysArea>) CacheUtils.get(AREA_CACHE, pareatId); // 如果缓存在存在就不需要查询数据库，
			if (arealist == null) {
				arealist = sysAreaDao.getRecursionAreaListByParentId(pareatId);
				if (arealist.size() > 0) {
					CacheUtils.put(AREA_CACHE, pareatId, arealist);
				}
			}
			return arealist;
		} else {
			return null;
		}
	}

	
	/**
	 * 根据父ID获取所有区域
	 * @return
	 */
	@Override
	public List<SysArea> getAllAreaList(String pareatId){
		if(StringTools.isBlank(pareatId)) {
			pareatId="0";
		}
		List<SysArea> allArealist=new ArrayList<SysArea>(); 
		List<SysArea> arealist = sysAreaDao.getAreaListByParentId(pareatId);
		if(arealist.size()>0) {
			for(SysArea area:arealist) {
				List<SysArea> tmpArealist=getAreaListByTopPareatId(area.getId());
				allArealist.add(area);
				allArealist.addAll(tmpArealist);
			}
		}
		return allArealist;
	}
	
	/**
	 * 根据父ID获取下级地区
	 * @return
	 */
	@Override
	public List<SysArea> getAreaList(String pareatId){
		if(StringTools.isBlank(pareatId)) {
			pareatId="0";
		}
		List<SysArea> allArealist=new ArrayList<SysArea>(); 
		List<SysArea> arealist = sysAreaDao.getAreaListByParentId(pareatId);
		if(arealist.size()>0) {
			for(SysArea area:arealist) {
				List<SysArea> tmpArealist=getAreaListByTopPareatId(area.getId());
				allArealist.add(area);
				allArealist.addAll(tmpArealist);
			}
		}
		return allArealist;
	}
}
