CREATE TABLE IF NOT EXISTS books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id BIGINT NOT NULL,
    description TEXT,
    image_url VARCHAR(512),
    CONSTRAINT fk_book_author FOREIGN KEY (author_id) REFERENCES "user"(id) ON DELETE CASCADE
);
