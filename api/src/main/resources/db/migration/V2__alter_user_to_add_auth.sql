-- Add a new column for password
ALTER TABLE "user_account"
ADD COLUMN password VARCHAR(255);

-- Add a new column for role as an ENUM (PostgreSQL example)
DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'user_role') THEN
        CREATE TYPE user_role AS ENUM ('ADMIN', 'ANALYST', 'CUSTOMER');
    END IF;
END $$;

ALTER TABLE "user_account"
ADD COLUMN role user_role;
