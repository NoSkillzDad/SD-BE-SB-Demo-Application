insert into users (username, password, enabled)
values
('user', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE),    -- password: password
('admin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);    -- password: password

insert into authorities (username, authority)
values
('user', 'ROLE_USER'),
('admin', 'ROLE_USER'),
('admin', 'ROLE_ADMIN');

insert into books (title, author, publisher, isbn)
values
('Harry Potter en de Steen der Wijzen', 'J.K. Rowling', 'Bloomsbury', '9076174083'),
('Harry Potter en de Geheime Kamer', 'J.K. Rowling', 'Bloomsbury', '9076174121'),
('Harry Potter en de Gevangene van Azkaban', 'J.K. Rowling', 'Bloomsbury', '9076174148'),
('Harry Potter en de Vuurbeker', 'J.K. Rowling', 'Bloomsbury', '9076174199'),
('Harry Potter en de Orde van de Feniks', 'J.K. Rowling', 'Bloomsbury', '906169700X'),
('Harry Potter en de Halfbloed Prins', 'J.K. Rowling', 'Bloomsbury', '9061697662'),
('Harry Potter en de Relieken van de Dood', 'J.K. Rowling', 'Bloomsbury', '9789061698326');

insert into members (first_name, last_name, address)
values
('Albert', 'Einstein', 'Harvard, USA');

insert into copies (book_id, is_available)
values
(1, true),
(1, true),
(2, true),
(2, true),
(3, true),
(3, true);
