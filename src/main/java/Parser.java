import java.time.LocalDate;

public class Parser {
    public static Task parseTodo(String line) throws PhiException {
        String[] userArr = line.split(" ");
        if (userArr.length < 2) {
            throw new PhiException("No description given. Please give one!");
        } else {
            String description = line.substring(5);
            return new Todo(description);
        }
    }

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
}
