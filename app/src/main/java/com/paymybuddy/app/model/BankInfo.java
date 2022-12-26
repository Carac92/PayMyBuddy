package com.paymybuddy.app.model;



import javax.persistence.*;
/**
 * Model for the bank account informations.
 */
@Entity
@Table(name = "Bank_Info")
public class BankInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "iban", nullable = false)
    private String iban;
    @Column(name = "bic", nullable = false)
    private String bic;
    @Column(name = "name_of_account", nullable = false)
    private String nameOfAccount;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;

    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bin) {
        this.bic = bin;
    }

    public String getNameOfAccount() {
        return nameOfAccount;
    }

    public void setNameOfAccount(String nameOfAccount) {
        this.nameOfAccount = nameOfAccount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
