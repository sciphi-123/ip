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
        String userInput = input.nextLine();
        if (userInput.equals("bye")) {
            this.exit();
            return;
        } else if (userInput.equals("list")) {
            this.listInput();
        } else if (userInput.startsWith("mark ")) {
            // "mark " contains 5 characters.
            int doneIndex = Integer.parseInt(userInput.substring(5));
            Task doneTask = this.list.get(doneIndex - 1);
            doneTask.markDone();
            System.out.println(String.format("%s %s", doneTask.getStatusIcon(), doneTask.getDescription()));
            System.out.println();
        } else if (userInput.startsWith("unmark ")) {
            // "unmark " contains 7 characters.
            int undoneIndex = Integer.parseInt(userInput.substring(7));
            Task undoneTask = this.list.get(undoneIndex - 1);
            undoneTask.markUndone();
            System.out.println(String.format("%s %s", undoneTask.getStatusIcon(), undoneTask.getDescription()));
            System.out.println();
        } else {
            this.addInput(new Task(userInput));
            this.echo(userInput);
        }
        this.checkInput();
    }

    public void addInput(Task userTask) {
        this.list.add(userTask);
    }

    public void listInput() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            Task temp = this.list.get(i);
            System.out.println(String.format("%d.%s %s", i + 1, temp.getStatusIcon(), temp.getDescription()));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Phi phi = new Phi();
        phi.greet();
        phi.checkInput();
    }
}
