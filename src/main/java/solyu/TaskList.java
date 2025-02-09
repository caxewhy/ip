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
    public boolean addTask(Task task) {
        assert task != null : "Task to be added should not be null";
        boolean isDuplicate = tasks.stream()
                .anyMatch(existingTask -> existingTask.equals(task));

        if (isDuplicate) {
            return false; // Task is a duplicate
        }

        tasks.add(task);
        return true;
    }

    /**
     * Removes and returns the task at the given index.
     *
     * @param index The index of the task to remove.
     * @return The removed Task object.
     */
    public Task removeTask(int index) {
        assert index >= 0 && index < tasks.size() : "Task index out of range";
        return tasks.remove(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index The task index.
     */
    public void markTask(int index) {
        tasks.stream()
                .skip(index) // Skip to the desired task
                .findFirst()
                .ifPresent(Task::markAsDone);
    }

    /**
     * Unmarks a task as not done.
     *
     * @param index The task index.
     */
    public void unmarkTask(int index) {
        tasks.stream()
                .skip(index) // Skip to the desired task
                .findFirst()
                .ifPresent(Task::unmark);
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

    /**
     * Finds and returns tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }
}
