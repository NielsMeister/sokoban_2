package Sokoban.game;

import Sokoban.game.enums.Block;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * GameBoard class, builds and provides the gameMap
 */
public class Board {
    /**
     * The 2d array for the map
     */
    private Block[][] board;
    private int playerY;
    private int playerX;

    /**
     * Longest row for indicating the width (length) of the map
     */
    private int longestLineInMap = 0;
    /**
     * The horizontal arrays, the rows of the 2d array
     */
    private List<String> lines = new ArrayList<>();
    private int level = 1;

    /**
     * HashMap to match images to according blocks
     */
    private final HashMap<Block, Image> blocksToImages = new HashMap<>();

    /**
     * Instantiates a new Board
     */
    public Board() {
    }

    /**
     * Loads .txt level file, creates board accordingly
     *
     * @return block [][] the built map
     * @throws ArrayIndexOutOfBoundsException incase of something going out of map
     * @throws Exception basic Exception
     */
    public Block[][] buildMap() throws Exception {
        String fileName = "level" + level + ".txt";
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(fileName);
            assert resource != null;
            resource.toURI();
            File file = new File(resource.toURI());
            lines = Files.readAllLines(file.toPath());
        } catch (NullPointerException | FileNotFoundException | ArithmeticException | IllegalArgumentException | AssertionError e) {
            setLevel(1);
        }
        for (int l = 0; l < lines.size(); l++) {
            if (longestLineInMap < lines.get(l).length() || longestLineInMap > lines.get(l).length()) {
                setLongestLineInMap(lines.get(l).length());
                setLines(lines);
            }
        }
        board = new Block[lines.size()][longestLineInMap];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < longestLineInMap; j++) {
                try {
                    switch (lines.get(i).charAt(j)) {
                        case '#' -> {
                            board[i][j] = Block.OBSTACLE_BLOCK;
                        }
                        case '+' -> {
                            board[j][i] = Block.OPERATORONDESTINATION_BLOCK;
                        }
                        case '.' -> {
                            board[i][j] = Block.PORTAL_BLOCK;
                        }
                        case '@' -> {
                            board[i][j] = Block.OPERATOR_BLOCK;
                            setPlayerY(i);
                            setPlayerX(j);
                        }
                        case '$' -> {
                            board[i][j] = Block.EYE_BLOCK;
                        }
                        case ' ' -> {
                            board[i][j] = Block.GROUND_BLOCK;
                        }
                        default -> {

                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Invalide Position für Zuweisung");
                }

            }
        }
        return board;
    }

    /**
     * Draws map: matches blocks with image
     *
     * @param gc        the graphicsContext
     * @param gameBoard the game board / map
     * @throws ArrayIndexOutOfBoundsException for the case of the images not being able to match to the blocks (correctly)
     * @throws Exception basic Exception
     */
    public void drawMap(GraphicsContext gc, Block[][] gameBoard) throws Exception {
        Image player = new Image("/gameImages/player.png");
        Image barrier = new Image("/gameImages/barrier.png");
        Image destination = new Image("/gameImages/destination.png");
        Image floor = new Image("/gameImages/floor.png");
        Image chest = new Image("/gameImages/chest.png");
        blocksToImages.put(Block.OBSTACLE_BLOCK, barrier);
        blocksToImages.put(Block.PORTAL_BLOCK, destination);
        blocksToImages.put(Block.GROUND_BLOCK, floor);
        blocksToImages.put(Block.OPERATOR_BLOCK, player);
        blocksToImages.put(Block.EYE_BLOCK, chest);
        blocksToImages.put(Block.OPERATORONDESTINATION_BLOCK, player);
        blocksToImages.put(Block.EYEONDESTINATION_BLOCK, chest);
        try {
            for (int i = 0; i < getLongestLineInMap(); i++) {
                for (int j = 0; j < getLines().size(); j++) {
                    gc.drawImage(blocksToImages.get(gameBoard[j][i]), i * 50, j * 50);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Die Bilder für die Karte konnten nicht korrekt zugewiesen werden");
        }
    }


    /**
     * Get map
     *
     * @return the 2d array, block[][]
     */
    public Block[][] getBoard() {
        return board;
    }

    /**
     * Sets board
     *
     * @param board value for the board
     */
    public void setBoard(Block[][] board) {
        this.board = board;
    }

    /**
     * Gets players value y
     *
     * @return the players y
     */
    public int getPlayerY() {
        return playerY;
    }

    /**
     * Sets players y value
     *
     * @param playerY value for the players y
     */
    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    /**
     * Gets players x value
     *
     * @return the value of the players x
     */
    public int getPlayerX() {
        return playerX;
    }

    /**
     * Sets players x value
     *
     * @param playerX the value for the players x
     */
    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    /**
     * Sets longest line in map, the widest point on the board
     *
     * @param longestLineInMap the longest horizontal row in map
     */
    public void setLongestLineInMap(int longestLineInMap) {
        this.longestLineInMap = longestLineInMap;
    }

    /**
     * Sets lines
     *
     * @param lines value for the lines
     */
    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    /**
     * Gets longest line in map, widest row on board
     *
     * @return the longest line in map
     */
    public int getLongestLineInMap() {
        return longestLineInMap;
    }

    /**
     * Gets lines
     *
     * @return StringList of lines
     */
    public List<String> getLines() {
        return lines;
    }

    /**
     * Gets current level
     *
     * @return current level value
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level
     *
     * @param level value for the 'new' level
     */
    public void setLevel(int level) {
        this.level = level;
    }
}
