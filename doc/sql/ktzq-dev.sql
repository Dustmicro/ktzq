/*
 Navicat Premium Data Transfer

 Source Server         : ktzq-dev
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 116.63.133.52:3306
 Source Schema         : ktzq-dev

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 08/02/2023 08:57:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for college_member
-- ----------------------------
DROP TABLE IF EXISTS `college_member`;
CREATE TABLE `college_member`  (
  `member_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '成员id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '成员姓名',
  `age` int(0) NOT NULL COMMENT '年龄',
  `sex` int(0) NOT NULL COMMENT '性别',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `college_id` int(0) NOT NULL COMMENT '球队id',
  `member_type_cd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '成员类型',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '开始时间',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据状态',
  `status_cd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态',
  PRIMARY KEY (`member_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '球队成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu_role_mapping
-- ----------------------------
DROP TABLE IF EXISTS `menu_role_mapping`;
CREATE TABLE `menu_role_mapping`  (
  `id` int(0) NOT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  `menu_id` int(0) NULL DEFAULT NULL,
  `status_cd` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pay_sub
-- ----------------------------
DROP TABLE IF EXISTS `pay_sub`;
CREATE TABLE `pay_sub`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `sub_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '科目名称',
  `sub_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '科目类型',
  `sub_count` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计费单价',
  `sub_pay` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '附加/固定费用',
  `sub_time` datetime(0) NULL DEFAULT NULL COMMENT '缴费周期',
  `sub_mark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `status_cd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '数据状态：0有效；1失效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '缴费科目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for record_day
-- ----------------------------
DROP TABLE IF EXISTS `record_day`;
CREATE TABLE `record_day`  (
  `record_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '考勤id',
  `member_id` int(0) NOT NULL COMMENT ' 成员id',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '考勤状态：0正常；1迟到；2缺席',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `mark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `status_cd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '数据状态：0有效；1失效',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '每日考勤表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for u_admin
-- ----------------------------
DROP TABLE IF EXISTS `u_admin`;
CREATE TABLE `u_admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员名称',
  `college_id` int(0) NULL DEFAULT NULL COMMENT '球队id',
  `college_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '球队名称',
  `aere_id` int(0) NULL DEFAULT NULL COMMENT '区域id',
  `aere_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区域名称',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据状态',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据状态：3锁定，1恢复',
  `status_cd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态：0有效，1失效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_admin
-- ----------------------------
INSERT INTO `u_admin` VALUES (1, '黄弋峰', 1, '信息技术学院', NULL, NULL, 1, '超级管理员', '18781166142', '四川省绵阳市', NULL, NULL, '0');

-- ----------------------------
-- Table structure for u_aere
-- ----------------------------
DROP TABLE IF EXISTS `u_aere`;
CREATE TABLE `u_aere`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `aere_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区域编号',
  `aere_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区域名称',
  `user_id` int(0) NOT NULL COMMENT '区域负责人',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区域负责人名称',
  `permissions` int(0) NULL DEFAULT NULL COMMENT '权限（XP00）',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区域负责人电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status_cd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态：0有效；1失效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_aere
-- ----------------------------
INSERT INTO `u_aere` VALUES (1, 'XCXY000', '西昌学院', 1, '黄弋峰', 1, '18781166142', '四川省凉山彝族自治州西昌市', '2022-12-02 10:06:20', '0');
INSERT INTO `u_aere` VALUES (2, 'CH001', '成都', 1, '黄弋峰', 1, '18781166142', '四川省成都市双流区', '2022-12-04 00:02:19', '0');

-- ----------------------------
-- Table structure for u_college
-- ----------------------------
DROP TABLE IF EXISTS `u_college`;
CREATE TABLE `u_college`  (
  `college_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `college_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学院编号',
  `college_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学院名称',
  `aere_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区域编号',
  `aere_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区域名称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `addressXQ` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址详情',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status_cd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态：0有效；1失效',
  PRIMARY KEY (`college_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学院信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_college
-- ----------------------------
INSERT INTO `u_college` VALUES (1, 'XXJSXY000', '信息技术学院', 'XCXY000', '西昌学院', '四川省凉山彝族自治州西昌市', '18781166142', NULL, '2022-12-02 09:58:41', '0');

-- ----------------------------
-- Table structure for u_dept
-- ----------------------------
DROP TABLE IF EXISTS `u_dept`;
CREATE TABLE `u_dept`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT ' ',
  `dept_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '部门id',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '部门名称',
  `college_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '学院编号',
  `aere_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '区域编号',
  `status_cd` int(0) NULL DEFAULT 0 COMMENT '数据状态：0有效；1失效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_dept
-- ----------------------------
INSERT INTO `u_dept` VALUES (1, 'XXBM09', '安保部', 'XX000', NULL, 0);

-- ----------------------------
-- Table structure for u_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `u_dictionary`;
CREATE TABLE `u_dictionary`  (
  `dic_id` int(0) NOT NULL AUTO_INCREMENT,
  `dic_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典值',
  `dic_type_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典值编号',
  `status_cd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '字典值状态：0有效；1失效',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '描述',
  PRIMARY KEY (`dic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_dictionary
-- ----------------------------
INSERT INTO `u_dictionary` VALUES (1, 'XP00', '0', '0', NULL, '2022-11-17 00:20:47', '游客');
INSERT INTO `u_dictionary` VALUES (2, 'memberType', '1', '0', '2022-12-13 06:22:40', '2022-11-17 00:19:35', '队长');
INSERT INTO `u_dictionary` VALUES (3, 'memberType', '2', '0', '2022-12-13 06:22:50', '2022-11-17 19:32:47', '副队长');
INSERT INTO `u_dictionary` VALUES (4, 'memberType', '3', '0', '2022-12-13 06:22:52', '2022-11-17 19:33:13', '教练');
INSERT INTO `u_dictionary` VALUES (5, 'memberType', '4', '0', '2022-12-13 06:23:00', '2022-11-17 19:33:39', '队员');
INSERT INTO `u_dictionary` VALUES (6, 'XP00', '6', '0', '2022-12-07 09:13:05', '2022-12-07 07:16:42', '经理');
INSERT INTO `u_dictionary` VALUES (7, 'XP00', '5', '0', '2022-12-07 09:12:52', '2022-12-07 08:58:39', '普通员工');
INSERT INTO `u_dictionary` VALUES (8, 'pwdErrNumLock', '3', '0', '2022-12-16 08:09:45', '2022-12-16 07:57:52', '允许密码错误最大次数');

-- ----------------------------
-- Table structure for u_operationLog
-- ----------------------------
DROP TABLE IF EXISTS `u_operationLog`;
CREATE TABLE `u_operationLog`  (
  `operation_id` int(0) NOT NULL,
  `user_id` int(0) NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `service_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `service_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `oprate_time` timestamp(0) NULL DEFAULT NULL,
  `start_time` timestamp(0) NULL DEFAULT NULL,
  `end_time` timestamp(0) NULL DEFAULT NULL,
  `college_id` int(0) NULL DEFAULT NULL,
  `aere_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`operation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for u_players
-- ----------------------------
DROP TABLE IF EXISTS `u_players`;
CREATE TABLE `u_players`  (
  `players_id` int(0) NOT NULL COMMENT '队员id',
  `players_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '队员编号',
  `college_id` int(0) NOT NULL COMMENT '所属球队id',
  `players_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `age` int(0) NOT NULL COMMENT '年龄',
  `sex` int(0) NOT NULL COMMENT '性别',
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `status_cd` int(0) NULL DEFAULT 0 COMMENT '数据状态',
  PRIMARY KEY (`players_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '球队成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for u_program
-- ----------------------------
DROP TABLE IF EXISTS `u_program`;
CREATE TABLE `u_program`  (
  `program_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `program_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日程名称',
  `program_type` int(0) NOT NULL COMMENT '日程类型（RCLX001）',
  `program_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日程内容',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人电话',
  `parent_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上级id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `status_cd` int(0) NULL DEFAULT 0 COMMENT '数据状态：0有效；1失效',
  PRIMARY KEY (`program_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '日程安排表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_program
-- ----------------------------
INSERT INTO `u_program` VALUES (1, '与土木工程学院进行友谊赛', 1, '本周六上午10点，将与土木工程学院进行一场友谊赛，请各位球员准时到场！', '黄弋峰', '18781166142', NULL, '2022-12-06 14:14:29', '2022-12-06 14:14:29', 0);
INSERT INTO `u_program` VALUES (2, '友谊赛', 1, '本周五上午10点，将与教职工进行一场友谊赛，请各位球员准时到场！', '黄弋峰', '18781166142', 'XXJSXY000', '2022-12-06 15:07:38', '2022-12-06 15:07:38', 0);

-- ----------------------------
-- Table structure for u_role
-- ----------------------------
DROP TABLE IF EXISTS `u_role`;
CREATE TABLE `u_role`  (
  `role_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `college_id` int(0) NULL DEFAULT NULL,
  `aere_id` int(0) NULL DEFAULT NULL,
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `mark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `reserve` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status_cd` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_role
-- ----------------------------
INSERT INTO `u_role` VALUES (1, '超级管理员', 1, NULL, '1', NULL, NULL, 0);

-- ----------------------------
-- Table structure for u_staff
-- ----------------------------
DROP TABLE IF EXISTS `u_staff`;
CREATE TABLE `u_staff`  (
  `staff_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `user_id` int(0) NOT NULL,
  `staff_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工编号',
  `staff_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工姓名',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工电话',
  `parent_dept` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '上级组织名称',
  `college_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `aere_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_id` int(0) NOT NULL COMMENT '员工岗位(XP00)',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sex` int(0) NOT NULL COMMENT '性别',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `status_cd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态：0有效；1失效',
  `age` int(0) NOT NULL,
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '员工信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '成员id',
  `log_id` int(0) NULL DEFAULT NULL COMMENT '标识id',
  `user_type_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '成员类型id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `age` int(0) NOT NULL COMMENT '年龄',
  `tel` bigint(0) NOT NULL COMMENT '电话',
  `sex` double(2, 0) NOT NULL COMMENT '性别：1女；2男',
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '身份证',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `psw_err_num` int(0) NULL DEFAULT NULL COMMENT '密码错误次数',
  `role_id` int(0) NOT NULL DEFAULT 0 COMMENT '权限（XP00）',
  `college_id` int(0) NULL DEFAULT 0 COMMENT '学院编号',
  `college_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '达摩学院' COMMENT '学院名称',
  `aere_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '区域编号',
  `aere_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '区域名称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '住址',
  `e_mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '邮箱',
  `u_mark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '描述',
  `status_cd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '成员状态：0有效；1失效',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES (1, NULL, '', '黄弋峰', 22, 18781166142, 2, '510704200002111877', '18781166142', '000000', 0, 1, 1, '信息技术学院', NULL, NULL, '四川省绵阳市', '1648114703@qq.com', '已退役', '0', '2022-11-17 19:27:00', '2023-01-04 05:41:38');
INSERT INTO `u_user` VALUES (2, NULL, '', '师延正', 22, 18780000000, 2, '510704200002111872', '18780000000', '000000', 0, 0, 1, '达摩学院', NULL, NULL, '四川省德阳市', '8208208820@qq.com', NULL, '0', '2022-12-05 19:03:42', '2023-01-04 05:41:46');
INSERT INTO `u_user` VALUES (3, NULL, NULL, '叶阳', 22, 18799999999, 2, '510704200002111874', 'yeyang1999', '000000', NULL, 1, 1, '信息技术学院', NULL, NULL, '四川省成都市', 'yeyang1999@qq.com', '开发者用户', '0', '2022-12-17 04:51:16', '2023-01-04 05:41:49');

-- ----------------------------
-- Table structure for u_work
-- ----------------------------
DROP TABLE IF EXISTS `u_work`;
CREATE TABLE `u_work`  (
  `w_id` int(0) NOT NULL AUTO_INCREMENT,
  `w_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '职务名称',
  `status_cd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`w_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '职务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role_mapping
-- ----------------------------
DROP TABLE IF EXISTS `user_role_mapping`;
CREATE TABLE `user_role_mapping`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `role_id` int(0) NOT NULL,
  `status_cd` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
