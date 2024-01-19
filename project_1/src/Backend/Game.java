package Backend;

import Utils.PlayerOptions;

public class Game {

    private Solver solver;
    private final Board gameBoard;
    private Board solvedBoard;
    private int level;

    public Game(Board board, int level){
        this.level = level;
        this.gameBoard = board;
    }

    public Game(){
        this.setLevel();
        this.gameBoard = generateBoard();
    }

    public Board getBoard(){return this.gameBoard;}
    public void setLevel(){
        this.level = PlayerOptions.chooseLevel();
    }
    public int getLevel(){
        return this.level;
    }
    public void setSolver(){

        if(getBoard().getSolution() != null){
            this.solver = new Solver(getBoard(), getBoard().getSolution());
        }else{
            this.solver = new Solver(getBoard());
        }
    }

    public Board generateBoard(){

        int boardChosen = 0;
        Generator generator = new Generator(Board.generateSize(getLevel()));

        while(boardChosen != 1){
            generator = new Generator(Board.generateSize(getLevel()));

            System.out.println(generator.getBoard());
            System.out.println();

            boardChosen = PlayerOptions.chooseGeneratedBoard();
        }

        return generator.getBoard();

    }

    public void setSolvedBoard(){
        setSolver();
        this.solvedBoard = this.solver.solvingResult();
    }

    public void play(){

        while(!gameBoard.isCompleted()){

            System.out.println(gameBoard);
            System.out.println();

            String coords = PlayerOptions.chooseCell(gameBoard.getSize());

            if(coords.equals("0")){

                surrender();
                return;

            }
            else{

                String[] coordsArr = coords.split(",");
                int coordX = Integer.parseInt(coordsArr[0]);
                int coordY = Integer.parseInt(coordsArr[1]);

                gameBoard.getCell(coordX, coordY).toggleFill();

            }


        }

        PlayerOptions.announceWin();
    }

    public void surrender(){
        PlayerOptions.announceLose();

        System.out.println("...Generowanie przykładowego rozwiązania...");
        System.out.println();
        setSolvedBoard();

        PlayerOptions.printSolvedBoard(this.solvedBoard);

        System.out.println();
    }

    public int run(){
        play();
        return PlayerOptions.playAgain();

    }


}
