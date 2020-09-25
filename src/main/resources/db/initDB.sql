DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL
);

CREATE UNIQUE INDEX users_unique_email_idx ON users (name);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id     INTEGER   NOT NULL,
    state VARCHAR NOT NULL,
    text VARCHAR NOT NULL ,
    registration TIMESTAMP           DEFAULT now() NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
)
-- CREATE UNIQUE INDEX orders_unique_user_registration_idx ON orders (user_id, registration);