package de.qeinz.doorguard.doorguard.models;

/**
 * JavaDoc this file!
 * Created: 28.03.2024
 *
 * @author Nikk (dominik@minesort.de)
 */
public class AccountRequest {
    private String password;
    private String accountCode;
    private String newAccountCode;
    private String name;

    public AccountRequest() {
    }

    public AccountRequest(String password, String accountCode, String newAccountCode, String name) {
        this.password = password;
        this.accountCode = accountCode;
        this.newAccountCode = newAccountCode;
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getNewAccountCode() {
        return newAccountCode;
    }

    public void setNewAccountCode(String newAccountCode) {
        this.newAccountCode = newAccountCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}