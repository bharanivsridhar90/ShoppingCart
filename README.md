# Shopping Cart Assignment

##  Overview
This project implements a simple **Shopping Cart system in Java**.  


The cart allows adding products, calculating subtotal, tax, and total, while ensuring boundary cases (empty cart, zero/negative quantities, large quantities, case‑insensitive product names) are handled correctly.


##  Project Structure
src/
└── com.assignment.cart/
├── Product.java            # Represents a product with name and price
├── CartItem.java           # Represents a product + quantity
├── ShoppingCart.java       # Manages cart items and delegates calculations
├── CartUtils.java          # Utility class for rounding
├── PriceProvider.java      # Interface for price lookup
├── PriceApiClient.java     # Real API implementation of PriceProvider
├── TaxCalculator.java      # Interface for tax calculation
└── DefaultTaxCalculator.java # Default tax implementation (12.5%)

test/
└── com.assignment.cart.tests/
├── StubPriceApiClient.java # Stubbed PriceProvider for predictable test prices
└── ShoppingCartTest.java   # JUnit tests covering functionality & boundaries



---

## ⚙️ Implementation Approach
- **Interfaces for Flexibility**  
  - `PriceProvider` → abstracts product price lookup.  
  - `TaxCalculator` → abstracts tax calculation logic.  
- **Dependency Injection**  
  - `ShoppingCart` receives `PriceProvider` and `TaxCalculator` via constructor.  
  - Enables swapping real vs. stub implementations easily.  
- **Utility Class**  
  - `CartUtils` centralizes rounding logic.  
- **Testability**  
  - `StubPriceApiClient` ensures tests don’t depend on live HTTP calls.  

---

## 🎯 Features
- Add products to cart (case‑insensitive names).  
- Increment quantity when adding the same product multiple times.  
- Calculate subtotal, tax (12.5%), and total.  
- Round all monetary values to two decimal places.  
- Handle boundary cases:
  - Empty cart → totals = 0.  
  - Zero/negative quantity → ignored.  
  - Large quantities → stress tested.  
  - Multiple distinct products → supported.

---

## 🧪 Testing
Implemented with **JUnit 5**.  
Key test cases:
- Adding same product twice increments quantity.  
- Rounding behavior verified.  
- Multiple different products handled.  
- Empty cart returns zero totals.  
- Zero/negative quantities ignored.  
- Large quantity stress test.  
- Case‑insensitive product names.  
- Multiple distinct products supported.  

---

## 🚀 How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/ShoppingCart.git

2. Open in Eclipse or any Java IDE.

3. Run ShoppingCartTest.java as JUnit Test.

4. All tests should pass with a green bar.

5. In addition to the JUnit test suite (which uses the stubbed price provider), you can run the application endtoend with the real API client. 

BaseUrl : https://equalexperts.github.io/backend-take-home-test-data/weetabix.json

6. sample output: { "product": "cornflakes", "price": 2.52 }

## Dependencies
- org.json (json-20231013.jar) included in `lib/` folder.
- Add to Build Path in Eclipse before running.

