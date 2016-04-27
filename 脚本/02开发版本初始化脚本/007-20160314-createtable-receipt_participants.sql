/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.6.24-log : Database - socialbiz
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `receipt_participants` */

DROP TABLE IF EXISTS `receipt_participants`;

CREATE TABLE `receipt_participants` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `RECEIPTID` int(10) NOT NULL COMMENT '回执基本信息主键',
  `NAME` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `MOBILE` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `POSITION` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职务',
  `PHONE` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '座机',
  `EMAIL` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
