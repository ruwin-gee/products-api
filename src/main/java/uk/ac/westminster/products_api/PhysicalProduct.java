package uk.ac.westminster.products_api;

// A PhysicalProduct IS-A Product because it is a kind of product the shop sells,
// so it can be used anywhere a Product is expected.
// A ShoppingCart would NOT pass this test: a cart HAS products, it is not one.

public class PhysicalProduct extends Product implements Discountable {

    private double weightKg;
    private String dimensions;
    private double discountedPrice;

    public PhysicalProduct() {
    }

    public PhysicalProduct(Long id, String name, double price,
                           double weightKg, String dimensions) {
        super(id, name, price);
        this.weightKg = weightKg;
        this.dimensions = dimensions;
        this.discountedPrice = price;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public String getDimensions() {
        return dimensions;
    }

    // You should always write @Override because without it a typo or wrong
    // parameter list silently creates a new method instead of overriding, and
    // the compiler can't warn you.
    @Override
    public String getSummary() {
        return "Physical: " + getName() + " | Weight: " + weightKg + "kg";
    }

    @Override
    public void applyDiscount(double rate) {
        // Shipping is never discounted, so cap the discount at 20%.
        double capped = Math.min(rate, 0.20);
        this.discountedPrice = getPrice() * (1 - capped);
    }

    @Override
    public double getDiscountedPrice() {
        return discountedPrice;
    }
}