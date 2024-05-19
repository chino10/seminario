-- MySQL Workbench

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ciudadanias_europeas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ciudadanias_europeas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `ciudadanias_europeas`;

-- -----------------------------------------------------
-- Tabla cliente
-- -----------------------------------------------------
CREATE TABLE `cliente` (
  `id_cliente` bigint NOT NULL AUTO_INCREMENT,
  `dni` bigint DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla pais
-- -----------------------------------------------------
CREATE TABLE `pais` (
  `id_pais` bigint NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_pais`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla consulado
-- -----------------------------------------------------
CREATE TABLE `consulado` (
  `id_consulado` bigint NOT NULL AUTO_INCREMENT,
  `id_pais_fk` bigint NOT NULL,
  `domicilio` varchar(70) DEFAULT NULL,
  `ciudad` varchar(70) DEFAULT NULL,
  `provincia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_consulado`),
  KEY `id_pais_idx` (`id_pais_fk`),
  CONSTRAINT `id_pais_fk` FOREIGN KEY (`id_pais_fk`) REFERENCES `pais` (`id_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla detalle_tramite
-- -----------------------------------------------------
CREATE TABLE `detalle_tramite` (
  `id_detalle_tramite` bigint NOT NULL AUTO_INCREMENT,
  `fecha_fin` datetime(6) DEFAULT NULL,
  `fecha_inicio` datetime(6) DEFAULT NULL,
  `id_estado_tramite` bigint DEFAULT NULL,
  `id_tramite` bigint DEFAULT NULL,
  PRIMARY KEY (`id_detalle_tramite`),
  KEY `id_estado_tramite_idx` (`id_estado_tramite`),
  KEY `id_tramite_idx` (`id_tramite`),
  CONSTRAINT `id_estado_tramite` 
    FOREIGN KEY (`id_estado_tramite`) 
	REFERENCES `estado_tramite` (`id_estado`),
  CONSTRAINT `id_tramite` 
	FOREIGN KEY (`id_tramite`) 
	REFERENCES `tramite` (`id_tramite`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla detalle_tramite_cliente
-- -----------------------------------------------------
CREATE TABLE `detalle_tramite_cliente` (
  `DetalleTramite_id_detalle_tramite` bigint NOT NULL,
  `listaClientes_id_cliente` bigint NOT NULL,
  UNIQUE KEY `UK_lskvqre6w80fu7u7l5frhuk9q` (`listaClientes_id_cliente`),
  KEY `FKaugcu6nwgdqxhobh9v6qee5t` (`DetalleTramite_id_detalle_tramite`),
  CONSTRAINT `FKaugcu6nwgdqxhobh9v6qee5t` 
	FOREIGN KEY (`DetalleTramite_id_detalle_tramite`) 
	REFERENCES `detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `FKdggifwgsavk3aru4g7jyq9tiw` 
	FOREIGN KEY (`listaClientes_id_cliente`) 
	REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla documento
-- -----------------------------------------------------
CREATE TABLE `documento` (
  `id_documento` bigint NOT NULL AUTO_INCREMENT,
  `fecha_presentacion` datetime(6) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_tipo_documento` bigint DEFAULT NULL,
  PRIMARY KEY (`id_documento`),
  KEY `id_tipo_documento_idx` (`id_tipo_documento`),
  CONSTRAINT `id_tipo_documento` 
	FOREIGN KEY (`id_tipo_documento`) 
	REFERENCES `tipo_documento` (`id_tipo_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla detalle_tramite_documento
-- -----------------------------------------------------
CREATE TABLE `detalle_tramite_documento` (
  `DetalleTramite_id_detalle_tramite` bigint NOT NULL,
  `listaDocumentos_id_documento` bigint NOT NULL,
  UNIQUE KEY `UK_fspwktxosd9918p6mm161wad0` (`listaDocumentos_id_documento`),
  KEY `FK3korffk9262nkvqlnndje7u24` (`DetalleTramite_id_detalle_tramite`),
  CONSTRAINT `FK3korffk9262nkvqlnndje7u24` 
	FOREIGN KEY (`DetalleTramite_id_detalle_tramite`) 
	REFERENCES `detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `FKcxrdembbu2a01r4kcnviedcu9` 
	FOREIGN KEY (`listaDocumentos_id_documento`) 
	REFERENCES `documento` (`id_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla detalle_tramite_lista_clientes
-- -----------------------------------------------------
CREATE TABLE `detalle_tramite_lista_clientes` (
  `detalle_tramite_id_detalle_tramite` bigint NOT NULL,
  `lista_clientes_id_cliente` bigint NOT NULL,
  UNIQUE KEY `UK_l61y10vfawwtlxe174myccbwg` (`lista_clientes_id_cliente`),
  KEY `FKkylm3eier2f4gh59u3krxn1ra` (`detalle_tramite_id_detalle_tramite`),
  CONSTRAINT `FK5lfm0qs1xqa39iwoi1imb6f7` 
	FOREIGN KEY (`lista_clientes_id_cliente`) 
	REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `FKkylm3eier2f4gh59u3krxn1ra` 
	FOREIGN KEY (`detalle_tramite_id_detalle_tramite`) 
	REFERENCES `detalle_tramite` (`id_detalle_tramite`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla detalle_tramite_lista_documentos
-- -----------------------------------------------------
CREATE TABLE `detalle_tramite_lista_documentos` (
  `detalle_tramite_id_detalle_tramite` bigint NOT NULL,
  `lista_documentos_id_documento` bigint NOT NULL,
  UNIQUE KEY `UK_jutd6rx1gwbhc941eyextj3sf` (`lista_documentos_id_documento`),
  KEY `FK3y5sjef8a01sd6p8f5lsr6tqw` (`detalle_tramite_id_detalle_tramite`),
  CONSTRAINT `FK3y5sjef8a01sd6p8f5lsr6tqw` 
	FOREIGN KEY (`detalle_tramite_id_detalle_tramite`) 
	REFERENCES `detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `FKtn87ec81xm5w59qj9jso97rmx` 
	FOREIGN KEY (`lista_documentos_id_documento`) 
	REFERENCES `documento` (`id_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla observacion
-- -----------------------------------------------------
CREATE TABLE `observacion` (
  `id_observacion` bigint NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_observacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla detalle_tramite_lista_observaciones
-- -----------------------------------------------------
CREATE TABLE `detalle_tramite_lista_observaciones` (
  `detalle_tramite_id_detalle_tramite` bigint NOT NULL,
  `lista_observaciones_id_observacion` bigint NOT NULL,
  UNIQUE KEY `UK_f4pl9wm63rodf03n2jeyj2okd` (`lista_observaciones_id_observacion`),
  KEY `FKp319hjvum2vqsj3s6yiu2uenr` (`detalle_tramite_id_detalle_tramite`),
  CONSTRAINT `FK3o8cdqey0kscxi1rhgowqfu52` 
	FOREIGN KEY (`lista_observaciones_id_observacion`) 
	REFERENCES `observacion` (`id_observacion`),
  CONSTRAINT `FKp319hjvum2vqsj3s6yiu2uenr` 
	FOREIGN KEY (`detalle_tramite_id_detalle_tramite`) 
	REFERENCES `detalle_tramite` (`id_detalle_tramite`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla rol
-- -----------------------------------------------------
CREATE TABLE `rol` (
  `id_rol` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla usuario
-- -----------------------------------------------------
CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `dni` bigint DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_rol` bigint DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `id_rol_fk_idx` (`id_rol`),
  CONSTRAINT `id_rol_fk` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla detalle_tramite_lista_usuarios
-- -----------------------------------------------------
CREATE TABLE `detalle_tramite_lista_usuarios` (
  `detalle_tramite_id_detalle_tramite` bigint NOT NULL,
  `lista_usuarios_id_usuario` bigint NOT NULL,
  UNIQUE KEY `UK_sq7xvalma3nq31xw6vin0h9s` (`lista_usuarios_id_usuario`),
  KEY `FKh41u4npw4daw1c6n9o4ypak40` (`detalle_tramite_id_detalle_tramite`),
  CONSTRAINT `FKh41u4npw4daw1c6n9o4ypak40` 
	FOREIGN KEY (`detalle_tramite_id_detalle_tramite`) 
	REFERENCES `detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `FKsi5gkdyjm5v506i3hw9fecrgo` 
	FOREIGN KEY (`lista_usuarios_id_usuario`) 
	REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla detalle_tramite_observacion
-- -----------------------------------------------------
CREATE TABLE `detalle_tramite_observacion` (
  `DetalleTramite_id_detalle_tramite` bigint NOT NULL,
  `listaObservaciones_id_observacion` bigint NOT NULL,
  UNIQUE KEY `UK_ds42slywc2po1wqvt59o74cx9` (`listaObservaciones_id_observacion`),
  KEY `FKarcs1we04qdg803b4kmukdq5h` (`DetalleTramite_id_detalle_tramite`),
  CONSTRAINT `FKarcs1we04qdg803b4kmukdq5h` 
	FOREIGN KEY (`DetalleTramite_id_detalle_tramite`) 
	REFERENCES `detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `FKcv8f4kn5nntib80faq5vclo13` 
	FOREIGN KEY (`listaObservaciones_id_observacion`) 
	REFERENCES `observacion` (`id_observacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla detalle_tramite_usuario
-- -----------------------------------------------------
CREATE TABLE `detalle_tramite_usuario` (
  `DetalleTramite_id_detalle_tramite` bigint NOT NULL,
  `listaUsuarios_id_usuario` bigint NOT NULL,
  UNIQUE KEY `UK_qdavbh4lglte5km9b0jojdtip` (`listaUsuarios_id_usuario`),
  KEY `FK6rmxdsblxbjnyr50vc1mques2` (`DetalleTramite_id_detalle_tramite`),
  CONSTRAINT `FK6rmxdsblxbjnyr50vc1mques2` 
	FOREIGN KEY (`DetalleTramite_id_detalle_tramite`) 
	REFERENCES `detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `FKh5evknd83sgw0i24d81c6gpn5` 
	FOREIGN KEY (`listaUsuarios_id_usuario`) 
	REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla estado_tramite
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`estado_tramite` (
  `id_estado` BIGINT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_estado`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Tabla mensaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`mensaje` (
  `id_mensaje` BIGINT NOT NULL AUTO_INCREMENT,
  `mensaje` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_mensaje`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Tabla pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`pago` (
  `importe` FLOAT NULL DEFAULT NULL,
  `id_pago` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_pago`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Tabla reporte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`reporte` (
  `fecha` DATETIME(6) NULL DEFAULT NULL,
  `id_reporte` BIGINT NOT NULL AUTO_INCREMENT,
  `datos` VARCHAR(255) NULL DEFAULT NULL,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  `observacion` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_reporte`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Tabla tipo_documento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`tipo_documento` (
  `id_tipo_documento` BIGINT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipo_documento`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Tabla tipo_tramite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`tipo_tramite` (
  `id_tipo_tramite` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(70) NOT NULL,
  `descripcion` VARCHAR(240) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipo_tramite`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Tabla tramite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`tramite` (
  `importe` FLOAT NULL DEFAULT NULL,
  `id_tramite` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_tramite`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
