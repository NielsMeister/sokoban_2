package Sokoban.game.controller;

import Sokoban.game.enums.Block;
import Sokoban.game.model.GameBoard;

/**
 * Controller class
 */
public class Controller {

    /**
     * Indicates if game is over
     */
    public boolean isGameOver = false;

    /**
     * GameOver boolean
     *
     * @return the boolean
     */
    public boolean gameOverCheck() {
        return isGameOver;
    }

    /**
     * Sets GameOver
     *
     * @param gameOver the game over
     */
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    /**
     * Moves the character accordingly, reloads board
     *
     * @param gameBoard the game board
     * @param x         the x
     * @param y         the y
     * @throws Exception the exception
     */
    public void move(GameBoard gameBoard, int x, int y) throws Exception {
        Block[][] map = gameBoard.getBoard();
        try {
            if (map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] == Block.OBSTACLE_BLOCK) {
                return;
            }
            if (map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] == Block.EYE_BLOCK || map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] == Block.EYEONDESTINATION_BLOCK) {
                if (map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] == Block.OBSTACLE_BLOCK || map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] == Block.EYE_BLOCK || map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] == Block.EYEONDESTINATION_BLOCK) {
                    return;
                }
                if (map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] == Block.PORTAL_BLOCK) {
                    map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] = Block.EYEONDESTINATION_BLOCK;
                } else {
                    map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] = Block.EYE_BLOCK;
                    gameOverCheck(x, y, map, gameBoard);
                }
                if (map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] == Block.EYEONDESTINATION_BLOCK) {
                    map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] = Block.EYE_BLOCK;
                }
            }
            if (map[gameBoard.getPlayerY()][gameBoard.getPlayerX()] == Block.OPERATORONDESTINATION_BLOCK) {
                map[gameBoard.getPlayerY()][gameBoard.getPlayerX()] = Block.PORTAL_BLOCK;
            } else {
                map[gameBoard.getPlayerY()][gameBoard.getPlayerX()] = Block.GROUND_BLOCK;
            }
            if (map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] == Block.PORTAL_BLOCK || map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] == Block.EYEONDESTINATION_BLOCK) {
                map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] = Block.OPERATORONDESTINATION_BLOCK;
            } else {
                map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] = Block.OPERATOR_BLOCK;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("Ungültige Bewegung im Spielfeld. Breite: " + gameBoard.getLongestLineInMap() + " Höhe : " + gameBoard.getLines().size() + "Eingaben: Breite: " + gameBoard.getPlayerX() + " Höhe: " + gameBoard.getPlayerY());
        } catch (NullPointerException e) {
            throw new Exception("Karte wurde nicht korrekt geladen");
        }
        eyesOnPortalCheck(map);
        gameBoard.setPlayerX(gameBoard.getPlayerX() + x);
        gameBoard.setPlayerY(gameBoard.getPlayerY() + y);
        gameBoard.setBoard(map);
    }

    /**
     * Checks if Game is over
     *
     * @param x         the x
     * @param y         the y
     * @param map       the map
     * @param gameBoard the game board
     * @throws Exception the exception
     */
    public void gameOverCheck(int x, int y, Block[][] map, GameBoard gameBoard) throws Exception {
        try {
            if (map[gameBoard.getPlayerY() + y * 2 - 1][gameBoard.getPlayerX() + x * 2] == Block.OBSTACLE_BLOCK && map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2 - 1] == Block.OBSTACLE_BLOCK) {
                setGameOver(true);
            }
            if (map[gameBoard.getPlayerY() + y * 2 - 1][gameBoard.getPlayerX() + x * 2] == Block.OBSTACLE_BLOCK && map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2 + 1] == Block.OBSTACLE_BLOCK) {
                setGameOver(true);
            }
            if (map[gameBoard.getPlayerY() + y * 2 + 1][gameBoard.getPlayerX() + x * 2] == Block.OBSTACLE_BLOCK && map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2 + 1] == Block.OBSTACLE_BLOCK) {
                setGameOver(true);
            }
            if (map[gameBoard.getPlayerY() + y * 2 + 1][gameBoard.getPlayerX() + x * 2] == Block.OBSTACLE_BLOCK && map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2 - 1] == Block.OBSTACLE_BLOCK) {
                setGameOver(true);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("Ungültige Bewegung im Spielfeld. Breite: " + gameBoard.getLongestLineInMap() + " Höhe: " + gameBoard.getLines().size() + "Eingaben: Breite: " + gameBoard.getPlayerX() + " Höhe: " + gameBoard.getPlayerY());
        }
    }

    /**
     * Checks if eyes are on target space
     *
     * @param map the map
     * @return the boolean
     * @throws Exception the exception
     */
    public boolean eyesOnPortalCheck(Block[][] map) throws Exception {
        int howManyTargetOrPlayerOnTargetAreFound = 0;
        try {
            for (Block[] blocks : map) {
                for (Block block : blocks) {
                    if (block == Block.PORTAL_BLOCK || block == Block.OPERATORONDESTINATION_BLOCK) {
                        howManyTargetOrPlayerOnTargetAreFound += 1;
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new Exception("Karte wurde nicht korrekt geladen");
        }
        return howManyTargetOrPlayerOnTargetAreFound == 0;
    }
}
