/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50559
Source Host           : localhost:3306
Source Database       : hlframe

Target Server Type    : MYSQL
Target Server Version : 50559
File Encoding         : 65001

Date: 2018-03-18 23:33:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_function`;
CREATE TABLE `sys_function` (
  `funid` varchar(32) NOT NULL,
  `fun_action` varchar(255) DEFAULT NULL COMMENT '请求方法名称',
  `name` varchar(255) DEFAULT NULL COMMENT '功能名称',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '对应菜单 ID',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限字符串',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `menu_icon` varchar(255) DEFAULT NULL COMMENT '按钮图标',
  `remarks` text COMMENT '备注',
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(2) DEFAULT NULL,
  PRIMARY KEY (`funid`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `FK_amfku6p8jqo81eim1ine28otu` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_function
-- ----------------------------
INSERT INTO `sys_function` VALUES ('402881e76228ef8d0162290506650000', 'adduser', '添加用户', '4028817261ec86300161ec87e7070000', 'sys:user:adduser', '1', '', '添加用户', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-15 17:36:57', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e76228ef8d01622905c2d40001', 'updatepasswrod', '设置密码', '4028817261ec86300161ec87e7070000', 'sys:user:password', '1', '', '添加用户说明', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-15 17:37:46', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622c5b7501622c61ee390000', 'addRole', '角色添加', '4028817261ec86300161ec8b18d90001', 'sys:role:addRole', '0', '', '角色管理下添加角色', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 09:17:18', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622c5b7501622c627c040001', 'editRole', '角色修改', '4028817261ec86300161ec8b18d90001', 'sys:role:editRole', '1', '', '角色管理中修改角色信息', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 09:17:54', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622c5b7501622c6424c10002', 'roleAuthorize', '角色授权', '4028817261ec86300161ec87e7070000', 'sys:role:Authorize', '1', '', '角色管理下授权 ', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 09:19:43', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622d827301622d852e720000', 'menuAdd', '新增', '4028817261ee84fb0161ee86b8d30000', 'sys:menu:menuAdd', '1', '', '菜单新增', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 14:35:25', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622d827301622d85b54f0001', 'menuEdit', '修改', '4028817261ee84fb0161ee86b8d30000', 'sys:menu:menuEdit', '1', '', '菜单 修改', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 14:36:00', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622d827301622d865c760002', 'deleteMenu', '删除', '4028817261ee84fb0161ee86b8d30000', 'sys:menu:deleteMenu', '1', '', '菜单删除', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 14:36:42', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622d827301622d87dd290003', 'showFunction', '功能编辑', '4028817261ee84fb0161ee86b8d30000', 'sys:menu:fun:showFunction', '1', '', '功能编辑', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 14:38:21', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622d827301622d887b410004', 'deleteMenuFun', '功能删除', '4028817261ee84fb0161ee86b8d30000', 'sys:menu:fun:deleteMenuFun', '1', '', '菜单功能删除', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 14:39:01', null, null, null);

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
  `del_flag` char(1) DEFAULT '0',
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
INSERT INTO `sys_menu` VALUES ('4028817261ec86300161ec87e7070000', '用户管理', '1', '/sys/user/userlist.html', '402881e7622c909201622c93f0ad0000', null, 'sys:user:userlist', '1', '1', '', '用户管理', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-03 23:42:58', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 10:14:26', '0');
INSERT INTO `sys_menu` VALUES ('4028817261ec86300161ec8b18d90001', '角色管理', '1', '/sys/role/roleList.html', '402881e7622c909201622c93f0ad0000', null, 'sys:role', '1', '1', '', '角色管理', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-03 23:46:33', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 10:14:39', '0');
INSERT INTO `sys_menu` VALUES ('4028817261ee84fb0161ee86b8d30000', '菜单管理', '1', '/sys/menu/menulist.html', '402881e7622c909201622c93f0ad0000', null, 'sys:menu', '1', '1', '', '菜单管理', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-04 09:01:02', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 10:13:33', '0');
INSERT INTO `sys_menu` VALUES ('402881e7622c909201622c93f0ad0000', '系统管理', '1', '#', '0', null, 'system', '1', '1', null, '系统管理模块', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 10:11:55', null, null, '0');
INSERT INTO `sys_menu` VALUES ('402881e7622d548701622d580b290000', '订单管理', '1', '/order/manage', '0', null, 'order:manage', '1', '0', null, '发货单管理', '629e91dabee14a5cb1dab230dba11e00', '2018-03-16 13:46:07', null, null, '0');
INSERT INTO `sys_menu` VALUES ('402881e7622d548701622d58d8300001', '会员管理', '1', '/member/memlist', '0', null, 'order:manage', '1', '0', null, '用户会员管理', '629e91dabee14a5cb1dab230dba11e00', '2018-03-16 13:47:00', null, null, '0');

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
INSERT INTO `sys_role` VALUES ('402881e761f40d3e0161f4c0ef890000', ' 系统管理员', 'system', '1', '1', null, '2018-03-05 14:02:20', null, null, ' 系统管理员', '0');
INSERT INTO `sys_role` VALUES ('402881e761f91b320161f92729ca0000', '超级管理员', 'basemg', '1', '1', null, '2018-03-06 10:32:28', null, null, '超级管理员', '0');
INSERT INTO `sys_role` VALUES ('402881f262223b59016222467ff90000', '测试人员', 'tester', '1', '1', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-14 10:11:08', null, null, '备注', '0');

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
  KEY `sys_role_menu_roleid` (`role_id`),
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menuid`),
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('402881726239896701623989f1240000', '4028817261ee84fb0161ee86b8d30000', '402881f262223b59016222467ff90000');
INSERT INTO `sys_role_menu` VALUES ('402881726239896701623989f2230001', '4028817261ec86300161ec8b18d90001', '402881f262223b59016222467ff90000');
INSERT INTO `sys_role_menu` VALUES ('402881726239896701623989f2290002', '4028817261ec86300161ec87e7070000', '402881f262223b59016222467ff90000');

-- ----------------------------
-- Table structure for sys_role_menu_fun
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu_fun`;
CREATE TABLE `sys_role_menu_fun` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单ID',
  `fun_id` varchar(32) DEFAULT NULL COMMENT '功能ID',
  PRIMARY KEY (`id`),
  KEY `tes` (`fun_id`),
  CONSTRAINT `tes` FOREIGN KEY (`fun_id`) REFERENCES `sys_function` (`funid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu_fun
-- ----------------------------
INSERT INTO `sys_role_menu_fun` VALUES ('40288172623989670162398a8b650003', '402881f262223b59016222467ff90000', '4028817261ee84fb0161ee86b8d30000', '402881e7622d827301622d852e720000');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172623989670162398a96440004', '402881f262223b59016222467ff90000', '4028817261ec86300161ec8b18d90001', '402881e7622c5b7501622c627c040001');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172623989670162398a9e240005', '402881f262223b59016222467ff90000', '4028817261ec86300161ec87e7070000', '402881e7622c5b7501622c6424c10002');

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
INSERT INTO `sys_user` VALUES ('629e91dabee14a5cb1dab230dba11e00', '系统管理员', 'system', null, 'e7a195df078e8f63e700582895fa1e85', '98699e28892f5e681a393b4b072ad61e', '3242@qq.com', '13656545546', '1', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-08 23:02:27', null, null, '系统管理员', null);
INSERT INTO `sys_user` VALUES ('a85c1caa86a242369ef8b735bed0b504', '何靖', 'hejing', null, 'e77e9f590d0c5332fedb77b409fe9c7f', '9c8dc7af3ed501f34ec972d4b2a4cddb', '304241452@qq.com', '13595029935', '0', null, '2018-03-06 10:33:19', null, null, '何靖', null);
INSERT INTO `sys_user` VALUES ('c48e5e7c03f34d1d97f1915b22428c21', '超级管理员', 'admin', null, '6e801742ff3b0af6df28eaa3849cc6a1', '3328b8f452cfdda10d3ad82dd58f3b10', '3042415452@qq.com', '13595029936', '1', null, '2018-02-11 22:40:46', '629e91dabee14a5cb1dab230dba11e00', '2018-03-17 11:48:49', null, null);
INSERT INTO `sys_user` VALUES ('f1e1911191094e21bb53ec61156dbede', '测试人员', 'tester', null, 'f53766dd0ba464e4f374ef488a3ecbee', '74aa1132001290895df66c6fd21eee49', '46454452@qq.com', '13595659989', '1', null, '2018-03-08 11:57:24', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-09 11:18:59', '   fwer=备注扩展信息cvvser    ', null);

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
INSERT INTO `sys_user_role` VALUES ('402881726239a9fb016239acc9210002', 'f1e1911191094e21bb53ec61156dbede', '402881f262223b59016222467ff90000');
INSERT INTO `sys_user_role` VALUES ('402881726239bbd8016239bd1ff90000', 'c48e5e7c03f34d1d97f1915b22428c21', '402881f262223b59016222467ff90000');
INSERT INTO `sys_user_role` VALUES ('402881f2621dfaa901621e005c770010', 'a85c1caa86a242369ef8b735bed0b504', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_user_role` VALUES ('402881f2621dfaa901621e005cd20011', 'a85c1caa86a242369ef8b735bed0b504', '402881e761f40d3e0161f4c0ef890000');
INSERT INTO `sys_user_role` VALUES ('402881f2621dfaa901621e02b00c0016', '629e91dabee14a5cb1dab230dba11e00', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_user_role` VALUES ('402881f2621dfaa901621e02b02d0017', '629e91dabee14a5cb1dab230dba11e00', '402881e761f40d3e0161f4c0ef890000');
