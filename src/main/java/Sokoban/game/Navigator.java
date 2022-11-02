package Sokoban.game;

import Sokoban.game.enums.Scenes;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Navigator {
    private final Stage stage;
    private final Map<Scenes, Scene> sceneMap = new HashMap<>();

    public Navigator(Stage stage) {
        this.stage = stage;
    }

    public void registerScene(Scenes scenes, Scene scene) {
        sceneMap.put(scenes, scene);
    }

    public void navigateTo(Scenes scenes) throws Exception {
        try {
            Scene scene = sceneMap.get(scenes);
            stage.setScene(scene);
        } catch (NullPointerException e) {
            throw new Exception("Invalide szene");
        }
    }
}
