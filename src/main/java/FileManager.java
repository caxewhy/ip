import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;

public class FileManager {
    private static final String FILE_PATH = Paths.get(System.getProperty("user.home"), "ip", "task.txt").toString();

    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            }

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.isBlank()) continue;

                    String[] parts = line.split(" \\| ");
                    if (parts.length < 3) {
                        System.out.println("Invalid lines: " + line);
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
                            System.out.println("Invalid Deadline: " + line);
                        }
                        break;

                    case "E":
                        if (parts.length >= 4) {
                            tasks.add(new Event(description, parts[3], isDone));
                        } else {
                            System.out.println("Invalid Events: " + line);
                        }
                        break;

                    default:
                        System.out.println("Invalid task type : " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error while loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public static void saveTasksToFile(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
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
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }
}

