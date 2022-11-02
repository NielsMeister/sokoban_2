package Todo;

public class Todo {
    public static void main(String[] args) {
        TodoList todo = new TodoList(3);
        todo.add(0, new Task("Test üben", 2));
        todo.add(1, new Task("Geschenk für Freundin kaufen", 1));
        todo.add(2, new Task("Zimmer aufräumen", 3));
        todo.done(1);
        todo.printList();
    }
}