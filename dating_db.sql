drop database datingdb;
drop user dating;

create user dating with password 'password';
create database datingdb with template=template0 owner=dating;
\connect datingdb;

alter default privileges grant all on tables to dating;
alter default privileges grant all on sequences to dating;

create table dating_users(
    username varchar(20) primary key not null,
    password text not null,

    display_name varchar(100) not null,
    bio text not null
);

create table dating_likes(
    username_a varchar(20) not null,
    username_b varchar(20) not null,
    foreign key(username_a)
        references dating_users(username)
        on delete cascade,
    foreign key(username_b)
        references dating_users(username)
        on delete cascade,
    primary key (username_a, username_b)
);
