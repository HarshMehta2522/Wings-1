DROP TABLE IF EXISTS User_Role;
DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS Cart_Product;
DROP TABLE IF EXISTS Cart;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Category;

CREATE TABLE IF NOT EXISTS Role(
    role_id bigint AUTO_INCREMENT PRIMARY KEY,
    role varchar(255)
);

CREATE TABLE IF NOT EXISTS User(
    user_id bigint AUTO_INCREMENT PRIMARY KEY,
    username varchar(255),
    password varchar(255)
   
);

CREATE TABLE IF NOT EXISTS Category(
    category_id bigint AUTO_INCREMENT PRIMARY KEY, 
    category_name varchar(255)
);

CREATE TABLE IF NOT EXISTS Product(
    product_id bigint AUTO_INCREMENT PRIMARY KEY,
    product_name varchar(255),
    price Double,
    seller_id bigint,
    category_id bigint,
    FOREIGN KEY (seller_id) REFERENCES User(user_id), 
    FOREIGN KEY (category_id) REFERENCES Category(category_id) 
);

CREATE TABLE IF NOT EXISTS Cart(
    cart_id bigint AUTO_INCREMENT PRIMARY KEY,
    total_amount Double,
    user_id bigint,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE IF NOT EXISTS Cart_product(
    cp_id bigint AUTO_INCREMENT PRIMARY KEY,
    cart_id bigint,
    product_id bigint,
    quantity bigint,
    FOREIGN KEY (cart_id) REFERENCES Cart(cart_id), 
    FOREIGN KEY (product_id) REFERENCES Product(product_id) 
);

CREATE TABLE User_role(
    user_id bigint,
    role_id bigint,
    FOREIGN KEY (user_id) REFERENCES User(user_id), 
    FOREIGN KEY (role_id) REFERENCES Role(role_id) 
);

INSERT INTO Category (category_name)
VALUES ('Fashion'),('Electronics'),('Books'),('Groceries'),('Medicines');

INSERT INTO User (username,password)
VALUES ('jack','pass_word'),('bob','pass_word'),('apple','pass_word'),('glaxo','pass_word');

INSERT INTO Role (role)
VALUES ('CONSUMER'),('SELLER');

INSERT INTO Cart (total_amount,user_id)
VALUES (20,1),(0,2);

INSERT INTO User_role (user_id,role_id)
VALUES (1,1),(2,1),(3,2),(4,2);

INSERT INTO Product (price,product_name,category_id,seller_id)
VALUES (29190,'Apple iPad 10.2 8th Gen WiFi iOS Tablet',2,3),(10,'Crocin pain relief tablet',5,4);

INSERT INTO Cart_product (cart_id,product_id,quantity)
VALUES (1,2,2);
