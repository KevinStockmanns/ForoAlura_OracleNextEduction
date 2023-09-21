create table respuestas(
    id bigint auto_increment,
    fecha_creacion timestamp not null,
    respuesta text not null,
    autor varchar(100) not null,
    estado boolean not null,
    topico_id bigint not null,
    primary key(id),
    FOREIGN KEY (topico_id) REFERENCES topicos(id)
);