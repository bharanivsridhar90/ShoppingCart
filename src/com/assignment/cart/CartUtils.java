package com.assignment.cart;

public class CartUtils {
    private static final double TAX_RATE = 0.125;

    // Calculate subtotal from a given amount
    public static double calculateSubtotal(double amount) {
        return round(amount);
    }

    // Calculate tax from subtotal
    public static double calculateTax(double subtotal) {
        return round(subtotal * TAX_RATE);
    }

    // Calculate total (subtotal + tax)
    public static double calculateTotal(double subtotal) {
        return round(subtotal + calculateTax(subtotal));
    }

    // Round to 2 decimal places
    public static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
