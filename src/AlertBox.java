
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class AlertBox {
    public static void display(String title, String message) {
        Stage dialog = new Stage();
        VBox layout = new VBox();

        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(title);

        Label messageLabel = new Label();
        messageLabel.setText(message);

        Button closeButton = new Button();
        closeButton.setText("Close");
        closeButton.setOnAction(e -> {
                dialog.close();
        });

        layout.setSpacing(5);
        layout.setPadding(new Insets(8,8,8,8));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(messageLabel, closeButton);

        Scene scene = new Scene(layout);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        dialog.setScene(scene);
        dialog.showAndWait();
        dialog.setX((screenBounds.getWidth() - dialog.getWidth()) / 2); 
        dialog.setY((screenBounds.getHeight() - dialog.getHeight()) / 2); 
    }
}
