CREATE TABLE if NOT EXISTS todo_users (
   id SERIAL PRIMARY KEY,
   name varchar not null,
   email varchar not null unique,
   login varchar not null unique,
   password varchar not null,
   created TIMESTAMP
);

comment on table todo_users is '”четна€ запись пользовател€';
comment on column todo_users.id is '”никальный идентификатор учетной записи пользовател€';
comment on column todo_users.name is '»м€ учетной записи пользовател€';
comment on column todo_users.email is 'Ёл.почта учетной записи пользовател€';
comment on column todo_users.login is '”никальный логин учетной записи пользовател€';
comment on column todo_users.password is 'ѕароль учетной записи пользовател€';
comment on column todo_users.created is '¬рем€ создани€ учетной записи пользовател€';