package Sokoban;

import Sokoban.game.Navigator;
import Sokoban.game.enums.Scenes;
import Sokoban.game.scenes.GameAllLevelsDone;
import Sokoban.game.scenes.GameEndWin;
import Sokoban.game.scenes.GamePlay;
import Sokoban.game.scenes.GameStart;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * SokobanMain class
 */
public class SokobanMain extends Application {
    private Navigator navigator;

    @Override
    public void start(Stage stage) throws Exception {
        navigator = new Navigator(stage);
        navigator.registerScene(Scenes.WIN_SCENE, new GameEndWin(navigator));
        navigator.registerScene(Scenes.START_SCENE, new GameStart(navigator));
        navigator.registerScene(Scenes.PLAY_SCENE, new GamePlay(navigator));
        navigator.registerScene(Scenes.ALLLEVELSDONE_SCENE, new GameAllLevelsDone(navigator));
        navigator.navigateTo(Scenes.START_SCENE);
        stage.setTitle("Sokoban");
        stage.show();
        stage.requestFocus();
        stage.setAlwaysOnTop(true);
    }
}
