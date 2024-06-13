CREATE DATABASE  IF NOT EXISTS `bd_proyectopi_g5` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bd_proyectopi_g5`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: bd_proyectopi_g5
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
-- Table structure for table `detalleprestamo`
--

DROP TABLE IF EXISTS `detalleprestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleprestamo` (
  `iddetalle` int NOT NULL AUTO_INCREMENT,
  `fechapago` date DEFAULT NULL,
  `fechapagada` date DEFAULT NULL,
  `monto` double DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `idprestamo` int DEFAULT NULL,
  PRIMARY KEY (`iddetalle`),
  KEY `fk_detallePrestamos_prestamo_idx` (`idprestamo`),
  CONSTRAINT `fk_detallePrestamos_prestamo` FOREIGN KEY (`idprestamo`) REFERENCES `prestamo` (`idprestamo`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleprestamo`
--

LOCK TABLES `detalleprestamo` WRITE;
/*!40000 ALTER TABLE `detalleprestamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalleprestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enlace`
--

DROP TABLE IF EXISTS `enlace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enlace` (
  `idenlace` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) NOT NULL,
  `url` varchar(200) NOT NULL,
  PRIMARY KEY (`idenlace`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enlace`
--

LOCK TABLES `enlace` WRITE;
/*!40000 ALTER TABLE `enlace` DISABLE KEYS */;
/*!40000 ALTER TABLE `enlace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enlacerol`
--

DROP TABLE IF EXISTS `enlacerol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enlacerol` (
  `idenlace` int NOT NULL,
  `idrol` int NOT NULL,
  KEY `idenlace` (`idenlace`),
  KEY `idrol` (`idrol`),
  CONSTRAINT `enlacerol_ibfk_1` FOREIGN KEY (`idenlace`) REFERENCES `enlace` (`idenlace`),
  CONSTRAINT `enlacerol_ibfk_2` FOREIGN KEY (`idrol`) REFERENCES `rol` (`idrol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enlacerol`
--

LOCK TABLES `enlacerol` WRITE;
/*!40000 ALTER TABLE `enlacerol` DISABLE KEYS */;
/*!40000 ALTER TABLE `enlacerol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo` (
  `idgrupo` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `estado` varchar(45) NOT NULL DEFAULT 'disponible',
  PRIMARY KEY (`idgrupo`),
  UNIQUE KEY `descripcion_UNIQUE` (`descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (1,'lince','ocupado'),(2,'miraflores','ocupado'),(3,'independencia','ocupado'),(4,'molina','ocupado'),(5,'chorrillos','disponible'),(6,'magdalena','disponible');
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inversionista`
--

DROP TABLE IF EXISTS `inversionista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inversionista` (
  `idinversionista` int NOT NULL AUTO_INCREMENT,
  `idusuario` int NOT NULL,
  PRIMARY KEY (`idinversionista`,`idusuario`),
  KEY `fk_inversionista_usuario1_idx` (`idusuario`),
  CONSTRAINT `fk_inversionista_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inversionista`
--

LOCK TABLES `inversionista` WRITE;
/*!40000 ALTER TABLE `inversionista` DISABLE KEYS */;
INSERT INTO `inversionista` VALUES (1,1);
/*!40000 ALTER TABLE `inversionista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jefeprestamista`
--

DROP TABLE IF EXISTS `jefeprestamista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jefeprestamista` (
  `idjefeprestamista` int NOT NULL AUTO_INCREMENT,
  `idinversionista` int DEFAULT NULL,
  `idusuario` int NOT NULL,
  `idgrupo` int DEFAULT NULL,
  PRIMARY KEY (`idjefeprestamista`,`idusuario`),
  KEY `fk_jefePrestatario_inversionista1_idx` (`idinversionista`) /*!80000 INVISIBLE */,
  KEY `fk_jefePrestatario_usuario1_idx` (`idusuario`),
  KEY `fk_jefePrestamista_grupo1_idx` (`idgrupo`),
  CONSTRAINT `fk_jefePrestamista_grupo1` FOREIGN KEY (`idgrupo`) REFERENCES `grupo` (`idgrupo`),
  CONSTRAINT `fk_jefePrestatario_inversionista1` FOREIGN KEY (`idinversionista`) REFERENCES `inversionista` (`idinversionista`),
  CONSTRAINT `fk_jefePrestatario_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jefeprestamista`
--

LOCK TABLES `jefeprestamista` WRITE;
/*!40000 ALTER TABLE `jefeprestamista` DISABLE KEYS */;
INSERT INTO `jefeprestamista` VALUES (8,1,15,1),(14,1,34,2),(15,1,35,3),(17,1,38,4);
/*!40000 ALTER TABLE `jefeprestamista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localcomercial`
--

DROP TABLE IF EXISTS `localcomercial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `localcomercial` (
  `idlocalcomercial` int NOT NULL AUTO_INCREMENT,
  `direccion` varchar(45) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `grupo_idgrupo` int NOT NULL,
  PRIMARY KEY (`idlocalcomercial`,`grupo_idgrupo`),
  UNIQUE KEY `descripcion_UNIQUE` (`descripcion`),
  KEY `fk_localComercial_grupo1_idx` (`grupo_idgrupo`),
  CONSTRAINT `fk_localComercial_grupo1` FOREIGN KEY (`grupo_idgrupo`) REFERENCES `grupo` (`idgrupo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localcomercial`
--

LOCK TABLES `localcomercial` WRITE;
/*!40000 ALTER TABLE `localcomercial` DISABLE KEYS */;
INSERT INTO `localcomercial` VALUES (1,'lince','mercado de lince',1),(2,'miraflores','plaza miraflores',2),(3,'independencia','mercado central',3),(4,'tomas valle','Plaza Norte',3),(5,'tomas valle','Plaza Rex',3),(6,'lince','Arenales',1),(7,'miraflores','Larcomar',2),(8,'molina','molina center',4),(9,'molina','mercado molina',4),(10,'chorrillos','chorrillos plaza',5),(11,'magdalena','plaza mar',6),(12,'magdalena','mcentro magdalena',6);
/*!40000 ALTER TABLE `localcomercial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modalidad`
--

DROP TABLE IF EXISTS `modalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modalidad` (
  `idmodalidad` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idmodalidad`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modalidad`
--

LOCK TABLES `modalidad` WRITE;
/*!40000 ALTER TABLE `modalidad` DISABLE KEYS */;
INSERT INTO `modalidad` VALUES (1,'diario'),(2,'yape');
/*!40000 ALTER TABLE `modalidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `montos`
--

DROP TABLE IF EXISTS `montos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `montos` (
  `idmonto` int NOT NULL AUTO_INCREMENT,
  `monto` double DEFAULT NULL,
  `dias` int DEFAULT NULL,
  `monto_interes` double DEFAULT NULL,
  PRIMARY KEY (`idmonto`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `montos`
--

LOCK TABLES `montos` WRITE;
/*!40000 ALTER TABLE `montos` DISABLE KEYS */;
INSERT INTO `montos` VALUES (1,150,15,154.11),(2,150,20,155.49),(5,150,25,157.11),(6,150,30,159.49),(7,150,35,159.49),(12,200,15,205.11),(13,200,20,207.49),(14,200,25,209.49),(15,200,30,210.32),(16,200,35,212.32),(17,300,15,308.11),(18,300,20,310.49),(19,300,25,313.49),(20,300,30,316.32),(21,300,35,319.32),(22,400,15,410.11),(23,400,20,414.49),(24,400,25,418.49),(25,400,30,421.32),(26,400,35,425.32),(27,500,15,513.11),(28,500,20,518.49),(29,500,25,522.49),(30,500,30,527.32),(31,500,35,532.32);
/*!40000 ALTER TABLE `montos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamista`
--

DROP TABLE IF EXISTS `prestamista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamista` (
  `idprestamista` int NOT NULL AUTO_INCREMENT,
  `idgrupo` int DEFAULT NULL,
  `idusuario` int NOT NULL,
  `idlocalcomercial` int DEFAULT NULL,
  PRIMARY KEY (`idprestamista`,`idusuario`),
  KEY `fk_prestamista_grupo1_idx` (`idgrupo`),
  KEY `fk_prestamista_usuario1_idx` (`idusuario`),
  KEY `fk_prestamista_localComercial1_idx` (`idlocalcomercial`),
  CONSTRAINT `fk_prestamista_grupo1` FOREIGN KEY (`idgrupo`) REFERENCES `grupo` (`idgrupo`),
  CONSTRAINT `fk_prestamista_localComercial1` FOREIGN KEY (`idlocalcomercial`) REFERENCES `localcomercial` (`idlocalcomercial`),
  CONSTRAINT `fk_prestamista_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamista`
--

LOCK TABLES `prestamista` WRITE;
/*!40000 ALTER TABLE `prestamista` DISABLE KEYS */;
INSERT INTO `prestamista` VALUES (1,1,14,1),(2,1,18,6),(3,3,19,5),(6,1,26,1),(10,1,30,1),(11,1,31,1),(12,3,36,3),(14,4,41,8);
/*!40000 ALTER TABLE `prestamista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `idprestamo` int NOT NULL AUTO_INCREMENT,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `cuotas` int DEFAULT NULL,
  `pago_diario` double DEFAULT NULL,
  `estado` enum('pendiente','denegado','aceptado','activo') DEFAULT 'pendiente',
  `idprestatario` int DEFAULT NULL,
  `idmonto` int DEFAULT NULL,
  `idmodalidad` int DEFAULT NULL,
  PRIMARY KEY (`idprestamo`),
  KEY `idprestatario` (`idprestatario`),
  KEY `idmodalidad` (`idmonto`),
  KEY `prestamos_ibfk_3_idx` (`idmodalidad`),
  CONSTRAINT `prestamo_ibfk_1` FOREIGN KEY (`idprestatario`) REFERENCES `prestatario` (`idprestatario`),
  CONSTRAINT `prestamo_ibfk_2` FOREIGN KEY (`idmonto`) REFERENCES `montos` (`idmonto`),
  CONSTRAINT `prestamos_ibfk_3` FOREIGN KEY (`idmodalidad`) REFERENCES `modalidad` (`idmodalidad`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (1,'2024-05-04','2024-06-03',30,10.52,'pendiente',1,15,1),(2,'2024-05-04','2024-06-08',35,21.29,'pendiente',1,31,1),(3,'2024-05-04','2024-06-08',35,6.38,'pendiente',1,7,1),(4,'2024-05-04','2024-06-08',35,21.29,'pendiente',1,31,2),(5,'2024-05-05','2024-06-09',35,13.3,'pendiente',1,21,2),(6,'2024-05-05','2024-05-20',15,34.23,'pendiente',1,17,1),(7,'2024-05-05','2024-05-20',15,57.01,'pendiente',1,27,2),(11,'2024-05-09','2024-06-03',25,30.73,'pendiente',5,29,1);
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestatario`
--

DROP TABLE IF EXISTS `prestatario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestatario` (
  `idprestatario` int NOT NULL AUTO_INCREMENT,
  `idlocalcomercial` int NOT NULL,
  `idusuario` int NOT NULL,
  PRIMARY KEY (`idprestatario`,`idusuario`),
  KEY `fk_prestatario_localComercial1_idx` (`idlocalcomercial`),
  KEY `fk_prestatario_usuario1_idx` (`idusuario`),
  CONSTRAINT `fk_prestatario_localComercial1` FOREIGN KEY (`idlocalcomercial`) REFERENCES `localcomercial` (`idlocalcomercial`),
  CONSTRAINT `fk_prestatario_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestatario`
--

LOCK TABLES `prestatario` WRITE;
/*!40000 ALTER TABLE `prestatario` DISABLE KEYS */;
INSERT INTO `prestatario` VALUES (4,1,25),(2,2,13),(3,2,14),(1,3,12),(5,6,42);
/*!40000 ALTER TABLE `prestatario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idrol` int NOT NULL AUTO_INCREMENT,
  `rol` varchar(45) NOT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'administrador'),(2,'inversionista'),(3,'jefePrestamista'),(4,'prestamista'),(5,'prestatario'),(6,'solicitante');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud`
--

DROP TABLE IF EXISTS `solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud` (
  `idsolicitud` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `idusuario` int NOT NULL,
  `idmonto` int NOT NULL,
  PRIMARY KEY (`idsolicitud`,`idusuario`),
  KEY `fk_solicitud_usuario1_idx` (`idusuario`),
  KEY `fk_solicitud_montos1_idx` (`idmonto`),
  CONSTRAINT `fk_solicitud_monto1` FOREIGN KEY (`idmonto`) REFERENCES `montos` (`idmonto`),
  CONSTRAINT `fk_solicitud_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud`
--

LOCK TABLES `solicitud` WRITE;
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
INSERT INTO `solicitud` VALUES (6,'2024-05-09','pendiente',42,29);
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `nombres` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `dni` varchar(45) NOT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `telefono` varchar(12) NOT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `rol_idrol` int NOT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `dni_UNIQUE` (`dni`),
  UNIQUE KEY `telefono_UNIQUE` (`telefono`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`),
  KEY `fk_usuario_rol1_idx` (`rol_idrol`),
  CONSTRAINT `fk_usuario_rol1` FOREIGN KEY (`rol_idrol`) REFERENCES `rol` (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'inversionista','1','inversionista','$2a$10$RluJCJssdJcoSC7nnnhtd.BBi9ToahOb4i3d.JATEmcrsff/pC1yS','23456789','inversionista@proyecto.com','978327167','av. izaguirre s/n independencia',2),(12,'sebas','sandoval','Sebastian','$2a$10$Wes0bs8WktrA6M3yQ.ztA.Gk1/NF8oVt0mY3EikFDoFZ1LSBpS84S','45645646','admin@gmail.com','45646456','asdasdasd',5),(13,'ignacio','bernal','ignacio','$2a$10$QkkBlFDinChKyiHuRSVIp.87C0QkwsUpEQaF1x5vfwNZesbiLhLZK','555555','janter@gmail.com','888888','su casa',5),(14,'David','Brazola','barzola','$2a$10$gZpeotMg06wSFfXEcuXCXO20N7tqjAuuIe0lKZteiKy0TNMP87b0C','456987','janter@gmail.com','789456','su casa',4),(15,'jefe1','jefe1','jprestamista','$2a$10$uTauPdKmnd4xZE97ipi9feSZWYNUYqraFhmuPAuLChuEKiDXrddlG','2222222222','jefe@gmail.com','856974123','jefe@gmail.com',3),(18,'prestamista a','a','prestamista','$2a$10$Ny6SwegfEUzX1hRgYYmYCuU/hZA4B.RPasYh2Ocn5EG0WcMijXsca','456213','pres@gmail.com','456546','pres casa',4),(19,'prestamista b','b','prestamistb','$2a$10$3EbrlLiDM6QIHjVMGt/IeOqLVbAf8EVgpaUeLJGUGcpG2Oscc5EGu','12356486','pres@gmail.com','123456789','pres casa',4),(23,'prestamista c','c','prestamistc','$2a$10$KG2297K8wti2WX/eIYxTNe/8ED7Sx9p/37S92uCEWi/7IR6u79zG6','315687','pres@gmail.com','789674','pres casa',4),(24,'prestamista d','d','prestamistd','$2a$10$KG2297K8wti2WX/eIYxTNe/8ED7Sx9p/37S92uCEWi/7IR6u79zG6','5464564','pres@gmail.com','45645645','pres casa',4),(25,'prestarioa','prestarioa','prestarioa','$2a$10$CRGGCBVZ6dQxilqjgcbvb.wgPsUlKbHGvQEHv/TSqI4QD5oQZQwC.','652629','janter@gmail.com','7896542','av prestario',5),(26,'Thomas','Vazques','thomas','$2a$10$ck/pmLgZRGsBlcb3Dbhivunvsbyu/i1oE/NoR0oW01shYKEPvo5wW','12345678','tomas@gmai.com','8888888','Jr. rosas',4),(30,'Sebastian','Sandoval','sansoval','$2a$10$cEeUkb5W2gz4VhRLCxRP0uiXcbHpap.gU7OnqwX/dCE.otW1HvdOC','47555131','sebastian@gmail.com','779532320','JR. alizos',4),(31,'Grecia','NO C','grecia','$2a$10$Z27HQe3VjShR20P2CwWu7OFwlXvWDH7/vT6PynwXLqj7oXgYFgbsK','7777777','gercia@gmai.com','89654235','comas p ',4),(34,'janter','janter','janter','$2a$10$CIaYZB6nblljz4Gx2uTn8.sH/j9CSw9lf.rrzK04dPU6k06D.fL4e','93525410','janter@gmail.com','985298252','casa de janter',3),(35,'luis','rodriguez','cuis','$2a$10$Cf0cdgDmIwKT6A55fv0tp.ZSxyJo1xxKHNv2VRL6Lo98VLnpjhAiS','62451259','cuiso17@yahoo.com','955629529','Florilandia',3),(36,'nathaly','pretel','nath','$2a$10$BRsEmx8yfxnnSctuxVvbae6Cz0gXXQevV.iiZASvEZvASx8bpYTme','78965412','srtprima@gmail.com','987456325','algun lugar ',4),(38,'jefea','jefea','jefe123','$2a$10$JSFVspSA9Rus9dlSxyKAEesVxLJTA95xGQethBEFiuFARQ8UV7Prq','63928269','jefe123@gmail.com','958298129','jr molina',3),(41,'presa','presa','presabc','$2a$10$hUaDoFxx7YZYc61oIhjA9uRKiulR0y6hZ6FYMKgD.UpD/VDTgtCiC','99999997','presabc@gmai.com','999999998','jr pres',4),(42,'prestatario 1','prestatario 1','prestatario','$2a$10$md8hYku3q99xT8uagTqVx.utF3/i8IMPrSh2BenuAazKucMovAAf6','38373635','prestatario1@proyecto.com','955567123','av. prestatario s/n',5);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-10 19:56:48
