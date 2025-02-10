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
     */
    public static String greet() {
        return "Hello! I'm Phi!\n" + "What can I do for you?\n\n";
    }

    /**
     * Prints a farewell message when the program is exiting.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public String printAdded(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\n" + this.taskCount(size);
    }

    /**
     * Prints a message confirming that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     */
    public String printDeleted(Task task, int size) {
        return "Noted. I've removed this task:\n" + task + "\n" + this.taskCount(size);
    }

    /**
     * Prints a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String printMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n\n";
    }

    /**
     * Prints a message confirming that a task has been marked as not done yet.
     *
     * @param task The task that was marked as not done.
     */
    public String printUnmarked(Task task) {
        return "Ok, I've marked this task as not done yet:\n" + task + "\n\n";
    }

    /**
     * Prints the number of tasks currently in the task list.
     *
     * @param listSize The current size of the task list.
     */
    public String taskCount(int listSize) {
        if (listSize == 0) {
            return "Now you have no task in the list.\n\n";
        } else if (listSize == 1) {
            return "Now you have 1 task in the list.\n\n";
        } else {
            return "Now you have " + listSize +  " tasks in the list.\n\n";
        }
    }

    /**
     * Prints all tasks in the task list.
     *
     * @param tasks The task list containing all tasks.
     */
    public String printAllTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "There are no tasks in your list.";
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

    public String returnError(String message) {
        return message;
    }
}
