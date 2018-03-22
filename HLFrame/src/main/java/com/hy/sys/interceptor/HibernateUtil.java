package com.hy.sys.interceptor;

import java.lang.annotation.Annotation;

import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * 
 * @author He.Xu.Dong
 * @Date 2016年8月3日 上午11:07:17
 * @version 1.0
 */

public class HibernateUtil {
	/**
	 * 获得表名
	 * 
	 * @param clazz
	 *            映射到数据库的po类
	 * @return String
	 */
	public static String getTableName(Class<?> clazz) {
		Table annotation = (Table) clazz.getAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return "";
	}

	/**
	 * 获取表名注释
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getTableComment(Class<?> clazz) {
		Annotation[] classAnnotation = clazz.getAnnotations();
		for (Annotation cAnnotation : classAnnotation) {
			if (cAnnotation instanceof Comment) {
				Comment a = (Comment) cAnnotation;
				return a.value();
			}
		}
		return "";
	}

	public static boolean isRecordLog(Class<?> clazz){
		Annotation[] classAnnotation = clazz.getAnnotations();
		for (Annotation cAnnotation : classAnnotation) {
			if (cAnnotation instanceof NotLog) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 获得列名
	 * 
	 * @param clazz
	 *            映射到数据库的po类
	 * @param icol
	 *            第几列
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String getColumnName(Class clazz, String name) {

		try {
			new Exception(	"--");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
}
