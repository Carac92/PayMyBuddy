DROP DATABASE PayMyBuddy;
CREATE DATABASE PayMyBuddy;
USE PayMyBuddy;

CREATE TABLE user (
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
email VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
birthdate DATE NOT NULL,
credit DECIMAL(10,2) NOT NULL
);

CREATE TABLE bank_info(
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INTEGER NOT NULL,
iban VARCHAR(255) NOT NULL,
bic VARCHAR(255) NOT NULL,
name_of_account VARCHAR(255) NOT NULL,
FOREIGN KEY (user_id) REFERENCES User(id)
);
-- mdp123
INSERT INTO user (email, password, first_name, last_name, address, birthdate, credit)
VALUES ('jean.dupont@mail.com', '$2y$10$dQoHaaFjLCrVb7PuXLKvMOnLJw.CfKFkac4bKOe19IajddHfo0YCi', 'Jean', 'Dupont',
 '1234 avenue', DATE '1989-07-03', 1000);
-- mdp123
INSERT INTO user (email, password, first_name, last_name, address, birthdate, credit)
VALUES ('marie.dupont@mail.com', '$2y$10$dQoHaaFjLCrVb7PuXLKvMOnLJw.CfKFkac4bKOe19IajddHfo0YCi', 'Marie', 'Dupont',
 '1234 avenue', DATE '1988-05-02', 1000);


CREATE TABLE contact(
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INTEGER,
contact_user_id INTEGER,
FOREIGN KEY (user_id) REFERENCES User(id),
FOREIGN KEY(contact_user_id) REFERENCES User(id)
);

CREATE TABLE money_transfer(
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
contact_id INTEGER NOT NULL,
user_id INTEGER NOT NULL,
transfer_date DATE NOT NULL,
amount DECIMAL(10,2) Not NULL,
description VARCHAR(255) NOT NULL,
FOREIGN KEY (user_id) REFERENCES User(id),
FOREIGN KEY (contact_id) REFERENCES User(id)
);

CREATE TABLE bill(
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INTEGER NOT NULL,
money_transfer_id INTEGER NOT NULL,
price DECIMAL(10,2) NOT NULL,
FOREIGN KEY (user_id) REFERENCES User(id),
FOREIGN KEY (money_transfer_id) REFERENCES Money_Transfer(id)
);

CREATE TABLE account_transfer(
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INTEGER NOT NULL,
bank_info_id INTEGER NOT NULL,
amount DECIMAL(10,2) NOT NULL,
transfer_date DATE NOT NULL,
FOREIGN KEY (bank_info_id) REFERENCES Bank_Info(id),
FOREIGN KEY (user_id) REFERENCES User(id)
);