
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class ConfirmBox {
    static boolean result;

    public static boolean display(String title, String message) {
        Stage dialog = new Stage();
        VBox layout = new VBox();
        HBox buttonLayout = new HBox();

        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(title);

        Label messageLabel = new Label();
        messageLabel.setText(message);

        Button yesButton = new Button();
        yesButton.setText("Yes");
        yesButton.setOnAction(e -> {
                result = true;
                dialog.close();
        });

        Button noButton = new Button();
        noButton.setText("No");
        noButton.setOnAction(e -> {
                result = false;
                dialog.close();
        });

        buttonLayout.setSpacing(10);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(yesButton, noButton);

        layout.setSpacing(5);
        layout.setPadding(new Insets(8,8,8,8));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(messageLabel, buttonLayout);

        Scene scene = new Scene(layout);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        dialog.setScene(scene);
        dialog.showAndWait();
        dialog.setX((screenBounds.getWidth() - dialog.getWidth()) / 2); 
        dialog.setY((screenBounds.getHeight() - dialog.getHeight()) / 2); 

        return result;
    }
}
