package com.hy.sys.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value =RetentionPolicy.RUNTIME)
public @interface Comment {
	 String value() default "";
}
