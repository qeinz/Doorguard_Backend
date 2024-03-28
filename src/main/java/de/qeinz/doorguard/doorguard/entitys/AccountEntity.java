package de.qeinz.doorguard.doorguard.entitys;

import jakarta.persistence.*;

/**
 * JavaDoc this file!
 * Created: 28.03.2024
 *
 * @author Nikk (dominik@minesort.de)
 */
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountName;
    private String accountCode;
    private String accountPassword;

    // Getter für 'id'
    public Long getId() {
        return id;
    }

    // Setter für 'id'
    public void setId(Long id) {
        this.id = id;
    }

    // Getter für 'accountName'
    public String getAccountName() {
        return accountName;
    }

    // Setter für 'accountName'
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    // Getter für 'accountCode'
    public String getAccountCode() {
        return accountCode;
    }

    // Setter für 'accountCode'
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    // Getter für 'accountPassword'
    public String getAccountPassword() {
        return accountPassword;
    }

    // Setter für 'accountPassword'
    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
}
