package Sokoban.game.model;

import Sokoban.game.Board;
import Sokoban.game.controller.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ControllerTest {
    @DisplayName("Move")
    @Nested
    class move {
        @Test()
        @DisplayName("not_found > Karte wurde nicht korrekt geladen")
        void notFound() {
            Controller controller = new Controller();
            Board gameBoard = new Board();
            Exception exception = Assertions.assertThrows(Exception.class, () ->
                    controller.move(gameBoard, 1, 0), "Karte wurde nicht korrekt geladen");
            assertEquals("Karte wurde nicht korrekt geladen", exception.getMessage());
        }
        @Test()
        @DisplayName("happy_path")
        void happyPath_moveRight() throws Exception {
            Controller controller = new Controller();
            Board gameBoard = new Board();
            gameBoard.buildMap();
            controller.move(gameBoard, 1, 0);
            String excepted = "[[BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, FLOOR, DESTINATION, BARRIER, FLOOR, BARRIER, DESTINATION, BARRIER], [BARRIER, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, DESTINATION, FLOOR, FLOOR, BARRIER], [BARRIER, FLOOR, BARRIER, FLOOR, FLOOR, CHEST, FLOOR, CHEST, FLOOR, BARRIER], [BARRIER, FLOOR, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, CHEST, CHEST, DESTINATION, BARRIER], [BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, PLAYER, FLOOR, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER]]";
            assertEquals(excepted, Arrays.deepToString(gameBoard.getBoard()));
        }
        @Test()
        @DisplayName("happy_path")
        void happyPath_moveLeft() throws Exception {
            Controller controller = new Controller();
            Board gameBoard = new Board();
            gameBoard.buildMap();
            controller.move(gameBoard, -1, 0);
            String excepted = "[[BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, FLOOR, DESTINATION, BARRIER, FLOOR, BARRIER, DESTINATION, BARRIER], [BARRIER, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, DESTINATION, FLOOR, FLOOR, BARRIER], [BARRIER, FLOOR, BARRIER, FLOOR, FLOOR, CHEST, FLOOR, CHEST, FLOOR, BARRIER], [BARRIER, FLOOR, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, CHEST, CHEST, DESTINATION, BARRIER], [BARRIER, BARRIER, BARRIER, PLAYER, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER]]";
            assertEquals(excepted, Arrays.deepToString(gameBoard.getBoard()));
        }
        @Test()
        @DisplayName("happy_path > stay at same position, cause wall is upper")
        void happyPath_moveUp() throws Exception {
            Controller controller = new Controller();
            Board gameBoard = new Board();
            gameBoard.buildMap();
            controller.move(gameBoard, 0, -1);
            String excepted = "[[BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, FLOOR, DESTINATION, BARRIER, FLOOR, BARRIER, DESTINATION, BARRIER], [BARRIER, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, DESTINATION, FLOOR, FLOOR, BARRIER], [BARRIER, FLOOR, BARRIER, FLOOR, FLOOR, CHEST, FLOOR, CHEST, FLOOR, BARRIER], [BARRIER, FLOOR, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, CHEST, CHEST, CHESTONDESTINATION, BARRIER], [BARRIER, BARRIER, BARRIER, FLOOR, PLAYER, FLOOR, FLOOR, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER]]";
            assertEquals(excepted, Arrays.deepToString(gameBoard.getBoard()));
        }
        @Test()
        @DisplayName("happy_path > stay at same position, cause wall is under")
        void happyPath_moveDown() throws Exception {
            Controller controller = new Controller();
            Board gameBoard = new Board();
            gameBoard.buildMap();
            controller.move(gameBoard, 0, 1);
            String excepted = "[[BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, FLOOR, DESTINATION, BARRIER, FLOOR, BARRIER, DESTINATION, BARRIER], [BARRIER, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, DESTINATION, FLOOR, FLOOR, BARRIER], [BARRIER, FLOOR, BARRIER, FLOOR, FLOOR, CHEST, FLOOR, CHEST, FLOOR, BARRIER], [BARRIER, FLOOR, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, CHEST, CHEST, DESTINATION, BARRIER], [BARRIER, BARRIER, BARRIER, FLOOR, PLAYER, FLOOR, FLOOR, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER]]";
            assertEquals(excepted, Arrays.deepToString(gameBoard.getBoard()));
        }
        @Test()
        @DisplayName("out_of_bounds > Ungültige Bewegung im Spielfeld.")
        void outOfBounds() throws Exception {
            Controller controller = new Controller();
            Board gameBoard = new Board();
            gameBoard.buildMap();
            Exception exception = Assertions.assertThrows(Exception.class, () ->
                    controller.move(gameBoard, TestUtils.generateRandomNumber(), TestUtils.generateRandomNumber()), "Ungültige Bewegung im Spielfeld. Breite: " + gameBoard.getLongestLineInMap() + " Höhe : " + gameBoard.getLines().size() + "Eingaben: Breite: " + gameBoard.getPlayerX() + " Höhe: " + gameBoard.getPlayerY());
            assertEquals("Ungültige Bewegung im Spielfeld. Breite: " + gameBoard.getLongestLineInMap() + " Höhe : " + gameBoard.getLines().size() + "Eingaben: Breite: " + gameBoard.getPlayerX() + " Höhe: " + gameBoard.getPlayerY(), exception.getMessage());
        }
    }
}