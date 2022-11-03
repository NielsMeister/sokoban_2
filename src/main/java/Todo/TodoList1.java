package Todo;

public class TodoList1 {
    public static void main(String[] args) {
        TodoList2 todo = new TodoList2(3);
        todo.add(0, new TaskModel("Test üben", 2));
        todo.add(1, new TaskModel("Geschenk für Freundin kaufen", 1));
        todo.add(2, new TaskModel("Zimmer aufräumen", 3));
        todo.done(1);
        todo.printList();
    }
}