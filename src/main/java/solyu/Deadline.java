package solyu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Constructs a Deadline task with a description and due date.
     *
     * @param description The task description.
     * @param dueDate The due date in string format (yyyy-MM-dd).
     * @throws IllegalArgumentException if the date format is invalid.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        assert dueDate != null && !dueDate.trim().isEmpty() : "Due date should not be null or empty";
        this.dueDate = parseDateTime(dueDate);
    }

    /**
     * Constructs a Deadline task with a description, due date, and completion status.
     *
     * @param description The task description.
     * @param dueDate The due date in string format (yyyy-MM-dd).
     * @param isDone true if the task is completed, otherwise false.
     * @throws IllegalArgumentException if the date format is invalid.
     */
    public Deadline(String description, String dueDate, boolean isDone) {
        super(description);
        this.dueDate = parseDateTime(dueDate);
        this.isDone = isDone;
    }

    /**
     * Parses a date string in the format "yyyy-MM-dd" into a LocalDate object.
     * Handles invalid date formats gracefully.
     *
     * @param dateStr The due date in string format.
     * @return The parsed LocalDate object.
     * @throws IllegalArgumentException if the date format is invalid.
     */
    private LocalDate parseDateTime(String dateStr) {
        assert dateStr != null && !dateStr.trim().isEmpty() : "Date string should not be null or empty";

        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateStr, format);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Error: Invalid date format! Please use yyyy-MM-dd (e.g., 2024-02-05)."
            );
        }
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter userFriendlyFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (Due Date: " + dueDate.format(userFriendlyFormat) + ")";
    }

    /**
     * Returns a string for saving the deadline task to a file.
     *
     * @return A string for file storage in the format "D | 1 | description | yyyy-MM-dd".
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dueDate;
    }
}
