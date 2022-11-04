package Sokoban.game.scenes;

import Sokoban.game.Pilot;
import Sokoban.game.enums.Scenes;
import javafx.scene.Group;

/**
 * GameEndWin Scene, gets triggered when players beats a level; when all eyes are on target position
 */
public class GameEndWinScene extends RootScene {

    /**
     * Instantiates a new GameEndWinScene
     * Sets the onKeyPressed to a lambda which navigates to start_scene
     *
     * @param pilot the navigator
     * @throws RuntimeException for the case exception happens during execution
     * @throws Exception basic Exception
     */
    public GameEndWinScene(Pilot pilot) throws Exception {
        super(new Group(), "background_missiondone", pilot);
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
