package Sokoban.game.controller;

import Sokoban.game.enums.Block;
import Sokoban.game.model.GameBoard;

/**
 * The type Controller.
 */
public class Controller {

    public boolean isGameOver = false;

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public void move(GameBoard gameBoard, int x, int y) throws Exception {
        Block[][] map = gameBoard.getBoard();
        try {
            if (map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] == Block.BARRIER) {
                return;
            }
            if (map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] == Block.CHEST || map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] == Block.CHESTONDESTINATION) {
                if (map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] == Block.BARRIER || map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] == Block.CHEST || map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] == Block.CHESTONDESTINATION) {
                    return;
                }
                if (map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] == Block.DESTINATION) {
                    map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] = Block.CHESTONDESTINATION;
                } else {
                    map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] = Block.CHEST;
                    isGameOver(x, y, map, gameBoard);
                }
                if (map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] == Block.CHESTONDESTINATION) {
                    map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2] = Block.CHEST;
                }
            }
            if (map[gameBoard.getPlayerY()][gameBoard.getPlayerX()] == Block.PLAYERONDESTINATION) {
                map[gameBoard.getPlayerY()][gameBoard.getPlayerX()] = Block.DESTINATION;
            } else {
                map[gameBoard.getPlayerY()][gameBoard.getPlayerX()] = Block.FLOOR;
            }
            if (map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] == Block.DESTINATION || map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] == Block.CHESTONDESTINATION) {
                map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] = Block.PLAYERONDESTINATION;
            } else {
                map[gameBoard.getPlayerY() + y][gameBoard.getPlayerX() + x] = Block.PLAYER;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("Ungültige Bewegung im Spielfeld. Breite: " + gameBoard.getLongestLineInMap() + " Höhe : " + gameBoard.getLines().size() + "Eingaben: Breite: " + gameBoard.getPlayerX() + " Höhe: " + gameBoard.getPlayerY());
        } catch (NullPointerException e) {
            throw new Exception("Karte wurde nicht korrekt geladen");
        }
        isEveryChestOnTarget(map);
        gameBoard.setPlayerX(gameBoard.getPlayerX() + x);
        gameBoard.setPlayerY(gameBoard.getPlayerY() + y);
        gameBoard.setBoard(map);
    }

    public void isGameOver(int x, int y, Block[][] map, GameBoard gameBoard) throws Exception {
        try {
            if (map[gameBoard.getPlayerY() + y * 2 - 1][gameBoard.getPlayerX() + x * 2] == Block.BARRIER && map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2 - 1] == Block.BARRIER) {
                setGameOver(true);
            }
            if (map[gameBoard.getPlayerY() + y * 2 - 1][gameBoard.getPlayerX() + x * 2] == Block.BARRIER && map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2 + 1] == Block.BARRIER) {
                setGameOver(true);
            }
            if (map[gameBoard.getPlayerY() + y * 2 + 1][gameBoard.getPlayerX() + x * 2] == Block.BARRIER && map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2 + 1] == Block.BARRIER) {
                setGameOver(true);
            }
            if (map[gameBoard.getPlayerY() + y * 2 + 1][gameBoard.getPlayerX() + x * 2] == Block.BARRIER && map[gameBoard.getPlayerY() + y * 2][gameBoard.getPlayerX() + x * 2 - 1] == Block.BARRIER) {
                setGameOver(true);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("Ungültige Bewegung im Spielfeld. Breite: " + gameBoard.getLongestLineInMap() + " Höhe: " + gameBoard.getLines().size() + "Eingaben: Breite: " + gameBoard.getPlayerX() + " Höhe: " + gameBoard.getPlayerY());
        }
    }

    public boolean isEveryChestOnTarget(Block[][] map) throws Exception {
        int howManyTargetOrPlayerOnTargetAreFound = 0;
        try {
            for (Block[] blocks : map) {
                for (Block block : blocks) {
                    if (block == Block.DESTINATION || block == Block.PLAYERONDESTINATION) {
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
