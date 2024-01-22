package Tests;

import Backend.Board;
import Utils.FileHandler;
import Utils.FileIO;
import Utils.PlayerOptions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static java.lang.System.setIn;
import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    void testGetBoardSize() {
        FileHandler fileHandler = new FileHandler();
        Board board = new Board(3);
        String result = fileHandler.getBoardSize(board);
        assertEquals(3, board.getSize());
    }

    @Test
    void testGetSolution() {
        Board board = new Board(5);
        board.fillMatrix();

        FileHandler fileHandler = new FileHandler();
        String solution = fileHandler.getSolution(board);
        String expectedSolution = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
        assertEquals(expectedSolution, solution);
    }

    @Test
    void elementsNotEmptyWhileFileIsEmpty() {
        FileIO.writeToFileTxt("emptyFile.txt", "");
        FileHandler fileHandler = new FileHandler();
        fileHandler.elementsNotEmpty("emptyFile.txt");
        String fileContent = FileIO.readFileTxt("emptyFile.txt");
        assertFalse(fileContent.isEmpty());
        assertEquals("0", fileContent);
    }

    @Test
    void elementsNotEmptyWhileFileNotEmpty() {
        FileIO.writeToFileTxt("notEmptyFile.txt", "42");
        FileHandler fileHandler = new FileHandler();
        fileHandler.elementsNotEmpty("notEmptyFile.txt");
        String fileContent = FileIO.readFileTxt("notEmptyFile.txt");
        assertFalse(fileContent.isEmpty());
        assertEquals("42", fileContent);
    }
}