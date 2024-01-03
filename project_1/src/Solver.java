import java.util.ArrayList;
import java.util.List;

public class Solver {
    private Board board;
    private List<List<List<Integer>>> options;

    public Solver(int[] rowsSum, int[] columnSum){
        this.board = new Board(rowsSum, columnSum);
        this.options = new ArrayList<>();
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

        while(i <= board.rowMaxSum){
            options.add(generateOptions(i));
            i++;
        }
    }

    public void tickNecessaryCells(){

        for(int i = 0; i < board.getSize(); i++){
            List<List<Integer>> rowSumOptions = options.get(board.rowsSum[i]);
            List<List<Integer>> columnSumOptions = options.get(board.columnsSum[i]);

            if(rowSumOptions.size() == 1){
                for(int element : rowSumOptions.get(0)){
                    board.getCell(element, i+1).toggleFill();
                }
            }

            if(columnSumOptions.size() == 1){
                for(int element : columnSumOptions.get(0)){
                    board.getCell(i+1, element).toggleFill();
                }
            }
        }
    }



    public static void main(String[] args){
        Solver solver = new Solver(new int[]{1, 0, 0, 1}, new int[]{0, 2, 0, 0});
        solver.tickNecessaryCells();
        solver.printBoard();
    }

}
