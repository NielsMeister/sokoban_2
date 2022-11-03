package Sokoban.game.scenes;

import Sokoban.game.Navigator;
import Sokoban.game.enums.Scenes;
import javafx.scene.Group;

/**
 * GameEndWin class
 */
public class GameEndWin extends BaseScene {

    /**
     * Instantiates a new Game end win.
     *
     * @param navigator the navigator
     * @throws Exception the exception
     */
    public GameEndWin(Navigator navigator) throws Exception {
        super(new Group(), "background_missiondone", navigator);
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
