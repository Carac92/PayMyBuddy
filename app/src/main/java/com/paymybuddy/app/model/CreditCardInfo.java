package com.paymybuddy.app.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Credit_Card_Info")
public class CreditCardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long creditCardID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "credit_card_number", nullable = false)
    private String creditCardNumber;
    @Column(name = "credit_card_name", nullable = false)
    private String creditCardName;
    @Column(name = "credit_card_expiration_date", nullable = false)
    private Date creditCardExpirationDate;
    @Column(name = "credit_card_ccv", nullable = false)
    private int creditCardCCV;

    // GETTERS AND SETTERS

    public Long getCreditCardID() {
        return creditCardID;
    }

    public void setCreditCardID(Long creditCardID) {
        this.creditCardID = creditCardID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public Date getCreditCardExpirationDate() {
        return creditCardExpirationDate;
    }

    public void setCreditCardExpirationDate(Date creditCardExpirationDate) {
        this.creditCardExpirationDate = creditCardExpirationDate;
    }

    public int getCreditCardCCV() {
        return creditCardCCV;
    }

    public void setCreditCardCCV(int creditCardCCV) {
        this.creditCardCCV = creditCardCCV;
    }
}
