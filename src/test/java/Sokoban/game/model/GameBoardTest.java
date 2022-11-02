package Sokoban.game.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class GameBoardTest {


    @DisplayName("Build Map")
    @Nested
    class buildMap {
        @Test
        @DisplayName("happy_path > check game board assignment")
        void happy_path() throws Exception {
            GameBoard gameBoard = new GameBoard();
            gameBoard.buildMap();
            String map = gameBoard.getLines().toString().replace("[", "").replace("]", "");
            String actual = "##########, ##########, #######  #, ### .# #.#, #     .  #, # #  $ $ #, # #####  #, ##### $$.#, ### @    #, ##########";
            assertEquals(map, actual);
        }

        @Test
        @DisplayName("not_found > if level not exists, go back to level 1")
        void not_found() throws Exception{
            GameBoard gameBoard = new GameBoard();
            gameBoard.setLevel(TestUtils.generateRandomNumber());
            try{
                gameBoard.buildMap();
            } catch (AssertionError e){
                assertEquals(1, gameBoard.getLevel());
            }
        }
    }
}