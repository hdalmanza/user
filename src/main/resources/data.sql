 DROP TABLE IF EXISTS testdb;

create table phone (id_phone bigint not null, city_code varchar(255), country_code varchar(255), number varchar(255), primary key (id_phone))
create table user (id varchar(255) not null, created varchar(255), email varchar(255), is_active boolean, last_login varchar(255), modified varchar(255), name varchar(255), password varchar(255), token varchar(255), primary key (id))
create table user_phones (user_id varchar(255) not null, phones_id_phone bigint not null)
alter table user_phones drop constraint if exists UK_mpbfx9kxhhoukcgjihsr1rqpl
alter table user_phones add constraint UK_mpbfx9kxhhoukcgjihsr1rqpl unique (phones_id_phone)
create sequence _sequence start with 1 increment by 1
alter table user_phones add constraint FKku6mpqxool208p88vyyl9rq2t foreign key (phones_id_phone) references phone
alter table user_phones add constraint FKdfslj94kbjqvp71kjjc36iije foreign key (user_id) references user