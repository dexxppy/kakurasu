import org.w3c.dom.ranges.Range;

import java.awt.font.NumericShaper;
import java.util.*;
import java.util.stream.IntStream;

public class Generator extends Board {

    public Generator(int size) {
        super(size);

        gen_rows();
        gen_cols();
    }

    public void gen_rows() {
        Random rand = new Random();

        for (int i = 0; i < size; ++i) {

            rowsSum[i] = rand.nextInt(rowMaxSum);

        }

        setRowsSum(rowsSum);
    }

    public void gen_cols() {
        int[] colsSums = new int[size];

        Combinations comb = new Combinations();

        for (int i = 0; i < size; ++i) {
            int row_number = rowsSum[i];

            int[] rand_comb = comb.get_random_combination(row_number, size);
            System.out.println(Arrays.toString(rand_comb));
            for (int j = 7; j > 0; --j) {

            }
        }

        setColumnsSum(colsSums);
    }


}
