import java.util.ArrayList;
import java.util.Scanner;

public class Phi {
    private ArrayList<Task> list;

    public Phi() {
        this.list = new ArrayList<>();
    }

    public void greet() {
        System.out.println("Hello! I'm Phi!");
        System.out.println("What can I do for you?");
        System.out.println();
    }

    public void echo(String input) {
        System.out.print("added: ");
        System.out.println(input);
        System.out.println();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void checkInput() {
        Scanner input = new Scanner(System.in);
        while (true) {
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                this.exit();
                break;
            } else if (userInput.equals("list")) {
                this.listInput();
            } else if (userInput.startsWith("mark ")) {
                // "mark " contains 5 characters.
                int doneIndex = Integer.parseInt(userInput.substring(5));
                Task doneTask = this.list.get(doneIndex - 1);
                doneTask.markDone();
                System.out.println(doneTask);
                System.out.println();
            } else if (userInput.startsWith("unmark ")) {
                // "unmark " contains 7 characters.
                int undoneIndex = Integer.parseInt(userInput.substring(7));
                Task undoneTask = this.list.get(undoneIndex - 1);
                undoneTask.markUndone();
                System.out.println(undoneTask);
                System.out.println();
            } else if (userInput.startsWith("todo ")) {
                String description = userInput.substring(5);
                this.addInput(new Todo(description));
            } else if (userInput.startsWith("deadline ")) {
                // remove task type; "deadline " contains 9 characters
                String[] clean = userInput.substring(9).split(" /");
                this.addInput(new Deadline(clean[0], clean[1].substring(3)));
            } else if (userInput.startsWith("event ")) {
                String[] clean = userInput.substring(6).split(" /");
                this.addInput(new Event(clean[0], clean[1].substring(5), clean[2].substring(3)));
            }
        }
    }

    public void addInput(Task userTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", userTask));
        this.list.add(userTask);
        this.taskCount();
    }

    public void taskCount() {
        int listSize = this.list.size();
        if (listSize == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println(String.format("Now you have %d tasks in the list.", listSize));
        }
        System.out.println();
    }

    public void listInput() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            Task temp = this.list.get(i);
            System.out.println(String.format("%d.%s", i + 1, temp));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Phi phi = new Phi();
        phi.greet();
        phi.checkInput();
    }
}
