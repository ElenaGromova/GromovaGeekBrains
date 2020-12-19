create table genders (
    id                      bigserial primary key,
    title                   varchar(50)
);

insert into genders (title)
values
('female'),
('male');

create table cities (
    id                      bigserial primary key,
    title                   varchar(255)
);

insert into cities (title)
values
('Omsk'),
('Novosibirsk'),
('Moscow');


create table profiles (
  id                    bigserial primary key,
  firstname             varchar(50),
  lastname              varchar(50),
  phone                 varchar(50),
  email                 varchar(50),
  birthday              date,
  gender_id             bigint references genders (id),
  city_id               bigint references cities (id)
);

insert into profiles (firstname, lastname, phone, email, birthday, gender_id, city_id)
values
('bob', 'brown', '88002003040', 'user@gmail.com', '1990-06-20', 2, 2),
('elena', 'smith', '88002013141', 'elena@gmail.com', '1986-06-24', 1, 1);

create table users (
  id                    bigserial primary key,
  username              varchar(30) not null,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  profile_id            bigint references profiles (id)
);

insert into users (username, password, profile_id)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 1),
('elena', '$2a$10$JcyEC.S9FI3Bm3OzpK0Mi.nB6oJjBmfwCLgyw3zHBcWd6lisnln/C', 2);

--username elena pass $2a$10$JcyEC.S9FI3Bm3OzpK0Mi.nB6oJjBmfwCLgyw3zHBcWd6lisnln/C email elena@gmail.com roles [Role(id=1, name=ROLE_USER)]
--Hibernate: insert into users (id, email, password, username) values (null, ?, ?, ?)
--Hibernate: insert into users_roles (user_id, role_id) values (?, ?)

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);


CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('SOMETHING');


insert into users_roles (user_id, role_id) values (1, 1), (1, 2), (2, 1);

create table categories (
    id                      bigserial primary key,
    title                   varchar(255)
);

create table products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int,
    category_id             bigint references categories (id)
);

create table orders (
    id                      bigserial primary key,
    user_id                 bigint references users(id),
    price                   int,
    address                 varchar(1000),
    phone                   varchar(15)
);

create table order_items (
    id                      bigserial primary key,
    product_id              bigint references products(id),
    order_id                bigint references orders(id),
    price                   int,
    price_per_product       int,
    quantity                int
);

insert into categories (title)
values
('Food'),
('Notebook'),
('Smartphone');

insert into products (title, price, category_id)
values
('Bread', 1, 1),
('Samsung V100', 2, 3),
('Acer X1000', 3, 2),
('Bread1', 1, 1),
('Samsung1 V100', 2, 3),
('Acer1 X1000', 3, 2);

insert into orders (user_id, price, address, phone)
values
(1, 100, 'address1','phone1'),
(1, 200, 'address2','phone2'),
(1, 300, 'address3','phone3'),
(2, 100, 'address1','phone1'),
(2, 200, 'address2','phone2');

--insert into order_items (product_id, order_id , price, price_per_product, quantity)
--values
--(1, 1, 2, 1, 2),
--(1, 2, 1, 1, 1),
--(1, 3, 3, 1, 3),
--(2, 4, 4, 2, 2),
--(3, 5, 3, 3, 1),
--(2, 4, 2, 2, 1),
--(3, 1, 9, 3, 3);

