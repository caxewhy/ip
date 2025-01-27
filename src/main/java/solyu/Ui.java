package solyu;

import java.util.List;
import java.util.Scanner;

/**
 * Handles all input/output operations with the user.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message.
     */
    public void showGreeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Solyu");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a prompt for user input.
     */
    public void showPrompt() {
        System.out.print(" Enter a command: ");
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
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a goodbye message.
     */
    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a task added message.
     *
     * @param description The task description.
     */
    public void showTaskAdded(String description) {
        System.out.println("____________________________________________________________");
        System.out.println("Task added: " + description);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a todo added message with the total size of tasks.
     */
    public void showToDoAdded(String description, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][ ] " + description);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a deadline added message with the total size of tasks.
     */
    public void showDeadlineAdded(String description, String by, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("[D][ ] " + description + " (by: " + by + ")");
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an event added message with the total size of tasks.
     */
    public void showEventAdded(String desc, String from, String to, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("[E][ ] " + desc + " (from: " + from + " to " + to + ")");
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The tasks to display.
     */
    public void showList(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("Task list is empty!");
        } else {
            System.out.println("You have " + tasks.size() + " tasks in your list.");
            System.out.println("Here is your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a task marked message.
     */
    public void showTaskMarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Task marked as done: " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a task unmarked message.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Task unmarked: " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a task deleted message.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
