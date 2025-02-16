package solyu;

/**
 * Handles user input.
 */
public class Parser {
    /**
     * Parses the user's full command into [command, argument].
     *
     * @param fullCommand Full user input.
     * @return A string array of size 2: [command, argument].
     */
    public String[] parse(String fullCommand) {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            return new String[] {"", ""}; // Return empty command and argument safely
        }

        // Normalize input: Remove multiple spaces and trim
        fullCommand = fullCommand.trim().replaceAll("\\s+", " ");

        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = (parts.length > 1) ? parts[1] : "";

        if (containsInvalidCharacters(argument)) {
            return new String[] {"invalid", "Command contains invalid characters!"};
        }

        return new String[]{command, argument};
    }

    /**
     * Checks if a command argument contains unexpected special characters.
     *
     * @param input The command argument to check.
     * @return true if invalid characters exist, false otherwise.
     */
    private boolean containsInvalidCharacters(String input) {
        return input.matches(".*[~`!@#$%^&*+=<>?/{}|].*"); // Exclude normal punctuation
    }
}
