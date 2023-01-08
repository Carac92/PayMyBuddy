package com.paymybuddy.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Application that permits to register, connect, add/delete a contact with his email address, add/delete
 * bank information, send money to a contact, transfer money from and to one of his bank accounts, generate bills for
 * each transfer with 0,05% of the amount of fees, delete account.
 */
@EnableTransactionManagement
@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
