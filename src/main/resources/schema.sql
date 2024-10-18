-- Create the user table
CREATE TABLE IF NOT EXISTS app_users
(
    user_id
    VARCHAR
(
    255
) PRIMARY KEY,
    username VARCHAR
(
    255
) UNIQUE NOT NULL,
    password VARCHAR
(
    255
) NOT NULL,
    email VARCHAR
(
    255
) NOT NULL,
    is_verified_account BOOLEAN DEFAULT FALSE,
    is_seller BOOLEAN DEFAULT FALSE,
    phone_number VARCHAR
(
    255
),
    address VARCHAR
(
    255
),
    zip_code VARCHAR
(
    255
)
    );

-- Create the category table
CREATE TABLE IF NOT EXISTS category
(
    category_id
    VARCHAR
(
    255
) PRIMARY KEY,
    name VARCHAR
(
    255
) NOT NULL,
    description TEXT
    );

-- Create the item listings table
CREATE TABLE IF NOT EXISTS item_listings
(
    item_id
    VARCHAR
(
    255
) PRIMARY KEY,
    name VARCHAR
(
    255
) NOT NULL,
    description TEXT,
    price DOUBLE NOT NULL,
    user_id VARCHAR
(
    255
),
    category_id VARCHAR
(
    255
),
    image_urls VARCHAR
(
    255
),
    listing_address VARCHAR
(
    255
),
    zip VARCHAR
(
    255
),
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY
(
    user_id
) REFERENCES app_users
(
    user_id
),
    FOREIGN KEY
(
    category_id
) REFERENCES category
(
    category_id
)
    );

-- Create the message table
CREATE TABLE message
(
    id           VARCHAR(255) PRIMARY KEY,
    item_id      VARCHAR(255),
    message_text TEXT,
    sender_id    VARCHAR(255),
    receiver_id  VARCHAR(255),
    timestamp    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (item_id) REFERENCES item_listings (item_id),
    FOREIGN KEY (sender_id) REFERENCES app_users (user_id),
    FOREIGN KEY (receiver_id) REFERENCES app_users (user_id)
);

-- Create the item listing report table
CREATE TABLE IF NOT EXISTS item_listing_report
(
    report_id
    VARCHAR
(
    255
) PRIMARY KEY,
    item_id VARCHAR
(
    255
),
    seller_id VARCHAR
(
    255
),
    reported_by VARCHAR
(
    255
),
    reason TEXT,
    reported_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY
(
    item_id
) REFERENCES item_listings
(
    item_id
)
    );
