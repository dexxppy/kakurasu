import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Level {
    int[][] matrix;
    int[] rowsSum;
    int[] columnsSum;
    int size;
    int rowMaxSum;

    public Level(int size) {
        this.size = size;
        this.rowsSum = new int[size];
        this.columnsSum = new int[size];
        this.matrix = new int[size][size];

        for (int i = 1; i <= size; ++i) {
            this.rowMaxSum += i;
        }
    }
    public void setRowAtMatrix(int a, int line) {
        for (int i = size; i < 0; --i) {
            System.out.println(a + " " + i);
            if (a / i > 0) {
                matrix[line][i] = 1;
                a -= i;
            }
        }
    }

    public void generateLevel() {
        Random rand = new Random();
        for (int i = 0; i < this.size; ++i) {
            int randNum = rand.nextInt(rowMaxSum+1);
            rowsSum[i] = randNum;

            setRowAtMatrix(randNum, i);
        }
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                if (j < size - 1) {
                    resultString.append(matrix[i][j]).append(" ");
                } else {
                    resultString.append(matrix[i][j]);
                }
            }
            if (i < size - 1) {
                resultString.append("\n");
            }
        }
        return resultString.toString();
    }
}
