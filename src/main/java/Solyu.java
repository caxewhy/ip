import java.util.Scanner;
import java.util.ArrayList;

public class Solyu {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<String>();

        String greet = "____________________________________________________________\n"
                + " Hello! I'm Solyu \n"
                + " What can I do for you? \n"
                + "____________________________________________________________\n";

//        String list = "____________________________________________________________\n"
//                + " list\n"
//                + "____________________________________________________________\n";

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
            String userInput = scanner.nextLine().trim().toLowerCase();

            switch (userInput) {
                case "greet":
                    System.out.println(greet);
                    break;

                case "add":
                    System.out.println("Task to be added: ");
                    String task = scanner.nextLine();
                    tasks.add(task);
                    System.out.println("____________________________________________________________");
                    System.out.println("Task added to list: " + task);
                    System.out.println("____________________________________________________________");
                    break;

                case "list":
                    if (tasks.isEmpty()) {
                        System.out.println("____________________________________________________________");
                        System.out.println("List is empty");
                    }
                    else {
                        System.out.println("____________________________________________________________");
                        for (int i = 0; i<tasks.size(); i++){
                            System.out.println((i+1) + ". " + tasks.get(i));
                        }
                    }
                    System.out.println("____________________________________________________________");
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
                    System.out.println(" Wrong command.");
                    System.out.println("____________________________________________________________");
                    break;
            }
        }
    }
}
