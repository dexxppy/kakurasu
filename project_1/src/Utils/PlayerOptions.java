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
            System.out.println("Wprowadź poprawny numer: ");
            answer = scanLine();
        }

        return Integer.parseInt(answer);

    }

    public static String getCell(int boardSize){

        String answer = scanLine();

        while(!cellInputCorrectness(answer, boardSize)){
            System.out.println("Wprowadź poprawne współrzędne: ");
            answer = scanLine();
        }

        return answer;

    }

    public static int chooseLevel(){

        System.out.println("""
        Wybierz trudność:
        1. Łatwy
        2. Średniozaawansowany
        3. Trudny
        """);

        System.out.println("Poziom (podaj 1,2 lub 3):");

        return getNumberInput(new String[]{"1", "2", "3"});
    }

    public static int chooseMainOption(){

        System.out.println("""
        Wybierz opcje:
        0. Wyjście
        1. Wybierz planszę do zagrania
        2. Zagraj z losową planszą
        3. Wygeneruj nową planszę
        """);

        System.out.println("Opcja (podaj 0,1,2 lub 3):");

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
                    Wybierz planszę, na której chcesz zagrać z pliku $filePath.
                    Opcja(podaj numer od 1 do $lastElem):"""
                    .replace("$filePath", filePath + "matrix.txt")
                    .replace("$lastElem", Integer.toString(maxOption))
            );

            return getNumberInput(inputs);

        }

        return 0;
    }

    public static int chooseGeneratedBoard(){

        System.out.println("""
        Wybierz opcje:
        1. Zagraj
        2. Wylosuj inną planszę
        """);

        System.out.println("Opcja (podaj 1 lub 2): ");

        return getNumberInput(new String[]{"1", "2"});

    }

    public static int playAgain(){

        System.out.println("""
        Zagraj ponownie?
        0. Wyjście do menu
        1. Wybierz planszę z pliku
        2. Zagraj z wygenerowaną planszą
        """);

        System.out.println("Opcja (podaj 0,1 lub 2): ");

        int answer = getNumberInput(new String[]{"0", "1", "2"});
        if(answer == 0){answer = -1;}

        return answer;

    }

    public static String chooseCell(int boardSize){

        System.out.println("( X-numer rzędu, Y-numer kolumny, 0-poddaj się )");
        System.out.println("Numer komórki (w formacie X,Y) lub 0: ");

        String answer = getCell(boardSize);

        System.out.println();
        return answer;

    }

    public static void announceLose(){
        System.out.println("Przegrałeś!");
        System.out.println();
    }

    public static void announceWin(){
        System.out.println("Gratulacje! Plansza została wypełniona poprawnie.");
        System.out.println();
    }

    public static void printSolvedBoard(Board board){
        if(board.isCompleted()){
            System.out.println("Przykładowo rozwiązana plansza: ");
            System.out.println();
            System.out.println(board);
        }else{
            System.out.println("Nie udało się wygenerować przykładowego rozwiązania");
        }

    }


}
