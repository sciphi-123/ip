import java.util.Scanner;

public class Phi {
    private void greet() {
        System.out.println("Hello! I'm Phi!");
        System.out.println("What can I do for you?");
        System.out.println();
    }

    private void echo(String input) {
        System.out.print("Phi: ");
        System.out.println(input);
        System.out.println();
    }

    private void exit() {
        System.out.println("Phi: Bye. Hope to see you again soon!");
    }

    private void checkInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("User: ");
        String userInput = input.next();
        if (userInput.equals("bye")) {
            this.exit();
            return;
        }
        this.echo(userInput);
        this.checkInput();
    }

    public static void main(String[] args) {
        Phi phi = new Phi();
        phi.greet();
        phi.checkInput();
    }
}
