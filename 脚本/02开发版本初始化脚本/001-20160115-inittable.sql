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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`socialbiz` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `socialbiz`;

/*Table structure for table `forum_cat` */

DROP TABLE IF EXISTS `forum_cat`;

CREATE TABLE `forum_cat` (
  `ID` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `CATNAME` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CATDESC` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `AUTHLVL` int(1) unsigned DEFAULT NULL,
  `ISPARENTCAT` tinyint(4) NOT NULL,
  `CREATEDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `forum_cat` */

insert  into `forum_cat`(`ID`,`CATNAME`,`CATDESC`,`AUTHLVL`,`ISPARENTCAT`,`CREATEDATE`) values (1,'项目申报','项目申报',0,1,'2015-07-29 02:17:36'),(2,'企业金融','企业金融',0,1,'2015-07-29 02:17:36'),(3,'人力资源','人力资源',0,1,'2015-07-29 02:17:36'),(4,'技术平台','技术平台',0,1,'2015-07-29 02:17:36'),(5,'资质认证','资质认证',0,1,'2015-07-29 02:17:36'),(6,'配套保障','配套保障',0,1,'2015-07-29 02:17:36'),(7,'众创空间','众创空间',0,1,'2015-07-29 02:17:36');

/*Table structure for table `forum_post` */

DROP TABLE IF EXISTS `forum_post`;

CREATE TABLE `forum_post` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `PARENTCAT` int(5) unsigned DEFAULT NULL,
  `AUTHLVL` int(1) unsigned DEFAULT NULL,
  `THREADSTARTER` tinyint(4) DEFAULT NULL,
  `THREADSTARTERID` int(10) unsigned DEFAULT NULL,
  `TITLE` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONTENT` varchar(4000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IMAGEURLS` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `AUTHOR` int(10) unsigned NOT NULL,
  `POSTNUM` int(10) NOT NULL,
  `POSTDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `NUMREPLIES` int(5) DEFAULT NULL,
  `NUMVIEWS` int(10) DEFAULT NULL,
  `EDITDATE` timestamp NULL DEFAULT NULL,
  `EDITAUTHOR` int(10) unsigned DEFAULT NULL,
  `STATUS` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ISPUBLISHED` tinyint(4) NOT NULL,
  `ISDELETED` tinyint(4) DEFAULT NULL,
  `DELETEDATE` timestamp NULL DEFAULT NULL,
  `DELETEDBY` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `FK_FORUM_FPPARENTCAT` (`PARENTCAT`),
  KEY `FK_FORUM_FPAUTHOR` (`AUTHOR`),
  KEY `FK_FORUM_FPEDITAUTHOR` (`EDITAUTHOR`),
  KEY `FK_FORUM_FPDELETEDBY` (`DELETEDBY`),
  KEY `IDX_FORUM_FPTHREADSTARTERID` (`THREADSTARTERID`),
  KEY `IDX_FORUM_FPTHREADSTARTER` (`THREADSTARTER`),
  KEY `IDX_FORUM_FPPOSTDATE` (`POSTDATE`),
  CONSTRAINT `FK_FORUM_FPAUTHOR` FOREIGN KEY (`AUTHOR`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_FORUM_FPDELETEDBY` FOREIGN KEY (`DELETEDBY`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_FORUM_FPEDITAUTHOR` FOREIGN KEY (`EDITAUTHOR`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_FORUM_FPPARENTCAT` FOREIGN KEY (`PARENTCAT`) REFERENCES `forum_cat` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `forum_post` */

/*Table structure for table `forum_status` */

DROP TABLE IF EXISTS `forum_status`;

CREATE TABLE `forum_status` (
  `ID` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `FORUM_ON` tinyint(4) NOT NULL,
  `EDITDATE` timestamp NULL DEFAULT NULL,
  `EDITED_BY` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `FK_FORUM_FSEDITEDBY` (`EDITED_BY`),
  CONSTRAINT `FK_FORUM_FSEDITEDBY` FOREIGN KEY (`EDITED_BY`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `forum_status` */

insert  into `forum_status`(`ID`,`FORUM_ON`,`EDITDATE`,`EDITED_BY`) values (1,1,NULL,NULL);

/*Table structure for table `news_cat` */

DROP TABLE IF EXISTS `news_cat`;

CREATE TABLE `news_cat` (
  `ID` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `CATNAME` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CATDESC` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `AUTHLVL` int(1) unsigned DEFAULT NULL,
  `ISPARENTCAT` tinyint(4) NOT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `news_cat` */

insert  into `news_cat`(`ID`,`CATNAME`,`CATDESC`,`AUTHLVL`,`ISPARENTCAT`,`CREATE_DATE`) values (1,'园区动态','园区动态',0,1,'2015-07-29 02:18:34'),(2,'企业新闻','企业新闻',0,1,'2015-07-29 02:18:34'),(3,'行业资讯','行业资讯',0,1,'2015-07-29 02:18:34');

/*Table structure for table `news_post` */

DROP TABLE IF EXISTS `news_post`;

CREATE TABLE `news_post` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `PARENTCAT` int(5) unsigned DEFAULT NULL,
  `AUTHLVL` int(1) unsigned DEFAULT NULL,
  `TITLE` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONTENT` varchar(6000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IMAGEURLS` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `AUTHOR` int(10) unsigned NOT NULL,
  `POSTNUM` int(10) DEFAULT NULL,
  `POSTDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `NUMVIEWS` int(10) DEFAULT NULL,
  `EDITDATE` timestamp NULL DEFAULT NULL,
  `EDITAUTHOR` int(10) unsigned DEFAULT NULL,
  `LASTPUBLISHDATE` timestamp NULL DEFAULT NULL,
  `STATUS` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ISPUBLISHED` tinyint(4) NOT NULL,
  `ISDELETED` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `FK_NEWS_NPPARENTCAT` (`PARENTCAT`),
  KEY `FK_NEWS_NPAUTHOR` (`AUTHOR`),
  KEY `FK_NEWS_NPEDITAUTHOR` (`EDITAUTHOR`),
  CONSTRAINT `FK_NEWS_NPAUTHOR` FOREIGN KEY (`AUTHOR`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_NEWS_NPEDITAUTHOR` FOREIGN KEY (`EDITAUTHOR`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_NEWS_NPPARENTCAT` FOREIGN KEY (`PARENTCAT`) REFERENCES `news_cat` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `news_post` */

/*Table structure for table `org_attr` */

DROP TABLE IF EXISTS `org_attr`;

CREATE TABLE `org_attr` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ORG_ID` int(10) unsigned NOT NULL,
  `YEAR` int(4) DEFAULT NULL,
  `REVENUE` decimal(14,2) DEFAULT NULL,
  `PROFIT` decimal(14,2) DEFAULT NULL,
  `TAXES_PAID` decimal(14,2) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `FK_ORG_OAORGID` (`ORG_ID`),
  CONSTRAINT `FK_ORG_OAORGID` FOREIGN KEY (`ORG_ID`) REFERENCES `org_org` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `org_attr` */

insert  into `org_attr`(`ID`,`ORG_ID`,`YEAR`,`REVENUE`,`PROFIT`,`TAXES_PAID`) values (1,2,2014,NULL,NULL,NULL),(2,2,2013,NULL,NULL,NULL),(3,2,2012,NULL,NULL,NULL);

/*Table structure for table `org_ext_contact` */

DROP TABLE IF EXISTS `org_ext_contact`;

CREATE TABLE `org_ext_contact` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ORG_ID` int(10) unsigned NOT NULL,
  `EXT_CONTACT` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EXT_ADDR_QILU_RM` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EXT_ADDR_QILU_LVL` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EXT_ADDR_QILU_BLDG` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `FK_ORG_OECORGID` (`ORG_ID`),
  CONSTRAINT `FK_ORG_OECORGID` FOREIGN KEY (`ORG_ID`) REFERENCES `org_org` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `org_ext_contact` */

/*Table structure for table `org_org` */

DROP TABLE IF EXISTS `org_org`;

CREATE TABLE `org_org` (
  `ID` int(10) unsigned NOT NULL,
  `ORGNAME` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ORGTYPE` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `INDUSTRYTYPE` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EST_DATE` datetime DEFAULT NULL,
  `GOVT_ORG_ID` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `GOVT_TAX_ID` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `REG_CAP` decimal(14,2) DEFAULT NULL,
  `REG_CAP_CURR` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PRI_CONTACT` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PRI_CONTACT_PHONENUM` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LEGAL_REP` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LEGAL_REP_PHONENUM` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ADDR_QILU_RM` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ADDR_QILU_LVL` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ADDR_QILU_BLDG` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `NUM_EMPLOYEES` int(10) DEFAULT NULL,
  `STOCK_SYMBOL` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PATENT_ID` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `SW_COPYRIGHT_COUNT` int(5) DEFAULT NULL,
  `LAST_RPTED_REVENUE` decimal(14,2) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` int(10) unsigned NOT NULL,
  `LAST_EDIT_DATE` timestamp NULL DEFAULT NULL,
  `EDITED_BY` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `FK_ORG_OOCREATEDBY` (`CREATED_BY`),
  KEY `FK_ORG_OOEDITEDBY` (`EDITED_BY`),
  CONSTRAINT `FK_ORG_OOCREATEDBY` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_ORG_OOEDITEDBY` FOREIGN KEY (`EDITED_BY`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_ORG_OOUSERID` FOREIGN KEY (`ID`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `org_org` */

insert  into `org_org`(`ID`,`ORGNAME`,`ORGTYPE`,`INDUSTRYTYPE`,`EST_DATE`,`GOVT_ORG_ID`,`GOVT_TAX_ID`,`REG_CAP`,`REG_CAP_CURR`,`PRI_CONTACT`,`PRI_CONTACT_PHONENUM`,`LEGAL_REP`,`LEGAL_REP_PHONENUM`,`ADDR_QILU_RM`,`ADDR_QILU_LVL`,`ADDR_QILU_BLDG`,`NUM_EMPLOYEES`,`STOCK_SYMBOL`,`PATENT_ID`,`SW_COPYRIGHT_COUNT`,`LAST_RPTED_REVENUE`,`CREATE_DATE`,`CREATED_BY`,`LAST_EDIT_DATE`,`EDITED_BY`) values (2,'齐鲁软件园',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A',70,NULL,NULL,NULL,0.00,'2015-07-29 02:20:28',1,'2015-08-30 21:22:30',1);

/*Table structure for table `sec_group_authorities` */

DROP TABLE IF EXISTS `sec_group_authorities`;

CREATE TABLE `sec_group_authorities` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `GROUP_ID` int(10) NOT NULL,
  `AUTHORITY` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SEC_SGAGROUPID` (`GROUP_ID`),
  CONSTRAINT `FK_SEC_SGAGROUPID` FOREIGN KEY (`GROUP_ID`) REFERENCES `sec_groups` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sec_group_authorities` */

insert  into `sec_group_authorities`(`ID`,`GROUP_ID`,`AUTHORITY`) values (1,1,'USRREG'),(2,2,'USRMGMT'),(3,3,'ORGREG'),(4,4,'ORGMGMT'),(5,5,'ORGMOD'),(6,6,'ORGADMIN');

/*Table structure for table `sec_group_members` */

DROP TABLE IF EXISTS `sec_group_members`;

CREATE TABLE `sec_group_members` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `GROUP_ID` int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SEC_SGMGROUPID` (`GROUP_ID`),
  CONSTRAINT `FK_SEC_SGMGROUPID` FOREIGN KEY (`GROUP_ID`) REFERENCES `sec_groups` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sec_group_members` */

insert  into `sec_group_members`(`ID`,`USERNAME`,`GROUP_ID`) values (8,'12345543211',6),(9,'12345543212',6),(11,'18105316991',6),(12,'15665759558',6);

/*Table structure for table `sec_groups` */

DROP TABLE IF EXISTS `sec_groups`;

CREATE TABLE `sec_groups` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `GROUP_NAME` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sec_groups` */

insert  into `sec_groups`(`ID`,`GROUP_NAME`) values (1,'G_USRREG'),(2,'G_USRMGMT'),(3,'G_ORGREG'),(4,'G_ORGMGMT'),(5,'G_ORGMOD'),(6,'G_ORGADMIN');

/*Table structure for table `sec_user_roles` */

DROP TABLE IF EXISTS `sec_user_roles`;

CREATE TABLE `sec_user_roles` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ROLE` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNI_USERNAME_ROLE` (`ROLE`,`USERNAME`),
  KEY `FK_USERNAME_IDX` (`USERNAME`),
  CONSTRAINT `FK_SEC_SURUSERNAME` FOREIGN KEY (`USERNAME`) REFERENCES `sec_users` (`USERNAME`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sec_user_roles` */

/*Table structure for table `sec_users` */

DROP TABLE IF EXISTS `sec_users`;

CREATE TABLE `sec_users` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `PASSWORD` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `REQPWDCHANGE` tinyint(4) NOT NULL DEFAULT '0',
  `ENABLED` tinyint(4) NOT NULL DEFAULT '1',
  `ACCOUNTNONEXPIRED` tinyint(4) NOT NULL DEFAULT '1',
  `ACCOUNTNONLOCKED` tinyint(4) NOT NULL DEFAULT '1',
  `CREDENTIALSNONEXPIRED` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`),
  KEY `FK_USERNAME_IDX` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sec_users` */

insert  into `sec_users`(`ID`,`USERNAME`,`PASSWORD`,`REQPWDCHANGE`,`ENABLED`,`ACCOUNTNONEXPIRED`,`ACCOUNTNONLOCKED`,`CREDENTIALSNONEXPIRED`) values (1,'12345543211','$2a$10$S1qvYuNGqZppo/pvzS0uuedSgQFLeKAbE2Yv5jbPZb2nG/TWaiz1i',0,1,1,1,1),(2,'12345543212','$2a$10$S1qvYuNGqZppo/pvzS0uuedSgQFLeKAbE2Yv5jbPZb2nG/TWaiz1i',0,1,1,1,1),(3,'15665759558','$2a$10$S1qvYuNGqZppo/pvzS0uuedSgQFLeKAbE2Yv5jbPZb2nG/TWaiz1i',0,1,1,1,1),(4,'18105316991','$2a$10$S1qvYuNGqZppo/pvzS0uuedSgQFLeKAbE2Yv5jbPZb2nG/TWaiz1i',0,1,1,1,1);

/*Table structure for table `sms_reqres_district` */

DROP TABLE IF EXISTS `sms_reqres_district`;

CREATE TABLE `sms_reqres_district` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `imei` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phonenum` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `apptype` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sms_reqres_district` */

/*Table structure for table `sms_typestatus` */

DROP TABLE IF EXISTS `sms_typestatus`;

CREATE TABLE `sms_typestatus` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `typestatus` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `editdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sms_typestatus` */

insert  into `sms_typestatus`(`id`,`typestatus`,`editdate`) values (1,'unicome','2016-01-22 13:13:27');

/*Table structure for table `updateinfo` */

DROP TABLE IF EXISTS `updateinfo`;

CREATE TABLE `updateinfo` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `version` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `updateinfo` */

insert  into `updateinfo`(`id`,`version`,`description`,`url`,`type`) values (1,'1.0.1','test',NULL,'android'),(2,'1.0.1','test',NULL,'ios');

/*Table structure for table `user_address` */

DROP TABLE IF EXISTS `user_address`;

CREATE TABLE `user_address` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) unsigned DEFAULT NULL,
  `ISPRIMARY` tinyint(4) DEFAULT NULL,
  `ADDR_TYPE` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `EMAIL1` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `EMAIL2` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PHONE1` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PHONE2` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FAX1` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FAX2` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `WEBSITE` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ADDR_NAME` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ADDR_STR` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ADDR_CITY` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ADDR_PROV` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ADDR_POSTALCODE` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ADDR_COUNTRY` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `STATUS` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `FIELD1` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FIELD2` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `FK_USER_UADUSERID` (`USER_ID`),
  CONSTRAINT `FK_USER_UADUSERID` FOREIGN KEY (`USER_ID`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `user_address` */

insert  into `user_address`(`ID`,`USER_ID`,`ISPRIMARY`,`ADDR_TYPE`,`EMAIL1`,`EMAIL2`,`PHONE1`,`PHONE2`,`FAX1`,`FAX2`,`WEBSITE`,`ADDR_NAME`,`ADDR_STR`,`ADDR_CITY`,`ADDR_PROV`,`ADDR_POSTALCODE`,`ADDR_COUNTRY`,`STATUS`,`FIELD1`,`FIELD2`) values (1,1,1,'WORK',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'NORMAL',NULL,NULL),(2,2,1,'WORK',NULL,NULL,NULL,NULL,NULL,NULL,'http://www.ciiic.cn',NULL,NULL,NULL,NULL,NULL,NULL,'PUBLISHED',NULL,NULL),(3,3,1,'WORK',NULL,NULL,'12345543212',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'NORMAL',NULL,NULL),(4,4,1,'WORK',NULL,NULL,'15665759558',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'NORMAL',NULL,NULL),(6,6,1,'WORK',NULL,NULL,'18105316991',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'NORMAL',NULL,NULL);

/*Table structure for table `user_blacklist` */

DROP TABLE IF EXISTS `user_blacklist`;

CREATE TABLE `user_blacklist` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) unsigned NOT NULL,
  `DATE_ADDED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DATE_REMOVED` timestamp NULL DEFAULT NULL,
  `ADDED_BY` int(10) unsigned NOT NULL,
  `REMOVED_BY` int(10) unsigned DEFAULT NULL,
  `STATUS` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `PHONENUM` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IMEI` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `FK_USER_UBUSERID` (`USER_ID`),
  KEY `FK_USER_UBADDEDBY` (`ADDED_BY`),
  KEY `FK_USER_UBREMOVEDBY` (`REMOVED_BY`),
  CONSTRAINT `FK_USER_UBADDEDBY` FOREIGN KEY (`ADDED_BY`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_USER_UBREMOVEDBY` FOREIGN KEY (`REMOVED_BY`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_USER_UBUSERID` FOREIGN KEY (`USER_ID`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `user_blacklist` */

/*Table structure for table `user_contact` */

DROP TABLE IF EXISTS `user_contact`;

CREATE TABLE `user_contact` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) unsigned NOT NULL,
  `ORG_ID` int(10) unsigned DEFAULT NULL,
  `JOB_TITLE` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `JOB_CLASS` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `JOB_PHONENUM` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `JOB_DEPT` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `JOB_JOIN_YEAR` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `FK_USER_UCUSERID` (`USER_ID`),
  KEY `FK_USER_UCORGIDID` (`ORG_ID`),
  CONSTRAINT `FK_USER_UCORGIDID` FOREIGN KEY (`ORG_ID`) REFERENCES `org_org` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_USER_UCUSERID` FOREIGN KEY (`USER_ID`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `user_contact` */

insert  into `user_contact`(`ID`,`USER_ID`,`ORG_ID`,`JOB_TITLE`,`JOB_CLASS`,`JOB_PHONENUM`,`JOB_DEPT`,`JOB_JOIN_YEAR`) values (1,1,2,NULL,'JOBCLASS_MID',NULL,NULL,NULL),(2,3,2,NULL,'JOBCLASS_MID',NULL,NULL,NULL),(3,4,2,NULL,'JOBCLASS_BASIC',NULL,NULL,NULL),(4,6,2,NULL,'JOBCLASS_MID',NULL,NULL,NULL);

/*Table structure for table `user_profile` */

DROP TABLE IF EXISTS `user_profile`;

CREATE TABLE `user_profile` (
  `ID` int(10) unsigned NOT NULL,
  `SEC_USERS_ID` int(10) unsigned NOT NULL,
  `PHONENUM` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IMEI` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IMAGEURL` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `G_NAME` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `S_NAME` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `NICKNAME` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `GENDER` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `BIRTHDATE` datetime DEFAULT NULL,
  `LAST_EDIT_DATE` timestamp NULL DEFAULT NULL,
  `EDITED_BY` int(10) unsigned DEFAULT NULL,
  `ISDELETED` tinyint(4) DEFAULT NULL,
  `DELETED_BY` int(10) unsigned DEFAULT NULL,
  `ISBLACKLISTED` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `FK_USER_UPSECUSERID` (`SEC_USERS_ID`),
  KEY `FK_USER_UPEDITEDBY` (`EDITED_BY`),
  KEY `FK_USER_UPDELETEDBY` (`DELETED_BY`),
  CONSTRAINT `FK_USER_UPDELETEDBY` FOREIGN KEY (`DELETED_BY`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_USER_UPEDITEDBY` FOREIGN KEY (`EDITED_BY`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_USER_UPSECUSERID` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `sec_users` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_USER_UPUSERID` FOREIGN KEY (`ID`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `user_profile` */

insert  into `user_profile`(`ID`,`SEC_USERS_ID`,`PHONENUM`,`IMEI`,`IMAGEURL`,`G_NAME`,`S_NAME`,`NICKNAME`,`GENDER`,`BIRTHDATE`,`LAST_EDIT_DATE`,`EDITED_BY`,`ISDELETED`,`DELETED_BY`,`ISBLACKLISTED`) values (1,1,'12345543211',NULL,'I1508301934007301829.jpeg','System Administrator',NULL,'SYSADMIN1','M',NULL,'2015-08-30 19:34:04',1,0,NULL,0),(3,2,'12345543212',NULL,NULL,'System Admintwo',NULL,'SYSADMIN2','M',NULL,NULL,NULL,0,NULL,0),(4,3,'15665759558',NULL,NULL,'彭绍毅',NULL,'jack','M',NULL,'2015-09-09 14:45:27',6,0,NULL,0),(6,4,'18105316991',NULL,NULL,'张峰',NULL,'张峰','M',NULL,NULL,NULL,0,NULL,0);

/*Table structure for table `user_reppoints` */

DROP TABLE IF EXISTS `user_reppoints`;

CREATE TABLE `user_reppoints` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) unsigned NOT NULL,
  `POINTS` int(10) unsigned NOT NULL,
  `LAST_ADDED` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `FK_USER_URUSERID` (`USER_ID`),
  CONSTRAINT `FK_USER_URUSERID` FOREIGN KEY (`USER_ID`) REFERENCES `user_user` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `user_reppoints` */

insert  into `user_reppoints`(`ID`,`USER_ID`,`POINTS`,`LAST_ADDED`) values (1,1,15,'2015-08-31 00:48:44'),(2,3,0,NULL),(3,4,5,'2015-09-07 09:36:43'),(4,6,0,NULL);

/*Table structure for table `user_user` */

DROP TABLE IF EXISTS `user_user`;

CREATE TABLE `user_user` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_TYPE` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `USER_DESC` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `user_user` */

insert  into `user_user`(`ID`,`USER_TYPE`,`USER_DESC`,`CREATE_DATE`) values (1,'SYSTEM ADMINISTRATOR','BUILT-IN ADMINISTRATOR FOR THIS SYSTEM','2015-07-29 02:08:13'),(2,'ORGANIZATION','BUILT-IN ORGANIZATION FOR THIS SYSTEM','2015-07-29 02:19:33'),(3,'USRREG','Regular registered user','2015-08-30 23:29:15'),(4,'USRREG','Regular registered user','2015-08-31 14:12:18'),(6,'USRREG','Regular registered user','2015-08-31 15:12:19');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
