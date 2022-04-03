## User Table
CREATE INDEX idx_provider_id_and_provider ON user(provider_id, provider);

## Schedule Table
CREATE INDEX idx_start_date_time ON schedule(start_date_time);
