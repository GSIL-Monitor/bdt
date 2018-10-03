/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:8889
 Source Schema         : bjl

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 04/10/2018 06:26:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tz_system
-- ----------------------------
DROP TABLE IF EXISTS `tz_system`;
CREATE TABLE `tz_system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tzxt` int(2) DEFAULT NULL,
  `started` tinyint(1) DEFAULT '0',
  `fh` int(2) DEFAULT NULL,
  `xh` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tzxt_unique` (`tzxt`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of tz_system
-- ----------------------------
BEGIN;
INSERT INTO `tz_system` VALUES (1, 1, 0, NULL, NULL);
INSERT INTO `tz_system` VALUES (2, 2, 0, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
