-- Fix database schema to match the application
-- Run this in PostgreSQL

-- Drop existing tables if they exist
DROP TABLE IF EXISTS view_logs CASCADE;
DROP TABLE IF EXISTS distributions CASCADE;
DROP TABLE IF EXISTS requests CASCADE;
DROP TABLE IF EXISTS inventory CASCADE;
DROP TABLE IF EXISTS donations CASCADE;

-- Create donations table
CREATE TABLE donations (
    donation_id SERIAL PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    quantity INTEGER NOT NULL,
    unit VARCHAR(50) NOT NULL,
    expire_date DATE,
    location VARCHAR(255),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create inventory table
CREATE TABLE inventory (
    donation_id INTEGER REFERENCES donations(donation_id) ON DELETE CASCADE,
    item_name VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    quantity INTEGER NOT NULL,
    unit VARCHAR(50) NOT NULL,
    expire_date DATE,
    location VARCHAR(255),
    status VARCHAR(50) DEFAULT 'Available',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create requests table with correct column names
CREATE TABLE requests (
    request_id SERIAL PRIMARY KEY,
    requester_full_name VARCHAR(255) NOT NULL,
    requester_location VARCHAR(255) NOT NULL,
    item_name VARCHAR(255) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    quantity INTEGER NOT NULL,
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'Pending',
    priority VARCHAR(50) DEFAULT 'Medium',
    contact_info TEXT
);

-- Create distributions table with correct column names
CREATE TABLE distributions (
    distribution_id SERIAL PRIMARY KEY,
    request_id INTEGER REFERENCES requests(request_id),
    item_name VARCHAR(255) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    distributed_quantity INTEGER NOT NULL,
    distribution_location VARCHAR(255),
    distribution_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'In Progress',
    distributed_by VARCHAR(255)
);

-- Create view_logs table with correct column names
CREATE TABLE view_logs (
    log_id SERIAL PRIMARY KEY,
    table_name VARCHAR(100),
    record_id INTEGER,
    action_type VARCHAR(100) NOT NULL,
    action_description TEXT,
    old_values TEXT,
    new_values TEXT,
    performed_by VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create trigger function
CREATE OR REPLACE FUNCTION update_inventory()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO inventory (donation_id, item_name, category, quantity, unit, expire_date, location, status)
        VALUES (NEW.donation_id, NEW.item_name, NEW.category, NEW.quantity, NEW.unit, NEW.expire_date, NEW.location,
                CASE WHEN NEW.expire_date < CURRENT_DATE THEN 'Expired' ELSE 'Available' END);
        RETURN NEW;
    ELSIF TG_OP = 'UPDATE' THEN
        UPDATE inventory SET
            item_name = NEW.item_name,
            category = NEW.category,
            quantity = NEW.quantity,
            unit = NEW.unit,
            expire_date = NEW.expire_date,
            location = NEW.location,
            status = CASE WHEN NEW.expire_date < CURRENT_DATE THEN 'Expired' ELSE 'Available' END
        WHERE donation_id = NEW.donation_id;
        RETURN NEW;
    ELSIF TG_OP = 'DELETE' THEN
        DELETE FROM inventory WHERE donation_id = OLD.donation_id;
        RETURN OLD;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Create trigger
CREATE TRIGGER donation_inventory_sync
    AFTER INSERT OR UPDATE OR DELETE ON donations
    FOR EACH ROW EXECUTE FUNCTION update_inventory();

-- Insert sample data
INSERT INTO donations (item_name, category, quantity, unit, expire_date, location, notes) VALUES
('Rice', 'Food', 50, 'kg', '2025-12-31', 'Warehouse A', 'White rice'),
('Blankets', 'Shelter', 20, 'pc', '2030-01-01', 'Warehouse B', 'Winter blankets'),
('Water', 'WASH', 100, 'L', '2025-06-30', 'Warehouse A', 'Drinking water'),
('Medicine', 'Medical', 30, 'box', '2024-12-31', 'Medical Storage', 'First aid supplies');