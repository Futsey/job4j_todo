CREATE TABLE if NOT EXISTS tasks (
   id SERIAL PRIMARY KEY,
   description TEXT not null,
   created TIMESTAMP,
   done BOOLEAN
);

comment on table tasks is 'Задачи'
comment on column tasks.id is 'Уникальный идентификатор задачи'
comment on column tasks.description is 'Описание задачи'
comment on column tasks.created is 'Время создания задачи'
comment on column tasks.done is 'Статус исполнения задачи'