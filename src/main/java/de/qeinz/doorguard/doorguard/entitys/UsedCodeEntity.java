package de.qeinz.doorguard.doorguard.entitys;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * JavaDoc this file!
 * Created: 29.03.2024
 *
 * @author Nikk (dominik@minesort.de)
 */
@Entity
@Table(name = "used_codes")
public class UsedCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "used_code", nullable = false)
    private String usedCode;

    @Column(name = "usage_time", nullable = false)
    private LocalDateTime usageTime;

    @Column(name = "oneday_password", nullable = false)
    private boolean isOneDayPassword;

    @Column(name = "onetime_password", nullable = false)
    private boolean isOneTimePassword;

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsedCode() {
        return usedCode;
    }

    public void setUsedCode(String usedCode) {
        this.usedCode = usedCode;
    }

    public LocalDateTime getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(LocalDateTime usageTime) {
        this.usageTime = usageTime;
    }

    public boolean isOneDayPassword() {
        return isOneDayPassword;
    }

    public void setOneDayPassword(boolean oneDayPassword) {
        isOneDayPassword = oneDayPassword;
    }

    public boolean isOneTimePassword() {
        return isOneTimePassword;
    }

    public void setOneTimePassword(boolean oneTimePassword) {
        isOneTimePassword = oneTimePassword;
    }
}