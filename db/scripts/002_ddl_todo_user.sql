CREATE TABLE if NOT EXISTS todo_users (
   id SERIAL PRIMARY KEY,
   name varchar not null,
   email varchar not null unique,
   login varchar not null unique,
   password varchar not null,
   created TIMESTAMP
);

comment on table todo_users is '������� ������ ������������';
comment on column users.id is '���������� ������������� ������� ������ ������������';
comment on column users.name is '��� ������� ������ ������������';
comment on column users.email is '��.����� ������� ������ ������������';
comment on column users.login is '���������� ����� ������� ������ ������������';
comment on column users.password is '������ ������� ������ ������������';
comment on column users.created is '����� �������� ������� ������ ������������';