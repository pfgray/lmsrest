create table enrollments (
    id            integer not null generated always as identity (start with 1, increment by 1),
    user_id       integer not null,
    course_id     integer not null,
    primary key (id),
    foreign key (user_id) references users(id),
    foreign key (course_id) references courses(id)
);

insert into courses (user_id, course_id) values (1, 1);
insert into courses (user_id, course_id) values (1, 2);
insert into courses (user_id, course_id) values (1, 3);
insert into courses (user_id, course_id) values (1, 4);