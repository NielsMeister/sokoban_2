package Sokoban.game.scenes;

import Sokoban.game.Navigator;
import Sokoban.game.enums.Scenes;
import javafx.scene.Group;

/**
 * GameStart class
 */
public class GameStart extends BaseScene {
    /**
     * Instantiates a new Game start.
     *
     * @param navigator the navigator
     * @throws Exception the exception
     */
    public GameStart(Navigator navigator) throws Exception {
        super(new Group(), "background_start", navigator);
        this.navigator = navigator;
        setOnKeyPressed(event ->
                {
                    try {
                        navigator.navigateTo(Scenes.PLAY_SCENE);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        action();
    }

    private void action() {
        super.draw();
    }
}
