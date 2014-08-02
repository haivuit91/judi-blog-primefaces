CREATE DATABASE  IF NOT EXISTS `db_judiwebsite` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_judiwebsite`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: db_judiwebsite
-- ------------------------------------------------------
-- Server version	5.6.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categoryID` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(65) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`categoryID`),
  UNIQUE KEY `categoryName_UNIQUE` (`categoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'JAVA',1),(2,'C#',1),(3,'Android',1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `postID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(145) NOT NULL,
  `content` varchar(20000) NOT NULL,
  `imagePath` varchar(105) DEFAULT NULL,
  `postDate` date NOT NULL,
  `userID` int(11) NOT NULL,
  `categoryID` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `idx` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`postID`),
  KEY `userID_idx` (`userID`),
  KEY `categoryID_idx` (`categoryID`),
  CONSTRAINT `categoryID` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (3,'title','Content',NULL,'2013-12-12',6,1,1,0),(4,'Title2','Content2',NULL,'2013-12-14',6,1,1,0),(5,'Tile234','Contents',NULL,'2013-12-05',6,1,1,0);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `projectID` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(105) NOT NULL,
  `description` varchar(205) NOT NULL,
  `startDate` date NOT NULL,
  `duration` int(11) NOT NULL,
  `typeID` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `idx` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`projectID`),
  KEY `typeID_idx` (`typeID`),
  CONSTRAINT `typeID` FOREIGN KEY (`typeID`) REFERENCES `type` (`typeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (8,'Judi Website','Website introduce our team (JUDI Team)','2014-07-30',20,3,0,0),(9,'Game Caro','Game Caro Game Caro','2014-07-21',20,6,1,0),(10,'New Project','New Project New Project','2014-08-10',12,5,0,0),(11,'Project second','The Second ProjectThe Second ProjectThe ','2014-08-07',10,6,1,0),(14,'Ngoc Duy','Duy CIT CIT CIT','2014-07-31',230,3,1,0);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_user_details`
--

DROP TABLE IF EXISTS `project_user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_user_details` (
  `projectUserID` int(11) NOT NULL AUTO_INCREMENT,
  `projectID` int(11) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `creator` tinyint(1) NOT NULL,
  `idx` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`projectUserID`),
  KEY `projectID` (`projectID`),
  KEY `userID` (`userID`),
  CONSTRAINT `project_user_details_ibfk_1` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`),
  CONSTRAINT `project_user_details_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_user_details`
--

LOCK TABLES `project_user_details` WRITE;
/*!40000 ALTER TABLE `project_user_details` DISABLE KEYS */;
INSERT INTO `project_user_details` VALUES (25,8,6,1,0),(26,9,6,1,0),(27,10,7,1,0),(28,11,7,1,0),(30,8,7,0,0),(32,14,8,1,0);
/*!40000 ALTER TABLE `project_user_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `roleID` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(45) NOT NULL,
  `imagePath` varchar(105) DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Administrator',NULL,1),(2,'Member',NULL,1),(3,'SMod',NULL,1),(4,'Mod',NULL,1);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `typeID` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(45) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`typeID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'Java Web Application',1),(2,'Android',1),(3,'J2SE',1),(4,'iOS',1),(5,'C#.NET',1),(6,'ASP.NET',1);
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `fullName` varchar(55) NOT NULL,
  `birthOfDay` date DEFAULT NULL,
  `gender` tinyint(1) NOT NULL,
  `idCard` varchar(15) DEFAULT NULL,
  `address` varchar(105) DEFAULT NULL,
  `email` varchar(55) NOT NULL,
  `phoneNumber` varchar(15) DEFAULT NULL,
  `imagePath` varchar(105) DEFAULT NULL,
  `roleID` int(11) NOT NULL,
  `idActive` varchar(45) DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  `idx` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userName_UNIQUE` (`userName`),
  KEY `roleID_idx` (`roleID`),
  CONSTRAINT `roleID` FOREIGN KEY (`roleID`) REFERENCES `role` (`roleID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,'admin','21232f297a57a5a743894a0e4a801fc3','Phạm Đình Công','1991-11-23',1,'173549176','Thanh Hóa','congpd.itedu@gmail.com','0982207386','images/avatar/admin.jpg',1,NULL,1,0),(7,'congpd','986d081e5969b421aebc7f28e15ce9ac','Judi Công','1991-11-23',1,'123534534','Thanh Hóa','congpd.itedu@gmail.com','0982207386','images/avatar/avatar_male.jpg',2,'',1,0),(8,'duynn','66cf7cc25fdc643bacc2f98bb442a6d3','Nguyễn Ngọc Duy','1990-03-27',1,'213152342','Quảng Nam','duynn.itedu@gmail.com','0982207386','images/avatar/avatar_female.jpg',2,'',1,0),(9,'haivv','af81b60da20b67904b0d2b9b56aee5ad','Vũ Văn Hải','1991-08-07',1,'213453423','Thanh Hóa','haivv.itedu@gmail.com','0983245654','images/avatar/avatar_male.jpg',2,'5165b188c5fe1d8b9f3c2e9623911f54',0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-02 20:00:07
