package Frontend;

import Backend.Board;
import Backend.Game;
import Backend.Generator;
import Utils.FileHandler;
import Utils.PlayerOptions;
import Utils.Printer;

public class Program {
    public static void run(){

        Printer printer = new Printer();
        FileHandler fileHandler = new FileHandler();

        printer.printStartText();

        int mode = PlayerOptions.chooseMainOption();
        int level;
        Game game;

        while(mode != 0) {
            switch (mode) {

                case 1:
                    level = PlayerOptions.chooseLevel();
                    int boardIndex = PlayerOptions.chooseBoardFromFile(level);

                    if(boardIndex != 0){
                        Board board = fileHandler.getFileBoard(boardIndex, level);
                        game = new Game(board, level);
                        mode = game.run();
                    }else{
                        System.out.println("File is empty! Add a new board in menu selection.");
                        System.out.println();
                        mode = PlayerOptions.chooseMainOption();
                    }

                    break;

                case 2:
                    game = new Game();
                    mode = game.run();

                    break;

                case 3:
                    level = PlayerOptions.chooseLevel();
                    Generator generator = new Generator(Board.generateSize(level));

                    fileHandler.addBoard(generator.getBoard(), level);
                    System.out.println("New board was added.");
                    System.out.println();

                    mode = PlayerOptions.chooseMainOption();
                    break;

                default:
                    mode = PlayerOptions.chooseMainOption();

            }
        }
    }
}
