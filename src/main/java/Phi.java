import java.util.ArrayList;
import java.util.Scanner;

public class Phi {
    private ArrayList<String> list;

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
        String userInput = input.next();
        if (userInput.equals("bye")) {
            this.exit();
            return;
        } else if (userInput.equals("list")) {
            this.listInput();
        } else {
            this.addInput(userInput);
            this.echo(userInput);
        }
        this.checkInput();
    }

    public void addInput(String userInput) {
        this.list.add(userInput);
    }

    public void listInput() {
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, this.list.get(i)));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Phi phi = new Phi();
        phi.greet();
        phi.checkInput();
    }
}
