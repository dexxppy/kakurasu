import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    public void testCellInitialization() {
        Cell cell = new Cell();
        assertNotNull(cell);
        assertFalse(cell.isFilled());
    }

    @Test
    void isFilled() {
        Cell cell = new Cell();
        cell.fill();
        assertTrue(cell.isFilled());
        cell.toggleFill();
        assertFalse(cell.isFilled());
        cell.toggleFill();
        assertTrue(cell.isFilled());
    }

    @Test
    void testToString() {
        Cell emptyCell = new Cell();
        assertEquals(" - ", emptyCell.toString());
        Cell filledCell = new Cell();
        filledCell.fill();
        assertEquals(" X ", filledCell.toString());
    }

    @Test
    void toggleFill() {
        Cell cell = new Cell();
        assertFalse(cell.isFilled());
        cell.toggleFill();
        assertTrue(cell.isFilled());
        cell.toggleFill();
        assertFalse(cell.isFilled());
    }

    @Test
    void fill() {
        Cell cell = new Cell();
        cell.fill();
        assertTrue(cell.isFilled());
    }
}