package Sokoban.game.model;

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
 * GameBoard class
 */
public class GameBoard {
    private Block[][] board;
    private int playerY;
    private int playerX;

    private int longestLineInMap = 0;
    /**
     * The Lines.
     */
    List<String> lines = new ArrayList<>();
    private int level = 1;

    private final HashMap<Block, Image> blocksToImages = new HashMap<>();

    /**
     * Instantiates a new Game board.
     */
    public GameBoard() {
    }

    /**
     * Loads level file, creates board accordingly
     *
     * @return block [][]
     * @throws Exception the exception
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
            }
        }
        return board;
    }

    /**
     * Draws map: matches blocks with image
     *
     * @param gc        the gc
     * @param gameBoard the game board
     * @throws Exception the exception
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
            throw new Exception("Die Bilder für die Karte konnten nicht korrekt zugewiesen werden");
        }
    }


    /**
     * Get board block [][].
     *
     * @return the block [][]
     */
    public Block[][] getBoard() {
        return board;
    }

    /**
     * Sets board.
     *
     * @param board the board
     */
    public void setBoard(Block[][] board) {
        this.board = board;
    }

    /**
     * Gets player y.
     *
     * @return the player y
     */
    public int getPlayerY() {
        return playerY;
    }

    /**
     * Sets player y.
     *
     * @param playerY the player y
     */
    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    /**
     * Gets player x.
     *
     * @return the player x
     */
    public int getPlayerX() {
        return playerX;
    }

    /**
     * Sets player x.
     *
     * @param playerX the player x
     */
    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    /**
     * Sets longest line in map.
     *
     * @param longestLineInMap the longest line in map
     */
    public void setLongestLineInMap(int longestLineInMap) {
        this.longestLineInMap = longestLineInMap;
    }

    /**
     * Sets lines.
     *
     * @param lines the lines
     */
    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    /**
     * Gets longest line in map.
     *
     * @return the longest line in map
     */
    public int getLongestLineInMap() {
        return longestLineInMap;
    }

    /**
     * Gets lines.
     *
     * @return the lines
     */
    public List<String> getLines() {
        return lines;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }
}
