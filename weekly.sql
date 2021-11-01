/*
 Navicat Premium Data Transfer

 Source Server         : 滕如坤
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : weekly

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 01/11/2021 15:14:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `owner_id` int(255) NULL DEFAULT NULL,
  `owner_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `new_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ext` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `post_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES (5, 1, '滕如坤', '1毕业设计选题审题表-新.doc', '202110280950505bfa17cdbb0945cca875d89c52b9c448.doc', '.doc', '/files/2021-10-28', '44544', 'application/msword', '2021-10-28 09:50:50');
INSERT INTO `file` VALUES (6, 1, '滕如坤', '1班.xlsx', '202110280951229c5538d79d484ec6830a5b1d421edd43.xlsx', '.xlsx', '/files/2021-10-28', '14517', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', '2021-10-28 09:51:23');
INSERT INTO `file` VALUES (7, 2, '锤子', '获奖名单.xls', '202110280952400ece885deadc4de1bef72a2c435e6dc1.xls', '.xls', '/files/2021-10-28', '406016', 'application/vnd.ms-excel', '2021-10-28 09:52:41');
INSERT INTO `file` VALUES (8, 2, '锤子', '应急简历.zip', '202110280953435096587eae66442991126487a468153f.zip', '.zip', '/files/2021-10-28', '6258034', 'application/x-zip-compressed', '2021-10-28 09:53:44');
INSERT INTO `file` VALUES (9, 1, '滕如坤', '光盘.zip', '2021102810395382843674e30f407e8806d8c3e96923d8.zip', '.zip', '/files/2021-10-28', '13', 'application/x-zip-compressed', '2021-10-28 10:39:53');
INSERT INTO `file` VALUES (10, 1, '滕如坤', '光盘.zip', '20211028105012f68a5677f41e4e18bbb35f6cff08cd31.zip', '.zip', '/files/2021-10-28', '13.88', 'application/x-zip-compressed', '2021-10-28 10:50:12');
INSERT INTO `file` VALUES (11, 1, '滕如坤', '李志 - 米店.mp3', '20211029102011e9741b8e6b3f47cd871ee967360a4a11.mp3', '.mp3', '/files/2021-10-29', '8.18', 'audio/mpeg', '2021-10-29 10:20:11');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (21, '开会', '线上');
INSERT INTO `notice` VALUES (24, '开会', '学研1004');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(100) UNSIGNED NOT NULL AUTO_INCREMENT,
  `study_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '7210365', '滕如坤', '666');
INSERT INTO `student` VALUES (2, '201707239', '锤子', '666');
INSERT INTO `student` VALUES (5, '201707238', '钟运', '666');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '王建新', '666');

-- ----------------------------
-- Table structure for weekly
-- ----------------------------
DROP TABLE IF EXISTS `weekly`;
CREATE TABLE `weekly`  (
  `id` int(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `owner_id` int(255) UNSIGNED NOT NULL,
  `owner_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `study_progress` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `daily_progress` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `total_time` int(255) NOT NULL COMMENT '学习总时长',
  `time_allocation` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '时间分配情况',
  `experience` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '心得体会',
  `post_time` datetime(0) NOT NULL COMMENT '提交时间',
  `comment` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '导师评价',
  `judge_time` datetime(0) NULL DEFAULT NULL COMMENT '审批时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of weekly
-- ----------------------------
INSERT INTO `weekly` VALUES (43, 5, '钟运', '我叫拐哥', '我叫拐哥', 1, '我叫拐哥', '我叫拐哥', '2021-11-01 10:27:47', '收到', '2021-11-01 10:27:59');
INSERT INTO `weekly` VALUES (44, 1, '滕如坤', '周报测试', '周报测试', 6, '周报测试', '周报测试', '2021-11-01 11:05:49', NULL, NULL);
INSERT INTO `weekly` VALUES (45, 1, '滕如坤', '周报测试2.0', '周报测试2.0', 66, '周报测试2.0', '周报测试2.0', '2021-11-01 11:07:38', NULL, NULL);
INSERT INTO `weekly` VALUES (46, 1, '滕如坤', '周报测试3.0', '周报测试3.0', 5, '周报测试3.0', '周报测试3.0', '2021-11-01 11:07:52', NULL, NULL);
INSERT INTO `weekly` VALUES (47, 2, '锤子', '测试', '测试', 41, '测试', '测试', '2021-11-01 11:10:01', '收到', '2021-11-01 15:12:21');
INSERT INTO `weekly` VALUES (48, 2, '锤子', '测试测试', '测试测试', 50, '测试测试', '测试测试', '2021-11-01 11:14:52', NULL, NULL);
INSERT INTO `weekly` VALUES (49, 2, '锤子', '测试测试', '测试测试', 55, '测试测试', '测试测试', '2021-11-01 11:16:57', NULL, NULL);
INSERT INTO `weekly` VALUES (50, 2, '锤子', '测试测试', '测试测试', 72, '测试测试', '测试测试', '2021-11-01 11:17:05', '收到', '2021-11-01 15:12:15');
INSERT INTO `weekly` VALUES (51, 1, '滕如坤', '测试测试', '测试测试', 60, '测试测试', '测试测试', '2021-11-01 15:10:51', NULL, NULL);
INSERT INTO `weekly` VALUES (52, 1, '滕如坤', '测试测试', '测试测试', 10, '测试测试', '测试测试', '2021-11-01 15:12:05', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
