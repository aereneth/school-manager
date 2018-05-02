import javafx.fxml.*;
import javafx.scene.control.*;

public class FacultyViewerController {
    Teacher teacher;
    public Label nameLabel;
    public Label phoneNumberLabel;
    public Label addressLabel;
    public Label departmentLabel;
    public Label designationLabel;
    public Spinner attendanceField;
    SpinnerValueFactory<Integer> attendanceValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 8);
    
    public void init(Teacher teacher) {
        this.teacher = teacher;
        nameLabel.setText(String.format("%s, %s", teacher.getLastName().toUpperCase(), teacher.getFirstName().toUpperCase()));
        phoneNumberLabel.setText(teacher.getPhoneNumber());
        addressLabel.setText(teacher.getAddress());
        
        departmentLabel.setText(teacher.getDepartment());
        designationLabel.setText(teacher.getDesignation());
        attendanceField.setValueFactory(attendanceValueFactory);
    }
    
    public void onAttendance() {
        String query = String.format("INSERT INTO ATTENDANCES(teacher_id, hours) VALUES(%d, %d)",
            teacher.getId(), attendanceValueFactory.getValue());
        Database.execute(query);
        
        AlertBox.display("Attendance", "Your attendance is recorded");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_menu.fxml"));
        
        try{
            Main.setRoot(loader.load());
        } catch(Exception e) {}
    }
    
    public void onReturn() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_menu.fxml"));
        
        try{
            Main.setRoot(loader.load());
        } catch(Exception e) {}
    }
}
