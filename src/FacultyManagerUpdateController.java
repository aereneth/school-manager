import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;

public class FacultyManagerUpdateController implements Initializable {
    Teacher teacher = new Teacher();
    public Label idLabel;
    public Label nameLabel;
    public TextField phoneNumberField;
    public TextField addressField;
    public ComboBox<String> departmentField;
    public ComboBox<String> designationField;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        departmentField.getItems().addAll("Business", "Computing");
        designationField.getItems().addAll("Head of Faculty", "Coordinator", "Lecturer");
    }
    
    public void init(Teacher teacher) {
        this.teacher = teacher;
        idLabel.setText(String.format("%d", teacher.getId()));
        nameLabel.setText(String.format("%s, %s", teacher.getLastName().toUpperCase(), teacher.getFirstName().toUpperCase()));
        phoneNumberField.setText(teacher.getPhoneNumber());
        addressField.setText(teacher.getAddress());
        
        departmentField.getSelectionModel().select(teacher.getDepartment());
        designationField.getSelectionModel().select(teacher.getDesignation());
    }
    
    public void onSave() {
        Teacher teacher = new Teacher();
        teacher.setPhoneNumber(phoneNumberField.getText());
        teacher.setAddress(addressField.getText());
        teacher.setDepartment(departmentField.getSelectionModel().getSelectedItem());
        teacher.setDesignation(designationField.getSelectionModel().getSelectedItem());
        
        String query = String.format("UPDATE TEACHERS SET phone_number='%s', address='%s', department='%s', designation='%s', salary=%f WHERE id=%d",
            phoneNumberField.getText(),
            addressField.getText(),
            departmentField.getSelectionModel().getSelectedItem(),
            designationField.getSelectionModel().getSelectedItem(),
            teacher.getNetSalary(),
            teacher.getId());
        Database.execute(query);
        
        AlertBox.display("Faculty Manager", "Faculty is successfully updated");
        
        FXMLLoader facultyManager = new FXMLLoader(getClass().getResource("faculty_manager.fxml"));

        try{
            Main.setRoot(facultyManager.load());
        } catch(Exception e) {}
    }
    
    public void onReturn() {
        FXMLLoader studentManager = new FXMLLoader(getClass().getResource("faculty_manager.fxml"));

        try{
            Main.setRoot(studentManager.load());
        } catch(Exception e) {}
    }
}
