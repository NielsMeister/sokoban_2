package Sokoban.game.model;

import Sokoban.game.Board;
import Sokoban.game.controller.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The ControllerTest class
 */
public class ControllerTest {
    /**
     * Create inner test class
     */
    @DisplayName("Move")
    @Nested
    class move {
        /**
         * NotFoundTest, checking if card could load
         */
        @Test()
        @DisplayName("not_found > Karte wurde nicht korrekt geladen")
        void notFound() {
            Controller controller = new Controller();
            Board gameBoard = new Board();
            Exception exception = Assertions.assertThrows(Exception.class, () ->
                    controller.move(gameBoard, 1, 0), "Karte wurde nicht korrekt geladen");
            assertEquals("Karte wurde nicht korrekt geladen", exception.getMessage());
        }

        /**
         * MoveRightTest, checks if moving right works properly
         *
         * @throws Exception from the moveRight method
         */
        @Test()
        @DisplayName("happy_path")
        void happyPath_moveRight() throws Exception {
            Controller controller = new Controller();
            Board gameBoard = new Board();
            gameBoard.buildMap();
            controller.move(gameBoard, 1, 0);
            String excepted = "[[OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, PORTAL_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK, PORTAL_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, PORTAL_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, EYE_BLOCK, GROUND_BLOCK, EYE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, EYE_BLOCK, EYE_BLOCK, PORTAL_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OPERATOR_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK]]";
            assertEquals(excepted, Arrays.deepToString(gameBoard.getBoard()));
        }

        /**
         * MoveLeftTest, checks if moveLeft works properly
         *
         * @throws Exception from the moveLeft method
         */
        @Test()
        @DisplayName("happy_path")
        void happyPath_moveLeft() throws Exception {
            Controller controller = new Controller();
            Board gameBoard = new Board();
            gameBoard.buildMap();
            controller.move(gameBoard, -1, 0);
            String excepted = "[[OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, PORTAL_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK, PORTAL_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, PORTAL_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, EYE_BLOCK, GROUND_BLOCK, EYE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, EYE_BLOCK, EYE_BLOCK, PORTAL_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OPERATOR_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK]]";
            assertEquals(excepted, Arrays.deepToString(gameBoard.getBoard()));
        }

        /**
         * MoveUpTest, checks if moveUp works properly
         *
         * @throws Exception from the moveUp method
         */
        @Test()
        @DisplayName("happy_path > stay at same position, cause wall is upper")
        void happyPath_moveUp() throws Exception {
            Controller controller = new Controller();
            Board gameBoard = new Board();
            gameBoard.buildMap();
            controller.move(gameBoard, 0, -1);
            String excepted = "[[OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, PORTAL_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK, PORTAL_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, PORTAL_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, EYE_BLOCK, GROUND_BLOCK, EYE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, EYE_BLOCK, EYE_BLOCK, PORTAL_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, OPERATOR_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK]]";
            assertEquals(excepted, Arrays.deepToString(gameBoard.getBoard()));

        }

        /**
         * MoveDownTest, checks if moveDown works properly
         *
         * @throws Exception from the moveDown method
         */
        @Test()
        @DisplayName("happy_path > stay at same position, cause wall is under")
        void happyPath_moveDown() throws Exception {
            Controller controller = new Controller();
            Board gameBoard = new Board();
            gameBoard.buildMap();
            controller.move(gameBoard, 0, 1);
            String excepted = "[[OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, PORTAL_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK, PORTAL_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, PORTAL_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, EYE_BLOCK, GROUND_BLOCK, EYE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, EYE_BLOCK, EYE_BLOCK, PORTAL_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, GROUND_BLOCK, OPERATOR_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, GROUND_BLOCK, OBSTACLE_BLOCK], [OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK, OBSTACLE_BLOCK]]";
            assertEquals(excepted, Arrays.deepToString(gameBoard.getBoard()));
        }

        /**
         * OutOfBoundsTest, checks for movement going (on) out of map
         *
         * @throws Exception from the move method
         */
        @Test()
        @DisplayName("out_of_bounds > Ung??ltige Bewegung im Spielfeld.")
        void outOfBounds() throws Exception {
            Controller controller = new Controller();
            Board gameBoard = new Board();
            gameBoard.buildMap();
            Exception exception = Assertions.assertThrows(Exception.class, () ->
                    controller.move(gameBoard, TestUtils.generateRandomNumber(), TestUtils.generateRandomNumber()), "Ung??ltige Bewegung im Spielfeld. Breite: " + gameBoard.getLongestLineInMap() + " H??he : " + gameBoard.getLines().size() + "Eingaben: Breite: " + gameBoard.getPlayerX() + " H??he: " + gameBoard.getPlayerY());
            assertEquals("Ung??ltige Bewegung im Spielfeld. Breite: " + gameBoard.getLongestLineInMap() + " H??he : " + gameBoard.getLines().size() + "Eingaben: Breite: " + gameBoard.getPlayerX() + " H??he: " + gameBoard.getPlayerY(), exception.getMessage());
        }
    }
}