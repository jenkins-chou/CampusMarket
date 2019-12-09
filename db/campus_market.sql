/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : campus_market

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2019-12-08 19:10:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_collection
-- ----------------------------
DROP TABLE IF EXISTS `base_collection`;
CREATE TABLE `base_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品id',
  `user_id` int(11) DEFAULT NULL COMMENT '收藏者id',
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_collection
-- ----------------------------
INSERT INTO `base_collection` VALUES ('1', null, null, '1575791347', '备注', 'normal');

-- ----------------------------
-- Table structure for base_comment
-- ----------------------------
DROP TABLE IF EXISTS `base_comment`;
CREATE TABLE `base_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品id',
  `comment_value` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `user_id` varchar(255) DEFAULT NULL COMMENT '评论者id',
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_comment
-- ----------------------------
INSERT INTO `base_comment` VALUES ('1', null, '商品', 'user_id', '1575791319', null, 'normal');

-- ----------------------------
-- Table structure for base_commodity
-- ----------------------------
DROP TABLE IF EXISTS `base_commodity`;
CREATE TABLE `base_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plate_id` varchar(255) DEFAULT NULL COMMENT '板块id',
  `commodity_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `commodity_price` varchar(255) DEFAULT NULL COMMENT '商品价格',
  `commodity_img` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `commodity_old_price` varchar(255) DEFAULT NULL COMMENT '商品旧价',
  `commodity_describe` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `state` varchar(255) DEFAULT NULL COMMENT '状态',
  `commodity_provider` varchar(255) DEFAULT NULL COMMENT '商品发布者',
  `commodity_produce_time` varchar(255) DEFAULT NULL COMMENT '上缠日期',
  `commodity_validity` varchar(255) DEFAULT NULL COMMENT '有效期',
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_commodity
-- ----------------------------
INSERT INTO `base_commodity` VALUES ('1', null, null, null, null, null, null, null, null, null, null, '1575791365', null, 'delete');

-- ----------------------------
-- Table structure for base_menu
-- ----------------------------
DROP TABLE IF EXISTS `base_menu`;
CREATE TABLE `base_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menu_level` varchar(255) DEFAULT NULL COMMENT '菜单等级',
  `menu_parent` varchar(255) DEFAULT NULL COMMENT '父菜单',
  `menu_sub_size` int(11) DEFAULT NULL COMMENT '子菜单数量',
  `menu_url` varchar(255) DEFAULT NULL COMMENT '页面URL',
  `menu_index` int(255) DEFAULT NULL COMMENT '排序号',
  `menu_icon` varchar(255) DEFAULT NULL COMMENT 'icon',
  `user_type` varchar(255) DEFAULT NULL COMMENT '用户类型',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_menu
-- ----------------------------
INSERT INTO `base_menu` VALUES ('1', '系统设置', '1', '0', null, '', '999', '', '4', '', '', 'delete');
INSERT INTO `base_menu` VALUES ('2', '菜单设置', '2', '1', null, '../pages/manager/system/menu.html', '1', '', '4', '', '', 'delete');
INSERT INTO `base_menu` VALUES ('118', '交易管理', '1', '1', null, null, '1', null, '4', null, null, 'normal');
INSERT INTO `base_menu` VALUES ('119', '用户管理', '1', '1', null, null, '1', null, '4', null, null, 'normal');
INSERT INTO `base_menu` VALUES ('120', '评论管理', '1', '1', null, null, '1', null, '4', null, null, 'normal');
INSERT INTO `base_menu` VALUES ('121', '模块管理', '1', '1', null, null, '1', null, '4', null, null, 'normal');
INSERT INTO `base_menu` VALUES ('122', '交易品管理', '2', '118', null, '../pages/manager/base_commodity/list.html', '1', null, '4', null, null, 'normal');
INSERT INTO `base_menu` VALUES ('123', '订单管理', '2', '118', null, '../pages/manager/base_order/list.html', '1', null, '4', null, null, 'normal');
INSERT INTO `base_menu` VALUES ('124', '用户列表', '2', '119', null, '../pages/manager/base_user/list.html', '1', null, '4', null, null, 'normal');
INSERT INTO `base_menu` VALUES ('125', '用户类型列表', '2', '119', null, '../pages/manager/base_user_type/list.html', '1', null, '4', null, null, 'normal');
INSERT INTO `base_menu` VALUES ('126', '评论管理', '2', '120', null, '../pages/manager/base_comment/list.html', '1', null, '4', null, null, 'normal');
INSERT INTO `base_menu` VALUES ('127', '收藏管理', '2', '119', null, '../pages/manager/base_collection/list.html', '1', null, '4', null, null, 'normal');
INSERT INTO `base_menu` VALUES ('128', '板块列表', '2', '121', null, '../pages/manager/base_plate/list.html', '1', null, '4', null, null, 'normal');
INSERT INTO `base_menu` VALUES ('129', '留言列表', '2', '119', null, '../pages/manager/base_message/list.html', '1', null, '4', null, null, 'normal');
INSERT INTO `base_menu` VALUES ('130', '数据统计', '1', '1', null, null, '1', null, '4', null, null, 'normal');
INSERT INTO `base_menu` VALUES ('131', '图表统计', '2', '130', null, '../pages/manager/statistics/index.html', '1', null, '4', null, null, 'normal');

-- ----------------------------
-- Table structure for base_message
-- ----------------------------
DROP TABLE IF EXISTS `base_message`;
CREATE TABLE `base_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sender` varchar(255) DEFAULT NULL COMMENT '发送者id',
  `getter` varchar(255) DEFAULT NULL COMMENT '接受者id',
  `message` varchar(255) DEFAULT NULL COMMENT '信息内容',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `send_time` varchar(255) DEFAULT NULL COMMENT '发送时间',
  `send_state` varchar(255) DEFAULT NULL COMMENT '发送状态',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of base_message
-- ----------------------------
INSERT INTO `base_message` VALUES ('1', null, null, null, null, null, null, '1575791350', null, 'delete');

-- ----------------------------
-- Table structure for base_module
-- ----------------------------
DROP TABLE IF EXISTS `base_module`;
CREATE TABLE `base_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `module_title` varchar(255) DEFAULT NULL COMMENT '功能模块标题',
  `module_name` varchar(255) DEFAULT NULL COMMENT '功能模块名称',
  `module_index` varchar(255) DEFAULT NULL COMMENT '功能模块排序号',
  `create_time` varchar(255) DEFAULT NULL COMMENT '功能模块创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '功能模块备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of base_module
-- ----------------------------
INSERT INTO `base_module` VALUES ('1', null, 'module1', null, '1573224661', null, 'normal');
INSERT INTO `base_module` VALUES ('2', null, null, null, '1573401476', null, 'normal');
INSERT INTO `base_module` VALUES ('3', null, null, null, '1573401482', null, 'normal');
INSERT INTO `base_module` VALUES ('4', null, null, null, '1573401496', null, 'normal');
INSERT INTO `base_module` VALUES ('5', null, null, null, '1573401513', null, 'normal');

-- ----------------------------
-- Table structure for base_order
-- ----------------------------
DROP TABLE IF EXISTS `base_order`;
CREATE TABLE `base_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `party_a` varchar(255) DEFAULT NULL COMMENT '卖方',
  `party_b` varchar(255) DEFAULT NULL COMMENT '买方',
  `commodity_id` varchar(255) DEFAULT NULL COMMENT '商品',
  `state` varchar(255) DEFAULT NULL COMMENT '状态',
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_order
-- ----------------------------
INSERT INTO `base_order` VALUES ('1', null, null, null, null, '1575791369', null, 'delete');
INSERT INTO `base_order` VALUES ('2', null, null, null, null, '1575791389', null, 'delete');

-- ----------------------------
-- Table structure for base_plate
-- ----------------------------
DROP TABLE IF EXISTS `base_plate`;
CREATE TABLE `base_plate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plate_name` varchar(255) DEFAULT NULL COMMENT '板块名称',
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_plate
-- ----------------------------
INSERT INTO `base_plate` VALUES ('1', '电器', '1575791300', null, 'normal');

-- ----------------------------
-- Table structure for base_type
-- ----------------------------
DROP TABLE IF EXISTS `base_type`;
CREATE TABLE `base_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `module_id` varchar(255) DEFAULT NULL COMMENT 'moduleid',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `describe` varchar(255) DEFAULT NULL COMMENT '描述',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_type
-- ----------------------------

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `realname` varchar(255) DEFAULT NULL COMMENT '真实名称',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `email` varchar(255) DEFAULT NULL COMMENT 'email',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `wechat` varchar(255) DEFAULT NULL COMMENT 'wechat',
  `qq` varchar(255) DEFAULT NULL COMMENT 'qq',
  `age` varchar(255) DEFAULT NULL COMMENT 'age',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `slogan` varchar(255) DEFAULT NULL COMMENT '签名',
  `region` varchar(255) DEFAULT NULL COMMENT '地区',
  `type` varchar(255) DEFAULT NULL COMMENT '用户类型',
  `type_describe` varchar(255) DEFAULT NULL COMMENT '用户类型描述',
  `state` varchar(255) DEFAULT NULL COMMENT '状态',
  `identity` varchar(255) DEFAULT NULL COMMENT '身份',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES ('7', '456', '456', null, null, null, null, null, null, null, null, null, null, '2', '教师', null, null, '1572004895', null, 'delete');
INSERT INTO `base_user` VALUES ('8', 'admin', 'admin', '系统管理员', '134126', 'email', '联系地址', 'wchat', 'qq', '26', '', 'hello', '地区', '4', '系统管理员', null, null, null, null, 'normal');
INSERT INTO `base_user` VALUES ('9', '123', '123', '周先生', '电话', 'email', '地址', '13413739238923', 'qq号', '21', null, '签名', '广东', '1', '学生', null, null, '1572147442', null, 'delete');
INSERT INTO `base_user` VALUES ('12', '789', '789', null, null, null, null, null, null, null, null, null, null, '1', '学生', null, null, '1572150642', null, 'delete');
INSERT INTO `base_user` VALUES ('13', '123', '123', null, null, null, null, null, null, null, null, null, null, '1', 'ѧ��', null, null, '1575559080', null, 'normal');

-- ----------------------------
-- Table structure for base_user_type
-- ----------------------------
DROP TABLE IF EXISTS `base_user_type`;
CREATE TABLE `base_user_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `describe` varchar(255) DEFAULT NULL COMMENT '描述',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user_type
-- ----------------------------
INSERT INTO `base_user_type` VALUES ('1', '1', null, '学生', null, null, 'delete');
INSERT INTO `base_user_type` VALUES ('2', '2', null, '教师', null, null, 'normal');
INSERT INTO `base_user_type` VALUES ('3', '3', null, '管理员', null, null, 'delete');
INSERT INTO `base_user_type` VALUES ('4', '4', null, '管理员', null, null, 'normal');

-- ----------------------------
-- Table structure for transaction_record
-- ----------------------------
DROP TABLE IF EXISTS `transaction_record`;
CREATE TABLE `transaction_record` (
  `id` int(11) NOT NULL,
  `commodity_id` varchar(255) DEFAULT NULL COMMENT '商品id',
  `state` varchar(255) DEFAULT NULL COMMENT '状态',
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transaction_record
-- ----------------------------
