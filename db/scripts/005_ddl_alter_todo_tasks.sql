ALTER TABLE todo_tasks ADD COLUMN IF NOT EXISTS priority_id int REFERENCES todo_priorities(id);