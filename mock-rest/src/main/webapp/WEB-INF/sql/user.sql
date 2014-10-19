create table users (
    id         integer not null generated always as identity (start with 1, increment by 1),
    username   varchar(20) NOT NULL,
    first_name varchar(20) NOT NULL,
    last_name  varchar(20) NOT NULL,
    mi         varchar(20),
    password   varchar(500) NOT NULL,
    primary key (id)
);

insert into users (username, first_name, last_name, password, mi) values ('gwashington', 'George', 'Washington', 'password', 'R');
insert into users (username, first_name, last_name, password, mi) values ('jadams', 'John', 'Adams', 'password', 'Q');
insert into users (username, first_name, last_name,  password)    values ('tjefferson', 'Thomas', 'Jefferson', 'password');
