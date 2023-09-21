create table topicos(
    id bigint auto_increment,
    titulo varchar(255) not null,
    mensaje text not null,
    fecha_creacion timestamp not null,
    estado boolean not null,
    autor varchar(100) not null,
    curso varchar(255) not null,
    primary key(id)
);