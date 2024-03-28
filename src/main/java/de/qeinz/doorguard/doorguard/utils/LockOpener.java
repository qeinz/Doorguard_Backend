package de.qeinz.doorguard.doorguard.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * JavaDoc this file!
 * Created: 28.03.2024
 *
 * @author Nikk (dominik@minesort.de)
 */
public class LockOpener {

    public static void main(String[] args) {
        String lockEntityId = "lock.front_door";
        String apiUrl = "http://your_home_assistant_url:8123/api";
        String accessToken = "your_access_token";

        unlockLock(lockEntityId, apiUrl, accessToken);
    }

    public static void unlockLock(String lockEntityId, String apiUrl, String accessToken) {
        try {
            URL url = new URL(apiUrl + "/services/lock/unlock");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String input = "{\"entity_id\": " + lockEntityId + "}";

            conn.getOutputStream().write(input.getBytes());

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("Lock successfully unlocked.");
            } else {
                System.out.println("Failed to unlock lock. Response code: " + conn.getResponseCode());
            }

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}