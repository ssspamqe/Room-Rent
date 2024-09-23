CREATE TABLE rents
(
    id         BIGINT PRIMARY KEY,
    room_id    BIGINT REFERENCES rooms (id)   not null,
    buyer_id   BIGINT REFERENCES buyers (id)  not null,
    seller_id  BIGINT REFERENCES sellers (id) not null,
    start      TIMESTAMP WITH TIME ZONE       not null,
    "end"      TIMESTAMP WITH TIME ZONE       not null,
    created_at TIMESTAMP WITH TIME ZONE       not null
)