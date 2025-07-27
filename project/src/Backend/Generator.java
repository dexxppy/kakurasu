package Backend;

import Utils.Combinations;

import java.util.*;

public class Generator{

    private final Board board;

    public Generator(int boardSize) {
        this.board = new Board(boardSize);
        generateRows();
        generateColumns();
    }

    public Generator() {
        this.board = new Board();
        generateRows();
        generateColumns();
    }

    public void generateRows() {
        Random rand = new Random();
        int[] rowsSum = new int[board.getSize()];

        for (int i = 0; i < board.getSize(); ++i) {
            rowsSum[i] = rand.nextInt(board.rowMaxSum + 1);
        }

        board.rowsSum = rowsSum;
    }

    public void generateColumns() {
        Combinations comb = new Combinations();
        int[] columnSums = new int[board.getSize()];

        for (int i = 1; i <= board.getSize(); ++i) {
            int rowNumber = board.rowsSum[i-1];

            int[] randomCombination = comb.getRandomCombination(rowNumber, board.getSize());

            for (int j = 0; j < randomCombination.length; ++j) {

                int columnNumber = randomCombination[j] - 1 ;
                columnSums[columnNumber] += i;

            }
        }

        board.columnsSum = columnSums;


    }
    public Board getBoard(){
        return this.board;
    }

}
