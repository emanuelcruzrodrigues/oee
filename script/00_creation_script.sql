drop table if exists equipment;

create table equipment(
  id numeric(10,0) not null primary key,
  name varchar(100) not null,
  dt_creation timestamp not null,
  dt_last_update timestamp not null,
  ip_last_update varchar(50) not null
);