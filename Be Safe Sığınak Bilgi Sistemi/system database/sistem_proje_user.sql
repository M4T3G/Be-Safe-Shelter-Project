-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: sistem_proje
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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_name` varchar(45) NOT NULL,
  `user_age` int NOT NULL,
  `user_tc` double NOT NULL,
  `user_password` varchar(8) NOT NULL,
  `user_point` int DEFAULT NULL,
  `user_occupation` varchar(45) NOT NULL,
  `user_gender` int NOT NULL,
  `shelter_id` int NOT NULL,
  `user_healthy_condition` int DEFAULT NULL,
  `user_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('John',30,758213946,'R!c2P@9q',60,'Engineer',1,1,1,2),('Michael',35,429876351,'Ls7&nD%4',78,'Doctor',1,1,2,4),('Sophia',28,165437982,'G8*shPp@',74,'Artist',0,2,3,5),('William',40,893257641,'T!d3Xu$6',58,'Unemployed',1,3,4,6),('Olivia',22,572691834,'H@n2L5!p',64,'Student',0,4,1,7),('James',33,314785629,'F*r1V@8t',66,'Engineer',1,5,2,8),('Benjamin',38,697384215,'W%g5sZ@9',76,'Teacher',1,6,3,9),('Mia',27,238574169,'B@j9t#pK',96,'Nurse',0,7,4,10),('Alexander',31,915246783,'Y%h6P@r8',60,'Architect',1,8,1,11),('Charlotte',24,486159327,'D#f8N!p2',70,'Student',0,9,2,12),('Daniel',36,721946835,'S3m7P@u!',84,'Doctor',1,10,3,13),('Harper',26,537819264,'E!c4O&m9',76,'Unemployed',0,1,4,14),('Ekrem',23,862714539,'N@5sT!p7',90,'Engineer',1,0,4,37);
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

-- Dump completed on 2024-05-10 21:17:36
