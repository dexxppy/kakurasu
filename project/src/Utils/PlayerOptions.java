package Utils;

import Backend.*;

import java.util.Arrays;
import java.util.Scanner;

public class PlayerOptions {

    static final FileHandler fileHandler = new FileHandler();
    static final Scanner scanner = new Scanner(System.in);

    public static boolean cellInputCorrectness(String input, int boardSize){
        char[] inputArray = input.toCharArray();

        if(inputArray.length == 0){
            return false;
        }

        if(inputArray.length != 3){
            return inputArray[0] - '0' == 0;
        }
        else{

            try{
                int x = inputArray[0] - '0';
                int y = inputArray[2] - '0';
                
                if(x < 1 || x > boardSize){
                    return false;
                }

                if(y < 1 || y > boardSize){
                    return false;
                }
                
            }catch(Exception e){
                return false;
            }
        }
        
        return true;
    }

    public static String scanLine(){
        String line = scanner.nextLine();
        System.out.println();
        return line;
    }

    public static boolean isNumeric(String input){
        try{
            Integer.parseInt(input);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    public static int getNumberInput(String[] correctInputs){

        String answer = scanLine();

        while(answer.isEmpty() || !isNumeric(answer) || !Arrays.asList(correctInputs).contains(answer)){
            System.out.println("Enter valid number: ");
            answer = scanLine();
        }

        return Integer.parseInt(answer);

    }

    public static String getCell(int boardSize){

        String answer = scanLine();

        while(!cellInputCorrectness(answer, boardSize)){
            System.out.println("Enter valid coordinates: ");
            answer = scanLine();
        }

        return answer;

    }

    public static int chooseLevel(){

        System.out.println("""
        Select difficulty:
        1. Easy
        2. Medium
        3. Hard
        """);

        System.out.println("Difficulty (enter 1,2 lub 3):");

        return getNumberInput(new String[]{"1", "2", "3"});
    }

    public static int chooseMainOption(){

        System.out.println("""
        Select option:
        0. Exit
        1. Select board to play
        2. Play with random board
        3. Generate new board
        """);

        System.out.println("Option (enter 0,1,2 lub 3):");

        return getNumberInput(new String[]{"0", "1", "2", "3"});
    }

    public static int chooseBoardFromFile(int level){

        int maxOption;
        String filePath;

        maxOption = switch (level) {
            case 2 -> {
                filePath = "files/levelMedium/";
                yield fileHandler.readElements(filePath);
            }
            case 3 -> {
                filePath = "files/levelHard/";
                yield fileHandler.readElements(filePath);
            }
            default -> {
                filePath = "files/levelEasy/";
                yield fileHandler.readElements(filePath);
            }
        };

        if(maxOption != 0) {

            String[] inputs = new String[maxOption];

            for (int i = 1; i <= maxOption; i++) {
                inputs[i - 1] = Integer.toString(i);
            }

            System.out.println(
                    """
                    Select board, on which you want to play from $filePath.
                    Option(enter number from 1 to $lastElem):"""
                    .replace("$filePath", filePath + "matrix.txt")
                    .replace("$lastElem", Integer.toString(maxOption))
            );

            return getNumberInput(inputs);

        }

        return 0;
    }

    public static int chooseGeneratedBoard(){

        System.out.println("""
        Select option:
        1. Play
        2. Draw a new board
        """);

        System.out.println("Option (select 1 or 2): ");

        return getNumberInput(new String[]{"1", "2"});

    }

    public static int playAgain(){

        System.out.println("""
        Play again?
        0. Exit to menu
        1. Select board from file
        2. Play with a generated board
        """);

        System.out.println("Option (enter 0,1 or 2): ");

        int answer = getNumberInput(new String[]{"0", "1", "2"});
        if(answer == 0){answer = -1;}

        return answer;

    }

    public static String chooseCell(int boardSize){

        System.out.println("( X-row number, Y-column number, 0-surrender )");
        System.out.println("Cell number (format X,Y) or 0: ");

        String answer = getCell(boardSize);

        System.out.println();
        return answer;

    }

    public static void announceLose(){
        System.out.println("You lost!");
        System.out.println();
    }

    public static void announceWin(){
        System.out.println("Congratulations! You correctly filled out the board.");
        System.out.println();
    }

    public static void printSolvedBoard(Board board){
        if(board.isCompleted()){
            System.out.println("Example of a solved board: ");
            System.out.println();
            System.out.println(board);
        }else{
            System.out.println("Generation of a solved board example failed");
        }

    }


}
