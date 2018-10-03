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

 Date: 03/10/2018 20:30:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for table_data
-- ----------------------------
DROP TABLE IF EXISTS `table_data`;
CREATE TABLE `table_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `table_no` int(2) DEFAULT NULL,
  `battle_no` int(10) DEFAULT NULL,
  `fit_no` int(2) DEFAULT NULL,
  `card` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `result` int(1) DEFAULT NULL COMMENT '庄1 闲2 和3',
  `xgl` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `zgl` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `xtsl` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `ztsl` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
