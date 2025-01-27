package solyu;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the Task list and its operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks Preloaded tasks from storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the given index.
     *
     * @param index The index of the task to remove.
     * @return The removed Task object.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index The task index.
     */
    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks a task as not done.
     *
     * @param index The task index.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Retrieves a task from the list.
     *
     * @param index The task index.
     * @return The Task object at that index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the total number of tasks.
     *
     * @return Size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying list of Task objects.
     *
     * @return A List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
