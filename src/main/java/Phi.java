import java.time.format.DateTimeParseException;

public class Phi {
    private final Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isRunning = true;

    public Phi(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        storage.loadTasks(this.tasks);
    }

    public void phi() {
        ui.greet();
        while (isRunning) {
            try {
                String line = ui.readCommand();
                isRunning = this.checkInput(line);
                storage.saveTasks(tasks);
            } catch (PhiException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Give a number!");
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The number entered is not within range!");
                System.out.println();
            } catch (DateTimeParseException e) {
                System.out.println("Please enter valid date(s) in the format yyyy-mm-dd.");
                System.out.println();
            }
        }
    }

    public boolean checkInput(String line) throws PhiException {
        try {
            Task task;
            if (line.equals("bye")) {
                ui.exit();
                return false;
            } else if (line.equals("list")) {
                ui.printAllTasks(tasks);
            } else if (line.startsWith("mark")) {
                task = tasks.markTask(Parser.parseIndex(line, tasks.size()));
                ui.printMarked(task);
            } else if (line.startsWith("unmark")) {
                task = tasks.unmarkTask(Parser.parseIndex(line, tasks.size()));
                ui.printUnmarked(task);
            } else if (line.startsWith("delete")) {
                task = tasks.deleteTask(Parser.parseIndex(line, tasks.size()));
                ui.printDeleted(task, tasks.size());
            } else if (line.startsWith("todo")) {
                task = Parser.parseTodo(line);
                tasks.addTask(task);
                ui.printAdded(task, tasks.size());
            } else if (line.startsWith("deadline")) {
                task = Parser.parseDeadline(line);
                tasks.addTask(task);
                ui.printAdded(task, tasks.size());
            } else if (line.startsWith("event")) {
                task = Parser.parseEvent(line);
                tasks.addTask(task);
                ui.printAdded(task, tasks.size());
            } else {
                throw new PhiException("This is not a valid command!");
            }
        } catch (PhiException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
        return true;
    }

    public static void main(String[] args) {
        new Phi("./data/phi.txt").phi();
    }
}