package supermarket_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
 
public class ProductController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> colName;

    @FXML
    private TableColumn<Product, Double> colPrice;

    private ObservableList<Product> productList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadProducts();
    }

    @FXML
    private void addProduct() {

        try {
            String name = txtName.getText();
            double price = Double.parseDouble(txtPrice.getText());

            Connection conn = DBConnection.getConnection();

            String sql =
                    "INSERT INTO Products(product_name, retail_price) VALUES (?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);

            ps.executeUpdate();

            conn.close();

            txtName.clear();
            txtPrice.clear();

            loadProducts(); // refresh table

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadProducts() {

        productList.clear();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT product_name, retail_price FROM Products";

            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {

                productList.add(
                        new Product(
                                0,
                                rs.getString("product_name"),
                                rs.getDouble("retail_price")
                        )
                );
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        colName.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getProductName()
                )
        );

        colPrice.setCellValueFactory(
                data -> new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getRetailPrice()
                )
        );

        productTable.setItems(productList);
    }
}