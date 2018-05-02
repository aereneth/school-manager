import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

public class StudentManagerController implements Initializable {
    public TableView<Student> studentTable;
    public TableColumn<Student, String> idColumn;
    public TableColumn<Student, String> firstNameColumn;
    public TableColumn<Student, String> lastNameColumn;
    public TableColumn<Student, String> genderColumn;
    public TableColumn<Student, String> phoneNumberColumn;
    public TableColumn<Student, String> addressColumn;
    public TableColumn<Student, String> amountPaidColumn;
    public TableColumn<Student, String> balanceColumn;
    public TableColumn<Student, String> tuitionFeeColumn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        studentTable.setItems(Database.getStudentMany());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        amountPaidColumn.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        tuitionFeeColumn.setCellValueFactory(new PropertyValueFactory<>("tuitionFee"));
    }

    public void onAdd() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student_manager_create.fxml"));

        try {
            Main.setRoot(loader.load());
        } catch(Exception e) {}
    }

    public void onDelete() {
        if(!ConfirmBox.display("Delete Record", "Are you sure you want to delete this record?")) {
            return;
        }

        String query = String.format("DELETE FROM STUDENTS WHERE ID = %d",
            studentTable.getSelectionModel().getSelectedItem().getId());
        Database.execute(query);
        
        ObservableList<Student> allItems = studentTable.getItems();
        Student selectedItems = studentTable.getSelectionModel().getSelectedItem();
        allItems.removeAll(selectedItems);
    }

    public void onUpdate() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student_manager_update.fxml"));

        try {
            Main.setRoot(loader.load());
        } catch(Exception e) {}

        StudentManagerUpdateController updateModuleController = loader.getController();
        updateModuleController.init(studentTable.getSelectionModel().getSelectedItem());
    }

    public void onTransact() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student_manager_transaction.fxml"));

        try {
            Main.setRoot(loader.load());
        } catch(Exception e) {}

        StudentManagerTransactionController controller = loader.getController();
        controller.init(studentTable.getSelectionModel().getSelectedItem());
    }
    
    public void onReturn() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_menu.fxml"));
        
        try {
            Main.setRoot(loader.load());
        } catch(Exception e) {}
    }
}
