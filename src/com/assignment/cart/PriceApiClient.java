package com.assignment.cart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PriceApiClient {

    private static final String BASE_URL = "https://equalexperts.github.io/backend-take-home-test-data/";

    public static double getPrice(String productName) {
        try {
            String urlString = BASE_URL + productName.toLowerCase() + ".json";
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            // Response looks like: { "price": 2.52 }
            String json = response.toString().trim();
            // Extract the number between "price": and }
            String priceStr = json.replaceAll("[^0-9.]", "");
            return Double.parseDouble(priceStr);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch price for product: " + productName, e);
        }
    }
}
