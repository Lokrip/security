CREATE TABLE IF NOT EXISTS "user" (
    id bigserial PRIMARY KEY,
    username VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR NOT NULL
);

create table IF NOT EXISTS "role"(
    id bigserial primary key,
    title varchar unique not null default 'USER'
);
