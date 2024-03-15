
--Этот скрипт только для h2

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial, score int, name VARCHAR(255), details_id bigint,
FOREIGN KEY (details_id) REFERENCES users_details (id), PRIMARY KEY(id));
INSERT INTO users (name, score, details_id) VALUES
('Bob', 80, 1),
('Jack', 80, 2),
('John', 80, 3);



DROP TABLE IF EXISTS users_details CASCADE;
CREATE TABLE users_details (id bigserial PRIMARY KEY, email VARCHAR(255), city VARCHAR (255));
INSERT INTO users_details (email, city) VALUES
('terminator@gmail.com', 'California'),
('remboo@gmail.com', 'Atlanta'),
('Uookeer@gmail.com', 'New York');


DROP TABLE IF EXISTS universities CASCADE;
CREATE TABLE universities (id bigserial PRIMARY KEY, title VARCHAR(255));
INSERT INTO universities (title) VALUES
('DSTU'),
('NPU');


DROP TABLE IF EXISTS students CASCADE;
CREATE TABLE students (id bigserial PRIMARY KEY, name VARCHAR (255), university_id bigint REFERENCES universities(id));
INSERT INTO students (name, university_id) VALUES
('Alexander', 1),
('Bass', 2),
('Johny', 1);

DROP TABLE IF EXISTS books CASCADE;
CREATE TABLE books (id bigserial PRIMARY KEY, title VARCHAR(255));
INSERT INTO books (title) VALUES
('Go Home'),
('Never again'),
('klmn'),
('operas');


DROP TABLE IF EXISTS readers CASCADE;
CREATE TABLE readers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO readers (name) VALUES
('Joshua'),
('Ken');

DROP TABLE IF EXISTS books_readers CASCADE;
CREATE TABLE books_readers (book_id bigint, reader_id bigint,
FOREIGN KEY (book_id) REFERENCES books (id), FOREIGN KEY (reader_id) REFERENCES readers (id));
INSERT INTO books_readers (book_id, reader_id) VALUES
(1,1),
(2,1),
(3,1),
(4,1),
(2,1),
(2,2);

--Для работы many to many требуется 3-я таблица , связующая.JOIN table.
--И мы гооврим , что 1ю-книгу прочитал 1й читатлеь. 2-ю - 1й и т.д.

