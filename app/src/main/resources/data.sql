DROP DATABASE PayMyBuddy;
CREATE DATABASE PayMyBuddy;
USE PayMyBuddy;

CREATE TABLE Users (
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
email VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
birthdate DATE NOT NULL,
credit FLOAT NOT NULL
);

CREATE TABLE Bank_Info(
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INTEGER NOT NULL,
iban VARCHAR(255) NOT NULL,
bic VARCHAR(255) NOT NULL,
name_of_account VARCHAR(255) NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(id)
);
-- mdp123
INSERT INTO Users (email, password, first_name, last_name, address, birthdate, credit)
VALUES ('jean.dupont@mail.com', '$2y$10$dQoHaaFjLCrVb7PuXLKvMOnLJw.CfKFkac4bKOe19IajddHfo0YCi', 'Jean', 'Dupont',
 '1234 avenue', DATE '1989-07-03', 1000);
-- mdp123
INSERT INTO Users (email, password, first_name, last_name, address, birthdate, credit)
VALUES ('marie.dupont@mail.com', '$2y$10$dQoHaaFjLCrVb7PuXLKvMOnLJw.CfKFkac4bKOe19IajddHfo0YCi', 'Marie', 'Dupont',
 '1234 avenue', DATE '1988-05-02', 1000);

CREATE TABLE Credit_Card_Info (
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INTEGER NOT NULL,
credit_card_number INTEGER NOT NULL,
credit_card_name VARCHAR(255) NOT NULL,
credit_card_expiration_date DATE NOT NULL,
credit_card_ccv INTEGER NOT NULL,
FOREIGN KEY(user_id) REFERENCES Users(id)
);

CREATE TABLE Contacts(
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INTEGER NOT NULL,
contact_user_id INTEGER NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(id),
FOREIGN KEY(contact_user_id) REFERENCES Users(id)
);

CREATE TABLE Money_Transfers(
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
contact_id INTEGER NOT NULL,
user_id INTEGER NOT NULL,
transfer_date DATE NOT NULL,
amount DECIMAL(10,2) Not NULL,
description VARCHAR(255) NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(id),
FOREIGN KEY (contact_id) REFERENCES Users(id)
);

CREATE TABLE Bills(
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INTEGER NOT NULL,
money_transfer_id INTEGER NOT NULL,
price DECIMAL(10,2) NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(id),
FOREIGN KEY (money_transfer_id) REFERENCES Money_Transfers(id)
);