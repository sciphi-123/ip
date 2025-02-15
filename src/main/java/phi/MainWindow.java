package phi;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The main window controller for the Phi application. It handles the user interface components,
 * including the text input, button actions, and displaying dialog boxes between the user and Phi.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Phi phi;
    private boolean isNewSession = true;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image phiImage = new Image(this.getClass().getResourceAsStream("/images/Phi.png"));

    /**
     * Initializes the main window components, binds the scrollPane, and sets up the action for the send button.
     * This method is automatically called after the FXML components are loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setOnAction(event -> handleUserInput());
    }

    /**
     * Sets the Phi instance for the main window and initializes the first greeting dialog.
     * This method is called when the Phi object is passed into the controller.
     *
     * @param p The Phi instance to interact with in this window.
     */
    public void setPhi(Phi p) {
        this.phi = p;

        if (isNewSession) {
            dialogContainer.getChildren().add(DialogBox.getPhiDialog(Ui.greet(), phiImage));
        }
    }

    /**
     * Handles the user's input, processes the response from Phi, and updates the dialog container with
     * both the user's input and Phi's response. It also clears the text field and exits the application if
     * the user types "bye".
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = phi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPhiDialog(response, phiImage)
        );
        userInput.clear();
    }
}
