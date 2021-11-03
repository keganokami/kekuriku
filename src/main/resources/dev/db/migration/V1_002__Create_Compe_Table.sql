create table compe (
compe_id varchar(255) primary key,
  admin_id varchar(255) REFERENCES admins NOT NULL,
  compe_name varchar(255) NOT NULL,
  compe_dates VARCHAR(100) NOT NULL,
  compe_place VARCHAR(100) NOT NULL,
  compe_guidelines_file BYTEA,
  compe_event JSONB NOT NULL,
  ${commonColumns}
);