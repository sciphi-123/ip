package phi;

import java.time.LocalDate;
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
     * Compares two Task objects based on type and date.
     * The sorting order is as follows:
     * 1. Deadlines come before Events, which come before Todos.
     * 2. Deadlines are sorted by due date ("by").
     *    If dates are equal, they are sorted by description.
     * 3. Events are sorted by start date ("from").
     *    If equal, they are sorted by end date ("to").
     *    If both dates match, they are sorted by description.
     * 4. Todos are sorted alphabetically by description.
     * 5. If tasks have different types, the order is:
     *    Deadline < Event < Todo.
     *
     * @param t1 the first task to compare
     * @param t2 the second task to compare
     * @return a negative value if t1 comes before t2,
     *         a positive value if t1 comes after t2,
     *         or zero if they are equal.
     */
    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof Deadline && t2 instanceof Deadline) {
            LocalDate by1 = ((Deadline) t1).getBy();
            LocalDate by2 = ((Deadline) t2).getBy();

            if (by1.compareTo(by2) != 0) {
                return by1.compareTo(by2);
            } else {
                return t1.getDescription().compareTo(t2.getDescription());
            }
        } else if (t1 instanceof Event && t2 instanceof Event) {
            LocalDate from1 = ((Event) t1).getFrom();
            LocalDate from2 = ((Event) t2).getFrom();

            if (from1.compareTo(from2) != 0) {
                return from1.compareTo(from2);
            } else {
                LocalDate to1 = ((Event) t1).getTo();
                LocalDate to2 = ((Event) t2).getTo();

                if (to1.compareTo(to2) != 0) {
                    return to1.compareTo(to2);
                } else {
                    return t1.getDescription().compareTo(t2.getDescription());
                }
            }
        } else if (t1 instanceof Deadline && !(t2 instanceof Deadline)) {
            return -1;
        } else if (t1 instanceof Event && !(t2 instanceof Deadline || t2 instanceof Event)) {
            return -1;
        } else if (t1 instanceof Todo && t2 instanceof Todo) {
            return t1.getDescription().compareTo(t2.getDescription());
        } else {
            return 0;
        }
    }
}
