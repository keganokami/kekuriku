CREATE EXTENSION IF  NOT EXISTS pgcrypto WITH SCHEMA public;

create table admins (
  id serial primary key,
  name varchar(255) not null,
  password varchar(255) not null,
  ${commonColumns}
);
insert into admins(name, password) values ('admin', 'pass');