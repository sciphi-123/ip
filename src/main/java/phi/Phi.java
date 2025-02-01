package phi;

import java.time.format.DateTimeParseException;

/**
 * The main class that runs the Phi task management application. It handles user input,
 * manages tasks, interacts with storage, and communicates with the user through the UI.
 * It coordinates the loading and saving of tasks, as well as executing different commands
 * such as adding, marking, unmarking, and deleting tasks.
 */
public class Phi {
    private final Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isRunning = true;

    /**
     * Constructs a Phi application with the specified file path for task storage.
     * Initializes the UI, storage, and task list. Loads existing tasks from storage.
     *
     * @param filePath The file path for storing tasks.
     */
    public Phi(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        storage.loadTasks(this.tasks);
    }

    /**
     * Starts the Phi task management application.
     * Greets the user and enters a loop where it continuously reads user input,
     * processes commands, and handles exceptions. The loop ends when the user types "bye".
     */
    public void phi() {
        ui.greet();
        while (isRunning) {
            try {
                String line = ui.readCommand();
                isRunning = this.checkInput(line);
                storage.saveTasks(tasks);
            } catch (PhiException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Give a number!");
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The number entered is not within range!");
                System.out.println();
            } catch (DateTimeParseException e) {
                System.out.println("Please enter valid date(s) in the format yyyy-mm-dd.");
                System.out.println();
            }
        }
    }

    /**
     * Checks the input command and performs the corresponding action.
     * Valid commands include "bye", "list", "mark", "unmark", "delete", "todo", "deadline", and "event".
     *
     * @param line The user input command.
     * @return A boolean value indicating whether the application should continue running.
     * @throws PhiException If the input command is invalid.
     */
    public boolean checkInput(String line) throws PhiException {
        try {
            Task task;
            if (line.equals("bye")) {
                ui.exit();
                return false;
            } else if (line.equals("list")) {
                ui.printAllTasks(tasks);
            } else if (line.startsWith("mark")) {
                task = tasks.markTask(Parser.parseIndex(line, tasks.size()));
                ui.printMarked(task);
            } else if (line.startsWith("unmark")) {
                task = tasks.unmarkTask(Parser.parseIndex(line, tasks.size()));
                ui.printUnmarked(task);
            } else if (line.startsWith("delete")) {
                task = tasks.deleteTask(Parser.parseIndex(line, tasks.size()));
                ui.printDeleted(task, tasks.size());
            } else if (line.startsWith("todo")) {
                task = Parser.parseTodo(line);
                tasks.addTask(task);
                ui.printAdded(task, tasks.size());
            } else if (line.startsWith("deadline")) {
                task = Parser.parseDeadline(line);
                tasks.addTask(task);
                ui.printAdded(task, tasks.size());
            } else if (line.startsWith("event")) {
                task = Parser.parseEvent(line);
                tasks.addTask(task);
                ui.printAdded(task, tasks.size());
            } else {
                throw new PhiException("This is not a valid command!");
            }
        } catch (PhiException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
        return true;
    }

    /**
     * The main entry point for the Phi application.
     * Initializes a new Phi instance and starts the application.
     *
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {
        new Phi("./data/phi.txt").phi();
    }
}
