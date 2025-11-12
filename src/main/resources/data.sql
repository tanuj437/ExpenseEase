INSERT INTO categories(name) VALUES ('Food'), ('Transport'), ('Rent'), ('Shopping'), ('Utilities');

INSERT INTO expenses(description, amount, date, payment_method, category_id, notes) VALUES
('Lunch at canteen', 120.00, '2025-10-12', 'CASH', (SELECT id FROM categories WHERE name='Food'), 'veg thali'),
('Metro card recharge', 300.00, '2025-10-14', 'UPI', (SELECT id FROM categories WHERE name='Transport'), 'Noida Sec-62'),
('October rent', 8000.00, '2025-10-01', 'UPI', (SELECT id FROM categories WHERE name='Rent'), 'shared room'),
('T-shirt', 699.00, '2025-10-20', 'CARD', (SELECT id FROM categories WHERE name='Shopping'), 'sale'),
('Electricity bill', 1200.00, '2025-10-05', 'UPI', (SELECT id FROM categories WHERE name='Utilities'), 'Oct bill');
