package Tests;

import Backend.Solver;
import org.junit.jupiter.api.Test;
import Backend.Board;
import Backend.Cell;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {


    @Test
    void testGenerateSize() {
        int level = 2;
        assertEquals(5, Board.generateSize(level));
        level = 4;
        assertEquals(7, Board.generateSize(level));
    }

    @Test
    void testFillMatrix() {
        Board board = new Board(3);
        board.fillMatrix();
        for (int i = 1; i <= board.getSize(); i++) {
            for (int j = 1; j <= board.getSize(); j++) {
                Cell cell = board.getCell(j, i);
                assertNotNull(cell);
                assertEquals(j, cell.getCoordinateX());
                assertEquals(i, cell.getCoordinateY());
            }
        }

    }

    @Test
    void testCountRow() {
        Board board = new Board(4);
        board.fillMatrix();
        board.getCell(1, 1).setFilled(true);
        board.getCell(1, 4).setFilled(true);
        board.getCell(4, 4).setFilled(true);
        assertEquals(1, board.countRow(1));
        assertEquals(5, board.countRow(4));
    }

    @Test
    void testCountColumn() {
        Board board = new Board(4);
        board.fillMatrix();
        board.getCell(1, 3).setFilled(true);
        board.getCell(1, 4).setFilled(true);
        assertEquals(7, board.countColumn(1));
    }

    @Test
    void testCountRowAndColumn() {
        Board board = new Board(3);
        board.fillMatrix();
        board.getCell(1, 1).setFilled(true);
        board.getCell(1, 2).setFilled(true);
        board.getCell(1, 3).setFilled(true);
        assertEquals(6, board.countColumn(1));
        assertEquals(1, board.countRow(1));
        assertEquals(1, board.countRow(2));
        assertEquals(1, board.countRow(3));
    }
}