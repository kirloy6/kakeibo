-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: kakeibo
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dailyrecords`
--

DROP TABLE IF EXISTS `dailyrecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dailyrecords` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `price` int NOT NULL,
  `record_date` date NOT NULL,
  `store` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2bosprafqba0br8vh5405ud8r` (`user_id`),
  CONSTRAINT `FK2bosprafqba0br8vh5405ud8r` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailyrecords`
--

LOCK TABLES `dailyrecords` WRITE;
/*!40000 ALTER TABLE `dailyrecords` DISABLE KEYS */;
INSERT INTO `dailyrecords` VALUES (2,'2022-06-07 20:52:48.759119',2,'2022-05-01','セブンイレブン 05-01 /30','2022-06-15 21:09:40.447410',2),(3,'2022-06-07 20:52:48.776085',20,'2022-04-01','セブンイレブン','2022-06-07 20:52:48.776085',2),(4,'2022-06-07 20:54:58.129065',3,'2022-05-01','セブンイレブン 05-01 /8','2022-06-15 21:14:17.953557',2),(5,'2022-06-07 20:54:58.142059',7,'2022-04-01','セブンイレブン','2022-06-07 21:07:08.778111',2),(6,'2022-06-07 21:07:08.791103',4,'2022-05-01','セブンイレブン','2022-06-07 21:07:08.791103',2),(7,'2022-06-07 21:07:08.802575',1,'2022-04-01','セブンイレブン','2022-06-07 21:07:08.802575',2),(9,'2022-06-07 21:08:38.371918',3,'2022-05-01','ローソン 05-01 /30 05-01 /20','2022-06-15 21:16:43.851496',2),(10,'2022-06-07 21:08:38.380911',20,'2022-04-01','ローソン','2022-06-07 21:08:38.380911',2),(11,'2022-06-07 21:18:16.776300',2,'2022-05-01','セブンイレブン','2022-06-07 21:18:16.776300',2),(12,'2022-06-07 21:18:16.786899',10,'2022-04-01','セブンイレブン','2022-06-09 23:09:10.169260',2),(14,'2022-06-09 22:50:30.672203',1,'2022-05-01','セブンイレブンaaa','2022-06-09 22:50:30.672203',2),(15,'2022-06-09 22:50:30.712223',1,'2022-04-01','セブンイレブンaaa','2022-06-09 22:50:30.712223',2),(24,'2022-06-09 23:09:10.177328',5,'2022-05-01','セブンイレブン','2022-06-09 23:09:10.177328',2),(25,'2022-06-09 23:09:10.185327',10,'2022-04-01','セブンイレブン','2022-06-09 23:09:10.185327',2),(27,'2022-06-09 23:12:41.380221',20,'2022-05-01','セブンイレブン 2022-06-09','2022-06-09 23:12:41.380221',2),(28,'2022-06-09 23:12:41.388240',10,'2022-04-01','セブンイレブン 2022-06-09','2022-06-09 23:12:41.388240',2),(38,'2022-06-11 02:24:09.122543',20000,'2022-05-01','ウェルシア 06-11 /300000','2022-06-11 02:24:09.122543',2),(39,'2022-06-11 02:24:09.138522',279000,'2022-04-01','ウェルシア 06-11 /300000','2022-06-11 02:24:09.138522',2),(40,'2022-06-11 02:31:22.555883',1,'2022-06-11','ファミリーマート 06-11 /1500','2022-06-15 19:31:38.773322',2),(44,'2022-06-14 20:49:14.631014',1,'2022-06-14','セブンイレブン','2022-06-14 20:49:14.631014',2),(45,'2022-06-14 20:51:09.399611',1,'2022-06-14','セブンイレブン','2022-06-14 20:51:09.399611',2),(46,'2022-06-14 20:57:45.777041',1,'2022-06-14','ローソン','2022-06-14 20:57:45.777041',2),(47,'2022-06-14 21:01:27.475454',2,'2022-06-08','セブンイレブン 06-08 /10','2022-06-15 20:56:31.544073',2),(48,'2022-06-14 21:04:20.811174',2,'2022-06-14','ローソン 06-14 /100','2022-06-15 21:04:30.798488',2),(49,'2022-06-15 19:31:38.893333',14,'2022-05-01','ファミリーマート 06-11 /1500','2022-06-15 19:31:38.893333',2),(50,'2022-06-15 19:31:38.933307',1485,'2022-04-01','ファミリーマート 06-11 /1500','2022-06-15 19:31:38.933307',2),(51,'2022-06-15 21:09:40.455484',3,'2022-09-01','セブンイレブン 05-01 /30','2022-06-15 21:09:40.455484',2),(52,'2022-06-15 21:09:40.519404',25,'2022-02-01','セブンイレブン 05-01 /30','2022-06-15 21:09:40.519404',2),(53,'2022-06-15 23:00:01.104133',2,'2022-06-15','セブンイレブン 06-15 /3000','2022-06-15 23:00:13.670262',2),(54,'2022-06-15 23:00:13.710334',3,'2022-01-01','セブンイレブン 06-15 /3000','2022-06-15 23:00:13.710334',2),(55,'2022-06-15 23:00:13.718261',2995,'2022-01-01','セブンイレブン 06-15 /3000','2022-06-15 23:00:13.718261',2),(56,'2022-06-15 23:02:23.830832',1400,'2022-06-15','ローソン 06-15 /3000','2022-06-15 23:02:35.187971',2),(57,'2022-06-15 23:02:35.227971',1000,'2022-01-01','ローソン 06-15 /3000','2022-06-15 23:02:35.227971',2),(58,'2022-06-15 23:02:35.243971',600,'2022-01-01','ローソン 06-15 /3000','2022-06-15 23:02:35.243971',2),(59,'2022-06-22 21:18:06.721441',300,'2022-06-28','ファミリーマート 06-28 /1000','2022-06-26 10:20:13.976283',2),(60,'2022-06-22 21:36:51.067192',2,'2022-05-30','セブンイレブン 05-30 /1000','2022-06-26 10:19:37.970493',2),(61,'2022-06-22 21:37:13.480549',2000,'2022-01-22','ローソン','2022-06-22 21:37:13.480549',2),(62,'2022-06-22 21:37:40.747670',10000,'2022-01-31','ローソン','2022-06-22 21:37:40.747670',2),(63,'2022-06-26 10:21:16.413579',3000,'2022-06-26','トモズ 06-26 /5000','2022-06-26 10:21:37.854899',2),(64,'2022-06-26 10:26:21.390554',1000,'2022-06-26','相鉄ローゼン','2022-06-26 10:26:21.390554',2);
/*!40000 ALTER TABLE `dailyrecords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demandrecords`
--

DROP TABLE IF EXISTS `demandrecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `demandrecords` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `price` int NOT NULL,
  `record_date` date NOT NULL,
  `store` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnflmxbkb5990gc3qc1ynbmsmu` (`user_id`),
  CONSTRAINT `FKnflmxbkb5990gc3qc1ynbmsmu` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demandrecords`
--

LOCK TABLES `demandrecords` WRITE;
/*!40000 ALTER TABLE `demandrecords` DISABLE KEYS */;
INSERT INTO `demandrecords` VALUES (1,'2022-06-09 14:19:13.992402',10000,'2022-06-09','a','2022-06-22 22:16:02.694880',2);
/*!40000 ALTER TABLE `demandrecords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixedtitles`
--

DROP TABLE IF EXISTS `fixedtitles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fixedtitles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fixedtitle` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixedtitles`
--

LOCK TABLES `fixedtitles` WRITE;
/*!40000 ALTER TABLE `fixedtitles` DISABLE KEYS */;
INSERT INTO `fixedtitles` VALUES (1,'家賃'),(2,'電気'),(3,'ガス'),(4,'水道'),(5,'プロバイダー'),(6,'NHK'),(7,'サブスク'),(8,'更新費');
/*!40000 ALTER TABLE `fixedtitles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `records`
--

DROP TABLE IF EXISTS `records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `records` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `price` int NOT NULL,
  `record_date` date NOT NULL,
  `title` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6p95uajgka0j0dc9vlbjw1sf1` (`user_id`),
  CONSTRAINT `FK6p95uajgka0j0dc9vlbjw1sf1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `records`
--

LOCK TABLES `records` WRITE;
/*!40000 ALTER TABLE `records` DISABLE KEYS */;
INSERT INTO `records` VALUES (1,'2022-05-31 00:13:56.181235',1000,'2022-05-31','家賃','2022-05-31 00:13:56.181235',2),(2,'2022-05-31 00:20:08.334248',70000,'2022-04-20','家賃','2022-05-31 00:20:08.334248',2),(3,'2022-05-31 18:34:02.539230',40000,'2022-05-31','家賃','2022-05-31 18:34:02.539230',2),(4,'2022-05-31 18:35:35.170840',30000,'2022-05-31','家賃','2022-05-31 18:35:35.170840',2),(5,'2022-05-31 19:24:41.179645',20000,'2022-05-30','NHK','2022-05-31 19:24:41.179645',2),(6,'2022-06-01 00:16:38.072597',1000,'2022-06-01','家賃','2022-06-01 00:16:38.072597',2),(8,'2022-06-13 20:55:35.090110',3000,'2022-06-13','電気','2022-06-13 20:55:35.090110',2),(11,'2022-06-22 21:38:29.493519',3000,'2022-03-29','水道','2022-06-22 21:38:29.493519',2);
/*!40000 ALTER TABLE `records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `store` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (1,'セブンイレブン'),(2,'ローソン'),(3,'ファミリーマート'),(4,'オーケー'),(5,'相鉄ローゼン'),(6,'マルエツ'),(7,'ウェルシア'),(8,'トモズ'),(9,'イオン'),(10,'アマゾン'),(11,'楽天'),(12,'ニトリ');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'8830191','増田　修平','234eue432'),(2,'8830192','増田　修平','91D1515A83A1CDAD869F50D216AE1C5FBEBD18567FE8DC7A14F1B4D15A955A5D'),(3,'guest','guest','43DE692FB043C8873A23912D0D60EF8D08F59BC0D37C57FBA527A2F2C51F0B7A');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-27 21:36:56
