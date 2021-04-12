Create table usuario
(
  id_usuario varchar(20) not null primary key,
  clave varchar(10) not null,
  cuenta float
);

Create table inversor
(
  id_usuario varchar(20) not null primary key,
  nombre varchar(60) not null,
  dni varchar(9) not null,
  direccion varchar(50),
  telefono varchar(9),
  autorizado boolean,
  foreign key (id_usuario) references usuario (id_usuario)
   on delete cascade on update cascade
);


Create table empresa
(
  id_usuario varchar(20) not null primary key,
  nombrecomercial varchar(60) not null,
  cif varchar(9) not null,
  direccion varchar(50),
  telefono varchar(9),
  autorizado boolean,
  foreign key (id_usuario) references usuario (id_usuario)
   on delete cascade on update cascade
);

Create table regulador
(
  id_usuario varchar(20) not null primary key,
  foreign key (id_usuario) references usuario (id_usuario)
   on delete cascade on update cascade
);

Create table participacionesInversor
(
  usuario varchar(20) not null,
  empresa varchar(20) not null,
  numParticipaciones integer not null,

  primary key (usuario, empresa),
  
  foreign key (usuario) references inversor (id_usuario)
   on delete cascade on update cascade,
  foreign key (empresa) references empresa (id_usuario)
   on delete cascade on update cascade
);

Create table participacionesEmpresa
(
  usuario varchar(20) not null,
  empresa varchar(20) not null,

  primary key (usuario, empresa),
  
  numParticipaciones integer not null,
  foreign key (usuario) references empresa (id_usuario)
   on delete cascade on update cascade,
  foreign key (empresa) references empresa (id_usuario)
   on delete cascade on update cascade
);
Create table ofertaVenta
(
  usuario varchar(20) not null,
  empresa varchar(20) not null,
  fecha date not null,
  numParticipaciones integer not null,
  precio double precision not null,

  primary key (usuario, fecha),
  
  foreign key (usuario) references usuario (id_usuario)
   on delete cascade on update cascade,
  foreign key (empresa) references empresa (id_usuario)
   on delete cascade on update cascade
);

Create table anuncioBeneficios
(
  empresa varchar(20) not null,
  fechaPago date not null,
  fechaAnuncio date,
  importeParticipacion double precision not null,

  primary key (fechaPago, empresa),
  
  foreign key (empresa) references empresa (id_usuario)
   on delete cascade on update cascade
);

Create table emitirParticipaciones
(
  empresa varchar(20) not null,
  fechaEmision date not null,

  primary key (fechaEmision, empresa),
  
  numeroParticipaciones integer not null,
  foreign key (empresa) references empresa (id_usuario)
   on delete cascade on update cascade
);
