package solyu;

import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}

class AddCommand extends Command {
    private final String description;

    public AddCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new Task(description);
        taskList.addTask(task);
        ui.showMessage("Added task: " + task);
        storage.saveTasksToFile((ArrayList<Task>) taskList.getTasks());
    }
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder("Task list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            output.append(i + 1).append(". ").append(taskList.getTask(i)).append("\n");
        }
        ui.showMessage(output.toString());
    }
}

class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task removedTask = taskList.removeTask(taskIndex);
            ui.showMessage("Removed task: " + removedTask);
            storage.saveTasksToFile((ArrayList<Task>) taskList.getTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("Invalid task index!");
        }
    }
}

class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.getTask(taskIndex);
            task.markAsDone();
            ui.showMessage("Marked task as done: " + task);
            storage.saveTasksToFile((ArrayList<Task>) taskList.getTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("Invalid task index!");
        }
    }
}

class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.getTask(taskIndex);
            task.unmark();
            ui.showMessage("Unmarked task: " + task);
            storage.saveTasksToFile((ArrayList<Task>) taskList.getTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("Invalid task index!");
        }
    }
}

class ToDoCommand extends Command {
    private final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new ToDo(description);
        taskList.addTask(task);
        ui.showMessage("Got it. I've added this task:\n" + task);
        storage.saveTasksToFile((ArrayList<Task>) taskList.getTasks());
    }
}

class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new Deadline(description, by);
        taskList.addTask(task);
        ui.showMessage("Got it. I've added this task:\n" + task);
        storage.saveTasksToFile((ArrayList<Task>) taskList.getTasks());
    }
}

class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new Event(description, from + " to " + to);
        taskList.addTask(task);
        ui.showMessage("Got it. I've added this task:\n" + task);
        storage.saveTasksToFile((ArrayList<Task>) taskList.getTasks());
    }
}
