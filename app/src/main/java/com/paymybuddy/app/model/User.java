package com.paymybuddy.app.model;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Entity of User.
 */
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    private BigDecimal credit;
    // DB links
    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH,
                orphanRemoval = true)
    private List <BankInfo> bankInfos;

    @OneToMany(mappedBy = "user",
            orphanRemoval = true)
    private List<MoneyTransfer> moneyTransfers;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Contact> contacts;

    @OneToMany(mappedBy = "user",
                orphanRemoval = true)
    private List<Bill> bills;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<AccountTransfer> accountTransfers;



    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public List<BankInfo> getBankInfos() {
        return bankInfos;
    }

    public void setBankInfos(List<BankInfo> bankInfos) {
        this.bankInfos = bankInfos;
    }

    public List<MoneyTransfer> getMoneyTransfers() {
        return moneyTransfers;
    }

    public void setMoneyTransfers(List<MoneyTransfer> moneyTransfers) {
        this.moneyTransfers = moneyTransfers;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<AccountTransfer> getAccountTransfers() {
        return accountTransfers;
    }

    public void setAccountTransfers(List<AccountTransfer> accountTransfers) {
        this.accountTransfers = accountTransfers;
    }
}
