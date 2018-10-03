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

 Date: 03/10/2018 20:29:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_manage_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_manage_user`;
CREATE TABLE `admin_manage_user` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `admin_id` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `user_id` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
