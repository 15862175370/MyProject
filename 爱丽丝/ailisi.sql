/*
Navicat MySQL Data Transfer

Source Server         : ailisi
Source Server Version : 50617
Source Host           : 127.0.0.1:3306
Source Database       : ailisi

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-06-20 09:08:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `act_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(20) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`act_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', '本月理发师排名', 'http://192.168.1.106:8080/Alisi2/images/huodong1.png', '2016-04-26 21:35:10');
INSERT INTO `activity` VALUES ('2', '美发小百科', 'http://192.168.1.106:8080/Alisi2/images/huodong2.png', '2016-04-26 21:35:14');
INSERT INTO `activity` VALUES ('3', '头发少怎么办，做好几步还你浓密美发', 'http://192.168.1.106:8080/Alisi2/images/huodong3.png', '2016-04-26 21:35:18');
INSERT INTO `activity` VALUES ('4', '头皮屑多怎么办偏食多增风险', 'http://192.168.1.106:8080/Alisi2/images/huodong4.png', '2016-04-26 21:35:22');
INSERT INTO `activity` VALUES ('5', 'dfdfsdfds', 'http://192.168.1.106:8080/Alisi2/images/huodong4.png', '2016-04-26 21:35:26');

-- ----------------------------
-- Table structure for activitycoment
-- ----------------------------
DROP TABLE IF EXISTS `activitycoment`;
CREATE TABLE `activitycoment` (
  `act_com_id` int(12) NOT NULL AUTO_INCREMENT,
  `m_act_id` int(12) DEFAULT NULL,
  `content` varchar(140) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `u_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`act_com_id`),
  KEY `fk1` (`m_act_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `activitycoment_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk1` FOREIGN KEY (`m_act_id`) REFERENCES `activity` (`act_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activitycoment
-- ----------------------------
INSERT INTO `activitycoment` VALUES ('1', '1', '哈哈', '2015-10-21 15:06:59', '1');
INSERT INTO `activitycoment` VALUES ('138', '1', '好棒！', '2015-10-31 15:57:46', '1');
INSERT INTO `activitycoment` VALUES ('139', '1', '', '2015-10-31 16:30:22', '1');
INSERT INTO `activitycoment` VALUES ('140', '1', '感觉良好', '2015-10-31 18:49:13', '1');
INSERT INTO `activitycoment` VALUES ('141', '1', '李伟', '2015-11-02 14:00:33', '1');
INSERT INTO `activitycoment` VALUES ('142', '1', '哈哈哈哈', '2015-11-02 14:00:51', '1');
INSERT INTO `activitycoment` VALUES ('143', '1', '我很喜欢第一个理发师', '2015-11-02 14:08:00', '1');
INSERT INTO `activitycoment` VALUES ('144', '1', '', '2015-11-02 14:08:00', '1');
INSERT INTO `activitycoment` VALUES ('145', '1', '扣女想我', '2015-11-02 14:18:55', '1');
INSERT INTO `activitycoment` VALUES ('146', '1', '季凯杰', '2015-11-02 14:19:42', '1');
INSERT INTO `activitycoment` VALUES ('147', '1', '季凯杰', '2015-11-02 14:19:46', '1');
INSERT INTO `activitycoment` VALUES ('148', '1', 'today,i am very happy!', '2015-11-03 20:17:24', '1');
INSERT INTO `activitycoment` VALUES ('149', '1', '很好', '2015-11-10 11:12:30', '1');
INSERT INTO `activitycoment` VALUES ('150', '1', '很好', '2015-11-10 11:12:36', '1');
INSERT INTO `activitycoment` VALUES ('151', '1', '很喜欢这发型', '2015-11-10 11:14:17', '1');
INSERT INTO `activitycoment` VALUES ('152', '1', '感觉这活动好赞', '2015-11-12 15:35:09', '132');
INSERT INTO `activitycoment` VALUES ('153', '1', '可口可乐了', '2015-11-13 15:07:47', '1');

-- ----------------------------
-- Table structure for act_praise
-- ----------------------------
DROP TABLE IF EXISTS `act_praise`;
CREATE TABLE `act_praise` (
  `u_id` int(11) NOT NULL,
  `act_id` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`u_id`),
  KEY `Fk_ACTPRAISE` (`act_id`),
  CONSTRAINT `Fk_ACTPRAISE` FOREIGN KEY (`act_id`) REFERENCES `activity` (`act_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ACTPRAISE_USER` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of act_praise
-- ----------------------------
INSERT INTO `act_praise` VALUES ('1', '1', '2015-10-13 16:52:20');

-- ----------------------------
-- Table structure for barber
-- ----------------------------
DROP TABLE IF EXISTS `barber`;
CREATE TABLE `barber` (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_name` varchar(20) NOT NULL,
  `b_position` varchar(20) NOT NULL,
  `b_icon` varchar(200) NOT NULL,
  `pro_degree` varchar(5) NOT NULL,
  `ser_degree` varchar(5) NOT NULL,
  `pop_degree` varchar(5) NOT NULL,
  `belong` int(11) DEFAULT NULL,
  `b_pass` varchar(20) NOT NULL,
  `b_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`b_id`),
  KEY `belong` (`belong`),
  CONSTRAINT `barber_ibfk_1` FOREIGN KEY (`belong`) REFERENCES `shop` (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of barber
-- ----------------------------
INSERT INTO `barber` VALUES ('1', '刘远', '店长', 'http://192.168.1.106:8080/Alisi2/images/liuyuan.png', '7.8', '10.0', '6.8', '3', '123', '2003入行多年的美发经验，擅长于时尚短发裁剪，人气时尚日韩风格电棒造型');
INSERT INTO `barber` VALUES ('2', '陈阳', '伙计', 'http://192.168.1.106:8080/Alisi2/upload/24e50006-a78d-4bb0-9807-acfe5121b998.png', '8.9', '10.0', '7.1', '3', '', '2003年进入美发行业，两年技师后顺利升级高级发型师。');
INSERT INTO `barber` VALUES ('3', '宇哥', '助手', 'http://192.168.1.106:8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png', '9.2', '10.0', '7.1', '3', '', '2008年进入美发行业');
INSERT INTO `barber` VALUES ('4', '亮仔', '首席发型师', 'http://192.168.1.106:8080/Alisi2/upload/c121b2dd-c969-4e01-bbbd-7132f006a330.png', '9.1', '10.0', '7.9', '3', '', '2011年进入美发行业。2010年学习广州富康学校魔鬼训练。');
INSERT INTO `barber` VALUES ('5', '熊伟', '首席发型师', 'http://192.168.1.106:8080/Alisi2/images/8.png', '8.9', '0', '0', '1', '', null);
INSERT INTO `barber` VALUES ('6', '谭学冲', '总监', 'http://192.168.1.106:8080/Alisi2/images/1.png', '9.0', '0', '0', '1', '', null);
INSERT INTO `barber` VALUES ('7', '钟岩', '首席发型师', 'http://192.168.1.106:8080/Alisi2/images/2.png', '8.9', '0', '0', '1', '', null);
INSERT INTO `barber` VALUES ('8', '紫南', '大扫除', 'http://192.168.1.106:8080/Alisi2/images/3.png', '9.5', '0', '0', '2', '', '我是谁你');
INSERT INTO `barber` VALUES ('9', '阿磊', '风格设计师', 'http://192.168.1.106:8080/Alisi2/images/4.png', '9.5', '0', '0', '2', '', '我是一个小逗比');
INSERT INTO `barber` VALUES ('10', '高野', '风格设计师', 'http://192.168.1.106:8080/Alisi2/images/5.png', '9.5', '0', '0', '2', '', '顶端设计师');
INSERT INTO `barber` VALUES ('11', '李雨欣', '技术指导', 'http://192.168.1.106:8080/Alisi2/images/6.png', '9.1', '6.2', '6.8', '2', '', '最美发型师');
INSERT INTO `barber` VALUES ('12', '张磊', '首席', 'http://192.168.1.106:8080/Alisi2/images/7.png', '9.2', '10', '2.4', '4', '', '流浪设计师');
INSERT INTO `barber` VALUES ('13', '姚海州', '造型师', 'http://192.168.1.106:8080/Alisi2/images/1.png', '8.5', '6.1', '10.0', '4', '', '大师你懂的');

-- ----------------------------
-- Table structure for bar_collect
-- ----------------------------
DROP TABLE IF EXISTS `bar_collect`;
CREATE TABLE `bar_collect` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `b_id` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`c_id`),
  KEY `u_id` (`u_id`),
  KEY `b_id` (`b_id`),
  CONSTRAINT `bar_collect_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`),
  CONSTRAINT `bar_collect_ibfk_2` FOREIGN KEY (`b_id`) REFERENCES `barber` (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bar_collect
-- ----------------------------
INSERT INTO `bar_collect` VALUES ('1', '1', '1', '1');
INSERT INTO `bar_collect` VALUES ('2', '1', '2', '1');
INSERT INTO `bar_collect` VALUES ('3', '2', '3', '0');
INSERT INTO `bar_collect` VALUES ('4', '2', '3', '1');
INSERT INTO `bar_collect` VALUES ('5', '105', '1', '1');

-- ----------------------------
-- Table structure for bar_post
-- ----------------------------
DROP TABLE IF EXISTS `bar_post`;
CREATE TABLE `bar_post` (
  `m_barber_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_id` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  `image` varchar(100) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`m_barber_id`),
  KEY `b_post_b_id` (`b_id`),
  CONSTRAINT `b_post_b_id` FOREIGN KEY (`b_id`) REFERENCES `barber` (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bar_post
-- ----------------------------
INSERT INTO `bar_post` VALUES ('1', '1', '最美发型', 'http://192.168.1.106:8080/Alisi2/images/6_1x.png', '2016-04-26 21:35:41');
INSERT INTO `bar_post` VALUES ('2', '2', '最丑发型', 'http://192.168.1.106:8080/Alisi2/images/6_2x.png', '2016-04-26 21:35:46');
INSERT INTO `bar_post` VALUES ('3', '1', '你猜发型', 'http://192.168.1.106:8080/Alisi2/images/6_3x.png', '2016-04-26 21:35:50');
INSERT INTO `bar_post` VALUES ('4', '4', '无言以对', 'http://192.168.1.106:8080/Alisi2/images/8_2x.png', '2016-04-26 21:35:53');
INSERT INTO `bar_post` VALUES ('5', '7', '第一个作品哦', 'http://192.168.1.106:8080/Alisi2/images/7_1x.png', '2016-04-26 21:35:57');
INSERT INTO `bar_post` VALUES ('6', '1', '哈哈哈哈哈', 'http://192.168.1.106:8080/Alisi2/images/8_2x.png', '2016-04-26 21:36:03');
INSERT INTO `bar_post` VALUES ('7', '1', '两个哈哈', 'http://192.168.1.106:8080/Alisi2/images/6_2x.png', '2016-04-26 21:36:07');

-- ----------------------------
-- Table structure for bar_postcomment
-- ----------------------------
DROP TABLE IF EXISTS `bar_postcomment`;
CREATE TABLE `bar_postcomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `m_barber_id` int(11) NOT NULL,
  `content` varchar(150) NOT NULL,
  `image` varchar(400) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_POST_COMMENT` (`m_barber_id`),
  KEY `FK_POST_USER` (`u_id`),
  CONSTRAINT `FK_POST_COMMENT` FOREIGN KEY (`m_barber_id`) REFERENCES `bar_post` (`m_barber_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_POST_USER` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bar_postcomment
-- ----------------------------
INSERT INTO `bar_postcomment` VALUES ('1', '1', '1', 'hahhh', '#', '2015-11-05 20:25:14');
INSERT INTO `bar_postcomment` VALUES ('2', '2', '1', '好棒啊', '#', '2015-11-05 20:26:56');
INSERT INTO `bar_postcomment` VALUES ('3', '3', '2', '还可以吧', '#', '2015-11-05 21:06:53');
INSERT INTO `bar_postcomment` VALUES ('4', '4', '1', '一般般吧', null, '2015-11-06 10:25:08');
INSERT INTO `bar_postcomment` VALUES ('5', '1', '1', '我挺喜欢的', null, '2015-11-06 10:39:17');
INSERT INTO `bar_postcomment` VALUES ('6', '1', '1', '你喜欢吗～', null, '2015-11-06 11:05:04');
INSERT INTO `bar_postcomment` VALUES ('7', '1', '1', '挺厉害的～', null, '2015-11-06 11:08:23');
INSERT INTO `bar_postcomment` VALUES ('8', '1', '1', '一般般唉', null, '2015-11-06 11:12:34');
INSERT INTO `bar_postcomment` VALUES ('9', '131', '2', '喜欢死了～', null, '2015-11-06 11:29:04');
INSERT INTO `bar_postcomment` VALUES ('10', '131', '2', '超爱！', null, '2015-11-06 11:31:32');
INSERT INTO `bar_postcomment` VALUES ('11', '132', '1', '你爱我吗', null, '2015-11-06 14:09:39');
INSERT INTO `bar_postcomment` VALUES ('12', '1', '1', '你猜啊～', null, '2015-11-06 14:16:06');
INSERT INTO `bar_postcomment` VALUES ('13', '134', '1', '好开心～', null, '2015-11-06 15:34:13');
INSERT INTO `bar_postcomment` VALUES ('14', '132', '1', '好开心', null, '2015-11-11 10:53:52');
INSERT INTO `bar_postcomment` VALUES ('15', '1', '1', 'cff', null, '2015-11-12 12:03:35');
INSERT INTO `bar_postcomment` VALUES ('16', '1', '1', 'cff', null, '2015-11-12 12:03:41');
INSERT INTO `bar_postcomment` VALUES ('17', '1', '1', 'cff', null, '2015-11-12 12:03:42');
INSERT INTO `bar_postcomment` VALUES ('18', '1', '1', 'cff', null, '2015-11-12 12:03:43');

-- ----------------------------
-- Table structure for bar_praise
-- ----------------------------
DROP TABLE IF EXISTS `bar_praise`;
CREATE TABLE `bar_praise` (
  `barpraise_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `m_barber_id` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`barpraise_id`),
  KEY `FK_BARPRAISE_USER` (`u_id`),
  KEY `FK_BARPRAISE_BARPOST` (`m_barber_id`),
  CONSTRAINT `FK_BARPRAISE_BARPOST` FOREIGN KEY (`m_barber_id`) REFERENCES `bar_post` (`m_barber_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_BARPRAISE_USER` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bar_praise
-- ----------------------------
INSERT INTO `bar_praise` VALUES ('1', '1', '1', '2015-09-28 19:00:13', '1');
INSERT INTO `bar_praise` VALUES ('2', '2', '1', '2015-09-28 19:00:25', '1');
INSERT INTO `bar_praise` VALUES ('3', '3', '1', '2015-09-28 19:00:37', '1');
INSERT INTO `bar_praise` VALUES ('4', '2', '3', '2015-09-29 18:17:18', '1');
INSERT INTO `bar_praise` VALUES ('5', '4', '1', '2015-10-27 10:37:10', '1');
INSERT INTO `bar_praise` VALUES ('14', '99', '2', '2015-10-29 14:45:09', '1');
INSERT INTO `bar_praise` VALUES ('15', '99', '2', '2015-10-29 14:45:10', '1');
INSERT INTO `bar_praise` VALUES ('16', '99', '2', '2015-10-29 14:45:11', '1');
INSERT INTO `bar_praise` VALUES ('17', '99', '2', '2015-10-29 14:45:12', '1');
INSERT INTO `bar_praise` VALUES ('18', '99', '2', '2015-10-29 14:45:13', '1');
INSERT INTO `bar_praise` VALUES ('19', '99', '2', '2015-10-29 14:45:14', '1');
INSERT INTO `bar_praise` VALUES ('20', '99', '3', '2015-10-29 14:45:21', '1');
INSERT INTO `bar_praise` VALUES ('21', '134', '2', '2015-11-06 15:35:02', '1');
INSERT INTO `bar_praise` VALUES ('22', '134', '2', '2015-11-06 15:35:03', '1');
INSERT INTO `bar_praise` VALUES ('23', '134', '2', '2015-11-06 15:35:04', '1');
INSERT INTO `bar_praise` VALUES ('24', '134', '2', '2015-11-06 15:35:04', '1');
INSERT INTO `bar_praise` VALUES ('25', '102', '2', '2015-11-09 10:20:06', '1');
INSERT INTO `bar_praise` VALUES ('26', '132', '2', '2015-11-11 10:58:00', '1');
INSERT INTO `bar_praise` VALUES ('27', '132', '2', '2015-11-11 10:58:01', '1');
INSERT INTO `bar_praise` VALUES ('28', '132', '2', '2015-11-11 10:58:01', '1');
INSERT INTO `bar_praise` VALUES ('29', '132', '2', '2015-11-11 10:58:02', '1');
INSERT INTO `bar_praise` VALUES ('30', '132', '2', '2015-11-11 10:58:03', '1');

-- ----------------------------
-- Table structure for freetime
-- ----------------------------
DROP TABLE IF EXISTS `freetime`;
CREATE TABLE `freetime` (
  `ft_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_id` int(11) NOT NULL,
  `day_id` int(12) NOT NULL,
  `t_id` int(12) NOT NULL,
  `state` varchar(2) NOT NULL,
  PRIMARY KEY (`ft_id`),
  KEY `free_day` (`day_id`),
  KEY `free_time` (`t_id`),
  CONSTRAINT `free_day` FOREIGN KEY (`day_id`) REFERENCES `showday` (`day_id`),
  CONSTRAINT `free_time` FOREIGN KEY (`t_id`) REFERENCES `showtime` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of freetime
-- ----------------------------
INSERT INTO `freetime` VALUES ('3', '1', '1', '1', '1');
INSERT INTO `freetime` VALUES ('4', '1', '1', '2', '1');
INSERT INTO `freetime` VALUES ('5', '1', '1', '3', '0');
INSERT INTO `freetime` VALUES ('6', '1', '1', '4', '0');
INSERT INTO `freetime` VALUES ('7', '1', '1', '5', '1');
INSERT INTO `freetime` VALUES ('8', '1', '1', '6', '1');
INSERT INTO `freetime` VALUES ('9', '1', '1', '7', '0');
INSERT INTO `freetime` VALUES ('10', '1', '1', '8', '1');
INSERT INTO `freetime` VALUES ('11', '1', '1', '9', '0');
INSERT INTO `freetime` VALUES ('12', '1', '1', '10', '0');
INSERT INTO `freetime` VALUES ('13', '1', '1', '11', '1');
INSERT INTO `freetime` VALUES ('14', '1', '1', '12', '1');
INSERT INTO `freetime` VALUES ('15', '1', '1', '13', '0');
INSERT INTO `freetime` VALUES ('16', '1', '1', '14', '1');
INSERT INTO `freetime` VALUES ('17', '1', '1', '15', '0');
INSERT INTO `freetime` VALUES ('18', '1', '1', '16', '0');
INSERT INTO `freetime` VALUES ('19', '1', '1', '17', '1');
INSERT INTO `freetime` VALUES ('20', '1', '1', '18', '0');
INSERT INTO `freetime` VALUES ('21', '1', '1', '19', '0');
INSERT INTO `freetime` VALUES ('22', '1', '1', '20', '0');
INSERT INTO `freetime` VALUES ('23', '1', '1', '21', '0');
INSERT INTO `freetime` VALUES ('24', '1', '1', '22', '0');

-- ----------------------------
-- Table structure for im_praise
-- ----------------------------
DROP TABLE IF EXISTS `im_praise`;
CREATE TABLE `im_praise` (
  `im_p_id` int(3) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `im_id` int(3) NOT NULL,
  `im_p_time` datetime NOT NULL,
  PRIMARY KEY (`im_p_id`),
  KEY `impraiseuser` (`u_id`),
  KEY `impraiseid` (`im_id`),
  CONSTRAINT `impraiseid` FOREIGN KEY (`im_id`) REFERENCES `infomessage` (`im_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `impraiseuser` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of im_praise
-- ----------------------------

-- ----------------------------
-- Table structure for im_reply
-- ----------------------------
DROP TABLE IF EXISTS `im_reply`;
CREATE TABLE `im_reply` (
  `im_reply_id` int(3) NOT NULL AUTO_INCREMENT,
  `im_id` int(3) NOT NULL,
  `u_id` int(11) NOT NULL,
  `im_reply_content` varchar(1000) NOT NULL,
  `im_reply_time` datetime NOT NULL,
  PRIMARY KEY (`im_reply_id`),
  KEY `imreplyuser` (`u_id`),
  KEY `imreplyid` (`im_id`),
  CONSTRAINT `imreplyid` FOREIGN KEY (`im_id`) REFERENCES `infomessage` (`im_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `imreplyuser` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of im_reply
-- ----------------------------
INSERT INTO `im_reply` VALUES ('1', '1', '2', '短发很漂亮！', '2015-10-26 16:12:43');
INSERT INTO `im_reply` VALUES ('2', '1', '2', '第三张图片蛮好的！！！', '2015-10-26 16:45:17');
INSERT INTO `im_reply` VALUES ('3', '1', '3', 'hehe', '2015-10-26 18:29:22');
INSERT INTO `im_reply` VALUES ('8', '1', '1', '我爱你', '2015-11-04 13:51:32');

-- ----------------------------
-- Table structure for infomessage
-- ----------------------------
DROP TABLE IF EXISTS `infomessage`;
CREATE TABLE `infomessage` (
  `im_id` int(3) NOT NULL AUTO_INCREMENT,
  `im_logo` varchar(200) NOT NULL,
  `im_name` varchar(50) NOT NULL,
  `im_image` varchar(200) DEFAULT NULL,
  `im_cotent` varchar(1000) NOT NULL,
  `im_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `im_image1` varchar(200) DEFAULT NULL,
  `im_image2` varchar(200) DEFAULT NULL,
  `im_image3` varchar(200) DEFAULT NULL,
  `im_content1` varchar(1000) DEFAULT NULL,
  `im_content2` varchar(1000) DEFAULT NULL,
  `im_content3` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`im_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of infomessage
-- ----------------------------
INSERT INTO `infomessage` VALUES ('1', 'http://192.168.1.106:8080/Alisi2/images/canvas.png', '明星短发发型盘点   欧尼为何钟情它', 'http://192.168.1.106:8080/Alisi2/images/infomessage_1_01.png', '在短发盛行的2015，女神也毅然的减去一头妩媚长发，挑战清爽的短发。将头发剪成齐肩的效果，在将发尾烫起微卷，比起之前的长发披肩这款发型更显成熟感。', '2016-04-26 21:44:16', 'http://10.202.1.99:8080/Alisi2/images/infomessage_1_02.png', 'http://10.202.1.99:8080/Alisi2/images/infomessage_1_03.png', 'http://10.202.1.99:8080/Alisi2/images/infomessage_1_04.png', '长相粉嫩的IU，BOB短发让她更显乖巧甜美，相比成熟的长发小清新了许多，柔顺而蓬松的造型发尾外卷，又不失时髦感。', '这款短卷发发型，超级甜美可爱。发尾微微卷翘，比较俏皮活泼，打造出更自然的时尚感，侧分刘海适当的修饰大额头，更显脸小，时尚又清纯，分分钟变身萌妹子，减龄效果真的超赞。', '简约的短发上采用了烫发的处理，烫发后层次感明显增强，发丝的蓬松效果十足，优雅侧分的刘海突出女生精致甜美的脸型。');
INSERT INTO `infomessage` VALUES ('2', 'http://192.168.1.106:8080/Alisi2/images/canvas.png', '爱丽丝', 'http://192.168.1.106:8080/Alisi2/images/canvas.png', '爱丽丝', '2016-04-26 21:44:20', '', null, null, null, null, null);
INSERT INTO `infomessage` VALUES ('3', 'http://192.168.1.106:8080/Alisi2/images/canvas4.png', '爱丽丝', 'http://192.168.1.106:8080/Alisi2/images/canvas.png', '爱丽丝', '2016-04-26 21:44:27', null, null, null, null, null, null);
INSERT INTO `infomessage` VALUES ('4', 'http://192.168.1.106:8080/Alisi2/images/canvas5.png', '爱丽丝', 'http://192.168.1.106:8080/Alisi2/images/canvas.png', '爱丽丝', '2016-04-26 21:44:33', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for ordercomment
-- ----------------------------
DROP TABLE IF EXISTS `ordercomment`;
CREATE TABLE `ordercomment` (
  `ord_com_id` int(11) NOT NULL AUTO_INCREMENT,
  `oder_id` int(11) NOT NULL,
  `content` varchar(140) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `image` varchar(200) DEFAULT NULL,
  `state` varchar(2) NOT NULL,
  PRIMARY KEY (`ord_com_id`),
  KEY `FK_ORDERCOMMENT` (`oder_id`),
  CONSTRAINT `FK_ORDERCOMMENT` FOREIGN KEY (`oder_id`) REFERENCES `t_order` (`o_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ordercomment
-- ----------------------------
INSERT INTO `ordercomment` VALUES ('2', '2', '哈哈哈', '2015-10-24 21:07:56', '##', '0');
INSERT INTO `ordercomment` VALUES ('3', '3', '不太好', '2015-10-24 21:16:22', '##', '1');
INSERT INTO `ordercomment` VALUES ('4', '4', '哎，找bug好痛苦', '2015-10-24 21:21:46', '##', '0');
INSERT INTO `ordercomment` VALUES ('5', '5', '找不到错误在哪', '2015-10-26 16:17:06', '##', '1');
INSERT INTO `ordercomment` VALUES ('6', '50', '还不错哦', '2015-11-06 14:21:11', '#', '1');

-- ----------------------------
-- Table structure for price
-- ----------------------------
DROP TABLE IF EXISTS `price`;
CREATE TABLE `price` (
  `p_id` int(12) NOT NULL AUTO_INCREMENT,
  `ser_id` int(11) NOT NULL,
  `b_id` int(11) NOT NULL,
  `price` int(255) NOT NULL,
  PRIMARY KEY (`p_id`),
  KEY `price_ibfk_1` (`b_id`),
  KEY `price_ibfk_2` (`ser_id`),
  CONSTRAINT `price_ibfk_1` FOREIGN KEY (`b_id`) REFERENCES `barber` (`b_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `price_ibfk_2` FOREIGN KEY (`ser_id`) REFERENCES `servtype` (`ser_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of price
-- ----------------------------
INSERT INTO `price` VALUES ('2', '1', '2', '432');
INSERT INTO `price` VALUES ('3', '1', '3', '998');
INSERT INTO `price` VALUES ('5', '2', '2', '300');
INSERT INTO `price` VALUES ('7', '3', '2', '400');
INSERT INTO `price` VALUES ('8', '1', '8', '434');
INSERT INTO `price` VALUES ('9', '1', '1', '100');
INSERT INTO `price` VALUES ('10', '2', '1', '101');
INSERT INTO `price` VALUES ('11', '3', '1', '102');
INSERT INTO `price` VALUES ('12', '4', '1', '103');

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `o_id` int(11) NOT NULL,
  `res_num` varchar(10) NOT NULL,
  `state` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Order` (`o_id`),
  CONSTRAINT `FK_Order` FOREIGN KEY (`o_id`) REFERENCES `t_order` (`o_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reservation
-- ----------------------------
INSERT INTO `reservation` VALUES ('1', '1', '232323', '1');
INSERT INTO `reservation` VALUES ('6', '15', '10020', '1');
INSERT INTO `reservation` VALUES ('17', '24', '10782', '1');
INSERT INTO `reservation` VALUES ('19', '26', '10005', '1');
INSERT INTO `reservation` VALUES ('20', '27', '10099', '1');
INSERT INTO `reservation` VALUES ('21', '28', '10929', '1');
INSERT INTO `reservation` VALUES ('22', '29', '10144', '1');
INSERT INTO `reservation` VALUES ('25', '31', '10716', '1');
INSERT INTO `reservation` VALUES ('26', '37', '10913', '1');
INSERT INTO `reservation` VALUES ('27', '38', '10777', '1');
INSERT INTO `reservation` VALUES ('28', '40', '10149', '1');
INSERT INTO `reservation` VALUES ('29', '40', '10832', '1');
INSERT INTO `reservation` VALUES ('30', '40', '10330', '1');
INSERT INTO `reservation` VALUES ('31', '40', '10839', '1');
INSERT INTO `reservation` VALUES ('32', '41', '10212', '1');
INSERT INTO `reservation` VALUES ('33', '43', '10288', '1');
INSERT INTO `reservation` VALUES ('34', '44', '10456', '1');
INSERT INTO `reservation` VALUES ('35', '45', '10773', '1');
INSERT INTO `reservation` VALUES ('36', '45', '10150', '1');
INSERT INTO `reservation` VALUES ('37', '46', '10285', '1');
INSERT INTO `reservation` VALUES ('38', '47', '10566', '1');
INSERT INTO `reservation` VALUES ('43', '50', '10847', '1');
INSERT INTO `reservation` VALUES ('44', '51', '10753', '1');
INSERT INTO `reservation` VALUES ('45', '52', '10298', '1');
INSERT INTO `reservation` VALUES ('46', '53', '10287', '1');
INSERT INTO `reservation` VALUES ('47', '54', '10614', '1');
INSERT INTO `reservation` VALUES ('48', '55', '10030', '1');
INSERT INTO `reservation` VALUES ('49', '56', '10444', '1');
INSERT INTO `reservation` VALUES ('50', '56', '10973', '1');
INSERT INTO `reservation` VALUES ('54', '57', '10226', '1');
INSERT INTO `reservation` VALUES ('55', '57', '10062', '1');
INSERT INTO `reservation` VALUES ('56', '58', '10228', '1');
INSERT INTO `reservation` VALUES ('57', '59', '10024', '1');
INSERT INTO `reservation` VALUES ('58', '60', '10344', '1');
INSERT INTO `reservation` VALUES ('59', '61', '10285', '1');
INSERT INTO `reservation` VALUES ('60', '61', '10712', '1');
INSERT INTO `reservation` VALUES ('61', '62', '10291', '1');

-- ----------------------------
-- Table structure for res_code
-- ----------------------------
DROP TABLE IF EXISTS `res_code`;
CREATE TABLE `res_code` (
  `total` int(11) NOT NULL AUTO_INCREMENT,
  `o_id` int(11) DEFAULT NULL,
  `r_code` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`total`),
  KEY `FK_RES_CODE_ORDER` (`o_id`),
  CONSTRAINT `FK_RES_CODE_ORDER` FOREIGN KEY (`o_id`) REFERENCES `t_order` (`o_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_code
-- ----------------------------

-- ----------------------------
-- Table structure for servtype
-- ----------------------------
DROP TABLE IF EXISTS `servtype`;
CREATE TABLE `servtype` (
  `ser_id` int(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ser_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of servtype
-- ----------------------------
INSERT INTO `servtype` VALUES ('1', '洗剪吹');
INSERT INTO `servtype` VALUES ('2', '烫');
INSERT INTO `servtype` VALUES ('3', '染');
INSERT INTO `servtype` VALUES ('4', '造型');

-- ----------------------------
-- Table structure for sex
-- ----------------------------
DROP TABLE IF EXISTS `sex`;
CREATE TABLE `sex` (
  `s_id` int(11) NOT NULL,
  `gender` varchar(2) DEFAULT '男',
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sex
-- ----------------------------
INSERT INTO `sex` VALUES ('1', '男');
INSERT INTO `sex` VALUES ('2', '女');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_name` varchar(20) NOT NULL,
  `s_phone` varchar(11) NOT NULL,
  `s_addr` varchar(100) NOT NULL,
  `s_desc` varchar(255) NOT NULL,
  `s_icon` varchar(100) NOT NULL,
  `s_distance` double(10,0) NOT NULL,
  `s_score` varchar(5) NOT NULL,
  `s_pass` varchar(10) NOT NULL,
  `s_longitude` double(20,6) DEFAULT NULL,
  `s_latitude` double(20,6) DEFAULT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', '伊姿丽美发沙龙', '15852264120', '华堂水晶城西侧底圈', '伊姿丽美容美发沙龙创立于2005年，通过10年的不断发展，已成为焗油相当影响力的品牌沙龙，公司本着抓也的技术，优质的服务为每一位消费者提供全方位的形象设计服务。', 'http://192.168.1.106:8080/Alisi2/images/shop1.png', '1011', '60', '123', '121.218841', '31.273421');
INSERT INTO `shop` VALUES ('2', 'NewLook', '110', '徐州工程学院', 'NewLook美容美发位于北京市通州区京通罗斯福4层429，交通便利，我们是一家 专业发型创作店，我们拥有一流的染，烫设备，并拥有优秀的美发师，可以为你提供最优质的服务，为你打造最靓丽最前卫的发型。', 'http://192.168.1.106:8080/Alisi2/images/shop2.png', '1017', '60', '1234', '121.494009', '31.220189');
INSERT INTO `shop` VALUES ('3', '亮仔国际', '15505183005', '马驹桥镇神龙潼一底商', '亮仔国际秉承着专业，创新，热枕，流行的经营理念，奉行真诚相处，顾客至上，追求卓越，共享事业，财务管理的企文化。创立多年来，不断学习，不断成长，多年来频繁输送一批批优秀的发型师前往日本，韩国，台湾等。', 'http://192.168.1.106:8080/Alisi2/images/store.png', '1017', '80', '123', '121.249471', '31.063812');
INSERT INTO `shop` VALUES ('4', '北上造型', '1111111', '马驹桥神龙潼关三区底商311', '北尚造型秉承动感，让你感动的经营宗旨。公司以人为本，以技术为依托，以品牌为核心，致力打造美发美容业的航空母舰。地理位置优越，交通便利，客源充足。', 'http://192.168.1.106:8080/Alisi2/images/shop4.png', '1018', '70', '1234', '121.341653', '30.760304');
INSERT INTO `shop` VALUES ('5', '瑞邦形象', '13521848785', '马驹桥神龙潼关三区底商111', '瑞邦形象是一家抓也的发型创作店。我们拥有一组发型精英为客户创作灵感发型，能给客户带来更多的靓丽', 'http://192.168.1.106:8080/Alisi2/images/shop5.png', '1012', '80', '123', '121.374337', '31.220714');
INSERT INTO `shop` VALUES ('6', '私人订制', '01059423119', '马驹桥神龙潼关二区底商', '经过多年的发展，北京发财造型已经在国内美容美发行业具有一定的影响力。解决了国内时尚爱美人士的需求度。我们在不断发展壮大的过程中也为社会创造了巨大的财富。让一大批通过美容美发迅速的发财致富。', 'http://192.168.1.106:8080/Alisi2/images/shop3.png', '1018', '90', '123', '121.379909', '31.183587');

-- ----------------------------
-- Table structure for showday
-- ----------------------------
DROP TABLE IF EXISTS `showday`;
CREATE TABLE `showday` (
  `day_id` int(2) NOT NULL,
  `day` varchar(20) NOT NULL,
  PRIMARY KEY (`day_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of showday
-- ----------------------------
INSERT INTO `showday` VALUES ('1', 'today');
INSERT INTO `showday` VALUES ('2', 'tomorrow');
INSERT INTO `showday` VALUES ('3', 'aftertomorrow');

-- ----------------------------
-- Table structure for showtime
-- ----------------------------
DROP TABLE IF EXISTS `showtime`;
CREATE TABLE `showtime` (
  `t_id` int(2) NOT NULL,
  `t_time` varchar(10) NOT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of showtime
-- ----------------------------
INSERT INTO `showtime` VALUES ('1', '10:00');
INSERT INTO `showtime` VALUES ('2', '10:30');
INSERT INTO `showtime` VALUES ('3', '11:00');
INSERT INTO `showtime` VALUES ('4', '11:30');
INSERT INTO `showtime` VALUES ('5', '12:00');
INSERT INTO `showtime` VALUES ('6', '12:30');
INSERT INTO `showtime` VALUES ('7', '13:00');
INSERT INTO `showtime` VALUES ('8', '13:30');
INSERT INTO `showtime` VALUES ('9', '14:00');
INSERT INTO `showtime` VALUES ('10', '14:30');
INSERT INTO `showtime` VALUES ('11', '15:00');
INSERT INTO `showtime` VALUES ('12', '15:30');
INSERT INTO `showtime` VALUES ('13', '16:00');
INSERT INTO `showtime` VALUES ('14', '16:30');
INSERT INTO `showtime` VALUES ('15', '17:00');
INSERT INTO `showtime` VALUES ('16', '17:30');
INSERT INTO `showtime` VALUES ('17', '18:00');
INSERT INTO `showtime` VALUES ('18', '18:30');
INSERT INTO `showtime` VALUES ('19', '19：00');
INSERT INTO `showtime` VALUES ('20', '19:30');
INSERT INTO `showtime` VALUES ('21', '20:00');
INSERT INTO `showtime` VALUES ('22', '20:30');

-- ----------------------------
-- Table structure for suggest
-- ----------------------------
DROP TABLE IF EXISTS `suggest`;
CREATE TABLE `suggest` (
  `s_id` int(3) NOT NULL AUTO_INCREMENT,
  `u_id` int(3) NOT NULL,
  `suggest` varchar(1000) NOT NULL,
  PRIMARY KEY (`s_id`),
  KEY `suggestid` (`u_id`),
  CONSTRAINT `suggestid` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of suggest
-- ----------------------------
INSERT INTO `suggest` VALUES ('1', '1', '有bug');
INSERT INTO `suggest` VALUES ('2', '1', '有bug');
INSERT INTO `suggest` VALUES ('3', '2', 'I think this app is Good. I think this app is Good.');
INSERT INTO `suggest` VALUES ('4', '2', '还白拔插');

-- ----------------------------
-- Table structure for t_barcomment
-- ----------------------------
DROP TABLE IF EXISTS `t_barcomment`;
CREATE TABLE `t_barcomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `m_barber_id` int(11) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_comm_post` (`m_barber_id`),
  KEY `FK_comm_user` (`u_id`),
  CONSTRAINT `FK_comm_post` FOREIGN KEY (`m_barber_id`) REFERENCES `bar_post` (`m_barber_id`),
  CONSTRAINT `FK_comm_user` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_barcomment
-- ----------------------------

-- ----------------------------
-- Table structure for t_communication
-- ----------------------------
DROP TABLE IF EXISTS `t_communication`;
CREATE TABLE `t_communication` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `r_id` int(11) NOT NULL,
  `state` varchar(1) NOT NULL,
  `other_id` int(11) NOT NULL,
  PRIMARY KEY (`c_id`),
  KEY `FK_COMMUNICATION_REPLY` (`r_id`),
  KEY `FK_COMMUNICATION_USER` (`u_id`),
  KEY `FK_COMMUNICATION_USER2` (`other_id`),
  CONSTRAINT `FK_COMMUNICATION_REPLY` FOREIGN KEY (`r_id`) REFERENCES `t_reply` (`r_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_COMMUNICATION_USER` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_COMMUNICATION_USER2` FOREIGN KEY (`other_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_communication
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `o_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `b_id` int(11) NOT NULL,
  `time1` int(2) NOT NULL,
  `time2` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `servid` int(11) NOT NULL,
  `state` varchar(1) NOT NULL DEFAULT '',
  `day_id` int(1) NOT NULL,
  PRIMARY KEY (`o_id`),
  KEY `u_id` (`u_id`),
  KEY `servid` (`servid`),
  KEY `b_id` (`b_id`),
  KEY `timeduan` (`time1`),
  CONSTRAINT `b_id` FOREIGN KEY (`b_id`) REFERENCES `barber` (`b_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `servid` FOREIGN KEY (`servid`) REFERENCES `servtype` (`ser_id`),
  CONSTRAINT `timeduan` FOREIGN KEY (`time1`) REFERENCES `showtime` (`t_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `u_id` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '1', '1', '2', '2016-05-05 19:28:13', '1', '0', '1');
INSERT INTO `t_order` VALUES ('2', '1', '2', '1', '2015-10-24 13:36:44', '2', '0', '2');
INSERT INTO `t_order` VALUES ('3', '2', '1', '1', '2015-10-24 13:39:38', '1', '1', '1');
INSERT INTO `t_order` VALUES ('4', '2', '2', '2', '2015-10-24 13:39:59', '2', '1', '1');
INSERT INTO `t_order` VALUES ('5', '2', '3', '1', '2015-10-25 16:31:09', '1', '1', '1');
INSERT INTO `t_order` VALUES ('15', '1', '1', '1', '2015-10-26 16:09:10', '1', '1', '1');
INSERT INTO `t_order` VALUES ('24', '101', '1', '1', '2015-10-29 14:59:04', '1', '0', '1');
INSERT INTO `t_order` VALUES ('26', '107', '1', '1', '2015-10-30 09:41:22', '1', '0', '1');
INSERT INTO `t_order` VALUES ('27', '108', '1', '1', '2015-10-30 10:19:07', '1', '0', '1');
INSERT INTO `t_order` VALUES ('28', '110', '1', '1', '2015-10-30 14:15:36', '1', '0', '1');
INSERT INTO `t_order` VALUES ('29', '108', '1', '1', '2015-10-30 14:31:58', '1', '0', '1');
INSERT INTO `t_order` VALUES ('31', '116', '1', '1', '2015-10-30 17:08:11', '1', '0', '1');
INSERT INTO `t_order` VALUES ('32', '122', '1', '1', '2015-11-02 10:19:14', '1', '0', '1');
INSERT INTO `t_order` VALUES ('33', '122', '1', '1', '2015-11-02 10:31:25', '1', '0', '1');
INSERT INTO `t_order` VALUES ('34', '122', '1', '1', '2015-11-02 10:39:02', '1', '0', '1');
INSERT INTO `t_order` VALUES ('35', '122', '1', '1', '2015-11-02 10:40:25', '1', '0', '1');
INSERT INTO `t_order` VALUES ('36', '124', '1', '1', '2015-11-02 10:42:17', '1', '0', '1');
INSERT INTO `t_order` VALUES ('37', '125', '1', '1', '2015-11-02 10:54:13', '1', '0', '1');
INSERT INTO `t_order` VALUES ('38', '125', '1', '1', '2015-11-02 11:02:42', '1', '0', '1');
INSERT INTO `t_order` VALUES ('39', '102', '1', '1', '2015-11-02 12:51:41', '1', '0', '1');
INSERT INTO `t_order` VALUES ('40', '127', '1', '1', '2015-11-02 13:54:03', '1', '0', '1');
INSERT INTO `t_order` VALUES ('41', '127', '1', '1', '2015-11-02 14:27:14', '1', '0', '1');
INSERT INTO `t_order` VALUES ('42', '127', '1', '1', '2015-11-02 14:28:09', '1', '0', '1');
INSERT INTO `t_order` VALUES ('43', '128', '1', '1', '2015-11-02 19:13:25', '1', '0', '1');
INSERT INTO `t_order` VALUES ('44', '102', '1', '1', '2015-11-03 20:16:04', '1', '0', '1');
INSERT INTO `t_order` VALUES ('45', '130', '1', '1', '2015-11-04 11:18:27', '1', '0', '1');
INSERT INTO `t_order` VALUES ('46', '131', '1', '1', '2015-11-06 11:28:05', '1', '0', '1');
INSERT INTO `t_order` VALUES ('47', '132', '1', '1', '2015-11-06 13:42:48', '1', '0', '1');
INSERT INTO `t_order` VALUES ('48', '132', '1', '1', '2015-11-06 13:44:48', '1', '0', '1');
INSERT INTO `t_order` VALUES ('50', '133', '1', '1', '2015-11-06 14:19:07', '1', '0', '1');
INSERT INTO `t_order` VALUES ('51', '134', '1', '1', '2015-11-06 15:26:06', '1', '0', '1');
INSERT INTO `t_order` VALUES ('52', '134', '1', '1', '2015-11-06 15:27:56', '1', '0', '1');
INSERT INTO `t_order` VALUES ('53', '134', '1', '1', '2015-11-06 15:28:33', '1', '0', '1');
INSERT INTO `t_order` VALUES ('54', '134', '1', '1', '2015-11-06 15:32:27', '1', '0', '1');
INSERT INTO `t_order` VALUES ('55', '136', '1', '1', '2015-11-11 10:15:11', '1', '0', '1');
INSERT INTO `t_order` VALUES ('56', '132', '1', '1', '2015-11-11 10:50:13', '1', '0', '1');
INSERT INTO `t_order` VALUES ('57', '102', '1', '1', '2015-11-13 15:56:08', '1', '0', '1');
INSERT INTO `t_order` VALUES ('58', '102', '1', '1', '2015-11-13 16:15:12', '1', '0', '1');
INSERT INTO `t_order` VALUES ('59', '108', '1', '1', '2015-11-14 14:58:39', '1', '0', '1');
INSERT INTO `t_order` VALUES ('60', '108', '1', '1', '2015-11-16 08:58:12', '1', '0', '1');
INSERT INTO `t_order` VALUES ('61', '102', '1', '1', '2015-11-16 13:08:09', '1', '0', '1');
INSERT INTO `t_order` VALUES ('62', '102', '1', '1', '2016-04-26 21:52:32', '1', '0', '1');

-- ----------------------------
-- Table structure for t_reply
-- ----------------------------
DROP TABLE IF EXISTS `t_reply`;
CREATE TABLE `t_reply` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `comm_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `content` varchar(100) NOT NULL,
  PRIMARY KEY (`r_id`),
  KEY `FK_REPLY_COMM` (`comm_id`),
  KEY `FK_RYPLY_USER` (`u_id`),
  CONSTRAINT `FK_REPLY_COMM` FOREIGN KEY (`comm_id`) REFERENCES `t_barcomment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_RYPLY_USER` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_reply
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(10) DEFAULT '丽丝粉',
  `phone` varchar(11) NOT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `u_sign` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `e_mail` varchar(20) DEFAULT NULL,
  `money` int(11) DEFAULT '1000',
  PRIMARY KEY (`u_id`),
  KEY `FK_USER_SEX` (`sex`),
  CONSTRAINT `FK_USER_SEX` FOREIGN KEY (`sex`) REFERENCES `sex` (`s_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', '15852264120', 'http://10.20.14.181:8080/Alisi2/images/barber1.png', '一个人', '苏州', '1', '5787', '597');
INSERT INTO `user` VALUES ('2', '王蓉', '15505183005', 'http://192.168.1.106:8080/Alisi2/images/9.png', '吃饱了就睡...', '天津', '2', '1134@qq.com', '500');
INSERT INTO `user` VALUES ('3', '李伟', '1445', 'http://192.168.1.106:8080/Alisi2/images/10.png', '哈哈哈哈哈哈哈', '山东', '1', '123789@qq.com', '500');
INSERT INTO `user` VALUES ('4', '孙霞', '123456', 'http://192.168.1.106:8080/Alisi2/images/11.png', '你猜啊！', '苏州', '2', '578706671@qq.com', null);
INSERT INTO `user` VALUES ('5', null, '1234', 'http://192.168.1.106:8080/Alisi2/images/12.png', null, null, null, null, '1000');
INSERT INTO `user` VALUES ('6', null, '1234', 'http://192.168.1.106:8080/Alisi2/images/13.png', null, null, null, null, '1000');
INSERT INTO `user` VALUES ('7', null, '1235', 'http://192.168.1.106:8080/Alisi2/images/14.png', null, null, null, null, '1000');
INSERT INTO `user` VALUES ('8', null, '123456', 'http://192.168.1.106:8080/Alisi2/images/15.png', null, null, null, null, '1000');
INSERT INTO `user` VALUES ('9', null, '1234567', 'http://192.168.1.106:8080/Alisi2/images/16.png', null, null, null, null, '1000');
INSERT INTO `user` VALUES ('10', null, '15852264120', 'http://192.168.1.106:8080/Alisi2/images/17.png', null, null, null, null, '1000');
INSERT INTO `user` VALUES ('11', null, '15852264120', 'http://192.168.1.106:8080/Alisi2/images/18.png', null, null, null, null, '1000');
INSERT INTO `user` VALUES ('97', null, '15852264785', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('98', '久远', '18852268975', null, '好痛苦啊', '苏州', '1', 'liuyuan', '1000');
INSERT INTO `user` VALUES ('99', null, '15852231256', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('100', null, '15852264189', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('101', null, '15852264567', null, null, null, null, null, '897');
INSERT INTO `user` VALUES ('102', null, '15862175370', null, null, null, null, null, '100');
INSERT INTO `user` VALUES ('103', null, '15852278968', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('104', null, '18652124578', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('105', null, '15852245897', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('106', null, '15862175370', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('107', null, '15862175378', null, null, null, null, null, '900');
INSERT INTO `user` VALUES ('108', null, '15162106553', null, null, null, null, null, '600');
INSERT INTO `user` VALUES ('109', null, '15162106553', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('110', null, '15852223456', null, null, null, null, null, '900');
INSERT INTO `user` VALUES ('111', null, '15162106553', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('112', null, '15162106553', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('113', null, '15162106553', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('114', null, '15162106553', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('115', null, '15862175370', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('116', null, '15852245678', null, null, null, null, null, '898');
INSERT INTO `user` VALUES ('117', null, '15162106553', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('118', null, '15162106553', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('119', null, '15162106553', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('120', null, '15505182983', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('121', null, '15508182983', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('122', null, '15505183006', null, null, null, null, null, '300');
INSERT INTO `user` VALUES ('123', null, '15505182546', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('124', null, '15852212345', null, null, null, null, null, '800');
INSERT INTO `user` VALUES ('125', null, '15852250939', null, null, null, null, null, '800');
INSERT INTO `user` VALUES ('126', null, '15862175370', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('127', null, '15863263951', null, null, null, null, null, '700');
INSERT INTO `user` VALUES ('128', null, '18051376422', null, null, null, null, null, '900');
INSERT INTO `user` VALUES ('129', null, '13852124586', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('130', null, '15852265234', null, null, null, null, null, '800');
INSERT INTO `user` VALUES ('131', '丽丝粉', '15852245876', null, null, null, null, null, '900');
INSERT INTO `user` VALUES ('132', '丽丝粉', '15852245786', null, null, null, null, null, '600');
INSERT INTO `user` VALUES ('133', '丽丝粉', '15852247856', null, null, null, null, null, '900');
INSERT INTO `user` VALUES ('134', '丽丝粉', '15852345786', null, null, null, null, null, '600');
INSERT INTO `user` VALUES ('135', '丽丝粉', '15862175370', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('136', '丽丝粉', '15852245869', null, null, null, null, null, '900');
INSERT INTO `user` VALUES ('137', '丽丝粉', '15852245786', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('138', '丽丝粉', '15862175370', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('139', '丽丝粉', '15862175370', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('140', '丽丝粉', '15862175370', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('141', '丽丝粉', '15862175370', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('142', '丽丝粉', '15862175370', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('143', '丽丝粉', '15862175360', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('144', '丽丝粉', '15162106553', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('145', '丽丝粉', '15162106553', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('146', '丽丝粉', '15162106553', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('147', '丽丝粉', '15162106553', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('148', '丽丝粉', '15862175370', null, null, null, null, null, '1000');
INSERT INTO `user` VALUES ('149', '丽丝粉', '15862175370', null, null, null, null, null, '1000');

-- ----------------------------
-- Table structure for user_post
-- ----------------------------
DROP TABLE IF EXISTS `user_post`;
CREATE TABLE `user_post` (
  `m_self_id` int(4) NOT NULL AUTO_INCREMENT,
  `u_id` int(4) NOT NULL,
  `content` varchar(100) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`m_self_id`),
  KEY `fk111111` (`u_id`),
  CONSTRAINT `fk111111` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_post
-- ----------------------------
INSERT INTO `user_post` VALUES ('1', '1', 'happy day', 'http://192.168.1.106:8080/Alisi2/images/6_1x.png', '2016-04-26 21:32:11');
INSERT INTO `user_post` VALUES ('2', '2', '中国你好', 'http://192.168.1.106:8080/Alisi2/images/6_1x.png', '2016-04-26 21:32:15');
INSERT INTO `user_post` VALUES ('3', '3', '饿', 'http://192.168.1.106:8080/Alisi2/images/6_1x.png', '2016-04-26 21:32:17');
INSERT INTO `user_post` VALUES ('4', '4', '他', 'http://192.168.1.106:8080/Alisi2/images/6_1x.png', '2016-04-26 21:32:21');
INSERT INTO `user_post` VALUES ('5', '1', '水杯', 'http://192.168.1.106:8080/Alisi2/upload/97e36714-13fd-4e3c-a84d-da0e84af3966.jpg', '2016-04-26 21:32:28');
INSERT INTO `user_post` VALUES ('6', '1', '哈哈好吧', 'http://192.168.1.106:8080/Alisi2/upload/d064c2cc-f573-4ed6-8a60-88ade9099857.jpg', '2016-04-26 21:32:34');
INSERT INTO `user_post` VALUES ('7', '1', '很好', 'http://192.168.1.106:8080/Alisi2/upload/30542e31-0084-41f4-9d1d-516c20845c02.jpg', '2016-04-26 21:32:40');
INSERT INTO `user_post` VALUES ('8', '1', '很好', 'http://192.168.1.106:8080/Alisi2/upload/2ae1b0fc-35f0-4cfd-bd34-5093cab4867f.jpg', '2016-04-26 21:32:46');

-- ----------------------------
-- Table structure for user_praise
-- ----------------------------
DROP TABLE IF EXISTS `user_praise`;
CREATE TABLE `user_praise` (
  `u_praise_id` int(4) NOT NULL AUTO_INCREMENT,
  `u_id` int(4) DEFAULT NULL,
  `m_self_id` int(4) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`u_praise_id`),
  KEY `fk233` (`u_id`),
  KEY `fk2333` (`m_self_id`),
  CONSTRAINT `fk233` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk2333` FOREIGN KEY (`m_self_id`) REFERENCES `user_post` (`m_self_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_praise
-- ----------------------------
INSERT INTO `user_praise` VALUES ('3', '2', '2', '2015-10-25 11:28:35');
INSERT INTO `user_praise` VALUES ('4', '2', '2', '2015-10-25 11:30:45');
INSERT INTO `user_praise` VALUES ('5', '2', '2', '2015-10-25 13:35:05');
INSERT INTO `user_praise` VALUES ('6', '2', '2', '2015-10-27 20:40:52');
INSERT INTO `user_praise` VALUES ('8', '1', '2', '2015-10-27 20:53:35');
INSERT INTO `user_praise` VALUES ('11', '1', '1', '2015-11-06 15:35:41');
INSERT INTO `user_praise` VALUES ('12', '1', '1', '2015-11-11 10:58:11');

-- ----------------------------
-- View structure for bsp
-- ----------------------------
DROP VIEW IF EXISTS `bsp`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `bsp` AS select `barber`.`b_id` AS `b_id`,`servtype`.`type` AS `type`,`price`.`price` AS `price` from ((`barber` join `servtype`) join `price`) where ((`barber`.`b_id` = `price`.`b_id`) and (`servtype`.`ser_id` = `price`.`ser_id`)) ;

-- ----------------------------
-- View structure for orderbarber
-- ----------------------------
DROP VIEW IF EXISTS `orderbarber`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `orderbarber` AS select `barber`.`b_id` AS `b_id`,`barber`.`b_name` AS `b_name`,`barber`.`pop_degree` AS `pop_degree`,`shop`.`s_name` AS `s_name` from (`barber` join `shop`) where (`shop`.`s_id` = `barber`.`belong`) ;

-- ----------------------------
-- View structure for selfshow
-- ----------------------------
DROP VIEW IF EXISTS `selfshow`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `selfshow` AS select `user_post`.`u_id` AS `u_id`,`user_post`.`m_self_id` AS `m_self_id`,`user`.`icon` AS `icon`,`user`.`u_name` AS `u_name`,`user_post`.`content` AS `content`,`user_post`.`image` AS `image`,`user_post`.`time` AS `time` from (`user` join `user_post`) where (`user`.`u_id` = `user_post`.`u_id`) ;

-- ----------------------------
-- View structure for view_barberid
-- ----------------------------
DROP VIEW IF EXISTS `view_barberid`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `view_barberid` AS select `t_order`.`b_id` AS `b_id` from `t_order` where `t_order`.`o_id` in (select `ordercomment`.`oder_id` from `ordercomment`) ;

-- ----------------------------
-- View structure for view_barpostcomment
-- ----------------------------
DROP VIEW IF EXISTS `view_barpostcomment`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `view_barpostcomment` AS select `u`.`u_name` AS `u_name`,`b`.`content` AS `content`,`b`.`time` AS `time`,`b`.`m_barber_id` AS `m_barber_id` from (`user` `u` join `bar_postcomment` `b`) where (`u`.`u_id` = `b`.`u_id`) ;
