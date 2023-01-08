package com.paymybuddy.app.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Model for money transfers inside the application between two different users.
 */
@Entity
public class MoneyTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transferDate;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne()
    @JoinColumn(name = "contact_id")
    private Contact contact;
    @OneToOne(mappedBy = "moneyTransfer", orphanRemoval = true)
    private Bill bill;



    //GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
    public Contact getContact() {
        return contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public MoneyTransfer(BigDecimal amount, Date transferDate, User user, Contact contact) {
        this.amount = amount;
        this.transferDate = transferDate;
        this.user = user;
        this.contact = contact;
    }
    public MoneyTransfer() {
    }
}