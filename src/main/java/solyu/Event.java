package solyu;

/**
 * An event task in the task list.
 */
public class Event extends Task {
    protected String from;

    /**
     * Constructs an Event task with the specified description and time period.
     *
     * @param description The description of the event.
     * @param from The time period of the event.
     */
    public Event(String description, String from) {
        super(description);
        this.from = from;
    }

    /**
     * Constructs an Event task with the specified description, time period, and completion status.
     *
     * @param description The description of the event.
     * @param from The time period of the event.
     * @param isDone true if the task is completed, otherwise false.
     */
    public Event(String description, String from, boolean isDone) {
        super(description);
        this.from = from;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the event task.
     * The format includes the task type identifier [E], the completion status,
     * the task description, and the time period.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ")";
    }

    /**
     * Returns a  string for saving the event task to a file.
     *
     * @return A string for file storage in the format "E | 1 | description | from".
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from;
    }
}
