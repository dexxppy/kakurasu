package Tests;

import Backend.Board;
import Backend.Generator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    @Test
    void testGen_rows() {
       Generator generator = new Generator(4);
       generator.gen_rows();
       int[] rowsSum = generator.getBoard().rowsSum;

       for(int sum: rowsSum){
           assertTrue(sum >=0 && sum <= generator.getBoard().rowMaxSum);
       }
    }

    @Test
    void gen_cols() {
        Generator generator = new Generator(4);
        generator.gen_rows();
        generator.gen_cols();
        int[] columnsSum = generator.getBoard().columnsSum;

        for (int sum : columnsSum) {
            assertTrue(sum >= 0 && sum <= 10);
        }
    }

    @Test
    void testGetBoard() {
        Generator generator = new Generator(5);
        Board board = generator.getBoard();
        assertNotNull(board);
        assertEquals(5,board.getSize());
    }
}