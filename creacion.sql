--regulador
Insert into usuario (id_usuario, clave, autorizado) values ('Regulador', '123', TRUE);

--insert de usuarios que seran inversores
Insert into usuario (id_usuario, clave, autorizado) values ('Elena', '432', TRUE);
Insert into usuario (id_usuario, clave, autorizado) values ('Fernando', '132', TRUE);
Insert into usuario (id_usuario, clave, autorizado) values ('PabloD', '332', FALSE);

--insert de usuarios que seran empresas
Insert into usuario (id_usuario, clave, autorizado) values ('Lenovo', '478', TRUE);
Insert into usuario (id_usuario, clave, autorizado) values ('HP', '778', TRUE);
Insert into usuario (id_usuario, clave, autorizado) values ('Pepephone', '878', TRUE);
Insert into usuario (id_usuario, clave, autorizado) values ('Pereiro', '477', FALSE);


--inversores
Insert into inversor(id_usuario, nombre, dni, direccion, Telefono, Saldo) values('Elena', 'Elena La mas Guapa', '33453214T', 'Santiago de Compostuela', '8877663', 15000);
Insert into inversor(id_usuario, nombre, dni, direccion, Telefono, Saldo) values('Fernando', 'Fernando el mas fuelte', '34658224L', 'Santiago de Compostuela', '4879603', 10000);
Insert into inversor(id_usuario, nombre, dni, direccion, Telefono, Saldo) values('PabloD', 'Pablo Diaz', '23463718W', 'Santiago de COompostuela', '8899063', 1000);
--empresas
Insert into empresa(id_usuario, nombrecomercial, cif, direccion, Telefono, Saldo) values('Lenovo', 'Lenovo Computers', '1111114T', 'Santiago de Compostuela', '8275663', 100000);
Insert into empresa(id_usuario, nombrecomercial, cif, direccion, Telefono, Saldo) values('HP', 'HP tecnologies', '1739502D', 'Santiago de Compostuela', '9361738', 370000);
Insert into empresa(id_usuario, nombrecomercial, cif, direccion, Telefono, Saldo) values('Pepephone', 'Los telefonos del pepe', '7483901C', 'Santiago de COompostuela', '8291023', 95080);
Insert into empresa(id_usuario, nombrecomercial, cif, direccion, Telefono, Saldo) values('Pereiro', 'As mellores do mundo enteiro', '0172839S', 'Santiago de COompostuela', '7361291', 1000000);

--regulador
Insert into regulador(id_usuario) values ('Regulador');

Insert into historial(empresa, usuario, fecha, cantidad, precio, tipo) values('Lenovo', 'Lenovo', '2002-02-25', 300, 12, 'Emision');
Insert into historial(empresa, usuario, fecha, cantidad, precio, tipo) values('Lenovo', 'Lenovo', '2002-03-25', 270, 11, 'Emision');
Insert into historial(empresa, usuario, fecha, cantidad, precio, tipo) values('HP', 'Lenovo', '2002-04-25', 200, 15, 'Compra');
Insert into historial(empresa, usuario, fecha, cantidad, precio, tipo) values('Lenovo', 'HP', '2002-05-25', 300, 12, 'Venta');
