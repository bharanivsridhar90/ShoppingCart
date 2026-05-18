package com.assignment.cart;

public class DefaultTaxCalculator implements TaxCalculator {
    private static final double TAX_RATE = 0.125;

    @Override
    public double calculateTax(double subtotal) {
        return CartUtils.round(subtotal * TAX_RATE);
    }
}
