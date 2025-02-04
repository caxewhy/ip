package solyu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Controller for the main chat window.
 * Handles user input and chatbot responses.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Solyu solyu;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/Solyu.png"));

    /**
     * Initializes the GUI components.
     * Binds enter key and button to send input.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Show the greeting message when GUI starts
        dialogContainer.getChildren().add(
                DialogBox.getSolyuDialog("Hello! I'm Solyu.\nWhat can I do for you?")
        );

        // Ensure Enter key sends input
        userInput.setOnAction(event -> handleUserInput());

        // Ensure Send button sends input
        sendButton.setOnAction(event -> handleUserInput());
    }

    /**
     * Sets the Solyu chatbot instance.
     *
     * @param solyu The chatbot instance.
     */
    public void setSolyu(Solyu solyu) {
        this.solyu = solyu;
    }

    /**
     * Handles user input and appends the response to the dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return; // Ignore empty input
        }

        String response = solyu.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, new ImageView(userImage)),
                DialogBox.getBotDialog(response, new ImageView(botImage))
        );

        userInput.clear(); // Clear the input field after sending
    }
}
