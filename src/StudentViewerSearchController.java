import javafx.fxml.*;
import javafx.scene.control.*;

public class StudentViewerSearchController {
    public TextField idField;
    
    public void onEnter() {
        FXMLLoader studentViewer = new FXMLLoader(getClass().getResource("student_viewer.fxml"));
        
        try {
            Main.setRoot(studentViewer.load());
        } catch(Exception e) {System.err.println(e.getMessage());}
        
        StudentViewerController studentViewerController = studentViewer.getController();
        studentViewerController.init(Database.getStudent(Integer.parseInt(idField.getText())));
    }
    
    public void onReturn() {
        FXMLLoader mainMenu = new FXMLLoader(getClass().getResource("main_menu.fxml"));

        try {
            Main.setRoot(mainMenu.load());
        } catch(Exception e) {System.err.println(e.getMessage());}
    }
}
