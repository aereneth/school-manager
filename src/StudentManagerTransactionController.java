import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;

public class StudentManagerTransactionController implements Initializable {
    Student student;
    public Label idLabel;
    public Label nameLabel;
    public Label tuitionFeeLabel;
    public Label amountPaidLabel;
    public Label balanceLabel;
    public Spinner amountField;
    SpinnerValueFactory<Double> amountValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 75000, 1000);
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        amountField.setValueFactory(amountValueFactory);
    }
    
    public void init(Student student) {
        this.student = student;
        
        idLabel.setText(String.format("%d", student.getId()));
        nameLabel.setText(String.format("%s, %s", student.getLastName().toUpperCase(), student.getFirstName().toUpperCase()));
        tuitionFeeLabel.setText(String.format("P %,.2f", student.getTuitionFee()));
        amountPaidLabel.setText(String.format("P %,.2f", student.getAmountPaid()));
        balanceLabel.setText(String.format("P %,.2f", student.getBalance()));
    }
    
    public void onEnter() {
        if(!student.feeDeposit(amountValueFactory.getValue())) {
            AlertBox.display("Transaction Error", "Amount entered is greater than the remaining balance");
            return;
        }
        
        String transactionQuery = String.format("INSERT INTO TRANSACTIONS(student_id, amount) VALUES(%d, %f)",
            student.getId(), amountValueFactory.getValue());
        Database.execute(transactionQuery);
        
        String studentQuery = String.format("UPDATE STUDENTS SET amount_paid=%f WHERE id=%d",
            student.getAmountPaid(),
            student.getId());
        Database.execute(studentQuery);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student_manager.fxml"));
        
        try {
            Main.setRoot(loader.load());
        } catch(Exception e) {}
    }
    
    public void onReturn() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student_manager.fxml"));
        
        try {
            Main.setRoot(loader.load());
        } catch(Exception e) {}
    }
}
