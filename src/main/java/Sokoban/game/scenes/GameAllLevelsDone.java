package Sokoban.game.scenes;

import Sokoban.game.Navigator;
import Sokoban.game.enums.Scenes;
import javafx.scene.Group;

public class GameAllLevelsDone extends BaseScene {
    public GameAllLevelsDone(Navigator navigator) throws Exception {
        super(new Group(), "background_gameover", navigator);
        this.navigator = navigator;
        setOnKeyPressed(event ->
                {
                    try {
                        navigator.navigateTo(Scenes.GAMESTART);
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
