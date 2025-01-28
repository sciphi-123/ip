import java.util.Scanner;

public class Ui {
    private final Scanner input = new Scanner(System.in);

    public String readCommand() {
        return input.nextLine().trim();
    }

    public void greet() {
        System.out.println("Hello! I'm Phi!");
        System.out.println("What can I do for you?");
        System.out.println();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        this.taskCount(size);
    }

    public void printDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        this.taskCount(size);
    }

    public void printMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println();
    }

    public void printUnmarked(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println();
    }

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

}
