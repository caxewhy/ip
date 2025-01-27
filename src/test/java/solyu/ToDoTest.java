package solyu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testToDoCreation() {
        ToDo todo = new ToDo("Read book");
        assertEquals("[T][ ] Read book", todo.toString(),
                "ToDo string representation should match expected format.");
    }

    @Test
    void testToDoMarkedAsDone() {
        ToDo todo = new ToDo("Complete assignment");
        todo.markAsDone();
        assertEquals("[T][X] Complete assignment", todo.toString(),
                "ToDo should be marked as done.");
    }

    @Test
    void testToDoUnmark() {
        ToDo todo = new ToDo("Buy groceries", true);
        todo.unmark();
        assertEquals("[T][ ] Buy groceries", todo.toString(),
                "ToDo should be unmarked and set to not done.");
    }

    @Test
    void testToDoFileFormat() {
        ToDo todo = new ToDo("Go to gym");
        assertEquals("T | 0 | Go to gym", todo.toFileFormat(),
                "ToDo file format should match expected output.");
    }

    @Test
    void testToDoFileFormatWhenMarked() {
        ToDo todo = new ToDo("Prepare for exam", true);
        assertEquals("T | 1 | Prepare for exam", todo.toFileFormat(),
                "ToDo file format should reflect done status.");
    }
}
