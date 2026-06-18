package supermarket_db;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

    	FXMLLoader loader =
    		   new FXMLLoader(Main.class.getResource("Supplier.fxml"));
    			// new FXMLLoader(Main.class.getResource("Customer.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setTitle("Supermarket System");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}