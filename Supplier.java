package supermarket_db;

public class Supplier {

    private int supplierId;
    private String companyName;
    private String contactPerson;
    private String supplierPhoneNumber;
    private String supplierEmail;
    private String city;
    private String street;
    private String postalCode;

    // Empty Constructor
    public Supplier() {

    }

    // Constructor with ID
    public Supplier(int supplierId,
                    String companyName,
                    String contactPerson,
                    String supplierPhoneNumber,
                    String supplierEmail,
                    String city,
                    String street,
                    String postalCode) {

        this.supplierId = supplierId;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.supplierPhoneNumber = supplierPhoneNumber;
        this.supplierEmail = supplierEmail;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    // Constructor without ID
    public Supplier(String companyName,
                    String contactPerson,
                    String supplierPhoneNumber,
                    String supplierEmail,
                    String city,
                    String street,
                    String postalCode) {

        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.supplierPhoneNumber = supplierPhoneNumber;
        this.supplierEmail = supplierEmail;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    // Getters

    public int getSupplierId() {
        return supplierId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getSupplierPhoneNumber() {
        return supplierPhoneNumber;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    // Setters

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setSupplierPhoneNumber(String supplierPhoneNumber) {
        this.supplierPhoneNumber = supplierPhoneNumber;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}