import java.util.Arrays;
import java.util.Scanner;

public class PlayerOptions {

    static final Scanner scanner = new Scanner(System.in);

    public static boolean cellInputCorrectness(String input, int boardSize){
        char[] inputArray = input.toCharArray();

        if(inputArray.length != 3){

            if(inputArray[0]-'0' == 0){
                return true;
            }

            return false;
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

    public static int getNumberInput(String[] correctInputs){

        String answer = scanner.nextLine();

        while(!Arrays.asList(correctInputs).contains(answer)){
            System.out.println("Wprowadź poprawny numer: ");
            answer = scanner.nextLine();
        }

        return Integer.parseInt(answer);

    }

    public static String getCell(int boardSize){

        String answer = scanner.nextLine();

        while(!cellInputCorrectness(answer, boardSize)){
            System.out.println("Wprowadź poprawne współrzędne: ");
            answer = scanner.nextLine();
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

        int answer = getNumberInput(new String[]{"1", "2", "3"});

        return answer;
    }

    public static int chooseMainOption(){

        System.out.println("""
        Wybierz opcje:
        0. Wyjście
        1. Wygeneruj nową planszę
        2. Zagraj
        """);

        System.out.println("Opcja (podaj 0,1 lub 2):");

        int answer = getNumberInput(new String[]{"0", "1", "2"});

        return answer;
    }

    public static int chooseGeneratedBoard(){

        System.out.println("""
        Wybierz opcje:
        1. Zagraj
        2. Wylosuj inną planszę
        """);

        System.out.println("Opcja (podaj 1 lub 2): ");

        int answer = getNumberInput(new String[]{"1", "2"});

        return answer;

    }

    public static int playAgain(){

        System.out.println("""
        Zagraj ponownie?
        0. Wyjście
        1. Nowa gra
        """);

        System.out.println("Opcja (podaj 0 lub 1): ");

        int answer = getNumberInput(new String[]{"0", "1"});

        return answer;

    }

    public static String chooseCell(int boardSize){

        System.out.println("( X-numer rzędu, Y-numer kolumny, 0-poddaj się )");
        System.out.println("Numer komórki (w formacie X,Y) lub 0: ");

        String answer = getCell(boardSize);

        return answer;

    }

    public static void announceLose(){
        System.out.println("Przegrałeś!");
    }

    public static void announceWin(){
        System.out.println("Gratulacje! Plansza została wypełniona poprawnie.");
    }

    public static void boardGenerationFailed(){
        System.out.println("Wystąpił błąd podczas generowania nowej planszy");
    }

    public static void boardGenerationSuccess(){
        System.out.println("Pomyślnie wygenerowano nową planszę");
    }


}
