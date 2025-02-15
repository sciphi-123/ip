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
     * Processes the user input command and performs the corresponding action.
     * Valid commands include:
     * - "bye"       : Exits the program.
     * - "list"      : Displays all tasks.
     * - "mark"      : Marks a task as completed.
     * - "unmark"    : Marks a task as incomplete.
     * - "delete"    : Removes a task from the list.
     * - "todo"      : Adds a new to-do task.
     * - "deadline"  : Adds a new deadline task.
     * - "event"     : Adds a new event task.
     * - "find"      : Searches for tasks containing a given keyword.
     * - "sort"      : Sorts the tasks based on their type and date.
     *
     * @param line The user input command.
     * @return The response message after executing the command.
     * @throws PhiException if the input command is invalid.
     */
    public String checkInput(String line) throws PhiException {
        try {
            Task task;
            if (line.toLowerCase().equals("bye")) {
                return ui.exit();
            } else if (line.toLowerCase().equals("list")) {
                return ui.printAllTasks(tasks);
            } else if (line.toLowerCase().startsWith("mark")) {
                task = tasks.markTask(Parser.parseIndex(line, tasks.size()));
                return ui.printMarked(task);
            } else if (line.toLowerCase().startsWith("unmark")) {
                task = tasks.unmarkTask(Parser.parseIndex(line, tasks.size()));
                return ui.printUnmarked(task);
            } else if (line.toLowerCase().startsWith("delete")) {
                int tempSize = tasks.size();
                task = tasks.deleteTask(Parser.parseIndex(line, tasks.size()));
                assert tempSize - 1 == tasks.size(): "Size of the list of tasks should decrease by 1";
                return ui.printDeleted(task, tasks.size());
            } else if (line.toLowerCase().startsWith("todo")) {
                task = Parser.parseTodo(line);
                int tempSize = tasks.size();
                tasks.addTask(task);
                assert tempSize + 1 == tasks.size(): "Size of the list of tasks should increase by 1";
                return ui.printAdded(task, tasks.size());
            } else if (line.toLowerCase().startsWith("deadline")) {
                task = Parser.parseDeadline(line);
                int tempSize = tasks.size();
                tasks.addTask(task);
                assert tempSize + 1 == tasks.size(): "Size of the list of tasks should increase by 1";
                return ui.printAdded(task, tasks.size());
            } else if (line.toLowerCase().startsWith("event")) {
                task = Parser.parseEvent(line);
                int tempSize = tasks.size();
                tasks.addTask(task);
                assert tempSize + 1 == tasks.size(): "Size of the list of tasks should increase by 1";
                return ui.printAdded(task, tasks.size());
            } else if (line.toLowerCase().startsWith("find")) {
                String keyword = Parser.parseFind(line);
                List<Task> relevantTasks = tasks.findTasks(keyword);
                return ui.printFound(relevantTasks);
            } else if (line.toLowerCase().startsWith("sort")) {
                tasks.sortTasks();
                return ui.printSortedTasks(tasks);
            } else {
                throw new PhiException("This is not a valid command!");
            }
        } catch (PhiException e) {
            return ui.returnError(e.getMessage());
        }
    }
}