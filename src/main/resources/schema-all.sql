DROP TABLE people IF EXISTS;

CREATE TABLE people  (
                         person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
                         first_name VARCHAR(20),
                         last_name VARCHAR(20)
);


CREATE TABLE CUSTOMER (
                          ID BIGINT IDENTITY PRIMARY KEY,
                          NAME VARCHAR(45),
                          CREDIT FLOAT
);