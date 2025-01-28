import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task userTask) {
        list.add(userTask);
    }

    public Task deleteTask(int index) {
        return list.remove(index);
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public Task markTask(int index) {
        Task task = list.get(index);
        task.markDone();
        return task;
    }

    public Task unmarkTask(int index) {
        Task task = list.get(index);
        task.markUndone();
        return task;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
