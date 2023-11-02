-- Sample data for Merchant
INSERT INTO merchant (id, name, address) VALUES (1, 'Merchant One', '123 First St.');
INSERT INTO merchant (id, name, address) VALUES (2, 'Merchant Two', '456 Second Ave.');

-- Sample data for Purchase
INSERT INTO purchase (id, merchant_id, item, price, purchase_date) VALUES (1, 1, 'Item A', 50.5, '2023-11-01');
INSERT INTO purchase (id, merchant_id, item, price, purchase_date) VALUES (2, 1, 'Item B', 30.0, '2023-11-02');
INSERT INTO purchase (id, merchant_id, item, price, purchase_date) VALUES (3, 2, 'Item C', 10.25, '2023-11-03');
