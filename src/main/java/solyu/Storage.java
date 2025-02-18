package solyu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading and saving tasks from/to a file.
 * Ensures proper handling of missing files and folders.
 */
public class Storage {
    private static final String ERROR_LOAD_TASKS = "Error while loading tasks: ";
    private static final String ERROR_SAVE_TASKS = "Error while saving tasks: ";

    private static final String DIRECTORY_NAME = "data"; // Relative directory
    private static final String FILE_NAME = "task.txt"; // File name
    private final Path filePath;

    /**
     * Creates a Storage object using a relative path inside the `data` directory.
     * Ensures the folder and file exist.
     */
    public Storage() {
        this.filePath = Paths.get(DIRECTORY_NAME, FILE_NAME);
        ensureFileExists();
    }

    /**
     * Ensures the data directory and file exist.
     * Creates them if they are missing.
     */
    private void ensureFileExists() {
        File directory = new File(DIRECTORY_NAME);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the "data" directory if missing
        }

        File file = filePath.toFile();
        try {
            if (!file.exists()) {
                file.createNewFile(); // Create the file if missing
            }
        } catch (IOException e) {
            System.out.println("Error creating storage file: " + e.getMessage());
        }
    }

    /**
     * Reads and loads tasks from the storage file.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = filePath.toFile();

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
        assert tasks != null : "Task list to be saved should not be null";

        try {
            Files.write(filePath, tasks.stream()
                    .map(Task::toFileFormat)
                    .toList());
        } catch (IOException e) {
            System.out.println(ERROR_SAVE_TASKS + e.getMessage());
        }
    }
}
