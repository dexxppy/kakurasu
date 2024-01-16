public class Game {

    private Board gameBoard;
    private int level;

    public Game(){

    }

    public void setLevel(){
        this.level = PlayerOptions.chooseLevel();
    }

    public int getLevel(){
        return this.level;
    }

    public void getBoard(){

        int boardChosen = 0;
        Generator generator = new Generator(Board.generateSize(getLevel()));

        while(boardChosen != 1){
            generator = new Generator(Board.generateSize(getLevel()));
            System.out.println(generator.getBoard());
            boardChosen = PlayerOptions.chooseGeneratedBoard();
        }

        this.gameBoard = generator.getBoard();

    }

    public void getSolvedBoard(){
        Solver solver = new Solver(this.gameBoard);
        PlayerOptions.printSolvedBoard(solver.solvingResult());

    }

    public void gamePlay(){

        while(!gameBoard.isCompleted()){
            System.out.println(gameBoard);
            String coords = PlayerOptions.chooseCell(gameBoard.getSize());

            if(coords.equals("0")){

                surrender();
                return;

            }else{

                String[] coordsArr = coords.split(",");
                int coordX = Integer.parseInt(coordsArr[0]);
                int coordY = Integer.parseInt(coordsArr[1]);

                gameBoard.getCell(coordX, coordY).toggleFill();

            }


        }

        PlayerOptions.announceWin();
    }

    public boolean playAgain(){

        if(PlayerOptions.playAgain() == 1){
            return true;
        }

        return false;

    }

    public void surrender(){
        PlayerOptions.announceLose();
        System.out.println();
        System.out.println("...Generowanie przykładowego rozwiązania...");
        System.out.println();
        getSolvedBoard();
        System.out.println();
    }

    public void play(){
        setLevel();
        getBoard();
        gamePlay();
    }

    public void run(){
        Printer printer = new Printer();
        printer.printStartText();
        play();


        while(playAgain()){
            play();
        }
    }



}
