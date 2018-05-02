import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.*;

public class StudentManagerUpdateController implements Initializable {
    Student student;
    public Label idLabel;
    public Label nameLabel;
    public Label balanceLabel;
    public Label amountPaidLabel;
    public Label tuitionFeeLabel;
    public Label totalModuleLabel;
    public Spinner oldModuleField;
    public Spinner newModuleField;
    SpinnerValueFactory<Integer> newModuleValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 6, 0);
    SpinnerValueFactory<Integer> oldModuleValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 6, 0);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        oldModuleField.setValueFactory(oldModuleValueFactory);
        newModuleField.setValueFactory(newModuleValueFactory);
        oldModuleValueFactory.valueProperty().addListener((obs, oldVal, newVal) -> {
            if(!student.setOldModule(oldModuleValueFactory.getValue())) {
                oldModuleValueFactory.setValue(oldVal);
            } else {
                totalModuleLabel.setText(String.format("%d", student.getModuleTotal()));
            }
        });
        newModuleValueFactory.valueProperty().addListener((obs, oldVal, newVal) -> {
            if(!student.setNewModule(newModuleValueFactory.getValue())) {
                newModuleValueFactory.setValue(oldVal);
            } else {
                totalModuleLabel.setText(String.format("%d", student.getModuleTotal()));
            }
        });
    }

    public void init(Student student) {
        this.student = student;
        idLabel.setText(String.format("%d", student.getId()));
        nameLabel.setText(String.format("%s, %s", student.getLastName().toUpperCase(), student.getFirstName().toUpperCase()));
        balanceLabel.setText(String.format("P %,.2f", student.getBalance()));
        tuitionFeeLabel.setText(String.format("P %,.2f", student.getTuitionFee()));
        amountPaidLabel.setText(String.format("P %,.2f", student.getAmountPaid()));
        totalModuleLabel.setText(String.format("%d", student.getModuleTotal()));
        
        oldModuleValueFactory.setValue(student.getModuleOld());
        newModuleValueFactory.setValue(student.getModuleNew());
    }
    
    public void onSave() {
        String query = String.format("UPDATE STUDENTS SET new_module = %d, old_module = %d, tuition_fee = %f WHERE id = %d",
            student.getModuleNew(), student.getModuleOld(), student.getTuitionFee(), student.getId());
        Database.execute(query);
        
        AlertBox.display("Student Manager", "Student is successfully updated");
        
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