package phi;

import java.time.LocalDate;

/**
 * A utility class that provides methods for parsing various task types
 * from user input strings. It includes methods to parse Todo, Deadline,
 * and Event tasks, as well as to parse task indices for marking, unmarking,
 * or deleting tasks.
 */
public class Parser {

    /**
     * Parses a Todo task from a string input.
     * The input should contain a description for the Todo task.
     *
     * @param line The user input string containing the task description.
     * @return A Todo task with the parsed description.
     * @throws PhiException If the description is missing.
     */
    public static Task parseTodo(String line) throws PhiException {
        String[] userArr = line.split(" ");
        if (userArr.length < 2) {
            throw new PhiException("No description given. Please give one!");
        } else {
            String description = line.substring(5);
            return new Todo(description);
        }
    }

    /**
     * Parses a Deadline task from a string input.
     * The input should contain a description and a deadline date in the format "/by [date]".
     *
     * @param line The user input string containing the task description and deadline.
     * @return A Deadline task with the parsed description and deadline.
     * @throws PhiException If the description or deadline is missing or the format is incorrect.
     */
    public static Task parseDeadline(String line) throws PhiException {
        String[] userArr = line.split(" ");
        if (userArr.length < 2) {
            throw new PhiException("No description given. Please give one!");
        } else {
            // remove task type; "deadline " contains 9 characters
            String[] clean = line.substring(9).split(" /by");
            if (clean.length != 2) {
                throw new PhiException("Expected format: deadline [description] /by [deadline]");
            } else {
                return new Deadline(clean[0], LocalDate.parse(clean[1].trim()));
            }
        }
    }

    /**
     * Parses an Event task from a string input.
     * The input should contain a description, start date, and end date in the format "/from [date] /to [date]".
     *
     * @param line The user input string containing the task description, start date, and end date.
     * @return An Event task with the parsed description, start date, and end date.
     * @throws PhiException If the description, start date, or end date is missing or the format is incorrect.
     */
    public static Task parseEvent(String line) throws PhiException {
        String[] userArr = line.split(" ");
        if (userArr.length < 2) {
            throw new PhiException("No description given. Please give one!");
        } else {
            // remove task type; "event " contains 6 characters
            String[] clean1 = line.substring(6).split(" /from");
            if (clean1.length != 2) {
                throw new PhiException("Expected format: event [description] /from [from time] /to [to time]");
            } else {
                String[] clean2 = clean1[1].split(" /to");
                if (clean2.length != 2) {
                    throw new PhiException("Expected format: event [description] /from [from time] /to [to time]");
                } else {
                    return new Event(clean1[0], LocalDate.parse(clean2[0].trim()), LocalDate.parse(clean2[1].trim()));
                }
            }
        }
    }

    /**
     * Parses an index from a string input.
     * The input should contain a task index for marking, unmarking, or deleting a task.
     *
     * @param line The user input string containing the task index.
     * @param maxIndex The maximum valid task index.
     * @return The parsed index, adjusted to zero-based indexing.
     * @throws PhiException If the index is invalid or out of range.
     */
    public static int parseIndex(String line, int maxIndex) throws PhiException {
        int index;
        try {
            String[] tokens = line.split(" ");
            index = Integer.parseInt(tokens[1]);
        } catch (Exception e) {
            throw new PhiException("Expected format: mark/unmark/delete [number]");
        }
        if (index > maxIndex) {
            throw new PhiException("Range exceeded!");
        }
        return index - 1;
    }

    /**
     * Parses the user input for the "find" command and extracts the keyword.
     *
     * @param line The full command input from the user.
     * @return The keyword to search for in the task list.
     * @throws PhiException If no keyword is provided in the command.
     */
    public static String parseFind(String line) throws PhiException {
        String[] userArr = line.split(" ");
        if (userArr.length < 2) {
            throw new PhiException("No keyword given. Please give one!");
        } else {
            // remove task type; "find " contains 5 characters
            return line.substring(5);
        }
    }
}
