import java.util.Scanner;

public class Solyu {
    public static void main(String[] args) {
        String greet = "____________________________________________________________\n"
                + " Hello! I'm Solyu \n"
                + " What can I do for you? \n"
                + "____________________________________________________________\n";

        String list = "____________________________________________________________\n"
                + " list\n"
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

            switch (userInput.toLowerCase()) {
                case "greet":
                    System.out.println(greet);
                    break;
                case "list":
                    System.out.println(list);
                    break;
                case "blah":
                    System.out.println(blah);
                    break;
                case "bye":
                    System.out.println(bye);
                    scanner.close(); // Close the scanner
                    return; // Exit the program
                default:
                    System.out.println("____________________________________________________________");
                    System.out.println(" Wrong command.");
                    System.out.println("____________________________________________________________");
                    break;
            }
        }
    }
}
