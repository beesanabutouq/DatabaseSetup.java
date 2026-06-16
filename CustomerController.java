package supermarket_db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;

public class CustomerController {

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;
    @FXML private DatePicker datePicker;

    @FXML private TableView<Customer> customerTable;

    @FXML private TableColumn<Customer, Integer> colId;
    @FXML private TableColumn<Customer, String> colName;
    @FXML private TableColumn<Customer, String> colPhone;
    @FXML private TableColumn<Customer, String> colEmail;
    @FXML private TableColumn<Customer, String> colAddress;
    @FXML private TableColumn<Customer, Date> colDate;

    private CustomerDAO dao = new CustomerDAO();

    @FXML
    public void initialize() {
        loadTable();
    }

    // LOAD TABLE
    private void loadTable() {
        ObservableList<Customer> list =
                FXCollections.observableArrayList(dao.getAllCustomers());

        customerTable.setItems(list);

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCustomerId()).asObject());
        colName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCustomerName()));
        colPhone.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPhoneNumber()));
        colEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCustomerEmail()));
        colAddress.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCustomerAddress()));
        colDate.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getRegistrationDate()));
    }

    // ADD
    @FXML
    public void addCustomer() {

        Customer c = new Customer(
                nameField.getText(),
                phoneField.getText(),
                emailField.getText(),
                addressField.getText(),
                Date.valueOf(datePicker.getValue())
        );

        dao.insertCustomer(c);
        loadTable();
        clearFields();
    }

    // UPDATE
    @FXML
    public void updateCustomer() {

        Customer c = new Customer(
                Integer.parseInt(idField.getText()),
                nameField.getText(),
                phoneField.getText(),
                emailField.getText(),
                addressField.getText(),
                Date.valueOf(datePicker.getValue())
        );

        dao.updateCustomer(c);
        loadTable();
        clearFields();
    }

    // DELETE
    @FXML
    public void deleteCustomer() {

        int id = Integer.parseInt(idField.getText());

        dao.deleteCustomer(id);
        loadTable();
        clearFields();
    }

    // SEARCH
    @FXML
    public void searchCustomer() {

        int id = Integer.parseInt(idField.getText());

        Customer c = dao.searchCustomer(id);

        if (c != null) {
            nameField.setText(c.getCustomerName());
            phoneField.setText(c.getPhoneNumber());
            emailField.setText(c.getCustomerEmail());
            addressField.setText(c.getCustomerAddress());
            datePicker.setValue(c.getRegistrationDate().toLocalDate());
        }
    }

    // CLEAR
    @FXML
    public void clearFields() {

        idField.clear();
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
        datePicker.setValue(null);
    }
}