package com.assignment.cart.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assignment.cart.DefaultTaxCalculator;
import com.assignment.cart.PriceProvider;
import com.assignment.cart.ShoppingCart;
import com.assignment.cart.TaxCalculator;


public class ShoppingCartTest {
	private ShoppingCart cart;
	private PriceProvider stubPriceProvider;  
	private TaxCalculator taxCalculator;   

	@BeforeEach
	public void setUp() {
	    // assign to the fields, not redeclare locals
	    stubPriceProvider = new StubPriceApiClient();
	    taxCalculator = new DefaultTaxCalculator();
	    cart = new ShoppingCart(stubPriceProvider, taxCalculator);
	}


	@Test
	public void testAddProductsAndTotals() {
		//  Validates adding the same product twice increments quantity
		//  Confirms subtotal, tax, and total calculations match expected values
		cart.addProduct("cornflakes", 1);
		cart.addProduct("cornflakes", 1);
		cart.addProduct("weetabix", 1);

		assertEquals(2, cart.getItems().get(0).getQuantity());
		assertEquals("cornflakes", cart.getItems().get(0).getProduct().getName());
		assertEquals(15.02, cart.getSubtotal(), 0.01);
		assertEquals(1.88, cart.getTax(), 0.01);
		assertEquals(16.90, cart.getTotal(), 0.01);
	}

	@Test
	public void testRoundingBehaviour() {
		//  Ensures subtotal, tax, and total are rounded to two decimal places
		cart.addProduct("cheerios", 1);

		assertEquals(8.43, cart.getSubtotal(), 0.01);
		assertEquals(1.05, cart.getTax(), 0.01);
		assertEquals(9.48, cart.getTotal(), 0.01);
	}

	@Test
	public void testAddingDifferentProducts() {
		//  Validates cart can handle multiple different products
		//  Confirms subtotal, tax, and total are calculated correctly
		cart.addProduct("frosties", 1);
		cart.addProduct("shreddies", 1);

		assertEquals(9.67, cart.getSubtotal(), 0.01);
		assertEquals(1.21, cart.getTax(), 0.01);
		assertEquals(10.88, cart.getTotal(), 0.01);
	}

	@Test
	public void testEmptyCartTotals() {
		//  Ensures an empty cart returns zero for subtotal, tax, and total

		assertEquals(0.0, cart.getSubtotal(), 0.0);
		assertEquals(0.0, cart.getTax(), 0.0);
		assertEquals(0.0, cart.getTotal(), 0.0);
	}

	@Test
	public void testZeroQuantityAdd() {
		//  Validates that adding a product with zero quantity does not add to the cart
		cart.addProduct("cornflakes", 0);

		assertTrue(cart.getItems().isEmpty());
		assertEquals(0.0, cart.getSubtotal(), 0.0);
	}

	@Test
	public void testLargeQuantityAdd() {
		//  Stress test: ensures cart handles very large quantities correctly
		cart.addProduct("cornflakes", 1000);

		double expectedSubtotal = 1000 * stubPriceProvider.getPrice("cornflakes");
		double expectedTax = expectedSubtotal * 0.125;
		double expectedTotal = expectedSubtotal + expectedTax;

		assertEquals(expectedSubtotal, cart.getSubtotal(), 0.01);
		assertEquals(expectedTax, cart.getTax(), 0.01);
		assertEquals(expectedTotal, cart.getTotal(), 0.01);
	}

	@Test
	public void testCaseInsensitiveProductName() {
		//  Ensures product names are case-insensitive when adding to cart
		cart.addProduct("CornFlakes", 1);

		assertEquals("cornflakes", cart.getItems().get(0).getProduct().getName().toLowerCase());
		assertEquals(1, cart.getItems().get(0).getQuantity());
	}

	@Test
	public void testMultipleDifferentProducts() {
		//  Validates cart can hold multiple distinct products simultaneously
		cart.addProduct("cornflakes", 1);
		cart.addProduct("weetabix", 1);
		cart.addProduct("shreddies", 1);
		cart.addProduct("frosties", 1);
		cart.addProduct("cheerios", 1);

		assertEquals(5, cart.getItems().size());
	}

	@Test
	public void testNegativeQuantityIgnored() {
		//  Ensures negative quantities are ignored and do not add items to the cart
		cart.addProduct("cornflakes", -5);

		assertTrue(cart.getItems().isEmpty());
	}
}
