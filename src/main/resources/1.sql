-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: toodle
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answers` (
  `id` int(11) NOT NULL,
  `answ` varchar(200) DEFAULT NULL,
  `task` int(11) DEFAULT NULL,
  `rght` int(11) DEFAULT NULL,
  `answ_img` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `answers_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (-1872323098,'Только автобусу.',118962802,0,NULL),(-1792902382,'Не менее 50 км/ч и не более 70 км/ч.',785081539,0,NULL),(-1666100447,'увеличение  периода колебаний.',-298318294,1,NULL),(-1659823898,'меняется скорость, но не меняется ускорение.',-1436105518,0,NULL),(-1652269916,'уменьшение резонансной частоты.',-298318294,1,NULL),(-1524127528,'увеличение w0  и уменьшение T.',439505362,0,NULL),(-1189976116,'Не более 50 км/ч.',785081539,0,NULL),(-1074896289,'Никому.',118962802,1,NULL),(-1058331274,'не изменяется.',-1400358451,1,NULL),(-1048513338,'Маркони Г.',1485875554,0,NULL),(-768524491,'уменьшение периода колебаний.',-298318294,0,NULL),(-758447923,'увеличение резонансной частоты  и уменьшение периода колебаний.',-298318294,0,NULL),(-649815425,'меняются скорость и ускорение.',-1436105518,1,NULL),(-329069951,'Только Б.',1247731897,0,NULL),(-300392552,'Да.',1324993034,1,NULL),(-236941249,'сначала увеличивается, затем уменьшается.',1708280596,1,NULL),(-58704915,'увеличение w0 и увеличение T.',439505362,0,NULL),(-47428704,'увеличение T.',439505362,1,NULL),(99591000,'Поповым А.С.',1485875554,1,NULL),(197298196,'увеличение w0.',439505362,0,NULL),(283632746,'В любых.',1247731897,0,NULL),(314672744,'увеличение резонансной частоты.',-298318294,0,NULL),(375852642,'Не менее 50 км/ч и не более 90 км/ч.',785081539,1,NULL),(519954026,'увеличивается.',-1400358451,0,NULL),(580447001,'уменьшение T.',439505362,0,NULL),(842799628,'не меняется скорость, но меняется ускорение.',-1436105518,0,NULL),(1065526238,'уменьшение резонансной частоты и увеличение периода колебаний.',-298318294,1,NULL),(1134780991,'уменьшение  и w0 и T.',439505362,0,NULL),(1192147713,'Только А или Б.',1247731897,1,NULL),(1210420361,'Эдисоном Т.А.',1485875554,0,NULL),(1252595772,'увеличение резонансной частоты и увеличение периода колебаний.',-298318294,0,NULL),(1333371628,'Только легковому автомобилю.',118962802,0,NULL),(1562082735,'уменьшение и резонансной частоты и периода колебаний.',-298318294,0,NULL),(1635721425,'уменьшается.',-1400358451,0,NULL),(1716130429,'Информирует Вас о том, что дорога поворачивает направо.',-11295911,0,NULL),(1745311163,'не меняются ни скорость, ни ускорение.',-1436105518,0,NULL),(1782839177,'Нет.',1324993034,0,NULL),(1784373843,'увеличивается.',1708280596,0,NULL),(1841809728,'уменьшение w0 и увеличение T.',439505362,1,NULL),(1851716770,'Предоставляет Вам преимущество при перестроении на правую полосу.',-11295911,0,NULL),(2008775196,'уменьшение w0.',439505362,1,NULL),(2099329284,'уменьшается.',1708280596,0,NULL),(2109856427,'Предупреждает Вас о приближении к сужению проезжей части.',-11295911,1,NULL);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lessons`
--

DROP TABLE IF EXISTS `lessons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lessons` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `lessons_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lessons`
--

LOCK TABLES `lessons` WRITE;
/*!40000 ALTER TABLE `lessons` DISABLE KEYS */;
INSERT INTO `lessons` VALUES (-1833100831,NULL),(137188977,'ПДД'),(1238634134,'Физика');
/*!40000 ALTER TABLE `lessons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(10) NOT NULL,
  `text` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `messages_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (2,'07/04/2016','First message'),(3,'07/04/2016','First text'),(5,'07/04/2016','do tittle'),(6,'21/04/2016','fadgh'),(7,'21/04/2016','f,ukdhjh,lv  l,. ghij.l, '),(8,'26/04/2016','agfsdgfd'),(9,'26/04/2016','afdgdf'),(10,'26/04/2016','afsdg'),(11,'28/04/2016','eswdfrsdf'),(12,'28/04/2016','asdfsadf'),(13,'28/04/2016','swfdef');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `id` int(11) NOT NULL,
  `question` varchar(1000) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `lesson` int(11) DEFAULT NULL,
  `task_image` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tasks_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (-1436105518,'ПРИ   НЕРАВНОМЕРНОМ   ДВИЖЕНИИ   ТЕЛА',2,1238634134,NULL),(-1400358451,'ПРИ УМЕНЬШЕНИИ  РАССТОЯНИЯ МЕЖДУ ЗАРЯДАМИ\n И  ИХ   ВЕЛИЧИНЫ В 2 РАЗА, КУЛОНОВСКАЯ СИЛА ВЗАИМОДЕЙСТВИЯ',2,1238634134,NULL),(-1146186956,NULL,0,-1833100831,NULL),(-298318294,'УВЕЛИЧЕНИЕ ЕМКОСТИ КОНДЕНСАТОРА КОЛЕБАТЕЛЬНОГО КОНТУРА ВЫЗЫВАЕТ',3,1238634134,NULL),(-11295911,'Эта разметка, нанесенная на полосе движения:',2,137188977,'ed94bf825aefba6f39fc1930a0f29382eb3f8e4d805f55f3ab0c7ef17631e18f.jpeg'),(118962802,'Вы намерены повернуть налево. Кому следует уступить дорогу?',2,137188977,'1a8fb12df239f4a27ccb5de1bf0a5f5c9645d9c1c87e5d001a36c5b55292d620.jpeg'),(439505362,'УВЕЛИЧЕНИЕ ЕМКОСТИ КОНДЕНСАТОРА КОЛЕБАТЕЛЬНОГО КОНТУРА ВЫЗЫВАЕТ',3,1238634134,NULL),(785081539,'С какой скоростью Вы можете продолжить движение вне населенного пункта по левой полосе на легковом автомобиле?',2,137188977,'48ba440428e71d7c9135acee7ee9a9e4d5e4f012bb820d08f26e79b1f642174f.jpeg'),(1247731897,'В каких направлениях Вам разрешено продолжить движение?',2,137188977,'05bb6607f6daacdf2b09d1315ee0f8d5288711df17fe3f8f46dc05b08ea0903a.jpeg'),(1324993034,'Может ли водитель легкового автомобиля в населенном пункте выполнить опережение грузовых автомобилей по такой траектории?',2,137188977,'8e5b2bf35e750f1c8df68de8b0dfcfe6fb2f5cc3c28d73037e2d600757f621bb.jpeg'),(1485875554,'ПРИНЦИП  РАДИОСВЯЗИ  ВПЕРВЫЕ ПРОДЕМОНСТРИРОВАН',2,1238634134,NULL),(1708280596,'ВЫСОТА ПОЛЕТА ТЕЛА, БРОШЕННОГО ПОД УГЛОМ К ГОРИЗОНТУ',2,1238634134,NULL);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `salt` int(11) DEFAULT NULL,
  `firstname` varchar(20) DEFAULT NULL,
  `lastname` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `mail` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`),
  UNIQUE KEY `user_login_uindex` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (19,'BoBeni','095c87c7e381dfb2efdfaf78d7fcaa',2104007634,'sgfh','sgh',12,'m','shsgfdh@mail.ru');
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

-- Dump completed on 2016-05-14 21:13:07
