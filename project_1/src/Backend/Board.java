package Backend;

import Utils.PlayerOptions;

import java.util.Arrays;

public class Board {
    /**
     * Creates an object of empty board, which later is filled by Backend.Generator object
     *
     * @param matrix board table consisting all cells
     * @param rowsSum constains sum for each row
     * @param columnsSum constains sum for each column
     * @param size board is sized as size x size
     * @param rowMaxSum specifies the maximum sum for a single row
     */

    Cell[] matrix;
    public int[] rowsSum;
    public int[] columnsSum;
    int size;
    public int rowMaxSum;
    String[] solution;

    public Board(int[] rowsSum, int[] columnSum) {
        this.size = rowsSum.length;
        this.rowsSum = rowsSum;
        this.columnsSum = columnSum;
        this.matrix = new Cell[size * size];

        for (int i = 1; i <= size; ++i) {
            this.rowMaxSum += i;
        }

        fillMatrix();
    }

    public Board(String[] rowsSum, String[] columnSum, String[] solution) {
        this.size = rowsSum.length;
        this.rowsSum = new int[rowsSum.length];
        this.columnsSum = new int[columnSum.length];

        for(int i=0; i < rowsSum.length; i++){
            this.rowsSum[i] = Integer.parseInt(rowsSum[i]);
            this.columnsSum[i] = Integer.parseInt(columnSum[i]);
        }

        this.matrix = new Cell[size * size];
        this.solution = solution;

        for (int i = 1; i <= size; ++i) {
            this.rowMaxSum += i;
        }

        fillMatrix();
    }

    public Board(int size) {
        this.size = size;
        this.rowsSum = new int[size];
        this.columnsSum = new int[size];
        this.matrix = new Cell[size * size];

        for (int i = 1; i <= size; ++i) {
            this.rowMaxSum += i;
        }

        fillMatrix();
    }

    public Board() {
        this.size = generateSize();
        this.rowsSum = new int[size];
        this.columnsSum = new int[size];
        this.matrix = new Cell[size * size];

        for (int i = 1; i <= size; ++i) {
            this.rowMaxSum += i;
        }

        fillMatrix();
    }

    public int getSize() {
        return this.size;
    }
    public int getRowSum(int i) {
        return this.rowsSum[i];
    }
    public int getColumnSum(int i) {
        return this.columnsSum[i];
    }
    public String[] getSolution(){ return this.solution;}
    public Cell getCell(int coordX, int coordY) {
        int index = (coordY - 1) * size + coordX - 1;
        return matrix[index];
    }

    public static int generateSize() {
        int level = PlayerOptions.chooseLevel();

        return level+3;
    }

    public static int generateSize(int level) {
        return level+3;
    }

    public void fillMatrix() {

        int currentPosition = 0;

        for (int i = 1; i <= size; i++) {

            for (int j = 1; j <= size; j++) {

                this.matrix[currentPosition] = new Cell(j, i);
                currentPosition += 1;

            }

        }

    }

    public int countRow(int y) {
        int counter = 0;

        for (Cell cell : matrix) {
            if (cell.isFilled() && cell.getCoordinateY() == y) {
                counter += cell.getCoordinateX();
            }
        }

        return counter;
    }

    public int countColumn(int x) {
        int counter = 0;

        for (Cell cell : matrix) {
            if (cell.isFilled() && cell.getCoordinateX() == x) {
                counter += cell.getCoordinateY();
            }
        }

        return counter;
    }

    public boolean isCompleted() {

        for (int i = 0; i < getSize(); i++) {
            int rowSum = countRow(i + 1);
            int columnSum = countColumn(i + 1);

            if (rowSum != getRowSum(i) || columnSum != getColumnSum(i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();

        StringBuilder columnIndexes = new StringBuilder("    ");
        StringBuilder indexSeparator = new StringBuilder("  |‾");

        for (int i = 1; i <= size; i++) {
            columnIndexes.append(" ").append(i).append(" ");
            indexSeparator.append(" ‾ ");
        }

        indexSeparator.append("‾|");
        resultString
                .append(columnIndexes)
                .append(System.getProperty("line.separator"))
                .append(indexSeparator);

        StringBuilder cells = new StringBuilder();
        int currentRow = 0;

        for (Cell cell : matrix) {

            if (cell.getCoordinateY() != currentRow) {

                if (currentRow != 0) {
                    cells
                            .append(" | ")
                            .append(this.rowsSum[currentRow - 1]);
                }
                currentRow = cell.getCoordinateY();
                cells
                        .append(System.getProperty("line.separator"))
                        .append(currentRow).append(" | ");
            }

            cells.append(cell);
        }

        resultString.append(
                cells.append(" | ")
                        .append(rowsSum[size - 1])
                        .append(System.getProperty("line.separator"))
        );


        StringBuilder columnSums = new StringBuilder("    ");
        StringBuilder sumSeparator = new StringBuilder("  |_");

        for (int i = 1; i <= size; i++) {

            if (this.columnsSum[i - 1] > 9) {
                columnSums
                        .append(" ")
                        .append(this.columnsSum[i - 1]);
            }
            else {
                columnSums
                        .append(" ")
                        .append(this.columnsSum[i - 1])
                        .append(" ");

            }

            sumSeparator.append(" _ ");
        }

        sumSeparator.append("_|");
        resultString
                .append(sumSeparator)
                .append(System.getProperty("line.separator"))
                .append(columnSums);


        return resultString.toString();


    }

}
