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
            try {
                if (userInput.equals("bye")) {
                    this.exit();
                    break;
                } else if (userInput.equals("list")) {
                    this.listInput();
                } else if (userInput.startsWith("mark")) {
                    String[] userArr = userInput.split(" ");
                    if (userArr.length < 2) {
                        throw new PhiException("No number given. Please give one!");
                    } else if (userArr.length >= 3){
                        throw new PhiException("Expected format: mark [number]");
                    } else {
                        // "mark " contains 5 characters.
                        int doneIndex = Integer.parseInt(userInput.substring(5));
                        Task doneTask = this.list.get(doneIndex - 1);
                        doneTask.markDone();
                        System.out.println(doneTask);
                        System.out.println();
                    }
                } else if (userInput.startsWith("unmark")) {
                    String[] userArr = userInput.split(" ");
                    if (userArr.length < 2) {
                        throw new PhiException("No number given. Please give one!");
                    } else if (userArr.length >= 3) {
                        throw new PhiException("Expected format: unmark [number]");
                    } else {
                        // "unmark " contains 7 characters.
                        int undoneIndex = Integer.parseInt(userInput.substring(7));
                        Task undoneTask = this.list.get(undoneIndex - 1);
                        undoneTask.markUndone();
                        System.out.println(undoneTask);
                        System.out.println();
                    }
                } else if (userInput.startsWith("todo")) {
                    String[] userArr = userInput.split(" ");
                    if (userArr.length < 2) {
                        throw new PhiException("No description given. Please give one!");
                    } else {
                        String description = userInput.substring(5);
                        this.addTask(new Todo(description));
                    }
                } else if (userInput.startsWith("deadline")) {
                    String[] userArr = userInput.split(" ");
                    if (userArr.length < 2) {
                        throw new PhiException("No description given. Please give one!");
                    } else {
                        // remove task type; "deadline " contains 9 characters
                        String[] clean = userInput.substring(9).split(" /by");
                        if (clean.length != 2) {
                            throw new PhiException("Expected format: deadline [description] /by [deadline]");
                        } else {
                            this.addTask(new Deadline(clean[0], clean[1]));
                        }
                    }
                } else if (userInput.startsWith("event")) {
                    String[] userArr = userInput.split(" ");
                    if (userArr.length < 2) {
                        throw new PhiException("No description given. Please give one!");
                    } else {
                        // remove task type; "event " contains 6 characters
                        String[] clean1 = userInput.substring(6).split(" /from");
                        if (clean1.length != 2) {
                            throw new PhiException("Expected format: deadline [description] /from [from time] /to [to time]");
                        } else {
                            String[] clean2 = clean1[1].split(" /to");
                            if (clean2.length != 2) {
                                throw new PhiException("Expected format: deadline [description] /from [from time] /to [to time]");
                            } else {
                                this.addTask(new Event(clean1[0], clean2[0], clean2[1]));
                            }
                        }
                    }
                } else if (userInput.startsWith("delete")) {
                    String[] userArr = userInput.split(" ");
                    if (userArr.length < 2) {
                        throw new PhiException("No number given. Please give one!");
                    } else if (userArr.length >= 3){
                        throw new PhiException("Expected format: delete [number]");
                    } else {
                        // "delete " contains 7 characters.
                        int deleteIndex = Integer.parseInt(userInput.substring(7));
                        Task taskDeleted = this.list.get(deleteIndex - 1);
                        this.deleteTask(taskDeleted);
                    }
                } else {
                    throw new PhiException("This is not a valid command!");
                }
            } catch (PhiException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println(" ");
            } catch (NumberFormatException e) {
                System.out.println("Give a number!");
                System.out.println(" ");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The number entered is not within range!");
                System.out.println(" ");
            }
        }
    }

    public void addTask(Task userTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", userTask));
        this.list.add(userTask);
        this.taskCount();
    }

    public void deleteTask(Task userTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(String.format("  %s", userTask));
        this.list.remove(userTask);
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
