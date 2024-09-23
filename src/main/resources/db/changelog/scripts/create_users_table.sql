CREATE TABLE users
(
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(16) not null,
    email VARCHAR(64) not null
)