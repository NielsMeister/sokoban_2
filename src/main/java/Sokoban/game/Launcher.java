package Sokoban.game;

import Sokoban.SokobanMain;
import javafx.application.Application;

/**
 * Launcher class, used for launching process
 */
public class Launcher {
    /**
     * The entry point of application, launches the main
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Application.launch(SokobanMain.class);
    }
}
