package Sokoban.game.scenes;

import Sokoban.game.Pilot;
import Sokoban.game.enums.Scenes;
import Sokoban.game.Board;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * BaseScene class, parents for all Scenes, injects every necessary element
 */
public abstract class RootScene extends Scene {
    /**
     * The Navigator
     */
    protected Pilot pilot;
    /**
     * The Background image
     */
    protected Image backgroundImage;
    /**
     * The Canvas, gets drawn onto
     */
    protected Canvas canvas;
    /**
     * The Gc, provides the drawing (-context)
     */
    protected GraphicsContext gc;
    /**
     * The constant root group
     */
    protected static Group root;
    /**
     * The Game board
     */
    protected Board board = new Board();
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 50;

    /**
     * Instantiates a new RootScene, initiates variables
     * sets up the canvas and graphicsContext for creating the drawing of map, scenes etc.
     *
     * @param root      the root group
     * @param imageName the image name
     * @param pilot     the navigator
     * @throws NullPointerException for the case of the image not being able to load (correctly)
     */
    public RootScene(Group root, String imageName, Pilot pilot) throws Exception {
        super(root);
        try {
            this.backgroundImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/gameImages/" + imageName + ".png")));
        } catch (NullPointerException e) {
            throw new NullPointerException("Bild konnte nicht geladen werden.");
        }
        canvas = new Canvas(backgroundImage.getWidth(), backgroundImage.getHeight());
        gc = this.canvas.getGraphicsContext2D();
        this.pilot = pilot;
        RootScene.root = root;
        root.getChildren().add(canvas);
    }

    /**
     * Instantiates a new RootScene, initiates variables
     * sets up the canvas and graphicsContext for creating the drawing of map, scenes etc.
     *
     * @param root      the root
     * @param pilot the navigator
     * @throws RuntimeException in case the map can't get built (correctly)
     */
    public RootScene(Group root, Pilot pilot) throws Exception {
        super(root);
        try {
            board.buildMap();
        } catch (Exception e) {
            pilot.navigateTo(Scenes.WIN_SCENE);
            throw new RuntimeException(e);
        }
        canvas = new Canvas(board.getLongestLineInMap() * BLOCK_WIDTH, board.getLines().size() * BLOCK_HEIGHT);
        gc = this.canvas.getGraphicsContext2D();
        this.pilot = pilot;
        root.getChildren().add(canvas);
    }

    /**
     * Draws backgroundImage on graphicsContext
     *
     * @params x, y for the drawImage function indicate the position of the image
     */
    public void draw() {
        gc.drawImage(backgroundImage, 0, 0);
    }
}