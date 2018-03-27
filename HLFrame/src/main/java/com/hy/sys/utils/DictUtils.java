package com.hy.sys.utils;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hy.sys.entity.SysDataDict;
import com.hy.sys.entity.SysDataDictItem;
import com.hy.sys.service.SysDictService;

@SuppressWarnings("unchecked")
public class DictUtils {

	public static final String DICT_CACHE = "dictCache";
	public static final String DICT_ITEM_ = "dict_item_";

	public static final String DICT_CACHE_LIST = "dictCacheList";
	public static final String DICT_CACHE_LIST_KEY = "dictCacheListKey";

	private static SysDictService dataDictService = SpringContextHolder.getBean(SysDictService.class);

	public static void setCacheDic() {
		List<SysDataDict> dict = (List<SysDataDict>) CacheUtils.get(DICT_CACHE_LIST, DICT_CACHE_LIST_KEY);
		if (dict == null) {
			dict = dataDictService.getAllDictList();
			if (dict != null) {
				CacheUtils.put(DICT_CACHE_LIST, DICT_CACHE_LIST_KEY, dict);
			}
		}
	}

	/**
	 * 实现对数据字典的缓存及读取
	 * 
	 * @param dictCode
	 * @return
	 */
	public static Set<SysDataDictItem> get(String dictCode) {

		Set<SysDataDictItem> itemList=null;
		List<SysDataDict> dictlist = getCacheDic();
		if (dictlist != null) {
			for (SysDataDict dict : dictlist) {
				if (StringTools.equals(dict.getDictCode(), dictCode)) {
					itemList=dict.getDataDict();
				}
			}
		}
		return itemList;

	}

	/**
	 * 实现对数据字典的缓存及读取
	 * 
	 * @param dictCode
	 * @return
	 */
	public static List<SysDataDict> getCacheDic() {

		List<SysDataDict> dict = (List<SysDataDict>) CacheUtils.get(DICT_CACHE_LIST, DICT_CACHE_LIST_KEY);
		if (dict == null) {
			dict = dataDictService.getAllDictList();
			if (dict != null) {
				CacheUtils.put(DICT_CACHE_LIST, DICT_CACHE_LIST_KEY, dict);
				return dict;
			}
		}else {
			return dict;
		}
		return null;

	}
}
