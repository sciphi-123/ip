package phi;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main GUI class for Phi, responsible for loading and displaying the main window
 * using FXML. It extends the JavaFX Application class to set up the application window.
 */
public class Main extends Application {
    private final Phi phi = new Phi();

    /**
     * Initializes and displays the main window of the Phi application.
     * It loads the FXML layout, sets up the scene, and injects the Phi instance into the controller.
     *
     * @param stage The primary stage for this application, onto which the scene will be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPhi(phi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
