package supermarket_db;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProductController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtBarcode;

    @FXML
    private TextField txtRetail;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtCategory;
    
    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product,String> colName;

    @FXML
    private TableColumn<Product,String> colBarcode;

    @FXML
    private TableColumn<Product,Double> colRetail;

    @FXML
    private TableColumn<Product,Double> colCost;

    @FXML
    private TableColumn<Product,Integer> colCategory;

    private ObservableList<Product> list =
            FXCollections.observableArrayList();

    private ProductDAO productDAO =
            new ProductDAO();

    @FXML
    public void initialize() {

        colName.setCellValueFactory(
                d -> new SimpleStringProperty(
                        d.getValue().getName()));

        colBarcode.setCellValueFactory(
                d -> new SimpleStringProperty(
                        d.getValue().getBarcode()));

        colRetail.setCellValueFactory(
                d -> new SimpleObjectProperty<>(
                        d.getValue().getRetailPrice()));

        colCost.setCellValueFactory(
                d -> new SimpleObjectProperty<>(
                        d.getValue().getCostPrice()));

        colCategory.setCellValueFactory(
                d -> new SimpleObjectProperty<>(
                        d.getValue().getCategoryId()));

        loadProducts();
        
        productTable.getSelectionModel()

        .selectedItemProperty()

        .addListener(

        (obs, oldVal, newVal)->{

        if(newVal != null)
        {

        txtName.setText(

        newVal.getName());

        txtBarcode.setText(

        newVal.getBarcode());

        txtRetail.setText(

        String.valueOf(

        newVal.getRetailPrice()));

        txtCost.setText(

        String.valueOf(newVal.getCostPrice()));

        txtCategory.setText(

        String.valueOf( newVal.getCategoryId()));

        }

        });
    }

    @FXML
    private void addProduct() {

        try {

            Product p =
                    new Product(
                            0,
                            txtName.getText(),
                            txtBarcode.getText(),
                            Double.parseDouble(txtRetail.getText()),
                            Double.parseDouble(txtCost.getText()),
                            Integer.parseInt(txtCategory.getText())
                    );

            productDAO.addProduct(p);

            loadProducts();

            clearFields();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void deleteProduct() {

        Product selected =
                productTable.getSelectionModel()
                            .getSelectedItem();

        if(selected == null)
            return;

        productDAO.deleteProduct(
                selected.getId()
        );

        loadProducts();
    }

    private void loadProducts() {

        list.clear();

        list.addAll(
                productDAO.getAllProducts()
        );

        productTable.setItems(list);
    }
    
    @FXML

    private void searchProduct()
    {

        list.clear();

        list.addAll(

                productDAO.searchProducts(

                        txtSearch.getText()

                )
        );

    }
    
    @FXML
    private void updateProduct()
    {
        Product selected =

                productTable

                .getSelectionModel()

                .getSelectedItem();

        if(selected==null)

            return;

        Product p =

                new Product(

                selected.getId(),

                txtName.getText(),

                txtBarcode.getText(),

                Double.parseDouble(txtRetail.getText()),

                Double.parseDouble(txtCost.getText()),

                Integer.parseInt(txtCategory.getText())

        );

        productDAO.updateProduct(p);

        loadProducts();

        clearFields();
    }

    private void clearFields() {

        txtName.clear();
        txtBarcode.clear();
        txtRetail.clear();
        txtCost.clear();
        txtCategory.clear();
    }
}