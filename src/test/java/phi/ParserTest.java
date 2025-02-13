package phi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class ParserTest {
    // For parseTodo
    @Test
    public void testParseTodo_validInput() {
        String line = "todo Buy milk";
        try {
            Task task = Parser.parseTodo(line);
            assertNotNull(task);
            assertTrue(task instanceof Todo);
            assertEquals("Buy milk", ((Todo) task).getDescription());
        } catch (PhiException e) {
            fail("Exception should not be thrown for valid input");
        }
    }

    @Test
    public void testParseTodo_missingDescription() {
        String line = "todo";
        PhiException exception = assertThrows(PhiException.class, () -> {
            Parser.parseTodo(line);
        });
        assertEquals("No description given. Please give one!", exception.getMessage());
    }

    // For parseTodo
    @Test
    public void testParseDeadline_validInput() {
        String line = "deadline Submit report /by 2025-02-01";
        try {
            Task task = Parser.parseDeadline(line);
            assertNotNull(task);
            assertTrue(task instanceof Deadline);
            assertEquals("Submit report", ((Deadline) task).getDescription());
            assertEquals(LocalDate.parse("2025-02-01"), ((Deadline) task).getBy());
        } catch (PhiException e) {
            fail("Exception should not be thrown for valid input");
        }
    }

    @Test
    public void testParseDeadline_missingDescription() {
        String line = "deadline";
        PhiException exception = assertThrows(PhiException.class, () -> {
            Parser.parseDeadline(line);
        });
        assertEquals("No description given. Please give one!", exception.getMessage());
    }
}

