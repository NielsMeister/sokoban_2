package Sokoban.game.scenes;

import Sokoban.game.Pilot;
import Sokoban.game.enums.Scenes;
import javafx.scene.Group;

/**
 * GameStart Scene, gets triggered when launching the project and after beating levels
 * handles proceeding to other scenes on keyPressed
 */
public class GameStartScene extends RootScene {
    /**
     * Instantiates a new GameStartScene
     * Sets the onKeyPressed to a lambda which navigates to play_scene
     *
     * @param pilot the navigator
     * @throws RuntimeException for the case an exception happens during execution
     */
    public GameStartScene(Pilot pilot) throws Exception {
        super(new Group(), "background_start", pilot);
        this.pilot = pilot;
        setOnKeyPressed(event ->
                {
                    try {
                        pilot.navigateTo(Scenes.PLAY_SCENE);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        action();
    }

    /**
     * executes the parental draw()
     */
    private void action() {
        super.draw();
    }
}
