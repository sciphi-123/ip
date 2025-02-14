package phi;

import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The main class that runs the Phi task management application. It handles user input,
 * manages tasks, interacts with storage, and communicates with the user through the UI.
 * It coordinates the loading and saving of tasks, as well as executing different commands
 * such as adding, marking, unmarking, and deleting tasks.
 */
public class Phi {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Phi application with the specified file path for task storage.
     * Initializes the UI, storage, and task list. Loads existing tasks from storage.
     */
    public Phi() {
        this.ui = new Ui();
        try {
            tasks = Storage.loadTasks();
        } catch (Exception e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the Phi task management application.
     * Greets the user and enters a loop where it continuously reads user input,
     * processes commands, and handles exceptions. The loop ends when the user types "bye".
     *
     * @param input The user input command.
     * @return The response generated based on the user input.
     */
    public String getResponse(String input) {
        try {
            assert input != null: "User input should not be null";
            String output = this.checkInput(input);
            Storage.saveTasks(tasks);
            return output;
        } catch (PhiException e) {
            return ui.returnError("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            return ui.returnError("Give a number!");
        } catch (IndexOutOfBoundsException e) {
            return ui.returnError("The number entered is not within range!");
        } catch (DateTimeParseException e) {
            return ui.returnError("Please enter valid date(s) in the format yyyy-mm-dd.");
        }
    }

    /**
     * Checks the input command and performs the corresponding action.
     * Valid commands include "bye", "list", "mark", "unmark", "delete", "todo", "deadline", and "event".
     *
     * @param line The user input command.
     * @return The output message after executing the command.
     * @throws PhiException If the input command is invalid.
     */
    public String checkInput(String line) throws PhiException {
        try {
            Task task;
            if (line.equals("bye")) {
                return ui.exit();
            } else if (line.equals("list")) {
                return ui.printAllTasks(tasks);
            } else if (line.startsWith("mark")) {
                task = tasks.markTask(Parser.parseIndex(line, tasks.size()));
                return ui.printMarked(task);
            } else if (line.startsWith("unmark")) {
                task = tasks.unmarkTask(Parser.parseIndex(line, tasks.size()));
                return ui.printUnmarked(task);
            } else if (line.startsWith("delete")) {
                int tempSize = tasks.size();
                task = tasks.deleteTask(Parser.parseIndex(line, tasks.size()));
                assert tempSize - 1 == tasks.size(): "Size of the list of tasks should decrease by 1";
                return ui.printDeleted(task, tasks.size());
            } else if (line.startsWith("todo")) {
                task = Parser.parseTodo(line);
                int tempSize = tasks.size();
                tasks.addTask(task);
                assert tempSize + 1 == tasks.size(): "Size of the list of tasks should increase by 1";
                return ui.printAdded(task, tasks.size());
            } else if (line.startsWith("deadline")) {
                task = Parser.parseDeadline(line);
                int tempSize = tasks.size();
                tasks.addTask(task);
                assert tempSize + 1 == tasks.size(): "Size of the list of tasks should increase by 1";
                return ui.printAdded(task, tasks.size());
            } else if (line.startsWith("event")) {
                task = Parser.parseEvent(line);
                int tempSize = tasks.size();
                tasks.addTask(task);
                assert tempSize + 1 == tasks.size(): "Size of the list of tasks should increase by 1";
                return ui.printAdded(task, tasks.size());
            } else if (line.startsWith("find")) {
                String keyword = Parser.parseFind(line);
                List<Task> relevantTasks = tasks.findTasks(keyword);
                return ui.printFound(relevantTasks);
            } else {
                throw new PhiException("This is not a valid command!");
            }
        } catch (PhiException e) {
            return ui.returnError(e.getMessage());
        }
    }
}