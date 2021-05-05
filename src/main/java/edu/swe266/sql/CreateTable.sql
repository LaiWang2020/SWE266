CREATE TABLE account (
    id UNIQUE BIGINT(20),
    username UNIQUE varchar(255),
    password varchar(255),
    money int
);