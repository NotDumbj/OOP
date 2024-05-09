package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MainController {
    @FXML private DatePicker dp;
    @FXML private Button btn;
    @FXML private TextField tb;
    @FXML private Button tbbtn;
    @FXML private VBox labchild;
    @FXML private ScrollPane sp; // Add this field

    private int counter = 0;

    @FXML
    private void handleButtonC() {
        System.out.println("Button Clicked!");
        File file = new File("data.txt");
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("Hello, File!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    private void dpaction() {
    	System.out.println("Date Selected : " + dp.getValue());
    }
    
    @FXML
    private void parentClicked() {
    	System.out.println("Where you think you are clicking ??");
    }
    
    @FXML
    private void handleShowButtonClick() {
        String st = tb.getText();
        if (st.isEmpty()) {
            if (counter == 0) {
                Label newlabel = new Label("I see what u did there. Just type something");
                labchild.getChildren().add(newlabel);
                counter++;
            } else if (counter == 1) {
                Label newlabel = new Label("I said type something to show here");
                labchild.getChildren().add(newlabel);
                counter++;
            } else if (counter == 2) {
                Label newlabel = new Label("I am just gonna put null here, if u do that again");
                labchild.getChildren().add(newlabel);
                counter++;
            } else {
                Label newlabel = new Label("null");
                labchild.getChildren().add(newlabel);
            }
        } else {
            Label newlabel = new Label(st);
            labchild.getChildren().add(newlabel);
        }
        tb.setText("");
        if(this.sp != null) {
        	sp.setVvalue(1.0); // Scroll to the bottom        	        	
        }
        
    }
}
