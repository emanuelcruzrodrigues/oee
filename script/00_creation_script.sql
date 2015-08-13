drop table if exists usuarios;
create table usuarios(
  id numeric(10,0) not null primary key,
  nome varchar(100) not null,
  senha numeric(10),
  dt_criacao timestamp not null,
  dt_ultima_alteracao timestamp not null
);

drop sequence if exists sq_usuario;
create sequence sq_usuario;

insert into usuarios (id, nome, dt_criacao, dt_ultima_alteracao) values (-1, 'admin', now(), now());

drop table if exists equipamentos;
create table equipamentos(
  id numeric(10,0) not null primary key,
  codigo numeric(10) not null,
  nome varchar(100) not null,
  dm_situacao varchar(1) not null,
  dt_criacao timestamp not null,
  dt_ultima_alteracao timestamp not null
);

drop sequence if exists sq_equipamento;
create sequence sq_equipamento;

drop table if exists motivos_paradas;
create table motivos_paradas(
  id numeric(10,0) not null primary key,
  codigo numeric(10) not null,
  descricao varchar(100) not null,
  dm_tipo_parada varchar(3) not null,
  dm_situacao varchar(1) not null,
  dt_criacao timestamp not null,
  dt_ultima_alteracao timestamp not null
);

drop sequence if exists sq_motivo_parada;
create sequence sq_motivo_parada;
