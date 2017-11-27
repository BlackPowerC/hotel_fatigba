-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: hotel_new
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `CaracteristiqueChambre`
--

DROP TABLE IF EXISTS `CaracteristiqueChambre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CaracteristiqueChambre` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CaracteristiqueChambre`
--

LOCK TABLES `CaracteristiqueChambre` WRITE;
/*!40000 ALTER TABLE `CaracteristiqueChambre` DISABLE KEYS */;
INSERT INTO `CaracteristiqueChambre` VALUES (1,'ventilée'),(2,'climatisée');
/*!40000 ALTER TABLE `CaracteristiqueChambre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Chambre`
--

DROP TABLE IF EXISTS `Chambre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Chambre` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_type` int(10) unsigned NOT NULL,
  `id_situation` int(10) unsigned NOT NULL,
  `id_caracteristique` int(10) unsigned NOT NULL,
  `prix` float NOT NULL,
  `etat` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Chambre_3_idx` (`id_caracteristique`),
  KEY `fk_Chambre_2_idx` (`id_type`),
  KEY `fk_Chambre_1_idx` (`id_situation`),
  CONSTRAINT `fk_Chambre_1` FOREIGN KEY (`id_situation`) REFERENCES `SituationChambre` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Chambre_2` FOREIGN KEY (`id_type`) REFERENCES `TypeChambre` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Chambre_3` FOREIGN KEY (`id_caracteristique`) REFERENCES `CaracteristiqueChambre` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Chambre`
--

LOCK TABLES `Chambre` WRITE;
/*!40000 ALTER TABLE `Chambre` DISABLE KEYS */;
INSERT INTO `Chambre` VALUES (1,2,1,2,100.95,0),(2,2,3,2,95.5,0),(3,3,2,2,100.95,0),(4,1,3,1,80.95,0);
/*!40000 ALTER TABLE `Chambre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Client`
--

DROP TABLE IF EXISTS `Client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Client` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `age` date NOT NULL,
  `fidele` tinyint(1) NOT NULL,
  `etranger` tinyint(1) NOT NULL,
  `id_nation` int(10) unsigned NOT NULL,
  `id_type` int(10) unsigned NOT NULL,
  `id_sexe` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Client_2_idx` (`id_nation`),
  KEY `fk_Client_1_idx` (`id_type`),
  KEY `fk_Client_3_idx` (`id_sexe`),
  CONSTRAINT `fk_Client_1` FOREIGN KEY (`id_type`) REFERENCES `TypeClient` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Client_2` FOREIGN KEY (`id_nation`) REFERENCES `Nation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Client_3` FOREIGN KEY (`id_sexe`) REFERENCES `Sexe` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
/*!40000 ALTER TABLE `Client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Consommation`
--

DROP TABLE IF EXISTS `Consommation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Consommation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_client` int(10) unsigned NOT NULL,
  `id_service` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Consommation_2_idx` (`id_service`),
  KEY `fk_Consommation_1_idx` (`id_client`),
  CONSTRAINT `fk_Consommation_1` FOREIGN KEY (`id_client`) REFERENCES `Client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Consommation_2` FOREIGN KEY (`id_service`) REFERENCES `Service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Consommation`
--

LOCK TABLES `Consommation` WRITE;
/*!40000 ALTER TABLE `Consommation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Consommation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Facturation`
--

DROP TABLE IF EXISTS `Facturation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Facturation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_client` int(10) unsigned NOT NULL,
  `id_consommation` int(10) unsigned NOT NULL,
  `id_reservation` int(10) unsigned NOT NULL,
  `date` datetime NOT NULL,
  `id_paiement` int(10) unsigned NOT NULL,
  `total` float NOT NULL,
  `etat` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Facturation_1_idx` (`id_client`),
  KEY `fk_Facturation_2_idx` (`id_consommation`),
  KEY `fk_Facturation_3_idx` (`id_reservation`),
  KEY `fk_Facturation_4_idx` (`id_paiement`),
  CONSTRAINT `fk_Facturation_1` FOREIGN KEY (`id_client`) REFERENCES `Client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Facturation_2` FOREIGN KEY (`id_consommation`) REFERENCES `Consommation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Facturation_3` FOREIGN KEY (`id_reservation`) REFERENCES `Reservation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Facturation_4` FOREIGN KEY (`id_paiement`) REFERENCES `ModePaiement` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Facturation`
--

LOCK TABLES `Facturation` WRITE;
/*!40000 ALTER TABLE `Facturation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Facturation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ModePaiement`
--

DROP TABLE IF EXISTS `ModePaiement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ModePaiement` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ModePaiement`
--

LOCK TABLES `ModePaiement` WRITE;
/*!40000 ALTER TABLE `ModePaiement` DISABLE KEYS */;
/*!40000 ALTER TABLE `ModePaiement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Nation`
--

DROP TABLE IF EXISTS `Nation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Nation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Nation`
--

LOCK TABLES `Nation` WRITE;
/*!40000 ALTER TABLE `Nation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Nation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ReglementFacture`
--

DROP TABLE IF EXISTS `ReglementFacture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ReglementFacture` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_facture` int(10) unsigned NOT NULL,
  `remise` tinyint(3) unsigned NOT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ReglementFacture_1_idx` (`id_facture`),
  CONSTRAINT `fk_ReglementFacture_1` FOREIGN KEY (`id_facture`) REFERENCES `Facturation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ReglementFacture`
--

LOCK TABLES `ReglementFacture` WRITE;
/*!40000 ALTER TABLE `ReglementFacture` DISABLE KEYS */;
/*!40000 ALTER TABLE `ReglementFacture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservation`
--

DROP TABLE IF EXISTS `Reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reservation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_client` int(10) unsigned NOT NULL,
  `id_chambre` int(10) unsigned NOT NULL,
  `date_reservation` datetime NOT NULL,
  `date_debut` datetime NOT NULL,
  `date_arrivee` datetime NOT NULL,
  `etat` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`,`id_client`,`id_chambre`),
  KEY `fk_Reservation_1_idx` (`id_client`),
  KEY `fk_Reservation_2_idx` (`id_chambre`),
  CONSTRAINT `fk_Reservation_1` FOREIGN KEY (`id_client`) REFERENCES `Client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservation_2` FOREIGN KEY (`id_chambre`) REFERENCES `Chambre` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Service`
--

DROP TABLE IF EXISTS `Service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Service` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(128) NOT NULL,
  `prix` float DEFAULT NULL,
  `etat` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Service`
--

LOCK TABLES `Service` WRITE;
/*!40000 ALTER TABLE `Service` DISABLE KEYS */;
/*!40000 ALTER TABLE `Service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sexe`
--

DROP TABLE IF EXISTS `Sexe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sexe` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sexe`
--

LOCK TABLES `Sexe` WRITE;
/*!40000 ALTER TABLE `Sexe` DISABLE KEYS */;
/*!40000 ALTER TABLE `Sexe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SituationChambre`
--

DROP TABLE IF EXISTS `SituationChambre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SituationChambre` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SituationChambre`
--

LOCK TABLES `SituationChambre` WRITE;
/*!40000 ALTER TABLE `SituationChambre` DISABLE KEYS */;
INSERT INTO `SituationChambre` VALUES (1,'intérieur'),(2,'jardin'),(3,'balcon');
/*!40000 ALTER TABLE `SituationChambre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TypeChambre`
--

DROP TABLE IF EXISTS `TypeChambre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TypeChambre` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TypeChambre`
--

LOCK TABLES `TypeChambre` WRITE;
/*!40000 ALTER TABLE `TypeChambre` DISABLE KEYS */;
INSERT INTO `TypeChambre` VALUES (1,'single'),(2,'double'),(3,'triple');
/*!40000 ALTER TABLE `TypeChambre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TypeClient`
--

DROP TABLE IF EXISTS `TypeClient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TypeClient` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TypeClient`
--

LOCK TABLES `TypeClient` WRITE;
/*!40000 ALTER TABLE `TypeClient` DISABLE KEYS */;
/*!40000 ALTER TABLE `TypeClient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TypeUtilisateur`
--

DROP TABLE IF EXISTS `TypeUtilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TypeUtilisateur` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TypeUtilisateur`
--

LOCK TABLES `TypeUtilisateur` WRITE;
/*!40000 ALTER TABLE `TypeUtilisateur` DISABLE KEYS */;
/*!40000 ALTER TABLE `TypeUtilisateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Utilisateur`
--

DROP TABLE IF EXISTS `Utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Utilisateur` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_type` int(10) unsigned NOT NULL,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Utilisateur_1_idx` (`id_type`),
  CONSTRAINT `fk_Utilisateur_1` FOREIGN KEY (`id_type`) REFERENCES `TypeUtilisateur` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Utilisateur`
--

LOCK TABLES `Utilisateur` WRITE;
/*!40000 ALTER TABLE `Utilisateur` DISABLE KEYS */;
/*!40000 ALTER TABLE `Utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-16 21:21:07
