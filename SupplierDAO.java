package supermarket_db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    private Connection conn;

    public SupplierDAO() {

        try {
            conn = DBConnection.getConnection();

            if (conn == null) {
                System.out.println("Connection is NULL!");
            } else {
                System.out.println("Database Connected Successfully!");
            }

            Statement st = conn.createStatement();

            ResultSet rs0 = st.executeQuery("SELECT DATABASE()");
            rs0.next();
            System.out.println("ACTIVE DB = " + rs0.getString(1));

            ResultSet rs1 = st.executeQuery("SELECT COUNT(*) FROM suppliers");
            rs1.next();
            System.out.println("SUPPLIERS COUNT = " + rs1.getInt(1));

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }

    // ==========================
    // Get All Suppliers
    // ==========================

    public List<Supplier> getAllSuppliers() {

        List<Supplier> list = new ArrayList<>();

        String sql = "SELECT * FROM suppliers";

        try {

            if (conn == null) {
                System.out.println("No DB Connection!");
                return list;
            }

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Supplier s = new Supplier(
                        rs.getInt("supplier_id"),
                        rs.getString("company_name"),
                        rs.getString("contact_person"),
                        rs.getString("supplier_phone_number"),
                        rs.getString("supplier_email"),
                        rs.getString("city"),
                        rs.getString("street"),
                        rs.getString("postal_code")
                );

                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Loaded Suppliers: " + list.size());

        return list;
    }


    // ==========================
    // Insert Supplier
    // ==========================

    public void insertSupplier(Supplier s) {

        String sql =
                "INSERT INTO suppliers " +
                "(company_name, contact_person, supplier_phone_number, " +
                "supplier_email, city, street, postal_code) " +
                "VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getCompanyName());
            ps.setString(2, s.getContactPerson());
            ps.setString(3, s.getSupplierPhoneNumber());
            ps.setString(4, s.getSupplierEmail());
            ps.setString(5, s.getCity());
            ps.setString(6, s.getStreet());
            ps.setString(7, s.getPostalCode());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ==========================
    // Update Supplier
    // ==========================

    public void updateSupplier(Supplier s) {

        String sql =
                "UPDATE suppliers SET " +
                "company_name=?, " +
                "contact_person=?, " +
                "supplier_phone_number=?, " +
                "supplier_email=?, " +
                "city=?, " +
                "street=?, " +
                "postal_code=? " +
                "WHERE supplier_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getCompanyName());
            ps.setString(2, s.getContactPerson());
            ps.setString(3, s.getSupplierPhoneNumber());
            ps.setString(4, s.getSupplierEmail());
            ps.setString(5, s.getCity());
            ps.setString(6, s.getStreet());
            ps.setString(7, s.getPostalCode());
            ps.setInt(8, s.getSupplierId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ==========================
    // Delete Supplier
    // ==========================

    public void deleteSupplier(int id) {

        String sql = "DELETE FROM suppliers WHERE supplier_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}