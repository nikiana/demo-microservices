--liquibase formatted sql

--changeset author:anastasia:create-users-table
CREATE TABLE users (
    id                  SERIAL PRIMARY KEY,
    first_name          TEXT,
    last_name           TEXT,
    password            TEXT NOT NULL,
    email               TEXT UNIQUE NOT NULL,
    enabled             BOOLEAN DEFAULT TRUE
);
--rollback DROP TABLE users

--changeset author:anastasia:create-roles-table
CREATE TABLE roles (
    id                  SERIAL PRIMARY KEY,
    name                TEXT UNIQUE
);
--rollback DROP TABLE roles

--changeset author:anastasia:add-default-value-to-roles-table
INSERT INTO roles (name) VALUES ('ADMIN'), ('USER');
--rollback DELETE FROM roles WHERE name='ADMIN' or name='USER'

--changeset author:anastasia:create-user_roles-table
CREATE TABLE user_roles (
    user_id             BIGINT REFERENCES users(id),
    role_id             BIGINT REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);
--rollback DROP TABLE user_roles