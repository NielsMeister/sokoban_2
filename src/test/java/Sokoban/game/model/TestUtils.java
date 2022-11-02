package Sokoban.game.model;

public class TestUtils {
    public static int generateRandomNumber() {
        int min = 1;
        int max = 1000;

        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
}
