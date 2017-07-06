/**
 * 
 */
package com.hy.sys.utils;

/**
 * @see 整形操作辅助类
 */
public class IntegerTools {

	/**
	 * 字符串转换为int对象
	 * 
	 * @param str
	 * @return 如果str为null或空串 返回 0
	 */
	public static int parseInt(String str) {
		if (StringTools.isEmpty(str)) {
			return 0;
		} else {
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				throw  e; 
			}
		}
	}

	/**
	 * 将obj对象转换为int
	 * 
	 * @param obj
	 * @return 如果obj为null或空串 返回 0
	 */
	public static int parseInt(Object obj) {
		String str = StringTools.objToStr(obj);
		return parseInt(str);
	}
}
