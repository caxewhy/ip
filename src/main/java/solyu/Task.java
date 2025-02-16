package solyu;

/**
 * This class serves as a base class for specific task types such as ToDo, Event, and Deadline.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is completed.
     * "X" indicates completed, while " " indicates not completed.
     *
     * @return A string representing the completion status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done by setting the status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done by setting the status to false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Converts the task details into a formatted string suitable for file storage.
     *
     * @return A string representation of the task in file storage format.
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }


    /**
     * Returns a string representation of the task.
     * Includes the completion status and task description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task other = (Task) obj;
        return description.equalsIgnoreCase(other.description); // Case-insensitive match
    }

    @Override
    public int hashCode() {
        return description.toLowerCase().hashCode(); // Consistent hash for case-insensitive check
    }

}
