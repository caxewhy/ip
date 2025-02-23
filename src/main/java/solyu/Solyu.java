package solyu;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class that drives the UI, Parser, TaskList, and Storage components.
 */
public class Solyu {
    // Constants for error messages
    private static final String ERROR_EMPTY_COMMAND =
            "Command cannot be empty. Please enter a valid command.";
    private static final String ERROR_INVALID_TASK_NUMBER =
            "Aye captain, Please specify a valid task number!";
    private static final String ERROR_TASK_NUMBER_OUT_OF_RANGE =
            "Aye captain, Task number out of range! Please enter a valid task number.";
    private static final String ERROR_INVALID_DATE_FORMAT =
            "Aye captain, Invalid date format! Please use yyyy-MM-dd.";
    private static final String ERROR_EMPTY_DESCRIPTION =
            "Aye captain, Please specify a task!";
    private static final String ERROR_MISSING_DEADLINE =
            "Aye captain, Please specify a deadline in the format: deadline <desc> /by yyyy-MM-dd";
    private static final String ERROR_MISSING_EVENT_PERIOD =
            "Aye captain, Please specify an event with a valid period: event <desc> /from X /to Y";
    private static final String ERROR_UNKNOWN_COMMAND =
            "Aye captain, do you want the ship to sink! I do not understand your command: ";
    private static final String ERROR_DUPLICATE_TASK =
            "Aye captain, This task already exists in your list!";
    private final Ui ui;
    private final Parser parser;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructs a Solyu instance with the given file path for storage.
     *
     * @param filePath The relative or absolute path to the file used for storage.
     */
    public Solyu() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        taskList = new TaskList(storage.loadTasksFromFile());
    }

    /**
     * Runs the main application loop, processes commands.
     */
    public void run() {
        ui.showGreeting();

        boolean isRunning = true;

        while (isRunning) {
            ui.showPrompt();
            String fullCommand = ui.readCommand();

            if (fullCommand == null || fullCommand.trim().isEmpty()) {
                ui.showError(ERROR_EMPTY_COMMAND);
                continue;
            }

            String response = getResponse(fullCommand);
            ui.showMessage(response);

            if ("bye".equals(parser.parse(fullCommand)[0])) {
                isRunning = false;
                System.exit(0);
            }
        }
    }

    /**
     * Returns the chatbot's response for the given user input.
     *
     * @param input User command.
     * @return Response from Solyu.
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";

        if (input == null || input.trim().isEmpty()) {
            return ui.getErrorMessage(ERROR_EMPTY_COMMAND);
        }

        String[] parsedInput = parser.parse(input);
        String command = parsedInput[0];
        String argument = parsedInput[1];

        switch (command) {
        case "greet":
            return ui.getGreetingMessage();

        case "find":
            return handleFind(argument);

        case "add":
            return handleTaskAdd(argument, new Task(argument));

        case "todo":
            return handleTodo(argument);

        case "deadline":
            return handleDeadline(argument);

        case "event":
            return handleEvent(argument);

        case "list":
            return ui.getListAsString(taskList.getTasks());

        case "mark":
            return handleTaskMark(argument, true);

        case "unmark":
            return handleTaskMark(argument, false);

        case "delete":
            return handleTaskDelete(argument);

        case "bye":
            storage.saveTasksToFile(new ArrayList<>(taskList.getTasks()));
            String goodbyeMessage = ui.getGoodbyeMessage();
            System.exit(0); // Exits the system completely
            return goodbyeMessage;

        default:
            return ui.getErrorMessage(ERROR_UNKNOWN_COMMAND + command + "\nPlease try another command :)");
        }
    }

    /**
     * Finds tasks containing the given keyword.
     *
     * @param argument The keyword to search for.
     * @return Formatted message displaying found tasks or an error message if none found.
     */
    private String handleFind(String argument) {
        if (argument.isEmpty()) {
            return ui.getErrorMessage("Please provide a keyword to search.");
        }
        List<Task> foundTasks = taskList.findTasks(argument);
        return foundTasks.isEmpty() ? ui.getErrorMessage("No matching tasks found!")
                : ui.getFoundTasksMessage(foundTasks);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param argument The task description.
     * @param task The task to be added.
     * @return Formatted message confirming task addition.
     */
    private String handleTaskAdd(String argument, Task task) {
        if (argument.isEmpty()) {
            return ui.getErrorMessage(ERROR_EMPTY_DESCRIPTION);
        }
        boolean isAdded = taskList.addTask(task);

        if (!isAdded) {
            return ui.getErrorMessage(ERROR_DUPLICATE_TASK);
        }
        storage.saveTasksToFile(new ArrayList<>(taskList.getTasks()));
        return ui.getTaskAddedMessage(argument);
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param argument The task description.
     * @return Formatted message confirming ToDo task addition.
     */
    private String handleTodo(String argument) {
        if (argument.isEmpty()) {
            return ui.getErrorMessage(ERROR_EMPTY_DESCRIPTION);
        }
        ToDo todo = new ToDo(argument);
        boolean isAdded = taskList.addTask(todo);

        if (!isAdded) {
            return ui.getErrorMessage(ERROR_DUPLICATE_TASK);
        }
        storage.saveTasksToFile(new ArrayList<>(taskList.getTasks()));
        return ui.getToDoAddedMessage(argument, taskList.size());
    }

    /**
     * Adds a new deadline task.
     *
     * @param argument The task description and due date.
     * @return Formatted message confirming deadline addition or an error message.
     */
    private String handleDeadline(String argument) {
        if (!argument.contains("/by")) {
            return ERROR_MISSING_DEADLINE;
        }
        String[] deadlineParts = argument.split("/by", 2);
        String deadlineDesc = deadlineParts[0].trim();
        String deadlineBy = deadlineParts[1].trim();

        if (deadlineDesc.isEmpty()) {
            return ERROR_EMPTY_DESCRIPTION;
        } else if (deadlineBy.isEmpty()) {
            return ERROR_MISSING_DEADLINE;
        }

        try {
            Deadline deadline = new Deadline(deadlineDesc, deadlineBy);
            boolean isAdded = taskList.addTask(deadline);

            if (!isAdded) {
                return ui.getErrorMessage(ERROR_DUPLICATE_TASK);
            }

            storage.saveTasksToFile(new ArrayList<>(taskList.getTasks()));
            return "Added deadline: " + deadlineDesc + " (by: " + deadlineBy + ")";
        } catch (IllegalArgumentException e) {
            return ERROR_INVALID_DATE_FORMAT;
        }
    }

    /**
     * Adds a new event task.
     *
     * @param argument The event description, start time, and end time.
     * @return Formatted message confirming event addition or an error message.
     */
    private String handleEvent(String argument) {
        if (!argument.contains("/from") || !argument.contains("/to")) {
            return ERROR_MISSING_EVENT_PERIOD;
        }

        String[] parts = argument.split("/from", 2);
        String description = parts[0].trim();
        String fromTo = parts[1].trim();
        String[] period = fromTo.split("/to", 2);

        if (period.length < 2 || description.isEmpty() || period[0].trim().isEmpty() || period[1].trim().isEmpty()) {
            return ERROR_MISSING_EVENT_PERIOD;
        }

        Event event = new Event(description, period[0].trim() + " to " + period[1].trim());
        boolean isAdded = taskList.addTask(event);

        if (!isAdded) {
            return ui.getErrorMessage(ERROR_DUPLICATE_TASK);
        }
        storage.saveTasksToFile(new ArrayList<>(taskList.getTasks()));
        return ui.getEventAddedMessage(description, period[0].trim(), period[1].trim());
    }

    /**
     * Marks or unmarks a task as completed or incomplete.
     *
     * @param argument The task number.
     * @param isMarking True if marking as completed, false if unmarking.
     * @return Formatted message confirming action or an error message.
     */
    private String handleTaskMark(String argument, boolean isMarking) {
        assert argument != null : "Task marking argument should not be null";

        if (argument.isEmpty()) {
            return ERROR_INVALID_TASK_NUMBER;
        }
        try {
            int index = Integer.parseInt(argument) - 1;
            assert index >= 0 && index < taskList.size() : "Task index should be within valid range";
            if (index < 0 || index >= taskList.size()) {
                return ui.getErrorMessage(ERROR_TASK_NUMBER_OUT_OF_RANGE);
            }
            if (isMarking) {
                taskList.markTask(index);
                return ui.getTaskMarkedMessage(taskList.getTask(index));
            } else {
                taskList.unmarkTask(index);
                return ui.getTaskUnmarkedMessage(taskList.getTask(index));
            }
        } catch (NumberFormatException e) {
            return ERROR_INVALID_TASK_NUMBER;
        } catch (IndexOutOfBoundsException e) {
            return ERROR_TASK_NUMBER_OUT_OF_RANGE;
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param argument The task number to delete.
     * @return Formatted message confirming task deletion or an error message.
     */
    private String handleTaskDelete(String argument) {
        if (argument.isEmpty()) {
            return ERROR_INVALID_TASK_NUMBER;
        }
        try {
            int index = Integer.parseInt(argument) - 1;
            Task removedTask = taskList.removeTask(index);
            storage.saveTasksToFile(new ArrayList<>(taskList.getTasks()));
            return ui.getTaskDeletedMessage(removedTask, taskList.size());
        } catch (NumberFormatException e) {
            return ERROR_INVALID_TASK_NUMBER;
        } catch (IndexOutOfBoundsException e) {
            return ERROR_TASK_NUMBER_OUT_OF_RANGE;
        }
    }
    /**
     * Starts the Solyu chatbot application.
     * This method intializes and runs the chatbot's main loop.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Solyu().run();
    }
}
