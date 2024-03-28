package de.qeinz.doorguard.doorguard.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * JavaDoc this file!
 * Created: 28.03.2024
 *
 * @author Nikk (dominik@minesort.de)
 */
public class LockOpener {

    public static void unlockLock() {
        String lockEntityId = System.getenv("LOCK_ID");
        String apiUrl = System.getenv("API_URL");
        String accessToken = System.getenv("TOKEN");
        System.out.println(lockEntityId);
        System.out.println(apiUrl);
        System.out.println(accessToken);
        try {
            URL url = new URL(apiUrl + "/services/lock/open");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String data = "{\"entity_id\": \"" + lockEntityId + "\"}";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Lock successfully unlocked.");
            }

            connection.disconnect();
        } catch (IOException e) {
            System.out.println("Error unlocking lock: " + e.getMessage());
        }
    }
}