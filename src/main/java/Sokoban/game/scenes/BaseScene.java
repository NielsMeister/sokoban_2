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

public abstract class BaseScene extends Scene {
    protected Navigator navigator;
    protected Image backgroundImage;
    protected Canvas canvas;
    protected GraphicsContext gc;
    protected static Group root;
    protected GameBoard gameBoard = new GameBoard();
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 50;

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

    public BaseScene(Group root, Navigator navigator) throws Exception {
        super(root);
        try {
            gameBoard.buildMap();
        } catch (Exception e) {
            navigator.navigateTo(Scenes.GAMEWIN);
            throw new RuntimeException(e);
        }
        canvas = new Canvas(gameBoard.getLongestLineInMap() * BLOCK_WIDTH, gameBoard.getLines().size() * BLOCK_HEIGHT);
        gc = this.canvas.getGraphicsContext2D();
        this.navigator = navigator;
        root.getChildren().add(canvas);
    }

    public void draw() {
        gc.drawImage(backgroundImage, 0, 0);
    }
}