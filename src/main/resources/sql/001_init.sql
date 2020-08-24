begin transaction;
drop table if exists links CASCADE;

drop sequence if exists link_sequence;

create sequence link_sequence start with 100000;

create table links (
    id bigint primary key default nextval('"link_sequence"'),
    text text not null);
end transaction;