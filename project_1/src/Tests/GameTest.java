package Tests;

import Backend.Board;
import Backend.Game;
import Utils.PlayerOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;

    @Test
    void testGenerateBoard() {
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        game = new Game();
        Board generatedBoard = game.generateBoard();
        assertEquals(4,generatedBoard.getSize());
    }

    @Test
    void play() {
    }

    @Test
    void surrender() {
    }

    @Test
    void run() {
    }
}