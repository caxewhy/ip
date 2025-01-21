import java.util.Scanner;
import java.util.ArrayList;

public class Solyu {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();

        String greet = "____________________________________________________________\n"
                + " Hello! I'm Solyu \n"
                + " What can I do for you? \n"
                + "____________________________________________________________\n";

        String blah = "____________________________________________________________\n"
                + " blah\n"
                + "____________________________________________________________\n";

        String bye = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";

        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(" Enter a command:");
            String userInput = scanner.nextLine().trim();
            String[] parts = userInput.split(" ", 2);
            String command = parts[0].toLowerCase();
            String argument = parts.length > 1 ? parts[1] : "";

            switch (command) {
                case "greet":
                    System.out.println(greet);
                    break;

                case "add":
                    if (!argument.isEmpty()) {
                        tasks.add(new Task(argument));
                        System.out.println("____________________________________________________________");
                        System.out.println("Task added: " + argument);
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please specify a task!");
                        System.out.println("____________________________________________________________");
                    }
                    break;

                case "todo":
                    if (!argument.isEmpty()) {
                        tasks.add(new ToDo(argument));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("[T][ ] " + argument);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please specify a task!");
                        System.out.println("____________________________________________________________");
                    }
                    break;

                case "deadline":
                    if (argument.contains("/by")) {
                        parts = argument.split("/by", 2);
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        tasks.add(new Deadline(description, by));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("[D][ ] " + description + " (by: " + by + ")");
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please specify deadline!");
                        System.out.println("____________________________________________________________");
                    }
                    break;

                case "event":
                    if (argument.contains("/from") && argument.contains("/to")) {
                        parts = argument.split("/from", 2);
                        String description = parts[0].trim();
                        String fromTo = parts[1].trim();
                        String[] period = fromTo.split("/to", 2);
                        String from = period[0].trim();
                        String to = period[1].trim();
                        tasks.add(new Event(description, from + " to " + to));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("[E][ ] " + description + " (from: " + from + " to " + to + ")");
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please specify an event with a valid period!");
                        System.out.println("____________________________________________________________");
                    }
                    break;

                case "list":
                    System.out.println("____________________________________________________________");
                    if (tasks.isEmpty()) {
                        System.out.println("Task list is empty!");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case "mark":
                    if (!argument.isEmpty()) {
//                        System.out.println("Task to be marked: ");
                        int mark = Integer.parseInt(argument) - 1;
                        if (mark >= 0 && mark < tasks.size()) {
                            tasks.get(mark).markAsDone();
                            System.out.println("____________________________________________________________");
                            System.out.println("Task marked as done: " + tasks.get(mark));
                            System.out.println("____________________________________________________________");
                        } else {
                            System.out.println("____________________________________________________________");
                            System.out.println("Invalid task!");
                            System.out.println("____________________________________________________________");
                        }
                    }
                    else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please specify a task!");
                        System.out.println("____________________________________________________________");
                    }
                    break;

                case "unmark":
                    if (!argument.isEmpty()) {
//                    System.out.println("Task to be unmarked: ");
                        int mark = Integer.parseInt(argument) - 1;
                        if (mark >= 0 && mark < tasks.size()) {
                            tasks.get(mark).unmark();
                            System.out.println("____________________________________________________________");
                            System.out.println("Task unmarked: " + tasks.get(mark));
                            System.out.println("____________________________________________________________");
                        } else {
                            System.out.println("____________________________________________________________");
                            System.out.println("Invalid task!");
                            System.out.println("____________________________________________________________");
                        }
                    }
                    else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please specify a task!");
                        System.out.println("____________________________________________________________");
                    }
                    break;

                case "blah":
                    System.out.println(blah);
                    break;

                case "bye":
                    System.out.println(bye);
                    scanner.close();
                    return;

                default:
                    System.out.println("____________________________________________________________");
                    System.out.println(" No such command: " + command);
                    System.out.println("____________________________________________________________");
                    break;
            }
        }
    }
}

