package application;
	
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public int counter = 0;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader haha = new FXMLLoader(getClass().getResource("hellothere.fxml"));
        VBox letsrunit = haha.load();
        Scene scene = new Scene(letsrunit, 720, 480);
        

        primaryStage.setTitle("Event Handling Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
