CREATE table buyers
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users (id) not null
)