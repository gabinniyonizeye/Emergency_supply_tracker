-- Create users table for authentication
CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert default admin user (username: admin, password: admin123)
INSERT INTO users (username, password, role) VALUES 
('admin', 'admin123', 'admin'),
('user', 'user123', 'user')
ON CONFLICT (username) DO NOTHING;