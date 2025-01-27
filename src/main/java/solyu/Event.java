package solyu;

public class Event extends Task {
    protected String from;

    public Event(String description, String from) {
        super(description);
        this.from = from;
    }

    public Event(String description, String from, boolean isDone) {
        super(description);
        this.from = from;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from;
    }
}
