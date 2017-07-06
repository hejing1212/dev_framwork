/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50162
Source Host           : localhost:3306
Source Database       : dev_framework

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2017-07-06 17:48:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(64) NOT NULL COMMENT '编号',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父级编号',
  `parent_ids` text COMMENT '所有父级编号',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `href` varchar(255) DEFAULT NULL COMMENT '连接地址\r\n            ',
  `target` int(11) DEFAULT NULL COMMENT '连接目标',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标\r\n            ',
  `is_show` int(11) DEFAULT NULL COMMENT '是否在菜单中显示',
  `permission` int(11) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `org_id` varchar(64) DEFAULT NULL COMMENT '机构ID',
  `name` varchar(200) DEFAULT NULL COMMENT '角色名称',
  `enname` varchar(200) DEFAULT NULL COMMENT '英文名称',
  `role_type` int(11) DEFAULT NULL COMMENT '角色类型',
  `data_scope` int(11) DEFAULT NULL COMMENT '数据范围',
  `is_sys` int(11) DEFAULT NULL COMMENT '是否系统数据',
  `useable` int(11) DEFAULT NULL COMMENT '是否可用',
  `create_by` datetime DEFAULT NULL COMMENT '创建日期',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_by` char(10) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色管理';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(64) NOT NULL,
  `menu_id` varchar(64) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK_Reference_3` (`menu_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色－菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(200) NOT NULL COMMENT '编号',
  `org_id` varchar(64) DEFAULT NULL COMMENT ' 机构编号\r\n            ',
  `office_id` varchar(64) DEFAULT NULL COMMENT '归属部门',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登录名',
  `password` varchar(64) DEFAULT NULL COMMENT '登录密码',
  `no` varchar(64) DEFAULT NULL COMMENT '工号',
  `name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱\r\n            ',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系电话\r\n            ',
  `mobile` varchar(64) DEFAULT NULL COMMENT '手机号',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型',
  `photo` varchar(500) DEFAULT NULL COMMENT '用户头像',
  `status` int(11) DEFAULT NULL COMMENT '用户状态{0正常，1停用}',
  `login_ip` varchar(64) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `login_flag` int(11) DEFAULT NULL COMMENT '是否可登录{0可以，1不可以}',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期\r\n            ',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标记{1已删除 ，0未删除}',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户管理\r\n';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2bebcc1814b849739e7882faedee37fa', null, null, null, 'asdfdsd', '', '', 'dsd@qs', '', '', null, null, '0', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('84794fccb6dd4bd695fdf40f7dcd05e9', null, null, null, '123123', '100012', '??', '304241452@qq.com', '13595029936', '13595029936', null, null, '0', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('ea1d5ca356c746abbff8764263aaead6', null, null, null, '123123', '100012', '123123', '123123', '13595029936', '13595029936', null, null, '0', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('f0f34ae04a4742849e6615313ced8700', null, null, null, '123123', '100012', '??', '304241452@qq.com', '13595029936', '13595029936', null, null, '0', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(64) NOT NULL COMMENT '用户编号',
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_Reference_1` (`role_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
