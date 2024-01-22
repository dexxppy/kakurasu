package Tests;

import Utils.PlayerOptions;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerOptionsTest {
private InputStream originalSystemIn;

void setUp() {
    originalSystemIn = System.in;
    }
    @AfterEach
    void tearDown(){
        System.setIn(originalSystemIn);
    }
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
        String input1 = "3\n";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        int result1 = PlayerOptions.chooseMainOption();
        System.setIn(System.in);
        assertEquals(3,result1);
    }
    @Test
    void chooseBoardFromFile() {
        int level = 1;
        String input2 = "4\n";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        int result = PlayerOptions.chooseBoardFromFile(level);
        System.setIn(System.in);
        assertTrue(result >=0);

    }

    @Test
    void chooseGeneratedBoard() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int result = PlayerOptions.chooseGeneratedBoard();
        System.setIn(System.in);
        assertEquals(1, result);
    }

    @Test
    void playAgain() {
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int result = PlayerOptions.playAgain();
        System.setIn(System.in);
        assertEquals(2, result);
    }






}