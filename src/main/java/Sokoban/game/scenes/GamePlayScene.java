package Sokoban.game.scenes;

import Sokoban.game.Pilot;
import Sokoban.game.controller.Controller;
import Sokoban.game.enums.Scenes;
import Sokoban.game.Board;
import javafx.scene.Group;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * GamePlay Scene, gets triggered after starting.
 * Is responsible for the main process, handles the character movement and the win or loss detection
 */
public class GamePlayScene extends RootScene {
    private final Controller controller = new Controller();
    private final Board board = new Board();

    /**
     * Instantiates a new GamePlayScene
     *
     * @param pilot the navigator
     * @throws Exception needed because of super constructor
     */
    public GamePlayScene(Pilot pilot) throws Exception {
        super(new Group(), pilot);
        game();
    }
    private void game() {
        handleOnKeyPress();
    }

    /**
     * reacts to key pressed.
     * moves character, draws map, checks for loss or win
     *
     * @throws RuntimeException for the case an exception gets thrown during execution
     */
    public void handleOnKeyPress() {
        try {
            board.setBoard(board.buildMap());
        } catch (Exception e) {
            throw new RuntimeException("Level existiert nicht.");
        }
        setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> {
                    moveDown();
                }
                case DOWN -> {
                    moveUp();
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
                handleLossOrWinException();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        setOnKeyReleased(event -> {
            try {
                handleLossOrWinException();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * deals with the win/loose detector
     *
     * @throws RuntimeException for the case of an exception getting thrown during execution
     */
    private void handleLossOrWinException() throws Exception {
        try {
            detectLossOrWin();
        } catch (Exception e) {
            board.setLevel(1);
            try {
                board.buildMap();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            pilot.navigateTo(Scenes.ALLLEVELSDONE_SCENE);
            throw new RuntimeException(e);
        }
    }

    /**
     * triggers move right (x = 1)
     */

    private void moveRight() {
        try {
            controller.move(board, 1, 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * triggers move left (x = -1)
     */

    private void moveLeft() {
        try {
            controller.move(board, -1, 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * triggers move up (y = 1)
     */

    private void moveUp() {
        try {
            controller.move(board, 0, 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * triggers move down (y = -1)
     */

    private void moveDown() {
        try {
            controller.move(board, 0, -1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Draws map
     */
    private void drawMap() {
        try {
            board.drawMap(gc, board.getBoard());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if there's anything (eyesOnPortal, getGameOver) triggering a win or loss.
     * handles the level shifting, navigates to according scene
     *
     * @throws NullPointerException for the case of the set level not existing
     * @throws RuntimeException for the case of an exception getting thrown during execution
     */

    private void detectLossOrWin() throws Exception {
        if (controller.eyesOnPortalCheck(board.getBoard())) {
            try {
                board.setLevel(board.getLevel() + 1);
                board.setBoard(board.buildMap());
                if (board.getLevel() == 1){
                    pilot.navigateTo(Scenes.ALLLEVELSDONE_SCENE);
                } else {
                    pilot.navigateTo(Scenes.WIN_SCENE);
                }
                board.drawMap(gc, board.getBoard());
                controller.setGameOver(false);
            } catch (URISyntaxException | NullPointerException e) {
                throw new NullPointerException("Level existiert nicht");
            }
            game();
        }
        if (controller.getGameOver()) {
            try {
                board.setBoard(board.buildMap());
                controller.setGameOver(false);
            } catch (IOException | NullPointerException e) {
                board.setLevel(1);
                pilot.navigateTo(Scenes.START_SCENE);
                throw new Exception("Level existiert nicht");
            }
            game();
        }
        try {
            board.drawMap(gc, board.getBoard());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
