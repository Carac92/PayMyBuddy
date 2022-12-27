package com.paymybuddy.app.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Model for the bills of linked to a user and a money transfer.
 */
@Entity
@Table(name = "Bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" )
    private User user;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "money_transfer_id")
    private MoneyTransfer moneyTransfer;

    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MoneyTransfer getMoneyTransfer() {
        return moneyTransfer;
    }

    public void setMoneyTransfer(MoneyTransfer moneyTransfer) {
        this.moneyTransfer = moneyTransfer;
    }
}
