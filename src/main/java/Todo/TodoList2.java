package Todo;

public class TodoList2 {

    TaskModel[] taskModels;

    public TodoList2(int numberOfTasks) {
        taskModels = new TaskModel[numberOfTasks];
    }

    public void add(int index, TaskModel taskModel) {
        taskModels[index] = taskModel;
    }

    public void done(int index) {
        taskModels[index].done();
    }

    public void printList() {
        for (TaskModel taskModel : taskModels) {
            System.out.println(taskModel.toString());
        }
    }
}