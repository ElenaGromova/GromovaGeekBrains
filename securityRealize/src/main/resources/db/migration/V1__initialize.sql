create table users (
    id                    bigserial primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

create table roles (
  id                    bigserial,
  name                  varchar(50) not null,
  primary key (id)
);

create table users_roles (
  user_id               int not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

create table authorities (
  id                    bigserial,
  name                  varchar(50) not null,
  primary key (id)
);

create table roles_authorities (
  role_id               int not null,
  authority_id          int not null,
  primary key (role_id, authority_id),
  foreign key (role_id) references roles (id),
  foreign key (authority_id) references authorities (id)
);

insert into users (username, password) 
values
('admin', '$2y$12$Jzuh1p/c39Tt2xThAoiGbOrDnv4K/YT0/PGoDnrfG6ArnGgcxIRDu'),
('user', '$2y$12$Jzuh1p/c39Tt2xThAoiGbOrDnv4K/YT0/PGoDnrfG6ArnGgcxIRDu'),
('manager', '$2y$12$Jzuh1p/c39Tt2xThAoiGbOrDnv4K/YT0/PGoDnrfG6ArnGgcxIRDu'),
('admin2', '$2y$12$Jzuh1p/c39Tt2xThAoiGbOrDnv4K/YT0/PGoDnrfG6ArnGgcxIRDu'),
('user2', '$2y$12$Jzuh1p/c39Tt2xThAoiGbOrDnv4K/YT0/PGoDnrfG6ArnGgcxIRDu');

insert into roles (name)
values
('ROLE_ADMIN'), ('ROLE_USER'), ('ROLE_MANAGER');

insert into authorities (name) 
values 
('SOMETHING_ADMIN'), ('SOMETHING_USER'), ('SOMETHING_MANAGER'), ('SOMETHING_ALL');

insert into users_roles (user_id, role_id) 
values 
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(4, 2),
(5, 2),
(5, 3);

insert into roles_authorities (role_id, authority_id) 
values 
(1, 1),
(2, 2),
(3, 3),
(1, 4),
(2, 4),
(3, 4);