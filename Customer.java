package supermarket_db;

import java.sql.Date;

public class Customer {

    private int customerId;
    private String customerName;
    private String phoneNumber;
    private String customerEmail;
    private String customerAddress;
    private Date registrationDate;

    // Default Constructor 
    public Customer() {

    }

    // Constructor without ID to add a customer first time 
    public Customer(String customerName,
                    String phoneNumber,
                    String customerEmail,
                    String customerAddress,
                    Date registrationDate) {

        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.registrationDate = registrationDate;
    }

    // Constructor with ID
    public Customer(int customerId,
                    String customerName,
                    String phoneNumber,
                    String customerEmail,
                    String customerAddress,
                    Date registrationDate) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.registrationDate = registrationDate;
    }

    // Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

}