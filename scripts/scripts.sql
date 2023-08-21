CREATE TABLE PHONE(id_phone bigint auto_increment, number bigint, city_code integer, country_code varchar(255));
ALTER TABLE PHONE ADD PRIMARY KEY (ID_PHONE);
CREATE TABLE USERS(id varchar(255) primary key, name varchar(255), email varchar(255), password varchar(255), created TIMESTAMP, last_login TIMESTAMP, token varchar(255), is_active INTEGER);
ALTER TABLE PHONE ADD ID VARCHAR(255);
ALTER TABLE PHONE ADD FOREIGN KEY (ID) REFERENCES USERS (ID);
