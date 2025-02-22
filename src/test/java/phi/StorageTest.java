package phi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
