package Sokoban.game.scenes;

import Sokoban.game.Navigator;
import Sokoban.game.enums.Scenes;
import javafx.scene.Group;

/**
 * GameAllLevelsDone class
 */
public class GameAllLevelsDone extends BaseScene {
    /**
     * Instantiates a new Game all levels done.
     *
     * @param navigator the navigator
     * @throws Exception the exception
     */
    public GameAllLevelsDone(Navigator navigator) throws Exception {
        super(new Group(), "background_gameover", navigator);
        this.navigator = navigator;
        setOnKeyPressed(event ->
                {
                    try {
                        navigator.navigateTo(Scenes.START_SCENE);
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
