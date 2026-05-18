package com.assignment.cart;

public class Main {
    public static void main(String[] args) {
        // Base URL provided by Equal Experts
        String baseUrl = "https://equalexperts.github.io/backend-take-home-test-data";

        // Wire up the real API client
        PriceProvider priceProvider = new PriceApiClient(baseUrl);
        TaxCalculator taxCalculator = new DefaultTaxCalculator();

        ShoppingCart cart = new ShoppingCart(priceProvider, taxCalculator);

        // Demo run with available products
        cart.addProduct("cornflakes", 2);
        cart.addProduct("weetabix", 1);

        System.out.println("Subtotal: " + cart.getSubtotal());
        System.out.println("Tax: " + cart.getTax());
        System.out.println("Total: " + cart.getTotal());
    }
}
