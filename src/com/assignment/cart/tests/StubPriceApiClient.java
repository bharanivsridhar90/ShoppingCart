package com.assignment.cart.tests;

import com.assignment.cart.PriceProvider;

public class StubPriceApiClient implements PriceProvider {
    @Override
    public double getPrice(String productName) {
        switch (productName.toLowerCase()) {
            case "cornflakes": return 2.52;
            case "weetabix":   return 9.98;
            case "cheerios":   return 8.43;
            case "frosties":   return 4.99;
            case "shreddies":  return 4.68;
            default: throw new IllegalArgumentException("Unknown product: " + productName);
        }
    }
}
