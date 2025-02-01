package phi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description and start and end dates.
 * An Event task has a description, a status indicating whether it's done,
 * and start and end dates to indicate the event's duration.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event task with a description, start date, and end date.
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
     *
     * @param description A description of the event task.
     * @param isDone A boolean indicating whether the task is completed.
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
     *
     * @return A string representation of the Event task in the format:
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