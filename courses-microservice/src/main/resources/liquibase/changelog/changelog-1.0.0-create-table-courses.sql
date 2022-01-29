--liquibase formatted sql

--changeset author:anastasia:create-courses-table
create table courses
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR (100) UNIQUE NOT NULL,
    description VARCHAR (300),
    price       FLOAT
);

-- rollback DROP TABLE courses;