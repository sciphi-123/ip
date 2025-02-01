package phi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.time.LocalDate;

public class StorageTest {
    private static final String FILE_PATH = "./data/test_tasks.txt";
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        storage = new Storage(FILE_PATH);
        taskList = new TaskList();

        taskList.addTask(new Todo("Buy milk"));
        taskList.addTask(new Deadline("Submit report", LocalDate.parse("2025-02-01")));
        taskList.addTask(new Event("Team meeting", LocalDate.parse("2025-02-01"), LocalDate.parse("2025-02-02")));
    }

    @Test
    public void testSaveTasks() {
        storage.saveTasks(taskList);

        File file = new File(FILE_PATH);
        assertTrue(file.exists(), "File should exist after saving tasks.");

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = br.readLine();
            assertNotNull(line, "File should contain at least one task.");
            assertTrue(line.contains("Buy milk"), "File should contain the correct task description.");
        } catch (IOException e) {
            fail("Error reading the file after saving tasks: " + e.getMessage());
        }
    }

    @Test
    public void testLoadTasks() {
        storage.saveTasks(taskList);

        TaskList newTaskList = new TaskList();
        storage.loadTasks(newTaskList);

        assertEquals(3, newTaskList.size(), "There should be 3 tasks loaded.");
        Task task1 = newTaskList.getTask(0);
        Task task2 = newTaskList.getTask(1);
        Task task3 = newTaskList.getTask(2);

        assertTrue(task1 instanceof Todo, "First task should be a Todo.");
        assertTrue(task2 instanceof Deadline, "Second task should be a Deadline.");
        assertTrue(task3 instanceof Event, "Third task should be an Event.");

        assertEquals("Buy milk", ((Todo) task1).getDescription(), "First task description should be 'Buy milk'.");
        assertEquals("Submit report", ((Deadline) task2).getDescription(), "Second task description should be 'Submit report'.");
        assertEquals("Team meeting", ((Event) task3).getDescription(), "Third task description should be 'Team meeting'.");
    }
}