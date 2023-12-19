public class Main {
    public static void main(String[] args) {
        Board board = new Board(4);
        System.out.println(board);
        board.generateLevel();
        System.out.println(board);
        board.printGameInfo();
    }
}
