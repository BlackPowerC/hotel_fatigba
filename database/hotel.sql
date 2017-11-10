-- MySQL dump 10.13  Distrib 5.7.16, for Linux (x86_64)
--
-- Host: localhost    Database: hotel1
-- ------------------------------------------------------
-- Server version	5.7.16-0ubuntu0.16.04.1

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
  `num_res` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_chambre`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Chambre`
--

LOCK TABLES `Chambre` WRITE;
/*!40000 ALTER TABLE `Chambre` DISABLE KEYS */;
INSERT INTO `Chambre` VALUES (1,'single','vue sur autoroute',90.5,0,NULL),(2,'single','vue sur mer',155.2,0,NULL),(3,'double','vue sur piscine',100.5,1,NULL),(4,'double','vue sur mer',155.2,0,NULL),(5,'single','vue sur piscine',100.5,0,NULL),(6,'single','interieur',75.9,0,NULL),(7,'single','interieur',75.9,0,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
INSERT INTO `Client` VALUES (1,'fatigba','jordy','V.I.P',1,1,'','',0,0),(2,'hodouto','horatio','V.I.P',1,1,'','',0,0),(3,'kodjo','dalila','Privée',1,0,'','',0,0),(4,'novieku','prudencio','Top',0,0,'','',0,0),(13,'idrissou','kahar','Privé',0,0,'Homme','Afrique du sud',0,1),(14,'gbadoe','urich','Groupe',0,0,'Homme','Algérie',0,1),(15,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(16,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(17,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(18,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(19,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(20,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(21,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(22,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(23,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(24,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(25,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(26,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(27,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(28,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(29,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(30,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(31,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(32,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(33,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(34,'e','d','Privé',0,0,'Femme','Afghanistan',0,3),(35,'e','d','Privé',0,0,'Femme','Afghanistan',0,3);
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
  `id_res` int(10) unsigned NOT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`id_fa`),
  KEY `id_cl` (`id_cl`),
  KEY `id_res` (`id_res`),
  CONSTRAINT `Facturation_ibfk_1` FOREIGN KEY (`id_cl`) REFERENCES `Client` (`id_client`),
  CONSTRAINT `Facturation_ibfk_2` FOREIGN KEY (`id_res`) REFERENCES `Reservation` (`id_res`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Facturation`
--

LOCK TABLES `Facturation` WRITE;
/*!40000 ALTER TABLE `Facturation` DISABLE KEYS */;
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
  `debut_res` datetime NOT NULL,
  `fin_res` datetime NOT NULL,
  `date_res` datetime NOT NULL,
  `state` varchar(50) NOT NULL,
  PRIMARY KEY (`id_res`),
  KEY `id_cl` (`id_cl`),
  KEY `id_ch` (`id_ch`),
  CONSTRAINT `Reservation_ibfk_1` FOREIGN KEY (`id_cl`) REFERENCES `Client` (`id_client`),
  CONSTRAINT `Reservation_ibfk_2` FOREIGN KEY (`id_ch`) REFERENCES `Chambre` (`id_chambre`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
INSERT INTO `Reservation` VALUES (1,2,3,'2016-10-04 07:00:00','2016-10-27 15:00:00','2016-09-28 14:24:38','1');
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
INSERT INTO `Service` VALUES (1,'Service de bar',15.75),(2,'Service de restauration',50.6),(3,'Service internet',15.75),(4,'Service de blanchisserie',60.75);
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
  PRIMARY KEY (`id_us`),
  KEY `fk_id_cl` (`id_cl`),
  KEY `fk_id_ser` (`id_ser`),
  CONSTRAINT `fk_id_cl` FOREIGN KEY (`id_cl`) REFERENCES `Client` (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UseService`
--

LOCK TABLES `UseService` WRITE;
/*!40000 ALTER TABLE `UseService` DISABLE KEYS */;
/*!40000 ALTER TABLE `UseService` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-14 10:01:01
