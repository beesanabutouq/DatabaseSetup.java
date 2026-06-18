package supermarket_db;

public class Warehouse {

    private int warehouseId;
    private String city;
    private String street;
    private int postalCode;
    private int capacity;
    private String warehouseEmail;

    public Warehouse(int warehouseId, String city, String street, int postalCode, int capacity, String warehouseEmail) {
        this.warehouseId = warehouseId;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.capacity = capacity;
        this.warehouseEmail = warehouseEmail;
    }
    public Warehouse(String city,
            String street,
            int postalCode,
            int capacity,
            String warehouseEmail) {

this.city = city;
this.street = street;
this.postalCode = postalCode;
this.capacity = capacity;
this.warehouseEmail = warehouseEmail;
}

    // Getters
    public int getWarehouseId() {
        return warehouseId;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getWarehouseEmail() {
        return warehouseEmail;
    }

    // Setters
    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setWarehouseEmail(String warehouseEmail) {
        this.warehouseEmail = warehouseEmail;
    }
}