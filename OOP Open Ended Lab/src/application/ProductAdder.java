package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProductAdder {
	DatabaseConnection db = new DatabaseConnection();
	String imagePath;
	@FXML TextField productname, productprice;
	@FXML TextArea productdescription;
	
	
	@FXML private void gotoproductshow(MouseEvent event) {
		   try {
		        Scene root = new Scene(FXMLLoader.load(getClass().getResource("ProductShower.fxml")));
		        Stage scn = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        
		        scn.setScene(root);
		        scn.setTitle("Daraz - Online Shopping App");
		        scn.show();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
	@FXML
	private void addproduct() {
		String proname = productname.getText();
		String proprice = productprice.getText();
		String prodesc = productdescription.getText();
		
		if(imagePath.equals(null)) 
		{
			System.out.println("Select the Image Path");
		}
		else {
			String query = "insert into products (name, description, img, price) values (?, ? ,? ,?);";
			Connection con;
			try {
				con = db.getConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, proname);
				ps.setString(2, prodesc);
				ps.setString(3, imagePath);
				ps.setString(4, proprice);
				
				int rowsaffected = ps.executeUpdate();
				
				if(rowsaffected > 0) {
					System.out.println("Product Added!");
				}
				else {
					System.out.println("Some Error Occured");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	 @FXML private void selectImage() {
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
	        );
	        File selectedFile = fileChooser.showOpenDialog(null);

	        if (selectedFile != null) {
	            imagePath = processSelectedImage(selectedFile);
	            System.out.println("Selected image path: " + imagePath);
	        }
	    }

	    private String processSelectedImage(File selectedFile) {
	        String targetFolder = "images/";
	        File folder = new File(targetFolder);
	        if (!folder.exists()) {
	            folder.mkdirs(); // Create the folder if it doesn't exist
	        }

	        // Generate a unique file name for the image
	        String fileName = System.currentTimeMillis() + "_" + selectedFile.getName();
	        File destFile = new File(folder, fileName);

	        try {
	            // Copy the selected image file to the target folder
	            Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            return "/" + fileName; // Return the image path
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
}
