/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : autotest

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-29 18:18:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for a_account
-- ----------------------------
DROP TABLE IF EXISTS `a_account`;
CREATE TABLE `a_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `token` varchar(1) COLLATE utf8_bin NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `memo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
