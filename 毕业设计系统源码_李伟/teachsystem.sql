/*
Navicat MySQL Data Transfer

Source Server         : liwei
Source Server Version : 50617
Source Host           : 127.0.0.1:3306
Source Database       : teachsystem

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-05-28 21:18:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administretor
-- ----------------------------
DROP TABLE IF EXISTS `administretor`;
CREATE TABLE `administretor` (
  `ano` int(12) NOT NULL,
  `aname` varchar(5) NOT NULL,
  `asex` varchar(2) NOT NULL,
  `pass` varchar(16) NOT NULL,
  `username` varchar(16) NOT NULL,
  PRIMARY KEY (`ano`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of administretor
-- ----------------------------
INSERT INTO `administretor` VALUES ('12', '啊我', '男', '12', '12');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cno` int(6) NOT NULL,
  `cname` varchar(11) NOT NULL,
  `classtime` varchar(15) NOT NULL,
  `Tno` int(4) NOT NULL,
  `cadress` varchar(15) NOT NULL,
  `content` varchar(200) NOT NULL,
  PRIMARY KEY (`cno`),
  KEY `fk10` (`Tno`),
  CONSTRAINT `fk10` FOREIGN KEY (`Tno`) REFERENCES `teacher` (`Tno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '数据库', '周五下午第一节', '20120510', '教一310', '数据库的操作');
INSERT INTO `course` VALUES ('2', 'JAVA', '周三下午第二节', '20120510', '教二218', 'java基础知识');
INSERT INTO `course` VALUES ('3', '语文', '周二', '20120510', '教一', '哈哈哈');

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `Dno` int(4) NOT NULL,
  `Dname` varchar(10) NOT NULL,
  PRIMARY KEY (`Dno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '信电学院');
INSERT INTO `dept` VALUES ('2', '人文学院');
INSERT INTO `dept` VALUES ('3', '土木学院');
INSERT INTO `dept` VALUES ('4', '化工学院');
INSERT INTO `dept` VALUES ('5', '数理学院');
INSERT INTO `dept` VALUES ('6', '机电学院');
INSERT INTO `dept` VALUES ('7', '环境学院');

-- ----------------------------
-- Table structure for mymessage
-- ----------------------------
DROP TABLE IF EXISTS `mymessage`;
CREATE TABLE `mymessage` (
  `mymessage_id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` int(11) NOT NULL,
  `Tno` int(4) NOT NULL,
  `mymessage_content` varchar(200) NOT NULL,
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`mymessage_id`),
  KEY `fk23` (`sno`),
  KEY `fk24` (`Tno`),
  CONSTRAINT `fk23` FOREIGN KEY (`sno`) REFERENCES `student` (`Sno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk24` FOREIGN KEY (`Tno`) REFERENCES `teacher` (`Tno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mymessage
-- ----------------------------
INSERT INTO `mymessage` VALUES ('1', '20160511', '20120510', '老师您辛苦了', '2016-03-22 22:54:12');
INSERT INTO `mymessage` VALUES ('8', '2016088', '20120510', '老师，教师节快乐', '2016-05-14 10:03:52');
INSERT INTO `mymessage` VALUES ('9', '20160512', '20120510', '老师，谢谢您在学习上给予的帮助', '2016-05-14 10:05:10');
INSERT INTO `mymessage` VALUES ('10', '20160511', '20120510', '的点点滴滴', '2016-05-19 18:57:14');
INSERT INTO `mymessage` VALUES ('11', '20160511', '20120510', '凤飞飞', '2016-05-25 16:45:14');

-- ----------------------------
-- Table structure for qusetion
-- ----------------------------
DROP TABLE IF EXISTS `qusetion`;
CREATE TABLE `qusetion` (
  `qusetion_id` int(5) NOT NULL AUTO_INCREMENT,
  `question_content` varchar(300) NOT NULL,
  `cno` int(6) NOT NULL,
  `answer_content_a` varchar(20) NOT NULL,
  `answer_content_b` varchar(20) NOT NULL,
  `answer_correct` varchar(20) NOT NULL,
  PRIMARY KEY (`qusetion_id`),
  KEY `fk7` (`cno`),
  CONSTRAINT `fk7` FOREIGN KEY (`cno`) REFERENCES `course` (`cno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qusetion
-- ----------------------------
INSERT INTO `qusetion` VALUES ('1', '1+1=？', '1', '1', '3', '2');
INSERT INTO `qusetion` VALUES ('2', '2+2=？', '1', '2', '3', '4');
INSERT INTO `qusetion` VALUES ('3', 'java怎么样', '2', '不错', '难', '易');
INSERT INTO `qusetion` VALUES ('5', '单片机使用的编码语言', '1', 'java', 'c++', 'c51');
INSERT INTO `qusetion` VALUES ('8', '今天周几', '1', '一', '二', '三');

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `reply_id` int(5) NOT NULL AUTO_INCREMENT,
  `Tno` int(4) NOT NULL,
  `Sno` int(11) NOT NULL,
  `sq_id` int(5) NOT NULL,
  `reply_content` varchar(200) NOT NULL,
  PRIMARY KEY (`reply_id`),
  KEY `fk16` (`Tno`),
  KEY `fk17` (`Sno`),
  CONSTRAINT `fk16` FOREIGN KEY (`Tno`) REFERENCES `teacher` (`Tno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk17` FOREIGN KEY (`Sno`) REFERENCES `student` (`Sno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '20120510', '2016088', '3', '刚刚灌灌灌灌灌');
INSERT INTO `reply` VALUES ('2', '20120510', '20160511', '1', '的高峰过后');
INSERT INTO `reply` VALUES ('3', '20120510', '20160511', '2', '多少个风格和规划');
INSERT INTO `reply` VALUES ('4', '20120510', '20160511', '4', '山东GV放大vfdvfv');
INSERT INTO `reply` VALUES ('5', '20120510', '20160511', '5', '的方式该部分不改变');
INSERT INTO `reply` VALUES ('7', '20120510', '20160511', '6', '啥地方少犯错的');
INSERT INTO `reply` VALUES ('8', '20120510', '20160511', '8', '难也得学');
INSERT INTO `reply` VALUES ('9', '20120510', '20160511', '7', '哈哈哈');
INSERT INTO `reply` VALUES ('10', '20120510', '20160512', '9', '很简单');

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `sc_id` int(10) NOT NULL AUTO_INCREMENT,
  `Sno` int(11) NOT NULL,
  `cno` int(6) NOT NULL,
  `score` int(3) DEFAULT NULL,
  PRIMARY KEY (`sc_id`),
  KEY `fk12` (`Sno`),
  KEY `cno` (`cno`),
  CONSTRAINT `fk12` FOREIGN KEY (`Sno`) REFERENCES `student` (`Sno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sc_ibfk_1` FOREIGN KEY (`cno`) REFERENCES `course` (`cno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('10', '20160511', '1', '8');
INSERT INTO `sc` VALUES ('12', '2016088', '2', null);
INSERT INTO `sc` VALUES ('13', '20160511', '2', '4');
INSERT INTO `sc` VALUES ('22', '20160512', '1', null);
INSERT INTO `sc` VALUES ('23', '2016088', '1', null);
INSERT INTO `sc` VALUES ('24', '20160513', '1', null);
INSERT INTO `sc` VALUES ('25', '20160515', '1', null);
INSERT INTO `sc` VALUES ('26', '20160517', '1', null);
INSERT INTO `sc` VALUES ('27', '20160518', '1', null);
INSERT INTO `sc` VALUES ('29', '20160519', '1', null);

-- ----------------------------
-- Table structure for sign
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `sign_id` int(5) NOT NULL AUTO_INCREMENT,
  `sno` int(11) NOT NULL,
  `cno` int(6) NOT NULL,
  `ip` varchar(16) NOT NULL,
  `state` varchar(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`sign_id`),
  KEY `fk100` (`sno`),
  KEY `fk101` (`cno`),
  CONSTRAINT `fk100` FOREIGN KEY (`sno`) REFERENCES `student` (`Sno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk101` FOREIGN KEY (`cno`) REFERENCES `course` (`cno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES ('1', '20160511', '1', '10.105.107.100', '1');

-- ----------------------------
-- Table structure for state_reply
-- ----------------------------
DROP TABLE IF EXISTS `state_reply`;
CREATE TABLE `state_reply` (
  `state` int(7) NOT NULL AUTO_INCREMENT,
  `statecontent` varchar(20) NOT NULL,
  PRIMARY KEY (`state`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of state_reply
-- ----------------------------
INSERT INTO `state_reply` VALUES ('1', '待回复');
INSERT INTO `state_reply` VALUES ('2', '已回复');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Sno` int(11) NOT NULL AUTO_INCREMENT,
  `Sname` varchar(4) NOT NULL,
  `Ssex` varchar(4) NOT NULL,
  `Sicon` varchar(100) DEFAULT NULL,
  `Sage` int(2) DEFAULT NULL,
  `Dno` int(4) NOT NULL,
  `Suser` varchar(11) NOT NULL,
  `SpassWord` varchar(11) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `token` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Sno`),
  KEY `fk1` (`Dno`),
  CONSTRAINT `fk1` FOREIGN KEY (`Dno`) REFERENCES `dept` (`Dno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20160521 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('2016088', '阿杜', '男', 'http://10.20.0.44:8080/TeachSystem/image/bar.png', '32', '1', '2016088', '2016088', '15862175370', '/65QOtXu4TgNK8mDJApyoFHSFaCf+EuWypO9Qq4hjMF/WN+azPGiHoVBuCS4wF9uNr0CH+PbR4eTjUv4WpUFpQrEaTnRx5wr');
INSERT INTO `student` VALUES ('20160511', '李伟', '男', 'http://10.20.0.44:8080/TeachSystem/image/bar.png', '23', '1', '20160511', '20160511', '15862175378', 'Un1rr43L+HAzOGfCTsfx1eaqNUkxoXHMtiDst/AZq4Jt9BiTz8ktBuKXMoFSbpCrNrRk0p4WbGk8r/3eermokNYRdwxKeazD');
INSERT INTO `student` VALUES ('20160512', '张伟', '男', 'http://10.20.0.44:8080/TeachSystem/image/bar.png', '24', '2', '20160512', '20160512', '15862175376', '9dhK8gwne3G6/v2CoUHDslHSFaCf+EuWypO9Qq4hjMF/WN+azPGiHndmbJAQjXyUFKsjG26/PvKtAd2bs9+xawrEaTnRx5wr');
INSERT INTO `student` VALUES ('20160513', '王旭', '男', null, '19', '4', '20160513', '20160513', '13878982345', null);
INSERT INTO `student` VALUES ('20160515', '赵雪', '女', null, '22', '3', '20160515', '20160515', '17835789745', null);
INSERT INTO `student` VALUES ('20160517', '黄晓明', '男', null, '36', '3', '20160517', '20160517', '1473980887', null);
INSERT INTO `student` VALUES ('20160518', '郑雯', '女', null, '34', '4', '20160518', '20160518', '15678906548', null);
INSERT INTO `student` VALUES ('20160519', '王霞', '男', null, '15', '1', '20160519', '20160519', '1567890987', null);
INSERT INTO `student` VALUES ('20160520', '刘刚', '男', null, '26', '2', '20160520', '20160520', '15862175370', null);

-- ----------------------------
-- Table structure for studentshare
-- ----------------------------
DROP TABLE IF EXISTS `studentshare`;
CREATE TABLE `studentshare` (
  `S_share_id` int(6) NOT NULL AUTO_INCREMENT,
  `Sno` int(11) NOT NULL,
  `Content` varchar(200) DEFAULT NULL,
  `Image` varchar(200) DEFAULT NULL,
  `Time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`S_share_id`),
  KEY `fk3` (`Sno`),
  CONSTRAINT `fk3` FOREIGN KEY (`Sno`) REFERENCES `student` (`Sno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentshare
-- ----------------------------

-- ----------------------------
-- Table structure for submitquestion
-- ----------------------------
DROP TABLE IF EXISTS `submitquestion`;
CREATE TABLE `submitquestion` (
  `sq_id` int(5) NOT NULL AUTO_INCREMENT,
  `sno` int(11) NOT NULL,
  `Tno` int(4) NOT NULL,
  `sq_content` varchar(255) DEFAULT NULL,
  `state` int(7) NOT NULL DEFAULT '0',
  PRIMARY KEY (`sq_id`),
  KEY `fk13` (`sno`),
  KEY `fk14` (`Tno`),
  CONSTRAINT `fk13` FOREIGN KEY (`sno`) REFERENCES `student` (`Sno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk14` FOREIGN KEY (`Tno`) REFERENCES `teacher` (`Tno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of submitquestion
-- ----------------------------
INSERT INTO `submitquestion` VALUES ('1', '20160511', '20120510', '这个题目好难啊？', '1');
INSERT INTO `submitquestion` VALUES ('2', '20160511', '20120510', '第一题不会做？', '1');
INSERT INTO `submitquestion` VALUES ('3', '2016088', '20120510', '数据库有点难', '1');
INSERT INTO `submitquestion` VALUES ('4', '20160511', '20120510', '的点点滴滴？', '1');
INSERT INTO `submitquestion` VALUES ('5', '20160511', '20120510', '发多少功夫好干部？', '1');
INSERT INTO `submitquestion` VALUES ('6', '20160511', '20120510', '的股份合格和？', '1');
INSERT INTO `submitquestion` VALUES ('7', '20160511', '20120510', '对方提供人非官方', '1');
INSERT INTO `submitquestion` VALUES ('8', '20160511', '20120510', '高数难', '1');
INSERT INTO `submitquestion` VALUES ('9', '20160512', '20120510', '老师，高数第五题请教', '1');
INSERT INTO `submitquestion` VALUES ('10', '20160511', '20120510', '顶顶顶顶', '0');
INSERT INTO `submitquestion` VALUES ('11', '20160511', '20120510', '热热热', '0');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `Tno` int(4) NOT NULL AUTO_INCREMENT,
  `Tname` varchar(4) NOT NULL,
  `Tsex` varchar(2) NOT NULL,
  `Tage` int(3) NOT NULL,
  `Ticon` varchar(100) DEFAULT NULL,
  `Dno` int(2) NOT NULL,
  `Tprof` varchar(5) DEFAULT NULL,
  `Tuser` varchar(11) NOT NULL,
  `Tpass` varchar(11) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `token` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Tno`),
  KEY `fk` (`Dno`),
  CONSTRAINT `fk` FOREIGN KEY (`Dno`) REFERENCES `dept` (`Dno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20120512 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('20120510', '刘老师', '男', '41', 'http://10.20.0.44:8080/TeachSystem/image/bar.png', '1', '教授', '20120510', '20120510', '15892789098', '9e95QJUGoPsQ1VwcIlAzYlHSFaCf+EuWypO9Qq4hjMECstemxuPAZftngLTPUbqAT4eY6E+Txda57L/bBfzi5grEaTnRx5wr');
INSERT INTO `teacher` VALUES ('20120511', '张老师', '男', '45', 'http://10.20.0.44:8080/TeachSystem/image/bar.png', '1', '讲师', '20120511', '20120511', '13898976547', '6gK/YSevqK6oSoHrHoCHyOaqNUkxoXHMtiDst/AZq4Jt9BiTz8ktBsEtmjBBesZS5MlJWxiGp/SAkIp5sjRJPtYRdwxKeazD');

-- ----------------------------
-- Table structure for teacheradvice
-- ----------------------------
DROP TABLE IF EXISTS `teacheradvice`;
CREATE TABLE `teacheradvice` (
  `ta_id` int(11) NOT NULL AUTO_INCREMENT,
  `tno` int(4) NOT NULL,
  `sno` int(11) NOT NULL,
  `ta_content` varchar(200) NOT NULL,
  `ta_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ta_id`),
  KEY `fk43` (`tno`),
  KEY `fk44` (`sno`),
  CONSTRAINT `fk43` FOREIGN KEY (`tno`) REFERENCES `teacher` (`Tno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk44` FOREIGN KEY (`sno`) REFERENCES `student` (`Sno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacheradvice
-- ----------------------------
INSERT INTO `teacheradvice` VALUES ('1', '20120510', '20160511', '较强学习', '2016-03-26 17:20:43');
INSERT INTO `teacheradvice` VALUES ('2', '20120510', '20160511', '个人个人个人发个广告', '2016-03-27 10:08:21');
INSERT INTO `teacheradvice` VALUES ('3', '20120510', '2016088', '反反复复', '2016-05-19 17:23:42');
INSERT INTO `teacheradvice` VALUES ('4', '20120510', '20160518', '好好学习', '2016-05-25 09:50:07');
INSERT INTO `teacheradvice` VALUES ('5', '20120510', '20160511', '反反复复', '2016-05-25 14:47:40');

-- ----------------------------
-- Table structure for teachershare
-- ----------------------------
DROP TABLE IF EXISTS `teachershare`;
CREATE TABLE `teachershare` (
  `T_share_id` int(11) NOT NULL AUTO_INCREMENT,
  `Tno` int(4) NOT NULL,
  `Tcontent` varchar(200) DEFAULT NULL,
  `Timage` varchar(200) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`T_share_id`),
  KEY `fk4` (`Tno`),
  CONSTRAINT `fk4` FOREIGN KEY (`Tno`) REFERENCES `teacher` (`Tno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teachershare
-- ----------------------------

-- ----------------------------
-- Table structure for teachnotify
-- ----------------------------
DROP TABLE IF EXISTS `teachnotify`;
CREATE TABLE `teachnotify` (
  `notify_id` int(6) NOT NULL AUTO_INCREMENT,
  `notify_content` varchar(200) NOT NULL,
  `notify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`notify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teachnotify
-- ----------------------------
INSERT INTO `teachnotify` VALUES ('1', '12软件1班今天下午课不上了', '2016-03-09 22:49:17');
INSERT INTO `teachnotify` VALUES ('3', '明天下午全体信电学生开会', '2016-03-09 22:50:37');
INSERT INTO `teachnotify` VALUES ('5', '2016年毕业生晚会今晚举行', '2016-03-09 22:51:31');
INSERT INTO `teachnotify` VALUES ('16', '明天下召开主题班会', '2016-03-10 20:49:16');
INSERT INTO `teachnotify` VALUES ('17', '12软件1班今天下午有数据库', '2016-03-10 20:52:35');
INSERT INTO `teachnotify` VALUES ('23', '12软件1班同学注意:明天下午的英语课调到周五了————张老师', '2016-03-22 22:59:47');
INSERT INTO `teachnotify` VALUES ('24', '是大大大滴滴答答', '2016-03-27 10:48:06');
INSERT INTO `teachnotify` VALUES ('25', '我校举办晚会', '2016-04-14 21:00:50');
INSERT INTO `teachnotify` VALUES ('28', '发反反复复', '2016-05-05 15:45:29');
INSERT INTO `teachnotify` VALUES ('30', '新年快乐', '2016-05-06 13:07:43');
INSERT INTO `teachnotify` VALUES ('31', '今天周五', '2016-05-08 09:46:58');
INSERT INTO `teachnotify` VALUES ('32', 'VB并不能', '2016-05-09 10:56:11');
INSERT INTO `teachnotify` VALUES ('33', '和顺丰单号的', '2016-05-10 13:11:16');
INSERT INTO `teachnotify` VALUES ('34', '今天好冷', '2016-05-15 13:12:21');
INSERT INTO `teachnotify` VALUES ('35', '111', '2016-05-15 14:56:15');
INSERT INTO `teachnotify` VALUES ('36', '呃呃呃', '2016-05-15 15:03:08');
INSERT INTO `teachnotify` VALUES ('37', '呃呃呃额额', '2016-05-15 15:05:38');
INSERT INTO `teachnotify` VALUES ('38', '的点点滴滴', '2016-05-19 17:23:20');
INSERT INTO `teachnotify` VALUES ('39', '今天是个好天气', '2016-05-25 09:49:45');
INSERT INTO `teachnotify` VALUES ('40', '南京', '2016-05-25 14:47:31');
INSERT INTO `teachnotify` VALUES ('41', '发反反复复', '2016-05-25 16:31:13');
INSERT INTO `teachnotify` VALUES ('42', '吞吞吐吐', '2016-05-25 16:46:17');

-- ----------------------------
-- View structure for choosecourse
-- ----------------------------
DROP VIEW IF EXISTS `choosecourse`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `choosecourse` AS SELECT course.cno,course.cname,course.classtime,course.Tno,teacher.Tname,course.cadress,course.content
from teacher,course
WHERE course.Tno=teacher.Tno ;

-- ----------------------------
-- View structure for mycourse
-- ----------------------------
DROP VIEW IF EXISTS `mycourse`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `mycourse` AS SELECT sc.sno,sc.cno,course.cname,course.classtime,course.Tno,teacher.Tname,course.cadress,course.content
from sc,teacher,course,student
WHERE course.Tno=teacher.Tno and sc.cno=course.cno and student.sno=sc.Sno ;

-- ----------------------------
-- View structure for mystudent
-- ----------------------------
DROP VIEW IF EXISTS `mystudent`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `mystudent` AS SELECT  course.Tno,teacher.Tname,sc.Sno,student.Sname,student.Ssex,student.Sicon,student.Sage,dept.Dname,student.phone
from  student,course,dept,sc,teacher
WHERE  sc.Sno=student.sno and sc.cno=course.cno and student.Dno=dept.Dno and course.Tno=teacher.Tno ;

-- ----------------------------
-- View structure for myteacher
-- ----------------------------
DROP VIEW IF EXISTS `myteacher`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `myteacher` AS SELECT  sc.Sno,sc.cno,course.cname,course.Tno,teacher.Tname,teacher.Tsex,teacher.Tage,teacher.Tprof,teacher.phone,teacher.Ticon,teacher.Dno,dept.Dname
from  student,teacher,course,dept,sc
WHERE  sc.Sno=student.sno and sc.cno=course.cno and teacher.Dno=dept.Dno and course.Tno=teacher.Tno ;

-- ----------------------------
-- View structure for question_and_answer
-- ----------------------------
DROP VIEW IF EXISTS `question_and_answer`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `question_and_answer` AS SELECT reply.sq_id,submitquestion.sq_content,reply.sno,student.Sname,reply.Tno,teacher.Tname,reply.reply_content
from  student,teacher,submitquestion,reply
WHERE reply.Sno=student.Sno and reply.Tno=teacher.Tno and reply.sq_id=submitquestion.sq_id ;

-- ----------------------------
-- View structure for score
-- ----------------------------
DROP VIEW IF EXISTS `score`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `score` AS SELECT sc.Sno,student.Sname,sc.cno,course.cname,sc.score
from  student,sc,course
WHERE  sc.Sno=student.Sno and sc.cno=course.cno ;

-- ----------------------------
-- View structure for studentsubmitquestion
-- ----------------------------
DROP VIEW IF EXISTS `studentsubmitquestion`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `studentsubmitquestion` AS SELECT submitquestion.sq_id,submitquestion.sno,student.Sname,submitquestion.Tno,teacher.Tname,submitquestion.sq_content,submitquestion.state
from  student,teacher,submitquestion
WHERE  submitquestion.Sno=student.Sno and submitquestion.Tno=teacher.Tno ;

-- ----------------------------
-- View structure for viewteacheradvice
-- ----------------------------
DROP VIEW IF EXISTS `viewteacheradvice`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `viewteacheradvice` AS SELECT  teacheradvice.sno,teacheradvice.tno,teacher.Tname,teacheradvice.ta_content,teacheradvice.ta_time
from  teacheradvice,teacher,student
WHERE  teacher.tno=teacheradvice.tno and teacheradvice.sno=student.Sno ;

-- ----------------------------
-- View structure for view_student_message
-- ----------------------------
DROP VIEW IF EXISTS `view_student_message`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `view_student_message` AS SELECT  mymessage.Tno,mymessage.sno,student.Sname,mymessage.mymessage_content,mymessage.send_time
from  mymessage,student
WHERE  mymessage.sno=student.Sno ;
