/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : acg

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 23/11/2019 17:35:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acg_infomation
-- ----------------------------
DROP TABLE IF EXISTS `acg_infomation`;
CREATE TABLE `acg_infomation`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `acg_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `acg_author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作者',
  `episode` int(10) NOT NULL DEFAULT 0 COMMENT '集数',
  `issue_time` datetime(0) NULL DEFAULT NULL COMMENT '发行时间',
  `completed` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否完结：0否 1是',
  `country` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国家',
  `acg_category` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类',
  `watch_able` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否可以观看：0否 1是',
  `memo` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `enable_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'Y' COMMENT '有效标志位：Y N',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'acg信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acg_infomation
-- ----------------------------
INSERT INTO `acg_infomation` VALUES (1, 'One Piece', '尾田荣一郎', 900, '1997-11-23 17:03:43', '已完结', '日本', '热血', '有', NULL, NULL, '2019-11-23 17:04:36', '2019-11-23 09:34:46', 'Y');
INSERT INTO `acg_infomation` VALUES (2, '秦时明月', '玄机', 200, NULL, '连载中', '中国', '历史', '有', NULL, NULL, '2019-11-23 09:34:23', '2019-11-23 09:34:42', 'Y');

SET FOREIGN_KEY_CHECKS = 1;
