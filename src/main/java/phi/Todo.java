package phi;

/**
 * Represents a Todo task, which is a type of task that doesn't have a specific date associated with it.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     * The task will be marked as not done by default.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo task with the specified description and done status.
     *
     * @param description The description of the Todo task.
     * @param isDone The done status of the Todo task (true if done, false otherwise).
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the Todo task, including its status and description.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
