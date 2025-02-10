package phi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task, which has a description and start and end dates.
 * An Event task is a type of Task that also tracks the duration of the event,
 * with specific start and end dates.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event task with a description, start date, and end date.
     * The task is initially marked as not completed.
     *
     * @param description A description of the event task.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with a description, completion status, start date, and end date.
     * This constructor allows you to specify whether the task is done or not.
     *
     * @param description A description of the event task.
     * @param isDone A boolean indicating whether the task is completed (true) or not (false).
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, boolean isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, including the description,
     * completion status, and the start and end dates of the event formatted as "MMM d yyyy".
     * The status is indicated with a mark such as "[✓]" for done or "[✘]" for not done.
     *
     * @return A string representing the Event task in the format:
     *         "[E][status] description (from: start_date to: end_date)".
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + String.format(" (from: %s to: %s)",
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
