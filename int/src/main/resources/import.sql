DROP TABLE users IF EXISTS;
CREATE TABLE IF NOT EXISTS users (id bigserial, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO users (name) VALUES ('Bob1');

DROP TABLE items IF EXISTS;
CREATE TABLE IF NOT EXISTS items (id bigserial, title VARCHAR(255), PRIMARY KEY (id));
INSERT INTO items (title) VALUES ('box1');