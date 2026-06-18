package supermarket_db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SupplierController {

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtContactPerson;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TableView<Supplier> supplierTable;

    @FXML
    private TableColumn<Supplier, Integer> colId;

    @FXML
    private TableColumn<Supplier, String> colCompany;

    @FXML
    private TableColumn<Supplier, String> colContact;

    @FXML
    private TableColumn<Supplier, String> colPhone;

    @FXML
    private TableColumn<Supplier, String> colEmail;

    @FXML
    private TableColumn<Supplier, String> colCity;

    @FXML
    private TableColumn<Supplier, String> colStreet;

    @FXML
    private TableColumn<Supplier, String> colPostalCode;

    private SupplierDAO supplierDAO = new SupplierDAO();

    private ObservableList<Supplier> supplierList =
            FXCollections.observableArrayList();


    @FXML
    public void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("supplierId"));

        colCompany.setCellValueFactory(
                new PropertyValueFactory<>("companyName"));

        colContact.setCellValueFactory(
                new PropertyValueFactory<>("contactPerson"));

        colPhone.setCellValueFactory(
                new PropertyValueFactory<>("supplierPhoneNumber"));

        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("supplierEmail"));

        colCity.setCellValueFactory(
                new PropertyValueFactory<>("city"));

        colStreet.setCellValueFactory(
                new PropertyValueFactory<>("street"));

        colPostalCode.setCellValueFactory(
                new PropertyValueFactory<>("postalCode"));

        loadSuppliers();


        supplierTable.setOnMouseClicked(e -> {

            Supplier s = supplierTable.getSelectionModel()
                                      .getSelectedItem();

            if (s != null) {

                txtSupplierId.setText(
                        String.valueOf(s.getSupplierId()));

                txtCompanyName.setText(
                        s.getCompanyName());

                txtContactPerson.setText(
                        s.getContactPerson());

                txtPhone.setText(
                        s.getSupplierPhoneNumber());

                txtEmail.setText(
                        s.getSupplierEmail());

                txtCity.setText(
                        s.getCity());

                txtStreet.setText(
                        s.getStreet());

                txtPostalCode.setText(
                        s.getPostalCode());
            }

        });

    }


    public void loadSuppliers() {

        supplierList.clear();

        supplierList.addAll(
                supplierDAO.getAllSuppliers());

        supplierTable.setItems(supplierList);

    }


    @FXML
    public void addSupplier() {

        if (txtCompanyName.getText().isEmpty() ||
            txtContactPerson.getText().isEmpty() ||
            txtPhone.getText().isEmpty()) {

            System.out.println("Please fill required fields!");
            return;
        }

        Supplier supplier = new Supplier(
                txtCompanyName.getText(),
                txtContactPerson.getText(),
                txtPhone.getText(),
                txtEmail.getText(),
                txtCity.getText(),
                txtStreet.getText(),
                txtPostalCode.getText()
        );

        for (Supplier s : supplierList) {
            if (s.getCompanyName().equalsIgnoreCase(txtCompanyName.getText())
                    && s.getSupplierPhoneNumber().equals(txtPhone.getText())) {

                System.out.println("Supplier already exists!");
                return;
            }
        }


        supplierDAO.insertSupplier(supplier);


        loadSuppliers();

        
        clearFields();
    }
    @FXML
    public void updateSupplier() {

        Supplier supplier = new Supplier(

                Integer.parseInt(
                        txtSupplierId.getText()),

                txtCompanyName.getText(),

                txtContactPerson.getText(),

                txtPhone.getText(),

                txtEmail.getText(),

                txtCity.getText(),

                txtStreet.getText(),

                txtPostalCode.getText()
        );

        supplierDAO.updateSupplier(supplier);

        loadSuppliers();

        clearFields();

    }


    @FXML
    public void deleteSupplier() {

        int id =
                Integer.parseInt(
                        txtSupplierId.getText());

        supplierDAO.deleteSupplier(id);

        loadSuppliers();

        clearFields();

    }


    @FXML
    public void clearFields() {

        txtSupplierId.clear();

        txtCompanyName.clear();

        txtContactPerson.clear();

        txtPhone.clear();

        txtEmail.clear();

        txtCity.clear();

        txtStreet.clear();

        txtPostalCode.clear();

    }

}