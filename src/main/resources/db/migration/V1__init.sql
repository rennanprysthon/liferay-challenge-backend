CREATE TABLE product_order (
    product_order_id SERIAL,
    taxes DECIMAL NOT NULL DEFAULT 0.0,
    total DECIMAL NOT NULL DEFAULT 0.0,
    PRIMARY KEY (product_order_id)
);
CREATE TABLE product (
    product_id SERIAL,
    name VARCHAR(200) NOT NULL,
    type VARCHAR(2) DEFAULT 'OT',
    price DECIMAL NOT NULL DEFAULT 0.0,
    imported BOOLEAN NOT NULL DEFAULT FALSE,
    cd_product_order INTEGER DEFAULT NULL,
    FOREIGN KEY (cd_product_order) REFERENCES product_order(product_order_id),
    PRIMARY KEY (product_id)
);

