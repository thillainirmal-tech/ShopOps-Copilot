CREATE TABLE products(id UUID PRIMARY KEY,sku VARCHAR(48) NOT NULL UNIQUE,name VARCHAR(160) NOT NULL,description TEXT NOT NULL,category VARCHAR(80) NOT NULL,price NUMERIC(12,2) NOT NULL CHECK(price>=0),stock_quantity INTEGER NOT NULL CHECK(stock_quantity>=0),version BIGINT NOT NULL DEFAULT 0);
CREATE TABLE customer_orders(id UUID PRIMARY KEY,customer_email VARCHAR(120) NOT NULL,sku VARCHAR(48) NOT NULL,quantity INTEGER NOT NULL CHECK(quantity>0),total NUMERIC(12,2) NOT NULL,status VARCHAR(20) NOT NULL,created_at TIMESTAMP WITH TIME ZONE NOT NULL);
CREATE INDEX idx_products_category ON products(category);
INSERT INTO products(id,sku,name,description,category,price,stock_quantity,version) VALUES
('10000000-0000-0000-0000-000000000001','TRAIL-45','TrailPack 45','Repairable 45 litre recycled-fabric hiking backpack with rain cover','outdoor',8999.00,18,0),
('10000000-0000-0000-0000-000000000002','DESK-AIR','DeskAir Stand','Adjustable aluminium laptop stand with tool-free height settings','workspace',3499.00,31,0),
('10000000-0000-0000-0000-000000000003','BREW-STEEL','BrewSteel Press','Insulated stainless-steel coffee press, 750 ml, plastic-free packaging','kitchen',4299.00,12,0);
