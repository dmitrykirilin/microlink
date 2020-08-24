drop table if exists links;

create table if not exists links(
    id int8 primary key,
    text varchar not null
);

insert into links values
(100500, 'https://www.yandex.ru'),
(100501, 'https://www.wow.ru'),
(100502, 'https://www.mail.ru')