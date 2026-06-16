package supermarket_db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void addProduct(Product p) {

        try {

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO Products " +
                    "(product_name, barcode_number, retail_price, cost_price, category_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, p.getName());
            ps.setString(2, p.getBarcode());
            ps.setDouble(3, p.getRetailPrice());
            ps.setDouble(4, p.getCostPrice());
            ps.setInt(5, p.getCategoryId());

            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {

        List<Product> products =
                new ArrayList<>();

        try {

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM Products";

            Statement stmt =
                    conn.createStatement();

            ResultSet rs =
                    stmt.executeQuery(sql);

            while (rs.next()) {

                Product p =
                        new Product(
                                rs.getInt("product_id"),
                                rs.getString("product_name"),
                                rs.getString("barcode_number"),
                                rs.getDouble("retail_price"),
                                rs.getDouble("cost_price"),
                                rs.getInt("category_id")
                        );

                products.add(p);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    public void deleteProduct(int id) {

        try {

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "DELETE FROM Products " +
                    "WHERE product_id = ?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Product>
    searchProducts(String keyword)
    {
        List<Product> products =
                new ArrayList<>();

        try {

            Connection conn = DBConnection.getConnection();

            String sql =

                    "SELECT * " +
                    "FROM Products " +
                    "WHERE product_name LIKE ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString( 1, "%" + keyword + "%" );

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {	products.add(

                        new Product(

                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("barcode_number"),
                        rs.getDouble("retail_price"),
                        rs.getDouble("cost_price"),
                        rs.getInt("category_id")

                ));
            }

            conn.close();

        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        return products;
    }
    
    
    public void updateProduct(Product p){
        try {

            Connection conn = DBConnection.getConnection();

            String sql =

                    "UPDATE Products " +
                    "SET " +
                    "product_name=?," +
                    "barcode_number=?," +
                    "retail_price=?," +
                    "cost_price=?," +
                    "category_id=? " +
                    "WHERE product_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,p.getName());
            ps.setString(2,p.getBarcode());
            ps.setDouble( 3,p.getRetailPrice());
            ps.setDouble(4, p.getCostPrice());
            ps.setInt(5,p.getCategoryId());
            ps.setInt(6,p.getId());
            ps.executeUpdate();
            
            conn.close();

        }

        catch(Exception e){

            e.printStackTrace();}

    }
}