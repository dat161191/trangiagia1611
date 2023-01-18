DROP DATABASE IF EXISTS sprint_1;
CREATE DATABASE sprint_1;
USE sprint_1;

CREATE TABLE city(
id_city bigint PRIMARY KEY AUTO_INCREMENT,
name_city VARCHAR(45)
);

CREATE TABLE wards(
id_wards bigint PRIMARY KEY AUTO_INCREMENT,
id_city bigint NOT NULL,
name_wards VARCHAR(45),
FOREIGN KEY(id_city) REFERENCES city(id_city)
);

CREATE TABLE district(
id_district bigint PRIMARY KEY AUTO_INCREMENT,
id_wards bigint NOT NULL,
name_district VARCHAR(45),
FOREIGN KEY(id_wards) REFERENCES wards(id_wards)
);

CREATE TABLE address(
id_address bigint PRIMARY KEY AUTO_INCREMENT,
number_address VARCHAR(45),
id_district bigint NOT NULL,
FOREIGN KEY(id_district) REFERENCES district(id_district)
);

CREATE TABLE image_list(
id_image_list bigint PRIMARY KEY AUTO_INCREMENT,
url VARCHAR(255)
);

CREATE TABLE demand_type(
id_demand_type bigint PRIMARY KEY AUTO_INCREMENT,
name_demand_type VARCHAR(45)
);

CREATE TABLE status_post(
id_status_post bigint PRIMARY KEY AUTO_INCREMENT,
name_status_post VARCHAR(45)
);

CREATE TABLE land_type(
id_land_type bigint PRIMARY KEY AUTO_INCREMENT,
name_lane_type VARCHAR(45)
);

create table `account` (
id_account bigint primary key auto_increment,
username_account varchar(50) not null,
encrypt_password varchar(255) not null,
flag_deleted boolean not null
);
create table `role` (
id_role bigint primary key auto_increment,
name_role varchar(50) not null
);
create table account_role (
id_account_role bigint primary key auto_increment,
id_account bigint not null,
foreign key(id_account)references `account`(id_account),
id_role bigint not null,
foreign key(id_role)references `role`(id_role)
);
CREATE TABLE `division`(
	id_division BIGINT primary key auto_increment,
    name_division varchar(50)
);
CREATE TABLE employee (
    id_employee BIGINT PRIMARY KEY AUTO_INCREMENT,
    code_employee VARCHAR(10),
    id_role BIGINT NOT NULL,
    FOREIGN KEY (id_role)
        REFERENCES `role` (id_role),
    name_employee VARCHAR(50),
    date_of_birth VARCHAR(10),
    gender BOOLEAN,
    phone_employee VARCHAR(10),
    email_employee VARCHAR(50),
    address VARCHAR(50),
    flag_deleted BOOLEAN DEFAULT FALSE,
    id_division BIGINT NOT NULL,
    FOREIGN KEY (id_division)
        REFERENCES `division` (id_division),
    id_account BIGINT NOT NULL,
    FOREIGN KEY (id_account)
        REFERENCES `account` (id_account)
);

create table customer(
id_customer BIGINT primary key auto_increment,
name_customer varchar(45) not null,
phone_customer varchar(45) not null,
email_customer varchar(45),
address_customer varchar(45),
id_card_customer varchar(45) not null,
code_customer varchar(45) not null,
gender_customer int,
flag_deleted BOOLEAN DEFAULT FALSE,
approval_customer BOOLEAN,
id_role BIGINT NOT NULL,
FOREIGN KEY (id_role)
REFERENCES `role` (id_role),
id_account BIGINT NOT NULL,
FOREIGN KEY (id_account)
REFERENCES `account` (id_account)
);

create table phone_customer(
id_phone bigint primary key auto_increment,
phone_number_customer varchar(45) not null,
id_customer bigint not null,
FOREIGN KEY (id_customer)REFERENCES customer(id_customer)
);


CREATE TABLE notification_table (
    id_notification_table BIGINT PRIMARY KEY AUTO_INCREMENT,
    posting_date DATE NOT NULL,
    title VARCHAR(45) NOT NULL,
    content VARCHAR(255) NOT NULL,
    flag_deleted BOOLEAN NOT NULL
);

create table detail_form (
id_detail_form bigint primary key auto_increment,
url_detail_form varchar(255)
);

create table data_form (
id_data_form bigint primary key auto_increment,
code_data_form varchar(255),
content_data_form varchar(255),
id_detail_form bigint NOT NULL,
flag_deleted BOOLEAN DEFAULT FALSE,
foreign key (id_detail_form) references detail_form(id_detail_form)
);

CREATE TABLE post(
id_post BIGINT PRIMARY KEY AUTO_INCREMENT,
name_post VARCHAR(255) NOT NULL,
area double NOT NULL,
note_post VARCHAR(500),
description_post VARCHAR(500),
price double NOT NULL,
flag_deleted boolean DEFAULT FALSE,
approval boolean DEFAULT FALSE,
date_creation DATETIME DEFAULT CURRENT_TIMESTAMP,
id_status_post BIGINT NOT NULL,
id_address BIGINT NOT NULL,
id_demand_type BIGINT NOT NULL,
id_land_type BIGINT NOT NULL,
id_image_list BIGINT NOT NULL,
id_customer BIGINT NOT NULL,
FOREIGN KEY(id_status_post) REFERENCES status_post(id_status_post),
FOREIGN KEY(id_address) REFERENCES address(id_address),
FOREIGN KEY(id_demand_type) REFERENCES demand_type(id_demand_type),
FOREIGN KEY(id_land_type) REFERENCES land_type(id_land_type),
FOREIGN KEY(id_image_list) REFERENCES image_list(id_image_list),
FOREIGN KEY(id_customer) REFERENCES customer(id_customer)
);

