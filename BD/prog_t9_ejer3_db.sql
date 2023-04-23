-- Creación database
DROP SCHEMA IF EXISTS  prog_t9_ejer3_db;
CREATE SCHEMA prog_t9_ejer3_db;
USE prog_t9_ejer3_db;

-- Creacion tabla "serie"
DROP TABLE IF EXISTS `serie`;
CREATE TABLE `serie` (
  `idserie` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `temporadas` int(11) NOT NULL,
  `capitulos` int(11) NOT NULL,
  PRIMARY KEY (`idserie`));
  
-- Inserción valores de prueba
INSERT INTO serie (nombre, temporadas, capitulos) VALUES 
('Game of Thrones', 8, 73),
('Breaking Bad', 5, 62),
('The Sopranos', 6, 86),
('Stranger Things', 4, 34),
('The Crown', 4, 40),
('Narcos', 3, 30),
('Black Mirror', 5, 22),
('Friends', 10, 236),
('The Office', 9, 201),
('Mad Men', 7, 92);