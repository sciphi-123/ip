package phi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class handles the storage of tasks by saving and loading them to/from a file.
 * It is responsible for reading and writing tasks to a file and parsing them to appropriate task objects.
 */
public class Storage {

    private static String FILE_PATH = "./data/phi.txt";

    /**
     * Constructs a Storage object with the given file path.
     * If the directory does not exist, it will be created.
     *
     * @param textFilePath The path to the file where tasks are stored.
     */
    public Storage(String textFilePath) {
        this.FILE_PATH = textFilePath;
        File file = new File(this.FILE_PATH);
        File dir = file.getParentFile();

        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Saves the tasks to the file specified in the textFilePath.
     * The tasks are written to the file in a format that can later be parsed back into Task objects.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasks(TaskList tasks) {
        try {
            File dir = new File("./data/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try (FileWriter fw = new FileWriter(FILE_PATH, false)) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.getTask(i);
                    fw.write(task.toString() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file specified in the textFilePath and adds them to the provided TaskList.
     * Each line in the file is parsed into a Task object.
     *
     */
    public static TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(FILE_PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.addTask(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a line of text into a corresponding Task object.
     * This method assumes the line is formatted correctly and contains information about the task type, description, and status.
     *
     * @param line A line of text representing a task.
     * @return The Task object parsed from the line, or null if the line cannot be parsed.
     */
    private static Task parseTask(String line) {
        try {
            char taskType = line.charAt(1);
            boolean isDone = line.charAt(4) == 'X';
            String description = line.substring(6).trim();

            switch (taskType) {
            case 'T':
                return new Todo(description, isDone);
            case 'D':
                String[] deadlineParts = description.split(" \\(by: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                if (deadlineParts.length == 2) {
                    return new Deadline(deadlineParts[0], isDone, LocalDate.parse(deadlineParts[1].replace(")", ""), formatter));
                }
                break;
            case 'E':
                String[] eventParts = description.split(" \\(from: ");
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMM d yyyy");
                if (eventParts.length == 2) {
                    String[] timeParts = eventParts[1].split(" to: ");
                    if (timeParts.length == 2) {
                        return new Event(eventParts[0], isDone, LocalDate.parse(timeParts[0], formatter1), LocalDate.parse(timeParts[1].replace(")", ""), formatter1));
                    }
                }
                break;
            }
        } catch (Exception e) {
            System.out.println("Error parsing task: " + e.getMessage());
        }
        return null;
    }
}
