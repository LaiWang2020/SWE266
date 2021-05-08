DROP DATABASE IF EXISTS bank;
create DATABASE bank;
USE bank;

CREATE TABLE account (
    id int NOT NULL UNIQUE ,
    username varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL ,
    money int
);

