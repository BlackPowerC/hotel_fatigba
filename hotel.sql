-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: hotel1
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `Chambre`
--

DROP TABLE IF EXISTS `Chambre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Chambre` (
  `id_chambre` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type_chambre` varchar(50) NOT NULL,
  `situation` varchar(50) NOT NULL,
  `prix` float NOT NULL,
  `state` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_chambre`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Chambre`
--

LOCK TABLES `Chambre` WRITE;
/*!40000 ALTER TABLE `Chambre` DISABLE KEYS */;
INSERT INTO `Chambre` VALUES (1,'Buisiness Class','Piscine',127,0),(2,'Single','Mer',155.2,1),(3,'Double','Piscine',100,0),(4,'Junior','Interieur',155.2,0),(5,'Single','Piscine',100.5,0),(6,'Executive','Interieur',300,0),(7,'Single','Interieur',75.9,0),(8,'Standard','Interieur',75.9,0),(9,'Business Class','Picsine',126.95,0),(10,'Junior','Picsine',100.95,0),(11,'Executive','Mer',230.95,0),(12,'Présidentielle','Interieur',345.95,0),(13,'Standard','Interieur',75.9,0),(14,'Business Class','Picsine',126.95,0),(15,'Junior','Picsine',100.95,0),(16,'Executive','Mer',230.95,0),(17,'Présidentielle','Picsine',370.95,0),(18,'Présidentielle','Terasse',360.95,0),(19,'Single','Autoroute',90.5,0),(20,'Single','Mer',155.2,0),(21,'Double','Piscine',100.5,0),(22,'Double','Mer',155.2,0),(23,'Buisiness Class','Piscine',100.5,0),(24,'Single','Interieur',75.9,0),(25,'Single','Interieur',75.9,0),(26,'Standard','Interieur',75.9,0),(27,'Business Class','Interieur',126.95,0),(28,'Executive','Terasse',250.95,1),(29,'Présidentielle','Interieur',345.95,0),(30,'Business Class','Picsine',126.95,0);
/*!40000 ALTER TABLE `Chambre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Client`
--

DROP TABLE IF EXISTS `Client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Client` (
  `id_client` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `type_client` varchar(20) NOT NULL,
  `local` tinyint(1) NOT NULL,
  `card` tinyint(1) NOT NULL,
  `sexe` varchar(20) NOT NULL,
  `nationalite` varchar(20) NOT NULL,
  `age` int(10) unsigned NOT NULL,
  `membre` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_client`,`nom`,`prenom`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
INSERT INTO `Client` VALUES (1,'Kodjo','Dalida','Privé',0,1,'Femme','Togo',19,0),(3,'Kodjo','Dalila','Affaire',0,1,'Femme','Allemagne',19,1),(4,'Houndonougbo','Jéronime','Affaire',0,0,'Femme','Bénin',19,0),(5,'Fatigba','Jordy','Privé',0,0,'Homme','Bénin',18,1),(6,'Aziagba','Sabine','Affaire',0,1,'Femme','Brunie',19,1),(7,'Coeur','Lida','Affaire',0,1,'Femme','Togo',18,0),(8,'Kéké','kaka','Groupe',0,0,'Homme','Bélize',24,3),(9,'Jolie','Belle','Privé',0,1,'Femme','France',22,1);
/*!40000 ALTER TABLE `Client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Facturation`
--

DROP TABLE IF EXISTS `Facturation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Facturation` (
  `id_fa` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_cl` int(10) unsigned NOT NULL,
  `total` float NOT NULL,
  `totalRem` float DEFAULT NULL,
  `totalSer` float NOT NULL,
  `totalRes` float NOT NULL,
  `mode` varchar(40) NOT NULL,
  PRIMARY KEY (`id_fa`),
  KEY `id_cl` (`id_cl`),
  CONSTRAINT `Facturation_ibfk_1` FOREIGN KEY (`id_cl`) REFERENCES `Client` (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Facturation`
--

LOCK TABLES `Facturation` WRITE;
/*!40000 ALTER TABLE `Facturation` DISABLE KEYS */;
INSERT INTO `Facturation` VALUES (6,8,0,124.696,141.7,0,'Chèque'),(7,8,141.7,89.5475,105.35,0,'Virement');
/*!40000 ALTER TABLE `Facturation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservation`
--

DROP TABLE IF EXISTS `Reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reservation` (
  `id_res` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_cl` int(10) unsigned NOT NULL,
  `id_ch` int(10) unsigned NOT NULL,
  `debut_res` varchar(20) NOT NULL,
  `fin_res` varchar(20) NOT NULL,
  `date_res` varchar(20) NOT NULL,
  `state` varchar(50) NOT NULL,
  `solved` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_res`),
  KEY `id_cl` (`id_cl`),
  KEY `id_ch` (`id_ch`),
  CONSTRAINT `Reservation_ibfk_1` FOREIGN KEY (`id_cl`) REFERENCES `Client` (`id_client`),
  CONSTRAINT `Reservation_ibfk_2` FOREIGN KEY (`id_ch`) REFERENCES `Chambre` (`id_chambre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
INSERT INTO `Reservation` VALUES (1,1,28,'2017-8-22','2017-9-28','2017-7-18','0',1),(2,4,2,'2017-6-11','2017-6-12','2017-8-26','0',1);
/*!40000 ALTER TABLE `Reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Service`
--

DROP TABLE IF EXISTS `Service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Service` (
  `id_ser` int(10) unsigned NOT NULL,
  `description` varchar(100) NOT NULL,
  `prix` float NOT NULL,
  PRIMARY KEY (`id_ser`,`description`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Service`
--

LOCK TABLES `Service` WRITE;
/*!40000 ALTER TABLE `Service` DISABLE KEYS */;
INSERT INTO `Service` VALUES (1,'Service de bar',15.75),(2,'Service de restauration',50.6),(3,'Service internet',15.75),(4,'Service de blanchisserie',60.75),(5,'Service musculation',35.7),(6,'Service de soin et beauté',70.85),(7,'Service de gaming',105.35);
/*!40000 ALTER TABLE `Service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UseService`
--

DROP TABLE IF EXISTS `UseService`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UseService` (
  `id_us` int(11) NOT NULL AUTO_INCREMENT,
  `id_cl` int(10) unsigned NOT NULL,
  `id_ser` int(10) unsigned NOT NULL,
  `state` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_us`),
  KEY `fk_id_cl` (`id_cl`),
  KEY `id_ser` (`id_ser`),
  CONSTRAINT `UseService_ibfk_1` FOREIGN KEY (`id_ser`) REFERENCES `Service` (`id_ser`),
  CONSTRAINT `fk_id_cl` FOREIGN KEY (`id_cl`) REFERENCES `Client` (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UseService`
--

LOCK TABLES `UseService` WRITE;
/*!40000 ALTER TABLE `UseService` DISABLE KEYS */;
INSERT INTO `UseService` VALUES (1,3,2,0),(2,7,7,0),(3,3,6,0),(4,4,3,1),(5,8,6,1),(6,6,3,0),(7,7,1,0),(8,3,2,0),(9,1,3,0),(10,8,6,1),(11,1,2,0),(12,8,7,1);
/*!40000 ALTER TABLE `UseService` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id_user` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'jordy','fatigba','jordy');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-28 12:51:06
