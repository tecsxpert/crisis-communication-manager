CREATE TABLE crisis (
    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,

    status VARCHAR(50) NOT NULL,  -- OPEN, IN_PROGRESS, RESOLVED

    priority VARCHAR(50) NOT NULL, -- LOW, MEDIUM, HIGH

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);

-- Indexes (IMPORTANT for performance)
CREATE INDEX idx_crisis_status ON crisis(status);
CREATE INDEX idx_crisis_priority ON crisis(priority);
CREATE INDEX idx_crisis_created_at ON crisis(created_at);