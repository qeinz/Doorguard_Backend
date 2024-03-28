package de.qeinz.doorguard.doorguard.models;

/**
 * JavaDoc this file!
 * Created: 27.03.2024
 *
 * @author Nikk (dominik@minesort.de)
 */
public class CodeRequest {
    private String accountCode;
    private boolean onetimePassword;
    private boolean onedayPassword;

    public CodeRequest() {
    }

    public CodeRequest(boolean onetimePassword, boolean onedayPassword) {
        this.onetimePassword = onetimePassword;
        this.onedayPassword = onedayPassword;
    }

    public boolean isOnetimePassword() {
        return onetimePassword;
    }

    public void setOnetimePassword(boolean onetimePassword) {
        this.onetimePassword = onetimePassword;
    }

    public boolean isOnedayPassword() {
        return onedayPassword;
    }

    public void setOnedayPassword(boolean onedayPassword) {
        this.onedayPassword = onedayPassword;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
}