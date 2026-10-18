-- Esquema reconstruido a partir del código de OlimpoGym.
SET NAMES utf8mb4;
-- El orden de las columnas importa: la exportación a CSV y algunas consultas dependen de él.
CREATE DATABASE IF NOT EXISTS pruebaparcial CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE pruebaparcial;

CREATE TABLE IF NOT EXISTS cliente (
  idcliente       INT AUTO_INCREMENT PRIMARY KEY,
  Nombre          VARCHAR(255),
  Apellidos       VARCHAR(255),
  Estatus         VARCHAR(255),
  PrecioMatricula INT,
  Telefono        INT,
  DNI             VARCHAR(255),
  Direccion       VARCHAR(255),
  fechaInicio     DATE,
  fechaFin        DATE,
  foto            VARCHAR(255)
);
