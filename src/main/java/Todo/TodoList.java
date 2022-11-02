package Todo;

public class TodoList {

    Task[] tasks;

    public TodoList(int numberOfTasks) {
        tasks = new Task[numberOfTasks];
    }

    public void add(int index, Task task) {
        tasks[index] = task;
    }

    public void done(int index) {
        tasks[index].done();
    }

    public void printList() {
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }
}