CREATE TABLE announcements
(
    id          BIGSERIAL PRIMARY KEY,
    description TEXT,
    room_id     BIGINT REFERENCES rooms (id)   not null,
    seller_id   BIGINT REFERENCES sellers (id) not null
)