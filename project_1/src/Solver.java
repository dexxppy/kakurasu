import java.util.ArrayList;
import java.util.List;

public class Solver {
    private Board board;
    private List<List<List<Integer>>> optionsForEachRow;
    private List<List<List<Integer>>> optionsForEachColumn;

    public static List<Integer> emptyFiller = new ArrayList<>();

    public Solver(int[] rowsSum, int[] columnSum){
        this.board = new Board(rowsSum, columnSum);
        this.optionsForEachRow = new ArrayList<>();
        this.optionsForEachColumn = new ArrayList<>();
        emptyFiller.add(0);
        setAllOptions();
    }

    public void printBoard(){
        System.out.println(board);
    }

    public List<List<Integer>> generateOptions(int number){

        List<List<Integer>> allOptions = Combinations.generateCombinations(number);
        List<List<Integer>> optionsToRemove = new ArrayList<>();

        for(List<Integer> option : allOptions){
            int sum = 0;
            boolean optionImpossibleToReach = false;

            for(int part : option){
                sum += part;

                if(part > board.size){
                    optionImpossibleToReach = true;
                }
            }

            if(sum != number || optionImpossibleToReach){
                optionsToRemove.add(option);
            }
        }

        for(List<Integer> option : optionsToRemove){
            allOptions.remove(option);
        }

        return allOptions;

    }

    public void setAllOptions(){

        int i = 0;

        while(i < board.size){
            optionsForEachRow.add(generateOptions(board.rowsSum[i]));
            optionsForEachColumn.add(generateOptions(board.columnsSum[i]));
            i++;
        }
    }

    public void tickNecessaryCells() {

        for (int i = 0; i < board.getSize(); i++) {
            List<List<Integer>> rowSumOptions = optionsForEachRow.get(i);

            if (rowSumOptions.size() == 1 && rowSumOptions.get(0).get(0) != 0) {
                for (int element : rowSumOptions.get(0)) {
                    board.getCell(element, i + 1).fill();
                    removeUnreachableOptionsForColumn(board.getCell(element, i + 1));
                    System.out.println("1");

                    printBoard();
                    System.out.println(optionsForEachRow);
                    System.out.println();
                    System.out.println(optionsForEachColumn);
                }
                optionsForEachRow.get(i).remove(0);
                optionsForEachRow.get(i).add(emptyFiller);
                System.out.println("2");
                printBoard();
                System.out.println(optionsForEachRow);
                System.out.println();
                System.out.println(optionsForEachColumn);
                }

        }

        for (int i = 0; i < board.getSize(); i++) {

            List<List<Integer>> columnSumOptions = optionsForEachColumn.get(i);

            if (columnSumOptions.size() == 1 && columnSumOptions.get(0).get(0) != 0) {
                for (int element : columnSumOptions.get(0)) {
                    board.getCell(i + 1, element).fill();
                    removeUnreachableOptionsForRow(board.getCell(i + 1, element));
                    System.out.println("1");
                    printBoard();
                    System.out.println(optionsForEachRow);
                    System.out.println();
                    System.out.println(optionsForEachColumn);
                }
                System.out.println("2");
                optionsForEachColumn.get(i).remove(0);
                optionsForEachColumn.get(i).add(emptyFiller);
                printBoard();
                System.out.println(optionsForEachRow);
                System.out.println();
                System.out.println(optionsForEachColumn);
            }

        }
    }

    // after ticking a new cell given in function arguments, remove all options, which dont include ticked cell,
    // but should, function used depends on whether cell got toggled through column sum(use removeUnreachableOptionsForRow)
    // or row sum(use removeUnreachableOptionsForColumn)
    public void removeUnreachableOptionsForRow(Cell cellTicked){
        int targetRow = cellTicked.getCoordinateY()-1;
        int necessaryElement = cellTicked.getCoordinateX();
        List<List<Integer>> optionsToRemove = new ArrayList<>();

        for(List<Integer> option : optionsForEachRow.get(targetRow)){
            boolean containsNecessaryElement = false;

            for(int element : option){
                if(element == necessaryElement || element == 0){
                    containsNecessaryElement = true;
                }
            }

            if(!containsNecessaryElement){
                optionsToRemove.add(option);
            }
        }

        for(List<Integer> option : optionsToRemove){
            optionsForEachRow.get(targetRow).remove(option);
        }

    }

    public void removeUnreachableOptionsForColumn(Cell cellTicked){
        int necessaryElement = cellTicked.getCoordinateY();
        int targetColumn = cellTicked.getCoordinateX()-1;
        List<List<Integer>> optionsToRemove = new ArrayList<>();


        for(List<Integer> option : optionsForEachColumn.get(targetColumn)){
            boolean containsNecessaryElement = false;

            for(int element : option){
                if(element == necessaryElement || element == 0){
                    containsNecessaryElement = true;
                }
            }

            if(!containsNecessaryElement){
                optionsToRemove.add(option);
            }
        }

        for(List<Integer> option : optionsToRemove){
            optionsForEachColumn.get(targetColumn).remove(option);
        }

    }

    // use those if row/column sum equals 0
    public void removeUnreachableOptionsForRows(int coordX){

        int rowIndex = 0;

        for(List<List<Integer>> row : optionsForEachRow){

            List<List<Integer>> optionsToRemove = new ArrayList<>();

            for(List<Integer> option : row){
                boolean containsNecessaryElement = false;

                for(int element : option){
                    if(element == coordX){
                        containsNecessaryElement = true;
                    }
                }

                if(containsNecessaryElement){
                    optionsToRemove.add(option);
                }
            }

            for(List<Integer> option : optionsToRemove){
                optionsForEachRow.get(rowIndex).remove(option);
            }

            rowIndex++;

        }

    }

    public void removeUnreachableOptionsForColumns(int coordY) {

        int columnIndex = 0;

        for (List<List<Integer>> column : optionsForEachColumn) {

            List<List<Integer>> optionsToRemove = new ArrayList<>();

            for (List<Integer> option : column) {
                boolean containsNecessaryElement = false;

                for (int element : option) {
                    if (element == coordY) {
                        containsNecessaryElement = true;
                    }
                }

                if (containsNecessaryElement) {
                    optionsToRemove.add(option);
                }
            }

            for (List<Integer> option : optionsToRemove) {
                optionsForEachRow.get(columnIndex).remove(option);
            }

            columnIndex++;

        }
    }

    public void clearUnreachableOptions(){
        for(int i=0; i < board.rowsSum.length; i++){
            if(board.rowsSum[i] == 0){
                optionsForEachRow.get(i).remove(0);
                optionsForEachRow.get(i).add(emptyFiller);
                removeUnreachableOptionsForColumns(i);
            }

            if(board.columnsSum[i] == 0){
                optionsForEachColumn.get(i).remove(0);
                optionsForEachColumn.get(i).add(emptyFiller);
                removeUnreachableOptionsForRows(i);
            }
        }
    }

    public void solve(){
        clearUnreachableOptions();

        while(!board.isCompleted()) {
            tickNecessaryCells();
            printBoard();
            System.out.println(optionsForEachRow);
            System.out.println();
            System.out.println(optionsForEachColumn);
        }

        System.out.println("Completed: " + board.isCompleted());

    }



    public static void main(String[] args){
        Generator generator1 = new Generator(4);
        Generator generator2 = new Generator(6);
        Generator generator3 = new Generator(8);
        Board board1 = generator1.getBoard();
        Board board2 = generator2.getBoard();
        Board board3 = generator3.getBoard();
        Solver solver1 = new Solver(board1.rowsSum, board1.columnsSum);
        solver1.solve();
//        Solver solver2 = new Solver(board2.rowsSum, board2.columnsSum);
//        solver2.solve();
//        Solver solver3 = new Solver(board3.rowsSum, board3.columnsSum);
//        solver3.solve();
    }

}
