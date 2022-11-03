package com.paymybuddy.app.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Money_Transfers")
public class MoneyTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "money_transfer_id", nullable = false)
    private Long moneyTransferID;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "transfer_date", nullable = false)
    private Date transferDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    //GETTERS AND SETTERS
    public Long getMoneyTransferID() {
        return moneyTransferID;
    }

    public void setMoneyTransferID(Long moneyTransferID) {
        this.moneyTransferID = moneyTransferID;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}