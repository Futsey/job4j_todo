CREATE TABLE if NOT EXISTS tasks (
   id SERIAL PRIMARY KEY,
   description TEXT not null,
   created TIMESTAMP,
   done BOOLEAN
);