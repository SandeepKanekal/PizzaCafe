CREATE DATABASE  IF NOT EXISTS `pizzacafe` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pizzacafe`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: pizzacafe
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `pizzacafe`.`bill_header`
--

DROP TABLE IF EXISTS `pizzacafe`.`bill_header`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizzacafe`.`bill_header` (
  `Bill_id` int NOT NULL AUTO_INCREMENT,
  `Bill_Date` datetime DEFAULT NULL,
  `Amount` decimal(10,2) DEFAULT NULL,
  `Tax` decimal(10,2) DEFAULT NULL,
  `Total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`Bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizzacafe`.`bill_header`
--

LOCK TABLES `pizzacafe`.`bill_header` WRITE;
/*!40000 ALTER TABLE `pizzacafe`.`bill_header` DISABLE KEYS */;
/*!40000 ALTER TABLE `pizzacafe`.`bill_header` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizzacafe`.`bill_item`
--

DROP TABLE IF EXISTS `pizzacafe`.`bill_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizzacafe`.`bill_item` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Bill_ID` int DEFAULT NULL,
  `Menu_ID` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Rate` decimal(5,2) DEFAULT NULL,
  `Total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_menu_id_idx` (`Menu_ID`),
  KEY `fk_bill_id_idx` (`Bill_ID`),
  CONSTRAINT `fk_bill_id` FOREIGN KEY (`Bill_ID`) REFERENCES `pizzacafe`.`bill_header` (`Bill_id`),
  CONSTRAINT `fk_menu_id` FOREIGN KEY (`Menu_ID`) REFERENCES `pizzacafe`.`menu` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizzacafe`.`bill_item`
--

LOCK TABLES `pizzacafe`.`bill_item` WRITE;
/*!40000 ALTER TABLE `pizzacafe`.`bill_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `pizzacafe`.`bill_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizzacafe`.`consumption`
--

DROP TABLE IF EXISTS `pizzacafe`.`consumption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizzacafe`.`consumption` (
  `ID` int NOT NULL,
  `QTY_Consumed` decimal(5,3) DEFAULT NULL,
  `Unit` varchar(45) DEFAULT NULL,
  `Menu_ID` int DEFAULT NULL,
  `Inventory_ID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Menu_ID_idx` (`Menu_ID`),
  KEY `Inventory_ID_idx` (`Inventory_ID`),
  CONSTRAINT `Inventory_ID` FOREIGN KEY (`Inventory_ID`) REFERENCES `pizzacafe`.`inventory` (`ID`),
  CONSTRAINT `Menu_ID` FOREIGN KEY (`Menu_ID`) REFERENCES `pizzacafe`.`menu` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizzacafe`.`consumption`
--

LOCK TABLES `pizzacafe`.`consumption` WRITE;
/*!40000 ALTER TABLE `pizzacafe`.`consumption` DISABLE KEYS */;
INSERT INTO `pizzacafe`.`consumption` VALUES (1,0.200,'Kilograms',1,1),(2,0.100,'Kilograms',1,2),(3,0.100,'Kilograms',1,3),(4,0.200,'Kilograms',2,1),(5,0.100,'Kilograms',2,2),(6,0.100,'Kilograms',2,3),(7,0.025,'Kilograms',2,4),(8,0.200,'Kilograms',3,1),(9,0.100,'Kilograms',3,2),(10,0.100,'Kilograms',3,3),(11,0.025,'Kilograms',3,4),(12,0.025,'Kilograms',3,5);
/*!40000 ALTER TABLE `pizzacafe`.`consumption` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizzacafe`.`inventory`
--

DROP TABLE IF EXISTS `pizzacafe`.`inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizzacafe`.`inventory` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(60) DEFAULT NULL,
  `Unit` varchar(45) DEFAULT NULL,
  `Quantity` decimal(5,2) DEFAULT NULL,
  `Cost_per_Unit` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizzacafe`.`inventory`
--

LOCK TABLES `pizzacafe`.`inventory` WRITE;
/*!40000 ALTER TABLE `pizzacafe`.`inventory` DISABLE KEYS */;
INSERT INTO `pizzacafe`.`inventory` VALUES (1,'Pizza Dough','Kilograms',100.00,30.00),(2,'Cheese','Kilograms',50.00,100.00),(3,'Sauce','Kilograms',50.00,60.00),(4,'Jalapeno','Kilograms',25.00,400.00),(5,'Vegetables','Kilograms',50.00,400.00);
/*!40000 ALTER TABLE `pizzacafe`.`inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizzacafe`.`menu`
--

DROP TABLE IF EXISTS `pizzacafe`.`menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizzacafe`.`menu` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Item` varchar(45) DEFAULT NULL,
  `Currency` varchar(45) DEFAULT NULL,
  `Price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizzacafe`.`menu`
--

LOCK TABLES `pizzacafe`.`menu` WRITE;
/*!40000 ALTER TABLE `pizzacafe`.`menu` DISABLE KEYS */;
INSERT INTO `pizzacafe`.`menu` VALUES (1,'Margherita','Rs.',100.00),(2,'Spicy Pizza','Rs.',150.00),(3,'Veggie Pizza','Rs.',200.00);
/*!40000 ALTER TABLE `pizzacafe`.`menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizzacafe`.`overheads`
--

DROP TABLE IF EXISTS `pizzacafe`.`overheads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizzacafe`.`overheads` (
  `ID` int NOT NULL,
  `Description` varchar(45) DEFAULT NULL,
  `Expense_day` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizzacafe`.`overheads`
--

LOCK TABLES `pizzacafe`.`overheads` WRITE;
/*!40000 ALTER TABLE `pizzacafe`.`overheads` DISABLE KEYS */;
INSERT INTO `pizzacafe`.`overheads` VALUES (1,'Rent',300.00),(2,'Salaries',300.00),(3,'Utilities',100.00);
/*!40000 ALTER TABLE `pizzacafe`.`overheads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizzacafe`.`user_credentials`
--

DROP TABLE IF EXISTS `pizzacafe`.`user_credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizzacafe`.`user_credentials` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizzacafe`.`user_credentials`
--

LOCK TABLES `pizzacafe`.`user_credentials` WRITE;
/*!40000 ALTER TABLE `pizzacafe`.`user_credentials` DISABLE KEYS */;
INSERT INTO `pizzacafe`.`user_credentials` VALUES (1,'Admin','dd65f426bd4c210276e2e7bf90368cf5f73dec78');
/*!40000 ALTER TABLE `pizzacafe`.`user_credentials` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-24  7:18:12
