CREATE EXTENSION IF  NOT EXISTS pgcrypto WITH SCHEMA public;

create table admins (
  userId varchar(255) primary key,
  password varchar(255) not null,
  role VARCHAR(100) NOT NULL,
  ${commonColumns}
);
insert into admins(userId, password, role) values ('admin001', 'pass', 'admin');