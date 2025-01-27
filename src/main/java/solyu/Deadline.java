package solyu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = parseDateTime(dueDate);
    }

    public Deadline(String description, String dueDate, boolean isDone) {
        super(description);
        this.dueDate = parseDateTime(dueDate);
        this.isDone = isDone;
    }

    private LocalDate parseDateTime(String dateStr) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateStr, format);
    }

    @Override
    public String toString() {
        DateTimeFormatter userFriendlyFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (dueDate: " + dueDate.format(userFriendlyFormat) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dueDate;
    }
}
