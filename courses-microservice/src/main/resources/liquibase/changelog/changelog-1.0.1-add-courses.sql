--liquibase formatted sql

--changeset anastasia:add-courses
insert into courses (name, description, price)
values ('Machine learning', 'Наши ИИ умнее наших студентов', 24000),
       ('Media studies', 'Посмотрим вместе Киселева', 16000),
       ('Business and Finance', 'Рептилоид Цукерберг или нет?', 20000),
       ('History of Art', 'Кто-то на это тоже идет', 12000);

-- rollback delete from courses where name='Machine learning' or name='Media studies' or name='Business and Finance' or name='History of Art'