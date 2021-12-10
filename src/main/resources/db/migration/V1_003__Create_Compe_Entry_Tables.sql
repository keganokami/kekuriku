create table entries (
  id CHAR(36) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  name_kana VARCHAR(255) NOT NULL,
  sex CHAR(1) NOT NULL,
  phone_number VARCHAR(30) NOT NULL,
  number VARCHAR(10),
  ${commonColumns}
);

create table compe_entry_management (
  id CHAR(36) PRIMARY KEY DEFAULT public.gen_random_uuid(),
  entry_id VARCHAR(255) NOT NULL REFERENCES entries,
  event_id VARCHAR(255) NOT NULL,
  compe_id VARCHAR(255) NOT NULL REFERENCES compe,
  round_id VARCHAR(255) NOT NULL,
  admin_id VARCHAR(255) NOT NULL,
  lane_number INTEGER,
  program_number INTEGER,
  application_record_int INTEGER,
  application_record_display VARCHAR(30),
  result_record_int INTEGER,
  result_record_display VARCHAR(30),
  order_of_arrival INTEGER,
  wind VARCHAR(10),
  can_go_to_next CHAR(1),
  ${commonColumns}
);