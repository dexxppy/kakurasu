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

            rowsSum[i] = rand.nextInt(rowMaxSum + 1);

        }
    }

    public void gen_cols() {
        Combinations comb = new Combinations();

        for (int i = 1; i <= size; ++i) {
            int row_number = rowsSum[i-1];

            int[] rand_comb = comb.get_random_combination(row_number, size);

            for (int j = 0; j < rand_comb.length; ++j) {

                int col_number = rand_comb[j] - 1 ;
                columnsSum[col_number] += i;

            }
        }
    }
}
