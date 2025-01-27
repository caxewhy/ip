package solyu;

import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Main class that drives the UI, Parser, TaskList, and Storage components.
 */
public class Solyu {
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a Solyu instance with the given file path for storage.
     *
     * @param filePath The relative or absolute path to the file used for storage.
     */
    public Solyu(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(Paths.get(
                System.getProperty("user.home"), "ip", filePath).toString());
        taskList = new TaskList(storage.loadTasksFromFile());
    }

    /**
     * Runs the main application loop, processes commands.
     */
    public void run() {
        ui.showGreeting();

        while (true) {
            ui.showPrompt();
            String fullCommand = ui.readCommand();

            if (fullCommand == null || fullCommand.trim().isEmpty()) {
                ui.showError("Command cannot be empty. Please enter a valid command.");
                continue;  // Skip iteration and ask for input again
            }

            String[] parsedInput = parser.parse(fullCommand);
            String command = parsedInput[0];
            String argument = parsedInput[1];

            switch (command) {
            case "greet":
                ui.showGreeting();
                break;

            case "find":
                if (!argument.isEmpty()) {
                    ui.showFoundTasks(taskList.findTasks(argument));
                } else {
                    ui.showError("Please provide a keyword to search.");
                }
                break;

            case "add":
                if (!argument.isEmpty()) {
                    taskList.addTask(new Task(argument));
                    ui.showTaskAdded(argument);
                } else {
                    ui.showError("Please specify a task!");
                }
                break;

            case "todo":
                if (!argument.isEmpty()) {
                    taskList.addTask(new ToDo(argument));
                    ui.showToDoAdded(argument, taskList.size());
                } else {
                    ui.showError("Please specify a task!");
                }
                break;

            case "deadline":
                if (argument.contains("/by")) {
                    String[] parts = argument.split("/by", 2);
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    taskList.addTask(new Deadline(description, by));
                    ui.showDeadlineAdded(description, by, taskList.size());
                } else {
                    ui.showError("Please specify a deadline in the format: deadline <desc> /by yyyy-mm-dd");
                }
                break;

            case "event":
                if (argument.contains("/from") && argument.contains("/to")) {
                    String[] parts = argument.split("/from", 2);
                    String description = parts[0].trim();
                    String fromTo = parts[1].trim();
                    String[] period = fromTo.split("/to", 2);
                    String from = period[0].trim();
                    String to = period[1].trim();
                    taskList.addTask(new Event(description, from + " to " + to));
                    ui.showEventAdded(description, from, to, taskList.size());
                } else {
                    ui.showError("Please specify an event with a valid period: event <desc> /from X /to Y");
                }
                break;

            case "list":
                ui.showList(taskList.getTasks());
                break;

            case "mark":
                if (!argument.isEmpty()) {
                    try {
                        int index = Integer.parseInt(argument) - 1;
                        taskList.markTask(index);
                        ui.showTaskMarked(taskList.getTask(index));
                    } catch (NumberFormatException e) {
                        ui.showError("Invalid input! Please enter a valid task number.");
                    } catch (IndexOutOfBoundsException e) {
                        ui.showError("Invalid task number!");
                    }
                } else {
                    ui.showError("Please specify a task number!");
                }
                break;

            case "unmark":
                if (!argument.isEmpty()) {
                    try {
                        int index = Integer.parseInt(argument) - 1;
                        taskList.unmarkTask(index);
                        ui.showTaskUnmarked(taskList.getTask(index));
                    } catch (NumberFormatException e) {
                        ui.showError("Invalid input! Please enter a valid task number.");
                    } catch (IndexOutOfBoundsException e) {
                        ui.showError("Invalid task number!");
                    }
                } else {
                    ui.showError("Please specify a task number!");
                }
                break;

            case "delete":
                if (!argument.isEmpty()) {
                    try {
                        int index = Integer.parseInt(argument) - 1;
                        ui.showTaskDeleted(taskList.removeTask(index), taskList.size());
                    } catch (NumberFormatException e) {
                        ui.showError("Invalid input! Please enter a valid task number.");
                    } catch (IndexOutOfBoundsException e) {
                        ui.showError("Invalid task number!");
                    }
                } else {
                    ui.showError("Please specify a task number!");
                }
                break;

            case "bye":
                ui.showGoodbye();
                storage.saveTasksToFile((ArrayList<Task>) taskList.getTasks());
                return;

            default:
                ui.showError("Oh no! I do not understand your command: " + command);
                break;
            }
        }
    }

    /**
     * The main entry point of the application.
     * Initializes and runs the Solyu chatbot.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Example usage: Using "task.txt" as the filename under user home/ip directory
        new Solyu("task.txt").run();
    }
}
