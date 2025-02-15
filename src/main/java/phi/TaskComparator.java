package phi;

import java.util.Comparator;

/**
 * A comparator for comparing Task objects.
 * Tasks are compared based on their type and relevant date attributes.
 * - Deadline tasks are compared by their "by" date.
 * - Event tasks are compared by their "from" date.
 * - Deadline tasks take precedence over other task types.
 * - Event tasks take precedence over other non-deadline tasks.
 */
public class TaskComparator implements Comparator<Task> {

    /**
     * Compares two Task objects based on their type and relevant date attributes.
     *
     * @param t1 the first task to compare
     * @param t2 the second task to compare
     * @return a negative integer if t1 should come before t2,
     *         a positive integer if t1 should come after t2,
     *         or zero if they are considered equal
     */
    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof Deadline && t2 instanceof Deadline) {
            return ((Deadline) t1).getBy().compareTo(((Deadline) t2).getBy());
        } else if (t1 instanceof Event && t2 instanceof Event) {
            return ((Event) t1).getFrom().compareTo(((Event) t2).getFrom());
        } else if (t1 instanceof Deadline && !(t2 instanceof Deadline)) {
            return -1;
        } else if (t1 instanceof Event && !(t2 instanceof Deadline || t2 instanceof Event)) {
            return -1;
        }
        return 0;
    }
}