package solyu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void testDeadlineCreation() {
        Deadline deadline = new Deadline("Project submission", "2025-12-01");
        assertEquals("[D][ ] Project submission (by: Dec 01 2025)", deadline.toString(),
                "Deadline string should be formatted correctly.");
    }

    @Test
    void testMarkAsDone() {
        Deadline deadline = new Deadline("Pay bills", "2024-10-20");
        deadline.markAsDone();
        assertEquals("[D][X] Pay bills (by: Oct 20 2024)", deadline.toString(),
                "Deadline should reflect as done with X.");
    }

    @Test
    void testInvalidDateFormat() {
        assertThrows(Exception.class, () -> new Deadline("Submit assignment", "20-10-2024"),
                "Invalid date format should throw exception.");
    }

    @Test
    void testFileFormat() {
        Deadline deadline = new Deadline("Complete project", "2023-07-15");
        assertEquals("D | 0 | Complete project | 2023-07-15", deadline.toFileFormat(),
                "File format should be correct.");
    }
}
