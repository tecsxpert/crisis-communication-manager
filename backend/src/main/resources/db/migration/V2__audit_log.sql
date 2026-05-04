CREATE TABLE audit_log (
    id BIGSERIAL PRIMARY KEY,

    action VARCHAR(100) NOT NULL,  -- CREATE, UPDATE, DELETE
    entity_id BIGINT,
    entity_type VARCHAR(100),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_audit_entity ON audit_log(entity_id);