package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene root = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
			primaryStage.setScene(root);	
			primaryStage.setTitle("Login System");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		launch(args);
	}
}
