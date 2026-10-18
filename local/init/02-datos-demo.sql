-- Clientes ficticios para poder enseñar la aplicación (sin foto).
SET NAMES utf8mb4;
USE pruebaparcial;

INSERT INTO cliente (Nombre, Apellidos, Estatus, PrecioMatricula, Telefono, DNI, Direccion, fechaInicio, fechaFin, foto) VALUES
('Carlos',  'García Pérez',      'Activo',   30, 600111222, '11111111A', 'C/ Mayor 12, Madrid',     '2026-09-01', '2026-10-01', ''),
('Lucía',   'Fernández Ruiz',    'Activo',   30, 600333444, '22222222B', 'Av. del Sol 5, Madrid',   '2026-09-15', '2026-11-15', ''),
('Miguel',  'Sánchez Ortega',    'Inactivo', 25, 600555666, '33333333C', 'C/ Luna 3, Getafe',       '2026-06-01', '2026-07-01', ''),
('Ana',     'Martín López',      'Activo',   35, 600777888, '44444444D', 'Plaza España 1, Leganés', '2026-10-01', '2027-01-01', ''),
('Javier',  'Romero Díaz',       'Activo',   30, 600999000, '55555555E', 'C/ Olmo 8, Alcorcón',     '2026-10-10', '2026-11-10', '');
