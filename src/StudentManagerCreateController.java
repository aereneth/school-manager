import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;

public class StudentManagerCreateController implements Initializable {

    public TextField firstNameField;
    public TextField lastNameField;
    public TextField phoneNumberField;
    public TextField addressField;
    public ComboBox<String> genderField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderField.getItems().addAll("Male", "Female");
    }

    public void onCreate() {
        String query = String.format("INSERT INTO STUDENTS(first_name, last_name, gender, phone_number, address) VALUES('%s', '%s', %d, '%s', '%s')",
            firstNameField.getText(),
            lastNameField.getText(),
            genderField.getSelectionModel().getSelectedItem() == "Male" ? 1 : 0,
            phoneNumberField.getText(),
            addressField.getText());
        Database.execute(query);
        
        AlertBox.display("Student Manager", "Student is successfully registered");

        firstNameField.setText("");
        lastNameField.setText("");
        phoneNumberField.setText("");
        addressField.setText("");

        genderField.getSelectionModel().selectFirst();

        FXMLLoader studentManager = new FXMLLoader(getClass().getResource("student_manager.fxml"));
        
        try{
            Main.setRoot(studentManager.load());
        } catch(Exception e) {}
    }

    public void onReturn() {
        FXMLLoader studentManager = new FXMLLoader(getClass().getResource("student_manager.fxml"));
        
        try{
            Main.setRoot(studentManager.load());
        } catch(Exception e) {}
    }
}
