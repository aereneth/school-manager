import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

public class FacultyManagerController implements Initializable {
    public TableView<Teacher> teacherTable;
    public TableColumn<Teacher, String> idColumn;
    public TableColumn<Teacher, String> firstNameColumn;
    public TableColumn<Teacher, String> lastNameColumn;
    public TableColumn<Teacher, String> genderColumn;
    public TableColumn<Teacher, String> phoneNumberColumn;
    public TableColumn<Teacher, String> addressColumn;
    public TableColumn<Teacher, String> departmentColumn;
    public TableColumn<Teacher, String> designationColumn;
    public TableColumn<Teacher, String> salaryColumn;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        teacherTable.setItems(Database.getTeacherMany());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }
    
    public void onAdd() {
        FXMLLoader facultyCreate = new FXMLLoader(getClass().getResource("faculty_manager_create.fxml"));

        try{
            Main.setRoot(facultyCreate.load());
        } catch(Exception e) {}
    }
    
    public void onUpdate() {
        FXMLLoader facultyUpdate = new FXMLLoader(getClass().getResource("faculty_manager_update.fxml"));

        try{
            Main.setRoot(facultyUpdate.load());
        } catch(Exception e) {}
        
        ((FacultyManagerUpdateController) facultyUpdate.getController()).init(teacherTable.getSelectionModel().getSelectedItem());
    }
    
    public void onDelete() {
        if(!ConfirmBox.display("Delete Record", "Are you sure you want to delete this record?")) {
            return;
        }

        String query = String.format("DELETE FROM TEACHERS WHERE ID = %d",
            teacherTable.getSelectionModel().getSelectedItem().getId());
        Database.execute(query);
        
        ObservableList<Teacher> allItems = teacherTable.getItems();
        Teacher selectedItems = teacherTable.getSelectionModel().getSelectedItem();
        allItems.removeAll(selectedItems);
    }
    
    public void onReturn() {
        FXMLLoader mainMenu = new FXMLLoader(getClass().getResource("main_menu.fxml"));

        try{
            Main.setRoot(mainMenu.load());
        } catch(Exception e) {}
    }
}
