package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        VBox root = FXMLLoader.load(getClass().getResource("ProductRegister.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Product Registry System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}