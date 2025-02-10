package phi;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    private boolean newSession = true;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image phiImage = new Image(this.getClass().getResourceAsStream("/images/Phi.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setOnAction(event -> handleUserInput());
    }

    public void setPhi(Phi p) {
        this.phi = p;

        if (newSession) {
            dialogContainer.getChildren().add(DialogBox.getPhiDialog(Ui.greet(), phiImage));
        }
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = phi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPhiDialog(response, phiImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            System.exit(0); // Close the application
        }
    }
}