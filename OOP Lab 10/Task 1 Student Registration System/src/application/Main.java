package application;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Main extends Application {
	
	public class Student {

	    private final StringProperty name;
	    private final StringProperty gender;
	    private final StringProperty email;
	    private final StringProperty id;

	    public Student(String name, String gender, String email, String id) {
	        this.name = new SimpleStringProperty(name);
	        this.gender = new SimpleStringProperty(gender);
	        this.email = new SimpleStringProperty(email);
	        this.id = new SimpleStringProperty(id);
	    }

	    public StringProperty nameProperty() {
	        return name;
	    }

	    public StringProperty genderProperty() {
	        return gender;
	    }

	    public StringProperty emailProperty() {
	        return email;
	    }

	    public StringProperty idProperty() {
	        return id;
	    }

	    public String getName() {
	        return name.get();
	    }

	    public void setName(String name) {
	        this.name.set(name);
	    }

	    public String getGender() {
	        return gender.get();
	    }

	    public void setGender(String gender) {
	        this.gender.set(gender);
	    }

	    public String getEmail() {
	        return email.get();
	    }

	    public void setEmail(String email) {
	        this.email.set(email);
	    }

	    public String getId() {
	        return id.get();
	    }

	    public void setId(String id) {
	        this.id.set(id);
	    }
	}
	
	private ObservableList<Student> students = FXCollections.observableArrayList();
	@Override
	public void start(Stage primaryStage) {

	    Label studentname = new Label("Enter your Name: ");
	    Label studentemail = new Label("Enter your Email: ");
	    Label studentid = new Label("Enter your ID: ");
	    Label studentgender = new Label("Select your Gender: ");

	    TextField studentnamet = new TextField();
	    TextField studentemailt = new TextField();
	    TextField studentidt = new TextField();

	    ToggleGroup genderGroup = new ToggleGroup();
	    RadioButton maleRadio = new RadioButton("Male");
	    maleRadio.setToggleGroup(genderGroup);
	    RadioButton femaleRadio = new RadioButton("Female");
	    femaleRadio.setToggleGroup(genderGroup);

	    CheckBox termscond = new CheckBox("Accept our Terms and Conditions");

	    Button btn = new Button("Register");

	    VBox studentform = new VBox();
	    studentform.getChildren().addAll(studentname,studentnamet,studentemail,studentemailt,studentid,studentidt, studentgender, new HBox (10, maleRadio, femaleRadio), termscond, btn);

	    TableView<Student> table = new TableView<>();

	    TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
	    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

	    TableColumn<Student, String> idColumn = new TableColumn<>("ID");
	    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

	    TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
	    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

	    TableColumn<Student, String> genderColumn = new TableColumn<>("Gender");
	    genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

	    table.getColumns().addAll(nameColumn, idColumn, emailColumn, genderColumn);
	    table.setItems(students);

	    VBox tablesection = new VBox();
	    tablesection.getChildren().addAll(table);

	    btn.setOnAction(event -> {
	        if (genderGroup.getSelectedToggle() != null) {
	            String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
	            students.add(new Student(studentnamet.getText(), gender, studentemailt.getText(), studentidt.getText()));
	        }
	    });

	    VBox mainholder = new VBox(10);
	    mainholder.setPadding(new Insets(20));
	    mainholder.getChildren().addAll(studentform, tablesection);

	    Scene scene = new Scene(mainholder, 500, 500);

	    primaryStage.setScene(scene);
	    primaryStage.setTitle("Student Registration Form");
	    primaryStage.show();
	}


	public static void main(String[] args) {
	launch(args);
	}
}
