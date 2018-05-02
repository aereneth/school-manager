import javafx.fxml.*;
import javafx.scene.control.*;

public class StudentViewerController {
    Student student;
    public Label nameLabel;
    public Label phoneNumberLabel;
    public Label addressLabel;
    public Label tuitionFeeLabel;
    public Label amountPaidLabel;
    public Label balanceLabel;
    public Label moduleLabel;
    
    public void init(Student student) {
        this.student = student;
        
        nameLabel.setText(String.format("%s, %s", student.getLastName().toUpperCase(), student.getFirstName().toUpperCase()));
        phoneNumberLabel.setText(student.getPhoneNumber());
        addressLabel.setText(student.getAddress());
        
        tuitionFeeLabel.setText(String.format("P %,.2f", student.getTuitionFee()));
        moduleLabel.setText(String.format("%d", student.getModuleTotal()));
        amountPaidLabel.setText(String.format("P %,.2f", student.getAmountPaid()));
        balanceLabel.setText(String.format("P %,.2f", student.getBalance()));
    }
    
    public void onReturn() {
        FXMLLoader mainMenu = new FXMLLoader(getClass().getResource("main_menu.fxml"));
        
        try {
            Main.setRoot(mainMenu.load());
        } catch(Exception e) {}
    }
}
