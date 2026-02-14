-- Limpiar primero
TRUNCATE TABLE products RESTART IDENTITY CASCADE;

-- Insertar 15 productos
INSERT INTO products (name, description, price, category, image_url, active) VALUES
                                                                                 ('Laptop HP Pavilion', 'Laptop HP Pavilion 15.6" Intel Core i5, 8GB RAM, 512GB SSD', 2499.99, 'Electronics', 'https://example.com/laptop-hp.jpg', true),
                                                                                 ('iPhone 15 Pro', 'Apple iPhone 15 Pro 256GB, Pantalla Super Retina XDR 6.1"', 4999.99, 'Electronics', 'https://example.com/iphone15.jpg', true),
                                                                                 ('Samsung Galaxy S24', 'Samsung Galaxy S24 5G, 8GB RAM, 256GB, Cámara 50MP', 3799.99, 'Electronics', 'https://example.com/samsung-s24.jpg', true),
                                                                                 ('MacBook Air M2', 'MacBook Air 13" con chip M2, 8GB RAM, 256GB SSD', 5999.99, 'Electronics', 'https://example.com/macbook-air.jpg', true),
                                                                                 ('Polo Nike Dri-FIT', 'Polo deportivo Nike Dri-FIT, 100% poliéster', 89.99, 'Clothing', 'https://example.com/polo-nike.jpg', true),
                                                                                 ('Jeans Levi''s 501', 'Jeans clásicos Levi''s 501 Original Fit', 179.99, 'Clothing', 'https://example.com/jeans-levis.jpg', true),
                                                                                 ('Zapatillas Adidas Ultraboost', 'Zapatillas running Adidas Ultraboost 23', 599.99, 'Clothing', 'https://example.com/adidas-ultraboost.jpg', true),
                                                                                 ('Licuadora Oster', 'Licuadora Oster 3 velocidades, 600W', 149.99, 'Home & Kitchen', 'https://example.com/licuadora-oster.jpg', true),
                                                                                 ('Microondas LG', 'Microondas LG 20L, 700W', 299.99, 'Home & Kitchen', 'https://example.com/microondas-lg.jpg', true),
                                                                                 ('Cafetera Nespresso', 'Cafetera Nespresso Essenza Mini', 449.99, 'Home & Kitchen', 'https://example.com/nespresso.jpg', true),
                                                                                 ('Clean Code', 'Clean Code - Robert C. Martin', 159.99, 'Books', 'https://example.com/clean-code.jpg', true),
                                                                                 ('Cien Años de Soledad', 'Cien Años de Soledad - García Márquez', 89.99, 'Books', 'https://example.com/cien-anos.jpg', true),
                                                                                 ('Balón Fútbol Nike', 'Balón de fútbol Nike Strike Team', 79.99, 'Sports', 'https://example.com/balon-nike.jpg', true),
                                                                                 ('Pesas Hexagonales', 'Set de pesas hexagonales 5kg, 10kg, 15kg', 299.99, 'Sports', 'https://example.com/pesas.jpg', true),
                                                                                 ('Bicicleta Montaña', 'Bicicleta de montaña Shimano 21 velocidades', 1299.99, 'Sports', 'https://example.com/bicicleta.jpg', true);