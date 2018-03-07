package org.ooka.usermngmt.domain;

import java.util.UUID;

public class User {
    private String id;
    private String userName;
    private String password;
    private AccountType accountType;

    public User() {}

    public User(CredentialsMessage credentialsMessage) {
        this.id = UUID.randomUUID().toString();
        this.userName = credentialsMessage.getEmail();
        this.password = credentialsMessage.getPassword();
        this.accountType = AccountType.NORMAL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
