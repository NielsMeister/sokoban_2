package Sokoban.game.model;

/**
 * The TestUtils class
 */
public class TestUtils {
    /**
     * Generate a random number
     *
     * @return the generated int
     */
    public static int generateRandomNumber() {
        int min = 1;
        int max = 1000;

        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
