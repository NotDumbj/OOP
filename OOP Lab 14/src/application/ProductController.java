package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductController implements Initializable{
    @FXML
    private TableView<Product> tableView;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private VBox toggle;
    @FXML
    private TextField productname;
    @FXML
    private TextField productprice;
    @FXML
    private Button showbtn;
    @FXML
    private Button showprobtn;

    private DatabaseConnection databaseConnection = new DatabaseConnection();

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	toggle.setVisible(false);
    	
    	idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
	}
    
    @FXML
    public void showproducts() {
       tableView.setItems(getProducts());
       if(idColumn.getCellData(1) == null) {
    	   showprobtn.setDisable(false);
       }
       else {
    	   showprobtn.setDisable(true);    	   
       }
       
    }


    
    private ObservableList<Product> getProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        String query = "SELECT * FROM products";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                products.add(new Product(id, name, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    
    
    @FXML
    private void showtoggle() {
    	if(showbtn.getText().equalsIgnoreCase("Show Add Section")) {
    		toggle.setVisible(true);
    		showbtn.setText("Hide Add Section");    		
    	}
    	else if(showbtn.getText().equalsIgnoreCase("Hide Add Section")) {
    		toggle.setVisible(false);
    		showbtn.setText("Show Add Section");    		
    	}
    }
    
    @FXML
    private void addproduct() {
		String pronam = productname.getText();
		String propri = productprice.getText();
		String sqlquery = "INSERT INTO products (name, price) VALUES (?,?)";
		try {
			Connection connection = databaseConnection.getConnection();
			PreparedStatement pt = connection.prepareStatement(sqlquery);
			pt.setString(1, pronam);
			pt.setString(2, propri);
			
			int rowsaffected = pt.executeUpdate();
			
			if(rowsaffected > 0) {
	            Alert signupSuccess = new Alert(AlertType.INFORMATION);
                signupSuccess.setTitle("Success");
                signupSuccess.setHeaderText(null);
                signupSuccess.setContentText("Product Added successfully!");
                signupSuccess.showAndWait();
                tableView.setItems(getProducts());
			}
			else {
			    Alert signupFailed = new Alert(AlertType.ERROR);
	            signupFailed.setTitle("Error");
	            signupFailed.setHeaderText(null);
	            signupFailed.setContentText("Failed to add product. Please try again.");
	            signupFailed.showAndWait();
			}
			
			pt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
