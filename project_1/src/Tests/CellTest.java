package Tests;

import Backend.Cell;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    public void testCellInitialization() {
        Cell cell = new Cell();
        assertNotNull(cell);
        Assertions.assertFalse(cell.isFilled());
    }

    @Test
    void isFilled() {
        Cell cell = new Cell();
        cell.fill();
        Assertions.assertTrue(cell.isFilled());
        cell.toggleFill();
        Assertions.assertFalse(cell.isFilled());
        cell.toggleFill();
        Assertions.assertTrue(cell.isFilled());
    }

    @Test
    void testToString() {
        Cell emptyCell = new Cell();
        Assertions.assertEquals(" - ", emptyCell.toString());
        Cell filledCell = new Cell();
        filledCell.fill();
        Assertions.assertEquals(" X ", filledCell.toString());
    }

    @Test
    void toggleFill() {
        Cell cell = new Cell();
        Assertions.assertFalse(cell.isFilled());
        cell.toggleFill();
        Assertions.assertTrue(cell.isFilled());
        cell.toggleFill();
        Assertions.assertFalse(cell.isFilled());
    }

    @Test
    void fill() {
        Cell cell = new Cell();
        cell.fill();
        Assertions.assertTrue(cell.isFilled());
    }
}