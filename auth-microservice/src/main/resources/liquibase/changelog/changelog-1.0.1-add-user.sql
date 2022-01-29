--liquibase formatted sql

--changeset author:anastasia:add-users
INSERT INTO users (email, first_name, last_name, password)
VALUES ('admin@mail.ru', 'admin', 'adminov', '$2a$12$l20gytc8WUSsprr.lIb9PesOFpU8/lj.tkdEHhmgPZnMFGqlTgrZa'),
       ('user@mail.ru', 'user', 'userov', '$2a$12$afl.c0.qb6l9kwVcq0m2eeDT5fl3nNzynSXcz4FQY2m9xqQxWcxgq');

-- rollback delete from users where email='admin@mail.ru' or email='user@mail.ru'

--changeset author:anastasia:add-users-roles
INSERT INTO user_roles(user_id, role_id)
VALUES(1, 1), (2, 2);

-- rollback delete from user_roles where user_id=1 or user_id=2
