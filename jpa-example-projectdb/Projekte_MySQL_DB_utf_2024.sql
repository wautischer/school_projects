CREATE DATABASE  IF NOT EXISTS `projekte` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `projekte`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: projekte
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `arbeitspakete`
--

DROP TABLE IF EXISTS `arbeitspakete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arbeitspakete` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `pr_nr` int unsigned NOT NULL,
  `code` varchar(5) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `Bezeichnung` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `PR_AP_FK_idx` (`pr_nr`),
  CONSTRAINT `PR_AP_FK` FOREIGN KEY (`pr_nr`) REFERENCES `projekte` (`nr`)
) ENGINE=InnoDB AUTO_INCREMENT=588 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arbeitspakete`
--

LOCK TABLES `arbeitspakete` WRITE;
/*!40000 ALTER TABLE `arbeitspakete` DISABLE KEYS */;
INSERT INTO `arbeitspakete` VALUES (1,1,'A001','Anforderungsanalyse'),(2,1,'A002','Userinterface Prototyp'),(3,1,'A003','Datenbank Design'),(4,1,'A004','Implementierung '),(5,1,'A005','Test'),(6,1,'A006','Dokumentation'),(7,1,'A007','Installation und Inbetriebnahme'),(8,1,'A008','Projektmanagement'),(9,2,'B001','Konzepterstellung'),(10,2,'B002','Hardware Design'),(11,2,'B003','Schaltungsentwurf'),(12,2,'B004','Bauteilbeschaffung'),(13,2,'B005','Software Evaluierung'),(14,2,'B006','Logik Entwicklung'),(15,2,'B007','Test Hardware'),(16,2,'B008','Test Software'),(17,2,'B009','Roboter Montage'),(18,2,'B010','Inbetriebnahme'),(19,2,'B011','Erstellung Dokumentation'),(20,3,'C001','Machbarkeitsstudie'),(21,3,'C002','Schaltungsentwurf'),(22,3,'C003','Bauteilbeschaffung'),(23,3,'C004','Aufbau Teststellung'),(24,3,'C005','Leistungsoptimierung'),(25,3,'C006','Test'),(26,3,'C007','Dokumentation'),(27,3,'C008 ','Inbetriebnahme'),(28,3,'C009','Projektmanagement'),(29,2,'B012','Projektmanagement'),(583,6,'ATDB1','Tabellenverknüpfungen und Design'),(584,6,'ATDB2','Formulare'),(585,6,'ATDB3','Berichte erstellen'),(586,6,'ATDB4','Buttonsteuerung'),(587,3,'C010','Test Arbeitspaket');
/*!40000 ALTER TABLE `arbeitspakete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_ap_zuord`
--

DROP TABLE IF EXISTS `ma_ap_zuord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_ap_zuord` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_id` int unsigned NOT NULL,
  `ap_id` int unsigned NOT NULL,
  `std` smallint unsigned NOT NULL,
  `datum` date NOT NULL,
  `anmerkung` varchar(200) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `maz_ap_fk_idx` (`ap_id`),
  KEY `maz_ma_fk_idx` (`ma_id`),
  CONSTRAINT `maz_ap_fk` FOREIGN KEY (`ap_id`) REFERENCES `arbeitspakete` (`id`),
  CONSTRAINT `maz_ma_fk` FOREIGN KEY (`ma_id`) REFERENCES `mitarbeiter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=318 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_ap_zuord`
--

LOCK TABLES `ma_ap_zuord` WRITE;
/*!40000 ALTER TABLE `ma_ap_zuord` DISABLE KEYS */;
INSERT INTO `ma_ap_zuord` VALUES (1,29,1,5,'2023-05-30','\r'),(2,25,1,3,'2023-05-30','\r'),(3,8,1,2,'2023-06-25','\r'),(4,22,1,3,'2023-07-14','\r'),(5,9,1,2,'2023-07-17','\r'),(6,3,1,3,'2023-08-06','\r'),(7,24,1,2,'2023-08-14','\r'),(8,33,1,1,'2023-08-26','\r'),(9,6,1,3,'2023-08-27','\r'),(10,31,1,5,'2023-09-03','\r'),(11,15,1,5,'2023-09-05','\r'),(12,29,1,5,'2023-09-13','\r'),(13,19,1,1,'2023-09-13','\r'),(14,16,1,3,'2023-09-22','\r'),(15,18,2,3,'2023-05-08','\r'),(16,23,2,4,'2023-07-07','\r'),(17,5,2,2,'2023-07-18','\r'),(18,4,2,4,'2023-07-24','\r'),(19,22,2,2,'2023-08-31','\r'),(20,11,2,3,'2023-09-07','\r'),(21,21,2,3,'2023-09-24','\r'),(22,11,3,4,'2023-05-24','\r'),(23,30,3,4,'2023-05-30','\r'),(24,9,3,3,'2023-07-04','\r'),(25,28,3,3,'2023-07-17','\r'),(26,3,3,5,'2023-07-25','\r'),(27,26,3,4,'2023-08-07','\r'),(28,22,3,2,'2023-09-01','\r'),(29,29,4,2,'2023-05-15','\r'),(30,9,4,2,'2023-05-16','\r'),(31,14,4,4,'2023-05-20','\r'),(32,25,4,1,'2023-05-28','\r'),(33,2,4,5,'2023-06-01','\r'),(34,11,4,1,'2023-06-03','\r'),(35,29,4,5,'2023-06-05','\r'),(36,23,4,1,'2023-06-18','\r'),(37,14,4,1,'2023-06-21','\r'),(38,26,4,2,'2023-07-27','\r'),(39,27,4,3,'2023-08-23','\r'),(40,17,4,4,'2023-08-27','\r'),(41,24,4,4,'2023-09-09','\r'),(42,16,5,4,'2023-05-05','\r'),(43,26,5,1,'2023-05-21','\r'),(44,2,5,2,'2023-05-26','\r'),(45,6,5,4,'2023-06-29','\r'),(46,20,5,5,'2023-07-03','\r'),(47,34,5,1,'2023-07-07','\r'),(48,25,5,2,'2023-07-08','\r'),(49,31,5,2,'2023-08-02','\r'),(50,33,5,5,'2023-08-17','\r'),(51,3,5,2,'2023-08-17','\r'),(52,23,5,3,'2023-08-20','\r'),(53,19,5,2,'2023-08-27','\r'),(54,18,5,5,'2023-08-29','\r'),(55,10,5,1,'2023-09-22','\r'),(56,21,5,3,'2023-09-29','\r'),(57,34,6,4,'2023-05-16','\r'),(58,33,6,5,'2023-05-30','\r'),(59,11,6,1,'2023-06-16','\r'),(60,25,6,1,'2023-07-17','\r'),(61,5,6,5,'2023-08-05','\r'),(62,21,6,3,'2023-08-26','\r'),(63,17,6,4,'2023-08-29','\r'),(64,33,6,1,'2023-09-02','\r'),(65,31,6,5,'2023-09-18','\r'),(66,23,7,5,'2023-05-04','\r'),(67,31,7,3,'2023-05-26','\r'),(68,3,7,2,'2023-06-11','\r'),(69,23,7,2,'2023-06-24','\r'),(70,15,7,3,'2023-06-24','\r'),(71,10,7,4,'2023-07-24','\r'),(72,31,7,3,'2023-08-05','\r'),(73,15,7,2,'2023-08-21','\r'),(74,18,7,4,'2023-09-05','\r'),(75,14,7,4,'2023-09-12','\r'),(76,18,7,2,'2023-09-15','\r'),(77,6,7,4,'2023-09-20','\r'),(78,7,8,5,'2023-05-13','\r'),(79,4,6,2,'2023-06-01','\r'),(80,7,8,1,'2023-06-02','\r'),(81,12,10,2,'2023-06-07','\r'),(82,27,19,4,'2023-06-22','\r'),(83,7,8,2,'2023-07-02','\r'),(84,30,4,4,'2023-07-15','\r'),(85,14,4,4,'2023-07-22','\r'),(86,7,8,5,'2023-08-10','\r'),(87,33,23,4,'2023-08-24','\r'),(88,11,23,1,'2023-08-31','\r'),(89,7,8,5,'2023-09-08','\r'),(90,4,13,1,'2023-09-09','\r'),(91,7,8,4,'2023-09-17','\r'),(92,7,8,3,'2023-09-28','\r'),(93,26,9,5,'2023-05-11','\r'),(94,12,9,4,'2023-05-11','\r'),(95,3,9,1,'2023-06-06','\r'),(96,27,9,1,'2023-06-10','\r'),(97,21,9,5,'2023-06-18','\r'),(98,18,9,4,'2023-07-18','\r'),(99,19,9,4,'2023-07-18','\r'),(100,16,9,2,'2023-08-11','\r'),(101,26,9,2,'2023-09-07','\r'),(102,3,9,2,'2023-09-07','\r'),(103,34,10,2,'2023-05-23','\r'),(104,18,10,4,'2023-06-24','\r'),(105,12,10,3,'2023-06-29','\r'),(106,6,10,1,'2023-07-04','\r'),(107,8,10,2,'2023-07-13','\r'),(108,8,10,1,'2023-07-29','\r'),(109,29,10,4,'2023-08-06','\r'),(110,20,10,4,'2023-08-11','\r'),(111,20,10,5,'2023-08-21','\r'),(112,33,10,5,'2023-08-27','\r'),(113,34,10,5,'2023-08-31','\r'),(114,2,10,2,'2023-09-01','\r'),(115,34,10,1,'2023-09-17','\r'),(116,21,11,1,'2023-05-08','\r'),(117,31,11,3,'2023-05-10','\r'),(118,3,11,3,'2023-05-22','\r'),(119,24,11,5,'2023-05-26','\r'),(120,20,11,5,'2023-06-08','\r'),(121,29,11,3,'2023-06-12','\r'),(122,21,11,2,'2023-07-20','\r'),(123,33,11,1,'2023-08-07','\r'),(124,8,11,3,'2023-08-12','\r'),(125,10,11,2,'2023-08-26','\r'),(126,34,11,3,'2023-08-27','\r'),(127,11,12,1,'2023-05-31','\r'),(128,9,12,4,'2023-06-07','\r'),(129,11,12,3,'2023-06-18','\r'),(130,17,12,1,'2023-06-25','\r'),(131,6,12,1,'2023-08-01','\r'),(132,5,12,2,'2023-08-13','\r'),(133,28,12,4,'2023-09-15','\r'),(134,21,12,1,'2023-09-17','\r'),(135,27,13,4,'2023-05-08','\r'),(136,32,13,3,'2023-05-14','\r'),(137,32,13,4,'2023-05-21','\r'),(138,5,13,1,'2023-05-22','\r'),(139,21,13,1,'2023-05-22','\r'),(140,27,13,2,'2023-05-23','\r'),(141,10,13,1,'2023-06-24','\r'),(142,9,13,5,'2023-07-06','\r'),(143,24,13,4,'2023-07-25','\r'),(144,2,13,5,'2023-08-01','\r'),(145,33,13,5,'2023-08-27','\r'),(146,26,13,3,'2023-08-30','\r'),(147,21,13,1,'2023-09-13','\r'),(148,24,14,4,'2023-05-05','\r'),(149,3,14,2,'2023-05-05','\r'),(150,9,14,2,'2023-05-11','\r'),(151,4,14,3,'2023-06-04','\r'),(152,15,14,4,'2023-07-06','\r'),(153,5,14,2,'2023-07-18','\r'),(154,30,14,2,'2023-08-06','\r'),(155,2,14,3,'2023-09-02','\r'),(156,34,14,1,'2023-09-17','\r'),(157,22,15,3,'2023-05-08','\r'),(158,7,15,1,'2023-06-06','\r'),(159,24,15,3,'2023-06-08','\r'),(160,8,15,3,'2023-06-11','\r'),(161,6,15,4,'2023-06-17','\r'),(162,5,15,2,'2023-06-18','\r'),(163,8,15,5,'2023-07-02','\r'),(164,15,15,5,'2023-07-21','\r'),(165,3,15,4,'2023-09-09','\r'),(166,13,15,3,'2023-09-15','\r'),(167,3,15,4,'2023-09-17','\r'),(168,30,15,2,'2023-09-20','\r'),(169,8,16,4,'2023-05-19','\r'),(170,16,16,2,'2023-06-03','\r'),(171,16,16,1,'2023-06-09','\r'),(172,4,16,5,'2023-06-23','\r'),(173,2,16,1,'2023-07-09','\r'),(174,1,16,2,'2023-07-31','\r'),(175,20,16,4,'2023-08-16','\r'),(176,8,16,4,'2023-08-29','\r'),(177,6,16,2,'2023-09-05','\r'),(178,6,16,3,'2023-09-28','\r'),(179,16,17,5,'2023-05-05','\r'),(180,33,17,2,'2023-05-13','\r'),(181,4,17,3,'2023-06-04','\r'),(182,17,17,1,'2023-07-03','\r'),(183,8,17,4,'2023-07-08','\r'),(184,18,17,4,'2023-07-28','\r'),(185,7,17,5,'2023-08-25','\r'),(186,7,17,3,'2023-08-31','\r'),(187,30,17,1,'2023-09-06','\r'),(188,10,17,2,'2023-09-07','\r'),(189,19,18,1,'2023-05-18','\r'),(190,10,18,1,'2023-06-10','\r'),(191,6,18,1,'2023-06-14','\r'),(192,30,18,1,'2023-06-14','\r'),(193,23,18,5,'2023-06-21','\r'),(194,15,18,3,'2023-07-03','\r'),(195,23,18,5,'2023-07-13','\r'),(196,24,18,1,'2023-07-23','\r'),(197,13,18,4,'2023-07-27','\r'),(198,20,18,4,'2023-08-04','\r'),(199,17,18,1,'2023-08-19','\r'),(200,6,18,5,'2023-08-24','\r'),(201,6,18,4,'2023-09-26','\r'),(202,9,18,3,'2023-09-27','\r'),(203,31,19,2,'2023-05-17','\r'),(204,2,19,3,'2023-05-27','\r'),(205,23,19,1,'2023-05-27','\r'),(206,21,19,5,'2023-06-05','\r'),(207,21,19,1,'2023-06-07','\r'),(208,1,19,5,'2023-06-13','\r'),(209,7,19,3,'2023-06-26','\r'),(210,31,19,5,'2023-06-27','\r'),(211,15,19,2,'2023-07-12','\r'),(212,9,19,4,'2023-08-21','\r'),(213,14,19,1,'2023-09-13','\r'),(214,22,19,4,'2023-09-23','\r'),(215,32,19,2,'2023-09-28','\r'),(216,6,20,3,'2023-05-12','\r'),(217,7,20,3,'2023-05-17','\r'),(218,5,20,5,'2023-06-10','\r'),(219,14,20,5,'2023-07-04','\r'),(220,17,20,4,'2023-07-13','\r'),(221,20,20,2,'2023-08-10','\r'),(222,19,20,1,'2023-08-17','\r'),(223,14,20,1,'2023-09-15','\r'),(224,12,20,3,'2023-09-21','\r'),(225,1,20,3,'2023-09-30','\r'),(226,20,21,2,'2023-05-06','\r'),(227,10,21,1,'2023-06-17','\r'),(228,25,21,1,'2023-06-20','\r'),(229,17,21,4,'2023-07-10','\r'),(230,20,21,4,'2023-07-20','\r'),(231,22,21,2,'2023-07-23','\r'),(232,23,21,3,'2023-07-23','\r'),(233,25,21,5,'2023-08-02','\r'),(234,34,21,3,'2023-08-23','\r'),(235,4,21,1,'2023-08-31','\r'),(236,28,21,2,'2023-09-09','\r'),(237,9,21,3,'2023-09-17','\r'),(238,4,22,5,'2023-05-07','\r'),(239,33,22,2,'2023-05-25','\r'),(240,7,22,1,'2023-06-07','\r'),(241,20,22,1,'2023-06-19','\r'),(242,19,22,5,'2023-07-12','\r'),(243,31,22,1,'2023-07-12','\r'),(244,14,22,5,'2023-07-23','\r'),(245,7,22,2,'2023-08-12','\r'),(246,4,22,2,'2023-08-23','\r'),(247,30,22,3,'2023-08-28','\r'),(248,4,22,1,'2023-08-31','\r'),(249,28,22,1,'2023-09-09','\r'),(250,4,22,4,'2023-09-14','\r'),(251,18,22,5,'2023-09-21','\r'),(252,27,23,4,'2023-06-17','\r'),(253,4,23,2,'2023-07-04','\r'),(254,12,23,1,'2023-07-24','\r'),(255,2,23,5,'2023-08-07','\r'),(256,27,23,4,'2023-09-03','\r'),(257,28,23,2,'2023-09-08','\r'),(258,7,24,1,'2023-05-05','\r'),(259,30,24,1,'2023-05-12','\r'),(260,16,24,4,'2023-05-15','\r'),(261,23,24,3,'2023-05-15','\r'),(262,11,24,4,'2023-06-23','\r'),(263,1,24,4,'2023-07-10','\r'),(264,27,24,1,'2023-07-25','\r'),(265,20,24,1,'2023-08-07','\r'),(266,2,24,4,'2023-08-16','\r'),(267,3,24,3,'2023-09-02','\r'),(268,4,25,2,'2023-05-13','\r'),(269,22,25,4,'2023-06-03','\r'),(270,31,25,4,'2023-06-20','\r'),(271,4,25,3,'2023-06-21','\r'),(272,8,25,2,'2023-06-26','\r'),(273,10,25,3,'2023-07-20','\r'),(274,13,25,5,'2023-07-27','\r'),(275,12,25,2,'2023-07-29','\r'),(276,18,26,4,'2023-05-14','\r'),(277,8,26,1,'2023-05-21','\r'),(278,25,26,1,'2023-06-12','\r'),(279,28,26,1,'2023-07-03','\r'),(280,31,26,2,'2023-07-08','\r'),(281,10,26,2,'2023-08-03','\r'),(282,1,26,5,'2023-09-23','\r'),(283,17,27,4,'2023-05-09','\r'),(284,3,27,5,'2023-06-09','\r'),(285,26,27,1,'2023-07-02','\r'),(286,25,27,2,'2023-07-04','\r'),(287,22,27,2,'2023-07-06','\r'),(288,33,27,5,'2023-07-10','\r'),(289,6,27,2,'2023-07-19','\r'),(290,5,27,5,'2023-07-27','\r'),(291,29,27,5,'2023-08-08','\r'),(292,18,27,1,'2023-08-24','\r'),(293,18,27,4,'2023-09-03','\r'),(294,19,27,5,'2023-09-03','\r'),(295,9,27,3,'2023-09-05','\r'),(296,32,27,5,'2023-09-08','\r'),(297,34,27,3,'2023-09-13','\r'),(298,31,27,1,'2023-09-22','\r'),(299,5,27,1,'2023-09-23','\r'),(300,27,28,2,'2023-05-12','\r'),(301,27,28,2,'2023-05-30','\r'),(302,27,28,5,'2023-06-04','\r'),(303,27,28,4,'2023-06-20','\r'),(304,27,28,3,'2023-07-07','\r'),(305,27,28,4,'2023-08-25','\r'),(306,27,28,5,'2023-08-27','\r'),(307,27,28,1,'2023-09-10','\r'),(308,27,28,4,'2023-09-23','\r'),(309,14,29,4,'2023-05-11','\r'),(310,14,29,3,'2023-05-25','\r'),(311,14,29,4,'2023-06-07','\r'),(312,14,29,2,'2023-06-28','\r'),(313,14,29,2,'2023-07-16','\r'),(314,14,29,1,'2023-08-23','\r'),(315,14,29,3,'2023-08-27','\r'),(316,14,29,5,'2023-09-12','\r'),(317,14,29,3,'2023-09-23','\r');
/*!40000 ALTER TABLE `ma_ap_zuord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mitarbeiter`
--

DROP TABLE IF EXISTS `mitarbeiter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mitarbeiter` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `nachname` varchar(45) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `vorname` varchar(45) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `geschlecht` varchar(1) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `gebdat` date NOT NULL,
  `strasse` varchar(60) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `plz` varchar(6) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `ort` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `bundesland` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mitarbeiter`
--

LOCK TABLES `mitarbeiter` WRITE;
/*!40000 ALTER TABLE `mitarbeiter` DISABLE KEYS */;
INSERT INTO `mitarbeiter` VALUES (1,'Preslon','Alexander','M','1989-03-11','Werner-Berg-Gasse 20/H2','8811','Scheifling','Steiermark'),(2,'Gräfin','Armin','M','1992-10-15','Waldrastweg 2','8820','Neumarkt in Steiermark','Steiermark'),(3,'Dürer','Arnold','M','1987-12-31','Untermarkt 17','8822','Mühlen','Steiermark'),(4,'Spasshofer','Thomas','M','1992-07-09','St. Peter 9','9020','Klagenfurt - Innere Stadt','Kärnten'),(5,'Ernstmüller','Sebastian','M','1987-08-12','St. Lorenzenerstraße 7/1','9020','Klagenfurt - Sankt Peter','Kärnten'),(6,'Müller','Benjamin','M','1992-10-29','Siebenbürgengasse 15','9020','Klagenfurt - Sankt Peter','Kärnten'),(7,'Pennetick','Christina','W','1992-07-31','Roseneggerstraße 9','9020','Klagenfurt - Innere Stadt','Kärnten'),(8,'Muran','Carmen','W','1989-04-30','Paracelsusstraße 19','9020','Klagenfurt - Innere Stadt','Kärnten'),(9,'Lastig','Jimmy','M','1992-10-24','Oberhof 43','9020','Klagenfurt - Innere Stadt','Kärnten'),(10,'Kramwetter','Christian','M','1992-06-03','Oberallach 10','9062','Moosburg','Kärnten'),(11,'Hechel','Christian','M','1992-06-23','Münzweg 3','9062','Moosburg','Kärnten'),(12,'Niederwallner','Christian','M','1992-08-13','Mörtschenstraße 38','9064','Pischeldorf','Kärnten'),(13,'Samtitsch','Christoph','M','1992-06-16','Meiselhofstraße 12','9072','Ludmannsdorf','Kärnten'),(14,'Josonke','Max ','M','1992-11-17','Maria Elend 208','9073','Klagenfurt - Viktring','Kärnten'),(15,'Melker','Christopher','M','1992-05-12','Lukowitz 23','9100','Völkermarkt','Kärnten'),(16,'Ofenfrouw','Daniel','M','1989-02-13','Lodengasse 18','9125','Kühnsdorf','Kärnten'),(17,'Weinöler','Martina','W','1992-06-12','Lassendorf 115','9131','Wölfnitz','Kärnten'),(18,'Mori','Monika','W','1992-11-09','Kulm 76','9141','Eberndorf','Kärnten'),(19,'Alukim','Erich','M','1989-06-08','Koschatstraße 4','9141','Eberndorf','Kärnten'),(20,'Polzen','Gabriel','M','1989-03-07','Koralmblickweg 20','9182','Maria Elend','Kärnten'),(21,'Mostflecki','Gerald','M','1992-11-26','Kl. St. Veit 13','9232','Rosegg','Kärnten'),(22,'Ollag','Manfred','M','1987-07-04','Kinderdorfstraße 9/1','9330','Althofen','Kärnten'),(23,'Viktori','Stefan','M','1992-08-12','Jakobsberg 74','9363','Metnitz','Kärnten'),(24,'Paulertl','Martin','M','1989-07-17','Höfling Schwarze Str. 26','9363','Metnitz','Kärnten'),(25,'Plischi','Martin','M','1992-09-15','Heinzgasse 12','9371','Brückl','Kärnten'),(26,'Flaschnig','Angelika','W','1992-12-31','Gablern 54','9431','Sankt Stefan','Kärnten'),(27,'Rothschild','Cleo','W','1989-02-13','Gablern 38','9473','Lavamünd','Kärnten'),(28,'Guckenbergen','Peter','M','1987-11-20','Friedrich-Marx-Straße 9','9560','Kallitsch','Kärnten'),(29,'Seifert','Patrick','M','1992-11-04','Flatschacherstraße 152','9560','Feldkirchen in Kärnten','Kärnten'),(30,'Finanzer','Paul','M','1992-09-14','Berg ob Leifling 40','9754','Steinfeld','Kärnten'),(31,'Rezibas','Raphael','M','1986-09-08','August-Jaksch-Straße 13','9800','Spittal an der Drau','Kärnten'),(32,'Wennschon','Stefan ','M','1992-11-04','Andreas Asenbauerstraße 9','9800','Spittal an der Drau','Kärnten'),(33,'Wiestadt','Wolfgang','M','1992-09-26','Am Steinkogel 9','9852','Trebesing','Kärnten'),(34,'Poltsch','Udo','M','1992-11-05','A. Tschabuschniggstraße 35/2/6','9863','Rennweg','Kärnten'),(36,'Einig','Ferdinand','m','2009-05-05','Marillenweg 65','1065','Wien','Wien');
/*!40000 ALTER TABLE `mitarbeiter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projekte`
--

DROP TABLE IF EXISTS `projekte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projekte` (
  `nr` int unsigned NOT NULL AUTO_INCREMENT,
  `titel` varchar(200) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `code` varchar(10) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`nr`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projekte`
--

LOCK TABLES `projekte` WRITE;
/*!40000 ALTER TABLE `projekte` DISABLE KEYS */;
INSERT INTO `projekte` VALUES (1,'Design und Entwicklung einer Web-Stundenplanverwaltung','WSTP'),(2,'HomeWork Robot - Roboter zur Hausübungsautomatisierung','ROBO'),(3,'WLAN Griller - Mikrowellen Accesspoints','WGR'),(5,'Projekt ohne Arbeit','POA'),(6,'Access TINF DB','ATDB');
/*!40000 ALTER TABLE `projekte` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-21 13:08:01
