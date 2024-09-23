CREATE TABLE sellers
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users (id) not null
)