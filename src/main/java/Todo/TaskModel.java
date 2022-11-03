package Todo;

public class TaskModel {
    /**
     * Beschreibung der Aufgabe.
     */
    private final String description;
    /**
     * Prioritä t der Aufgabe. An dieser Stelle werden keine Annahmen
     * getroffen, was ein grosser respektive ein kleiner Wert bedeutet.
     * Dies kann der Anwender bei der Verwendung selber entscheiden.
     */
    private final int priority;
    /**
     * Status der Aufgabe. Dieser Wert wird auf {@ code true} gesetzt,
     * falls diese Aufgabe erledigt ist.
     */
    private boolean done = false;

    /**
     * Erstellen einer neuen Aufgabe.
     *
     * @ param description Beschreibung der Aufgabe
     * @ param priority Prioritä t der Aufgabe
     */
    public TaskModel(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    /**
     * Wird verwendet, um eine Aufgabe als erledigt zu markieren.
     */
    public void done() {
        done = true;
    }

    public String toString() {
        return ((done) ? "[x] " : "[ ] ")
                + description + " (" + priority + ")";
    }

}