CREATE TABLE if NOT EXISTS todo_tasks (
   id SERIAL PRIMARY KEY,
   description TEXT not null,
   created TIMESTAMP,
   done BOOLEAN
);

comment on table todo_tasks is 'Задачи';
comment on column todo_tasks.id is 'Уникальный идентификатор задачи';
comment on column todo_tasks.description is 'Описание задачи';
comment on column todo_tasks.created is 'Время создания задачи';
comment on column todo_tasks.done is 'Статус исполнения задачи';