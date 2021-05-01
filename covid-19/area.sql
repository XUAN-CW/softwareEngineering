/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50649
 Source Host           : localhost:3306
 Source Schema         : covid

 Target Server Type    : MySQL
 Target Server Version : 50649
 File Encoding         : 65001

 Date: 01/05/2021 23:21:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area`  (
  `id` bigint(11) DEFAULT 0,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parentId` int(255) DEFAULT NULL,
  `updateTime` datetime(0) DEFAULT '0000-00-00 00:00:00',
  `currentConfirm` int(255) DEFAULT NULL,
  `confirm` int(255) DEFAULT NULL,
  `suspect` int(255) DEFAULT NULL,
  `cure` int(255) DEFAULT NULL,
  `dead` int(255) DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
