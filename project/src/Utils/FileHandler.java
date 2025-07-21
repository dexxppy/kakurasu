package Utils;
import Backend.*;

public class FileHandler {

    public String getBoardSize(Board board){
        return board.getSize() + "\n";
    }

    public String getBoardColSums(Board board){

        StringBuilder columns = new StringBuilder();

        for(int i = 0; i < board.getSize(); i++){
            columns.append(board.getColumnSum(i));
            columns.append(" ");
        }
        columns.append("\n");

        return columns.toString();
    }

    public String getBoardRowSums(Board board){

        StringBuilder rows = new StringBuilder();

        for(int i = 0; i < board.getSize(); i++){
            rows.append(board.getRowSum(i));
            rows.append(" ");
        }
        rows.append("\n");

        return rows.toString();
    }

    public String getSolution(Board board){
        Solver solver = new Solver(board);
        Board solution = solver.solvingResult();

        StringBuilder solutionBin = new StringBuilder();

        for(int i = 1; i <= solution.getSize(); i++){
            for(int j = 1; j <= solution.getSize(); j++){
                int value;

                if(solution.getCell(j, i).isFilled()){
                    value = 1;
                }else{
                    value = 0;
                }

                solutionBin.append(value).append(" ");
            }
        }

        return solutionBin.toString();
    }

    public String getBoardInfo(Board board){

        return getBoardSize(board) + "/" +
                getBoardRowSums(board) + "/" +
                getBoardColSums(board) + "/" +
                getSolution(board) + "/";
    }

    public void writeToMatrix(Board board, int index, String filePath){
        String matrixFileName = "matrix.txt";
        FileIO.appendToFileTxt(filePath+matrixFileName, index + ".\n" + board);
    }

    public void writeToTxt(Board board, int index, String filePath){
        String boardInfo = getBoardInfo(board);
        String txtFileName = "txt.txt";
        FileIO.appendToFileTxt(filePath+txtFileName, index + ".\n" + boardInfo);
    }

    // protect elements file from being empty
    public void elementsNotEmpty(String filePath){
        if(FileIO.readFileTxt(filePath).isEmpty()){
            FileIO.writeToFileTxt(filePath, "0");
        }
    }

    public void incrementElements(String filePath){
        String filePathName = filePath + "elements.txt";
        elementsNotEmpty(filePathName);
        int size = Integer.parseInt(FileIO.readFileTxt(filePathName))+1;
        FileIO.writeToFileTxt(filePathName, Integer.toString(size));
    }

    public int readElements(String filePath){
        String filePathName = filePath + "elements.txt";
        elementsNotEmpty(filePathName);
        return Integer.parseInt(FileIO.readFileTxt(filePathName));
    }

    public static String getFilePath(int level){
        return switch (level) {
            case 2 -> "files/levelMedium/";
            case 3 -> "files/levelHard/";
            default -> "files/levelEasy/";
        };
    }

    public void addBoard(Board board, int level){

        System.out.println("...Dodawanie planszy do pliku...");

        try {
            String path = getFilePath(level);
            int index = readElements(path) + 1;

            writeToMatrix(board, index, path);
            writeToTxt(board, index, path);
            incrementElements(path);

        }catch(Exception e){
            System.out.println("Nie udało się zapisać do pliku");
        }

        System.out.println();

    }

    public Board getFileBoard(int index, int level){

        String filePath = getFilePath(level) + "txt.txt";
        String indexStr = index + ".";
        String[] boardInfo = FileIO.readFileTxtPart(indexStr, filePath).split("/");
        String[] rowSums = boardInfo[1].split(" ");
        String[] colSums = boardInfo[2].split(" ");
        String[] solution = boardInfo[3].split(" ");

        return new Board(rowSums, colSums, solution);

    }

}
