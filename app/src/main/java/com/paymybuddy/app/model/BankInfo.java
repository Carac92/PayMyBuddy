package com.paymybuddy.app.model;



import javax.persistence.*;

@Entity
@Table(name = "Bank_Info")
public class BankInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bank_info_id", nullable = false)
    private Long bankInfoID;
    @Column(name = "iban", nullable = false)
    private String iban;
    @Column(name = "bic", nullable = false)
    private String bin;
    @Column(name = "name_of_account", nullable = false)
    private String nameOfAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    // GETTERS AND SETTERS

    public Long getBankInfoID() {
        return bankInfoID;
    }

    public void setBankInfoID(Long bankInfoID) {
        this.bankInfoID = bankInfoID;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
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
