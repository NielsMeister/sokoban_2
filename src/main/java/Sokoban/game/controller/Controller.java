package Sokoban.game.controller;

import Sokoban.game.Board;
import Sokoban.game.enums.Block;

/**
 * Controller class, contains logic for movement and gameEnding
 */
public class Controller {

    /**
     * Indicates if game is over
     */
    protected boolean isGameOver = false;

    /**
     * Gets gameOver boolean
     *
     * @return the gameOver boolean
     */
    public boolean getGameOver() {
        return isGameOver;
    }

    /**
     * Sets gameOver boolean
     *
     * @param gameOver the value to set gameOver to
     */
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    /**
     * Moves the character accordingly (prevents illegal movement), reloads board, checks for gameEnding
     *
     * @param board the game board
     * @param x     the value of how far horizontally to move, getting value from key-input: left = -1, right = 1
     * @param y     the value of how far vertically to move, getting value from key-input: down = -1, up = 1
     * @throws ArrayIndexOutOfBoundsException for the case of a movement going (on) out of map
     * @throws NullPointerException           for the case of the map not getting loaded (correctly)
     * @throws Exception basic Exception
     */
    public void move(Board board, int x, int y) throws Exception {
        Block[][] map = board.getBoard();
        try {
            if (map[board.getPlayerY() + y][board.getPlayerX() + x] == Block.OBSTACLE_BLOCK) {
                return;
            }
            if (map[board.getPlayerY() + y][board.getPlayerX() + x] == Block.EYE_BLOCK || map[board.getPlayerY() + y][board.getPlayerX() + x] == Block.EYEONDESTINATION_BLOCK) {
                if (map[board.getPlayerY() + y * 2][board.getPlayerX() + x * 2] == Block.OBSTACLE_BLOCK || map[board.getPlayerY() + y * 2][board.getPlayerX() + x * 2] == Block.EYE_BLOCK || map[board.getPlayerY() + y * 2][board.getPlayerX() + x * 2] == Block.EYEONDESTINATION_BLOCK) {
                    return;
                }
                if (map[board.getPlayerY() + y * 2][board.getPlayerX() + x * 2] == Block.PORTAL_BLOCK) {
                    map[board.getPlayerY() + y * 2][board.getPlayerX() + x * 2] = Block.EYEONDESTINATION_BLOCK;
                } else {
                    map[board.getPlayerY() + y * 2][board.getPlayerX() + x * 2] = Block.EYE_BLOCK;
                    getGameOver(x, y, map, board);
                }
                if (map[board.getPlayerY() + y][board.getPlayerX() + x] == Block.EYEONDESTINATION_BLOCK) {
                    map[board.getPlayerY() + y * 2][board.getPlayerX() + x * 2] = Block.EYE_BLOCK;
                }
            }
            if (map[board.getPlayerY()][board.getPlayerX()] == Block.OPERATORONDESTINATION_BLOCK) {
                map[board.getPlayerY()][board.getPlayerX()] = Block.PORTAL_BLOCK;
            } else {
                map[board.getPlayerY()][board.getPlayerX()] = Block.GROUND_BLOCK;
            }
            if (map[board.getPlayerY() + y][board.getPlayerX() + x] == Block.PORTAL_BLOCK || map[board.getPlayerY() + y][board.getPlayerX() + x] == Block.EYEONDESTINATION_BLOCK) {
                map[board.getPlayerY() + y][board.getPlayerX() + x] = Block.OPERATORONDESTINATION_BLOCK;
            } else {
                map[board.getPlayerY() + y][board.getPlayerX() + x] = Block.OPERATOR_BLOCK;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Ungültige Bewegung im Spielfeld. Breite: " + board.getLongestLineInMap() + " Höhe : " + board.getLines().size() + "Eingaben: Breite: " + board.getPlayerX() + " Höhe: " + board.getPlayerY());
        } catch (NullPointerException e) {
            throw new NullPointerException("Karte wurde nicht korrekt geladen");
        }

        eyesOnPortalCheck(map);
        board.setPlayerX(board.getPlayerX() + x);
        board.setPlayerY(board.getPlayerY() + y);
        board.setBoard(map);
    }

    /**
     * Checks if Eye is stuck (surrounded by more than one obstacle) and if so, triggers gameOver
     *
     * @param x     value for adjusting player position horizontally
     * @param y     value for adjusting player position vertically
     * @param map   the built map
     * @param board the game board
     * @throws ArrayIndexOutOfBoundsException for the case of movement going (on) out of map
     * @throws Exception basic Exception
     */
    public void getGameOver(int x, int y, Block[][] map, Board board) throws Exception {
        try {
            if (map[board.getPlayerY() + y * 2 - 1][board.getPlayerX() + x * 2] == Block.OBSTACLE_BLOCK && map[board.getPlayerY() + y * 2][board.getPlayerX() + x * 2 - 1] == Block.OBSTACLE_BLOCK) {
                setGameOver(true);
            }
            if (map[board.getPlayerY() + y * 2 - 1][board.getPlayerX() + x * 2] == Block.OBSTACLE_BLOCK && map[board.getPlayerY() + y * 2][board.getPlayerX() + x * 2 + 1] == Block.OBSTACLE_BLOCK) {
                setGameOver(true);
            }
            if (map[board.getPlayerY() + y * 2 + 1][board.getPlayerX() + x * 2] == Block.OBSTACLE_BLOCK && map[board.getPlayerY() + y * 2][board.getPlayerX() + x * 2 + 1] == Block.OBSTACLE_BLOCK) {
                setGameOver(true);
            }
            if (map[board.getPlayerY() + y * 2 + 1][board.getPlayerX() + x * 2] == Block.OBSTACLE_BLOCK && map[board.getPlayerY() + y * 2][board.getPlayerX() + x * 2 - 1] == Block.OBSTACLE_BLOCK) {
                setGameOver(true);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Ungültige Bewegung im Spielfeld. Breite: " + board.getLongestLineInMap() + " Höhe: " + board.getLines().size() + "Eingaben: Breite: " + board.getPlayerX() + " Höhe: " + board.getPlayerY());
        }
    }

    /**
     * Checks if objects (eyes) are on target space (portal)
     *
     * @param map the 2d array as the map
     * @return boolean which indicates if all eyes are on targeted position
     * @throws NullPointerException for the case map doesn't load (correctly)
     * @throws Exception basic Exception
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
            throw new NullPointerException("Karte wurde nicht korrekt geladen");
        }
        return howManyTargetOrPlayerOnTargetAreFound == 0;
    }
}
