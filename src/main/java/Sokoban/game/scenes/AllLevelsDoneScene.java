package Sokoban.game.scenes;

import Sokoban.game.Pilot;
import Sokoban.game.enums.Scenes;
import javafx.scene.Group;

/**
 * GameAllLevelsDone Scene, gets triggered when player beat all levels
 */
public class AllLevelsDoneScene extends RootScene {
    /**
     * Instantiates a new AllLevelsDoneScene
     * Sets the onKeyPressed to a lambda which navigates to start_scene
     *
     * @param pilot the navigator
     * @throws RuntimeException for the case exception happens during execution
     */
    public AllLevelsDoneScene(Pilot pilot) throws Exception {
        super(new Group(), "background_gameover", pilot);
        this.pilot = pilot;
        setOnKeyPressed(event ->
                {
                    try {
                        pilot.navigateTo(Scenes.START_SCENE);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        action();
    }

    /**
     * executes draw() from parent class
     */
    private void action() {
        super.draw();
    }
}
