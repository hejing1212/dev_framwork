package com.hy.sys.interceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/** 

 * @author He.Xu.Dong 
 * @Date 2016年8月3日 下午4:16:56 
 * @version 1.0 
 */
@Retention(value =RetentionPolicy.RUNTIME)
public @interface Comment {
	  String value() default "";
}
