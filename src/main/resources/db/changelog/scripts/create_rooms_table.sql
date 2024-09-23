CREATE TABLE rooms
(
    id        BIGSERIAL PRIMARY KEY,
    name      varchar(32)                    not null,
    position  geometry(POINT, 4326),
    seller_id BIGINT REFERENCES sellers (id) not null
)