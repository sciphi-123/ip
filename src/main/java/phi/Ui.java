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
    public void greet() {
        System.out.println("Hello! I'm Phi!");
        System.out.println("What can I do for you?");
        System.out.println();
    }

    /**
     * Prints a farewell message when the program is exiting.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public void printAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        this.taskCount(size);
    }

    /**
     * Prints a message confirming that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     */
    public void printDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        this.taskCount(size);
    }

    /**
     * Prints a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println();
    }

    /**
     * Prints a message confirming that a task has been marked as not done yet.
     *
     * @param task The task that was marked as not done.
     */
    public void printUnmarked(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println();
    }

    /**
     * Prints the number of tasks currently in the task list.
     *
     * @param listSize The current size of the task list.
     */
    public void taskCount(int listSize) {
        if (listSize == 0) {
            System.out.println("Now you have no task in the list.");
        } else if (listSize == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + listSize +  " tasks in the list.");
        }
        System.out.println();
    }

    /**
     * Prints all tasks in the task list.
     *
     * @param tasks The task list containing all tasks.
     */
    public void printAllTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task temp = tasks.getTask(i);
                System.out.println(String.format("%d.%s", i + 1, temp));
            }
        }
        System.out.println();
    }

    /**
     * Prints the tasks that match the given search criteria.
     *
     * @param tasks A list of tasks that contain the keyword in their description.
     */
    public void printFound(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching results found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
            }
        }
        System.out.println();
    }
}
