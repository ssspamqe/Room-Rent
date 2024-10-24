CREATE TABLE admins
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users (id)
)