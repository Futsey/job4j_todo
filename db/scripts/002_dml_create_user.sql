CREATE TABLE if NOT EXISTS users (
   id SERIAL PRIMARY KEY,
   name varchar not null,
   email varchar not null,
   password varchar not null,
   created TIMESTAMP
);