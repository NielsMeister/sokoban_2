package Sokoban.game.model;

import Sokoban.game.controller.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    @DisplayName("Move")
    @Nested
    class move {
        @Test()
        @DisplayName("not_found > Karte wurde nicht korrekt geladen")
        void notFound() {
            Controller controller = new Controller();
            GameBoard gameBoard = new GameBoard();
            Exception exception = Assertions.assertThrows(Exception.class, () ->
                    controller.move(gameBoard, 1, 0), "Karte wurde nicht korrekt geladen");

            assertEquals("Karte wurde nicht korrekt geladen", exception.getMessage());
        }

        @Test()
        @DisplayName("happy_path")
        void happyPath() throws Exception {
            Controller controller = new Controller();
            GameBoard gameBoard = new GameBoard();
            gameBoard.buildMap();
            controller.move(gameBoard, 1, 0);
            String excepted = "[[BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, FLOOR, DESTINATION, BARRIER, FLOOR, BARRIER, DESTINATION, BARRIER], [BARRIER, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, DESTINATION, FLOOR, FLOOR, BARRIER], [BARRIER, FLOOR, BARRIER, FLOOR, FLOOR, CHEST, FLOOR, CHEST, FLOOR, BARRIER], [BARRIER, FLOOR, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, CHEST, CHEST, DESTINATION, BARRIER], [BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, PLAYER, FLOOR, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER]]";
            assertEquals(excepted, Arrays.deepToString(gameBoard.getBoard()));
        }

        @Test()
        @DisplayName("out_of_bounds > Ungültige Bewegung im Spielfeld.")
        void outOfBounds() throws Exception{
            Controller controller = new Controller();
            GameBoard gameBoard = new GameBoard();
            gameBoard.buildMap();
            Exception exception = Assertions.assertThrows(Exception.class, () ->
                    controller.move(gameBoard, TestUtils.generateRandomNumber(), TestUtils.generateRandomNumber()), "Ungültige Bewegung im Spielfeld. Breite: " + gameBoard.getLongestLineInMap() + " Höhe : " + gameBoard.getLines().size() + "Eingaben: Breite: " + gameBoard.getPlayerX() + " Höhe: " + gameBoard.getPlayerY());

            assertEquals("Ungültige Bewegung im Spielfeld. Breite: " + gameBoard.getLongestLineInMap() + " Höhe : " + gameBoard.getLines().size() + "Eingaben: Breite: " + gameBoard.getPlayerX() + " Höhe: " + gameBoard.getPlayerY(), exception.getMessage());
        }
    }
}
