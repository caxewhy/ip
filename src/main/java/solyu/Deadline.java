package solyu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Deadline task in the task list.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a new deadline task with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date in the format "yyyy-MM-dd".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    /**
     * Constructs a new deadline task with the given description, due date, and completion status.
     *
     * @param description The description of the deadline task.
     * @param by The due date in the format "yyyy-MM-dd".
     * @param isDone true if the task is completed, otherwise false.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = parseDateTime(by);
        this.isDone = isDone;
    }

    /**
     * Parses a date string in the format "yyyy-MM-dd" into a LocalDate object.
     *
     * @param dateStr The due date in string format.
     * @return The parsed LocalDate object.
     */
    private LocalDate parseDateTime(String dateStr) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateStr, format);
    }

    /**
     * Returns a string representation of the deadline task.
     * The format includes the task type identifier [D], the completion status, task description,
     * and the due date formatted as user readable format 'MMM dd yyyy' (e.g., Oct 15 2024).
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter userFriendlyFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(userFriendlyFormat) + ")";
    }

    /**
     * Returns a string for saving the deadline task to a file.
     *
     * @return A string for file storage in the format "D | 1 | description | yyyy-MM-dd".
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
