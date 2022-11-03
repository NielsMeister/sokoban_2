package Sokoban.game.scenes;

import Sokoban.game.Navigator;
import Sokoban.game.enums.Scenes;
import Sokoban.game.model.GameBoard;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * BaseScene class
 */
public abstract class BaseScene extends Scene {
    /**
     * The Navigator.
     */
    protected Navigator navigator;
    /**
     * The Background image.
     */
    protected Image backgroundImage;
    /**
     * The Canvas.
     */
    protected Canvas canvas;
    /**
     * The Gc.
     */
    protected GraphicsContext gc;
    /**
     * The constant root.
     */
    protected static Group root;
    /**
     * The Game board.
     */
    protected GameBoard gameBoard = new GameBoard();
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 50;

    /**
     * Instantiates a new Base scene, initiates variables
     *
     * @param root      the root
     * @param imageName the image name
     * @param navigator the navigator
     * @throws Exception the exception
     */
    public BaseScene(Group root, String imageName, Navigator navigator) throws Exception {
        super(root);
        try {
            this.backgroundImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/gameImages/" + imageName + ".png")));
        } catch (NullPointerException e) {
            throw new Exception("Bild konnte nicht geladen werden.");
        }
        canvas = new Canvas(backgroundImage.getWidth(), backgroundImage.getHeight());
        gc = this.canvas.getGraphicsContext2D();
        this.navigator = navigator;
        BaseScene.root = root;
        root.getChildren().add(canvas);
    }

    /**
     * Instantiates a new Base scene
     *
     * @param root      the root
     * @param navigator the navigator
     * @throws Exception the exception
     */
    public BaseScene(Group root, Navigator navigator) throws Exception {
        super(root);
        try {
            gameBoard.buildMap();
        } catch (Exception e) {
            navigator.navigateTo(Scenes.WIN_SCENE);
            throw new RuntimeException(e);
        }
        canvas = new Canvas(gameBoard.getLongestLineInMap() * BLOCK_WIDTH, gameBoard.getLines().size() * BLOCK_HEIGHT);
        gc = this.canvas.getGraphicsContext2D();
        this.navigator = navigator;
        root.getChildren().add(canvas);
    }

    /**
     * Draws gc image
     */
    public void draw() {
        gc.drawImage(backgroundImage, 0, 0);
    }
}