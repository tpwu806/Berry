/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.6.10 : Database - iot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`iot` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `iot`;

/*Table structure for table `device_device` */

DROP TABLE IF EXISTS `device_device`;

CREATE TABLE `device_device` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `DEVICENAME` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DEVICEIP` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DEVICEPORT` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DEVICESTATUS` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DEVICETYPE` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SENSORNUMBER` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `device_device` */

/*Table structure for table `device_type` */

DROP TABLE IF EXISTS `device_type`;

CREATE TABLE `device_type` (
  `DEVICETYPEID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `DEVICETYPE` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`DEVICETYPEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `device_type` */

/*Table structure for table `sensor_sensor` */

DROP TABLE IF EXISTS `sensor_sensor`;

CREATE TABLE `sensor_sensor` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SENSORNAME` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SENSORTYPE` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SENSORPARAMETER` int(20) NOT NULL,
  `SENSORPARAMETER2` int(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sensor_sensor` */

/*Table structure for table `sensor_type` */

DROP TABLE IF EXISTS `sensor_type`;

CREATE TABLE `sensor_type` (
  `SENSORTYPEID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SENSORTYPE` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`SENSORTYPEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sensor_type` */

/*Table structure for table `supervise_supervise` */

DROP TABLE IF EXISTS `supervise_supervise`;

CREATE TABLE `supervise_supervise` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `DEVICENAME` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SENSORNAME` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SUPERVISETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `SUPERVISEVALUE` int(10) NOT NULL,
  `SUPERVISEVALUE2` int(10) DEFAULT NULL,
  `WARNINGCLASS` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `supervise_supervise` */

/*Table structure for table `user_user` */

DROP TABLE IF EXISTS `user_user`;

CREATE TABLE `user_user` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `USER_PASS` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `user_user` */

insert  into `user_user`(`ID`,`USER_NAME`,`USER_PASS`) values (1,'admin','admin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
