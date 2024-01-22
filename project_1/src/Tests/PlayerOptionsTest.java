package Tests;

import Utils.PlayerOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerOptionsTest {

    @Test
    void cellInputCorrectness() {
        Assertions.assertTrue(PlayerOptions.cellInputCorrectness("1,1",3));
        assertTrue(PlayerOptions.cellInputCorrectness("3,1",3));
        assertFalse(PlayerOptions.cellInputCorrectness("",3));
        assertFalse(PlayerOptions.cellInputCorrectness("0,0",3));
        assertFalse(PlayerOptions.cellInputCorrectness("4,2",3));
        assertFalse(PlayerOptions.cellInputCorrectness("abc",3));
        assertFalse(PlayerOptions.cellInputCorrectness("1,4",3));
        assertFalse(PlayerOptions.cellInputCorrectness("-1,4",3));
        assertFalse(PlayerOptions.cellInputCorrectness("-1,-1",3));
        assertFalse(PlayerOptions.cellInputCorrectness("1,-1",3));
    }

    @Test
    void isNumeric() {
        assertTrue(PlayerOptions.isNumeric("123"));
        assertTrue(PlayerOptions.isNumeric("-26"));
        assertFalse(PlayerOptions.isNumeric("abc"));
        assertFalse(PlayerOptions.isNumeric("1.26"));
    }

    @Test
    void chooseLevel() {
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int result = PlayerOptions.chooseLevel();
        System.setIn(System.in);
        assertEquals(2, result);
    }

    @Test
    void chooseMainOption() {
    }

    @Test
    void chooseBoardFromFile() {
    }

    @Test
    void chooseGeneratedBoard() {
    }

    @Test
    void playAgain() {
    }

    @Test
    void chooseCell() {
    }

    @Test
    void announceLose() {
    }

    @Test
    void announceWin() {
    }

    @Test
    void printSolvedBoard() {
    }
}