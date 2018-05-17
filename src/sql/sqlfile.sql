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
  `major_id` int(11) DEFAULT NULL COMMENT '所属专业ID',
  `name` varchar(50) DEFAULT NULL COMMENT '班级名称',
  `code` varchar(50) DEFAULT NULL COMMENT '班级编号',
  `instructor` int(11) DEFAULT NULL COMMENT '导员',
  PRIMARY KEY (`id`),
  KEY `instructor` (`instructor`),
  KEY `major_id` (`major_id`),
  CONSTRAINT `class_ibfk_2` FOREIGN KEY (`instructor`) REFERENCES `user` (`id`),
  CONSTRAINT `class_ibfk_3` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `class` */

insert  into `class`(`id`,`major_id`,`name`,`code`,`instructor`) values (1,1,'软件班','1502',1);

/*Table structure for table `college` */

DROP TABLE IF EXISTS `college`;

CREATE TABLE `college` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '学院名称',
  `dean` int(11) DEFAULT NULL COMMENT '院长',
  PRIMARY KEY (`id`),
  KEY `dean` (`dean`),
  CONSTRAINT `college_ibfk_1` FOREIGN KEY (`dean`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `college` */

insert  into `college`(`id`,`name`,`dean`) values (1,'信息学院',1);

/*Table structure for table `major` */

DROP TABLE IF EXISTS `major`;

CREATE TABLE `major` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '专业名称',
  `college_id` int(11) DEFAULT NULL COMMENT '专业所属学院',
  PRIMARY KEY (`id`),
  KEY `college_id` (`college_id`),
  CONSTRAINT `major_ibfk_1` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `major` */

insert  into `major`(`id`,`name`,`college_id`) values (1,'软件工程',1);

/*Table structure for table `new_publish` */

DROP TABLE IF EXISTS `new_publish`;

CREATE TABLE `new_publish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `new_id` int(11) NOT NULL COMMENT '新闻ID',
  `user_id` int(11) DEFAULT NULL COMMENT '指定所要给的个人',
  `class_id` int(11) DEFAULT NULL COMMENT '指定班级',
  `college_id` int(11) DEFAULT NULL COMMENT '指定学院',
  `all` tinyint(1) DEFAULT NULL COMMENT '指定是否所有人',
  PRIMARY KEY (`id`),
  KEY `new_id` (`new_id`),
  KEY `user_id` (`user_id`),
  KEY `college_id` (`college_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `new_publish_ibfk_1` FOREIGN KEY (`new_id`) REFERENCES `news` (`id`),
  CONSTRAINT `new_publish_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `new_publish_ibfk_3` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`),
  CONSTRAINT `new_publish_ibfk_4` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `new_publish` */

insert  into `new_publish`(`id`,`new_id`,`user_id`,`class_id`,`college_id`,`all`) values (1,1,1,NULL,NULL,NULL),(2,1,2,NULL,NULL,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `news` */

insert  into `news`(`id`,`context`,`title`,`pubdate`,`author`,`remark`) values (1,'毋庸置疑，Spring Boot在众多从事Java微服务开发的程序员群体中是一个很特别的存在。说它特别是因为它确实简化了基于Spring技术栈的应用/微服务开发过程，使得我们能够很快速地就搭建起一个应用的脚手架并在其上进行项目的开发，再也不用像以前那样使用大量的XML或是注解了，应用在这样的约定优于配置的前提下可以以最快的速度创建出来。','我眼中的Spring Boot','2018-05-17 13:28:50',1,'转载自CSDN');

/*Table structure for table `news_detail` */

DROP TABLE IF EXISTS `news_detail`;

CREATE TABLE `news_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) DEFAULT NULL COMMENT '新闻类型',
  `Importance` int(11) DEFAULT NULL COMMENT '新闻重要程度',
  `deadline` date DEFAULT NULL COMMENT '新闻过期时间',
  `Importance_deadline` date DEFAULT NULL COMMENT '新闻重要性有效时间',
  `news_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `news_id` (`news_id`),
  CONSTRAINT `news_detail_ibfk_1` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `news_detail` */

insert  into `news_detail`(`id`,`type_name`,`Importance`,`deadline`,`Importance_deadline`,`news_id`) values (1,'学习',1,'2018-05-24','2018-05-20',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`user_id`,`number`,`class_id`) values (1,2,'201522224154',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`code`,`name`,`phone`,`sex`,`age`) values (1,'dwq','2211789','董文强','18892061129','男',22),(2,'sj','2211454','石娟','18829503014','女',21);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values (1,1,4),(2,2,1),(3,2,5);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
