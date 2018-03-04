/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50559
Source Host           : localhost:3306
Source Database       : hlframe

Target Server Type    : MYSQL
Target Server Version : 50559
File Encoding         : 65001

Date: 2018-03-04 23:47:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) DEFAULT '' COMMENT '日志标题',
  `content` varchar(1000) DEFAULT '' COMMENT '日志内容',
  `logtype` varchar(4) DEFAULT '' COMMENT '操作方式',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(5) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_date`),
  CONSTRAINT `FK_5yqmukedkuti9qdm38cgn5qpo` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menuid` varchar(32) NOT NULL COMMENT '菜单ID',
  `name` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `type` int(5) DEFAULT NULL COMMENT '资源类型',
  `url` varchar(200) DEFAULT NULL COMMENT '点击后前往的地址',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父编号',
  `parent_ids` varchar(1000) DEFAULT NULL COMMENT '父编号列表',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限字符串',
  `isshow` tinyint(1) DEFAULT '0' COMMENT '是否显示',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `menu_icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `remarks` varchar(255) DEFAULT NULL COMMENT '摘要',
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`menuid`),
  KEY `idx_sys_resource_parent_id` (`parent_id`),
  KEY `idx_sys_resource_parent_ids` (`parent_ids`(255)),
  KEY `FK_chmy2umajqqsxs72xupaw2bp2` (`create_by`),
  KEY `FK_qpmp4gcxex623foonm8iatf2s` (`update_by`),
  CONSTRAINT `FK_chmy2umajqqsxs72xupaw2bp2` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`userid`),
  CONSTRAINT `FK_qpmp4gcxex623foonm8iatf2s` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('4028817261ec86300161ec87e7070000', '用户管理', '1', 'sys/user/userlist.html', '0', null, 'sys:user', '1', '1', '2av', '用户管理', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-03 23:42:58', null, null, null);
INSERT INTO `sys_menu` VALUES ('4028817261ec86300161ec8b18d90001', '角色管理', '1', 'sys/role/rolelist.html', '4028817261ec86300161ec87e7070000', null, 'sys:role', '1', '1', '2av', '角色管理', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-03 23:46:33', null, null, null);
INSERT INTO `sys_menu` VALUES ('4028817261ee84fb0161ee86b8d30000', '菜单管理', '1', '/sys/menu', '4028817261ec86300161ec87e7070000', null, 'sys:menu', '1', '1', null, '菜单管理', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-04 09:01:02', null, null, null);
INSERT INTO `sys_menu` VALUES ('4028817261f0a66a0161f0b6ea960000', '菜单添加', '1', 'sys/menu/add.html', '4028817261ee84fb0161ee86b8d30000', null, 'sys:menu:add', '1', '0', null, '菜单添加', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-04 19:12:52', null, null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleid` varchar(64) NOT NULL COMMENT '角色ID',
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) DEFAULT NULL COMMENT '英文名称',
  `is_sys` varchar(64) DEFAULT NULL COMMENT '是否系统数据',
  `usable` varchar(64) DEFAULT NULL COMMENT '是否可用',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`roleid`),
  KEY `sys_role_del_flag` (`del_flag`),
  KEY `sys_role_enname` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(32) NOT NULL COMMENT '编号',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单编号',
  `role_id` varchar(32) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`),
  KEY `sys_role_menu_menuid` (`menu_id`),
  KEY `sys_role_menu_roleid` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userid` varchar(64) NOT NULL COMMENT '用户ID',
  `realname` varchar(64) DEFAULT NULL COMMENT '真实名称',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `portrait` varchar(250) DEFAULT NULL COMMENT '头像',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `salt` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL COMMENT '邮件',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `status` varchar(32) DEFAULT NULL COMMENT '系统用户的状态',
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` text,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `idx_sys_user_username` (`username`),
  UNIQUE KEY `idx_sys_user_email` (`email`),
  UNIQUE KEY `idx_sys_user_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('c48e5e7c03f34d1d97f1915b22428c21', '??', 'admin', null, '91936ebdba1fd07fa6947d5f1ffcfdae', 'f247b291e710e44096e7405e024cb78b', '3042415452@qq.com', '13595029936', '1', null, '2018-02-11 22:40:46', null, null, null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `role_id` varchar(32) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`),
  KEY `sys_user_role_userid` (`user_id`),
  KEY `sys_user_role_roleid` (`role_id`),
  CONSTRAINT `FK_fethvr269t6stivlddbo5pxry` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`userid`),
  CONSTRAINT `FK_fxu3td9m5o7qov1kbdvmn0g0x` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
