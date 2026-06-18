package supermarket_db;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CategoryController {
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtSearch;
	
	@FXML
    private TableView<Category> categoryTable;
	
	@FXML
    private TableColumn<Category,Integer> colid;
	
	@FXML
    private TableColumn<Category,String> colname;
	
	
	private ObservableList <Category> list = FXCollections.observableArrayList();
	
	private CategoryDAO categoryDAO = new CategoryDAO();
	
	@FXML
	public void initialize () {
		colid.setCellValueFactory(d -> new SimpleObjectProperty <>(d.getValue().getId()));
		
		colname.setCellValueFactory(d-> new SimpleStringProperty(d.getValue().getName()));
		
		loadCategories();
		
		// track which row user clicks on or highlights inside the UI
        categoryTable.getSelectionModel().selectedItemProperty()
        
        // listen for changes in which row user clicks
        .addListener((obs, oldVal, newVal)->{

        if(newVal != null)
        {

        // fill in text boxes for newly selected Product object
        txtName.setText(newVal.getName());

     
        }

        });
	}
	
	
	@FXML
    private void addCategory() {

        try {

            if(txtName.getText().isEmpty()) {

                showAlert( Alert.AlertType.ERROR,
                        "Category name cannot be empty.");
                return;
            }

            Category c =
                   new Category(
                            0,
                            txtName.getText()
                    );

            boolean success = categoryDAO.addCategory(c);

            if (success) {
                loadCategories();
                clearFields();

                showAlert(Alert.AlertType.INFORMATION,
                        "Category added successfully!");
            } 
            else {
                showAlert(Alert.AlertType.ERROR,
                        "Category already exists or could not be added.");
            }
        }

        catch(Exception e) {

            e.printStackTrace();

            showAlert(Alert.AlertType.ERROR,
                    "Something went wrong."
            );
        }
    }
	
	
	
	 @FXML
	 private void deleteCategory() {

	        Category selected = categoryTable.getSelectionModel().getSelectedItem();

	        if(selected == null)
	            return;

	        Alert alert =
	                new Alert(Alert.AlertType.CONFIRMATION);

	        alert.setContentText(
	                "Delete this category?"
	        );

	        if(alert.showAndWait().get() == ButtonType.OK) {

	            boolean success = categoryDAO.deleteCategory(
	                    selected.getId());
	            if (success) {
	            showAlert(Alert.AlertType.INFORMATION,
	                    "Category deleted successfully!");


	            loadCategories();}
	            
	            else{

	                showAlert(
	                        Alert.AlertType.ERROR,
	                        "Cannot delete category.");
	            }
	        }
	    }
	 
	 

	private void loadCategories() {

        list.clear();

        list.addAll(
                categoryDAO.getAllCategories()
        );

        categoryTable.setItems(list);
    }	
	

	
	@FXML
    private void searchCategory(){
    	
    	// empty list
        list.clear();

        // append multiple items at once
        list.addAll(categoryDAO.searchCategories(txtSearch.getText()));
    // contents automatically altered in TableVeiw bcuz list is an Observable list
    }
	
	
	 @FXML
	    private void updateCategory()
	    {
	        Category selected = categoryTable.getSelectionModel().getSelectedItem();

	        if(selected==null)

	            return;

	        Category c =

	                new Category(
	                selected.getId(),
	                txtName.getText()
	               );

	        categoryDAO.updateCategory(c);
	        
	        showAlert(Alert.AlertType.INFORMATION,
	                "Category updated successfully!"
	        );

	        loadCategories();

	        clearFields();
	    }
	 
	 
	
private void clearFields() {
	    	// reset text boxes
	        txtName.clear();
	    }
	    
	    
private void showAlert(Alert.AlertType type,String message) {

	        Alert alert = new Alert(type);

	        alert.setTitle("Message");

	        alert.setHeaderText(null);

	        alert.setContentText(message);

	        alert.showAndWait();
	    }
	
	
}