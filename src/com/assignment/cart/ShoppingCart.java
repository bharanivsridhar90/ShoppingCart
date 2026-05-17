package com.assignment.cart;


import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<CartItem> items = new ArrayList<>();

    // Add product by name and quantity
    public void addProduct(String productName, int quantity) {
    	if (quantity <= 0) {
            // Do nothing if quantity is zero or negative
            return;
        }
        // Fetch price from API
        double price = PriceApiClient.getPrice(productName);
        Product product = new Product(productName, price);

        // Check if product already exists in cart
        for (CartItem item : items) {
            if (item.getProduct().getName().equalsIgnoreCase(productName)) {
                item.increaseQuantity(quantity);
                return;
            }
        }
        // Otherwise add new item
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
        return CartUtils.calculateSubtotal(subtotal);
    }

    public double getTax() {
        return CartUtils.calculateTax(getSubtotal());
    }

    public double getTotal() {
        return CartUtils.calculateTotal(getSubtotal());
    }

}

