package supermarket_db;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmployeeController {

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtPosition;

	@FXML
	private TextField txtSalary;

	@FXML
	private TextField txtHireDate;

	@FXML
	private TextField txtManagerId;

	@FXML
	private TextField txtSearch;

	@FXML
	private TableView<Employee> employeeTable;

	@FXML
	private TableColumn<Employee, String> colName;

	@FXML
	private TableColumn<Employee, String> colPosition;

	@FXML
	private TableColumn<Employee, Double> colSalary;

	@FXML
	private TableColumn<Employee, String> colHireDate;

	@FXML
	private TableColumn<Employee, Integer> colManagerId;

	private ObservableList<Employee> list = FXCollections.observableArrayList();

	private EmployeeDAO employeeDAO = new EmployeeDAO();

	@FXML
	public void initialize() {

		colName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));

		colPosition.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPosition()));

		colSalary.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getSalary()));

		colHireDate.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getHireDate()));

		colManagerId.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getManagerId()));

		loadEmployees();

		// track which row user clicks on or highlights
		employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {

			if (newVal != null) {

				// fill in text boxes for newly selected product
				txtName.setText(newVal.getName());

				txtPosition.setText(newVal.getPosition());

				txtSalary.setText(String.valueOf(newVal.getSalary()));

				txtHireDate.setText(newVal.getHireDate());

				txtManagerId.setText(String.valueOf(newVal.getManagerId()));
			}
		});
	}

	@FXML
	private void addEmployee() {

		try {
			if (txtName.getText().isEmpty()) {

				showAlert(Alert.AlertType.ERROR, "Employee name cannot be empty.");
				return;
			}

			if (txtPosition.getText().isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Position cannot be empty.");
				return;
			}

			Employee e = new Employee(0, txtName.getText(), txtPosition.getText(),
					Double.parseDouble(txtSalary.getText()), txtHireDate.getText(),
					Integer.parseInt(txtManagerId.getText()));

			boolean success = employeeDAO.addEmployee(e);
			if (success) {
				loadEmployees();

				clearFields();

				showAlert(Alert.AlertType.INFORMATION, "Employee added successfully!");
			}

		} catch (Exception ex) {

			ex.printStackTrace();
			showAlert(Alert.AlertType.ERROR, "Something went wrong.");
		}
	}

	@FXML
	private void deleteEmployee() {

		Employee selected = employeeTable.getSelectionModel().getSelectedItem();

		if (selected == null)
			return;

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

		alert.setContentText("Delete this employee?");

		if (alert.showAndWait().get() == ButtonType.OK) {

			boolean success = employeeDAO.deleteEmployee(selected.getId());
			if (success) {
				loadEmployees();

				showAlert(Alert.AlertType.INFORMATION, "Employee deleted successfully!");
			}

			else {

				showAlert(Alert.AlertType.ERROR, "Cannot delete this employee.");
			}
		}
	}

	@FXML
	private void updateEmployee() {

		Employee selected = employeeTable.getSelectionModel().getSelectedItem();

		if (selected == null)
			return;

		try {

			double salary = Double.parseDouble(txtSalary.getText());

			int managerId = Integer.parseInt(txtManagerId.getText());

			if (salary < 0) {

				showAlert(Alert.AlertType.ERROR, "Salary cannot be negative.");
				return;
			}
			if (txtName.getText().isEmpty()) {

				showAlert(Alert.AlertType.ERROR, "Employee name cannot be empty.");
				return;
			}
			if (txtPosition.getText().isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Position cannot be empty.");
				return;
			}

			Employee e = new Employee(selected.getId(), txtName.getText(), txtPosition.getText(), salary,
					txtHireDate.getText(), managerId);

			employeeDAO.updateEmployee(e);

			loadEmployees();

			clearFields();

			showAlert(Alert.AlertType.INFORMATION, "Employee updated successfully!");

		} catch (NumberFormatException ex) {

			showAlert(Alert.AlertType.ERROR, "Salary and Manager ID must be numeric.");
		}
	}

	@FXML
	private void searchEmployee() {
		// empty list
		list.clear();
		// append multiple items at once
		list.addAll(employeeDAO.searchEmployees(txtSearch.getText()));
	}

	private void loadEmployees() {

		list.clear();

		list.addAll(employeeDAO.getAllEmployees());

		employeeTable.setItems(list);
	}

	private void clearFields() {
		// reset text boxes
		txtName.clear();
		txtPosition.clear();
		txtSalary.clear();
		txtHireDate.clear();
		txtManagerId.clear();
	}

	private void showAlert(Alert.AlertType type, String message) {

		Alert alert = new Alert(type);

		alert.setTitle("Message");

		alert.setHeaderText(null);

		alert.setContentText(message);

		alert.showAndWait();
	}
}