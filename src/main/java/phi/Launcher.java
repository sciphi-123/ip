package phi;

import javafx.application.Application;

/**
 * A launcher class to work around classpath issues by launching the JavaFX application.
 * This class serves as an entry point for the application, resolving issues that may arise
 * when directly running the JavaFX application class.
 */
public class Launcher {

    /**
     * The main method that launches the JavaFX application by calling Application.launch().
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
