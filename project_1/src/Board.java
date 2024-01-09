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

    public Board(int[] rowsSum, int[] columnSum) {
        this.size = rowsSum.length;
        this.rowsSum = rowsSum;
        this.columnsSum = columnSum;
        this.matrix = new Cell[size*size];

        for (int i = 1; i <= size; ++i) {
            this.rowMaxSum += i;
        }

        fillMatrix();
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

    public Board() {
        this.size = generateSize();
        this.rowsSum = new int[size];
        this.columnsSum = new int[size];
        this.matrix = new Cell[size*size];

        for (int i = 1; i <= size; ++i) {
            this.rowMaxSum += i;
        }

        fillMatrix();
    }

    public int getSize(){
        return this.size;
    }

    public int getRowSum(int i){
        return this.rowsSum[i];
    }

    public int getColumnSum(int i){
        return this.columnsSum[i];
    }

    public Cell[] getMatrix(){ return this.matrix;}
    public Cell getCell(int coordX, int coordY){
        int index = (coordY-1)*size+coordX-1;
        return matrix[index];
    }

    public static int generateSize(){
        Random random = new Random();
        int level = PlayerOptions.chooseLevel();

        return switch (level) {
            case 1 -> (random.nextInt(3, 5));
            case 2 -> (random.nextInt(5, 7));
            case 3 -> (random.nextInt(7, 9));
            default -> 0;
        };
    }

    public static int generateSize(int level){
        Random random = new Random();

        return switch (level) {
            case 1 -> (random.nextInt(3, 5));
            case 2 -> (random.nextInt(5, 7));
            case 3 -> (random.nextInt(7, 9));
            default -> 0;
        };
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

    public int countRow(int y){
        int counter = 0;

        for(Cell cell : matrix){
            if(cell.isFilled() && cell.getCoordinateY() == y){
                counter += cell.getCoordinateX();
            }
        }

        return counter;
    }

    public int countColumn(int x){
        int counter = 0;

        for(Cell cell : matrix){
            if(cell.isFilled() && cell.getCoordinateX() == x){
                counter += cell.getCoordinateY();
            }
        }

        return counter;
    }

    public boolean isCompleted(){

        for(int i = 0; i < getSize(); i++){
            int rowSum = countRow(i+1);
            int columnSum = countColumn(i+1);

            if(rowSum != getRowSum(i) || columnSum != getColumnSum(i)){
                return false;
            }
        }

        return true;
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
