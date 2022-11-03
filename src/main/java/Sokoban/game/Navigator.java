package Sokoban.game;

import Sokoban.game.enums.Scenes;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Navigator class
 */
public class Navigator {
    private final Stage stage;
    private final Map<Scenes, Scene> sceneMap = new HashMap<>();

    /**
     * Instantiates a new Navigator.
     *
     * @param stage the stage
     */
    public Navigator(Stage stage) {
        this.stage = stage;
    }

    /**
     * Registers scenes
     *
     * @param scenes the scenes
     * @param scene  the scene
     */
    public void registerScene(Scenes scenes, Scene scene) {
        sceneMap.put(scenes, scene);
    }

    /**
     * Navigates between scenes
     *
     * @param scenes the scenes
     * @throws Exception the exception
     */
    public void navigateTo(Scenes scenes) throws Exception {
        try {
            Scene scene = sceneMap.get(scenes);
            stage.setScene(scene);
        } catch (NullPointerException e) {
            throw new Exception("Invalide szene");
        }
    }
}
