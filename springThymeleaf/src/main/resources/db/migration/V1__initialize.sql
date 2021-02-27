create table students (
    id                      bigserial,
    name                    varchar(255) not null,
    age                     int,
    primary key (id)
);

insert into students (name, age)
values
('Bob', 25),
('John', 35),
('Tom', 25),
('Sara', 35),
('July', 25),
('Sam', 35),
('Jack', 45);
