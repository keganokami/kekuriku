CREATE EXTENSION IF  NOT EXISTS pgcrypto WITH SCHEMA public;

create table admins (
  user_id varchar(255) primary key,
  password varchar(255) not null,
  email_address varchar(255) not null,
  role VARCHAR(100) NOT NULL,
  ${commonColumns}
);
insert into admins(user_id, password, email_address, role) values ('admin001', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'admin', 'test@gmail.com');

create table tmp_test (
  user_id varchar(255) primary key,
  password varchar(255) not null,
  email_address varchar(255) not null,
  role VARCHAR(100) NOT NULL,
  ${commonColumns}
);

insert into tmp_test(user_id, password, email_address, role) values ('admin001', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'admin', 'test@gmail.com');
