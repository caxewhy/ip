package solyu;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading and saving tasks from/to a file.
 * Provides functionality to read existing tasks from the file and write updates when tasks are modified.
 */
public class Storage {
    private static final String ERROR_LOAD_TASKS = "Error while loading tasks: ";
    private static final String ERROR_SAVE_TASKS = "Error while saving tasks: ";
    private final String filePath;

    /**
     * Creates a Storage object for the specified file path.
     * Ensures the file exists or creates a new one if missing.
     *
     * @param filePath The path of the file to load/save tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads and loads tasks from the storage file.
     * If the file does not exist, it will create a new empty file.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks; // return empty if no existing file
            }

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.isBlank()) {
                        continue;
                    }
                    String[] parts = line.split(" \\| ");
                    if (parts.length < 3) {
                        System.out.println("Invalid line: " + line);
                        continue;
                    }

                    String taskType = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    switch (taskType) {
                    case "T":
                        tasks.add(new ToDo(description, isDone));
                        break;
                    case "D":
                        if (parts.length >= 4) {
                            tasks.add(new Deadline(description, parts[3], isDone));
                        } else {
                            System.out.println("Invalid Deadline line: " + line);
                        }
                        break;
                    case "E":
                        if (parts.length >= 4) {
                            tasks.add(new Event(description, parts[3], isDone));
                        } else {
                            System.out.println("Invalid Event line: " + line);
                        }
                        break;
                    default:
                        System.out.println("Invalid task type: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(ERROR_LOAD_TASKS + e.getMessage());
        }
        return tasks;
    }

    /**
     * Writes the current list of tasks to the storage file.
     * Each task is formatted and saved in a line-separated format.
     *
     * @param tasks The {@code ArrayList<Task>} containing the tasks to be written to the file.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            try (PrintWriter writer = new PrintWriter(file)) {
                for (Task task : tasks) {
                    writer.println(task.toFileFormat());
                }
            }
        } catch (IOException e) {
            System.out.println(ERROR_SAVE_TASKS + e.getMessage());
        }
    }
}
