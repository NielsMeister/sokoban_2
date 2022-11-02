package Sokoban.game.scenes;

import Sokoban.game.Navigator;
import Sokoban.game.enums.Scenes;
import javafx.scene.Group;

public class GameStart extends BaseScene {
    public GameStart(Navigator navigator) throws Exception {
        super(new Group(), "background_start", navigator);
        this.navigator = navigator;
        setOnKeyPressed(event ->
                {
                    try {
                        navigator.navigateTo(Scenes.GAMEPLAY);
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
