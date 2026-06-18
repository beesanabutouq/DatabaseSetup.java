package supermarket_db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class WarehouseController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtCapacity;

    @FXML
    private TextField txtEmail;

    @FXML
    private TableView<Warehouse> tableWarehouse;

    @FXML
    private TableColumn<Warehouse, Integer> colId;

    @FXML
    private TableColumn<Warehouse, String> colCity;

    @FXML
    private TableColumn<Warehouse, String> colStreet;

    @FXML
    private TableColumn<Warehouse, Integer> colPostalCode;

    @FXML
    private TableColumn<Warehouse, Integer> colCapacity;

    @FXML
    private TableColumn<Warehouse, String> colEmail;

    private ObservableList<Warehouse> warehouseList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        System.out.println("Warehouse Controller Loaded");

        colId.setCellValueFactory(
                new PropertyValueFactory<>("warehouseId"));

        colCity.setCellValueFactory(
                new PropertyValueFactory<>("city"));

        colStreet.setCellValueFactory(
                new PropertyValueFactory<>("street"));

        colPostalCode.setCellValueFactory(
                new PropertyValueFactory<>("postalCode"));

        colCapacity.setCellValueFactory(
                new PropertyValueFactory<>("capacity"));

        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("warehouseEmail"));

        loadData();

        tableWarehouse.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldSelection, selected) -> {

                    if (selected != null) {

                        txtId.setText(
                                String.valueOf(selected.getWarehouseId()));

                        txtCity.setText(selected.getCity());

                        txtStreet.setText(selected.getStreet());

                        txtPostalCode.setText(
                                String.valueOf(selected.getPostalCode()));

                        txtCapacity.setText(
                                String.valueOf(selected.getCapacity()));

                        txtEmail.setText(
                                selected.getWarehouseEmail());
                    }
                });
    }

    public void loadData() {

        warehouseList.clear();

        warehouseList.addAll(
                WarehouseDAO.getAllWarehouses());

        tableWarehouse.setItems(warehouseList);
    }

    @FXML
    public void addWarehouse() {

        try {

            String city = txtCity.getText().trim();
            String street = txtStreet.getText().trim();
            String email = txtEmail.getText().trim();

            int postal =
                    Integer.parseInt(txtPostalCode.getText().trim());

            int capacity =
                    Integer.parseInt(txtCapacity.getText().trim());

            Warehouse w = new Warehouse(
                    city,
                    street,
                    postal,
                    capacity,
                    email
            );

            boolean inserted =
                    WarehouseDAO.insertWarehouse(w);

            if (inserted) {

                loadData();
                clearFields();

                System.out.println("Added successfully");
            } else {

                System.out.println("Duplicate or failed insert");
            }

        } catch (NumberFormatException e) {

            System.out.println("Invalid numbers");
        }
    }
    @FXML
    public void updateWarehouse() {

        Warehouse selected =
                tableWarehouse.getSelectionModel()
                        .getSelectedItem();

        if (selected == null) {

            System.out.println(
                    "Please select a warehouse first");

            return;
        }

        try {

            selected.setCity(
                    txtCity.getText().trim());

            selected.setStreet(
                    txtStreet.getText().trim());

            selected.setPostalCode(
                    Integer.parseInt(
                            txtPostalCode.getText().trim()));

            selected.setCapacity(
                    Integer.parseInt(
                            txtCapacity.getText().trim()));

            selected.setWarehouseEmail(
                    txtEmail.getText().trim());

            boolean updated =
                    WarehouseDAO.updateWarehouse(selected);

            if (updated) {

                loadData();
                clearFields();

                System.out.println(
                        "Warehouse updated successfully");
            } else {

                System.out.println(
                        "Failed to update warehouse");
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter valid numeric values");
        }
    }

    @FXML
    public void deleteWarehouse() {

        Warehouse selected =
                tableWarehouse.getSelectionModel()
                        .getSelectedItem();

        if (selected == null) {

            System.out.println(
                    "Please select a warehouse first");

            return;
        }

        boolean deleted =
                WarehouseDAO.deleteWarehouse(
                        selected.getWarehouseId());

        if (deleted) {

            loadData();
            clearFields();

            System.out.println(
                    "Warehouse deleted successfully");
        } else {

            System.out.println(
                    "Failed to delete warehouse");
        }
    }

    private void clearFields() {

        txtId.clear();
        txtCity.clear();
        txtStreet.clear();
        txtPostalCode.clear();
        txtCapacity.clear();
        txtEmail.clear();
    }
}