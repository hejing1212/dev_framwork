package com.hy.sys.utils.logs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//将定义的注解的生命周期设置在运行时期
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DeleteLog {

	/** 要执行的操作类型比如：save操作 **/  
   public SysLogOperationType operationType(); 
   /** 要执行的具体操作比如：添加用�? **/  
   public String operationName();
}
