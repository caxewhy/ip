package solyu;

/**
 * Deals with interpreting user input.
 */
public class Parser {
    /**
     * Parses the user's full command into [command, argument].
     *
     * @param fullCommand Full user input.
     * @return A string array of size 2: [command, argument].
     */
    public String[] parse(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = (parts.length > 1) ? parts[1] : "";
        return new String[] {command, argument};
    }
}
