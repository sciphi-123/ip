package phi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a deadline date.
 * A Deadline task has a description, a status indicating whether it's done, and a date by which it needs to be completed.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with a description and a deadline date.
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
     *
     * @param description A description of the deadline task.
     * @param isDone A boolean indicating whether the task is completed.
     * @param by The date by which the task needs to be completed.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the date by which the task is due.
     *
     * @return The deadline date of the task.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the Deadline task, including the description,
     * completion status, and the deadline date formatted as "MMM d yyyy".
     *
     * @return A string representation of the Deadline task in the format:
     *         "[D][status] description (by: deadline_date)".
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
