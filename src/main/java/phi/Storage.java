package phi;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Storage {
    private String textFilePath;

    public Storage(String textFilePath) {
        this.textFilePath = textFilePath;
        File file = new File(this.textFilePath);
        File dir = file.getParentFile();

        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }
    }

    public void saveTasks(TaskList tasks) {
        try {
            File dir = new File("./data/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try (FileWriter fw = new FileWriter(textFilePath, false)) {
                for (int i = 0; i < tasks.size(); i++){
                    Task task = tasks.getTask(i);
                    fw.write(task.toString() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void loadTasks(TaskList tasks) {
        try (BufferedReader br = new BufferedReader(new FileReader(textFilePath))) {
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
