DELETE
FROM user_roles;
DELETE
FROM requests;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, password)
VALUES ('User1', 'user1'),
       ('User2', 'user2'),
       ('Admin', 'admin'),
       ('Operator1', 'operator1'),
       ('Operator2', 'operator2');


INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100002),
       ('OPERATOR', 100003),
       ('OPERATOR', 100004);

INSERT INTO requests (status, text, user_id)
VALUES ('DRAFT', 'draft order by User1', 100000),
       ('SENT', 'sent order by User1', 100000),
       ('RECEIVED', 'received order by User2', 100001),
       ('REJECTED','rejected order by User2', 100001);