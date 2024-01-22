package Tests;

import Backend.Board;
import Backend.Solver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class SolverTest {

    private Board sampleBoard;

    @BeforeAll
    public   void setUp() {
        int[] rowsSum = {3,6,7,8,5};
        int[] columnsSum = {10,14,8,12,9};
        sampleBoard = new Board(rowsSum,columnsSum);
    }

    @Test
    public void testSolverInitialization() {
        Solver solver = new Solver(sampleBoard);
        assertNotNull(solver);
    }

    @Test
    void testGenerateOptions() {
        Solver solver = new Solver(sampleBoard);
        List<List<Integer>> options = solver.generateOptions(3);
        assertNotNull(options);
        assertEquals(2,options.size());
    }
    @Test
    void testFillBoardWithOptions() {
        Solver solver = new Solver(sampleBoard);
        List<List<Integer>> options = solver.generateOptions(3);
        Solver.fillBoardWithOptions(sampleBoard, options);
        assertTrue(Solver.isValid(sampleBoard));
    }

    @Test
    void testIsValid() {
        Solver solver = new Solver(sampleBoard);
        assertTrue(Solver.isValid(sampleBoard));
    }

    @Test
    void testSolvingResult() {
        Solver solver = new Solver(sampleBoard);
        assertNotNull(solver.solvingResult());
    }
}