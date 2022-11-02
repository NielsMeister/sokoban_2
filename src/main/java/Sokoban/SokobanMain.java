package Sokoban;

import Sokoban.game.Navigator;
import Sokoban.game.enums.Scenes;
import Sokoban.game.scenes.GameAllLevelsDone;
import Sokoban.game.scenes.GameEndWin;
import Sokoban.game.scenes.GamePlay;
import Sokoban.game.scenes.GameStart;
import javafx.application.Application;
import javafx.stage.Stage;

public class SokobanMain extends Application {
    private Navigator navigator;

    @Override
    public void start(Stage stage) throws Exception {
        navigator = new Navigator(stage);
        navigator.registerScene(Scenes.GAMEWIN, new GameEndWin(navigator));
        navigator.registerScene(Scenes.GAMESTART, new GameStart(navigator));
        navigator.registerScene(Scenes.GAMEPLAY, new GamePlay(navigator));
        navigator.registerScene(Scenes.GAMEALLLEVELSDONE, new GameAllLevelsDone(navigator));
        navigator.navigateTo(Scenes.GAMESTART);
        stage.setTitle("Sokoban");
        stage.show();
        stage.requestFocus();
        stage.setAlwaysOnTop(true);
    }
}
