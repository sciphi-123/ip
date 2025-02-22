package phi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a list of tasks. Provides methods to manage tasks such as adding, deleting,
 * marking as done, and retrieving tasks.
 */
public class TaskList {
    private final List<Task> list;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructs a task list with an existing list of tasks.
     *
     * @param list The list of tasks to initialize the task list with.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a new task to the list.
     *
     * @param userTask The task to add to the list.
     */
    public void addTask(Task userTask) {
        list.add(userTask);
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task to delete.
     * @return The task that was removed from the list.
     */
    public Task deleteTask(int index) {
        return list.remove(index);
    }

    /**
     * Retrieves the task at the specified index in the list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     * @return The task that was marked as done.
     */
    public Task markTask(int index) {
        Task task = list.get(index);
        task.markDone();
        return task;
    }

    /**
     * Marks the task at the specified index as undone.
     *
     * @param index The index of the task to mark as undone.
     * @return The task that was marked as undone.
     */
    public Task unmarkTask(int index) {
        Task task = list.get(index);
        task.markUndone();
        return task;
    }

    /**
     * Searches for tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A list of tasks whose descriptions contain the specified keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> result = new ArrayList<>();
        for (Task t : list) {
            if (t.getDescription().contains(keyword)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Sorts the list of tasks using TaskComparator.
     * Deadlines are sorted by their due date, and events are sorted by their start date.
     * The sorting order ensures that deadlines come before other task types.
     */
    public void sortTasks() {
        Collections.sort(list, new TaskComparator());
    }
}
