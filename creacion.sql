Create table usuario
(
  id_usuario varchar(20) not null,
  clave varchar(10) not null,
  autorizado boolean not null,
  solicitadobaja boolean,

  primary key(id_usuario)
);

Create table inversor
(
  id_usuario varchar(20) not null,
  nombre varchar(60) not null,
  saldo float not null default 0,
  dni varchar(9) not null,
  direccion varchar(50),
  telefono varchar(9),

  primary key (id_usuario),

  foreign key (id_usuario) references usuario (id_usuario)
   on delete cascade on update cascade
);


Create table empresa
(
  id_usuario varchar(20) not null,
  nombrecomercial varchar(60) not null,
  cif varchar(9) not null,
  saldo float default 0,
  saldobloqueado float default 0,
  participacionesbloqueadas integer default 0, 
  direccion varchar(50),
  telefono varchar(9),
  
  primary key (id_usuario),

  foreign key (id_usuario) references usuario (id_usuario)
   on delete cascade on update cascade
);

Create table regulador
(
  id_usuario varchar(20) not null,
  comision float ,
  saldo float default 0,

  primary key (id_usuario),

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
  numParticipaciones integer not null,

  primary key (usuario, empresa),

  foreign key (usuario) references empresa (id_usuario)
   on delete cascade on update cascade,
  foreign key (empresa) references empresa (id_usuario)
   on delete cascade on update cascade
);

Create table ofertaVenta
(
  usuario varchar(20) not null,
  empresa varchar(20) not null,
  fecha timestamp not null,
  numParticipaciones integer not null,
  precio float not null,

  primary key (usuario, fecha),
  
  foreign key (usuario) references usuario (id_usuario)
   on delete cascade on update cascade,
  foreign key (empresa) references empresa (id_usuario)
   on delete cascade on update cascade
);

Create table anuncioBeneficios
(
  empresa varchar(20) not null,
  fechaPago timestamp not null,
  fechaAnuncio timestamp,
  numeroparticipaciones integer, 
  importeParticipacion float,
  solicitadobaja boolean,

  primary key (fechaPago, empresa),
  
  foreign key (empresa) references empresa (id_usuario)
   on delete cascade on update cascade
);

Create table emitirParticipaciones
(
  empresa varchar(20) not null,
  fechaEmision timestamp not null,
  numeroParticipaciones integer not null,

  primary key (fechaEmision, empresa),
  
  foreign key (empresa) references empresa (id_usuario)
   on delete cascade on update cascade
);

Create table historial
(
  empresa varchar(20) not null,
  usuario varchar(20) not null,
  fecha timestamp not null,
  cantidad integer not null,
  precio float not null,
  tipo varchar(20) not null,

  primary key (fecha),

  foreign key (empresa) references empresa (id_usuario)
   on delete cascade on update cascade,
  foreign key (usuario) references usuario (id_usuario)
   on delete cascade on update cascade
);