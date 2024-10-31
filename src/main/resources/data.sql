DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

CREATE TABLE IF NOT EXISTS authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    page_count INTEGER NOT NULL,
    author_id BIGINT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

INSERT INTO authors (name, country) VALUES
    ('Joshua Bloch', 'USA'),
    ('Douglas Adams', 'UK'),
    ('Bill Bryson', 'USA');

-- Then add the books, letting H2 handle the IDs
INSERT INTO books (name, page_count, author_id)
SELECT 'Effective Java', 416, id FROM authors WHERE name = 'Joshua Bloch'
UNION ALL
SELECT 'Hitchhiker''s Guide to the Galaxy', 208, id FROM authors WHERE name = 'Douglas Adams'
UNION ALL
SELECT 'Down Under', 436, id FROM authors WHERE name = 'Bill Bryson';