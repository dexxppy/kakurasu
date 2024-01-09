import java.util.ArrayList;
import java.util.List;

public class Solver {

    private Board board;
    private int[] rowSums;

    private List<List<List<Integer>>> optionsForEachRow;

    public Solver(int[] rowSums, int[] colSums) {
        this.board = new Board(rowSums, colSums);
        this.rowSums = rowSums;
        this.optionsForEachRow = new ArrayList<>();
        setRowOptions();
        solveKakurasu();
    }

    public Solver(Board board) {
        this.board = new Board(board.rowsSum, board.columnsSum);
        this.rowSums = board.rowsSum;
        this.optionsForEachRow = new ArrayList<>();
        setRowOptions();
        solveKakurasu();
    }

    public List<List<Integer>> generateOptions(int number){

        try {

            if (number == 0) {
                List<List<Integer>> allOptions = new ArrayList<>();
                List<Integer> onlyOption = new ArrayList<>();
                onlyOption.add(0);
                allOptions.add(onlyOption);
                return allOptions;
            }

            List<List<Integer>> allOptions = Combinations.generateCombinations(number);

            List<List<Integer>> optionsToRemove = new ArrayList<>();

            for (List<Integer> option : allOptions) {
                int sum = 0;
                boolean optionImpossibleToReach = false;

                for (int part : option) {
                    sum += part;

                    if (part > board.size) {
                        optionImpossibleToReach = true;
                    }
                }

                if (sum != number || optionImpossibleToReach) {
                    optionsToRemove.add(option);
                }
            }

            for (List<Integer> option : optionsToRemove) {
                allOptions.remove(option);
            }

            return allOptions;
        }catch(Exception e){
            return new ArrayList<>();
        }

    }

    public void setRowOptions(){
        int i = 0;
        while(i < board.getSize()){
            this.optionsForEachRow.add(generateOptions(rowSums[i]));
            i++;
        }
    }

    public ArrayList<int[]> getNumberOfCombinations(){

        int[] numberOfCombinations = new int[board.getSize()];
        int i = 0;

        for(List<List<Integer>> rowOptions : optionsForEachRow){
            numberOfCombinations[i] = rowOptions.size();
            i++;
        }

        return CombinationsGenerator.generateCombinations(numberOfCombinations);

    }

    public List<List<Integer>> getOptions(int[] indexes){

        List<List<Integer>> options = new ArrayList<>();
        int rowIndex = 0;

        for(int index : indexes){
            options.add(optionsForEachRow.get(rowIndex).get(index-1));
            rowIndex++;
        }

        return options;

    }


    public static Board fillBoardWithOptions(Board board, List<List<Integer>> options){

        int rowIndex = 1;

        for(List<Integer> option : options){

            if(option.get(0) != 0){
                for(int colIndex : option){
                    board.getCell(colIndex, rowIndex).fill();
                }
            }

            if(!isValid(board)){
                return board;
            }

            rowIndex++;
        }

        return board;
    }

    private boolean solveKakurasu() {

        try{
            ArrayList<int[]> combinations = getNumberOfCombinations();

            for(int[] combination : combinations){
                Board testBoard = new Board(board.rowsSum, board.columnsSum);
                List<List<Integer>> options = getOptions(combination);
                fillBoardWithOptions(testBoard, options);

                if(testBoard.isCompleted()){
                    this.board = testBoard;
                    return true;
                }


            }
        }catch(Exception e){
            return false;
        }

        return false;

    }

    public static boolean isValid(Board board){
        for(int i = 1; i <= board.getSize(); i++){

            int sum = 0;

            for(int j = 1; j <= board.getSize(); j++){
                if(board.getCell(i, j).isFilled()){
                    sum += j;
                }
            }

            if(sum > board.columnsSum[i-1]){
                return false;
            }
        }

        return true;
    }

    public Board solvingResult(){
        if(board.isCompleted()){
            return getSolvedBoard();
        }else{
            return getUnSolvedBoard();
        }
    }

    public Board getSolvedBoard(){
        return board;
    }

    public Board getUnSolvedBoard(){
        return board;
    }


}
