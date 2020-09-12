DROP DATABASE IF EXISTS `agenda`;
CREATE DATABASE `agenda`;
USE `agenda`;
CREATE TABLE `localidad` (
  `idlocalidad` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idlocalidad`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `agenda`.`tipocontacto` (
  `idTipoContacto` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipoContacto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `personas` (
  `idPersona` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Calle` varchar(45) NOT NULL,
  `Nacimiento` date NOT NULL,
  `Altura` int NOT NULL,
  `Piso` int NOT NULL,
  `Departamento` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `localidad` int NOT NULL,
  `tipoDeContacto` int NOT NULL,
  PRIMARY KEY (`idPersona`),
  KEY `localidad` (`localidad`),
  KEY `tipoDeContacto` (`tipoDeContacto`),
  CONSTRAINT `personas_ibfk_1` FOREIGN KEY (`localidad`) REFERENCES `localidad` (`idlocalidad`),
  CONSTRAINT `personas_ibfk_2` FOREIGN KEY (`tipoDeContacto`) REFERENCES `tipocontacto` (`idTipoContacto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;