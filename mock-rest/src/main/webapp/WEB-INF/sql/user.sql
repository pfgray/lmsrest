create table users (
    id         varchar(20) NOT NULL,
    username   varchar(20) NOT NULL,
    first_name varchar(20),
    last_name  varchar(20),
    mi         varchar(20),
    primary key (id)
);


insert into users (id, username, first_name, last_name, mi) values ('1', 'gwashington', 'George', 'Washington', 'R');
insert into users (id, username, first_name, last_name, mi) values ('2', 'jadams', 'John', 'Adams', 'Q');
insert into users (id, username, first_name, last_name)     values ('3', 'tjefferson', 'Thomas', 'Jefferson');
