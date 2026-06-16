package supermarket_db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAO {

    // Insert Customer
    public void insertCustomer(Customer c) {

        String sql =
                "INSERT INTO Customers " +
                "(customer_name, phone_number, customer_email, customer_address, registration_date) " +
                "VALUES (?,?,?,?,?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, c.getCustomerName());

            ps.setString(2, c.getPhoneNumber());

            ps.setString(3, c.getCustomerEmail());

            ps.setString(4, c.getCustomerAddress());

            ps.setDate(5, c.getRegistrationDate());

            ps.executeUpdate();

            System.out.println("Customer Added Successfully");

        }

        catch (SQLException e) {

            e.printStackTrace();

        }

    }


    // Update Customer
    public void updateCustomer(Customer c) {

        String sql =
                "UPDATE Customers " +
                "SET customer_name=?, " +
                "phone_number=?, " +
                "customer_email=?, " +
                "customer_address=?, " +
                "registration_date=? " +
                "WHERE customer_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, c.getCustomerName());

            ps.setString(2, c.getPhoneNumber());

            ps.setString(3, c.getCustomerEmail());

            ps.setString(4, c.getCustomerAddress());

            ps.setDate(5, c.getRegistrationDate());

            ps.setInt(6, c.getCustomerId());

            ps.executeUpdate();

            System.out.println("Customer Updated Successfully");

        }

        catch (SQLException e) {

            e.printStackTrace();

        }

    }


    // Delete Customer

    public void deleteCustomer(int id) {

        String sql =
                "DELETE FROM Customers WHERE customer_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Customer Deleted Successfully");

        }

        catch (SQLException e) {

            e.printStackTrace();

        }

    }



    // Search Customer by ID

    public Customer searchCustomer(int id) {

        String sql =
                "SELECT * FROM Customers WHERE customer_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                return new Customer(

                        rs.getInt("customer_id"),

                        rs.getString("customer_name"),

                        rs.getString("phone_number"),

                        rs.getString("customer_email"),

                        rs.getString("customer_address"),

                        rs.getDate("registration_date")
                );

            }

        }

        catch(SQLException e) {

            e.printStackTrace();

        }

        return null;

    }



    // Get All Customers

    public ArrayList<Customer> getAllCustomers() {

        ArrayList<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM Customers";

        try {

        	  Connection con =
                      DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Customer c = new Customer(

                        rs.getInt("customer_id"),

                        rs.getString("customer_name"),

                        rs.getString("phone_number"),

                        rs.getString("customer_email"),

                        rs.getString("customer_address"),

                        rs.getDate("registration_date")

                );

                customers.add(c);

            }
            con.close();

        }
        

        catch(SQLException e) {

            e.printStackTrace();

        }

        return customers;

    }

}