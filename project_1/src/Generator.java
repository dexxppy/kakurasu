import org.w3c.dom.ranges.Range;

import java.awt.font.NumericShaper;
import java.util.*;
import java.util.stream.IntStream;

public class Generator{

    private Board board;

    public Generator(int boardSize) {
        this.board = new Board(boardSize);
        gen_rows();
        gen_cols();
    }

    public Generator() {
        this.board = new Board();
        gen_rows();
        gen_cols();
    }

    public void gen_rows() {
        Random rand = new Random();
        int[] rowsSum = new int[board.getSize()];

        for (int i = 0; i < board.getSize(); ++i) {
            rowsSum[i] = rand.nextInt(board.rowMaxSum + 1);
        }

        board.rowsSum = rowsSum;
    }

    public void gen_cols() {
        Combinations comb = new Combinations();
        int[] columnSums = new int[board.getSize()];

        for (int i = 1; i <= board.getSize(); ++i) {
            int row_number = board.rowsSum[i-1];

            int[] rand_comb = comb.get_random_combination(row_number, board.getSize());

            for (int j = 0; j < rand_comb.length; ++j) {

                int col_number = rand_comb[j] - 1 ;
                columnSums[col_number] += i;

            }
        }

        board.columnsSum = columnSums;


    }

    public void regenerate(){
        gen_rows();
        gen_cols();
    }

    public Board getBoard(){
        return this.board;
    }

    public void markPoint(int i, int i1) {
    }
}
