package com.paymybuddy.app.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Model for money transfers inside the application between two different users.
 */
@Entity
@Table(name = "Money_Transfer")
public class MoneyTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "transfer_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transferDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private User contact;
    @OneToOne(mappedBy = "moneyTransfer", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public void setContact(User contact) {
        this.contact = contact;
    }
    public User getContact() {
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

    public MoneyTransfer(BigDecimal amount, Date transferDate, User user, User contact) {
        this.amount = amount;
        this.transferDate = transferDate;
        this.user = user;
        this.contact = contact;
    }
    public MoneyTransfer() {
    }
}