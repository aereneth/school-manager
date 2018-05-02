import java.util.HashMap;

import javafx.application.*;
import javafx.collections.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.layout.Pane;

public class Main extends Application {
    private static HashMap<String, Pane> screenMap = new HashMap<>();
    private static Stage stage;
    static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        stage = window;
        scene = new Scene(FXMLLoader.load(getClass().getResource("main_menu.fxml")));

        window.setTitle("School Account Manager");
        window.setScene(scene);
        window.sizeToScene();
        window.show();		
    }

    public static void setRoot(Parent parent) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        scene.setRoot(parent);
        stage.sizeToScene();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2); 
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2); 
    }
    
    public static void exit() {
        stage.close();
    }
}
