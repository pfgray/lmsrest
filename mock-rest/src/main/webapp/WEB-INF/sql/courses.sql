create table courses (
    id         integer not null generated always as identity (start with 1, increment by 1),
    guid       varchar(20) NOT NULL,
    name       varchar(50) NOT NULL
    primary key (id)
);

insert into courses (guid, name) values ('COMPSCI_101_2014_1', 'Computer Science 101');
insert into courses (guid, name) values ('ENGLISH_101_2014_1', 'English 101');
insert into courses (guid, name) values ('MATHMTC_101_2014_1', 'Mathematics 101');
insert into courses (guid, name) values ('HISTORY_101_2014_1', 'History 101');



