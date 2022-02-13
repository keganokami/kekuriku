create table compe (
  compe_id varchar(255) primary key,
  admin_id varchar(255) REFERENCES admins,
  compe_name varchar(255) NOT NULL,
  compe_dates VARCHAR(100) NOT NULL,
  compe_place VARCHAR(100) NOT NULL,
  compe_guidelines_file BYTEA,
  compe_fee_type CHAR(1) NOT NULL,
  compe_participation_fee INTEGER,
  setting_max_participation boolean NOT NULL,
  compe_max_participation INTEGER NOT NULL,
  compe_event JSONB NOT NULL,
  ${commonColumns}
);