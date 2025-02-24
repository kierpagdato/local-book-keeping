INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `email`, `first_name`, `last_name`, `account_expired`, `account_locked`, `password_expired`, `version`, `date_created`, `last_updated`)
VALUES
    (1, 'librarian', '{bcrypt}$2a$10$8igghkx6olevd1DZusAZkuhb9QDMNm3byVwfCKsgrkhz2zYwbIDze', TRUE, 'librarian@gmail.com', 'Emily', 'Doe', FALSE, FALSE, FALSE, 0, NOW(), NOW()),
    (2, 'johndoe', '{bcrypt}$2a$10$8igghkx6olevd1DZusAZkuhb9QDMNm3byVwfCKsgrkhz2zYwbIDze', TRUE, 'john.doe@gmail.com', 'John', 'Doe', FALSE, FALSE, FALSE, 0, NOW(), NOW());

INSERT INTO `role` (`id`, `authority`, `text`, `version`, `date_created`, `last_updated`)
VALUES
    (1, 'ROLE_LIBRARIAN', 'Librarian', 0, NOW(), NOW()),
    (2, 'ROLE_USER', 'User', 0, NOW(), NOW());

INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES
    (1, 1),
    (2,2);