package solyu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testEventCreation() {
        Event event = new Event("Project meeting", "Aug 6th 2pm to 4pm");
        assertEquals("[E][ ] Project meeting (from: Aug 6th 2pm to 4pm)", event.toString(),
                "Event string representation should match expected format.");
    }

    @Test
    void testEventMarkedAsDone() {
        Event event = new Event("Company retreat", "May 10th 10am to 5pm");
        event.markAsDone();
        assertEquals("[E][X] Company retreat (from: May 10th 10am to 5pm)", event.toString(),
                "Event should be marked as done.");
    }

    @Test
    void testEventUnmark() {
        Event event = new Event("Team outing", "Dec 1st 9am to 6pm", true);
        event.unmark();
        assertEquals("[E][ ] Team outing (from: Dec 1st 9am to 6pm)", event.toString(),
                "Event should be unmarked and set to not done.");
    }

    @Test
    void testEventFileFormat() {
        Event event = new Event("Conference call", "Nov 20th 3pm to 5pm");
        assertEquals("E | 0 | Conference call | Nov 20th 3pm to 5pm", event.toFileFormat(),
                "Event file format should match expected output.");
    }

    @Test
    void testEventFileFormatWhenMarked() {
        Event event = new Event("Product launch", "Jan 15th 1pm to 3pm", true);
        assertEquals("E | 1 | Product launch | Jan 15th 1pm to 3pm", event.toFileFormat(),
                "Event file format should reflect done status.");
    }
}
