drop table if exists users;

create table users(
    id bigint primary key,
    email varchar(50) not null primary key,
    password varchar(50) not null,
    enabled boolean not null,
    role varchar(50) not null
);