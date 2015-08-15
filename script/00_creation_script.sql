----------------------------------------------------------------------------

drop table if exists usuarios;
drop sequence if exists sq_usuario;

drop table if exists apontamentos_quantidades;
drop sequence if exists sq_apontamento_quantidade;

drop table if exists ordens_producoes;
drop sequence if exists sq_ordem_producao;

drop table if exists equipamentos;
drop sequence if exists sq_equipamento;

drop table if exists motivos_paradas;
drop sequence if exists sq_motivo_parada;


----------------------------------------------------------------------------

create table usuarios(
  id numeric(10,0) not null primary key,
  nome varchar(100) not null,
  senha numeric(10),
  dt_criacao timestamp not null,
  dt_ultima_alteracao timestamp not null
);
create sequence sq_usuario;
insert into usuarios (id, nome, dt_criacao, dt_ultima_alteracao) values (-1, 'admin', now(), now());

----------------------------------------------------------------------------

create table equipamentos(
  id numeric(10,0) not null primary key,
  codigo numeric(10) not null,
  nome varchar(100) not null,
  dm_situacao varchar(1) not null,
  dt_criacao timestamp not null,
  dt_ultima_alteracao timestamp not null
);
create unique index idx_uk_equipamentos on equipamentos(codigo);
create sequence sq_equipamento;

----------------------------------------------------------------------------

create table motivos_paradas(
  id numeric(10,0) not null primary key,
  codigo numeric(10) not null,
  descricao varchar(100) not null,
  dm_tipo_parada varchar(3) not null,
  dm_situacao varchar(1) not null,
  dt_criacao timestamp not null,
  dt_ultima_alteracao timestamp not null
);
create unique index idx_uk_motivos_paradas on motivos_paradas(codigo);
create sequence sq_motivo_parada;

----------------------------------------------------------------------------

create table ordens_producoes(
	id numeric(10) not null primary key,
	codigo numeric(10) not null,
	descricao varchar(100) not null,
	id_equipamento numeric(10) not null,
	unidades_por_minuto numeric(16,6) not null,
	dm_Situacao varchar(1) not null,
	dt_Criacao timestamp not null,
	dt_ultima_alteracao timestamp not null
);
create unique index idx_uk_ordens_producoes on ordens_producoes(codigo);
alter table ordens_producoes add constraint fk_orpr_equi foreign key (id_equipamento) references equipamentos(id);
create sequence sq_ordem_producao;

----------------------------------------------------------------------------

create table apontamentos_quantidades(
	id numeric(10) not null primary key,
	id_ordem_producao numeric(10) not null,
	dt_hr timestamp not null,
	quantidade numeric(16,6) not null,
	dm_qualidade varchar(1) not null,
	dt_criacao timestamp not null,
	dt_ultima_alteracao timestamp not null
);
create sequence sq_apontamento_quantidade;
alter table apontamentos_quantidades add constraint fk_apqu_orpr foreign key(id_ordem_producao) references ordens_producoes(id);

----------------------------------------------------------------------------
