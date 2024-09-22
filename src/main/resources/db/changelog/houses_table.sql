CREATE TABLE houses(
    id BIGSERIAL PRIMARY KEY,
    name varchar(30),
    position geometry(POINT,4326)
)