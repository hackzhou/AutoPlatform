/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : autotest

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-29 18:19:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for a_task
-- ----------------------------
DROP TABLE IF EXISTS `a_task`;
CREATE TABLE `a_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `version_id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `run_flag` int(1) NOT NULL,
  `run_time` varchar(5) COLLATE utf8_bin NOT NULL,
  `createby` varchar(255) COLLATE utf8_bin NOT NULL,
  `runby` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `memo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
