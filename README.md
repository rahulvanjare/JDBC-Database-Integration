create database user_db;

use user_db;

create table users (
	id int primary key auto_increment,
    name varchar(100),
    email varchar(100)
    );
