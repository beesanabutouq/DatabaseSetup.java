package supermarket_db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO {

    private static final String URL =
            "jdbc:mysql://localhost:3306/supermarket_db";

    private static final String USER = "root";
    private static final String PASSWORD = "fdshk12345@@";

    // =========================
    // CONNECTION
    // =========================
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // =========================
    // GET ALL
    // =========================
    public static List<Warehouse> getAllWarehouses() {

        List<Warehouse> list = new ArrayList<>();

        String sql = "SELECT * FROM Warehouses";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Warehouse w = new Warehouse(
                        rs.getInt("warehouse_id"),
                        rs.getString("city"),
                        rs.getString("street"),
                        rs.getInt("postal_code"),
                        rs.getInt("capacity"),
                        rs.getString("warehouse_email")
                );

                list.add(w);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // INSERT
    // =========================
    public static boolean insertWarehouse(Warehouse w) {

        // 1. check duplicate first
        if (exists(w.getCity(), w.getStreet(), w.getWarehouseEmail())) {
            System.out.println("Duplicate warehouse - not inserted");
            return false;
        }

        String sql =
                "INSERT INTO Warehouses " +
                "(city, street, postal_code, capacity, warehouse_email) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, w.getCity());
            ps.setString(2, w.getStreet());
            ps.setInt(3, w.getPostalCode());
            ps.setInt(4, w.getCapacity());
            ps.setString(5, w.getWarehouseEmail());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    // =========================
    // UPDATE
    // =========================
    public static boolean updateWarehouse(Warehouse w) {

        String sql =
                "UPDATE Warehouses " +
                "SET city=?, street=?, postal_code=?, " +
                "capacity=?, warehouse_email=? " +
                "WHERE warehouse_id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, w.getCity());
            ps.setString(2, w.getStreet());
            ps.setInt(3, w.getPostalCode());
            ps.setInt(4, w.getCapacity());
            ps.setString(5, w.getWarehouseEmail());
            ps.setInt(6, w.getWarehouseId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // DELETE
    // =========================
    public static boolean deleteWarehouse(int warehouseId) {

        String sql =
                "DELETE FROM Warehouses WHERE warehouse_id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, warehouseId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // CHECK DUPLICATE
    // =========================
    public static boolean exists(
            String city,
            String street,
            String email) {

        String sql =
                "SELECT warehouse_id " +
                "FROM Warehouses " +
                "WHERE LOWER(city)=? " +
                "AND LOWER(street)=? " +
                "AND LOWER(warehouse_email)=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, city.toLowerCase());
            ps.setString(2, street.toLowerCase());
            ps.setString(3, email.toLowerCase());

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // GET BY ID 
    // =========================
    public static Warehouse getWarehouseById(int id) {

        String sql =
                "SELECT * FROM Warehouses WHERE warehouse_id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Warehouse(
                        rs.getInt("warehouse_id"),
                        rs.getString("city"),
                        rs.getString("street"),
                        rs.getInt("postal_code"),
                        rs.getInt("capacity"),
                        rs.getString("warehouse_email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}