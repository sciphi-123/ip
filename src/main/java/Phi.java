import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Phi {
    private static final String TEXTFILE_PATH = "./data/phi.txt";
    private static ArrayList<Task> list;

    public Phi() {
        list = new ArrayList<>();
        loadTasks();
    }

    public void greet() {
        System.out.println("Hello! I'm Phi!");
        System.out.println("What can I do for you?");
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
                    listInput();
                } else if (userInput.startsWith("mark")) {
                    String[] userArr = userInput.split(" ");
                    if (userArr.length < 2) {
                        throw new PhiException("No number given. Please give one!");
                    } else if (userArr.length >= 3) {
                        throw new PhiException("Expected format: mark [number]");
                    } else {
                        // "mark " contains 5 characters.
                        int doneIndex = Integer.parseInt(userInput.substring(5));
                        Task doneTask = list.get(doneIndex - 1);
                        doneTask.markDone();
                        saveTasks(list);
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
                        Task undoneTask = list.get(undoneIndex - 1);
                        undoneTask.markUndone();
                        saveTasks(list);
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
                            System.out.println(clean[1]);
                            this.addTask(new Deadline(clean[0], LocalDate.parse(clean[1].trim())));
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
                            throw new PhiException("Expected format: event [description] /from [from time] /to [to time]");
                        } else {
                            String[] clean2 = clean1[1].split(" /to");
                            if (clean2.length != 2) {
                                throw new PhiException("Expected format: event [description] /from [from time] /to [to time]");
                            } else {
                                this.addTask(new Event(clean1[0], LocalDate.parse(clean2[0].trim()), LocalDate.parse(clean2[1].trim())));
                            }
                        }
                    }
                } else if (userInput.startsWith("delete")) {
                    String[] userArr = userInput.split(" ");
                    if (userArr.length < 2) {
                        throw new PhiException("No number given. Please give one!");
                    } else if (userArr.length >= 3) {
                        throw new PhiException("Expected format: delete [number]");
                    } else {
                        // "delete " contains 7 characters.
                        int deleteIndex = Integer.parseInt(userInput.substring(7));
                        Task taskDeleted = list.get(deleteIndex - 1);
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
            } catch (DateTimeParseException e) {
                System.out.println("Please enter valid date(s) in the format yyyy-mm-dd.");
                System.out.println();
            }
        }
    }

    private void saveTasks(ArrayList<Task> arr) {
        try {
            File dir = new File("./data/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try (FileWriter fw = new FileWriter(TEXTFILE_PATH, false)) {
                for (Task task : arr) {
                    fw.write(task.toString() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void loadTasks() {
        try (BufferedReader br = new BufferedReader(new FileReader(TEXTFILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    list.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    private Task parseTask(String line) {
        try {
            char taskType = line.charAt(1);
            boolean isDone = line.charAt(4) == 'X';
            String description = line.substring(6).trim();

            switch (taskType) {
                case 'T':
                    return new Todo(description, isDone);
                case 'D':
                    String[] deadlineParts = description.split(" \\(by: ");
                    if (deadlineParts.length == 2) {
                        return new Deadline(deadlineParts[0], isDone, LocalDate.parse(deadlineParts[1].replace(")", "")));
                    }
                    break;
                case 'E':
                    String[] eventParts = description.split(" \\(from: ");
                    if (eventParts.length == 2) {
                        String[] timeParts = eventParts[1].split(" to: ");
                        if (timeParts.length == 2) {
                            return new Event(eventParts[0], isDone, LocalDate.parse(timeParts[0]), LocalDate.parse(timeParts[1].replace(")", "")));
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error parsing task: " + e.getMessage());
        }
        return null;
    }

    public void addTask(Task userTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + userTask);
        list.add(userTask);
        saveTasks(list);
        this.taskCount();
    }

    public void deleteTask(Task userTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + userTask);
        list.remove(userTask);
        saveTasks(list);
        this.taskCount();
    }

    public void taskCount() {
        int listSize = list.size();
        if (listSize == 0) {
            System.out.println("Now you have no task in the list.");
        } else if (listSize == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + listSize +  " tasks in the list.");
        }
        System.out.println();
    }

    public void listInput() {
        if (list.size() == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                Task temp = list.get(i);
                System.out.println(String.format("%d.%s", i + 1, temp));
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Phi phi = new Phi();
        phi.greet();
        phi.checkInput();
    }
}