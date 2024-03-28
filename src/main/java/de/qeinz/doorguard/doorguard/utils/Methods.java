package de.qeinz.doorguard.doorguard.utils;

import java.util.Random;

/**
 * JavaDoc this file!
 * Created: 27.03.2024
 *
 * @author Nikk (dominik@minesort.de)
 */
public class Methods {

    public static String generatePassword() {
        String characters = "0123456789";
        StringBuilder uidBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            uidBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return uidBuilder.toString();
    }

}
