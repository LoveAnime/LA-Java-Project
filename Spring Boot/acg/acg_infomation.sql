/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : manage

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-09-25 10:00:13
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `acg_infomation`
-- ----------------------------
DROP TABLE IF EXISTS `acg_infomation`;
CREATE TABLE `acg_infomation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `acg_name` varchar(32) NOT NULL COMMENT '名称',
  `acg_author` varchar(32) NOT NULL COMMENT '作者',
  `episode` int(10) NOT NULL DEFAULT '0' COMMENT '集数',
  `issue_time` datetime DEFAULT NULL COMMENT '发行时间',
  `completed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否完结',
  `country` varchar(32) DEFAULT NULL COMMENT '国家',
  `acg_category` varchar(32) DEFAULT NULL COMMENT '分类',
  `watch_able` varchar(32) DEFAULT NULL COMMENT '是否可以观看',
  `memo` varchar(32) DEFAULT NULL COMMENT '备注信息',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable_flag` char(1) DEFAULT 'Y',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='acg信息表';

-- ----------------------------
-- Records of acg_infomation
-- ----------------------------
