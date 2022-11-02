package Sokoban.game.model;

import Sokoban.game.enums.Block;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Game board.
 */
public class GameBoard {
    private Block[][] board;
    private int playerY;
    private int playerX;

    private int longestLineInMap = 0;
    List<String> lines = new ArrayList<>();
    private int level = 1;

    private final HashMap<Block, Image> blocksToImages = new HashMap<>();

    /**
     * Instantiates a new Game board.
     */
    public GameBoard() {
    }

    /**
     * Build map block [ ] [ ].
     *
     * @return block [ ] [ ]
     * @throws IOException        the io exception
     * @throws URISyntaxException the uri syntax exception
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
                        board[i][j] = Block.BARRIER;
                    }
                    case '+' -> {
                        board[j][i] = Block.PLAYERONDESTINATION;
                    }
                    case '.' -> {
                        board[i][j] = Block.DESTINATION;
                    }
                    case '@' -> {
                        board[i][j] = Block.PLAYER;
                        setPlayerY(i);
                        setPlayerX(j);
                    }
                    case '$' -> {
                        board[i][j] = Block.CHEST;
                    }
                    case ' ' -> {
                        board[i][j] = Block.FLOOR;
                    }
                    default -> {

                    }
                }
            }
        }
        return board;
    }

    public void drawMap(GraphicsContext gc, Block[][] gameBoard) throws Exception {
        Image player = new Image("/gameImages/player.png");
        Image barrier = new Image("/gameImages/barrier.png");
        Image destination = new Image("/gameImages/destination.png");
        Image floor = new Image("/gameImages/floor.png");
        Image chest = new Image("/gameImages/chest.png");
        blocksToImages.put(Block.BARRIER, barrier);
        blocksToImages.put(Block.DESTINATION, destination);
        blocksToImages.put(Block.FLOOR, floor);
        blocksToImages.put(Block.PLAYER, player);
        blocksToImages.put(Block.CHEST, chest);
        blocksToImages.put(Block.PLAYERONDESTINATION, player);
        blocksToImages.put(Block.CHESTONDESTINATION, chest);
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


    public Block[][] getBoard() {
        return board;
    }

    public void setBoard(Block[][] board) {
        this.board = board;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public void setLongestLineInMap(int longestLineInMap) {
        this.longestLineInMap = longestLineInMap;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public int getLongestLineInMap() {
        return longestLineInMap;
    }

    public List<String> getLines() {
        return lines;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
