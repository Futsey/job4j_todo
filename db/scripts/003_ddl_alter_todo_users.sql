ALTER TABLE todo_tasks ADD COLUMN if NOT EXISTS
user_id int not null references todo_users(id);