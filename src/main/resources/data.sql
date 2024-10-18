-- Insert initial users
INSERT INTO app_users (user_id, username, password, email, is_verified_account, is_seller, phone_number, address,
                       zip_code)
VALUES ('user1', 'testbuyer', 'password123', 'buyer@test.com', TRUE, FALSE, '1234567890', '123 Buyer St', '10001'),
       ('user2', 'testseller', 'password123', 'seller@test.com', TRUE, TRUE, '0987654321', '456 Seller St', '10002'),
       ('user3', 'testseller2', 'password123', 'seller2@test.com', TRUE, TRUE, '0987787821', '001 Seller St', '19122');


-- Insert initial categories
INSERT INTO category (category_id, name, description)
VALUES ('1', 'Electronics', 'Devices and gadgets'),
       ('2', 'Furniture', 'Home and office furniture'),
       ('3', 'Collectibles', 'Collectible Trading Cards and Memorablia'),
       ('4', 'Fashion', 'Clothing, Shoes and Accessories'),
       ('5', 'Sports', 'Sports Accessories');

-- Insert item listings with similar names
INSERT INTO item_listings (item_id, name, description, price, user_id, category_id, image_urls, listing_address, zip,
                           is_available)
VALUES
    -- Electronics (Category 1) - Similar names around "Laptop", "Phone", "Headphones"
    ('1', 'Apple MacBook Pro 2020', 'A powerful laptop', 1200.00, 'user2', '1', 'http://example.com/laptop.png',
     '123 Seller St', '10002', TRUE),
    ('2', 'Dell XPS 13 Laptop', 'Ultra-thin laptop', 1300.00, 'user2', '1', 'http://example.com/xps13.png',
     '123 Seller St', '10002', TRUE),
    ('3', 'Samsung Galaxy S22 Phone', 'Flagship smartphone', 900.00, 'user3', '1', 'http://example.com/s22phone.png',
     '001 Seller St', '19120', TRUE),
    ('4', 'Apple iPhone 13 Phone', 'Latest iPhone model', 1100.00, 'user3', '1', 'http://example.com/iphone13.png',
     '001 Seller St', '19120', TRUE),
    ('5', 'Sony WH-1000XM5 Headphones', 'Noise-cancelling headphones', 350.00, 'user3', '1',
     'http://example.com/wh1000xm5.png', '001 Seller St', '19120', TRUE),
    ('6', 'Bose QuietComfort 45 Headphones', 'Comfortable noise-cancelling headphones', 300.00, 'user2', '1',
     'http://example.com/bose45.png', '123 Seller St', '10002', TRUE),

    -- Furniture (Category 2) - Similar names around "Chair", "Table", "Desk"
    ('7', 'Ergonomic Office Chair', 'Comfortable ergonomic chair', 200.00, 'user3', '2',
     'http://example.com/ergonomicchair.png', '001 Seller St', '19120', TRUE),
    ('8', 'Executive Office Chair', 'Luxury office chair with leather upholstery', 250.00, 'user2', '2',
     'http://example.com/executivechair.png', '123 Seller St', '10002', TRUE),
    ('9', 'Wooden Coffee Table', 'Solid wood coffee table with storage', 150.00, 'user3', '2',
     'http://example.com/coffeetable.png', '001 Seller St', '19120', TRUE),
    ('10', 'Glass Coffee Table', 'Modern glass top coffee table', 180.00, 'user2', '2',
     'http://example.com/glasstable.png', '123 Seller St', '10002', TRUE),
    ('11', 'Adjustable Standing Desk', 'Height adjustable standing desk', 350.00, 'user3', '2',
     'http://example.com/standingdesk.png', '001 Seller St', '19120', TRUE),
    ('12', 'Corner Desk', 'Spacious L-shaped corner desk', 400.00, 'user2', '2', 'http://example.com/cornerdesk.png',
     '123 Seller St', '10002', TRUE),

    -- Collectibles (Category 3) - Similar names around "Card", "Figure", "Memorabilia"
    ('13', 'Vintage Baseball Card', '1952 Topps Mickey Mantle', 5000.00, 'user2', '3',
     'http://example.com/baseballcard.png', '123 Seller St', '10002', TRUE),
    ('14', 'Rare Pokemon Card', 'Holographic Charizard Pokemon card', 800.00, 'user2', '3',
     'http://example.com/pokemoncard.png', '123 Seller St', '10002', TRUE),
    ('15', 'Marvel Action Figure Set', 'Full set of Marvel superhero action figures', 300.00, 'user3', '3',
     'http://example.com/actionfigures.png', '001 Seller St', '19120', TRUE),
    ('16', 'Star Wars Action Figures', 'Original Star Wars action figure set', 500.00, 'user2', '3',
     'http://example.com/starwarsfigures.png', '123 Seller St', '10002', TRUE),
    ('17', 'Comic Book Collection', 'Vintage Marvel comic books', 1200.00, 'user3', '3',
     'http://example.com/comicbooks.png', '001 Seller St', '19120', TRUE),
    ('18', 'Star Wars Memorabilia', 'Original Star Wars collectibles', 2000.00, 'user3', '3',
     'http://example.com/starwarsmem.png', '001 Seller St', '19120', TRUE),

    -- Fashion (Category 4) - Similar names around "Jacket", "Shoes", "Watch"
    ('19', 'Levis Denim Jacket', 'Classic mens denim jacket', 80.00, 'user2', '4', 'http://example.com/denimjacket.png',
     '123 Seller St', '10002', TRUE),
    ('20', 'North Face Winter Jacket', 'Waterproof winter jacket', 200.00, 'user2', '4',
     'http://example.com/northface.png', '123 Seller St', '10002', TRUE),
    ('21', 'Nike Air Max Shoes', 'Running shoes, size 10', 120.00, 'user3', '4', 'http://example.com/airmax.png',
     '001 Seller St', '19120', TRUE),
    ('22', 'Adidas Ultraboost Shoes', 'Running shoes, size 9', 180.00, 'user3', '4',
     'http://example.com/ultraboost.png', '001 Seller St', '19120', TRUE),
    ('23', 'Casio G-Shock Watch', 'Digital sports watch', 100.00, 'user3', '4', 'http://example.com/gshock.png',
     '001 Seller St', '19120', TRUE),
    ('24', 'Rolex Submariner Watch', 'Luxury diving watch', 5000.00, 'user2', '4', 'http://example.com/rolex.png',
     '123 Seller St', '10002', TRUE),

    -- Sports (Category 5) - Similar names around "Ball", "Racket", "Shoes"
    ('25', 'Adidas Soccer Ball', 'Official match soccer ball', 30.00, 'user2', '5', 'http://example.com/soccerball.png',
     '123 Seller St', '10002', TRUE),
    ('26', 'Wilson Tennis Ball Set', 'Set of 3 professional tennis balls', 10.00, 'user3', '5',
     'http://example.com/tennisballs.png', '001 Seller St', '19120', TRUE),
    ('27', 'Wilson Tennis Racket', 'Pro tennis racket', 150.00, 'user3', '5', 'http://example.com/tennisracket.png',
     '001 Seller St', '19120', TRUE),
    ('28', 'Nike Soccer Cleats', 'Professional soccer cleats', 120.00, 'user2', '5',
     'http://example.com/soccercleats.png', '123 Seller St', '10002', TRUE),
    ('29', 'Spalding Basketball', 'Official NBA basketball', 50.00, 'user3', '5', 'http://example.com/basketball.png',
     '001 Seller St', '19120', TRUE),
    ('30', 'Ping Pong Racket Set', 'Ping pong racket with balls', 40.00, 'user2', '5',
     'http://example.com/pingpongset.png', '123 Seller St', '10002', TRUE);

-- Insert initial message records
INSERT INTO message (id, item_id, message_text, sender_id, receiver_id)
VALUES ('msg1', '1', 'Is this item still available?', 'user1', 'user2');

-- Insert initial report
INSERT INTO item_listing_report (report_id, item_id, seller_id, reported_by, reason)
VALUES ('report1', '1', 'user2', 'user1', 'Item not as described');