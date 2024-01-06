import java.util.ArrayList;
import java.util.List;

public class Solver {
    private Board board;
    private List<List<List<Integer>>> optionsForEachRow;
    private List<List<List<Integer>>> optionsForEachColumn;

    public Solver(int[] rowsSum, int[] columnSum){
        this.board = new Board(rowsSum, columnSum);
        this.optionsForEachRow = new ArrayList<>();
        this.optionsForEachColumn = new ArrayList<>();
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
                addEmptyFiller(i, true);
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
                addEmptyFiller(i, false);
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

        for(int i = 1; i <= board.getSize(); i++){
            if(columnSolved(i)){
                if(!board.getCell(i, targetRow+1).isFilled()){
                    for(List<Integer> rowOptions : optionsForEachRow.get(targetRow)){
                        if(rowOptions.contains(i)){
                            optionsToRemove.add(rowOptions);
                        }
                    }
                }
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

        for(int i = 1; i <= board.getSize(); i++){
            if(rowSolved(i)){
                if(!board.getCell(targetColumn+1, i).isFilled()){
                    for(List<Integer> columnOptions : optionsForEachColumn.get(targetColumn)){
                        if(columnOptions.contains(i)){
                            optionsToRemove.add(columnOptions);
                        }
                    }
                }
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
                optionsForEachColumn.get(columnIndex).remove(option);
            }

            columnIndex++;

        }
    }

    public void clearUnreachableOptions(){
        for(int i=0; i < board.rowsSum.length; i++){
            if(board.rowsSum[i] == 0){
                addEmptyFiller(i, true);
                removeUnreachableOptionsForColumns(i);
            }

            if(board.columnsSum[i] == 0){
                addEmptyFiller(i, false);
                removeUnreachableOptionsForRows(i);
            }
        }
    }

    public void addEmptyFiller(int index, boolean isRow){

        List<Integer> emptyFiller = new ArrayList<>();
        emptyFiller.add(0);

        if(isRow){

            while(!optionsForEachRow.get(index).isEmpty()){
                optionsForEachRow.get(index).remove(0);
            }

            optionsForEachRow.get(index).add(emptyFiller);
        }else{

            while(!optionsForEachColumn.get(index).isEmpty()) {
                optionsForEachColumn.get(index).remove(0);
            }

            optionsForEachColumn.get(index).add(emptyFiller);
        }

    }

    public void clearCompleted(){

        for(int i = 0; i < board.getSize(); i++){
            if(rowSolved(i+1)){
                addEmptyFiller(i, true);
            }

            if(columnSolved(i+1)){
                addEmptyFiller(i, false);
            }
        }
    }

    public void easySolve(){
        clearUnreachableOptions();
        System.out.println(optionsForEachRow);
        System.out.println();
        System.out.println(optionsForEachColumn);

        while(!solvingSuccessful()) {
            if(!easilySolvable() || !isSolvable()){
                return;
            }
            tickNecessaryCells();
            clearCompleted();
            printBoard();
            System.out.println(optionsForEachRow);
            System.out.println();
            System.out.println(optionsForEachColumn);
        }

        System.out.println("Completed: " + solvingSuccessful());

    }

    public boolean solvingSuccessful(){
        return board.isCompleted();
    }

    // index > 0
    public boolean rowSolved(int index){

        int sum = 0;

        for(int i = 1; i <= board.size; i++){
            if(board.getCell(i, index).isFilled()){
                sum += i;
            }
        }

        return sum == board.rowsSum[index-1];
    }

    // index > 0
    public boolean columnSolved(int index){

        int sum = 0;

        for(int i = 1; i <= board.size; i++){
            if(board.getCell(index, i).isFilled()){
                sum += i;
            }
        }

        return sum == board.columnsSum[index-1];
    }

    public boolean isSolvable() {

        for (int i = 1; i <= board.getSize(); i++) {

            if (!rowSolved(i) && optionsForEachRow.get(i - 1).get(0).get(0) == 0) {
                return false;
            } else if (rowSolved(i) && optionsForEachRow.get(i - 1).get(0).get(0) != 0) {
                return false;
            }

            if (!columnSolved(i) && optionsForEachColumn.get(i - 1).get(0).get(0) == 0) {
                return false;
            } else if (columnSolved(i) && optionsForEachColumn.get(i - 1).get(0).get(0) != 0) {
                return false;
            }

        }

        return true;
    }

    public boolean easilySolvable(){

        for(List<List<Integer>> rowOptions : optionsForEachRow){
            if(rowOptions.size() == 1){
                return true;
            }
        }

        for(List<List<Integer>> columnOptions : optionsForEachColumn){
            if(columnOptions.size() == 1){
                return true;
            }
        }

        return false;
    }

    public void removeUnneededRowOptions(int index, List<Integer> baseOption){

        List<List<Integer>> optionsToRemove = new ArrayList<>();

        for(List<Integer> option : optionsForEachRow.get(index)){
            if(!option.equals(baseOption)){
                optionsToRemove.add(option);
            }
        }

        for(List<Integer> option : optionsToRemove){
            optionsForEachRow.get(index).remove(option);
        }
    }

    public void removeUnneededColumnOptions(int index, List<Integer> baseOption){

        List<List<Integer>> optionsToRemove = new ArrayList<>();

        for(List<Integer> option : optionsForEachColumn.get(index)){
            if(!option.equals(baseOption)){
                optionsToRemove.add(option);
            }
        }

        for(List<Integer> option : optionsToRemove){
            optionsForEachColumn.get(index).remove(option);
        }
    }

    public void tryEachOption(){

        Board tempBoard = this.board;
        List<List<List<Integer>>> tempOptionsForEachRow = this.optionsForEachRow;
        List<List<List<Integer>>> tempOptionsForEachColumn = this.optionsForEachColumn;

        int rowIndex = 0;
        for(List<List<Integer>> rowOptions : optionsForEachRow){

            for(List<Integer> option : rowOptions){

                removeUnneededRowOptions(rowIndex, option);
                easySolve();

                if(solvingSuccessful()){
                    return;
                }else{
                    this.board = tempBoard;
                    this.optionsForEachRow = tempOptionsForEachRow;
                    this.optionsForEachColumn = tempOptionsForEachColumn;
                }

            }

            rowIndex++;
        }

        int columnIndex = 0;
        for(List<List<Integer>> columnOptions : optionsForEachColumn){

            for(List<Integer> option : columnOptions){

                removeUnneededColumnOptions(columnIndex, option);
                easySolve();

                if(solvingSuccessful()){
                    return;
                }else{
                    this.board = tempBoard;
                    this.optionsForEachRow = tempOptionsForEachRow;
                    this.optionsForEachColumn = tempOptionsForEachColumn;
                }

            }

            columnIndex++;
        }
    }

    public void solve(){
        printBoard();
        clearUnreachableOptions();

        if(easilySolvable()){
            System.out.println("Easy solve-------------------------------------------");
            easySolve();
        }else{
            printBoard();
            System.out.println(optionsForEachRow);
            System.out.println();
            System.out.println(optionsForEachColumn);
            while(!solvingSuccessful()) {
                System.out.println("every option-------------------------------------------");
                tryEachOption();
            }
        }

        if(solvingSuccessful()){
            System.out.println(this.board);
        }else{
            System.out.println("Nie udało się rozwiązać planszy");
        }

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
    }

}
