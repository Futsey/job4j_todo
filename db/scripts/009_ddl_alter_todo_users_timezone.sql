ALTER TABLE todo_users
ADD COLUMN IF
NOT EXISTS user_zone TIMESTAMP WITHOUT TIME ZONE DEFAULT now();