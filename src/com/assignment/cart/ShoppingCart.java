package com.assignment.cart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<CartItem> items = new ArrayList<>();
    private final PriceProvider priceProvider;
    private final TaxCalculator taxCalculator;

    public ShoppingCart(PriceProvider priceProvider, TaxCalculator taxCalculator) {
        this.priceProvider = priceProvider;
        this.taxCalculator = taxCalculator;
    }

    public void addProduct(String productName, int quantity) {
        if (quantity <= 0) return;

        double price = priceProvider.getPrice(productName);
        Product product = new Product(productName, price);

        for (CartItem item : items) {
            if (item.getProduct().getName().equalsIgnoreCase(productName)) {
                item.increaseQuantity(quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getSubtotal() {
        double subtotal = 0.0;
        for (CartItem item : items) {
            subtotal += item.getLineTotal();
        }
        return CartUtils.round(subtotal);
    }

    public double getTax() {
        return taxCalculator.calculateTax(getSubtotal());
    }

    public double getTotal() {
        return CartUtils.round(getSubtotal() + getTax());
    }
}
