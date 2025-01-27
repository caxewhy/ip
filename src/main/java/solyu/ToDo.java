package solyu;

/**
 *  To-Do task in the task list.
 */
public class ToDo extends Task {

    /**
     * Constructs a new to do task with a description.
     *
     * @param description The description of the to do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new to do task with a description and completion status
     *
     * @param description The description of the to do task.
     * @param isDone true if the task is completed, otherwise false.
     */
    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the to do task.
     * The format includes the task type identifier [T], the completion status, and the task description.
     *
     * @return A string representing the to do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
