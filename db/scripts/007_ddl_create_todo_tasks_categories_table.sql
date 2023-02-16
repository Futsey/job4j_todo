CREATE TABLE if NOT EXISTS todo_tasks_categories (
   task_id BIGINT REFERENCES todo_tasks(id),
   category_id BIGINT REFERENCES todo_categories(id),
   PRIMARY KEY(task_id, category_id)
);