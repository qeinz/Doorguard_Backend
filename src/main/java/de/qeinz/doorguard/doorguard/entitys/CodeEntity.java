package de.qeinz.doorguard.doorguard.entitys;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;
    private boolean onetimePassword;
    private boolean onedayPassword;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private boolean activated; // Neue Spalte für Aktivierung

    // Standardkonstruktor
    public CodeEntity() {
        this.creationDate = LocalDateTime.now();
        this.expirationDate = null;
        this.activated = true; // Standardmäßig auf "true" setzen
    }

    // Konstruktor mit Parametern
    public CodeEntity(String password, boolean onetimePassword, boolean onedayPassword) {
        this.password = password;
        this.onetimePassword = onetimePassword;
        this.onedayPassword = onedayPassword;
        this.creationDate = LocalDateTime.now();
        if (onetimePassword) {
            this.expirationDate = LocalDateTime.now();
        } else {
            this.expirationDate = null;
        }
        this.activated = true; // Standardmäßig auf "true" setzen
    }

    // Getter und Setter hier einfügen

    // Getter und Setter für 'id'
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter und Setter für 'password'
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter und Setter für 'onetimePassword'
    public boolean isOnetimePassword() {
        return onetimePassword;
    }

    public void setOnetimePassword(boolean onetimePassword) {
        this.onetimePassword = onetimePassword;
    }

    // Getter und Setter für 'onedayPassword'
    public boolean isOnedayPassword() {
        return onedayPassword;
    }

    public void setOnedayPassword(boolean onedayPassword) {
        this.onedayPassword = onedayPassword;
    }

    // Getter und Setter für 'creationDate'
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    // Getter und Setter für 'expirationDate'
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    // Getter und Setter für 'activated'
    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }


    private String accountCode;

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }


    private String accountName;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    private String accountPassword;

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
}