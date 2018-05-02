import javafx.fxml.*;

public class MainMenuController {
    public void onStudentViewer() {
        FXMLLoader studentViewerInput = new FXMLLoader(getClass().getResource("student_viewer_search.fxml"));
        
        try{
            Main.setRoot(studentViewerInput.load());
        } catch(Exception e) {}
    }

    public void onStudentManager() {
        FXMLLoader studentManager = new FXMLLoader(getClass().getResource("student_manager.fxml"));
        
        try{
            Main.setRoot(studentManager.load());
        } catch(Exception e) {}
    }
    
    public void onFacultyViewer() {
        FXMLLoader studentManager = new FXMLLoader(getClass().getResource("faculty_viewer_search.fxml"));
        
        try{
            Main.setRoot(studentManager.load());
        } catch(Exception e) {}
    }
    
    public void onFacultyManager() {
        FXMLLoader studentManager = new FXMLLoader(getClass().getResource("faculty_manager.fxml"));
        
        try{
            Main.setRoot(studentManager.load());
        } catch(Exception e) {}
    }
    
    public void onExit() {
        Main.exit();
    }
}
