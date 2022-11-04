package Sokoban.game;

import Sokoban.game.enums.Scenes;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Pilot class, navigates through the scenes
 */
public class Pilot {
    /**
     * Stage for gc and canvas to be 'drawn' on
     */
    private final Stage stage;
    /**
     * Initiating a map of the scene enums and the actual classes to match with eachother
     */
    private final Map<Scenes, Scene> sceneMap = new HashMap<>();

    /**
     * Instantiates a new Pilot
     *
     * @param stage the stage value to set
     */
    public Pilot(Stage stage) {
        this.stage = stage;
    }

    /**
     * Registers scenes into the sceneMap
     *
     * @param scenes the scenes to register (enum)
     * @param scene  the scene to register (class)
     */
    public void registerScene(Scenes scenes, Scene scene) {
        sceneMap.put(scenes, scene);
    }

    /**
     * Navigates between scenes
     *
     * @param scenes the scenes to switch to
     * @throws NullPointerException in case of the given scene not existing
     */
    public void navigateTo(Scenes scenes) throws Exception {
        try {
            Scene scene = sceneMap.get(scenes);
            stage.setScene(scene);
        } catch (NullPointerException e) {
            throw new NullPointerException("Invalide Szene");
        }
    }
}
