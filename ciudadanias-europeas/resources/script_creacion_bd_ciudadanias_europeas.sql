CREATE DATABASE  IF NOT EXISTS `ciudadanias_europeas_2_pruebas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ciudadanias_europeas_2_pruebas`;


--
-- Table structure for table `cliente`
--
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id_cliente` bigint NOT NULL AUTO_INCREMENT,
  `dni` bigint DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `pais`
--
DROP TABLE IF EXISTS `pais`;
CREATE TABLE `pais` (
  `id_pais` bigint NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_pais`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `consulado`
--
DROP TABLE IF EXISTS `consulado`;
CREATE TABLE `consulado` (
  `id_consulado` bigint NOT NULL AUTO_INCREMENT,
  `id_pais_fk` bigint NOT NULL,
  `domicilio` varchar(70) DEFAULT NULL,
  `ciudad` varchar(70) DEFAULT NULL,
  `provincia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_consulado`),
  KEY `id_pais_idx` (`id_pais_fk`),
  CONSTRAINT `id_pais_fk` FOREIGN KEY (`id_pais_fk`) REFERENCES `pais` (`id_pais`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `estado_tramite`
--
DROP TABLE IF EXISTS `estado_tramite`;
CREATE TABLE `estado_tramite` (
  `id_estado` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `observacion`
--
DROP TABLE IF EXISTS `observacion`;
CREATE TABLE `observacion` (
  `id_observacion` bigint NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NULL DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_observacion`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `rol`
--
DROP TABLE IF EXISTS `rol`;
CREATE TABLE `rol` (
  `id_rol` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `tipo_documento`
--
DROP TABLE IF EXISTS `tipo_documento`;
CREATE TABLE `tipo_documento` (
  `id_tipo_documento` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `tipo_tramite`
--
DROP TABLE IF EXISTS `tipo_tramite`;
CREATE TABLE `tipo_tramite` (
  `id_tipo_tramite` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(70) NOT NULL,
  `descripcion` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_tramite`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `usuario`
--
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `dni` bigint DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_rol` bigint DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `id_rol_fk_idx` (`id_rol`),
  CONSTRAINT `id_rol_fk` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `mensaje`
--
DROP TABLE IF EXISTS `mensaje`;
CREATE TABLE `mensaje` (
  `id_mensaje` bigint NOT NULL AUTO_INCREMENT,
  `mensaje` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_mensaje`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `reporte`
--
DROP TABLE IF EXISTS `reporte`;


CREATE TABLE `reporte` (
  `id_reporte` bigint NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NULL DEFAULT NULL,
  `datos` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_reporte`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `pago`
--
DROP TABLE IF EXISTS `pago`;
CREATE TABLE `pago` (
  `id_pago` bigint NOT NULL AUTO_INCREMENT,
  `importe` float DEFAULT NULL,
  `id_detalle_tramite` bigint DEFAULT NULL,
  `id_observacion` bigint DEFAULT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `id_detalle_tramite_idx` (`id_detalle_tramite`),
  KEY `id_observacion_idx` (`id_observacion`),
  CONSTRAINT `id_detalle_tramite` FOREIGN KEY (`id_detalle_tramite`) REFERENCES `detalle_tramite` (`id_detalle_tramite`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_observacion` FOREIGN KEY (`id_observacion`) REFERENCES `observacion` (`id_observacion`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `tramite`
--
DROP TABLE IF EXISTS `tramite`;
CREATE TABLE `tramite` (
  `id_tramite` bigint NOT NULL AUTO_INCREMENT,
  `importe` float DEFAULT NULL,
  `id_consulado` bigint DEFAULT NULL,
  `id_tipo_tramite` bigint DEFAULT NULL,
  `moneda` varchar(45) DEFAULT NULL,
  `activo` tinyint NOT NULL,
  PRIMARY KEY (`id_tramite`),
  KEY `id_consulado_idx` (`id_consulado`),
  KEY `id_tipo_tramite_idx` (`id_tipo_tramite`),
  CONSTRAINT `id_consulado` FOREIGN KEY (`id_consulado`) REFERENCES `consulado` (`id_consulado`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_tipo_tramite` FOREIGN KEY (`id_tipo_tramite`) REFERENCES `tipo_tramite` (`id_tipo_tramite`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `detalle_tramite`
--
DROP TABLE IF EXISTS `detalle_tramite`;
CREATE TABLE `detalle_tramite` (
  `id_detalle_tramite` bigint NOT NULL AUTO_INCREMENT,
  `fecha_inicio` timestamp NULL DEFAULT NULL,
  `fecha_fin` timestamp NULL DEFAULT NULL,
  `id_estado_tramite` bigint DEFAULT NULL,
  `id_tramite` bigint DEFAULT NULL,
  `activo` tinyint NOT NULL,
  PRIMARY KEY (`id_detalle_tramite`),
  KEY `id_estado_tramite_idx` (`id_estado_tramite`),
  KEY `id_tramite_idx` (`id_tramite`),
  CONSTRAINT `id_estado_tramite` FOREIGN KEY (`id_estado_tramite`) REFERENCES `estado_tramite` (`id_estado`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_tramite` FOREIGN KEY (`id_tramite`) REFERENCES `tramite` (`id_tramite`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `tipo_documento`
--
DROP TABLE IF EXISTS `tipo_documento`;
CREATE TABLE `tipo_documento` (
  `id_tipo_documento` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `documento`
--
DROP TABLE IF EXISTS `documento`;
CREATE TABLE `documento` (
  `id_documento` bigint NOT NULL AUTO_INCREMENT,
  `fecha_presentacion` timestamp NULL DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_tipo_documento` bigint DEFAULT NULL,
  PRIMARY KEY (`id_documento`),
  KEY `id_tipo_documento_idx` (`id_tipo_documento`),
  CONSTRAINT `id_tipo_documento` FOREIGN KEY (`id_tipo_documento`) REFERENCES `tipo_documento` (`id_tipo_documento`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `detalle_tramite_cliente`
--
DROP TABLE IF EXISTS `detalle_tramite_cliente`;
CREATE TABLE `detalle_tramite_cliente` (
  `id_detalle_tramite` bigint NOT NULL,
  `id_cliente` bigint NOT NULL,
  PRIMARY KEY (`id_cliente`,`id_detalle_tramite`),
  KEY `id_detalle_tramite_idx` (`id_detalle_tramite`) /*!80000 INVISIBLE */,
  KEY `id_cliente_idx` (`id_cliente`),
  CONSTRAINT `id_cliente_det_tra` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `id_detalle_tramite_cli` FOREIGN KEY (`id_detalle_tramite`) REFERENCES `detalle_tramite` (`id_detalle_tramite`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `detalle_tramite_documento`
--
DROP TABLE IF EXISTS `detalle_tramite_documento`;
CREATE TABLE `detalle_tramite_documento` (
  `id_detalle_tramite` bigint NOT NULL,
  `id_documento` bigint NOT NULL,
  PRIMARY KEY (`id_documento`,`id_detalle_tramite`),
  KEY `id_detalle_tramite_idx` (`id_detalle_tramite`) /*!80000 INVISIBLE */,
  KEY `id_documento_idx` (`id_documento`),
  CONSTRAINT `id_detalle_tramite_doc` FOREIGN KEY (`id_detalle_tramite`) REFERENCES `detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `id_documento_det_tra` FOREIGN KEY (`id_documento`) REFERENCES `documento` (`id_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `detalle_tramite_observacion`
--
DROP TABLE IF EXISTS `detalle_tramite_observacion`;


CREATE TABLE `detalle_tramite_observacion` (
  `id_detalle_tramite` bigint NOT NULL,
  `id_observacion` bigint NOT NULL,
  KEY `id_detalle_tramite_idx` (`id_detalle_tramite`),
  KEY `id_observacion_idx` (`id_observacion`),
  CONSTRAINT `id_detalle_tramite_obs` FOREIGN KEY (`id_detalle_tramite`) REFERENCES `detalle_tramite` (`id_detalle_tramite`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_observacion_det_tra` FOREIGN KEY (`id_observacion`) REFERENCES `observacion` (`id_observacion`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `detalle_tramite_usuario`
--
DROP TABLE IF EXISTS `detalle_tramite_usuario`;
CREATE TABLE `detalle_tramite_usuario` (
  `id_detalle_tramite` bigint NOT NULL,
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_detalle_tramite`),
  KEY `id_usuario_idx` (`id_usuario`),
  KEY `id_detalle_tramite_usu_idx` (`id_detalle_tramite`),
  CONSTRAINT `id_detalle_tramite_usu` FOREIGN KEY (`id_detalle_tramite`) REFERENCES `detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `id_usuario_det_tra` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



/**********************************/
-- ---------------------------------
-- ---------------------------------
/**********************************/
-- ---------------------------------
-- ---------------------------------
--
--
-- INSERTS DE DATOS DE PARAMETRÍAS
--
--
-- ---------------------------------
-- ---------------------------------
/**********************************/
-- ---------------------------------
-- ---------------------------------
/**********************************/

--
-- Dumping data for table `cliente`
--
LOCK TABLES `cliente` WRITE;
INSERT INTO `cliente` VALUES (3,12345678,'Juan','Pérez','cuperez@gmail.com'),(4,98765432,'Pedro','Torres','ptorres@gmail.com');
UNLOCK TABLES;


--
-- Dumping data for table `pais`
--
LOCK TABLES `pais` WRITE;
INSERT INTO `pais` VALUES (1,'España'),(2,'Italia');
UNLOCK TABLES;


--
-- Dumping data for table `consulado`
--
LOCK TABLES `consulado` WRITE;
INSERT INTO `consulado` VALUES (1,1,'Guido 1770','CABA','Buenos Aires'),(2,2,'Av. Vélez Sarsfield 360','Córdoba','Córdoba');
UNLOCK TABLES;


--
-- Dumping data for table `estado_tramite`
--
LOCK TABLES `estado_tramite` WRITE;
INSERT INTO `estado_tramite` VALUES (1,'El cliente se comunicó y realizó el pago inicial.','Iniciado'),(2,'El cliente presentó documentos y se están validando.','En curso'),(3,'Se han enviado los documentos a los respectivos consulados','Enviado'),(4,'Se recibó el pasaporte por parte del consulado','Finalizado');
UNLOCK TABLES;


--
-- Dumping data for table `rol`
--
LOCK TABLES `rol` WRITE;
INSERT INTO `rol` VALUES (1,'Operador','Sólo operaciones diarias.'),(2,'Administrador','Operaciones diarias, validaciones y reportes.');
UNLOCK TABLES;


--
-- Dumping data for table `tipo_tramite`
--
LOCK TABLES `tipo_tramite` WRITE;
INSERT INTO `tipo_tramite` VALUES (1,'Rectificación','Solicitud de modificación de partidas.'),(2,'Ciudadanía','Solicitud de ciudadanía');
UNLOCK TABLES;


--
-- Dumping data for table `usuario`
--
LOCK TABLES `usuario` WRITE;
INSERT INTO `usuario` VALUES (1,11223344,'López','empleado@ciudadanias.com.ar','Empleado',1),(2,55667788,'Gómez','administrador@ciudadanias.com.ar','Administrador',2);
UNLOCK TABLES;


--
-- Dumping data for table `tipo_documento`
--
LOCK TABLES `tipo_documento` WRITE;
INSERT INTO `tipo_documento` VALUES (1,'Anexo con informacoión extra.','Anexo'),(2,'Apostille enviado desde el país origen.','Apostille'),(3,'De nacimiento, matrimonio, defunción.','Partida');
UNLOCK TABLES;