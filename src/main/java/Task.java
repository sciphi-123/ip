public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markDone() {
        System.out.println("Nice! I've marked this task as done:");
        this.isDone = true;
    }

    public void markUndone() {
        System.out.println("Ok, I've marked this task as not done yet:");
        this.isDone = false;
    }
}

