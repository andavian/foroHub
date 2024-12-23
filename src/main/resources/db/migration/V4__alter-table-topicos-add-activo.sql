alter table topicos add column activo tinyint;
update topicos set activo = 1;
alter table topicos modify activo tinyint not null;


