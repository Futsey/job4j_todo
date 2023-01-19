CREATE TABLE if NOT EXISTS users (
   id SERIAL PRIMARY KEY,
   name varchar not null,
   email varchar not null,
   password varchar not null,
   created TIMESTAMP
);

comment on table users is '”четна€ запись пользовател€';
comment on column users.id is '”никальный идентификатор учетной записи пользовател€';
comment on column users.name is '»м€ учетной записи пользовател€';
comment on column users.email is 'Ёл.почта учетной записи пользовател€';
comment on column users.password is 'ѕароль учетной записи пользовател€';
comment on column users.created is '¬рем€ создани€ учетной записи пользовател€';