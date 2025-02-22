package phi;

import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface (UI) of the Phi task manager.
 * Handles the interaction between the user and the application by displaying messages
 * and receiving user input.
 */
public class Ui {
    private final Scanner input = new Scanner(System.in);

    /**
     * Reads the next line of user input and trims any leading or trailing whitespace.
     *
     * @return The user's input as a trimmed string.
     */
    public String readCommand() {
        return input.nextLine().trim();
    }

    /**
     * Greets the user when the program starts.
     *
     * @return A greeting message from the application.
     */
    public static String greet() {
        return "Hello! I'm Phi!\n" + "What can I do for you?\n\n";
    }

    /**
     * Prints a farewell message when the program is exiting.
     *
     * @return A goodbye message when the application exits.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     * @return A confirmation message about the task addition.
     */
    public String printAdded(Task task, int size) {
        return "Got it. I've added this task:\n    " + task + "\n" + this.taskCount(size);
    }

    /**
     * Prints a message confirming that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     * @return A confirmation message about the task deletion.
     */
    public String printDeleted(Task task, int size) {
        return "Noted. I've removed this task:\n    " + task + "\n" + this.taskCount(size);
    }

    /**
     * Prints a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return A confirmation message about the task being marked as done.
     */
    public String printMarked(Task task) {
        return "Nice! I've marked this task as done:\n    " + task + "\n\n";
    }

    /**
     * Prints a message confirming that a task has been marked as not done yet.
     *
     * @param task The task that was marked as not done.
     * @return A confirmation message about the task being marked as not done yet.
     */
    public String printUnmarked(Task task) {
        return "Ok, I've marked this task as not done yet:\n    " + task + "\n\n";
    }

    /**
     * Prints the number of tasks currently in the task list.
     *
     * @param listSize The current size of the task list.
     * @return A message displaying the task count.
     */
    public String taskCount(int listSize) {
        if (listSize == 0) {
            return "Now you have no task in the list.\n\n";
        } else if (listSize == 1) {
            return "Now you have 1 task in the list.\n\n";
        } else {
            return "Now you have " + listSize + " tasks in the list.\n\n";
        }
    }

    /**
     * Prints all tasks in the task list.
     *
     * @param tasks The task list containing all tasks.
     * @return A string representation of all tasks in the list.
     */
    public String printAllTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "There are no tasks in your list.\n";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(String.format("%d.%s\n", i + 1, tasks.getTask(i)));
            }
            return "Here are the tasks in your list:\n" + sb.toString();
        }
    }

    /**
     * Prints the tasks that match the given search criteria.
     *
     * @param tasks A list of tasks that contain the keyword in their description.
     * @return A string representation of the matching tasks.
     */
    public String printFound(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();

        if (tasks.isEmpty()) {
            sb.append("No matching results found.");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Returns a string representation of all tasks in the given task list,
     * followed by a message indicating that the tasks have been sorted.
     *
     * @param task the TaskList containing the tasks to be printed
     * @return a string listing all tasks and a confirmation message
     */
    public String printSortedTasks(TaskList task) {
        return printAllTasks(task) + "\n" + "The tasks have been sorted.";
    }

    /**
     * Returns the error message to be displayed.
     *
     * @param message The error message to be shown.
     * @return The error message.
     */
    public String returnError(String message) {
        return message;
    }
}