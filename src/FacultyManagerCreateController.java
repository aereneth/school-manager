import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;

public class FacultyManagerCreateController implements Initializable {

    public TextField firstNameField;
    public TextField lastNameField;
    public TextField phoneNumberField;
    public TextField addressField;
    public ComboBox<String> genderField;
    public ComboBox<String> departmentField;
    public ComboBox<String> designationField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderField.getItems().addAll("Male", "Female");
        departmentField.getItems().addAll("Business", "Computing");
        designationField.getItems().addAll("Head of Faculty", "Coordinator", "Lecturer");
    }

    public void onCreate() {
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstNameField.getText());
        teacher.setLastName(lastNameField.getText());
        teacher.setGender(genderField.getSelectionModel().getSelectedItem() == "Male" ? 'M' : 'F');
        teacher.setPhoneNumber(phoneNumberField.getText());
        teacher.setAddress(addressField.getText());
        teacher.setDepartment(departmentField.getSelectionModel().getSelectedItem());
        teacher.setDesignation(designationField.getSelectionModel().getSelectedItem());
        
        String query = String.format("INSERT INTO TEACHERS(first_name, last_name, gender, phone_number, address, designation, department, salary) VALUES('%s', '%s', %d, '%s', '%s', '%s', '%s', %f)",
            firstNameField.getText(),
            lastNameField.getText(),
            genderField.getSelectionModel().getSelectedItem() == "Male" ? 1 : 0,
            phoneNumberField.getText(),
            addressField.getText(),
            designationField.getSelectionModel().getSelectedItem(),
            departmentField.getSelectionModel().getSelectedItem(),
            teacher.getNetSalary());
        Database.execute(query);
        
        AlertBox.display("Faculty Manager", "Faculty is successfully registered");

        firstNameField.setText("");
        lastNameField.setText("");
        phoneNumberField.setText("");
        addressField.setText("");

        genderField.getSelectionModel().selectFirst();
        designationField.getSelectionModel().selectFirst();
        departmentField.getSelectionModel().selectFirst();

        FXMLLoader studentManager = new FXMLLoader(getClass().getResource("faculty_manager.fxml"));
        
        try{
            Main.setRoot(studentManager.load());
        } catch(Exception e) {}
    }

    public void onReturn() {
        FXMLLoader studentManager = new FXMLLoader(getClass().getResource("faculty_manager.fxml"));
        
        try{
            Main.setRoot(studentManager.load());
        } catch(Exception e) {}
    }
}
