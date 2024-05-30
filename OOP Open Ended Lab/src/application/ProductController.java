package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProductController implements Initializable{
	@FXML
	TilePane tilepane;
	
	List<String> names = new ArrayList<>();
	
	DatabaseConnection db = new DatabaseConnection();
	
	 private VBox createProductVBox(String productName, String imagePath, Double price, String productdescription) {
	        VBox vbox = new VBox();
	        vbox.setAlignment(javafx.geometry.Pos.TOP_CENTER);
	        vbox.setPrefHeight(275.0);
	        vbox.setPrefWidth(234.0);
	        vbox.setSpacing(9.0);
	        vbox.setPadding(new Insets(9, 0, 0 , 9));
	        vbox.setStyle("-fx-border-color: #686D76; -fx-background-color: #C3FF93; -fx-border-radius: 3px;");

	        Label nameLabel = new Label(productName);
	        nameLabel.setFont(new Font("Pivot Classic", 12.0));

	        VBox imgviewer = new VBox();
	        imgviewer.setMinHeight(150.0);
	        imgviewer.setAlignment(javafx.geometry.Pos.CENTER);
	        imgviewer.setMinWidth(145.0);
	        
	        ImageView imageView = new ImageView();
	        try {
	        	Image img = new Image(imagePath);
	        	imageView.setImage(img);	        	
	        }catch (Exception e){
	        	System.out.println("Error Occur:- " + e.getMessage());
	        }
	        imageView.setFitHeight(130.0);
	        imageView.setFitWidth(145.0);
	        imageView.setPickOnBounds(true);
	        imageView.setPreserveRatio(true);
	        imageView.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

	        imgviewer.getChildren().add(imageView);
	        
	        VBox desc = new VBox();
	        desc.setAlignment(javafx.geometry.Pos.TOP_CENTER);
	        
	        HBox descBox = new HBox();
	        descBox.setPrefHeight(18.0);
	        descBox.setPrefWidth(250.0);
	        descBox.setSpacing(20.0);

	        Label descLabel = new Label("Description: ");
	        descLabel.setFont(new Font("Pivot Classic", 12.0));
	        descBox.getChildren().add(descLabel);
	        
	        Label descriptionLabel = new Label(productdescription);
	        descriptionLabel.setMaxWidth(214.0);
	        descriptionLabel.setPrefHeight(21.0);
	        descriptionLabel.setPrefWidth(214.0);
	        descriptionLabel.setAlignment(javafx.geometry.Pos.CENTER);
	        
	        desc.getChildren().addAll(descBox, descriptionLabel);
	        
	        HBox priceBox = new HBox();
	        priceBox.setPrefHeight(18.0);
	        priceBox.setPrefWidth(250.0);
	        priceBox.setSpacing(20.0);

	        Label priceLabel = new Label("Price: ");
	        priceLabel.setFont(new Font("Pivot Classic", 12.0));
	        priceBox.getChildren().add(priceLabel);

	        String pri = Double.toString(price);
	        Label priceValueLabel = new Label(pri);
	        priceBox.getChildren().add(priceValueLabel);

	        vbox.getChildren().addAll(nameLabel, imgviewer, desc, priceBox);
	        return vbox;
	    }
	 
	 int readdata(Connection co) throws SQLException {
		 String query = "SELECT * FROM products";
		 PreparedStatement pb = co.prepareStatement(query);
		 ResultSet rs = pb.executeQuery();
		 
		 while(rs.next()) {
			 String pn = rs.getString("name");
			 String ip = rs.getString("img");
			 String pd = rs.getString("description");
			 Double pr = rs.getDouble("price");
			 
			 boolean same = false;
			 
			 if(!(names.isEmpty())) {
				 for (String n : names) {
					 if(pn.equals(n)) {
						 System.out.println("Info: Same Product Found");
						 same = true;
					 }
				 }
			 }
			 if(same == false) {
				 names.add(pn);
				 VBox vb = createProductVBox(pn,ip,pr,pd);
				 
				 tilepane.getChildren().add(vb);
			 }
		 }
		return 0;
	 }
	 
	 @FXML
	 private void refresh (){
		 initialize(null, null);
	 }
	 
	 @FXML
	 private void showaddsection(ActionEvent event) {
		    try {
		        Scene root = new Scene(FXMLLoader.load(getClass().getResource("addproduct.fxml")));
		        Stage scn = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        
		        scn.setScene(root);
		        scn.setTitle("Add Product");
		        scn.show();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	 }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Connection con = db.getConnection();
			readdata(con);
		} catch (SQLException e) {
			System.out.println("Some Error Occur. Error Msg : " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
