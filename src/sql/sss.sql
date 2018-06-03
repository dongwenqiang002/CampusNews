/*
SQLyog Community v10.0 
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

/*Table structure for table `manage_menu` */

DROP TABLE IF EXISTS `manage_menu`;

CREATE TABLE `manage_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(50) DEFAULT NULL,
  `index` varchar(50) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `role_level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `manage_menu_ibfk_1` (`parent_id`),
  CONSTRAINT `manage_menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `manage_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `manage_user` */

DROP TABLE IF EXISTS `manage_user`;

CREATE TABLE `manage_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `manage_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `context` varchar(3000) NOT NULL COMMENT '新闻主体内容',
  `title` varchar(200) NOT NULL COMMENT '新闻标题',
  `pubdate` datetime NOT NULL COMMENT '新闻发布时间',
  `author` int(11) NOT NULL COMMENT '作者',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `end_time` datetime NOT NULL COMMENT '新闻失效时间',
  `type` int(11) DEFAULT NULL COMMENT '新闻类型',
  PRIMARY KEY (`id`),
  KEY `author` (`author`),
  KEY `type` (`type`),
  CONSTRAINT `news_ibfk_1` FOREIGN KEY (`author`) REFERENCES `user` (`id`),
  CONSTRAINT `news_ibfk_2` FOREIGN KEY (`type`) REFERENCES `news_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `news_detail` */

DROP TABLE IF EXISTS `news_detail`;

CREATE TABLE `news_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) DEFAULT NULL COMMENT '新闻类型',
  `Importance` int(11) DEFAULT NULL COMMENT '新闻重要程度',
  `deadline` date DEFAULT NULL COMMENT '新闻过期时间',
  `importance_deadline` date DEFAULT NULL COMMENT '新闻重要性有效时间',
  `news_id` int(11) DEFAULT NULL COMMENT '新闻ID',
  PRIMARY KEY (`id`),
  KEY `news_id` (`news_id`),
  CONSTRAINT `news_detail_ibfk_1` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `news_type` */

DROP TABLE IF EXISTS `news_type`;

CREATE TABLE `news_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_name` varchar(50) NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `publish_news` */

DROP TABLE IF EXISTS `publish_news`;

CREATE TABLE `publish_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `news_id` int(11) NOT NULL COMMENT '所发布的新闻ID',
  `schoolOS_id` int(11) DEFAULT NULL COMMENT '所要给组织传达的新闻',
  `user_id` int(11) DEFAULT NULL COMMENT '所要给个人传达的新闻',
  PRIMARY KEY (`id`),
  KEY `news_id` (`news_id`),
  KEY `schoolOS_id` (`schoolOS_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `publish_news_ibfk_1` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`),
  CONSTRAINT `publish_news_ibfk_2` FOREIGN KEY (`schoolOS_id`) REFERENCES `schoolos` (`id`),
  CONSTRAINT `publish_news_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL COMMENT '权限名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `schoolos` */

DROP TABLE IF EXISTS `schoolos`;

CREATE TABLE `schoolos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父组织ID',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `code` varchar(100) DEFAULT NULL COMMENT '编号',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `schoolos_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `schoolos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(266) NOT NULL COMMENT '用户名(就是微信的OPENID)唯一',
  `code` varchar(50) DEFAULT NULL COMMENT '用户编号(应该是学号)',
  `name` varchar(10) DEFAULT NULL COMMENT '用户真实姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `sex` varchar(5) DEFAULT NULL COMMENT '性别',
  `age` int(10) unsigned DEFAULT NULL COMMENT '年龄',
  `wxName` varchar(50) DEFAULT NULL COMMENT '微信名',
  `avatarUrl` varchar(200) DEFAULT NULL COMMENT '微信头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Table structure for table `user_schoolos` */

DROP TABLE IF EXISTS `user_schoolos`;

CREATE TABLE `user_schoolos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `schoolOS_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `schoolOS_is` (`schoolOS_id`),
  CONSTRAINT `user_schoolos_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_schoolos_ibfk_2` FOREIGN KEY (`schoolOS_id`) REFERENCES `schoolos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Table structure for table `user_student` */

DROP TABLE IF EXISTS `user_student`;

CREATE TABLE `user_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_code` varchar(100) DEFAULT NULL COMMENT '学号',
  `user_id` int(11) DEFAULT NULL COMMENT '学生的用户ID',
  `school_time` date DEFAULT NULL COMMENT '入学时间',
  `grade` int(11) DEFAULT NULL COMMENT '所在的第几界',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
