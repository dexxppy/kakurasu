import java.util.Arrays;
import java.util.Random;

public class Board {
    /**
     * Creates an object of empty board, which later is filled by Generator object
     *
     * @param matrix board table consisting all cells
     * @param rowsSum constains sum for each row
     * @param columsSum constains sum for each column
     * @param size board is sized as size x size
     * @param rowMaxSum specifies the maximum sum for a single row
     */

    Cell[] matrix;
    int[] rowsSum;
    int[] columnsSum;
    int size;
    int rowMaxSum;

    public void setRowsSum(int[] rowsSum) {
        this.rowsSum = rowsSum;
    }

    public void setColumnsSum(int[] columnsSum) {
        this.columnsSum = columnsSum;
    }

    public Board(int size) {
        this.size = size;
        this.rowsSum = new int[size];
        this.columnsSum = new int[size];
        this.matrix = new Cell[size*size];

        for (int i = 1; i <= size; ++i) {
            this.rowMaxSum += i;
        }

        fillMatrix();
    }



    public void fillMatrix(){

        int currentPosition = 0;

        for(int i=1; i <= size; i++){

            for(int j=1; j <= size; j++){

                this.matrix[currentPosition] = new Cell(j, i);
                currentPosition += 1;

            }

        }

    }

    public void markPoint(int x, int y) {
        int index = (y-1) * size + (x-1);
        Cell cell = matrix[index];
        cell.setFilled(!cell.isFilled());
    }

    public void printGameInfo() {
        System.out.println(Arrays.deepToString(matrix));
        System.out.println(Arrays.toString(rowsSum));
        System.out.println(Arrays.toString(columnsSum));
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();

        StringBuilder columnIndexes = new StringBuilder("    ");
        StringBuilder indexSeparator = new StringBuilder("  |‾");

        for(int i = 1; i <= size; i++){
            columnIndexes.append(" ").append(i).append(" ");
            indexSeparator.append(" ‾ ");
        }

        indexSeparator.append("‾|");
        resultString.append(columnIndexes + System.getProperty("line.separator") + indexSeparator);

        StringBuilder cells = new StringBuilder();
        int currentRow = 0;

        for(Cell cell : matrix){

            if(cell.getCoordinateY() != currentRow){

                if(currentRow != 0){
                    cells.append(" | " + this.rowsSum[currentRow-1]);
                }
                currentRow = cell.getCoordinateY();
                cells.append(System.getProperty("line.separator") + currentRow + " | ");
            }

            cells.append(cell);
        }

        resultString.append(cells.append(" | " + rowsSum[size-1]+System.getProperty("line.separator")));


        StringBuilder columnSums = new StringBuilder("    ");
        StringBuilder sumSeparator = new StringBuilder("  |_");

        for(int i = 1; i <= size; i++){
            if (this.columnsSum[i-1] > 9) {
                columnSums.append(" " + this.columnsSum[i-1]);
            } else {
                columnSums.append(" " + this.columnsSum[i-1] + " ");

            }

            sumSeparator.append(" _ ");
        }

        sumSeparator.append("_|");
        resultString.append(sumSeparator).append(System.getProperty("line.separator")).append(columnSums);


        return resultString.toString();


    }

}
