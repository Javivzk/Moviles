BEGIN
--INSERTS USUARIOS
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('javivzk','1234','Javier','Vizcaino','Olivares',622315206,'javivzk@email.com','ADMIN');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('Emp1','1234','Jose','Lopez','Cuervo',644128962,'jolocu@email.com','EMPLOYEE');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('Emp2','1234','Sonia','Marin','Romanos',642563200,'somaro@email.com','EMPLOYEE');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('User1','1234','Vicente','Carbonel','Goya',614789632,'vicago@email.com','USER');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('User2','1234','Ana','Sanz','Cortes',621547898,'anasaco@email.com','USER');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('User3','1234','Benito','Garcia','Martin',644123698,'begama@email.com','USER');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('User4','1234','Belen','Rueda','Camara',642356987,'beruca@email.com','USER');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('User5','1234','Domingo','Miguel','Ribera',641223644,'domiri@email.com','USER');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('User6','1234','Montse','Figueroa','Muñoz',644125478,'mofimu@email.com','USER');

--INSERTS CITAS
INSERT INTO CITAS (id_usuario, fecha, seccion, comentarios) VALUES (6,'05/04/2022','INFORMACION','Quiero pedir informacion sobre la financiacion de x movil');
INSERT INTO CITAS (id_usuario, fecha, seccion, comentarios) VALUES (7,'09/04/2022','VENTA','Me gustaria comprar x movil');
INSERT INTO CITAS (id_usuario, fecha, seccion, comentarios) VALUES (8,'10/04/2022','TALLER','Necesito una cita para reparar mi movil');
INSERT INTO CITAS (id_usuario, fecha, seccion, comentarios) VALUES (9,'15/04/2022','INFORMACION','Me gustaria pedir informacion sobre la reparacion de x movil');

--INSERTS EMPLEADO
INSERT INTO EMPLEADO(dni, id_usuario, departamento, salario, direccion, poblacion, provincia, pais, codigoPostal, codigoEmpleado) VALUES ('16523748L',1,'CEO',2000,'Calle Zaragoza','Zaragoza','Zaragoza','España','50006','EMP001');
INSERT INTO EMPLEADO(dni, id_usuario, departamento, salario, direccion, poblacion, provincia, pais, codigoPostal, codigoEmpleado) VALUES ('53118072D',2,'VENTAS',1800,'Calle Huesca','Zaragoza','Zaragoza','España','50006','EMP002');
INSERT INTO EMPLEADO(dni, id_usuario, departamento, salario, direccion, poblacion, provincia, pais, codigoPostal, codigoEmpleado) VALUES ('56845879E',3,'VENTAS',1800,'Calle Teruel','Zaragoza','Zaragoza','España','50006','EMP003');

--INSERTS CLIENTE 
INSERT INTO CLIENTE(dni, id_usuario, direccion, poblacion, provincia, pais, codigoPostal) VALUES ('23854790H',4,'Calle Zaragoza','Utebo','Zaragoza','España','50180');
INSERT INTO CLIENTE(dni, id_usuario, direccion, poblacion, provincia, pais, codigoPostal) VALUES ('21949610C',5,'Paseo de Teruel','Zaragoza','Zaragoza','España','50006');
INSERT INTO CLIENTE(dni, id_usuario, direccion, poblacion, provincia, pais, codigoPostal) VALUES ('48264357F',6,'Calle Utebo','Casetas','Zaragoza','España','50620');
INSERT INTO CLIENTE(dni, id_usuario, direccion, poblacion, provincia, pais, codigoPostal) VALUES ('43786604D',7,'Avenida Zaragoza','Mallen','Zaragoza','España','50550');
INSERT INTO CLIENTE(dni, id_usuario, direccion, poblacion, provincia, pais, codigoPostal) VALUES ('01174325Z',8,'Calle del Carmen','Zaragoza','Zaragoza','España','50006');

--INSERTS MOVILES
INSERT INTO MOVIL(referencia, marca, modelo, color, precioBase) VALUES ('696393655873955','Xiaomi','Redmi Note 11','Verde',179);
INSERT INTO MOVIL(referencia, marca, modelo, color, precioBase) VALUES ('853976752747537','Xiaomi','12 Pro','Negro',999);
INSERT INTO MOVIL(referencia, marca, modelo, color, precioBase) VALUES ('922293383544773','Xiaomi','12','Azul',799);
INSERT INTO MOVIL(referencia, marca, modelo, color, precioBase) VALUES ('652855892632958','Apple','Iphone 12','Negro',809);
INSERT INTO MOVIL(referencia, marca, modelo, color, precioBase) VALUES ('865385943624883','Apple','Iphone SE','Blanco Estrella',529);
INSERT INTO MOVIL(referencia, marca, modelo, color, precioBase) VALUES ('553779268695693','Apple','Iphone 13 Pro','Negro',1159);
INSERT INTO MOVIL(referencia, marca, modelo, color, precioBase) VALUES ('394736759986269','Samsung','S22','Negro',859);
INSERT INTO MOVIL(referencia, marca, modelo, color, precioBase) VALUES ('868645857485877','Samsung','Galaxy Z','Blanco',1599);
INSERT INTO MOVIL(referencia, marca, modelo, color, precioBase) VALUES ('732625542577658','Huawei','P50 Pro','Negro',1199);
INSERT INTO MOVIL(referencia, marca, modelo, color, precioBase) VALUES ('542537423726349','Huawei','P50 Pocket','Dorado',1599);

--INSERTS VENTAS
INSERT INTO VENTAS(dni_empleado, dni_cliente, referencia, imei, color, precioTotal) VALUES ('16523748L','01174325Z','696393655873955','508535487491666','Verde',189);
INSERT INTO VENTAS(dni_empleado, dni_cliente, referencia, imei, color, precioTotal) VALUES ('53118072D','43786604D','853976752747537','866004930049577','Negro',1064);
INSERT INTO VENTAS(dni_empleado, dni_cliente, referencia, imei, color, precioTotal) VALUES ('53118072D','48264357F','868645857485877','864682609575394','Blanco',1664);
INSERT INTO VENTAS(dni_empleado, dni_cliente, referencia, imei, color, precioTotal) VALUES ('56845879E','21949610C','922293383544773','354842717867797','Azul',894);


--INSERT EXTRAS
INSERT INTO EXTRAS(descripcion, precio) VALUES ('Protector de Pantalla',10);
INSERT INTO EXTRAS(descripcion, precio) VALUES ('Funda rigida',15);
INSERT INTO EXTRAS(descripcion, precio) VALUES ('Personalizacion de Funda',20);
INSERT INTO EXTRAS(descripcion, precio) VALUES ('Seguro Antirobos y Anticaidas',50);
INSERT INTO EXTRAS(descripcion, precio) VALUES ('Altavoz Bluetooth',30);
INSERT INTO EXTRAS(descripcion, precio) VALUES ('Powerbank',40);
INSERT INTO EXTRAS(descripcion, precio) VALUES ('Auriculares Bluetooth',100);

--INSERTS COMPLEMENTOS
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (1,1);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (2,2);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (2,4);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (3,2);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (3,4);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (4,1);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (4,2);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (4,3);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (4,4);
COMMIT;
END;
