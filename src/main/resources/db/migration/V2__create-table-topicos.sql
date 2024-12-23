create table topicos (
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(100) not null unique,
    fechaDeCreacion datetime not null default current_timestamp, -- Fecha automática
    status varchar(100) not null,
    autor varchar(100) not null,
    curso varchar(100) not null,

    primary key(id)
);
