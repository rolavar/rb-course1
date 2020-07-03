drop table if exists PERSON CASCADE;
drop table if exists ACCOUNT CASCADE;
CREATE TABLE PERSON(id bigint identity primary key, firstName varchar(255), lastName varchar(255), taxId varchar(255));
CREATE TABLE ACCOUNT(id bigint identity primary key, balance bigint, creditCardNumber varchar(255), date timestamp, personId bigint, foreign key (personId) references PERSON(id))