use `web_book_tracker`;

INSERT INTO `user` (id, username, firstname, lastname, password, version) VALUES (43442333, 'stephanekechi@gmail.de', 'Stephane', 'Nganou', '$2a$12$UqXbsd.KCyLCiiScX0fNt.kNJzOe3gVdTHuk8JGsmEPQm9LSo1Dbq', 1);

INSERT INTO `authority` (id, user_id, auth_group, version) VALUES (434423534233, 43442333, 'MANAGER', 1);

INSERT INTO `book` (id, version, book_description, image, book_name, price)
VALUES (11443683, 1, 'The Greatness of Live describes some encounted pitfull of life and the sufferings and beauty the came with',
        null, 'The Greatness of my life', 89.99);
INSERT INTO `book` (id, version, book_description, image, book_name, price)
VALUES (20443683, 1, 'Learning how to Die: illustrates the rehearsal of die befor its happening', null, 'Learning how to Die', 59.99)