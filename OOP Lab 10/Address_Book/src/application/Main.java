package application;
	
import java.util.Iterator;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class Main extends Application {
	public class Address {

	    private final StringProperty name;
	    private final StringProperty email;
	    private final StringProperty phoneNumber;

	    public Address(String name, String email, String phoneNumber) {
	        this.name = new SimpleStringProperty(name);
	        this.email = new SimpleStringProperty(email);
	        this.phoneNumber = new SimpleStringProperty(phoneNumber);
	    }

	    // Getter and setter methods for name
	    public String getName() {
	        return name.get();
	    }

	    public void setName(String name) {
	        this.name.set(name);
	    }

	    public StringProperty nameProperty() {
	        return name;
	    }

	    // Getter and setter methods for email
	    public String getEmail() {
	        return email.get();
	    }

	    public void setEmail(String email) {
	        this.email.set(email);
	    }

	    public StringProperty emailProperty() {
	        return email;
	    }

	    // Getter and setter methods for phoneNumber
	    public String getPhoneNumber() {
	        return phoneNumber.get();
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber.set(phoneNumber);
	    }

	    public StringProperty phoneNumberProperty() {
	        return phoneNumber;
	    }
	}
	
	private ObservableList<Address> data = FXCollections.observableArrayList(); 
	@Override
	public void start(Stage primaryStage) {
		
		VBox root1 = new VBox();
		Label name = new Label("Enter Name : ");
		TextField nametf = new TextField();
		Label phonenumber = new Label("Enter Phone Number : ");
		TextField phonenumbertf = new TextField();
		Label email = new Label("Enter Email : ");
		TextField emailtf = new TextField();
		
		VBox headingcontainer = new VBox();
		headingcontainer.setAlignment(Pos.CENTER);
		Label heading = new Label("Address Book");
		heading.getStyleClass().add("heading");
		headingcontainer.getChildren().add(heading);
		
		
		TableView<Address> table = new TableView<>();
		TableColumn<Address, String> nameColumn = new TableColumn<>("Name");
	    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	    TableColumn<Address, String> phoneNumberColumn = new TableColumn<>("Phone Number");
	    phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
	    TableColumn<Address, String> emailColumn = new TableColumn<>("Email");
	    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

	    table.getColumns().addAll(nameColumn, phoneNumberColumn, emailColumn);	
	    table.editableProperty().set(false);
	    table.setItems(data);
	    
		Button add = new Button("Save Contact");
		add.setOnAction(e -> {
		    String Name = nametf.getText();
		    String PhoneNumber = phonenumbertf.getText();
		    String Email = emailtf.getText();


		    boolean exists = false;
		    for (Address address : data) {
		        if (address.getName().equals(Name) || address.getPhoneNumber().equals(PhoneNumber) || address.getEmail().equals(Email)) {
		            exists = true;
		            break;
		        }
		    }

		    if (!exists) {
		        Address adr = new Address(Name, Email, PhoneNumber);
		        data.add(adr);
		    } else {

		        System.out.println("The data already exists in the list.");
		    }
		});


		root1.setPadding(new Insets(20));
		root1.getChildren().addAll(headingcontainer, name, nametf, phonenumber, phonenumbertf, email, emailtf);
		
		
		VBox root = new VBox();
		root.setSpacing(15);
		root.getChildren().addAll(root1, add, table);
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.CENTER);
		
		Scene scn = new Scene(root , 1024, 920);
		
		scn.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		primaryStage.setScene(scn);
		primaryStage.setTitle("Address Book");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
