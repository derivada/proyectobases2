--regulador como usuario
Insert into usuario (id_usuario, clave, autorizado) values ('Regulador', '123', TRUE);

--regulador como regulador
Insert into regulador(id_usuario) values ('Regulador');

--insert de usuarios que seran inversores
Insert into usuario (id_usuario, clave, autorizado) values ('Elena', '432', TRUE);
Insert into usuario (id_usuario, clave, autorizado) values ('Fernando', '132', TRUE);
Insert into usuario (id_usuario, clave, autorizado) values ('PabloD', '332', FALSE);
Insert into usuario (id_usuario, clave, autorizado) values ('PabloP', '456', FALSE);
Insert into usuario (id_usuario, clave, autorizado) values ('Miguel', '789', TRUE);

--inversores
Insert into inversor(id_usuario, nombre, dni, direccion, Telefono, Saldo) values('Elena', 'Elena Fernandez', '33453214T', 'Santiago de Compostela', '8877663', 15000);
Insert into inversor(id_usuario, nombre, dni, direccion, Telefono, Saldo) values('Fernando', 'Fernando Fraile', '34658224L', 'Santiago de Compostela', '4879603', 10000);
Insert into inversor(id_usuario, nombre, dni, direccion, Telefono, Saldo) values('PabloD', 'Pablo Diaz', '23463718W', 'Santiago de Compostela', '8899063', 1000);
Insert into inversor(id_usuario, nombre, dni, direccion, Telefono, Saldo) values('PabloP', 'Pablo Perez', '46296580A', 'Santiago de Compostela', null , 400);
Insert into inversor(id_usuario, nombre, dni, direccion, Telefono, Saldo) values('Miguel', 'Miguel Mendez', '25634899Z', 'Santiago de Compostela', '900800700', 50000);

--insert de usuarios que seran empresas
Insert into usuario (id_usuario, clave, autorizado) values ('Lenovo', '478', TRUE);
Insert into usuario (id_usuario, clave, autorizado) values ('HP', '778', TRUE);
Insert into usuario (id_usuario, clave, autorizado) values ('Pepephone', '878', TRUE);
Insert into usuario (id_usuario, clave, autorizado) values ('Pereiro', '477', FALSE);
Insert into usuario (id_usuario, clave, autorizado) values ('Kia', '999', TRUE);

--empresas
Insert into empresa(id_usuario, nombrecomercial, cif, direccion, Telefono, Saldo) values('Lenovo', 'Lenovo Computers', '1111114T', 'Santiago de Compostela', '8275663', 100000);
Insert into empresa(id_usuario, nombrecomercial, cif, direccion, Telefono, Saldo) values('HP', 'HP Technologies', '1739502D', 'Santiago de Compostela', '9361738', 370000);
Insert into empresa(id_usuario, nombrecomercial, cif, direccion, Telefono, Saldo) values('Pepephone', 'Pepephone S.L.', '7483901C', 'Santiago de Compostela', '8291023', 95080);
Insert into empresa(id_usuario, nombrecomercial, cif, direccion, Telefono, Saldo) values('Pereiro', 'As mellores do mundo enteiro', '0172839S', 'Santiago de Compostela', '7361291', 1000000);
Insert into empresa(id_usuario, nombrecomercial, cif, direccion, Telefono, Saldo) values('Kia', 'KIA Motors', '224466Z', 'Santiago de Compostela', '7361291', 5000000);

--carteras de participaciones
--empresas
insert into participacionesEmpresa(usuario, empresa, numparticipaciones) values('Lenovo', 'Lenovo', 1080);
insert into participacionesEmpresa(usuario, empresa, numparticipaciones) values('HP', 'HP', 500);
insert into participacionesEmpresa(usuario, empresa, numparticipaciones) values('Pepephone', 'Pepephone', 250);
insert into participacionesEmpresa(usuario, empresa, numparticipaciones) values('Kia', 'Kia', 675);
--inversores
Insert into participacionesinversor(usuario,empresa, numparticipaciones) values ('Elena','Lenovo',5);
Insert into participacionesinversor(usuario,empresa, numparticipaciones) values ('Elena','Pepephone',10);
Insert into participacionesinversor(usuario,empresa, numparticipaciones) values ('Fernando','Pereiro',20);
Insert into participacionesinversor(usuario,empresa, numparticipaciones) values ('Miguel','Lenovo',15);





