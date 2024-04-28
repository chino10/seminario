-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ciudadanias_europeas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ciudadanias_europeas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ciudadanias_europeas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ciudadanias_europeas` ;

-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`cliente` (
  `id_cliente` BIGINT NOT NULL AUTO_INCREMENT,
  `dni` BIGINT NULL DEFAULT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `apellido` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id_cliente`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`pais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`pais` (
  `id_pais` BIGINT NOT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_pais`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`consulado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`consulado` (
  `id_consulado` BIGINT NOT NULL AUTO_INCREMENT,
  `id_pais_fk` BIGINT NOT NULL,
  `domicilio` VARCHAR(70) NULL DEFAULT NULL,
  `ciudad` VARCHAR(70) NULL DEFAULT NULL,
  `provincia` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_consulado`),
  INDEX `id_pais_idx` (`id_pais_fk` ASC) VISIBLE,
  CONSTRAINT `id_pais_fk`
    FOREIGN KEY (`id_pais_fk`)
    REFERENCES `ciudadanias_europeas`.`pais` (`id_pais`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`detalle_tramite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`detalle_tramite` (
  `fechaFin` DATETIME(6) NULL DEFAULT NULL,
  `fechaInicio` DATETIME(6) NULL DEFAULT NULL,
  `id_detalle_tramite` BIGINT NOT NULL AUTO_INCREMENT,
  `fecha_fin` DATETIME(6) NULL DEFAULT NULL,
  `fecha_inicio` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id_detalle_tramite`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`detalle_tramite_cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`detalle_tramite_cliente` (
  `DetalleTramite_id_detalle_tramite` BIGINT NOT NULL,
  `listaClientes_id_cliente` BIGINT NOT NULL,
  UNIQUE INDEX `UK_lskvqre6w80fu7u7l5frhuk9q` (`listaClientes_id_cliente` ASC) VISIBLE,
  INDEX `FKaugcu6nwgdqxhobh9v6qee5t` (`DetalleTramite_id_detalle_tramite` ASC) VISIBLE,
  CONSTRAINT `FKaugcu6nwgdqxhobh9v6qee5t`
    FOREIGN KEY (`DetalleTramite_id_detalle_tramite`)
    REFERENCES `ciudadanias_europeas`.`detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `FKdggifwgsavk3aru4g7jyq9tiw`
    FOREIGN KEY (`listaClientes_id_cliente`)
    REFERENCES `ciudadanias_europeas`.`cliente` (`id_cliente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`documento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`documento` (
  `fecha_presentacion` DATETIME(6) NULL DEFAULT NULL,
  `id_documento` BIGINT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_documento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`detalle_tramite_documento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`detalle_tramite_documento` (
  `DetalleTramite_id_detalle_tramite` BIGINT NOT NULL,
  `listaDocumentos_id_documento` BIGINT NOT NULL,
  UNIQUE INDEX `UK_fspwktxosd9918p6mm161wad0` (`listaDocumentos_id_documento` ASC) VISIBLE,
  INDEX `FK3korffk9262nkvqlnndje7u24` (`DetalleTramite_id_detalle_tramite` ASC) VISIBLE,
  CONSTRAINT `FK3korffk9262nkvqlnndje7u24`
    FOREIGN KEY (`DetalleTramite_id_detalle_tramite`)
    REFERENCES `ciudadanias_europeas`.`detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `FKcxrdembbu2a01r4kcnviedcu9`
    FOREIGN KEY (`listaDocumentos_id_documento`)
    REFERENCES `ciudadanias_europeas`.`documento` (`id_documento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`detalle_tramite_lista_clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`detalle_tramite_lista_clientes` (
  `detalle_tramite_id_detalle_tramite` BIGINT NOT NULL,
  `lista_clientes_id_cliente` BIGINT NOT NULL,
  UNIQUE INDEX `UK_l61y10vfawwtlxe174myccbwg` (`lista_clientes_id_cliente` ASC) VISIBLE,
  INDEX `FKkylm3eier2f4gh59u3krxn1ra` (`detalle_tramite_id_detalle_tramite` ASC) VISIBLE,
  CONSTRAINT `FK5lfm0qs1xqa39iwoi1imb6f7`
    FOREIGN KEY (`lista_clientes_id_cliente`)
    REFERENCES `ciudadanias_europeas`.`cliente` (`id_cliente`),
  CONSTRAINT `FKkylm3eier2f4gh59u3krxn1ra`
    FOREIGN KEY (`detalle_tramite_id_detalle_tramite`)
    REFERENCES `ciudadanias_europeas`.`detalle_tramite` (`id_detalle_tramite`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`detalle_tramite_lista_documentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`detalle_tramite_lista_documentos` (
  `detalle_tramite_id_detalle_tramite` BIGINT NOT NULL,
  `lista_documentos_id_documento` BIGINT NOT NULL,
  UNIQUE INDEX `UK_jutd6rx1gwbhc941eyextj3sf` (`lista_documentos_id_documento` ASC) VISIBLE,
  INDEX `FK3y5sjef8a01sd6p8f5lsr6tqw` (`detalle_tramite_id_detalle_tramite` ASC) VISIBLE,
  CONSTRAINT `FK3y5sjef8a01sd6p8f5lsr6tqw`
    FOREIGN KEY (`detalle_tramite_id_detalle_tramite`)
    REFERENCES `ciudadanias_europeas`.`detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `FKtn87ec81xm5w59qj9jso97rmx`
    FOREIGN KEY (`lista_documentos_id_documento`)
    REFERENCES `ciudadanias_europeas`.`documento` (`id_documento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`observacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`observacion` (
  `fecha` DATETIME(6) NULL DEFAULT NULL,
  `id_observacion` BIGINT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_observacion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`detalle_tramite_lista_observaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`detalle_tramite_lista_observaciones` (
  `detalle_tramite_id_detalle_tramite` BIGINT NOT NULL,
  `lista_observaciones_id_observacion` BIGINT NOT NULL,
  UNIQUE INDEX `UK_f4pl9wm63rodf03n2jeyj2okd` (`lista_observaciones_id_observacion` ASC) VISIBLE,
  INDEX `FKp319hjvum2vqsj3s6yiu2uenr` (`detalle_tramite_id_detalle_tramite` ASC) VISIBLE,
  CONSTRAINT `FK3o8cdqey0kscxi1rhgowqfu52`
    FOREIGN KEY (`lista_observaciones_id_observacion`)
    REFERENCES `ciudadanias_europeas`.`observacion` (`id_observacion`),
  CONSTRAINT `FKp319hjvum2vqsj3s6yiu2uenr`
    FOREIGN KEY (`detalle_tramite_id_detalle_tramite`)
    REFERENCES `ciudadanias_europeas`.`detalle_tramite` (`id_detalle_tramite`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`rol` (
  `id_rol` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` VARCHAR(240) NULL DEFAULT NULL,
  PRIMARY KEY (`id_rol`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`usuario` (
  `dni` BIGINT NULL DEFAULT NULL,
  `id_usuario` BIGINT NOT NULL AUTO_INCREMENT,
  `apellido` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  `id_rol` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  INDEX `id_rol_fk_idx` (`id_rol` ASC) VISIBLE,
  CONSTRAINT `id_rol_fk`
    FOREIGN KEY (`id_rol`)
    REFERENCES `ciudadanias_europeas`.`rol` (`id_rol`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`detalle_tramite_lista_usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`detalle_tramite_lista_usuarios` (
  `detalle_tramite_id_detalle_tramite` BIGINT NOT NULL,
  `lista_usuarios_id_usuario` BIGINT NOT NULL,
  UNIQUE INDEX `UK_sq7xvalma3nq31xw6vin0h9s` (`lista_usuarios_id_usuario` ASC) VISIBLE,
  INDEX `FKh41u4npw4daw1c6n9o4ypak40` (`detalle_tramite_id_detalle_tramite` ASC) VISIBLE,
  CONSTRAINT `FKh41u4npw4daw1c6n9o4ypak40`
    FOREIGN KEY (`detalle_tramite_id_detalle_tramite`)
    REFERENCES `ciudadanias_europeas`.`detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `FKsi5gkdyjm5v506i3hw9fecrgo`
    FOREIGN KEY (`lista_usuarios_id_usuario`)
    REFERENCES `ciudadanias_europeas`.`usuario` (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`detalle_tramite_observacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`detalle_tramite_observacion` (
  `DetalleTramite_id_detalle_tramite` BIGINT NOT NULL,
  `listaObservaciones_id_observacion` BIGINT NOT NULL,
  UNIQUE INDEX `UK_ds42slywc2po1wqvt59o74cx9` (`listaObservaciones_id_observacion` ASC) VISIBLE,
  INDEX `FKarcs1we04qdg803b4kmukdq5h` (`DetalleTramite_id_detalle_tramite` ASC) VISIBLE,
  CONSTRAINT `FKarcs1we04qdg803b4kmukdq5h`
    FOREIGN KEY (`DetalleTramite_id_detalle_tramite`)
    REFERENCES `ciudadanias_europeas`.`detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `FKcv8f4kn5nntib80faq5vclo13`
    FOREIGN KEY (`listaObservaciones_id_observacion`)
    REFERENCES `ciudadanias_europeas`.`observacion` (`id_observacion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`detalle_tramite_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`detalle_tramite_usuario` (
  `DetalleTramite_id_detalle_tramite` BIGINT NOT NULL,
  `listaUsuarios_id_usuario` BIGINT NOT NULL,
  UNIQUE INDEX `UK_qdavbh4lglte5km9b0jojdtip` (`listaUsuarios_id_usuario` ASC) VISIBLE,
  INDEX `FK6rmxdsblxbjnyr50vc1mques2` (`DetalleTramite_id_detalle_tramite` ASC) VISIBLE,
  CONSTRAINT `FK6rmxdsblxbjnyr50vc1mques2`
    FOREIGN KEY (`DetalleTramite_id_detalle_tramite`)
    REFERENCES `ciudadanias_europeas`.`detalle_tramite` (`id_detalle_tramite`),
  CONSTRAINT `FKh5evknd83sgw0i24d81c6gpn5`
    FOREIGN KEY (`listaUsuarios_id_usuario`)
    REFERENCES `ciudadanias_europeas`.`usuario` (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`estado` (
  `id_estado` BIGINT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_estado`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`mensaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`mensaje` (
  `id_mensaje` BIGINT NOT NULL AUTO_INCREMENT,
  `mensaje` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_mensaje`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudadanias_europeas`.`pago` (
  `importe` FLOAT NULL DEFAULT NULL,
  `id_pago` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_pago`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ciudadanias_europeas`.`reporte`
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
-- Table `ciudadanias_europeas`.`tipo_documento`
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
-- Table `ciudadanias_europeas`.`tipo_tramite`
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
-- Table `ciudadanias_europeas`.`tramite`
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
