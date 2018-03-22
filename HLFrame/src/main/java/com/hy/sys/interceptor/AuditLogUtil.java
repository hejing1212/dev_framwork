package com.hy.sys.interceptor;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hy.sys.entity.SysLog;
import com.hy.sys.utils.StringUtils;


/**
 * 日志保存工具
 * @author He.Xu.Dong
 * @Date 2016年10月20日 上午9:28:08
 * @version 1.0
 */
@Component("auditLogUtil")
public class AuditLogUtil {
	
	@Autowired
	private  JdbcTemplate jdbcTemplate;
	
	public  void LogIt(SysLog entity) {
		String sql = "INSERT INTO `sys_log` ("
				+ "`id`,"
				+ "`opt_data_id`,"
				+ "`opt_table_name`,"
				+ "`opt_table_comment`,"
				+ "`opt_user_id`,"
				+ "`opt_user_name`,"
				+ "`opt_type`,"
				+ "`opt_content`,"
				+ "`create_by`,"
				+ "`create_date`,"
				+ "`del_flag`"
				+ ") "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,
				StringUtils.randomUUID(),
				entity.getOpt_data_id(),
				entity.getOpt_table_name(),
				entity.getOpt_table_comment(),
				entity.getOpt_user_id(),
				entity.getOpt_user_name(),
				entity.getOpt_type(),
				entity.getOpt_content(),
				entity.getCreate_by(),
				new Date(),
				0);
		
	}
}
