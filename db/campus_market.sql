/*
 Navicat Premium Data Transfer

 Source Server         : administrator
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : campus_market

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 14/02/2020 14:35:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `update_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` VALUES (1, '8', 15197, '1566744032', '1566921059', NULL, 'normal');
INSERT INTO `account` VALUES (2, '13', 8264, '1581317835', '1581355031', NULL, 'normal');
COMMIT;

-- ----------------------------
-- Table structure for account_recharge
-- ----------------------------
DROP TABLE IF EXISTS `account_recharge`;
CREATE TABLE `account_recharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `money` double(255,0) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_recharge
-- ----------------------------
BEGIN;
INSERT INTO `account_recharge` VALUES (1, 8, NULL, 7885, 'success', NULL, '1566744032', NULL, 'normal');
INSERT INTO `account_recharge` VALUES (2, 8, NULL, 10000, 'success', NULL, '1566921059', NULL, 'normal');
INSERT INTO `account_recharge` VALUES (3, 13, NULL, 1000, 'success', NULL, '1581317835', NULL, 'normal');
INSERT INTO `account_recharge` VALUES (4, 13, NULL, 456, 'success', NULL, '1581318014', NULL, 'normal');
INSERT INTO `account_recharge` VALUES (5, 13, NULL, 10000, 'success', NULL, '1581355031', NULL, 'normal');
COMMIT;

-- ----------------------------
-- Table structure for bankcard
-- ----------------------------
DROP TABLE IF EXISTS `bankcard`;
CREATE TABLE `bankcard` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `bank_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `bankcard_number` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `bankcard_username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `bankcard_phone` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `create_time` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `del` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of bankcard
-- ----------------------------
BEGIN;
INSERT INTO `bankcard` VALUES (1, '8', 'dgg', 'ghgf', 'ghhf', 'uhggh', '1566744010', NULL, 'normal');
INSERT INTO `bankcard` VALUES (2, NULL, '招商银行', '45621354', 'zzj', '12345678910', '1581255635', NULL, 'normal');
INSERT INTO `bankcard` VALUES (3, '13', '招商银行', '123', '周一', '1365234', '1581255866', NULL, 'normal');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_collection
-- ----------------------------
BEGIN;
INSERT INTO `base_collection` VALUES (3, 4, 16, '1578804263', NULL, 'normal');
INSERT INTO `base_collection` VALUES (4, 2, 16, '1578804273', NULL, 'normal');
INSERT INTO `base_collection` VALUES (5, 6, 16, '1578807076', NULL, 'normal');
INSERT INTO `base_collection` VALUES (6, 6, 16, '1578811145', NULL, 'normal');
INSERT INTO `base_collection` VALUES (7, 2, 18, '1578840640', NULL, 'normal');
INSERT INTO `base_collection` VALUES (8, 2, 13, '1581167782', NULL, 'normal');
COMMIT;

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
BEGIN;
INSERT INTO `base_comment` VALUES (1, NULL, '商品', 'user_id', '1575791319', NULL, 'normal');
COMMIT;

-- ----------------------------
-- Table structure for base_commodity
-- ----------------------------
DROP TABLE IF EXISTS `base_commodity`;
CREATE TABLE `base_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plate_id` varchar(255) DEFAULT NULL COMMENT '板块id',
  `plate_name` varchar(255) DEFAULT NULL COMMENT '类型名称',
  `commodity_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `commodity_price` varchar(255) DEFAULT NULL COMMENT '商品价格',
  `commodity_img` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `commodity_old_price` varchar(255) DEFAULT NULL COMMENT '商品旧价',
  `commodity_describe` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `state` varchar(255) DEFAULT NULL COMMENT '状态',
  `commodity_provider` varchar(255) DEFAULT NULL COMMENT '商品发布者',
  `commodity_provider_username` varchar(255) DEFAULT NULL COMMENT '商品发布者名称',
  `commodity_produce_time` varchar(255) DEFAULT NULL COMMENT '上缠日期',
  `commodity_validity` varchar(255) DEFAULT NULL COMMENT '有效期',
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_commodity
-- ----------------------------
BEGIN;
INSERT INTO `base_commodity` VALUES (2, '1', '电器', '测试商品', '123', 'upload/1578806472462.jpg', '123', '这是描述这是描述', NULL, '16', 'zzj', '56', '688', '1578738118', NULL, 'normal');
INSERT INTO `base_commodity` VALUES (3, '1', '电器', 'uug', 'uhg', NULL, 'hhgs', 'hhgs', NULL, '16', 'zzj', 'unhgs', 'hhbhk', '1578751116', NULL, 'delete');
INSERT INTO `base_commodity` VALUES (4, '1', '电器', '交易品', 'ghv', 'upload/1578806587760.jpg', 'bjhjkx', 'jbhjx', NULL, '16', 'zzj', 'hjvhs', 'jbgghs', '1578751581', NULL, 'normal');
INSERT INTO `base_commodity` VALUES (5, '1', '电器', 'hjhhd', 'jhhid', NULL, 'jnhs', 'jjbkjkxxx', NULL, '16', 'zzj', 'ksnbss', 'iznhss', '1578752817', NULL, 'delete');
INSERT INTO `base_commodity` VALUES (6, '1', '电器', '测了可口可乐了', '123', 'upload/1578814823851.jpg', '150', '描述', NULL, '16', 'zzj', '来来来', '12', '1578805893', NULL, 'delete');
INSERT INTO `base_commodity` VALUES (7, '1', '电器', '测试标题22', '123', 'upload/1578840724921.jpg', '400', '描述', NULL, '18', '777', '2019', '10年', '1578840687', NULL, 'delete');
INSERT INTO `base_commodity` VALUES (8, '1', '电器', '苹果笔记本', '1350', 'upload/1581405572402.jpg', '2200', '描述', NULL, '13', '123', '日期', '有效期', '1581327012', NULL, 'normal');
COMMIT;

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
BEGIN;
INSERT INTO `base_menu` VALUES (1, '系统设置', '1', '0', NULL, '', 999, '', '4', '', '', 'delete');
INSERT INTO `base_menu` VALUES (2, '菜单设置', '2', '1', NULL, '../pages/manager/system/menu.html', 1, '', '4', '', '', 'delete');
INSERT INTO `base_menu` VALUES (118, '交易管理', '1', '1', NULL, NULL, 1, NULL, '4', NULL, NULL, 'normal');
INSERT INTO `base_menu` VALUES (119, '用户管理', '1', '1', NULL, NULL, 1, NULL, '4', NULL, NULL, 'normal');
INSERT INTO `base_menu` VALUES (120, '评论管理', '1', '1', NULL, NULL, 1, NULL, '4', NULL, NULL, 'normal');
INSERT INTO `base_menu` VALUES (121, '模块管理', '1', '1', NULL, NULL, 1, NULL, '4', NULL, NULL, 'normal');
INSERT INTO `base_menu` VALUES (122, '交易品管理', '2', '118', NULL, '../pages/manager/base_commodity/list.html', 1, NULL, '4', NULL, NULL, 'normal');
INSERT INTO `base_menu` VALUES (123, '订单管理', '2', '118', NULL, '../pages/manager/base_order/list.html', 1, NULL, '4', NULL, NULL, 'normal');
INSERT INTO `base_menu` VALUES (124, '用户列表', '2', '119', NULL, '../pages/manager/base_user/list.html', 1, NULL, '4', NULL, NULL, 'normal');
INSERT INTO `base_menu` VALUES (125, '用户类型列表', '2', '119', NULL, '../pages/manager/base_user_type/list.html', 1, NULL, '4', NULL, NULL, 'normal');
INSERT INTO `base_menu` VALUES (126, '评论管理', '2', '120', NULL, '../pages/manager/base_comment/list.html', 1, NULL, '4', NULL, NULL, 'normal');
INSERT INTO `base_menu` VALUES (127, '收藏管理', '2', '119', NULL, '../pages/manager/base_collection/list.html', 1, NULL, '4', NULL, NULL, 'normal');
INSERT INTO `base_menu` VALUES (128, '板块列表', '2', '121', NULL, '../pages/manager/base_plate/list.html', 1, NULL, '4', NULL, NULL, 'normal');
INSERT INTO `base_menu` VALUES (129, '留言列表', '2', '119', NULL, '../pages/manager/base_message/list.html', 1, NULL, '4', NULL, NULL, 'normal');
INSERT INTO `base_menu` VALUES (130, '数据统计', '1', '1', NULL, NULL, 1, NULL, '4', NULL, NULL, 'normal');
INSERT INTO `base_menu` VALUES (131, '图表统计', '2', '130', NULL, '../pages/manager/statistics/index.html', 1, NULL, '4', NULL, NULL, 'normal');
COMMIT;

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
BEGIN;
INSERT INTO `base_message` VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, '1575791350', NULL, 'delete');
COMMIT;

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
BEGIN;
INSERT INTO `base_module` VALUES (1, NULL, 'module1', NULL, '1573224661', NULL, 'normal');
INSERT INTO `base_module` VALUES (2, NULL, NULL, NULL, '1573401476', NULL, 'normal');
INSERT INTO `base_module` VALUES (3, NULL, NULL, NULL, '1573401482', NULL, 'normal');
INSERT INTO `base_module` VALUES (4, NULL, NULL, NULL, '1573401496', NULL, 'normal');
INSERT INTO `base_module` VALUES (5, NULL, NULL, NULL, '1573401513', NULL, 'normal');
COMMIT;

-- ----------------------------
-- Table structure for base_order
-- ----------------------------
DROP TABLE IF EXISTS `base_order`;
CREATE TABLE `base_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `party_a` varchar(255) DEFAULT NULL COMMENT '卖方',
  `party_b` varchar(255) DEFAULT NULL COMMENT '买方',
  `commodity_id` varchar(255) DEFAULT NULL COMMENT '商品',
  `status` varchar(255) DEFAULT NULL COMMENT '状态,完成或未完成',
  `money` varchar(255) DEFAULT NULL COMMENT '总价',
  `shipping_address_id` varchar(255) DEFAULT NULL,
  `shipping_address_detail` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL COMMENT '详细',
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_order
-- ----------------------------
BEGIN;
INSERT INTO `base_order` VALUES (3, '16', '13', '2', 'complete', '123', '1', '广东省深圳市南山区天后博物馆', NULL, '1581404597', NULL, 'normal');
COMMIT;

-- ----------------------------
-- Table structure for base_order_state
-- ----------------------------
DROP TABLE IF EXISTS `base_order_state`;
CREATE TABLE `base_order_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` varchar(255) DEFAULT NULL COMMENT 'moduleid',
  `state` varchar(255) DEFAULT NULL COMMENT '状态',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_order_state
-- ----------------------------
BEGIN;
INSERT INTO `base_order_state` VALUES (1, '3', 'create', '1581404597', NULL, 'delete');
INSERT INTO `base_order_state` VALUES (2, '3', 'hbggd', '1581609522', NULL, 'delete');
INSERT INTO `base_order_state` VALUES (3, '3', 'nbhd', '1581609528', NULL, 'delete');
INSERT INTO `base_order_state` VALUES (4, '3', 'cgjh', '1581609599', NULL, 'delete');
COMMIT;

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
BEGIN;
INSERT INTO `base_plate` VALUES (1, '电器', '1575791300', NULL, 'normal');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user
-- ----------------------------
BEGIN;
INSERT INTO `base_user` VALUES (7, '456', '456', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '教师', NULL, NULL, '1572004895', NULL, 'delete');
INSERT INTO `base_user` VALUES (8, 'admin', 'admin', '系统管理员', '134126', 'email', '联系地址', 'wchat', 'qq', '26', '', 'hello', '地区', '4', '系统管理员', NULL, NULL, NULL, NULL, 'normal');
INSERT INTO `base_user` VALUES (9, '123', '123', '周先生', '电话', 'email', '地址', '13413739238923', 'qq号', '21', NULL, '签名', '广东', '1', '学生', NULL, NULL, '1572147442', NULL, 'delete');
INSERT INTO `base_user` VALUES (12, '789', '789', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '学生', NULL, NULL, '1572150642', NULL, 'delete');
INSERT INTO `base_user` VALUES (13, '123', '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'ѧ��', NULL, NULL, '1575559080', NULL, 'normal');
INSERT INTO `base_user` VALUES (16, 'zzj', 'zzj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'upload/1578806713780.jpg', NULL, NULL, '1', 'ѧ��', NULL, NULL, '1578733159', NULL, 'normal');
INSERT INTO `base_user` VALUES (17, 'tt', 'tt', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'upload/1578839709815.jpg', NULL, NULL, '1', 'ѧ��', NULL, NULL, '1578839699', NULL, 'normal');
INSERT INTO `base_user` VALUES (18, '777', '777', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'upload/1578840782215.jpg', NULL, NULL, '1', 'ѧ��', NULL, NULL, '1578840615', NULL, 'normal');
COMMIT;

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
BEGIN;
INSERT INTO `base_user_type` VALUES (1, '1', NULL, '学生', NULL, NULL, 'delete');
INSERT INTO `base_user_type` VALUES (2, '2', NULL, '教师', NULL, NULL, 'normal');
INSERT INTO `base_user_type` VALUES (3, '3', NULL, '管理员', NULL, NULL, 'delete');
INSERT INTO `base_user_type` VALUES (4, '4', NULL, '管理员', NULL, NULL, 'normal');
COMMIT;

-- ----------------------------
-- Table structure for base_wishlist
-- ----------------------------
DROP TABLE IF EXISTS `base_wishlist`;
CREATE TABLE `base_wishlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plate_id` varchar(255) DEFAULT NULL COMMENT '板块id',
  `plate_name` varchar(255) DEFAULT NULL COMMENT '类型名称',
  `wish_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `wish_price` varchar(255) DEFAULT NULL COMMENT '商品价格',
  `wish_describe` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `wish_provider` varchar(255) DEFAULT NULL COMMENT '商品发布者',
  `wish_provider_username` varchar(255) DEFAULT NULL COMMENT '商品发布者名称',
  `wish_provider_avatar` varchar(255) DEFAULT NULL COMMENT '发布者头像',
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_wishlist
-- ----------------------------
BEGIN;
INSERT INTO `base_wishlist` VALUES (7, '1', '电器', 'blog', '120', '去看', '16', 'zzj', 'upload/1578806713780.jpg', '1578829045', NULL, 'delete');
INSERT INTO `base_wishlist` VALUES (8, '1', '电器', '恶魔来了', '寄了', '龙', '16', 'zzj', 'upload/1578806713780.jpg', '1578830607', NULL, 'delete');
INSERT INTO `base_wishlist` VALUES (9, '1', '电器', '来来来', '120', '描述', '16', 'zzj', 'upload/1578806713780.jpg', '1578838432', NULL, 'normal');
INSERT INTO `base_wishlist` VALUES (10, '1', '电器', '标题', '130', '描述', '18', '777', 'null', '1578840769', NULL, 'normal');
COMMIT;

-- ----------------------------
-- Table structure for shipping_address
-- ----------------------------
DROP TABLE IF EXISTS `shipping_address`;
CREATE TABLE `shipping_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `is_default` varchar(255) DEFAULT NULL COMMENT '默认地址',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shipping_address
-- ----------------------------
BEGIN;
INSERT INTO `shipping_address` VALUES (1, '13', '广东省', '深圳市', '南山区天后博物馆', NULL, '1581325612', NULL, 'normal');
INSERT INTO `shipping_address` VALUES (2, '13', '广东省', '广州市', '天河城百货', NULL, '1581326232', NULL, 'delete');
COMMIT;

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

SET FOREIGN_KEY_CHECKS = 1;
