-- Create the "user" table
CREATE TABLE "user" (
    id UUID PRIMARY KEY,            -- Unique ID for the user
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Creation timestamp
    created_by VARCHAR(255),        -- User who created the record
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Last modification timestamp
    updated_by VARCHAR(255),        -- User who last modified the record
    name VARCHAR(255) NOT NULL,     -- User's name
    email VARCHAR(255) NOT NULL UNIQUE, -- User's email address
    picture_url TEXT                -- URL of the user's picture
);

-- Create the "issue" table
CREATE TABLE issue (
    id UUID PRIMARY KEY,            -- Unique ID for the issue
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Creation timestamp
    created_by VARCHAR(255),        -- User who created the record
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Last modification timestamp
    updated_by VARCHAR(255),        -- User who last modified the record
    title VARCHAR(255) NOT NULL,    -- Title of the issue
    description TEXT,               -- Description of the issue
    status VARCHAR(50),             -- Status of the issue (ENUM stored as string)
    user_id UUID NOT NULL,          -- Foreign key referencing the "user" table
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE
);
