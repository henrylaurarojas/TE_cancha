CREATE DATABASE CANCHAS;

USE CANCHAS;

CREATE TABLE cliente(
idcl INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(50),
apellido VARCHAR(50),
ci VARCHAR(15),
celular VARCHAR(10)
);

CREATE TABLE empleado(
ide INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(50),
ci VARCHAR(30),
pass VARCHAR(30),
celular VARCHAR(15)
);

CREATE TABLE cancha(
idcan INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(50),
precio INT,
obs VARCHAR(100)
);

CREATE TABLE reserva(
idr INT AUTO_INCREMENT PRIMARY KEY,
fecha VARCHAR(50),
desde VARCHAR(50),
hasta VARCHAR(50),
idcl INT,
ide INT,
idcan INT,
obs VARCHAR(100),
FOREIGN KEY (idcl) REFERENCES cliente(idcl),
FOREIGN KEY (ide) REFERENCES empleado(ide),
FOREIGN KEY (idcan) REFERENCES cancha(idcan)
);


CREATE TABLE caja(
id INT AUTO_INCREMENT PRIMARY KEY,
fecha VARCHAR(50),
idr INT,
monto INT,
FOREIGN KEY (idr) REFERENCES reserva(idr)
);

SELECT * FROM cliente;
SELECT * FROM empleado;
SELECT * FROM cancha;
SELECT * FROM reserva;
SELECT * FROM caja;
DROP TABLE caja;

SELECT r.idr, r.fecha, r.desde, r.hasta, c.nombre, e.nombre, ca.nombre, r.obs FROM reserva r, cliente c, empleado e, cancha ca WHERE r.idcl=c.idcl AND r.ide=e.ide AND r.idcan=ca.idcan;

SELECT c.id, c.fecha, c.monto, r.fecha, r.desde, r.hastacaja FROM caja c, reserva r WHERE r.idr=c.idres ;

INSERT INTO cancha VALUE (1, 'San Pedro', 100, 'Fútbol Zona San Pedro');

INSERT INTO cliente VALUE (1, 'Ximena', 'Huanca Laura', '8323453', 68203421);

INSERT INTO empleado VALUE (1, 'Henry', 9205630, 9205630, 71259583);

INSERT INTO reserva VALUE (1, '30-11-2021', '08:00', '09:00', 1, 1, 1, 'cancelado');

INSERT INTO caja VALUE (1, '30-11-2021', 1, 100);
INSERT INTO caja VALUE (2, 30-11-2021, 1, 90);
INSERT INTO caja VALUE (3, 2021-11-30, 1, 90);
INSERT INTO caja VALUE (4, '2021-12-05', 1, 100);