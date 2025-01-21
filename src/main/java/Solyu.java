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

