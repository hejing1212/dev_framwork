/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50559
Source Host           : localhost:3306
Source Database       : hlframe

Target Server Type    : MYSQL
Target Server Version : 50559
File Encoding         : 65001

Date: 2018-03-26 18:12:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `dict_code` varchar(100) DEFAULT NULL COMMENT '分组名称',
  `dict_name` varchar(100) DEFAULT NULL COMMENT '分组编码',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典分组';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('40288172625d097901625d0a42160001', 'sex', '姓别', '姓别', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-25 20:02:54', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-25 20:28:42', '0');
INSERT INTO `sys_dict` VALUES ('402881e762607d26016260e8dd0e0000', 'show', '是否', '内容显示状态', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 14:04:56', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 14:09:01', '0');
INSERT INTO `sys_dict` VALUES ('402881e762607d26016260eda5c40004', 'menutype', '菜单类型', '菜单类型', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 14:10:09', null, null, '0');
INSERT INTO `sys_dict` VALUES ('402881e762607d26016260f12a1c0007', 'userstatus', '用户状态', '用户状态', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 14:14:00', null, null, '0');

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `dict_code` varchar(32) DEFAULT NULL COMMENT '分组ID',
  `item_name` varchar(100) DEFAULT NULL COMMENT '键值键',
  `item_value` varchar(100) DEFAULT NULL COMMENT '值',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `remarks` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_10gvjvkb2cyfblrqy1wftalw4` (`dict_code`),
  CONSTRAINT `FK_10gvjvkb2cyfblrqy1wftalw4` FOREIGN KEY (`dict_code`) REFERENCES `sys_dict` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('40288172625dcf1001625dd040440002', '40288172625d097901625d0a42160001', '女', '0', '4', '女性', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-25 23:39:11', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 13:59:10', '0');
INSERT INTO `sys_dict_item` VALUES ('40288172625dcf1001625dd080ac0003', '40288172625d097901625d0a42160001', '男', '1', '0', '男性', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-25 23:39:27', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 13:58:55', '0');
INSERT INTO `sys_dict_item` VALUES ('402881e7625ffcfa01626009de7d0000', null, 'vvvv', 'sfd', '213123', 'asfd', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 10:01:22', null, null, '0');
INSERT INTO `sys_dict_item` VALUES ('402881e7625ffcfa01626030692e0004', null, 'sdfsdf', 'sdfvvv', '0', 'asdfsafd', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 10:43:28', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 10:44:04', '0');
INSERT INTO `sys_dict_item` VALUES ('402881e7625ffcfa0162603294b90005', null, 'ddd', 'dsf', '0', 'sdfsdfeer', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 10:45:50', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 10:46:26', '0');
INSERT INTO `sys_dict_item` VALUES ('402881e762607d26016260e9653d0001', '402881e762607d26016260e8dd0e0000', '是', '1', '0', '显示', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 14:05:31', null, null, '0');
INSERT INTO `sys_dict_item` VALUES ('402881e762607d26016260e9abcd0002', '402881e762607d26016260e8dd0e0000', '否', '0', '0', '不显示', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 14:05:49', null, null, '0');
INSERT INTO `sys_dict_item` VALUES ('402881e762607d26016260effa130005', '402881e762607d26016260eda5c40004', '模块', '1', '0', '模块无连接', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 14:12:42', null, null, '0');
INSERT INTO `sys_dict_item` VALUES ('402881e762607d26016260f02ab70006', '402881e762607d26016260eda5c40004', '菜单', '0', '0', '', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 14:12:55', null, null, '0');
INSERT INTO `sys_dict_item` VALUES ('402881e762607d26016260f157280008', '402881e762607d26016260f12a1c0007', '正常', '0', '0', '', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 14:14:12', null, null, '0');
INSERT INTO `sys_dict_item` VALUES ('402881e762607d26016260f170720009', '402881e762607d26016260f12a1c0007', '锁定', '1', '0', '', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 14:14:18', null, null, '0');

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
INSERT INTO `sys_function` VALUES ('40288172623ecb9401623ece93f70000', 'init', '显示', '40288172623e9ff301623ea228330000', 'system:set', '1', '', '系统设置', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 23:09:08', null, null, '0');
INSERT INTO `sys_function` VALUES ('40288172623ecb9401623ecf271e0001', 'init', '显示', '40288172623e9ff301623ea429290001', 'home:index', '1', '', '后台首页显示', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 23:09:46', null, null, '0');
INSERT INTO `sys_function` VALUES ('40288172624d8fdf01624d9421360001', 'init', '显示', '40288172624d8fdf01624d939b4b0000', 'sys:datadict:dictlist', '1', '', '显示', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-22 19:59:36', null, null, '0');
INSERT INTO `sys_function` VALUES ('402881e5623cea4d01623cf60fc40014', 'init', '显示', '402881e5623ca50401623ca608170000', 'sys:user:userconter', '1', '', '显示，该菜单 不显示，下级工菜单就无法显示', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 14:33:01', null, null, '0');
INSERT INTO `sys_function` VALUES ('402881e5623cea4d01623cf6dd6a0015', 'system_init', '显示', '402881e7622c909201622c93f0ad0000', 'sys:manage', '1', '', '显示，该菜单 不显示，下级工菜单就无法显示', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 14:33:54', null, null, '0');
INSERT INTO `sys_function` VALUES ('402881e5623d4e2601623d4f606d0000', 'init', '显示', '402881e7622d548701622d58d8300001', 'member:manage', '2', '', '展示会员模块', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 16:10:34', null, null, '0');
INSERT INTO `sys_function` VALUES ('402881e5623d4e2601623d5052c10001', 'init', '显示', '402881e7622d548701622d580b290000', 'Order:manage', '2', '', '订单模块显示', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 16:11:36', null, null, '0');
INSERT INTO `sys_function` VALUES ('402881e56240f534016240fc26d30001', 'init', '显示', '402881e56240f534016240fb78e20000', 'admin:systemset', '1', '', '显示', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-20 09:18:09', null, null, '0');
INSERT INTO `sys_function` VALUES ('402881e5624cccd001624d0c9e180003', 'init', '显示', '402881e5624cccd001624ceba14f0002', 'sys:log:logList', '1', '', '显示日志列表', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-22 17:31:35', null, null, '0');
INSERT INTO `sys_function` VALUES ('402881e76228ef8d0162290506650000', 'adduser', '添加用户', '4028817261ec86300161ec87e7070000', 'sys:user:adduser', '1', '', '添加用户', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-15 17:36:57', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e76228ef8d01622905c2d40001', 'updatepasswrod', '设置密码', '4028817261ec86300161ec87e7070000', 'sys:user:password', '1', '', '添加用户说明', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-15 17:37:46', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622c5b7501622c61ee390000', 'addRole', '角色添加', '4028817261ec86300161ec8b18d90001', 'sys:role:addRole', '0', '', '角色管理下添加角色', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 09:17:18', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622c5b7501622c627c040001', 'editRole', '角色修改', '4028817261ec86300161ec8b18d90001', 'sys:role:editRole', '1', '', '角色管理中修改角色信息', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 09:17:54', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622c5b7501622c6424c10002', 'roleAuthorize', '角色编辑', '4028817261ec86300161ec87e7070000', 'sys:role:addAuthorize', '1', '', '角色管理下授权 ', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-21 11:51:29', 'c48e5e7c03f34d1d97f1915b22428c21', null, null);
INSERT INTO `sys_function` VALUES ('402881e7622d827301622d852e720000', 'menuAdd', '新增', '4028817261ee84fb0161ee86b8d30000', 'sys:menu:menuAdd', '1', '', '菜单新增', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 14:35:25', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622d827301622d85b54f0001', 'menuEdit', '修改', '4028817261ee84fb0161ee86b8d30000', 'sys:menu:menuEdit', '1', '', '菜单 修改', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 14:36:00', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622d827301622d865c760002', 'deleteMenu', '删除', '4028817261ee84fb0161ee86b8d30000', 'sys:menu:deleteMenu', '1', '', '菜单删除', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 14:36:42', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622d827301622d87dd290003', 'showFunction', '功能编辑', '4028817261ee84fb0161ee86b8d30000', 'sys:menu:fun:showFunction', '1', '', '功能编辑', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 14:38:21', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e7622d827301622d887b410004', 'deleteMenuFun', '功能删除', '4028817261ee84fb0161ee86b8d30000', 'sys:menu:fun:deleteMenuFun', '1', '', '菜单功能删除', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 14:39:01', null, null, null);
INSERT INTO `sys_function` VALUES ('402881e762468d5f016246acfe5e0000', 'updatauser', '修改', '4028817261ec86300161ec87e7070000', 'sys:user:edituser', '1', '', '修改用户功能', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-21 11:49:25', null, null, '0');
INSERT INTO `sys_function` VALUES ('402881e762468d5f016246b021920001', 'roleRm', '角色移除', '4028817261ec86300161ec87e7070000', 'sys:user:userRoleRm', '2', '', '移除用户角色', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-21 11:52:50', null, null, '0');
INSERT INTO `sys_function` VALUES ('402881e762471db0016247c068870001', 'init', '显示', '402881e762471db0016247beaba10000', 'system:druid:monitor', '1', '', 'Druid监控中心', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-21 16:50:14', null, null, '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(12) DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `opt_content` text,
  `opt_data_id` varchar(128) DEFAULT NULL,
  `opt_table_comment` varchar(64) DEFAULT NULL,
  `opt_table_name` varchar(64) DEFAULT NULL,
  `opt_type` int(11) DEFAULT NULL,
  `opt_user_id` varchar(64) DEFAULT NULL,
  `opt_user_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('01f7a86704e44cdbab55f8af22c64175', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|menu_id值为：40288172624d8fdf01624d939b4b0000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c590006', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('02071eb6aba94137ab281d0b34161138', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e5623cea4d01623cf6dd6a0015|menu_id值为：402881e7622c909201622c93f0ad0000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c710011', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('023f4f8e772347c0b3d8bb1b45341d8d', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|fun_id值为：402881e56240f534016240fc26d30001|menu_id值为：402881e56240f534016240fb78e20000|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171c1d000c', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('02a9ce2916d242ffbf09a270254383cd', null, '2018-03-26 11:42:51', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 11:42:50|del_flag值为：0|dictCode值为：40288172625d20b201625d656f0f0002|itemName值为：asdf|itemValue值为：asf|remarks值为：fsafdsaf|sort值为：0|', '402881e762605ee601626066c6100000', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('0617cd200ca0482c90d806f106e8933f', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e76228ef8d01622905c2d40001|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946cb70021', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('068deed142264ccc8fa8b75f0b322372', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e5623cea4d01623cf60fc40014|menu_id值为：402881e5623ca50401623ca608170000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d61f40018', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('06e063ebdc6f4117a59945c4e41e3156', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e5623ca50401623ca608170000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d5f87000c', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('07a5aa7f0ac241b983acf12ee64b7fa7', null, '2018-03-22 19:59:36', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-22 07:59:36|del_flag值为：0|fun_action值为：init|menu_id值为：40288172624d8fdf01624d939b4b0000|name值为：显示|permission值为：sys:datadict:dictlist|remarks值为：显示|sort值为：1|', '40288172624d8fdf01624d9421360001', '', 'sys_function', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('07f34a57a3b14145b798eb634d4f80e6', null, '2018-03-26 11:43:03', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 11:43:03|del_flag值为：0|dictCode值为：40288172625d20b201625d656f0f0002|itemName值为：sfdaf|itemValue值为：asfd|remarks值为：adsfs|sort值为：0|', '402881e762605ee601626066f7c50002', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('0aa2de0a3cff445aac06c81ed436fa2a', null, '2018-03-26 12:14:30', '0', null, null, '表：sys_dict_item,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 10:14:35|del_flag值为：0|update_by值为：c48e5e7c03f34d1d97f1915b22428c21|update_date值为：2018-03-26 10:14:48|dictCode值为：40288172625d20b201625d64ecfe0001|itemName值为：vvvvv|itemValue值为：SFD|remarks值为：SDFSFD|sort值为：0|操作人：admin', '402881e7625ffcfa01626015face0002', '', 'sys_dict_item', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('0c57f36c0ad24086b7a15c6bf353bc02', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d85b54f0001|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d6283001a', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('0caa107d67e44bcb89c1adcf8b9d95c4', null, '2018-03-25 23:48:13', '0', null, null, '操作记录：|update_date由：2018-03-25 11:46:39变为：2018-03-25 11:47:40|', '40288172625dcf1001625dd009cf0001', '', 'sys_dict_item', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('0e58a864a24d4fb8a34a356d04266dcd', null, '2018-03-26 11:42:57', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 11:42:57|del_flag值为：0|dictCode值为：40288172625d20b201625d656f0f0002|itemName值为：fasfd|itemValue值为：sd|remarks值为：safsaf|sort值为：0|', '402881e762605ee601626066e1250001', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('0fe650d0fbb74d66badaf897292653b8', null, '2018-03-26 14:14:12', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 02:14:12|del_flag值为：0|dictCode值为：402881e762607d26016260f12a1c0007|itemName值为：正常|itemValue值为：0|sort值为：0|', '402881e762607d26016260f157280008', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('1198139389654917a6e717c0b94ea84c', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|menu_id值为：402881e762471db0016247beaba10000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c590008', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('123aecc32c194777933d03d2db67ec71', null, '2018-03-26 10:28:52', '0', null, null, '操作记录：|update_date由：2018-03-26 10:15:46变为：2018-03-26 10:28:42|', '402881e7625ffcfa01626016ebea0003', '', 'sys_dict_item', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('135b721c2d7f43f2aa11b15af713f31c', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e5623cea4d01623cf6dd6a0015|menu_id值为：402881e7622c909201622c93f0ad0000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d606e0012', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('13ddc34f51004f5d9db3753c0bcd8866', null, '2018-03-25 23:38:57', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 11:38:57|del_flag值为：0|dictCode值为：40288172625d097901625d09e4640000|itemName值为：asdf|itemValue值为：asdf|remarks值为：s|sort值为：0|', '40288172625dcf1001625dd009cf0001', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('151d7e83da1e495a9fdeed70be021c86', null, '2018-03-25 19:23:28', '0', null, null, '操作记录：|update_date由：2018-03-25 05:13:55变为：2018-03-25 07:23:28|menu_icon由：&#xe6fd;变为：|url由：sys/dict/dictlist.html变为：/sys/dict/dictlist.html|', '40288172624d8fdf01624d939b4b0000', '', 'sys_menu', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('189738384083442c9b0b1aadd405dd01', null, '2018-03-22 17:32:26', '0', null, null, '操作记录：|fun_id值为：402881e76228ef8d01622905c2d40001|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d64320021', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('1af6d4d978e14bb5bedf53f7b7ae29d6', null, '2018-03-26 14:12:55', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 02:12:55|del_flag值为：0|dictCode值为：402881e762607d26016260eda5c40004|itemName值为：菜单|itemValue值为：0|sort值为：0|', '402881e762607d26016260f02ab70006', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('1d1b248222e9423eb0289645181cdc01', null, '2018-03-25 19:24:19', '0', null, null, '操作记录：|update_date由：2018-03-25 07:23:28变为：2018-03-25 07:24:19|url由：/sys/dict/dictlist.html变为：/sys/dict/dictList.html|', '40288172624d8fdf01624d939b4b0000', '', 'sys_menu', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('1f07f723ef414024a8f5cb60f0dc1712', null, '2018-03-25 21:54:18', '0', null, null, '操作记录：|update_date由：2018-03-25 09:52:03变为：2018-03-25 09:53:06|', '40288172625d20b201625d6b94820003', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('20cd3c1832e4443795ac367a533fef1b', null, '2018-03-26 12:14:36', '0', null, null, '表：sys_dict,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 09:41:58|del_flag值为：0|update_by值为：c48e5e7c03f34d1d97f1915b22428c21|update_date值为：2018-03-25 09:50:08|dataDict值为：[]|dictCode值为：eeeDe|dictName值为：AAREW|remarks值为：dddffffSDFSDF|操作人：admin', '40288172625d20b201625d64ecfe0001', '', 'sys_dict', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('21c51e8613244a44baa08353ca9df099', null, '2018-03-26 10:43:28', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 10:43:28|del_flag值为：0|dictCode值为：40288172625d20b201625d6b94820003|itemName值为：sdfsdf|itemValue值为：sdf|remarks值为：asdfsafd|sort值为：0|', '402881e7625ffcfa01626030692e0004', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('25808a9a4ae54c30b582c3c5114b5dbf', null, '2018-03-26 13:59:10', '0', null, null, '操作记录：|update_date由：2018-03-26 12:03:33变为：2018-03-26 01:59:10|itemName由：sadfasdfVV变为：女|itemValue由：sadfsadf变为：0|remarks由：3erfferere变为：女性|', '40288172625dcf1001625dd040440002', '', 'sys_dict_item', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('27cac80757544568a888b3cc23025d0e', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：40288172623ecb9401623ecf271e0001|menu_id值为：40288172623e9ff301623ea429290001|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c8c0017', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('2be08ae9e80145999db9e760ac28f223', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d887b410004|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d6339001d', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('2cfad9bbe266469a94c5637f28243001', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|fun_id值为：402881e5623d4e2601623d4f606d0000|menu_id值为：402881e7622d548701622d58d8300001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d60100010', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('2ffeb2b2076d4f478d8c7fb3f97577d0', null, '2018-03-25 21:54:35', '0', null, null, '操作记录：|update_date由：2018-03-25 09:53:06变为：2018-03-25 09:54:34|', '40288172625d20b201625d6b94820003', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('340a855dc598434da369f874e43a5f84', null, '2018-03-26 10:11:13', '0', null, null, '操作记录：|update_date由：2018-03-25 11:50:33变为：2018-03-26 10:10:23|itemName由：asdf变为：vvvvvvv|', '40288172625dcf1001625dd009cf0001', '', 'sys_dict_item', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('3614eb8676284ca388fdba7413727667', null, '2018-03-22 16:55:42', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-22 04:55:30|del_flag值为：0|current值为：0|isshow值为：1|level值为：3|menu_icon值为：&#xe632;|name值为：日志管理|parentIds值为：mogami2018hejingdev,402881e7622c909201622c93f0ad0000,40288172623e9ff301623ea228330000|parent_id值为：40288172623e9ff301623ea228330000|remarks值为： 日志管理|sort值为：1|type值为：1|url值为：/sys/log/list|', '402881e5624cccd001624ceba14f0002', '', 'sys_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('3876218aae28466c8c6b7fb7ff9bfea3', null, '2018-03-22 17:32:26', '0', null, null, '操作记录：|fun_id值为：402881e7622c5b7501622c6424c10002|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d647c0022', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('3a6cc162b8ca4166ba9b18eaf5ca549b', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e5624cccd001624d0c9e180003|menu_id值为：402881e5624cccd001624ceba14f0002|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c7e0014', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('3a7424e0cb46462f837710fcc4b98268', null, '2018-03-26 12:15:10', '0', null, null, '表：sys_dict_item,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 10:15:37|del_flag值为：0|update_by值为：c48e5e7c03f34d1d97f1915b22428c21|update_date值为：2018-03-26 10:28:42|dictCode值为：40288172625d20b201625d656f0f0002|itemName值为：SDFSFDFF|itemValue值为：SDF|remarks值为：SFDASDF|sort值为：0|操作人：admin', '402881e7625ffcfa01626016ebea0003', '', 'sys_dict_item', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('3c4afb98fbf8437593314dee7ea4466a', null, '2018-03-26 14:10:09', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 02:10:09|del_flag值为：0|dictCode值为：menutype|dictName值为：菜单类型|remarks值为：菜单类型|', '402881e762607d26016260eda5c40004', '', 'sys_dict', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('3d0f1537a3e34dd3af1407fc1d3012f3', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e762471db0016247c068870001|menu_id值为：402881e762471db0016247beaba10000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d61300015', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('3efd547e9b7c4f1bb9ee6342adf5da05', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|menu_id值为：40288172623e9ff301623ea228330000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c590005', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('3f943ad3424f43ebbc59e07c397685a9', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e76228ef8d0162290506650000|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d63f00020', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('433b954c1e764aa7a3457a2872aa863a', null, '2018-03-26 14:14:18', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 02:14:18|del_flag值为：0|dictCode值为：402881e762607d26016260f12a1c0007|itemName值为：锁定|itemValue值为：1|sort值为：0|', '402881e762607d26016260f170720009', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('433d9bc294e546e98246763e1c48c0bb', null, '2018-03-26 11:59:11', '0', null, null, '表：sys_dict,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 09:49:01|del_flag值为：0|update_by值为：c48e5e7c03f34d1d97f1915b22428c21|update_date值为：2018-03-26 12:03:50|dataDict值为：[com.hy.sys.entity.SysDataDictItem@390b053a, com.hy.sys.entity.SysDataDictItem@459fe3ad]|dictCode值为：SDF|dictName值为：werewwe|remarks值为：SFSADFASDF|操作人：admin', '40288172625d20b201625d6b94820003', '', 'sys_dict', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('449c4177770543d586b040be969ccf21', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|fun_id值为：40288172623ecb9401623ecf271e0001|menu_id值为：40288172623e9ff301623ea429290001|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171c21000d', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('4705ffa2eb314feea3e175de4f1e51e6', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|fun_id值为：402881e5624cccd001624d0c9e180003|menu_id值为：402881e5624cccd001624ceba14f0002|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171c13000a', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('47de8c2235104684b9ad272c63ab8018', null, '2018-03-25 23:48:40', '0', null, null, '操作记录：|update_date由：2018-03-25 11:47:40变为：2018-03-25 11:48:33|', '40288172625dcf1001625dd009cf0001', '', 'sys_dict_item', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('4a92c05741804214a04f12f21bc4880c', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e762471db0016247c068870001|menu_id值为：402881e762471db0016247beaba10000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c820015', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('4ab02a44f9674c56845ed5beafce4aa0', null, '2018-03-25 21:45:55', '0', null, null, '操作记录：|update_date由：2018-03-25 09:44:08变为：2018-03-25 09:45:44|', '40288172625d20b201625d64ecfe0001', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('4d27f1cbf3004b5189303025acf94e05', null, '2018-03-25 21:49:47', '0', null, null, '操作记录：|update_date由：2018-03-25 09:47:44变为：2018-03-25 09:49:47|dictName由：bbbDFSDF变为：VWEREW|', '40288172625d20b201625d64ecfe0001', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('4d7672fa545e4508a1a6c7b3d1a53215', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|menu_id值为：402881e7622c909201622c93f0ad0000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c590004', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('4e9fb5d7d318413ea0abb820ddb02920', null, '2018-03-25 21:43:48', '0', null, null, '操作记录：|update_date由：2018-03-25 09:43:39变为：2018-03-25 09:43:48|remarks由：safsfDSF变为：safsfDSFSDF|', '40288172625d20b201625d646fef0000', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('522a0f8101da432cba9f07e6cf747dd7', null, '2018-03-26 00:03:33', '0', null, null, '操作记录：|update_date由：2018-03-25 11:44:44变为：2018-03-26 12:03:33|remarks由：3erff变为：3erfferere|', '40288172625dcf1001625dd040440002', '', 'sys_dict_item', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('52d856c48ed341e5a28ac0435f3657c7', null, '2018-03-25 21:51:18', '0', null, null, '操作记录：|update_date由：2018-03-25 09:48:36变为：2018-03-25 09:51:16|dictName由：VVVVV变为：VVVVVd|remarks由：vvvvvsdfsdfsdf变为：vvvvv|', '40288172625d20b201625d656f0f0002', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('533890bd21614063942c1a76ca6b7a14', null, '2018-03-25 20:02:56', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 08:02:54|del_flag值为：0|dictCode值为：VVVVV|dictName值为：VVVV|remarks值为：DDDDDDDDDDDDDDD|', '40288172625d097901625d0a42160001', '', 'sys_dict', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('54af070717aa4c4a8be37869ad2a01ee', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|menu_id值为：40288172623e9ff301623ea429290001|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c5a000a', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('5535225f2c33404496d5ed503fce13ab', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e56240f534016240fc26d30001|menu_id值为：402881e56240f534016240fb78e20000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d616a0016', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('55cc15d486d24dffa4f40f5c2a2ef196', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|menu_id值为：402881e5624cccd001624ceba14f0002|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c590007', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('5665d782f75b477d9b6fbddda375e2b7', null, '2018-03-25 21:41:58', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 09:41:58|del_flag值为：0|dictCode值为：eeee|dictName值为：bbb|remarks值为：ddd|', '40288172625d20b201625d64ecfe0001', '', 'sys_dict', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('5677eaa300bb4155bdb36593a5bbeb25', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|menu_id值为：402881e5623ca50401623ca608170000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c5a000b', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('5968cab03e9c4c2b85a22c32bcedc823', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：4028817261ec86300161ec8b18d90001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d5f88000e', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('59e4f15730cb44f6bff9de73dfb3e432', null, '2018-03-26 14:14:00', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 02:14:00|del_flag值为：0|dictCode值为：userstatus|dictName值为：用户状态|remarks值为：用户状态|', '402881e762607d26016260f12a1c0007', '', 'sys_dict', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('5e739a0a85c448a98320e480745aefcc', null, '2018-03-26 14:05:49', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 02:05:49|del_flag值为：0|dictCode值为：402881e762607d26016260e8dd0e0000|itemName值为：否|itemValue值为：0|remarks值为：不显示|sort值为：0|', '402881e762607d26016260e9abcd0002', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('60289b718fa04135b3111c88a308a1a5', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e5624cccd001624ceba14f0002|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d4ba00008', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('62af5bfe0ff8458badd01e9dfd80fcd5', null, '2018-03-25 21:41:26', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 09:41:26|del_flag值为：0|dictCode值为：asdf|dictName值为：sadf|remarks值为：saf|', '40288172625d20b201625d646fef0000', '', 'sys_dict', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('62d8d5a97d70442f826ceb4ec533523c', null, '2018-03-25 20:33:35', '0', null, null, '操作记录：|update_date由：2018-03-25 08:29:27变为：2018-03-25 08:33:35|remarks由：数据类型变为：value=\"数据类型\"|', '40288172625d097901625d09e4640000', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('63125d26d63e4b44b88fc578cad5e949', null, '2018-03-26 00:03:50', '0', null, null, '操作记录：|update_date由：2018-03-25 09:54:36变为：2018-03-26 12:03:50|dictName由：VSDFSDFf变为：werewwe|', '40288172625d20b201625d6b94820003', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('65ab3092a3ea49f2aa85ca1c021fe705', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|menu_id值为：402881e762471db0016247beaba10000|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171bd90004', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('670d6593502c4ebaa2ed964650d9ed77', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e5624cccd001624d0c9e180003|menu_id值为：402881e5624cccd001624ceba14f0002|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d60f90014', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('69b1b9773ce64483800332ea4aab6dd8', null, '2018-03-26 11:11:05', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 11:11:04|del_flag值为：0|dictCode值为：40288172625d20b201625d646fef0000|itemName值为：ddddddfff|itemValue值为：sf|remarks值为：sdsf|sort值为：0|', '402881e76260481d01626049b1250000', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('69eb2f7868a941b1bc9ad7f397736da0', null, '2018-03-26 11:11:18', '0', null, null, '操作记录：|update_by由：null变为：c48e5e7c03f34d1d97f1915b22428c21|itemValue由：sf变为：sfa|', '402881e76260481d01626049b1250000', '', 'sys_dict_item', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('6be71636ebf945a997676633264ab59e', null, '2018-03-25 23:38:22', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 11:38:21|del_flag值为：0|dictCode值为：40288172625d097901625d09e4640000|itemName值为：FFff|itemValue值为：sdfsd|remarks值为：asdfasfd|sort值为：8|', '40288172625dcf1001625dcf7ec80000', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('6c644ebe9e1244228121491ec7bc53d9', null, '2018-03-25 23:36:30', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 11:36:28|del_flag值为：0|dictCode值为：40288172625d097901625d09e4640000|itemName值为：234234|itemValue值为：32|remarks值为：erewr|sort值为：2|', '40288172625dcd8e01625dcdc9390000', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('72efd263e179424b87228e6d1383cbea', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d5f89000f', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('756dfb64cd614691837f3ffa90fe1f92', null, '2018-03-26 10:01:22', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 10:01:22|del_flag值为：0|dictCode值为：40288172625d20b201625d6be6460004|itemName值为：vvvv|itemValue值为：sfd|remarks值为：asfd|sort值为：213123|', '402881e7625ffcfa01626009de7d0000', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('765ae7e1ca7d433a98015b12da36283e', null, '2018-03-26 11:59:35', '0', null, null, '表：sys_dict,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 09:49:35|del_flag值为：0|update_by值为：c48e5e7c03f34d1d97f1915b22428c21|update_date值为：2018-03-25 09:50:45|dataDict值为：[com.hy.sys.entity.SysDataDictItem@393f695e]|dictCode值为：SDF|dictName值为：eweweFD|remarks值为：SDF|操作人：admin', '40288172625d20b201625d6be6460004', '', 'sys_dict', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('775e1a64cfb54d768dae1ac717359057', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|menu_id值为：402881e7622d548701622d580b290000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c590003', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('77646e0352e54876bbeada0bc72e5ffd', null, '2018-03-22 17:29:17', '0', null, null, '操作记录：|update_date由：2018-03-22 05:24:07变为：2018-03-22 05:27:15|menu_icon由：&#xe632;变为：|', '402881e5624cccd001624ceba14f0002', '', 'sys_menu', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('7881c4ce10a94921a4f13d7f42126040', null, '2018-03-25 23:49:00', '0', null, null, '操作记录：|update_date由：2018-03-25 11:48:33变为：2018-03-25 11:49:00|', '40288172625dcf1001625dd009cf0001', '', 'sys_dict_item', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('7960a9b7a29d4268b1e41a81c0a3fab8', null, '2018-03-26 12:14:57', '0', null, null, '表：sys_dict_item,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 10:08:03|del_flag值为：0|dictCode值为：40288172625d20b201625d646fef0000|itemName值为：模压 |itemValue值为：sdf|remarks值为：saf|sort值为：4|操作人：admin', '402881e7625ffcfa0162600ffde10001', '', 'sys_dict_item', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('7a94d44b06b24e49a28ddd0db6851de2', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d85b54f0001|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c98001a', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('7aec26f44d1243ba80aeb1bddbf8a246', null, '2018-03-25 21:47:47', '0', null, null, '操作记录：|update_date由：2018-03-25 09:45:44变为：2018-03-25 09:47:44|dictCode由：eeee变为：eeeDe|dictName由：bbb变为：bbbDFSDF|remarks由：dddffff变为：dddffffSDFSDF|', '40288172625d20b201625d64ecfe0001', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('7b03d02b993847a6b3471cac264f38b5', null, '2018-03-26 14:08:53', '0', null, null, '表：sys_dict,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 02:06:54|del_flag值为：0|dataDict值为：[]|dictCode值为：isyes|dictName值为：是否|remarks值为：状态|操作人：admin', '402881e762607d26016260eaabf30003', '', 'sys_dict', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('7b689f93b0694382a75a027c664090d5', null, '2018-03-26 14:12:42', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 02:12:42|del_flag值为：0|dictCode值为：402881e762607d26016260eda5c40004|itemName值为：模块|itemValue值为：1|remarks值为：模块无连接|sort值为：0|', '402881e762607d26016260effa130005', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('7db11b8ce70b4755b5ab067602a40560', null, '2018-03-25 21:48:12', '0', null, null, '操作记录：|update_date由：2018-03-25 09:43:04变为：2018-03-25 09:48:10|dictCode由：vv变为：vvsdfsdf|dictName由：ewewfw变为：ewewfwvvsdfs|remarks由：vvvvv变为：vvvvvsdfsdf|', '40288172625d20b201625d656f0f0002', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('7eb28780f8a143b3876e29d27bbc15fe', null, '2018-03-26 12:14:55', '0', null, null, '表：sys_dict_item,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 11:11:04|del_flag值为：0|update_by值为：c48e5e7c03f34d1d97f1915b22428c21|update_date值为：2018-03-26 11:11:11|dictCode值为：40288172625d20b201625d646fef0000|itemName值为：ddddddfff|itemValue值为：sfa|remarks值为：sdsf|sort值为：0|操作人：admin', '402881e76260481d01626049b1250000', '', 'sys_dict_item', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('7f1b76c811d3451b866d0276dd6b85cf', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|menu_id值为：402881e56240f534016240fb78e20000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c590009', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('7fd08bcf3b1d4cd398faca187d99e2e3', null, '2018-03-26 14:06:54', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 02:06:54|del_flag值为：0|dictCode值为：isyes|dictName值为：是否|remarks值为：状态|', '402881e762607d26016260eaabf30003', '', 'sys_dict', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('8065feae3adf4c84b1f080b086f598f6', null, '2018-03-25 21:50:54', '0', null, null, '操作记录：|update_date由：2018-03-25 09:50:15变为：2018-03-25 09:50:45|dictName由：ewewe变为：eweweFD|', '40288172625d20b201625d6be6460004', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('81017ab60a1149b498ccaaa8feb6dcee', null, '2018-03-26 10:15:37', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 10:15:37|del_flag值为：0|dictCode值为：40288172625d20b201625d656f0f0002|itemName值为：SDFSFD|itemValue值为：SDF|remarks值为：SFDASDF|sort值为：0|', '402881e7625ffcfa01626016ebea0003', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('8449be562cd045b69676b0731640b186', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e7622c5b7501622c61ee390000|menu_id值为：4028817261ec86300161ec8b18d90001|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946ca9001e', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('847205bebb4141db9385d50299be6f83', null, '2018-03-25 23:39:11', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 11:39:11|del_flag值为：0|dictCode值为：40288172625d097901625d0a42160001|itemName值为：sadfasdf|itemValue值为：sadfsadf|remarks值为：3erff|sort值为：4|', '40288172625dcf1001625dd040440002', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('854b653ff8604b74acc730be54d8912e', null, '2018-03-26 10:14:23', '0', null, null, '操作记录：|update_date由：2018-03-26 10:09:34变为：2018-03-26 10:14:18|remarks由：asdfasfd变为：asdfasfdD|', '40288172625dcf1001625dcf7ec80000', '', 'sys_dict_item', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('85dd4043467546e9994cbc38f3b0808b', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e56240f534016240fb78e20000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d5f68000a', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('8701c6ef3f6143229139b44b21bb9f59', null, '2018-03-22 17:59:32', '0', null, null, '操作记录：|update_by由：629e91dabee14a5cb1dab230dba11e00变为：c48e5e7c03f34d1d97f1915b22428c21|update_date由：2018-03-17 11:48:49变为：2018-03-22 05:59:32|portrait由：null变为：/upload/2018-03-22/f15cdc58-a788-4caf-bdde-909a196ef659.png|remarks由：null变为：  |', 'c48e5e7c03f34d1d97f1915b22428c21', '', 'sys_user', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('8a410374b04b4bc796afdfba9aed5b8c', null, '2018-03-26 11:01:36', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 11:01:30|del_flag值为：0|email值为：304245@qq.com|password值为：e515cd52db60ad261130204f5cf7f9d2|phone值为：123123|portrait值为：/upload/2018-03-26/36db187e-d32a-44f7-9c26-2bea665def39.png|realname值为：sf|remarks值为：sdfsf|salt值为：0c4d7acec5292f1376a4e853442112f1|status值为：1|username值为：testers|', '9771b4e5205c4601aaec8ff078b499c5', '', 'sys_user', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('8cc805d71582486b8eab9a42d7d5146b', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|menu_id值为：40288172623e9ff301623ea228330000|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171bd80001', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('8d086520f30142d7a395f2c5b770e129', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：40288172624d8fdf01624d9421360001|menu_id值为：40288172624d8fdf01624d939b4b0000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c790013', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('8e2b89423a8f48089c2eb381404b921a', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c5a000e', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('90a9f94ff3104a0cabddd978f89f9c22', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|menu_id值为：40288172624d8fdf01624d939b4b0000|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171bd80002', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('90b984e115394be3aca884fc0b2377ac', null, '2018-03-26 12:15:12', '0', null, null, '表：sys_dict,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 09:42:31|del_flag值为：0|update_by值为：c48e5e7c03f34d1d97f1915b22428c21|update_date值为：2018-03-25 09:51:16|dataDict值为：[]|dictCode值为：vvsdfsdfsdf|dictName值为：VVVVVd|remarks值为：vvvvv|操作人：admin', '40288172625d20b201625d656f0f0002', '', 'sys_dict', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('919efa5d047d40a8a2d3d95f29038a65', null, '2018-03-25 21:48:39', '0', null, null, '操作记录：|update_date由：2018-03-25 09:48:10变为：2018-03-25 09:48:36|dictCode由：vvsdfsdf变为：vvsdfsdfsdf|dictName由：ewewfwvvsdfs变为：VVVVV|remarks由：vvvvvsdfsdf变为：vvvvvsdfsdfsdf|', '40288172625d20b201625d656f0f0002', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('92b41de44c2f4687bb8dcb02c100f34b', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e762468d5f016246acfe5e0000|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946cc00023', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('92c9788de51e4829bea628bc7e88a49f', null, '2018-03-25 20:29:27', '0', null, null, '操作记录：|update_date由：2018-03-25 08:21:57变为：2018-03-25 08:29:27|dictCode由：SDFASDF变为：datatype|dictName由：SDF变为：数据类型|remarks由：DDDDDDDDD变为：数据类型|', '40288172625d097901625d09e4640000', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('946802286d9844d4b394a4b6087ba7ff', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d87dd290003|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d62f9001c', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('94d1eb1123c74649a6a6f504703cb0c2', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|menu_id值为：402881e56240f534016240fb78e20000|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171bd90005', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('950c6946cc224d1aa8de3970bf2d8e46', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|menu_id值为：402881e5624cccd001624ceba14f0002|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171bd80003', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('9542b6a6a0bf4b48befd2eab603c66de', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|menu_id值为：40288172623e9ff301623ea429290001|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171bd90006', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('95add3b77785463d9b24076235dfe39a', null, '2018-03-26 00:03:40', '0', null, null, '操作记录：|update_date由：2018-03-25 09:43:48变为：2018-03-26 12:03:40|dictName由：AAAs变为：AAAsaaaaa|', '40288172625d20b201625d646fef0000', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('97521397a0db4bf49a813c79fc3c65db', null, '2018-03-22 17:31:40', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-22 05:31:35|del_flag值为：0|fun_action值为：init|menu_id值为：402881e5624cccd001624ceba14f0002|name值为：显示|permission值为：sys:log:logList|remarks值为：显示日志列表|sort值为：1|', '402881e5624cccd001624d0c9e180003', '', 'sys_function', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('97bb9048396b46339ead0858e5c8dc81', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|menu_id值为：4028817261ec86300161ec8b18d90001|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c5a000d', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('994f35d58ddc44e9964da5413f230d49', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|fun_id值为：402881e762471db0016247c068870001|menu_id值为：402881e762471db0016247beaba10000|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171c18000b', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('9aa6263b80284d41920f1c6e6a90fa13', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d5f87000d', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('9abd1a21473949c99bad800e590c511c', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d852e720000|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d62300019', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('9dd2a6c75bee471ebd64bd6730553072', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：40288172623e9ff301623ea228330000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d47140007', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('9e4de819bff14ac4995abc949b87c275', null, '2018-03-26 12:15:06', '0', null, null, '表：sys_dict_item,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 11:42:50|del_flag值为：0|dictCode值为：40288172625d20b201625d656f0f0002|itemName值为：asdf|itemValue值为：asf|remarks值为：fsafdsaf|sort值为：0|操作人：admin', '402881e762605ee601626066c6100000', '', 'sys_dict_item', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('9fcfad711de84a71b2cd5bace5281733', null, '2018-03-26 12:15:07', '0', null, null, '表：sys_dict_item,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 11:42:57|del_flag值为：0|dictCode值为：40288172625d20b201625d656f0f0002|itemName值为：fasfd|itemValue值为：sd|remarks值为：safsaf|sort值为：0|操作人：admin', '402881e762605ee601626066e1250001', '', 'sys_dict_item', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('a162f7d8e9b74cd49e552901aa2dbc2a', null, '2018-03-25 21:54:37', '0', null, null, '操作记录：|update_date由：2018-03-25 09:54:34变为：2018-03-25 09:54:36|', '40288172625d20b201625d6b94820003', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('a3d9d761f30b48bd8dab05626ae0ee85', null, '2018-03-25 21:42:31', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 09:42:31|del_flag值为：0|dictCode值为：vv|dictName值为：vvv|remarks值为：vvvvv|', '40288172625d20b201625d656f0f0002', '', 'sys_dict', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('a40d6db0e53347df90a18d6a320e94bf', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e76228ef8d0162290506650000|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946cb30020', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('a4e2af3485a0452f91c0cfec85edff68', null, '2018-03-25 21:49:14', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 09:49:01|del_flag值为：0|dictCode值为：SDF|dictName值为：VSDFSDFf|remarks值为：SF|', '40288172625d20b201625d6b94820003', '', 'sys_dict', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('a5e2c86a639d491abf91a1bea8299c71', null, '2018-03-25 20:34:50', '0', null, null, '操作记录：|update_date由：2018-03-25 08:33:35变为：2018-03-25 08:34:50|remarks由：value=\"数据类型\"变为：数据类型|', '40288172625d097901625d09e4640000', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('a867d2ec95c84f7ab8703bb907a5bc2f', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：40288172623ecb9401623ece93f70000|menu_id值为：40288172623e9ff301623ea228330000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c750012', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('a8ef466b9a24426d8e3064e726e67d77', null, '2018-03-25 21:50:08', '0', null, null, '操作记录：|update_date由：2018-03-25 09:49:47变为：2018-03-25 09:50:08|dictName由：VWEREW变为：AAREW|', '40288172625d20b201625d64ecfe0001', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('a921c7db301f403e82a3fbb936de160e', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d887b410004|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946ca5001d', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('aaa72ff8a4b847e9bf3eef0678f5764e', null, '2018-03-25 20:02:32', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 08:02:16|del_flag值为：0|dictCode值为：asdf|dictName值为：sadfsadf|remarks值为：asdfsadfasdf|', '40288172625d097901625d09e4640000', '', 'sys_dict', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('b2d743b3e2ad4977a901c75835cb6757', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e762471db0016247beaba10000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d4e380009', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('b342cd115786408db14c5d1f57b4dc88', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|fun_id值为：402881e5623cea4d01623cf6dd6a0015|menu_id值为：402881e7622c909201622c93f0ad0000|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171c030007', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('b46d55e4442a4ab4b7d4ae0ca908c539', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e7622c5b7501622c627c040001|menu_id值为：4028817261ec86300161ec8b18d90001|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946cae001f', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('b8fe470e55f347ffb93f31d583517374', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e7622d548701622d580b290000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0cff160005', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('bc04a417b1a14e55805124fa595b37bb', null, '2018-03-26 10:08:03', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 10:08:03|del_flag值为：0|dictCode值为：40288172625d20b201625d646fef0000|itemName值为：模压 |itemValue值为：sdf|remarks值为：saf|sort值为：4|', '402881e7625ffcfa0162600ffde10001', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('bc0b41a80bb54189ba492b5ee81d90e9', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|menu_id值为：402881e7622c909201622c93f0ad0000|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171bc90000', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('bcb4ae4bec3a416ebbfa317f45f3959e', null, '2018-03-26 10:14:35', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 10:14:35|del_flag值为：0|dictCode值为：40288172625d20b201625d64ecfe0001|itemName值为：SDFSFD|itemValue值为：SFD|remarks值为：SDFSFD|sort值为：0|', '402881e7625ffcfa01626015face0002', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('bcbaa9f36e214826ba4e62c3a2e88b73', null, '2018-03-22 19:59:02', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-22 07:59:01|del_flag值为：0|current值为：0|isshow值为：1|level值为：3|menu_icon值为：&#xe6fd;|name值为：数据字典|parentIds值为：mogami2018hejingdev,402881e7622c909201622c93f0ad0000,40288172623e9ff301623ea228330000|parent_id值为：40288172623e9ff301623ea228330000|remarks值为：数据字典|sort值为：1|type值为：1|url值为：sys/datadict/dictList|', '40288172624d8fdf01624d939b4b0000', '', 'sys_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('bd003984ae494fb79ce62e40f6b9d2cf', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d865c760002|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c9d001b', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('bd4740a1907440b39496f3c742084e23', null, '2018-03-26 11:55:23', '0', null, null, '表：sys_dict_item,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 11:38:57|del_flag值为：0|update_by值为：c48e5e7c03f34d1d97f1915b22428c21|update_date值为：2018-03-26 10:10:23|dictCode值为：40288172625d097901625d09e4640000|itemName值为：vvvvvvv|itemValue值为：asdf|remarks值为：ssdfsdfsdf|sort值为：0|操作人：admin', '40288172625dcf1001625dd009cf0001', '', 'sys_dict_item', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('be3e16ddaf8a4571af2e6a7a4032a51d', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e7622c909201622c93f0ad0000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d3d730006', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('c0e2f53f20f8435caaf98d47b575ce44', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|fun_id值为：40288172623ecb9401623ece93f70000|menu_id值为：40288172623e9ff301623ea228330000|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171c080008', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('c19fcf4e267a4b9cb5891771bd72364a', null, '2018-03-25 20:37:39', '0', null, null, '操作记录：|update_date由：2018-03-25 08:34:50变为：2018-03-25 08:37:39|', '40288172625d097901625d09e4640000', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('c2f6db4c7e5d48e9aef40209b4212a2a', null, '2018-03-25 10:57:43', '0', null, null, '操作记录：|fun_id值为：40288172624d8fdf01624d9421360001|menu_id值为：40288172624d8fdf01624d939b4b0000|role_id值为：402881e761f40d3e0161f4c0ef890000|', '40288172625b13e001625b171c0d0009', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('c5584133b3184f87b3348e5c829b4e7c', null, '2018-03-25 21:42:48', '0', null, null, '操作记录：|update_date由：2018-03-25 09:41:46变为：2018-03-25 09:42:48|dictName由：AAA变为：AAAs|', '40288172625d20b201625d646fef0000', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('c741e3e1497a46388542bccb62dde8ba', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e7622c5b7501622c6424c10002|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946cbb0022', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('ca7211e632cf4b5fa75267bca7727c16', null, '2018-03-22 17:30:23', '0', null, null, '操作记录：|update_date由：2018-03-22 05:27:15变为：2018-03-22 05:30:01|menu_icon由：变为：&#xe632;|', '402881e5624cccd001624ceba14f0002', '', 'sys_menu', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('ca8dfe1960f945f28f0a7f55d1bfb37e', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e5623d4e2601623d5052c10001|menu_id值为：402881e7622d548701622d580b290000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d602c0011', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('cb1b24f443a04ba1ad3c3949432726df', null, '2018-03-25 23:50:34', '0', null, null, '操作记录：|update_date由：2018-03-25 11:49:00变为：2018-03-25 11:50:33|remarks由：s变为：ssdfsdfsdf|', '40288172625dcf1001625dd009cf0001', '', 'sys_dict_item', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('cc5d5aff7a0b44dbbd066b622717581d', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d852e720000|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c940019', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('cf1b200c286241cf896b408219821e80', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e762468d5f016246b021920001|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946cc40024', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('d7207217357a4ecc83f7b19258f63fee', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622c5b7501622c61ee390000|menu_id值为：4028817261ec86300161ec8b18d90001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d637d001e', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('d861fce15cfe447f890999903df419eb', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622c5b7501622c627c040001|menu_id值为：4028817261ec86300161ec8b18d90001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d63b3001f', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('d89d6e3672a540bd9af7ad4cedabb695', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e5623cea4d01623cf60fc40014|menu_id值为：402881e5623ca50401623ca608170000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c900018', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('d9d51d9de8824dae9c98f98bca6e5cb5', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e7622d548701622d58d8300001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0ce5b20004', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('dbec98dfe3784178b51f721861f0dff9', null, '2018-03-26 12:15:08', '0', null, null, '表：sys_dict_item,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 11:43:03|del_flag值为：0|dictCode值为：40288172625d20b201625d656f0f0002|itemName值为：sfdaf|itemValue值为：asfd|remarks值为：adsfs|sort值为：0|操作人：admin', '402881e762605ee601626066f7c50002', '', 'sys_dict_item', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('dbf7870270b7427b85fac328dc187bf0', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e5623d4e2601623d4f606d0000|menu_id值为：402881e7622d548701622d58d8300001|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c6a000f', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('dee6e749697b477997ffc7f46940f44c', null, '2018-03-25 21:49:35', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 09:49:35|del_flag值为：0|dictCode值为：SDF|dictName值为：VSDF|remarks值为：SDF|', '40288172625d20b201625d6be6460004', '', 'sys_dict', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('e089e38f3ac4454b8dcf77f9a3fc27a8', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e56240f534016240fc26d30001|menu_id值为：402881e56240f534016240fb78e20000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c870016', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('e0adf89d4ddd470cbe4314f6dd0bbfae', null, '2018-03-26 13:58:55', '0', null, null, '操作记录：|update_date由：2018-03-26 12:03:26变为：2018-03-26 01:58:55|itemName由：sdf变为：男|itemValue由：VVVV变为：1|remarks由：dsafsafd变为：男性|', '40288172625dcf1001625dd080ac0003', '', 'sys_dict_item', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('e0cc1b506dd044bea299da8428149e39', null, '2018-03-22 17:32:26', '0', null, null, '操作记录：|fun_id值为：402881e762468d5f016246b021920001|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d651b0024', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('e23df4690fb14daa98e4990fbeab2060', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c5a000c', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('e271231460e94459bdc6b83a15b5eb1e', null, '2018-03-25 20:38:37', '0', null, null, '操作记录：|update_date由：2018-03-25 08:37:39变为：2018-03-25 08:38:37|remarks由：数据类型变为：数据类型1|', '40288172625d097901625d09e4640000', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('e5b081e4e86a4959a485541f663fcf9b', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：40288172623ecb9401623ece93f70000|menu_id值为：40288172623e9ff301623ea228330000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d60ae0013', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('e9d79d0190834cd789780d43f2637d3f', null, '2018-03-26 12:11:51', '0', null, null, '表：sys_dict,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 08:02:16|del_flag值为：0|update_by值为：c48e5e7c03f34d1d97f1915b22428c21|update_date值为：2018-03-25 08:38:37|dataDict值为：[]|dictCode值为：datatype|dictName值为：数据类型|remarks值为：数据类型1|操作人：admin', '40288172625d097901625d09e4640000', '', 'sys_dict', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('e9ef9922b9d64f78a8f79b8ef442f491', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：40288172623ecb9401623ecf271e0001|menu_id值为：40288172623e9ff301623ea429290001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d61ac0017', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('ea258a39104e47ddb2d0633a95fa1547', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e5623d4e2601623d5052c10001|menu_id值为：402881e7622d548701622d580b290000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c6d0010', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('eaa082e4c81a479cb2970430a08c8fb1', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|menu_id值为：402881e7622d548701622d58d8300001|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946c580002', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('ef53ca80bfa141e59b98c1acd104d841', null, '2018-03-26 10:45:50', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 10:45:50|del_flag值为：0|dictCode值为：40288172625d20b201625d6b94820003|itemName值为：ddd|itemValue值为：dsf|remarks值为：sdfsdf|sort值为：0|', '402881e7625ffcfa0162603294b90005', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('f1dc671acf344486b36c3e85e2365b8c', null, '2018-03-26 14:04:56', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 02:04:56|del_flag值为：0|dictCode值为：show|dictName值为：显示|remarks值为：内容显示状态|', '402881e762607d26016260e8dd0e0000', '', 'sys_dict', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('f290b668dd634849915185be8b30afa1', null, '2018-03-22 19:59:55', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d87dd290003|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '40288172624d8fdf01624d946ca1001c', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('f420910272754fbba3078505fd6b66b1', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d865c760002|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d62c3001b', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('f4cdeaf88a38448e84511a2b92c6d22e', null, '2018-03-26 14:09:01', '0', null, null, '操作记录：|update_by由：null变为：c48e5e7c03f34d1d97f1915b22428c21|update_date由：变为：2018-03-26 02:09:01|dictName由：显示变为：是否|', '402881e762607d26016260e8dd0e0000', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('f620b6cc30b744d092d76920f74e09f3', null, '2018-03-25 23:39:28', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 11:39:27|del_flag值为：0|dictCode值为：40288172625d097901625d0a42160001|itemName值为：sdf|itemValue值为：sf|remarks值为：dsafsafd|sort值为：0|', '40288172625dcf1001625dd080ac0003', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('f66683033ac942989f277ef49232c2a8', null, '2018-03-26 12:14:59', '0', null, null, '表：sys_dict,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 09:41:26|del_flag值为：0|update_by值为：c48e5e7c03f34d1d97f1915b22428c21|update_date值为：2018-03-26 12:03:40|dataDict值为：[]|dictCode值为：asdf|dictName值为：AAAsaaaaa|remarks值为：safsfDSFSDF|操作人：admin', '40288172625d20b201625d646fef0000', '', 'sys_dict', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('f7124ded0f074d24b310c6fbb85a7e09', null, '2018-03-26 11:55:08', '0', null, null, '表：sys_dict_item,表名：操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 11:38:21|del_flag值为：0|update_by值为：c48e5e7c03f34d1d97f1915b22428c21|update_date值为：2018-03-26 10:14:18|dictCode值为：40288172625d097901625d09e4640000|itemName值为：DDDDDDDDD|itemValue值为：sdfsd|remarks值为：asdfasfdD|sort值为：8|操作人：admin', '40288172625dcf1001625dcf7ec80000', '', 'sys_dict_item', '4', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('f765e8dea80a4bc2a8861fb20c79a816', null, '2018-03-22 17:32:26', '0', null, null, '操作记录：|fun_id值为：402881e762468d5f016246acfe5e0000|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d64bc0023', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('f7f79015bee540b88922cb98dcc70e29', null, '2018-03-25 21:43:39', '0', null, null, '操作记录：|update_date由：2018-03-25 09:42:48变为：2018-03-25 09:43:39|remarks由：safsf变为：safsfDSF|', '40288172625d20b201625d646fef0000', '', 'sys_dict', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('fa5a9bfde9ca48d8a32916d78739d7ef', null, '2018-03-25 23:37:19', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-25 11:37:18|del_flag值为：0|dictCode值为：40288172625d097901625d09e4640000|itemName值为：dfg|itemValue值为：asdf|remarks值为：asdf|sort值为：0|', '40288172625dcd8e01625dce884b0001', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('fb5265d0f3064d3599d862be7540d6de', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：40288172623e9ff301623ea429290001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d5f69000b', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('fba5e51ba2054b8fb2a1d96eee836bbb', null, '2018-03-26 14:05:31', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-26 02:05:31|del_flag值为：0|dictCode值为：402881e762607d26016260e8dd0e0000|itemName值为：是|itemValue值为：1|remarks值为：显示|sort值为：0|', '402881e762607d26016260e9653d0001', '', 'sys_dict_item', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');

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
  `level` int(10) DEFAULT NULL COMMENT '菜单级数',
  `isshow` int(1) DEFAULT '0' COMMENT '是否显示',
  `current` int(11) DEFAULT NULL COMMENT '默认打开，一个节点只能有一个打开0:no 1:show',
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
INSERT INTO `sys_menu` VALUES ('4028817261ec86300161ec87e7070000', '用户管理', '1', '/sys/user/userlist.html', '402881e5623ca50401623ca608170000', 'mogami2018hejingdev,402881e7622c909201622c93f0ad0000,402881e5623ca50401623ca608170000', '3', '1', '0', '1', '', '用户管理', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-03 23:42:58', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-20 10:17:49', '0');
INSERT INTO `sys_menu` VALUES ('4028817261ec86300161ec8b18d90001', '角色管理', '1', '/sys/role/roleList.html', '402881e5623ca50401623ca608170000', 'mogami2018hejingdev,402881e7622c909201622c93f0ad0000,402881e5623ca50401623ca608170000', '3', '1', '0', '1', '&#xe60d;', '角色管理', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-03 23:46:33', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 22:37:36', '0');
INSERT INTO `sys_menu` VALUES ('4028817261ee84fb0161ee86b8d30000', '菜单管理', '1', '/sys/menu/menulist.html', '402881e5623ca50401623ca608170000', 'mogami2018hejingdev,402881e7622c909201622c93f0ad0000,402881e5623ca50401623ca608170000', '3', '1', '0', '1', '&#xe60d;', '菜单管理', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-04 09:01:02', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 23:20:11', '0');
INSERT INTO `sys_menu` VALUES ('40288172623e9ff301623ea228330000', '系统设置', '1', '#', '402881e7622c909201622c93f0ad0000', 'mogami2018hejingdev,402881e7622c909201622c93f0ad0000', '2', '1', '1', '1000', '&#xe6ed;', '管理系统配置项', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 22:20:37', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-21 16:53:25', '0');
INSERT INTO `sys_menu` VALUES ('40288172623e9ff301623ea429290001', '后台首页', '1', '/admin/index.html', '40288172623e9ff301623ea228330000', 'mogami2018hejingdev,402881e7622c909201622c93f0ad0000,40288172623e9ff301623ea228330000', '3', '1', '1', '10000', '', '登录系统显示第一个界面', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 22:22:48', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-20 10:16:15', '0');
INSERT INTO `sys_menu` VALUES ('40288172624d8fdf01624d939b4b0000', '数据字典', '1', '/sys/dict/dictList.html', '40288172623e9ff301623ea228330000', 'mogami2018hejingdev,402881e7622c909201622c93f0ad0000,40288172623e9ff301623ea228330000', '3', '1', '0', '1', '', '数据字典', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-22 19:59:01', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-25 19:24:19', '0');
INSERT INTO `sys_menu` VALUES ('402881e5623ca50401623ca608170000', '用户中心', '1', '#', '402881e7622c909201622c93f0ad0000', 'mogami2018hejingdev,402881e7622c909201622c93f0ad0000', '2', '1', '0', '100', '&#xe60d;', '系统管理中用户设置模块', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 13:05:36', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 23:17:17', '0');
INSERT INTO `sys_menu` VALUES ('402881e56240f534016240fb78e20000', '系统配置', '1', '/admin/systemSet.html', '40288172623e9ff301623ea228330000', 'mogami2018hejingdev,402881e7622c909201622c93f0ad0000,40288172623e9ff301623ea228330000', '3', '1', '0', '2', '&#xe63f;', '平台信息配置模块', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-20 09:17:25', null, null, '0');
INSERT INTO `sys_menu` VALUES ('402881e5624cccd001624ceba14f0002', '日志管理', '1', '/sys/log/logList.html', '40288172623e9ff301623ea228330000', 'mogami2018hejingdev,402881e7622c909201622c93f0ad0000,40288172623e9ff301623ea228330000', '3', '1', '0', '1', '&#xe632;', ' 日志管理', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-22 16:55:30', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-22 17:30:01', '0');
INSERT INTO `sys_menu` VALUES ('402881e7622c909201622c93f0ad0000', '系统管理', '1', '#', 'mogami2018hejingdev', 'mogami2018hejingdev', '1', '1', '1', '1', '&#xe6be;', '系统管理模块-修改', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-16 10:11:55', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-21 16:52:40', '0');
INSERT INTO `sys_menu` VALUES ('402881e7622d548701622d580b290000', '订单管理', '1', '/order/manage', 'mogami2018hejingdev', 'mogami2018hejingdev', '1', '1', '0', '0', '&#xe6b7;', '发货单管理', '629e91dabee14a5cb1dab230dba11e00', '2018-03-16 13:46:07', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-21 16:54:06', '0');
INSERT INTO `sys_menu` VALUES ('402881e7622d548701622d58d8300001', '会员管理', '1', '/member/memlist', 'mogami2018hejingdev', 'mogami2018hejingdev', '1', '1', '0', '0', '&#xe60d;', '用户会员管理', '629e91dabee14a5cb1dab230dba11e00', '2018-03-16 13:47:00', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 23:18:46', '0');
INSERT INTO `sys_menu` VALUES ('402881e762471db0016247beaba10000', 'Druid监控中心', '1', '/druid/index.html', '40288172623e9ff301623ea228330000', 'mogami2018hejingdev,402881e7622c909201622c93f0ad0000,40288172623e9ff301623ea228330000', '3', '1', '0', '1', '&#xe6ed;', 'Druid监控中心', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-21 16:48:20', null, null, '0');

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
INSERT INTO `sys_role_menu` VALUES ('40288172624d8fdf01624d946c580002', '402881e7622d548701622d58d8300001', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('40288172624d8fdf01624d946c590003', '402881e7622d548701622d580b290000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('40288172624d8fdf01624d946c590004', '402881e7622c909201622c93f0ad0000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('40288172624d8fdf01624d946c590005', '40288172623e9ff301623ea228330000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('40288172624d8fdf01624d946c590006', '40288172624d8fdf01624d939b4b0000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('40288172624d8fdf01624d946c590007', '402881e5624cccd001624ceba14f0002', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('40288172624d8fdf01624d946c590008', '402881e762471db0016247beaba10000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('40288172624d8fdf01624d946c590009', '402881e56240f534016240fb78e20000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('40288172624d8fdf01624d946c5a000a', '40288172623e9ff301623ea429290001', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('40288172624d8fdf01624d946c5a000b', '402881e5623ca50401623ca608170000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('40288172624d8fdf01624d946c5a000c', '4028817261ee84fb0161ee86b8d30000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('40288172624d8fdf01624d946c5a000d', '4028817261ec86300161ec8b18d90001', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('40288172624d8fdf01624d946c5a000e', '4028817261ec86300161ec87e7070000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('40288172625b13e001625b171bc90000', '402881e7622c909201622c93f0ad0000', '402881e761f40d3e0161f4c0ef890000');
INSERT INTO `sys_role_menu` VALUES ('40288172625b13e001625b171bd80001', '40288172623e9ff301623ea228330000', '402881e761f40d3e0161f4c0ef890000');
INSERT INTO `sys_role_menu` VALUES ('40288172625b13e001625b171bd80002', '40288172624d8fdf01624d939b4b0000', '402881e761f40d3e0161f4c0ef890000');
INSERT INTO `sys_role_menu` VALUES ('40288172625b13e001625b171bd80003', '402881e5624cccd001624ceba14f0002', '402881e761f40d3e0161f4c0ef890000');
INSERT INTO `sys_role_menu` VALUES ('40288172625b13e001625b171bd90004', '402881e762471db0016247beaba10000', '402881e761f40d3e0161f4c0ef890000');
INSERT INTO `sys_role_menu` VALUES ('40288172625b13e001625b171bd90005', '402881e56240f534016240fb78e20000', '402881e761f40d3e0161f4c0ef890000');
INSERT INTO `sys_role_menu` VALUES ('40288172625b13e001625b171bd90006', '40288172623e9ff301623ea429290001', '402881e761f40d3e0161f4c0ef890000');

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
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946c6a000f', '402881e761f91b320161f92729ca0000', '402881e7622d548701622d58d8300001', '402881e5623d4e2601623d4f606d0000');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946c6d0010', '402881e761f91b320161f92729ca0000', '402881e7622d548701622d580b290000', '402881e5623d4e2601623d5052c10001');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946c710011', '402881e761f91b320161f92729ca0000', '402881e7622c909201622c93f0ad0000', '402881e5623cea4d01623cf6dd6a0015');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946c750012', '402881e761f91b320161f92729ca0000', '40288172623e9ff301623ea228330000', '40288172623ecb9401623ece93f70000');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946c790013', '402881e761f91b320161f92729ca0000', '40288172624d8fdf01624d939b4b0000', '40288172624d8fdf01624d9421360001');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946c7e0014', '402881e761f91b320161f92729ca0000', '402881e5624cccd001624ceba14f0002', '402881e5624cccd001624d0c9e180003');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946c820015', '402881e761f91b320161f92729ca0000', '402881e762471db0016247beaba10000', '402881e762471db0016247c068870001');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946c870016', '402881e761f91b320161f92729ca0000', '402881e56240f534016240fb78e20000', '402881e56240f534016240fc26d30001');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946c8c0017', '402881e761f91b320161f92729ca0000', '40288172623e9ff301623ea429290001', '40288172623ecb9401623ecf271e0001');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946c900018', '402881e761f91b320161f92729ca0000', '402881e5623ca50401623ca608170000', '402881e5623cea4d01623cf60fc40014');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946c940019', '402881e761f91b320161f92729ca0000', '4028817261ee84fb0161ee86b8d30000', '402881e7622d827301622d852e720000');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946c98001a', '402881e761f91b320161f92729ca0000', '4028817261ee84fb0161ee86b8d30000', '402881e7622d827301622d85b54f0001');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946c9d001b', '402881e761f91b320161f92729ca0000', '4028817261ee84fb0161ee86b8d30000', '402881e7622d827301622d865c760002');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946ca1001c', '402881e761f91b320161f92729ca0000', '4028817261ee84fb0161ee86b8d30000', '402881e7622d827301622d87dd290003');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946ca5001d', '402881e761f91b320161f92729ca0000', '4028817261ee84fb0161ee86b8d30000', '402881e7622d827301622d887b410004');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946ca9001e', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec8b18d90001', '402881e7622c5b7501622c61ee390000');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946cae001f', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec8b18d90001', '402881e7622c5b7501622c627c040001');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946cb30020', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec87e7070000', '402881e76228ef8d0162290506650000');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946cb70021', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec87e7070000', '402881e76228ef8d01622905c2d40001');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946cbb0022', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec87e7070000', '402881e7622c5b7501622c6424c10002');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946cc00023', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec87e7070000', '402881e762468d5f016246acfe5e0000');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172624d8fdf01624d946cc40024', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec87e7070000', '402881e762468d5f016246b021920001');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172625b13e001625b171c030007', '402881e761f40d3e0161f4c0ef890000', '402881e7622c909201622c93f0ad0000', '402881e5623cea4d01623cf6dd6a0015');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172625b13e001625b171c080008', '402881e761f40d3e0161f4c0ef890000', '40288172623e9ff301623ea228330000', '40288172623ecb9401623ece93f70000');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172625b13e001625b171c0d0009', '402881e761f40d3e0161f4c0ef890000', '40288172624d8fdf01624d939b4b0000', '40288172624d8fdf01624d9421360001');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172625b13e001625b171c13000a', '402881e761f40d3e0161f4c0ef890000', '402881e5624cccd001624ceba14f0002', '402881e5624cccd001624d0c9e180003');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172625b13e001625b171c18000b', '402881e761f40d3e0161f4c0ef890000', '402881e762471db0016247beaba10000', '402881e762471db0016247c068870001');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172625b13e001625b171c1d000c', '402881e761f40d3e0161f4c0ef890000', '402881e56240f534016240fb78e20000', '402881e56240f534016240fc26d30001');
INSERT INTO `sys_role_menu_fun` VALUES ('40288172625b13e001625b171c21000d', '402881e761f40d3e0161f4c0ef890000', '40288172623e9ff301623ea429290001', '40288172623ecb9401623ecf271e0001');

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
INSERT INTO `sys_user` VALUES ('9771b4e5205c4601aaec8ff078b499c5', 'sf', 'testers', '/upload/2018-03-26/36db187e-d32a-44f7-9c26-2bea665def39.png', 'e515cd52db60ad261130204f5cf7f9d2', '0c4d7acec5292f1376a4e853442112f1', '304245@qq.com', '123123', '1', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 11:01:30', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-26 11:02:30', ' sdfsf vvvvvvv', '0');
INSERT INTO `sys_user` VALUES ('a85c1caa86a242369ef8b735bed0b504', '何靖', 'hejing', null, 'e77e9f590d0c5332fedb77b409fe9c7f', '9c8dc7af3ed501f34ec972d4b2a4cddb', '304241452@qq.com', '13595029935', '1', null, '2018-03-06 10:33:19', null, null, '何靖', null);
INSERT INTO `sys_user` VALUES ('c48e5e7c03f34d1d97f1915b22428c21', '超级管理员', 'admin', '/upload/2018-03-22/f15cdc58-a788-4caf-bdde-909a196ef659.png', '6e801742ff3b0af6df28eaa3849cc6a1', '3328b8f452cfdda10d3ad82dd58f3b10', '3042415452@qq.com', '13595029936', '1', null, '2018-02-11 22:40:46', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-22 17:59:32', '  ', null);
INSERT INTO `sys_user` VALUES ('c6e56591e2d54b7c9a16582fcfcd3c1d', '贵州海誉', 'gzhy', '/upload/2018-03-21/d861bedb-aa4c-4787-a580-9c37a6b00c66.png', 'd4a7524e6754aefbec4c72b4e3598521', '80bb2a367c93ff8e12e354daa84b6d75', '4898@qq.com', '1355959998', '1', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-21 11:24:52', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-21 11:35:52', '  贵州海誉科技股份有限公司  ', '0');
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
INSERT INTO `sys_user_role` VALUES ('402881e5623ce37d01623ce73dd90000', 'c48e5e7c03f34d1d97f1915b22428c21', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_user_role` VALUES ('402881f2621dfaa901621e005cd20011', 'a85c1caa86a242369ef8b735bed0b504', '402881e761f40d3e0161f4c0ef890000');
INSERT INTO `sys_user_role` VALUES ('402881f2621dfaa901621e02b00c0016', '629e91dabee14a5cb1dab230dba11e00', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_user_role` VALUES ('402881f2621dfaa901621e02b02d0017', '629e91dabee14a5cb1dab230dba11e00', '402881e761f40d3e0161f4c0ef890000');
