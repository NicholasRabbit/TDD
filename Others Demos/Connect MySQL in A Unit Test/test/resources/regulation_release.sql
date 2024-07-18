/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.0.159-博英本地
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 192.168.0.159:3306
 Source Schema         : online_course

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 18/07/2024 10:04:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for regulaiton_release
-- ----------------------------
DROP TABLE IF EXISTS `regulaiton_release`;
CREATE TABLE `regulaiton_release`  (
  `id` bigint(0) NOT NULL,
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请人',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '提交时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(6) NULL DEFAULT NULL COMMENT '修改时间',
  `category` tinyint(1) NULL DEFAULT 0 COMMENT '分类',
  `compId` bigint(0) NULL DEFAULT NULL COMMENT '部门id',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '制度内容',
  `key_word` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关键字',
  `review_by` bigint(0) NULL DEFAULT NULL COMMENT '审核人id',
  `review_date` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '制度审核状态：0，待审核；1，审核通过；2.审核未通过',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '制度标题',
  `comp_id` bigint(0) NULL DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '制度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of regulaiton_release
-- ----------------------------
INSERT INTO `regulaiton_release` VALUES (1001, NULL, NULL, '0', NULL, NULL, 0, NULL, '测试内容', NULL, NULL, NULL, 0, '测试标题', NULL);

SET FOREIGN_KEY_CHECKS = 1;
