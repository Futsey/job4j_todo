CREATE TABLE if NOT EXISTS users (
   id SERIAL PRIMARY KEY,
   name varchar not null,
   email varchar not null,
   password varchar not null,
   created TIMESTAMP
);

comment on table users is '������� ������ ������������';
comment on column users.id is '���������� ������������� ������� ������ ������������';
comment on column users.name is '��� ������� ������ ������������';
comment on column users.email is '��.����� ������� ������ ������������';
comment on column users.password is '������ ������� ������ ������������';
comment on column users.created is '����� �������� ������� ������ ������������';