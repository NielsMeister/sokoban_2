package Sokoban;

import Sokoban.game.Pilot;
import Sokoban.game.enums.Scenes;
import Sokoban.game.scenes.AllLevelsDoneScene;
import Sokoban.game.scenes.GameEndWinScene;
import Sokoban.game.scenes.GamePlayScene;
import Sokoban.game.scenes.GameStartScene;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * SokobanMain class, used for setting up application to then get launched
 */
public class SokobanMain extends Application {
    private Pilot pilot;

    @Override
    public void start(Stage stage) throws Exception {
        pilot = new Pilot(stage);
        pilot.registerScene(Scenes.WIN_SCENE, new GameEndWinScene(pilot));
        pilot.registerScene(Scenes.START_SCENE, new GameStartScene(pilot));
        pilot.registerScene(Scenes.PLAY_SCENE, new GamePlayScene(pilot));
        pilot.registerScene(Scenes.ALLLEVELSDONE_SCENE, new AllLevelsDoneScene(pilot));
        pilot.navigateTo(Scenes.START_SCENE);
        stage.setTitle("Sokoban");
        stage.show();
        stage.requestFocus();
        stage.setAlwaysOnTop(true);
    }
}
