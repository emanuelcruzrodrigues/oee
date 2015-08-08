drop table if exists equipamentos;

create table equipamentos(
  id numeric(10,0) not null primary key,
  nome varchar(100) not null,
  dm_situacao varchar(1) not null,
  dt_criacao timestamp not null,
  dt_ultima_alteracao timestamp not null,
  ip_ultima_alteracao varchar(50) not null
);