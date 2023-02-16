CREATE TABLE if NOT EXISTS todo_categories (
   id BIGSERIAL PRIMARY KEY,
   name varchar NOT NULL UNIQUE
);