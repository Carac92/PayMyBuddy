package com.paymybuddy.app.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bill_id", nullable = false)
    private Long billID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" )
    private User user;
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    // GETTERS AND SETTERS

    public Long getBillID() {
        return billID;
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
}
