/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.7.17-log : Database - campusnews
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`campusnews` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `campusnews`;

/*Table structure for table `class` */

DROP TABLE IF EXISTS `class`;

CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `college_id` int(11) NOT NULL COMMENT '所属系别',
  `major_id` int(11) DEFAULT NULL COMMENT '所属专业ID',
  `name` varchar(50) DEFAULT NULL COMMENT '班级名称',
  `code` varchar(50) DEFAULT NULL COMMENT '班级编号',
  `instructor` int(11) DEFAULT NULL COMMENT '导员',
  PRIMARY KEY (`id`),
  KEY `college_id` (`college_id`),
  KEY `instructor` (`instructor`),
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`),
  CONSTRAINT `class_ibfk_2` FOREIGN KEY (`instructor`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `class` */

/*Table structure for table `college` */

DROP TABLE IF EXISTS `college`;

CREATE TABLE `college` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '学院名称',
  `dean` int(11) DEFAULT NULL COMMENT '院长',
  PRIMARY KEY (`id`),
  KEY `dean` (`dean`),
  CONSTRAINT `college_ibfk_1` FOREIGN KEY (`dean`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `college` */

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `context` varchar(3000) NOT NULL COMMENT '新闻主体内容',
  `title` varchar(200) NOT NULL COMMENT '新闻标题',
  `pubdate` datetime NOT NULL COMMENT '新闻发布时间',
  `author` int(11) NOT NULL COMMENT '作者',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `author` (`author`),
  CONSTRAINT `news_ibfk_1` FOREIGN KEY (`author`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `news` */

/*Table structure for table `publish_new` */

DROP TABLE IF EXISTS `publish_new`;

CREATE TABLE `publish_new` (
  `id` int(11) NOT NULL,
  `new_id` int(11) NOT NULL COMMENT '新闻ID',
  `user_id` int(11) DEFAULT NULL COMMENT '指定所要给的个人',
  `class_id` int(11) DEFAULT NULL COMMENT '指定班级',
  `college_id` int(11) DEFAULT NULL COMMENT '指定学院',
  `all` tinyint(1) DEFAULT NULL COMMENT '指定是否所有人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `publish_new` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL COMMENT '权限名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`role_name`) values (1,'学生'),(2,'老师'),(3,'系主任'),(4,'管理员'),(5,'校级新闻发布'),(6,'院级新闻发布'),(7,'班级新闻发布');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '学生用户ID',
  `number` varchar(50) DEFAULT NULL COMMENT '学号',
  `class_id` int(11) NOT NULL COMMENT '所属班级',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL COMMENT '用户名(应该和微信一致)',
  `code` varchar(50) DEFAULT NULL COMMENT '用户编号(应该是学号)',
  `name` varchar(10) DEFAULT NULL COMMENT '用户真实姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `sex` varchar(5) DEFAULT NULL COMMENT '性别',
  `age` int(10) unsigned DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`code`,`name`,`phone`,`sex`,`age`) values (1,'dwq','2211789','董文强','18892061129','男',22);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '用户权限ID',
  PRIMARY KEY (`id`,`user_id`,`role_id`),
  KEY `user_id` (`user_id`),
  KEY `role` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
