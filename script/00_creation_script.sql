drop table if exists usuarios;

create table usuarios(
  id numeric(10,0) not null primary key,
  nome varchar(100) not null,
  senha numeric(10),
  dt_criacao timestamp not null,
  dt_ultima_alteracao timestamp not null,
  ip_ultima_alteracao varchar(50) not null
);

create sequence sq_usuario;

insert into usuarios (id, nome, dt_criacao, dt_ultima_alteracao, ip_ultima_alteracao) values (-1, 'admin', now(), now(), '127.0.0.1');

drop table if exists equipamentos;

create table equipamentos(
  id numeric(10,0) not null primary key,
  nome varchar(100) not null,
  dm_situacao varchar(1) not null,
  dt_criacao timestamp not null,
  dt_ultima_alteracao timestamp not null,
  ip_ultima_alteracao varchar(50) not null
);