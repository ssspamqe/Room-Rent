CREATE TABLE user_passwords
(
    user_id       BIGINT PRIMARY KEY REFERENCES users (id),
    password_hash VARCHAR(256) NOT NULL
);