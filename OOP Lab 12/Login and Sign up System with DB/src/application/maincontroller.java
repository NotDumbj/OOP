package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class maincontroller implements Initializable{
	//login
	@FXML TextField loginusername;
	@FXML PasswordField loginpasswordp;
	//sign-up
	@FXML TextField signupusername;
	@FXML PasswordField signuppasswordp;
	@FXML PasswordField signupconfirmpasswordp;
	
	//database connection
	public static Connection c;
	public static Connection connecttoserverdb() throws ClassNotFoundException, SQLException {
	    Connection conn = null;

	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

	        String dbURL = "jdbc:sqlserver://TECHNOMANCER\\Shop:1433;databaseName=dbTest;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
	        String user = "test";
	        String pass = "pak123";
	        conn = DriverManager.getConnection(dbURL, user, pass);

	        if (conn != null) {
	            System.out.println("The connection has been successfully established.");

	            DatabaseMetaData dm = conn.getMetaData();
	            System.out.println("Driver name: " + dm.getDriverName());
	            System.out.println("Driver version: " + dm.getDriverVersion());
	            System.out.println("Product name: " + dm.getDatabaseProductName());
	            System.out.println("Product version: " + dm.getDatabaseProductVersion());
	        }
	    } catch (SQLException ex) {
	        System.out.println("An error occurred while establishing the connection:");
	        ex.printStackTrace();
	    }
	    c = conn;
	    return conn;
	}

	//login
	
	@FXML
	private void login() {
		String username = loginusername.getText();
		String password = loginpasswordp.getText();
		
		String sqlquery = "SELECT * FROM usersdata WHERE username=? AND password=?";
		
		try {
			PreparedStatement pst = c.prepareStatement(sqlquery);
			pst.setString(1, username);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				Alert loginsuccess = new Alert(AlertType.INFORMATION);
				loginsuccess.setTitle("Success Message");
				loginsuccess.setHeaderText(null);
				loginsuccess.setContentText("Login Successful");
				loginsuccess.showAndWait();
			}
			else {
				Alert loginfailed = new Alert(AlertType.ERROR);
				loginfailed.setTitle("Error Message");
				loginfailed.setHeaderText(null);
				loginfailed.setContentText("Login Failed. Enter Correct Info.");
				loginfailed.showAndWait();
			}
			
			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		    if (c != null) {
		        try {
		            c.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
		
	}
	

	@FXML 
	private void gotosignform(MouseEvent event) {
	    try {
	        Scene root = new Scene(FXMLLoader.load(getClass().getResource("signup.fxml")));
	        Stage scn = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        
	        scn.setScene(root);
	        scn.setTitle("Signup System");
	        scn.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	//sign-up
	
	@FXML
	private void gotologinform(MouseEvent e) {
		try {
			Scene root = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
			Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
			
			st.setScene(root);
			st.setTitle("Login System");
			st.show();
			
		} catch (IOException error) {
			error.printStackTrace();
		}
	}
	
	@FXML 
	private void signup() {
		String usern = signupusername.getText();
		String pass = signuppasswordp.getText();
		String cpass = signupconfirmpasswordp.getText();
		
		if(pass.equalsIgnoreCase(cpass)) {
			String sqlquery = "INSERT INTO usersdata (username, password) VALUES (?,?)";
			try {
				PreparedStatement pt = c.prepareStatement(sqlquery);
				pt.setString(1, usern);
				pt.setString(2, pass);
				
				int rowsaffected = pt.executeUpdate();
				
				if(rowsaffected > 0) {
		            Alert signupSuccess = new Alert(AlertType.INFORMATION);
	                signupSuccess.setTitle("Success");
	                signupSuccess.setHeaderText(null);
	                signupSuccess.setContentText("User registered successfully!");
	                signupSuccess.showAndWait();
				}
				else {
				    Alert signupFailed = new Alert(AlertType.ERROR);
		            signupFailed.setTitle("Error");
		            signupFailed.setHeaderText(null);
		            signupFailed.setContentText("Failed to register user. Please try again.");
		            signupFailed.showAndWait();
				}
				
				pt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
	        Alert passwordMismatch = new Alert(AlertType.ERROR);
	        passwordMismatch.setTitle("Error");
	        passwordMismatch.setHeaderText(null);
	        passwordMismatch.setContentText("Passwords don't match. Please try again.");
	        passwordMismatch.showAndWait();
		}
		
	}
	
	//form load function
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			connecttoserverdb();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
