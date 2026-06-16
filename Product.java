package supermarket_db;

public class Product {
    private int productId;
    private String productName;
    private double retailPrice;

    // Constructor
    public Product(int productId, String productName, double retailPrice) {
        this.productId = productId;
        this.productName = productName;
        this.retailPrice = retailPrice;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }
}