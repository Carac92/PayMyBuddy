CREATE DATABASE PayMyBuddy;
USE PayMyBuddy;

CREATE TABLE Users (
user_id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
email VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
birthdate DATE NOT NULL,
credit FLOAT NOT NULL
);

CREATE TABLE Bank_Info(
bank_info_id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INTEGER NOT NULL,
iban VARCHAR(255) NOT NULL,
bic VARCHAR(255) NOT NULL,
name_of_account VARCHAR(255) NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

INSERT INTO Users (email, password, first_name, last_name, address, birthdate, credit)
VALUES ('dev@mail.com', '$2y$10$jF9xM.R1Ocw7qKLdzX4zBuynuthr1vIjttQ22Yi0M8nMDV.0C72vO', 'dev', 'dev', 'test', DATE '1994-07-03', 0.00);

CREATE TABLE Credit_Card_Info (
credit_card_id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INTEGER NOT NULL,
credit_card_number INTEGER NOT NULL,
credit_card_name VARCHAR(255) NOT NULL,
credit_card_expiration_date DATE NOT NULL,
credit_card_ccv INTEGER NOT NULL,
FOREIGN KEY(user_id) REFERENCES Users(user_id)
);

CREATE TABLE Contacts(
contact_id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INTEGER NOT NULL,
user_contact_id INTEGER NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(user_id),
FOREIGN KEY(user_contact_id) REFERENCES Users(user_id)
);

CREATE TABLE MoneyTransfers(
money_transfer_id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
contact_id INTEGER NOT NULL,
user_id INTEGER NOT NULL,
transfer_date DATE NOT NULL,
amount DECIMAL(10,2) Not NULL,
FOREIGN KEY (user_id) REFERENCES Users(user_id),
FOREIGN KEY (contact_id) REFERENCES Contacts(contact_id)
);

CREATE TABLE Bills(
bill_id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INTEGER NOT NULL,
price DECIMAL(10,2) NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(user_id)
);