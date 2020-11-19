drop database datingdb;
drop user dating;

create user dating with password 'password';
create database datingdb with template=template0 owner=dating;
\connect datingdb;

alter default privileges grant all on tables to dating;
alter default privileges grant all on sequences to dating;

create table dating_users(
    user_id integer primary key not null,
    username varchar(20) unique not null,
    password text not null,

    display_name varchar(100) not null,
    bio text not null
);

create sequence dating_users_seq increment 1 start 1;