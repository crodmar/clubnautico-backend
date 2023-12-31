CREATE DATABASE `clubnautico`;

USE clubnautico;

CREATE TABLE `socio` (
  `id_socio` int NOT NULL AUTO_INCREMENT,
  `dni_socio` varchar(9) NOT NULL,
  `nombre_socio` varchar(20) NOT NULL,
  `apellido1_socio` varchar(20) NOT NULL,
  `apellido2_socio` varchar(20) DEFAULT NULL,
  `telefono_socio` int DEFAULT NULL,
  `email_socio` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_socio`),
  UNIQUE KEY `dni_socio` (`dni_socio`)
);

CREATE TABLE `patron` (
  `id_patron` int NOT NULL AUTO_INCREMENT,
  `dni_patron` varchar(9) NOT NULL,
  `nombre_patron` varchar(20) NOT NULL,
  `apellido1_patron` varchar(20) NOT NULL,
  `apellido2_patron` varchar(20) DEFAULT NULL,
  `telefono_patron` int DEFAULT NULL,
  `email_patron` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_patron`),
  UNIQUE KEY `dni_patron` (`dni_patron`)
);

CREATE TABLE `barco` (
  `id_barco` int NOT NULL AUTO_INCREMENT,
  `num_matricula` varchar(9) NOT NULL,
  `nombre_barco` varchar(20) NOT NULL,
  `num_amarre` int NOT NULL,
  `cuota` double NOT NULL,
  `id_socio` int NOT NULL,
  PRIMARY KEY (`id_barco`),
  UNIQUE KEY `num_matricula` (`num_matricula`),
  UNIQUE KEY `num_amarre_UNIQUE` (`num_amarre`),
  KEY `barco_ibfk_1` (`id_socio`),
  CONSTRAINT `barco_ibfk_1` FOREIGN KEY (`id_socio`) REFERENCES `socio` (`id_socio`)
);

CREATE TABLE `salida` (
  `id_salida` int NOT NULL AUTO_INCREMENT,
  `fecha_hora` datetime NOT NULL,
  `destino` varchar(20) NOT NULL,
  `id_patron` int NOT NULL,
  `id_barco` int NOT NULL,
  PRIMARY KEY (`id_salida`),
  KEY `salida_ibfk_1` (`id_patron`),
  KEY `salida_ibfk_2` (`id_barco`),
  CONSTRAINT `salida_ibfk_1` FOREIGN KEY (`id_patron`) REFERENCES `patron` (`id_patron`),
  CONSTRAINT `salida_ibfk_2` FOREIGN KEY (`id_barco`) REFERENCES `barco` (`id_barco`)
);

INSERT INTO socio VALUES
  (NULL, '12345678A', 'Cristina', 'Rodríguez', 'Martín', 600600600, 'cristina@gmail.com'),
  (NULL, '87654321B', 'Carlos', 'González', 'López', 655655655, 'carlos@gmail.com'),
  (NULL, '98765432C', 'Ana', 'Martínez', 'García', 600700700, 'ana@gmail.com'),
  (NULL, '56789012D', 'Pedro', 'Ramírez', 'Sánchez', 650750750, 'pedro@gmail.com'),
  (NULL, '11223344E', 'Laura', 'Fernández', 'Gómez', 600800800, 'laura@gmail.com'),
  (NULL, '55667788F', 'Juan', 'Pérez', 'Martínez', 900900900, 'juan@gmail.com'),
  (NULL, '99887766G', 'Elena', 'Hernández', 'Santos', 950950950, 'elena@gmail.com'),
  (NULL, '44556677H', 'Miguel', 'López', 'Rodríguez', 600600601, 'miguel@gmail.com'),
  (NULL, '33445566I', 'Isabel', 'Gutiérrez', 'Fernández', 600700701, 'isabel@gmail.com');


CREATE TABLE usuario (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `email_usuario` varchar(50) NOT NULL,
  `contrasena_usuario` varchar(50) NOT NULL,
  PRIMARY KEY (`id_usuario`)
);

INSERT INTO usuario VALUES (
  NULL, 'admin@admin.com', 'admin'
);