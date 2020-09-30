DROP DATABASE `agenda`;
CREATE DATABASE `agenda` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `agenda`;
CREATE TABLE `pais` (
  `idPais` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idPais`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
CREATE TABLE `provincia` (
  `idProvincia` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `pais` int NOT NULL,
  PRIMARY KEY (`idProvincia`),
  KEY `fk_pais_idx` (`pais`),
  CONSTRAINT `fk_pais` FOREIGN KEY (`pais`) REFERENCES `pais` (`idPais`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
CREATE TABLE `localidad` (
  `idlocalidad` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `provincia` int NOT NULL,
  PRIMARY KEY (`idlocalidad`),
  KEY `fk_provincia_idx` (`provincia`),
  CONSTRAINT `fk_provincia` FOREIGN KEY (`provincia`) REFERENCES `provincia` (`idProvincia`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
CREATE TABLE `tipocontacto` (
  `idTipoContacto` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipoContacto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `deporte` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `personas` (
  `idPersona` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Calle` varchar(45),
  `Nacimiento` date,
  `Altura` varchar(10),
  `Piso` varchar(10),
  `Departamento` varchar(45),
  `Email` varchar(45) NOT NULL,
  `localidad` int,
  `tipoDeContacto` int NOT NULL,
  `deporte` int,
  PRIMARY KEY (`idPersona`),
  KEY `fk_localidad_idx` (`localidad`),
  KEY `fk_tipoDeContacto_idx` (`tipoDeContacto`),
  KEY `fk_deporte_idx` (`deporte`),
  CONSTRAINT `fk_deporte` FOREIGN KEY (`deporte`) REFERENCES `deporte` (`id`),
  CONSTRAINT `fk_localidad` FOREIGN KEY (`localidad`) REFERENCES `localidad` (`idlocalidad`),
  CONSTRAINT `fk_tipoDeContacto` FOREIGN KEY (`tipoDeContacto`) REFERENCES `tipocontacto` (`idTipoContacto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
