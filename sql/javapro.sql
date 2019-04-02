/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : javapro

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-09-19 18:12:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `BookId` int(50) NOT NULL AUTO_INCREMENT,
  `BookName` varchar(20) NOT NULL,
  `Writter` varchar(20) NOT NULL,
  `BookType` varchar(20) NOT NULL,
  `Price` double(255,2) NOT NULL,
  `IsBorrow` varchar(20) NOT NULL DEFAULT '否',
  PRIMARY KEY (`BookId`),
  KEY `BookId` (`BookId`,`BookName`)
) ENGINE=InnoDB AUTO_INCREMENT=20180921 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('20180901', '时间简史', '史蒂芬.霍金', '科技类', '98.00', '否');
INSERT INTO `book` VALUES ('20180903', '你坏', '大冰', '青春文学', '38.80', '否');
INSERT INTO `book` VALUES ('20180904', '阿弥陀佛么么哒', '大冰', '青春文学', '38.80', '是');
INSERT INTO `book` VALUES ('20180906', '他们最善良', '大冰', '青春文学', '38.80', '否');
INSERT INTO `book` VALUES ('20180911', '乖，摸摸头', '大冰', '青春文学', '38.60', '否');
INSERT INTO `book` VALUES ('20180915', '天工开物', '宋应星', '综合性', '78.00', '否');
INSERT INTO `book` VALUES ('20180916', '荣格', '尼采', '哲学', '58.00', '是');
INSERT INTO `book` VALUES ('20180917', 'Java从入门到精通', '国家863中部软件孵化器', '技术类', '98.00', '否');
INSERT INTO `book` VALUES ('20180918', '狂人日记', '鲁迅', '文学类', '62.00', '是');
INSERT INTO `book` VALUES ('20180919', '走遍中国', '阿纲', '人文自然', '36.90', '否');
INSERT INTO `book` VALUES ('20180920', '呐喊', '鲁迅', '文学类', '67.00', '否');

-- ----------------------------
-- Table structure for `borrow`
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `BorrowId` int(20) NOT NULL AUTO_INCREMENT,
  `BookId` int(20) NOT NULL,
  `BookName` varchar(50) NOT NULL,
  `BookType` varchar(20) NOT NULL,
  `userid` int(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `BorrowTime` date NOT NULL,
  `ReturnTime` date NOT NULL,
  `IsReturn` varchar(10) NOT NULL DEFAULT '否',
  PRIMARY KEY (`BorrowId`),
  KEY `userid` (`userid`,`username`),
  KEY `BookId` (`BookId`,`BookName`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('5', '20180903', '你坏', '青春文学', '180901', 'aaa', '2018-09-12', '2018-09-12', '是');
INSERT INTO `borrow` VALUES ('6', '20180906', '他们最善良', '青春文学', '180901', 'aaa', '2018-09-13', '2018-09-14', '是');
INSERT INTO `borrow` VALUES ('7', '20180901', '时间简史', '科技类', '180901', 'aaa', '2018-09-13', '2018-09-13', '是');
INSERT INTO `borrow` VALUES ('8', '20180920', '呐喊', '文学类', '180902', 'bbb', '2018-09-13', '2018-09-13', '是');
INSERT INTO `borrow` VALUES ('9', '20180916', '荣格', '哲学', '180902', 'bbb', '2018-09-13', '2018-09-13', '是');
INSERT INTO `borrow` VALUES ('10', '20180917', 'Java从入门到精通', '技术类', '180902', 'bbb', '2018-09-13', '2018-09-14', '是');
INSERT INTO `borrow` VALUES ('11', '20180915', '天工开物', '综合性', '180902', 'bbb', '2018-09-13', '2018-09-14', '是');
INSERT INTO `borrow` VALUES ('12', '20180919', '走遍中国', '人文自然', '180902', 'bbb', '2018-09-13', '2018-09-14', '是');
INSERT INTO `borrow` VALUES ('13', '20180920', '呐喊', '文学类', '180902', 'bbb', '2018-09-14', '2018-09-14', '是');
INSERT INTO `borrow` VALUES ('14', '20180918', '狂人日记', '文学类', '180902', 'bbb', '2018-09-14', '2018-10-14', '否');
INSERT INTO `borrow` VALUES ('15', '20180904', '阿弥陀佛么么哒', '青春文学', '180901', 'aaa', '2018-09-14', '2018-10-14', '否');
INSERT INTO `borrow` VALUES ('16', '20180916', '荣格', '哲学', '180901', 'aaa', '2018-09-14', '2018-10-14', '否');

-- ----------------------------
-- Table structure for `root`
-- ----------------------------
DROP TABLE IF EXISTS `root`;
CREATE TABLE `root` (
  `rootID` int(20) NOT NULL AUTO_INCREMENT,
  `rootName` varchar(50) NOT NULL,
  `rootPWD` varchar(20) NOT NULL,
  PRIMARY KEY (`rootID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of root
-- ----------------------------
INSERT INTO `root` VALUES ('1', 'admin', '1234');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `userpsw` varchar(20) NOT NULL,
  `useremail` varchar(20) NOT NULL,
  `useraddr` varchar(100) NOT NULL,
  `userphone` varchar(20) NOT NULL,
  PRIMARY KEY (`userid`),
  KEY `userid` (`userid`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=180904 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('180901', 'aaa', '123', '123@163.com', '中国成都', '12345678901');
INSERT INTO `user` VALUES ('180902', 'bbb', '456', '112233@163.com', '中国-四川-巴中', '12345678123');
