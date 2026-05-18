package com.assignment.cart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class PriceApiClient implements PriceProvider {
    private final String apiBaseUrl;

    public PriceApiClient(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    @Override
    public double getPrice(String productName) {
        try {
            // Example: https://equalexperts.github.io/backend-take-home-test-data/cornflakes.json
            String queryUrl = apiBaseUrl + "/" + productName.toLowerCase() + ".json";
            URL url = new URL(queryUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();

            JSONObject json = new JSONObject(response.toString());
            return json.getDouble("price");

        } catch (Exception e) {
            throw new RuntimeException("Error fetching price for " + productName, e);
        }
    }

}
