CREATE TABLE user_roles
(
    user_id BIGINT REFERENCES users (id) not null,
    role    VARCHAR(32)                  not null,
    PRIMARY KEY (user_id, role)
)