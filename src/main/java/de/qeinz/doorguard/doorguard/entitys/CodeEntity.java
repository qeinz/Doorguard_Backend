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
    private LocalDateTime creationDate; // Das Erstellungsdatum
    private LocalDateTime expirationDate; // Das Ablaufdatum

    // Konstruktor, Getter und Setter hier einfügen

    // Konstruktor
    public CodeEntity() {
        this.creationDate = LocalDateTime.now(); // Setze das Erstellungsdatum auf das aktuelle Datum und Uhrzeit
        // Setze das Ablaufdatum basierend auf onetimePassword
        if (onetimePassword) {
            this.expirationDate = LocalDateTime.now(); // Ablaufdatum für Einmalpasswort auf das Erstellungsdatum setzen
        } else {
            // Setze das Ablaufdatum auf null, wenn es sich nicht um ein Einmalpasswort handelt
            this.expirationDate = null;
        }
    }

    // Getter und Setter für 'id', 'password', 'onetimePassword', 'onedayPassword', 'creationDate' und 'expirationDate' hier einfügen

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
}