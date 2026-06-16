package supermarket_db;

public class Product {

    private int id;
    private String name;
    private String barcode;
    private double retailPrice;
    private double costPrice;
    private int categoryId;

    public Product(int id, String name, String barcode,
                   double retailPrice, double costPrice,
                   int categoryId) {

        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.retailPrice = retailPrice;
        this.costPrice = costPrice;
        this.categoryId = categoryId;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getBarcode() { return barcode; }

    public double getRetailPrice() { return retailPrice; }

    public double getCostPrice() { return costPrice; }

    public int getCategoryId() { return categoryId; }
}