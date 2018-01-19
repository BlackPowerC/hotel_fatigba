-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: 172.17.0.2    Database: hotel_new
-- ------------------------------------------------------
-- Server version	5.7.12

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
INSERT INTO `Chambre` VALUES (1,2,1,2,100.95,0),(2,1,2,1,200.8,1),(3,3,2,2,100.95,0),(4,1,3,1,80.95,0);
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
  `email` varchar(127) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
INSERT INTO `Client` VALUES (1,'jordy','fatigba','1998-04-22',1,1,'fatigba72@gmail.com',12,1,1),(2,'kodjo','dalida','1998-02-17',1,1,'exemple@gmail.com',214,2,2),(4,'nadege','affo','1985-01-23',1,1,'nadege@gmail.com',89,2,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ModePaiement`
--

LOCK TABLES `ModePaiement` WRITE;
/*!40000 ALTER TABLE `ModePaiement` DISABLE KEYS */;
INSERT INTO `ModePaiement` VALUES (1,'espèce'),(2,'virement'),(3,'carte de crédit'),(4,'e-banque'),(5,'autre');
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
  `code` int(3) NOT NULL,
  `alpha2` varchar(2) NOT NULL,
  `alpha3` varchar(3) NOT NULL,
  `nom_en_gb` varchar(45) NOT NULL,
  `nom_fr_fr` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Nation`
--

LOCK TABLES `Nation` WRITE;
/*!40000 ALTER TABLE `Nation` DISABLE KEYS */;
INSERT INTO `Nation` VALUES (1,4,'AF','AFG','Afghanistan','Afghanistan'),(2,8,'AL','ALB','Albania','Albanie'),(3,10,'AQ','ATA','Antarctica','Antarctique'),(4,12,'DZ','DZA','Algeria','Algérie'),(5,16,'AS','ASM','American Samoa','Samoa Américain'),(6,20,'AD','AND','Andorra','Andorre'),(7,24,'AO','AGO','Angola','Angola'),(8,28,'AG','ATG','Antigua and Barbuda','Antigua-et-Barb'),(9,31,'AZ','AZE','Azerbaijan','Azerbaïdjan'),(10,32,'AR','ARG','Argentina','Argentine'),(11,36,'AU','AUS','Australia','Australie'),(12,40,'AT','AUT','Austria','Autriche'),(13,44,'BS','BHS','Bahamas','Bahamas'),(14,48,'BH','BHR','Bahrain','Bahreïn'),(15,50,'BD','BGD','Bangladesh','Bangladesh'),(16,51,'AM','ARM','Armenia','Arménie'),(17,52,'BB','BRB','Barbados','Barbade'),(18,56,'BE','BEL','Belgium','Belgique'),(19,60,'BM','BMU','Bermuda','Bermudes'),(20,64,'BT','BTN','Bhutan','Bhoutan'),(21,68,'BO','BOL','Bolivia','Bolivie'),(22,70,'BA','BIH','Bosnia and Herzegovina','Bosnie-Herzégov'),(23,72,'BW','BWA','Botswana','Botswana'),(24,74,'BV','BVT','Bouvet Island','Île Bouvet'),(25,76,'BR','BRA','Brazil','Brésil'),(26,84,'BZ','BLZ','Belize','Belize'),(27,86,'IO','IOT','British Indian Ocean Territory','Territoire Brit'),(28,90,'SB','SLB','Solomon Islands','Îles Salomon'),(29,92,'VG','VGB','British Virgin Islands','Îles Vierges Br'),(30,96,'BN','BRN','Brunei Darussalam','Brunéi Darussal'),(31,100,'BG','BGR','Bulgaria','Bulgarie'),(32,104,'MM','MMR','Myanmar','Myanmar'),(33,108,'BI','BDI','Burundi','Burundi'),(34,112,'BY','BLR','Belarus','Bélarus'),(35,116,'KH','KHM','Cambodia','Cambodge'),(36,120,'CM','CMR','Cameroon','Cameroun'),(37,124,'CA','CAN','Canada','Canada'),(38,132,'CV','CPV','Cape Verde','Cap-vert'),(39,136,'KY','CYM','Cayman Islands','Îles Caïmanes'),(40,140,'CF','CAF','Central African','République Cent'),(41,144,'LK','LKA','Sri Lanka','Sri Lanka'),(42,148,'TD','TCD','Chad','Tchad'),(43,152,'CL','CHL','Chile','Chili'),(44,156,'CN','CHN','China','Chine'),(45,158,'TW','TWN','Taiwan','Taïwan'),(46,162,'CX','CXR','Christmas Island','Île Christmas'),(47,166,'CC','CCK','Cocos (Keeling) Islands','Îles Cocos (Kee'),(48,170,'CO','COL','Colombia','Colombie'),(49,174,'KM','COM','Comoros','Comores'),(50,175,'YT','MYT','Mayotte','Mayotte'),(51,178,'CG','COG','Republic of the Congo','République du C'),(52,180,'CD','COD','The Democratic Republic Of The Congo','République Démo'),(53,184,'CK','COK','Cook Islands','Îles Cook'),(54,188,'CR','CRI','Costa Rica','Costa Rica'),(55,191,'HR','HRV','Croatia','Croatie'),(56,192,'CU','CUB','Cuba','Cuba'),(57,196,'CY','CYP','Cyprus','Chypre'),(58,203,'CZ','CZE','Czech Republic','République Tchè'),(59,204,'BJ','BEN','Benin','Bénin'),(60,208,'DK','DNK','Denmark','Danemark'),(61,212,'DM','DMA','Dominica','Dominique'),(62,214,'DO','DOM','Dominican Republic','République Domi'),(63,218,'EC','ECU','Ecuador','Équateur'),(64,222,'SV','SLV','El Salvador','El Salvador'),(65,226,'GQ','GNQ','Equatorial Guinea','Guinée Équatori'),(66,231,'ET','ETH','Ethiopia','Éthiopie'),(67,232,'ER','ERI','Eritrea','Érythrée'),(68,233,'EE','EST','Estonia','Estonie'),(69,234,'FO','FRO','Faroe Islands','Îles Féroé'),(70,238,'FK','FLK','Falkland Islands','Îles (malvinas)'),(71,239,'GS','SGS','South Georgia and the South Sandwich Islands','Géorgie du Sud '),(72,242,'FJ','FJI','Fiji','Fidji'),(73,246,'FI','FIN','Finland','Finlande'),(74,248,'AX','ALA','Åland Islands','Îles Åland'),(75,250,'FR','FRA','France','France'),(76,254,'GF','GUF','French Guiana','Guyane Français'),(77,258,'PF','PYF','French Polynesia','Polynésie Franç'),(78,260,'TF','ATF','French Southern Territories','Terres Australe'),(79,262,'DJ','DJI','Djibouti','Djibouti'),(80,266,'GA','GAB','Gabon','Gabon'),(81,268,'GE','GEO','Georgia','Géorgie'),(82,270,'GM','GMB','Gambia','Gambie'),(83,275,'PS','PSE','Occupied Palestinian Territory','Territoire Pale'),(84,276,'DE','DEU','Germany','Allemagne'),(85,288,'GH','GHA','Ghana','Ghana'),(86,292,'GI','GIB','Gibraltar','Gibraltar'),(87,296,'KI','KIR','Kiribati','Kiribati'),(88,300,'GR','GRC','Greece','Grèce'),(89,304,'GL','GRL','Greenland','Groenland'),(90,308,'GD','GRD','Grenada','Grenade'),(91,312,'GP','GLP','Guadeloupe','Guadeloupe'),(92,316,'GU','GUM','Guam','Guam'),(93,320,'GT','GTM','Guatemala','Guatemala'),(94,324,'GN','GIN','Guinea','Guinée'),(95,328,'GY','GUY','Guyana','Guyana'),(96,332,'HT','HTI','Haiti','Haïti'),(97,334,'HM','HMD','Heard Island and McDonald Islands','Îles Heard et M'),(98,336,'VA','VAT','Vatican City State','Saint-Siège (ét'),(99,340,'HN','HND','Honduras','Honduras'),(100,344,'HK','HKG','Hong Kong','Hong-Kong'),(101,348,'HU','HUN','Hungary','Hongrie'),(102,352,'IS','ISL','Iceland','Islande'),(103,356,'IN','IND','India','Inde'),(104,360,'ID','IDN','Indonesia','Indonésie'),(105,364,'IR','IRN','Islamic Republic of Iran','République Isla'),(106,368,'IQ','IRQ','Iraq','Iraq'),(107,372,'IE','IRL','Ireland','Irlande'),(108,376,'IL','ISR','Israel','Israël'),(109,380,'IT','ITA','Italy','Italie'),(110,384,'CI','CIV','Côte d\'Ivoire','Côte d\'Ivoire'),(111,388,'JM','JAM','Jamaica','Jamaïque'),(112,392,'JP','JPN','Japan','Japon'),(113,398,'KZ','KAZ','Kazakhstan','Kazakhstan'),(114,400,'JO','JOR','Jordan','Jordanie'),(115,404,'KE','KEN','Kenya','Kenya'),(116,408,'KP','PRK','Democratic People\'s Republic of Korea','République Popu'),(117,410,'KR','KOR','Republic of Korea','République de C'),(118,414,'KW','KWT','Kuwait','Koweït'),(119,417,'KG','KGZ','Kyrgyzstan','Kirghizistan'),(120,418,'LA','LAO','Lao People\'s Democratic Republic','République Démo'),(121,422,'LB','LBN','Lebanon','Liban'),(122,426,'LS','LSO','Lesotho','Lesotho'),(123,428,'LV','LVA','Latvia','Lettonie'),(124,430,'LR','LBR','Liberia','Libéria'),(125,434,'LY','LBY','Libyan Arab Jamahiriya','Jamahiriya Arab'),(126,438,'LI','LIE','Liechtenstein','Liechtenstein'),(127,440,'LT','LTU','Lithuania','Lituanie'),(128,442,'LU','LUX','Luxembourg','Luxembourg'),(129,446,'MO','MAC','Macao','Macao'),(130,450,'MG','MDG','Madagascar','Madagascar'),(131,454,'MW','MWI','Malawi','Malawi'),(132,458,'MY','MYS','Malaysia','Malaisie'),(133,462,'MV','MDV','Maldives','Maldives'),(134,466,'ML','MLI','Mali','Mali'),(135,470,'MT','MLT','Malta','Malte'),(136,474,'MQ','MTQ','Martinique','Martinique'),(137,478,'MR','MRT','Mauritania','Mauritanie'),(138,480,'MU','MUS','Mauritius','Maurice'),(139,484,'MX','MEX','Mexico','Mexique'),(140,492,'MC','MCO','Monaco','Monaco'),(141,496,'MN','MNG','Mongolia','Mongolie'),(142,498,'MD','MDA','Republic of Moldova','République de M'),(143,500,'MS','MSR','Montserrat','Montserrat'),(144,504,'MA','MAR','Morocco','Maroc'),(145,508,'MZ','MOZ','Mozambique','Mozambique'),(146,512,'OM','OMN','Oman','Oman'),(147,516,'NA','NAM','Namibia','Namibie'),(148,520,'NR','NRU','Nauru','Nauru'),(149,524,'NP','NPL','Nepal','Népal'),(150,528,'NL','NLD','Netherlands','Pays-Bas'),(151,530,'AN','ANT','Netherlands Antilles','Antilles Néerla'),(152,533,'AW','ABW','Aruba','Aruba'),(153,540,'NC','NCL','New Caledonia','Nouvelle-Calédo'),(154,548,'VU','VUT','Vanuatu','Vanuatu'),(155,554,'NZ','NZL','New Zealand','Nouvelle-Zéland'),(156,558,'NI','NIC','Nicaragua','Nicaragua'),(157,562,'NE','NER','Niger','Niger'),(158,566,'NG','NGA','Nigeria','Nigéria'),(159,570,'NU','NIU','Niue','Niué'),(160,574,'NF','NFK','Norfolk Island','Île Norfolk'),(161,578,'NO','NOR','Norway','Norvège'),(162,580,'MP','MNP','Northern Mariana Islands','Îles Mariannes '),(163,581,'UM','UMI','United States Minor Outlying Islands','Îles Mineures É'),(164,583,'FM','FSM','Federated States of Micronesia','États Fédérés d'),(165,584,'MH','MHL','Marshall Islands','Îles Marshall'),(166,585,'PW','PLW','Palau','Palaos'),(167,586,'PK','PAK','Pakistan','Pakistan'),(168,591,'PA','PAN','Panama','Panama'),(169,598,'PG','PNG','Papua New Guinea','Papouasie-Nouve'),(170,600,'PY','PRY','Paraguay','Paraguay'),(171,604,'PE','PER','Peru','Pérou'),(172,608,'PH','PHL','Philippines','Philippines'),(173,612,'PN','PCN','Pitcairn','Pitcairn'),(174,616,'PL','POL','Poland','Pologne'),(175,620,'PT','PRT','Portugal','Portugal'),(176,624,'GW','GNB','Guinea-Bissau','Guinée-Bissau'),(177,626,'TL','TLS','Timor-Leste','Timor-Leste'),(178,630,'PR','PRI','Puerto Rico','Porto Rico'),(179,634,'QA','QAT','Qatar','Qatar'),(180,638,'RE','REU','Réunion','Réunion'),(181,642,'RO','ROU','Romania','Roumanie'),(182,643,'RU','RUS','Russian Federation','Fédération de R'),(183,646,'RW','RWA','Rwanda','Rwanda'),(184,654,'SH','SHN','Saint Helena','Sainte-Hélène'),(185,659,'KN','KNA','Saint Kitts and Nevis','Saint-Kitts-et-'),(186,660,'AI','AIA','Anguilla','Anguilla'),(187,662,'LC','LCA','Saint Lucia','Sainte-Lucie'),(188,666,'PM','SPM','Saint-Pierre and Miquelon','Saint-Pierre-et'),(189,670,'VC','VCT','Saint Vincent and the Grenadines','Saint-Vincent-e'),(190,674,'SM','SMR','San Marino','Saint-Marin'),(191,678,'ST','STP','Sao Tome and Principe','Sao Tomé-et-Pri'),(192,682,'SA','SAU','Saudi Arabia','Arabie Saoudite'),(193,686,'SN','SEN','Senegal','Sénégal'),(194,690,'SC','SYC','Seychelles','Seychelles'),(195,694,'SL','SLE','Sierra Leone','Sierra Leone'),(196,702,'SG','SGP','Singapore','Singapour'),(197,703,'SK','SVK','Slovakia','Slovaquie'),(198,704,'VN','VNM','Vietnam','Viet Nam'),(199,705,'SI','SVN','Slovenia','Slovénie'),(200,706,'SO','SOM','Somalia','Somalie'),(201,710,'ZA','ZAF','South Africa','Afrique du Sud'),(202,716,'ZW','ZWE','Zimbabwe','Zimbabwe'),(203,724,'ES','ESP','Spain','Espagne'),(204,732,'EH','ESH','Western Sahara','Sahara Occident'),(205,736,'SD','SDN','Sudan','Soudan'),(206,740,'SR','SUR','Suriname','Suriname'),(207,744,'SJ','SJM','Svalbard and Jan Mayen','Svalbard etÎle '),(208,748,'SZ','SWZ','Swaziland','Swaziland'),(209,752,'SE','SWE','Sweden','Suède'),(210,756,'CH','CHE','Switzerland','Suisse'),(211,760,'SY','SYR','Syrian Arab Republic','République Arab'),(212,762,'TJ','TJK','Tajikistan','Tadjikistan'),(213,764,'TH','THA','Thailand','Thaïlande'),(214,768,'TG','TGO','Togo','Togo'),(215,772,'TK','TKL','Tokelau','Tokelau'),(216,776,'TO','TON','Tonga','Tonga'),(217,780,'TT','TTO','Trinidad and Tobago','Trinité-et-Toba'),(218,784,'AE','ARE','United Arab Emirates','Émirats Arabes '),(219,788,'TN','TUN','Tunisia','Tunisie'),(220,792,'TR','TUR','Turkey','Turquie'),(221,795,'TM','TKM','Turkmenistan','Turkménistan'),(222,796,'TC','TCA','Turks and Caicos Islands','Îles Turks et C'),(223,798,'TV','TUV','Tuvalu','Tuvalu'),(224,800,'UG','UGA','Uganda','Ouganda'),(225,804,'UA','UKR','Ukraine','Ukraine'),(226,807,'MK','MKD','The Former Yugoslav Republic of Macedonia','L\'ex-République'),(227,818,'EG','EGY','Egypt','Égypte'),(228,826,'GB','GBR','United Kingdom','Royaume-Uni'),(229,833,'IM','IMN','Isle of Man','Île de Man'),(230,834,'TZ','TZA','United Republic Of Tanzania','République-Unie'),(231,840,'US','USA','United States','États-Unis'),(232,850,'VI','VIR','U.S. Virgin Islands','Îles Vierges de'),(233,854,'BF','BFA','Burkina Faso','Burkina Faso'),(234,858,'UY','URY','Uruguay','Uruguay'),(235,860,'UZ','UZB','Uzbekistan','Ouzbékistan'),(236,862,'VE','VEN','Venezuela','Venezuela'),(237,876,'WF','WLF','Wallis and Futuna','Wallis et Futun'),(238,882,'WS','WSM','Samoa','Samoa'),(239,887,'YE','YEM','Yemen','Yémen'),(240,891,'CS','SCG','Serbia and Montenegro','Serbie-et-Monté'),(241,894,'ZM','ZMB','Zambia','Zambie');
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
  `date_reservation` varchar(127) NOT NULL,
  `date_debut` varchar(127) NOT NULL,
  `date_fin` varchar(127) NOT NULL,
  `etat` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`,`id_client`),
  KEY `fk_Reservation_1_idx` (`id_client`),
  CONSTRAINT `fk_Reservation_1` FOREIGN KEY (`id_client`) REFERENCES `Client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
INSERT INTO `Reservation` VALUES (1,1,'2017-12-22 06:18:00','2017-12-25 00:00:00','2017-12-25 00:00:00',0),(2,1,'2017-12-20 00:00:00','2017-12-29 00:00:00','2017-12-31 00:00:00',0);
/*!40000 ALTER TABLE `Reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reserve`
--

DROP TABLE IF EXISTS `Reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reserve` (
  `id_reservation` int(10) unsigned NOT NULL,
  `id_chambre` int(10) unsigned NOT NULL,
  `id_client` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_reservation`,`id_chambre`,`id_client`),
  KEY `id_chambre` (`id_chambre`),
  KEY `id_client` (`id_client`),
  CONSTRAINT `Reserve_ibfk_1` FOREIGN KEY (`id_reservation`) REFERENCES `Reservation` (`id`),
  CONSTRAINT `Reserve_ibfk_2` FOREIGN KEY (`id_chambre`) REFERENCES `Chambre` (`id`),
  CONSTRAINT `Reserve_ibfk_3` FOREIGN KEY (`id_client`) REFERENCES `Client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reserve`
--

LOCK TABLES `Reserve` WRITE;
/*!40000 ALTER TABLE `Reserve` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reserve` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Service`
--

LOCK TABLES `Service` WRITE;
/*!40000 ALTER TABLE `Service` DISABLE KEYS */;
INSERT INTO `Service` VALUES (1,'Blanchisserie',100.5,1);
/*!40000 ALTER TABLE `Service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Session`
--

DROP TABLE IF EXISTS `Session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Session` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `debut` varchar(127) NOT NULL,
  `fin` varchar(127) NOT NULL,
  `duree` time NOT NULL,
  `id_user` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `Utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Session`
--

LOCK TABLES `Session` WRITE;
/*!40000 ALTER TABLE `Session` DISABLE KEYS */;
INSERT INTO `Session` VALUES (1,'2018-10-11 11:46:29',' ','00:00:00',1),(2,'2018-10-11 11:46:33','2018-10-11 11:47:34','06:10:00',1),(3,'2018-10-11 13:40:35',' ','00:00:00',1),(4,'2018-10-11 13:40:39',' ','00:00:00',1),(5,'2018-10-11 13:41:15',' ','00:00:00',1),(6,'2018-10-11 13:41:18',' ','00:00:00',1),(7,'2018-10-11 13:41:53',' ','00:00:00',1),(8,'2018-10-11 13:41:56',' ','00:00:00',1),(9,'2018-10-11 13:43:52',' ','00:00:00',1),(10,'2018-10-11 13:43:56',' ','00:00:00',1),(11,'2018-10-11 13:45:33',' ','00:00:00',1),(12,'2018-10-11 13:45:35',' ','00:00:00',1),(13,'2018-10-11 14:12:46',' ','00:00:00',1),(14,'2018-10-11 14:12:49',' ','00:00:00',1),(15,'2018-10-11 14:14:17',' ','00:00:00',1),(16,'2018-10-11 14:14:20',' ','00:00:00',1),(17,'2018-10-11 14:16:43',' ','00:00:00',1),(18,'2018-10-11 14:16:46',' ','00:00:00',1),(19,'2018-10-11 14:18:52',' ','00:00:00',1),(20,'2018-10-11 14:18:58',' ','00:00:00',1),(21,'2018-10-11 20:36:46',' ','00:00:00',1),(22,'2018-10-11 20:36:49','2018-10-11 20:36:59','01:00:00',1),(23,'2018-10-11 21:2:8',' ','00:00:00',1),(24,'2018-10-11 21:2:10',' ','00:00:00',1),(25,'2018-10-11 22:7:18',' ','00:00:00',1),(26,'2018-10-11 22:7:21','2018-10-11 22:7:42','02:10:00',1),(27,'2018-10-11 22:8:50',' ','00:00:00',1),(28,'2018-10-11 22:8:55',' ','00:00:00',1),(29,'2018-11-12 13:41:11',' ','00:00:00',1),(30,'2018-11-12 13:43:14',' ','00:00:00',1),(31,'2018-11-12 17:51:30',' ','00:00:00',1),(32,'2018-11-12 17:51:34',' ','00:00:00',1),(33,'2018-11-12 17:57:22',' ','00:00:00',1),(34,'2018-11-12 17:57:24','2018-11-12 17:58:45','08:10:00',1),(35,'2018-11-13 20:41:59',' ','00:00:00',1),(36,'2018-11-13 20:42:2',' ','00:00:00',1),(37,'2018-11-14 16:57:12',' ','00:00:00',1),(38,'2018-11-14 16:57:15','2018-11-14 16:57:26','01:10:00',1),(39,'2018-11-14 17:4:5',' ','00:00:00',1),(40,'2018-11-14 17:4:8',' ','00:00:00',1),(41,'2018-11-14 17:6:0',' ','00:00:00',1),(42,'2018-11-14 17:6:2',' ','00:00:00',1),(43,'2018-11-14 17:20:8',' ','00:00:00',1),(44,'2018-11-14 17:20:15',' ','00:00:00',1),(45,'2018-11-14 17:25:9',' ','00:00:00',1),(46,'2018-11-14 17:25:12',' ','00:00:00',1),(47,'2018-11-14 17:28:56',' ','00:00:00',1),(48,'2018-11-14 17:28:59',' ','00:00:00',1),(49,'2018-11-14 17:30:34',' ','00:00:00',1),(50,'2018-11-14 17:30:37','2018-11-14 17:30:49','01:20:00',1),(51,'2018-11-14 17:31:14',' ','00:00:00',1),(52,'2018-11-14 17:31:17',' ','00:00:00',1),(53,'2018-11-14 17:31:47',' ','00:00:00',1),(54,'2018-11-14 17:31:51',' ','00:00:00',1),(55,'2018-11-14 17:32:36',' ','00:00:00',1),(56,'2018-11-14 17:32:38','2018-11-14 17:33:2','02:40:00',1),(57,'2018-11-14 18:10:19',' ','00:00:00',1),(58,'2018-11-14 18:10:27','2018-11-14 18:12:47','14:00:00',1),(59,'2018-11-14 18:14:24',' ','00:00:00',1),(60,'2018-11-14 18:14:42',' ','00:00:00',1),(61,'2018-11-14 18:17:17',' ','00:00:00',1),(62,'2018-11-14 18:17:23',' ','00:00:00',1),(63,'2018-11-14 18:19:9',' ','00:00:00',1),(64,'2018-11-14 18:19:12',' ','00:00:00',1),(65,'2018-11-14 18:21:33',' ','00:00:00',1),(66,'2018-11-14 18:21:36',' ','00:00:00',1),(67,'2018-11-14 18:23:35',' ','00:00:00',1),(68,'2018-11-14 18:23:38',' ','00:00:00',1),(69,'2018-11-14 18:28:10',' ','00:00:00',1),(70,'2018-11-14 18:28:13','2018-11-14 18:28:38','02:50:00',1),(71,'2018-15-15 15:30:54',' ','00:00:00',1),(72,'2018-15-15 15:30:56',' ','00:00:00',1),(73,'2018-15-15 15:33:35',' ','00:00:00',1),(74,'2018-15-15 15:33:37',' ','00:00:00',1),(75,'2018-15-15 15:38:47',' ','00:00:00',1),(76,'2018-15-15 15:38:49',' ','00:00:00',1),(77,'2018-15-15 15:41:28',' ','00:00:00',1),(78,'2018-15-15 15:41:34',' ','00:00:00',1),(79,'2018-15-15 15:51:49',' ','00:00:00',1),(80,'2018-15-15 15:51:51',' ','00:00:00',1),(81,'2018-15-15 16:19:57',' ','00:00:00',1),(82,'2018-15-15 16:19:59',' ','00:00:00',1),(83,'2018-15-15 16:21:24',' ','00:00:00',1),(84,'2018-15-15 16:21:25',' ','00:00:00',1),(85,'2018-15-15 16:23:41',' ','00:00:00',1),(86,'2018-15-15 16:23:44',' ','00:00:00',1),(87,'2018-15-15 16:25:11',' ','00:00:00',1),(88,'2018-15-15 16:25:13','2018-15-15 16:25:35','02:20:00',1),(89,'2018-15-15 16:28:35',' ','00:00:00',1),(90,'2018-15-15 16:28:37','2018-15-15 16:30:28','11:10:00',1),(91,'2018-15-15 16:30:44',' ','00:00:00',1),(92,'2018-15-15 16:30:46',' ','00:00:00',1),(93,'2018-15-15 16:31:46',' ','00:00:00',1),(94,'2018-15-15 16:31:47',' ','00:00:00',1),(95,'2018-15-15 16:32:45',' ','00:00:00',1),(96,'2018-15-15 16:32:46','2018-15-15 16:32:58','01:20:00',1),(97,'2018-16-16 16:27:2',' ','00:00:00',1),(98,'2018-16-16 16:27:4',' ','00:00:00',1);
/*!40000 ALTER TABLE `Session` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sexe`
--

LOCK TABLES `Sexe` WRITE;
/*!40000 ALTER TABLE `Sexe` DISABLE KEYS */;
INSERT INTO `Sexe` VALUES (1,'homme'),(2,'femme');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TypeClient`
--

LOCK TABLES `TypeClient` WRITE;
/*!40000 ALTER TABLE `TypeClient` DISABLE KEYS */;
INSERT INTO `TypeClient` VALUES (1,'lambda'),(2,'v.i.p'),(3,'premium'),(4,'master');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TypeUtilisateur`
--

LOCK TABLES `TypeUtilisateur` WRITE;
/*!40000 ALTER TABLE `TypeUtilisateur` DISABLE KEYS */;
INSERT INTO `TypeUtilisateur` VALUES (1,'admin'),(2,'clientAdmin'),(3,'chambreAdmin'),(4,'reservationAdmin'),(5,'serviceAdmin'),(6,'facturationAdmin');
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
  `id_sexe` int(10) unsigned NOT NULL,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Utilisateur_1_idx` (`id_type`),
  CONSTRAINT `fk_Utilisateur_1` FOREIGN KEY (`id_type`) REFERENCES `TypeUtilisateur` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Utilisateur`
--

LOCK TABLES `Utilisateur` WRITE;
/*!40000 ALTER TABLE `Utilisateur` DISABLE KEYS */;
INSERT INTO `Utilisateur` VALUES (1,1,1,'fatigba','jordy','fatigba72@gmail.com','dalila');
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

-- Dump completed on 2018-01-16 21:35:58
