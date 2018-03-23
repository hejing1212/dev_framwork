/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50559
Source Host           : localhost:3306
Source Database       : hlframe

Target Server Type    : MYSQL
Target Server Version : 50559
File Encoding         : 65001

Date: 2018-03-23 18:28:22
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
INSERT INTO `sys_function` VALUES ('40288172623ecb9401623ece93f70000', 'init', '显示', '40288172623e9ff301623ea228330000', 'system:set', '1', '', '系统设置', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 23:09:08', null, null, '0');
INSERT INTO `sys_function` VALUES ('40288172623ecb9401623ecf271e0001', 'init', '显示', '40288172623e9ff301623ea429290001', 'home:index', '1', '', '后台首页显示', 'c48e5e7c03f34d1d97f1915b22428c21', '2018-03-19 23:09:46', null, null, '0');
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
INSERT INTO `sys_log` VALUES ('068deed142264ccc8fa8b75f0b322372', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e5623cea4d01623cf60fc40014|menu_id值为：402881e5623ca50401623ca608170000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d61f40018', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('06e063ebdc6f4117a59945c4e41e3156', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e5623ca50401623ca608170000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d5f87000c', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('0c57f36c0ad24086b7a15c6bf353bc02', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d85b54f0001|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d6283001a', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('135b721c2d7f43f2aa11b15af713f31c', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e5623cea4d01623cf6dd6a0015|menu_id值为：402881e7622c909201622c93f0ad0000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d606e0012', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('189738384083442c9b0b1aadd405dd01', null, '2018-03-22 17:32:26', '0', null, null, '操作记录：|fun_id值为：402881e76228ef8d01622905c2d40001|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d64320021', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('2be08ae9e80145999db9e760ac28f223', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d887b410004|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d6339001d', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('2cfad9bbe266469a94c5637f28243001', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|fun_id值为：402881e5623d4e2601623d4f606d0000|menu_id值为：402881e7622d548701622d58d8300001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d60100010', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('3614eb8676284ca388fdba7413727667', null, '2018-03-22 16:55:42', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-22 04:55:30|del_flag值为：0|current值为：0|isshow值为：1|level值为：3|menu_icon值为：&#xe632;|name值为：日志管理|parentIds值为：mogami2018hejingdev,402881e7622c909201622c93f0ad0000,40288172623e9ff301623ea228330000|parent_id值为：40288172623e9ff301623ea228330000|remarks值为： 日志管理|sort值为：1|type值为：1|url值为：/sys/log/list|', '402881e5624cccd001624ceba14f0002', '', 'sys_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('3876218aae28466c8c6b7fb7ff9bfea3', null, '2018-03-22 17:32:26', '0', null, null, '操作记录：|fun_id值为：402881e7622c5b7501622c6424c10002|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d647c0022', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('3d0f1537a3e34dd3af1407fc1d3012f3', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e762471db0016247c068870001|menu_id值为：402881e762471db0016247beaba10000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d61300015', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('3f943ad3424f43ebbc59e07c397685a9', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e76228ef8d0162290506650000|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d63f00020', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('5535225f2c33404496d5ed503fce13ab', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e56240f534016240fc26d30001|menu_id值为：402881e56240f534016240fb78e20000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d616a0016', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('5968cab03e9c4c2b85a22c32bcedc823', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：4028817261ec86300161ec8b18d90001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d5f88000e', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('60289b718fa04135b3111c88a308a1a5', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e5624cccd001624ceba14f0002|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d4ba00008', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('670d6593502c4ebaa2ed964650d9ed77', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e5624cccd001624d0c9e180003|menu_id值为：402881e5624cccd001624ceba14f0002|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d60f90014', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('72efd263e179424b87228e6d1383cbea', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d5f89000f', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('77646e0352e54876bbeada0bc72e5ffd', null, '2018-03-22 17:29:17', '0', null, null, '操作记录：|update_date由：2018-03-22 05:24:07变为：2018-03-22 05:27:15|menu_icon由：&#xe632;变为：|', '402881e5624cccd001624ceba14f0002', '', 'sys_menu', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('85dd4043467546e9994cbc38f3b0808b', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e56240f534016240fb78e20000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d5f68000a', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('8701c6ef3f6143229139b44b21bb9f59', null, '2018-03-22 17:59:32', '0', null, null, '操作记录：|update_by由：629e91dabee14a5cb1dab230dba11e00变为：c48e5e7c03f34d1d97f1915b22428c21|update_date由：2018-03-17 11:48:49变为：2018-03-22 05:59:32|portrait由：null变为：/upload/2018-03-22/f15cdc58-a788-4caf-bdde-909a196ef659.png|remarks由：null变为：  |', 'c48e5e7c03f34d1d97f1915b22428c21', '', 'sys_user', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('946802286d9844d4b394a4b6087ba7ff', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d87dd290003|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d62f9001c', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('97521397a0db4bf49a813c79fc3c65db', null, '2018-03-22 17:31:40', '0', null, null, '操作记录：|create_by值为：c48e5e7c03f34d1d97f1915b22428c21|create_date值为：2018-03-22 05:31:35|del_flag值为：0|fun_action值为：init|menu_id值为：402881e5624cccd001624ceba14f0002|name值为：显示|permission值为：sys:log:logList|remarks值为：显示日志列表|sort值为：1|', '402881e5624cccd001624d0c9e180003', '', 'sys_function', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('9aa6263b80284d41920f1c6e6a90fa13', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d5f87000d', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('9abd1a21473949c99bad800e590c511c', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d852e720000|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d62300019', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('9dd2a6c75bee471ebd64bd6730553072', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：40288172623e9ff301623ea228330000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d47140007', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('b2d743b3e2ad4977a901c75835cb6757', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e762471db0016247beaba10000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d4e380009', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('b8fe470e55f347ffb93f31d583517374', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e7622d548701622d580b290000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0cff160005', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('be3e16ddaf8a4571af2e6a7a4032a51d', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e7622c909201622c93f0ad0000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d3d730006', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('ca7211e632cf4b5fa75267bca7727c16', null, '2018-03-22 17:30:23', '0', null, null, '操作记录：|update_date由：2018-03-22 05:27:15变为：2018-03-22 05:30:01|menu_icon由：变为：&#xe632;|', '402881e5624cccd001624ceba14f0002', '', 'sys_menu', '2', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('ca8dfe1960f945f28f0a7f55d1bfb37e', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e5623d4e2601623d5052c10001|menu_id值为：402881e7622d548701622d580b290000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d602c0011', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('d7207217357a4ecc83f7b19258f63fee', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622c5b7501622c61ee390000|menu_id值为：4028817261ec86300161ec8b18d90001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d637d001e', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('d861fce15cfe447f890999903df419eb', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622c5b7501622c627c040001|menu_id值为：4028817261ec86300161ec8b18d90001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d63b3001f', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('d9d51d9de8824dae9c98f98bca6e5cb5', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：402881e7622d548701622d58d8300001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0ce5b20004', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('e0cc1b506dd044bea299da8428149e39', null, '2018-03-22 17:32:26', '0', null, null, '操作记录：|fun_id值为：402881e762468d5f016246b021920001|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d651b0024', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('e5b081e4e86a4959a485541f663fcf9b', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：40288172623ecb9401623ece93f70000|menu_id值为：40288172623e9ff301623ea228330000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d60ae0013', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('e9ef9922b9d64f78a8f79b8ef442f491', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：40288172623ecb9401623ecf271e0001|menu_id值为：40288172623e9ff301623ea429290001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d61ac0017', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('f420910272754fbba3078505fd6b66b1', null, '2018-03-22 17:32:25', '0', null, null, '操作记录：|fun_id值为：402881e7622d827301622d865c760002|menu_id值为：4028817261ee84fb0161ee86b8d30000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d62c3001b', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('f765e8dea80a4bc2a8861fb20c79a816', null, '2018-03-22 17:32:26', '0', null, null, '操作记录：|fun_id值为：402881e762468d5f016246acfe5e0000|menu_id值为：4028817261ec86300161ec87e7070000|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d64bc0023', '', 'sys_role_menu_fun', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');
INSERT INTO `sys_log` VALUES ('fb5265d0f3064d3599d862be7540d6de', null, '2018-03-22 17:32:24', '0', null, null, '操作记录：|menu_id值为：40288172623e9ff301623ea429290001|role_id值为：402881e761f91b320161f92729ca0000|', '402881e5624cccd001624d0d5f69000b', '', 'sys_role_menu', '1', 'c48e5e7c03f34d1d97f1915b22428c21', 'admin');

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
INSERT INTO `sys_role_menu` VALUES ('402881e5624cccd001624d0ce5b20004', '402881e7622d548701622d58d8300001', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('402881e5624cccd001624d0cff160005', '402881e7622d548701622d580b290000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('402881e5624cccd001624d0d3d730006', '402881e7622c909201622c93f0ad0000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('402881e5624cccd001624d0d47140007', '40288172623e9ff301623ea228330000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('402881e5624cccd001624d0d4ba00008', '402881e5624cccd001624ceba14f0002', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('402881e5624cccd001624d0d4e380009', '402881e762471db0016247beaba10000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('402881e5624cccd001624d0d5f68000a', '402881e56240f534016240fb78e20000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('402881e5624cccd001624d0d5f69000b', '40288172623e9ff301623ea429290001', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('402881e5624cccd001624d0d5f87000c', '402881e5623ca50401623ca608170000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('402881e5624cccd001624d0d5f87000d', '4028817261ee84fb0161ee86b8d30000', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('402881e5624cccd001624d0d5f88000e', '4028817261ec86300161ec8b18d90001', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_role_menu` VALUES ('402881e5624cccd001624d0d5f89000f', '4028817261ec86300161ec87e7070000', '402881e761f91b320161f92729ca0000');

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
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d60100010', '402881e761f91b320161f92729ca0000', '402881e7622d548701622d58d8300001', '402881e5623d4e2601623d4f606d0000');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d602c0011', '402881e761f91b320161f92729ca0000', '402881e7622d548701622d580b290000', '402881e5623d4e2601623d5052c10001');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d606e0012', '402881e761f91b320161f92729ca0000', '402881e7622c909201622c93f0ad0000', '402881e5623cea4d01623cf6dd6a0015');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d60ae0013', '402881e761f91b320161f92729ca0000', '40288172623e9ff301623ea228330000', '40288172623ecb9401623ece93f70000');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d60f90014', '402881e761f91b320161f92729ca0000', '402881e5624cccd001624ceba14f0002', '402881e5624cccd001624d0c9e180003');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d61300015', '402881e761f91b320161f92729ca0000', '402881e762471db0016247beaba10000', '402881e762471db0016247c068870001');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d616a0016', '402881e761f91b320161f92729ca0000', '402881e56240f534016240fb78e20000', '402881e56240f534016240fc26d30001');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d61ac0017', '402881e761f91b320161f92729ca0000', '40288172623e9ff301623ea429290001', '40288172623ecb9401623ecf271e0001');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d61f40018', '402881e761f91b320161f92729ca0000', '402881e5623ca50401623ca608170000', '402881e5623cea4d01623cf60fc40014');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d62300019', '402881e761f91b320161f92729ca0000', '4028817261ee84fb0161ee86b8d30000', '402881e7622d827301622d852e720000');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d6283001a', '402881e761f91b320161f92729ca0000', '4028817261ee84fb0161ee86b8d30000', '402881e7622d827301622d85b54f0001');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d62c3001b', '402881e761f91b320161f92729ca0000', '4028817261ee84fb0161ee86b8d30000', '402881e7622d827301622d865c760002');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d62f9001c', '402881e761f91b320161f92729ca0000', '4028817261ee84fb0161ee86b8d30000', '402881e7622d827301622d87dd290003');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d6339001d', '402881e761f91b320161f92729ca0000', '4028817261ee84fb0161ee86b8d30000', '402881e7622d827301622d887b410004');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d637d001e', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec8b18d90001', '402881e7622c5b7501622c61ee390000');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d63b3001f', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec8b18d90001', '402881e7622c5b7501622c627c040001');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d63f00020', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec87e7070000', '402881e76228ef8d0162290506650000');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d64320021', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec87e7070000', '402881e76228ef8d01622905c2d40001');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d647c0022', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec87e7070000', '402881e7622c5b7501622c6424c10002');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d64bc0023', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec87e7070000', '402881e762468d5f016246acfe5e0000');
INSERT INTO `sys_role_menu_fun` VALUES ('402881e5624cccd001624d0d651b0024', '402881e761f91b320161f92729ca0000', '4028817261ec86300161ec87e7070000', '402881e762468d5f016246b021920001');

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
INSERT INTO `sys_user_role` VALUES ('402881f2621dfaa901621e005c770010', 'a85c1caa86a242369ef8b735bed0b504', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_user_role` VALUES ('402881f2621dfaa901621e005cd20011', 'a85c1caa86a242369ef8b735bed0b504', '402881e761f40d3e0161f4c0ef890000');
INSERT INTO `sys_user_role` VALUES ('402881f2621dfaa901621e02b00c0016', '629e91dabee14a5cb1dab230dba11e00', '402881e761f91b320161f92729ca0000');
INSERT INTO `sys_user_role` VALUES ('402881f2621dfaa901621e02b02d0017', '629e91dabee14a5cb1dab230dba11e00', '402881e761f40d3e0161f4c0ef890000');
