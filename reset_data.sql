-- Emergency Supply Tracker - Reset Data Script
-- This script clears all existing data and inserts realistic emergency supply data

-- Clear all existing data (in correct order due to foreign key constraints)
DELETE FROM distributions;
DELETE FROM requests;
DELETE FROM inventory;
DELETE FROM donations;
DELETE FROM view_logs;

-- Reset sequences
ALTER SEQUENCE donations_donation_id_seq RESTART WITH 1;
ALTER SEQUENCE requests_request_id_seq RESTART WITH 1;
ALTER SEQUENCE distributions_distribution_id_seq RESTART WITH 1;
ALTER SEQUENCE view_logs_log_id_seq RESTART WITH 1;

-- Insert realistic emergency supply donations
INSERT INTO donations (item_name, category, quantity, unit, expire_date, location, notes) VALUES
-- Food Supplies
('Canned Beans', 'Food', 120, 'cans', '2026-08-15', 'Warehouse A - Section 1', 'High protein emergency food'),
('Rice (White)', 'Food', 75, 'kg', '2025-12-31', 'Warehouse A - Section 1', 'Long grain white rice'),
('Pasta', 'Food', 45, 'kg', '2026-03-20', 'Warehouse A - Section 1', 'Dried pasta for emergency meals'),
('Canned Soup', 'Food', 200, 'cans', '2025-11-30', 'Warehouse A - Section 1', 'Vegetable and chicken soup'),
('Peanut Butter', 'Food', 30, 'jars', '2025-09-15', 'Warehouse A - Section 1', 'High energy protein source'),
('Crackers', 'Food', 80, 'boxes', '2025-07-10', 'Warehouse A - Section 1', 'Emergency snack food'),
('Baby Formula', 'Food', 25, 'cans', '2025-06-30', 'Warehouse A - Section 2', 'Infant nutrition'),

-- Water and Sanitation
('Bottled Water', 'WASH', 500, 'bottles', '2027-01-01', 'Warehouse B - Section 1', '1.5L bottles for drinking'),
('Water Purification Tablets', 'WASH', 100, 'packs', '2028-12-31', 'Warehouse B - Section 1', 'Emergency water treatment'),
('Soap Bars', 'WASH', 150, 'bars', '2027-06-15', 'Warehouse B - Section 2', 'Personal hygiene'),
('Hand Sanitizer', 'WASH', 80, 'bottles', '2025-12-31', 'Warehouse B - Section 2', '500ml bottles'),
('Toilet Paper', 'WASH', 60, 'rolls', '2030-01-01', 'Warehouse B - Section 2', 'Sanitation supplies'),

-- Medical Supplies
('First Aid Kits', 'Medical', 40, 'kits', '2027-05-20', 'Medical Storage Room', 'Complete emergency first aid'),
('Bandages', 'Medical', 200, 'rolls', '2026-10-15', 'Medical Storage Room', 'Various sizes'),
('Pain Relievers', 'Medical', 50, 'bottles', '2025-08-30', 'Medical Storage Room', 'Acetaminophen tablets'),
('Antiseptic', 'Medical', 35, 'bottles', '2026-02-28', 'Medical Storage Room', 'Wound cleaning solution'),
('Thermometers', 'Medical', 20, 'units', '2030-01-01', 'Medical Storage Room', 'Digital thermometers'),
('Face Masks', 'Medical', 1000, 'masks', '2027-12-31', 'Medical Storage Room', 'Surgical masks'),

-- Shelter and Clothing
('Emergency Blankets', 'Shelter', 100, 'blankets', '2030-01-01', 'Warehouse C - Section 1', 'Thermal emergency blankets'),
('Sleeping Bags', 'Shelter', 50, 'bags', '2030-01-01', 'Warehouse C - Section 1', 'Cold weather sleeping bags'),
('Tents', 'Shelter', 15, 'tents', '2030-01-01', 'Warehouse C - Section 1', '4-person emergency tents'),
('Tarps', 'Shelter', 25, 'tarps', '2030-01-01', 'Warehouse C - Section 1', 'Waterproof shelter material'),
('Winter Coats', 'Clothing', 60, 'coats', '2030-01-01', 'Warehouse C - Section 2', 'Adult winter coats'),
('Children Clothes', 'Clothing', 80, 'sets', '2030-01-01', 'Warehouse C - Section 2', 'Various sizes'),

-- Tools and Equipment
('Flashlights', 'Tools', 75, 'units', '2030-01-01', 'Equipment Storage', 'LED emergency flashlights'),
('Batteries', 'Tools', 200, 'packs', '2027-03-15', 'Equipment Storage', 'AA and AAA batteries'),
('Portable Radios', 'Tools', 30, 'radios', '2030-01-01', 'Equipment Storage', 'Battery-powered emergency radios'),
('Generators', 'Tools', 5, 'units', '2030-01-01', 'Equipment Storage', 'Portable gas generators'),
('Fuel Cans', 'Tools', 20, 'cans', '2025-12-31', 'Equipment Storage', '5L gasoline containers');

-- Insert realistic emergency requests
INSERT INTO requests (requester_full_name, requester_location, item_name, unit, quantity, status, priority, contact_info) VALUES
('Sarah Johnson', 'Downtown Shelter', 'Bottled Water', 'bottles', 50, 'Pending', 'High', 'Phone: 555-0123'),
('Mike Chen', 'Community Center East', 'Emergency Blankets', 'blankets', 20, 'Pending', 'Medium', 'Email: mike.chen@email.com'),
('Lisa Rodriguez', 'Family Services Center', 'Baby Formula', 'cans', 10, 'Approved', 'High', 'Phone: 555-0456'),
('David Thompson', 'Red Cross Station', 'First Aid Kits', 'kits', 15, 'Pending', 'High', 'Phone: 555-0789'),
('Emma Wilson', 'Local Church', 'Canned Soup', 'cans', 30, 'Approved', 'Medium', 'Email: emma.w@church.org'),
('Robert Brown', 'Emergency Response Team', 'Flashlights', 'units', 25, 'In Progress', 'High', 'Phone: 555-0321'),
('Maria Garcia', 'Homeless Outreach', 'Winter Coats', 'coats', 15, 'Pending', 'High', 'Phone: 555-0654'),
('James Lee', 'Disaster Relief Center', 'Portable Radios', 'radios', 8, 'Approved', 'Medium', 'Email: j.lee@relief.org');

-- Insert distribution records for approved/in-progress requests
INSERT INTO distributions (request_id, item_name, unit, distributed_quantity, distribution_location, status, distributed_by) VALUES
(3, 'Baby Formula', 'cans', 10, 'Family Services Center', 'Completed', 'John Smith'),
(5, 'Canned Soup', 'cans', 30, 'Local Church', 'Completed', 'Jane Doe'),
(6, 'Flashlights', 'units', 25, 'Emergency Response Team', 'In Progress', 'Mike Johnson');

-- Insert some activity logs
INSERT INTO view_logs (table_name, record_id, action_type, action_description, performed_by) VALUES
('donations', 1, 'INSERT', 'New donation added: Canned Beans', 'System Admin'),
('donations', 2, 'INSERT', 'New donation added: Rice (White)', 'System Admin'),
('requests', 1, 'INSERT', 'New request submitted: Bottled Water', 'Sarah Johnson'),
('requests', 3, 'UPDATE', 'Request status changed to Approved', 'System Admin'),
('distributions', 1, 'INSERT', 'Distribution completed: Baby Formula', 'John Smith');