package com.hy.sys.utils;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.hy.sys.entity.SysDataDict;
import com.hy.sys.entity.SysDataDictItem;
import com.hy.sys.service.SysDictService;

@SuppressWarnings("unchecked")
public class DictUtils {

	@Autowired
	private static SysDictService dataDictService;
	public static final String DICT_CACHE = "dictCache";
	public static final String DICT_ITEM_ = "dict_item_";
	
	public static final String DICT_CACHE_LIST = "dict_cache_list";
	public static final String DICT_CACHE_LIST_KEY = "dict_cache_list";
	/**
	 * 实现对数据字典的缓存及读取
	 * @param dictCode
	 * @return
	 */
	public static Set<SysDataDictItem> get(String dictCode) {

		SysDataDict dict = (SysDataDict) CacheUtils.get(DICT_CACHE, DICT_ITEM_ + dictCode);
		if (dict == null) {
			dict = dataDictService.getDictListByDictCode(dictCode);
			if (dict != null) {
				CacheUtils.put(DICT_CACHE, DICT_ITEM_ + dictCode, dict);
				Set<SysDataDictItem> list =dict.getDataDict();
				return list;
			}
		}
		return null;

	}
	
	/**
	 * 实现对数据字典的缓存及读取
	 * @param dictCode
	 * @return
	 */
	public static List<SysDataDict> getAllList() {

		List<SysDataDict> dict = (List<SysDataDict>) CacheUtils.get(DICT_CACHE_LIST, DICT_CACHE_LIST_KEY);
		if (dict == null) {
			dict = dataDictService.getAllDictList();
			if (dict != null) {
				CacheUtils.put(DICT_CACHE_LIST, DICT_CACHE_LIST_KEY, dict);			
				return dict;
			}
		}
		return null;

	}
}
