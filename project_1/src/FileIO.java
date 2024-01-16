import java.io.*;

public class FileIO {

    //TXT writer
    public static void writeToFileTxt(String fileNameTxt, String content) {
        try {
            BufferedWriter txtWriter = new BufferedWriter(new FileWriter(fileNameTxt, true));
            txtWriter.write("Generator output: \n");
            txtWriter.write(content);
            txtWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //TXT reader
    public static void readFileTxt(String fileNameReadTxt) {
        try {
            BufferedReader txtReader = new BufferedReader(new FileReader(fileNameReadTxt));
            String line;
            while ((line = txtReader.readLine()) != null)
                System.out.println(line);
            txtReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    //CSV writer
//    public static void writeFileCsv(Board board,String fileNameCsv){
//        try{
//            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(fileNameCsv,true));
//            csvWriter.write(board.getSize());
//            csvWriter.newLine();
//            for(int i=0;i<board.getSize();i++){
//                csvWriter.write(","+board.getRowSum(i));
//            }
//            csvWriter.newLine();
//            for(int i = 0; i<board.getSize(); i++){
//                csvWriter.write("," + board.getColumnSum(i));
//            }
//            csvWriter.newLine();
//            for (int i = 1; i <= board.getSize(); i++){
//                for (int j =1;j<=board.getSize();j++){
//                    Cell cell = board.getCell(j,i);
//                    csvWriter.write(cell.isFilled() ? "1" : "0");
//                    if (j<board.getSize()){
//                        csvWriter.write(",");
//                    }
//                }
//                csvWriter.newLine();
//            }
//            csvWriter.close();
//        } catch (IOException e){
//            throw new RuntimeException(e);
//        }
//    }
//
//    //CSV reader
//    public static void readFileCsv(String fileNameReadCsv, String separator) {
//        try {
//            BufferedReader csvReader = new BufferedReader(new FileReader(fileNameReadCsv));
//            String line;
//            while ((line = csvReader.readLine()) != null) {
//                String[] pole = line.split(separator);
//                for (String f : pole) {
//                    System.out.println(pole + "\t");
//                }
//                System.out.println();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}