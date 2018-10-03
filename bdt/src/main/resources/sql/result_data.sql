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

 Date: 04/10/2018 06:27:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for result_data
-- ----------------------------
DROP TABLE IF EXISTS `result_data`;
CREATE TABLE `result_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `table_no` int(2) DEFAULT NULL,
  `battle_no` int(10) DEFAULT NULL,
  `fit_no` int(10) DEFAULT NULL,
  `tzfx` int(1) DEFAULT NULL COMMENT '庄1 闲2',
  `tzxt` int(1) DEFAULT NULL COMMENT '1TZ1 2TZ2',
  `tzzh` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `tzje` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `tzzt` tinyint(1) DEFAULT NULL COMMENT '1-成功 0-失败',
  `tzjg` int(1) DEFAULT NULL COMMENT '1-0.95 2-0 3--1 4-1',
  `yxje` decimal(10,2) DEFAULT NULL,
  `yssy` decimal(10,0) DEFAULT NULL,
  `sjsy` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
