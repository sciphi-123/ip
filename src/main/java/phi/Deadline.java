package phi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, which has a description and a specific deadline date by which it must be completed.
 * A Deadline task is a type of Task, with an additional field to store the deadline date.
 * It can also track whether the task has been completed or not.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with a description and a deadline date.
     * The task is initially marked as not completed.
     *
     * @param description A description of the deadline task.
     * @param by The date by which the task needs to be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with a description, completion status, and a deadline date.
     * This constructor allows you to specify whether the task is done or not.
     *
     * @param description A description of the deadline task.
     * @param isDone A boolean indicating whether the task is completed (true) or not (false).
     * @param by The date by which the task needs to be completed.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the date by which the task is due.
     *
     * @return The deadline date of the task as a LocalDate object.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the Deadline task, including the description,
     * completion status, and the deadline date formatted as "MMM d yyyy".
     * The status is indicated with a mark such as "[✓]" for done or "[✘]" for not done.
     *
     * @return A string representing the Deadline task in the format:
     *         "[D][status] description (by: deadline_date)".
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
