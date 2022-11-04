package Sokoban.game.model;

import Sokoban.game.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BoardTest {


    @DisplayName("Build Map")
    @Nested
    class buildMap {
        @Test
        @DisplayName("happy_path > check game board assignment")
        void happy_path() throws Exception {
            Board board = new Board();
            board.buildMap();
            String map = board.getLines().toString().replace("[", "").replace("]", "");
            String actual = "##########, ##########, #######  #, ### .# #.#, #     .  #, # #  $ $ #, # #####  #, ##### $$.#, ### @    #, ##########";
            assertEquals(map, actual);
        }

        @Test
        @DisplayName("not_found > if level not exists, go back to level 1")
        void not_found() throws Exception{
            Board board = new Board();
            board.setLevel(TestUtils.generateRandomNumber());
            try{
                board.buildMap();
            } catch (AssertionError e){
                assertEquals(1, board.getLevel());
            }
        }
    }
}