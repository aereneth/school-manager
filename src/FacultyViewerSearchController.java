import javafx.fxml.*;
import javafx.scene.control.*;

public class FacultyViewerSearchController {
    public TextField idField;
    
    public void onEnter() {
        FXMLLoader facultyViewer = new FXMLLoader(getClass().getResource("faculty_viewer.fxml"));
        
        try {
            Main.setRoot(facultyViewer.load());
        } catch(Exception e) {System.err.println(e.getMessage());}
        
        FacultyViewerController facultyViewerController = facultyViewer.getController();
        facultyViewerController.init(Database.getTeacher(Integer.parseInt(idField.getText())));
    }
    
    public void onReturn() {
        FXMLLoader mainMenu = new FXMLLoader(getClass().getResource("main_menu.fxml"));

        try {
            Main.setRoot(mainMenu.load());
        } catch(Exception e) {System.err.println(e.getMessage());}
    }
}