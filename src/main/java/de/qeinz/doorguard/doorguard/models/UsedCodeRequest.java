package de.qeinz.doorguard.doorguard.models;

/**
 * JavaDoc this file!
 * Created: 29.03.2024
 *
 * @author Nikk (dominik@minesort.de)
 */
public class UsedCodeRequest {

    private String usedCode;
    private boolean isOneDayPassword;
    private boolean isOneTimePassword;

    public String getUsedCode() {
        return usedCode;
    }

    public void setUsedCode(String usedCode) {
        this.usedCode = usedCode;
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