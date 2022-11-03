package Sokoban.game.scenes;

import Sokoban.game.Navigator;
import Sokoban.game.controller.Controller;
import Sokoban.game.enums.Scenes;
import Sokoban.game.model.GameBoard;
import javafx.scene.Group;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * GamePlay class
 */
public class GamePlay extends BaseScene {
    private final Controller controller = new Controller();
    private final GameBoard gameBoard = new GameBoard();

    /**
     * Constructor
     *
     * @param navigator the navigator
     * @throws Exception the exception
     */
    public GamePlay(Navigator navigator) throws Exception {
        super(new Group(), navigator);
        game();
    }
    private void game() {
        listenOnKey();
    }

    /**
     * reacts to key pressed.
     * moves character, draws map, checks for loss or win
     */
    public void listenOnKey() {
        try {
            gameBoard.setBoard(gameBoard.buildMap());
        } catch (Exception e) {
            throw new RuntimeException("Level existiert nicht.");
        }
        setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> {
                    moveUp();
                }
                case DOWN -> {
                    moveDown();
                }
                case LEFT -> {
                    moveLeft();
                }
                case RIGHT -> {
                    moveRight();
                }
            }
            drawMap();
            try {
                handleDetectWinOrLooseException();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        setOnKeyReleased(event -> {
            try {
                handleDetectWinOrLooseException();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * deals with the win/loose detector
     *
     * @throws Exception
     */
    private void handleDetectWinOrLooseException() throws Exception {
        try {
            detectWinOrLoose();
        } catch (Exception e) {
            gameBoard.setLevel(1);
            try {
                gameBoard.buildMap();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            navigator.navigateTo(Scenes.ALLLEVELSDONE_SCENE);
            throw new RuntimeException(e);
        }
    }

    /**
     * triggers move right
     */

    private void moveRight() {
        try {
            controller.move(gameBoard, 1, 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * triggers move left
     */

    private void moveLeft() {
        try {
            controller.move(gameBoard, -1, 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * triggers move down
     */

    private void moveDown() {
        try {
            controller.move(gameBoard, 0, 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * triggers move up
     */

    private void moveUp() {
        try {
            controller.move(gameBoard, 0, -1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Draws map
     */
    private void drawMap() {
        try {
            gameBoard.drawMap(gc, gameBoard.getBoard());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if there's anything triggering a win or loss
     *
     * @throws Exception
     */

    private void detectWinOrLoose() throws Exception {
        if (controller.eyesOnPortalCheck(gameBoard.getBoard())) {
            try {
                gameBoard.setLevel(gameBoard.getLevel() + 1);
                gameBoard.setBoard(gameBoard.buildMap());
                if (gameBoard.getLevel() == 1){
                    navigator.navigateTo(Scenes.ALLLEVELSDONE_SCENE);
                } else {
                    navigator.navigateTo(Scenes.WIN_SCENE);
                }
                gameBoard.drawMap(gc, gameBoard.getBoard());
                controller.setGameOver(false);
            } catch (URISyntaxException | NullPointerException e) {
                throw new Exception("Level exisitert nicht");
            }
            game();
        }
        if (controller.gameOverCheck()) {
            try {
                gameBoard.setBoard(gameBoard.buildMap());
                controller.setGameOver(false);
            } catch (IOException | NullPointerException e) {
                gameBoard.setLevel(1);
                navigator.navigateTo(Scenes.START_SCENE);
                throw new Exception("Level exisitert nicht");
            }
            game();
        }
        try {
            gameBoard.drawMap(gc, gameBoard.getBoard());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
