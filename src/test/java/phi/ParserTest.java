package phi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToString() {
        Todo todo = new Todo("Buy oranges");
        assertEquals("[T][ ] Buy oranges", todo.toString(), "toString() should match expected format.");

        todo.markAsDone();
        assertEquals("[T][X] Buy oranges", todo.toString(), "toString() should reflect marked status.");
    }

    @Test
    public void testSerialise() {
        Todo todo = new Todo("Buy oranges");
        assertEquals("T | 0 | Buy oranges", todo.serialize(), "serialise() should correctly format an unmarked task.");

        todo.markAsDone();
        assertEquals("T | 1 | Buy oranges", todo.serialize(), "serialise() should correctly format a marked task.");
    }
}
