package phi;

/**
 * Represents an abstract task with a description and a completion status.
 * Subclasses of this class represent specific types of tasks such as Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with a given description and a default status of not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Constructs a Task with a given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task (true if done, false if not).
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representing the status icon of the task.
     * The status icon is "[X]" if the task is done, and "[ ]" if it is not done.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done by setting the isDone flag to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone by setting the isDone flag to false.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.getDescription().trim());
    }
}
