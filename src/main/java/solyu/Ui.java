package solyu;

import java.util.List;
import java.util.Scanner;

/**
 * Handles all input/output operations with the user.
 */
public class Ui {
    private static final String PROMPT_MESSAGE = "Enter a command: ";
    private static final String MESSAGE_EMPTY_LIST = "Task list is empty!";
    private final Scanner scanner;

    /**
     * Constructs a Ui object with a scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message.
     */
    public void showGreeting() {
        System.out.println(getGreetingMessage());
    }

    /**
     * Prints a prompt for user input.
     */
    public void showPrompt() {
        System.out.print(PROMPT_MESSAGE);
    }

    /**
     * Reads a line of user input.
     *
     * @return The full command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(getErrorMessage(message));
    }

    /**
     * Prints a generic message.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Returns the greeting message.
     *
     * @return Greeting message as a string.
     */
    public String getGreetingMessage() {
        return "Hello! I'm Solyu\n"
                + "What can I do for you?";
    }

    /**
     * Returns an error message.
     *
     * @param message The error message.
     * @return Formatted error message.
     */
    public String getErrorMessage(String message) {
        return message;
    }

    /**
     * Returns the goodbye message.
     *
     * @return Goodbye message as a string.
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message for a task being added.
     *
     * @param description Task description.
     * @return Formatted task added message.
     */
    public String getTaskAddedMessage(String description) {
        return "Task added: " + description;
    }

    /**
     * Returns a formatted message for a ToDo task being added.
     *
     * @param description Task description.
     * @param size The updated number of tasks.
     * @return Formatted ToDo added message.
     */
    public String getToDoAddedMessage(String description, int size) {
        return "Got it. I've added this task:\n"
                + "[T][ ] " + description + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns a formatted message for an event task being added.
     *
     * @param description The task description.
     * @param from The start time.
     * @param to The end time.
     * @return Formatted event added message.
     */
    public String getEventAddedMessage(String description, String from, String to) {
        return "Got it. I've added this task:\n"
                + "[E][ ] " + description + " (from: " + from + " to " + to + ")";
    }

    /**
     * Returns a formatted message for marking a task as done.
     *
     * @param task The marked task.
     * @return Formatted message for marking a task.
     */
    public String getTaskMarkedMessage(Task task) {
        return "Task marked as done: " + task;
    }

    /**
     * Returns a formatted message for unmarking a task.
     *
     * @param task The unmarked task.
     * @return Formatted message for unmarking a task.
     */
    public String getTaskUnmarkedMessage(Task task) {
        return "Task unmarked: " + task;
    }

    /**
     * Returns a formatted message for deleting a task.
     *
     * @param task The deleted task.
     * @param size The updated number of tasks.
     * @return Formatted message for task deletion.
     */
    public String getTaskDeletedMessage(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns a formatted list of tasks.
     *
     * @param tasks List of tasks.
     * @return Formatted task list.
     */
    public String getListAsString(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return MESSAGE_EMPTY_LIST;
        }

        StringBuilder listString = new StringBuilder();
        listString.append("Here is your task list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            listString.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return listString.toString();
    }

    /**
     * Returns a formatted message for found tasks.
     *
     * @param foundTasks List of matching tasks.
     * @return Formatted search results message.
     */
    public String getFoundTasksMessage(List<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            return "No matching tasks found!";
        }

        StringBuilder foundList = new StringBuilder();
        foundList.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < foundTasks.size(); i++) {
            foundList.append((i + 1)).append(". ").append(foundTasks.get(i)).append("\n");
        }
        return foundList.toString();
    }

}
