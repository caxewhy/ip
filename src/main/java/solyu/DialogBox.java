package solyu;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box for displaying user and chatbot messages in the GUI.
 */
public class DialogBox extends HBox {
    private Label text;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox.
     *
     * @param label The label containing the text.
     * @param img The image representing the speaker.
     */
    private DialogBox(Label label, ImageView img) {
        text = label;
        displayPicture = img;

        text.setWrapText(true);
        displayPicture.setFitWidth(50);
        displayPicture.setFitHeight(50);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Creates a dialog box for user input.
     *
     * @param inputText The text input from the user.
     * @param img The user’s profile image.
     * @return A DialogBox containing user input.
     */
    public static DialogBox getUserDialog(String inputText, ImageView img) {
        Label userText = new Label(inputText);
        DialogBox db = new DialogBox(userText, img);
        db.setAlignment(Pos.TOP_RIGHT); // Align user message to right
        db.setStyle("-fx-background-color: #FFD3D3; -fx-background-radius: 15px; -fx-padding: 10px;");
        userText.setWrapText(true);
        return db;
    }

    /**
     * Creates a dialog box for chatbot responses.
     *
     * @param responseText The chatbot’s response text.
     * @param img The chatbot's profile image.
     * @return A DialogBox containing the chatbot response.
     */
    public static DialogBox getBotDialog(String responseText, ImageView img) {
        Label botText = new Label(responseText);
        DialogBox db = new DialogBox(botText, img);
        db.setAlignment(Pos.TOP_LEFT); // Align bot message to left

        // Flip order: Image first, then text
        db.getChildren().clear();
        db.getChildren().addAll(img, botText);

        db.setStyle("-fx-background-color: #AEC6CF; -fx-background-radius: 15px; -fx-padding: 10px;");
        botText.setWrapText(true);

        return db;
    }

    /**
     * Creates a dialog box for Solyu's initial greeting.
     *
     * @param text The greeting message.
     * @return A DialogBox containing Solyu's greeting.
     */
    public static DialogBox getSolyuDialog(String text) {
        Label botText = new Label(text);
        ImageView img = new ImageView(new Image(DialogBox.class.getResourceAsStream("/images/Solyu.png")));

        DialogBox db = new DialogBox(botText, img);
        db.setAlignment(Pos.TOP_LEFT); // Align greeting to the left

        db.getChildren().clear();
        db.getChildren().addAll(img, botText);

        db.setStyle("-fx-background-color: #AEC6CF; -fx-background-radius: 15px; -fx-padding: 10px;");
        botText.setWrapText(true);

        return db;
    }
}
