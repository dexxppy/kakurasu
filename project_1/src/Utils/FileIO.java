package Utils;

import java.io.*;

public class FileIO {

    //TXT writer
    public static void appendToFileTxt(String fileNameTxt, String content) {
        try {
            BufferedWriter txtWriter = new BufferedWriter(new FileWriter(fileNameTxt, true));
            txtWriter.write(content);
            txtWriter.write("\n");
            txtWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFileTxt(String filenameTxt, String content){
        try{
            FileWriter txtWriter = new FileWriter(filenameTxt);
            txtWriter.write(content);
            txtWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //TXT reader
    public static String readFileTxt(String fileNameReadTxt) {
        StringBuilder fileContent = new StringBuilder();

        try {
            BufferedReader txtReader = new BufferedReader(new FileReader(fileNameReadTxt));
            String line;
            while ((line = txtReader.readLine()) != null)
                fileContent.append(line);
            txtReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileContent.toString();
    }

    public static String readFileTxtPart(String start, String fileNameReadTxt){
        StringBuilder fileContent = new StringBuilder();

        try {
            BufferedReader txtReader = new BufferedReader(new FileReader(fileNameReadTxt));
            String line;
            boolean read = false;

            while ((line = txtReader.readLine()) != null) {

                if(read) {
                    if(line.isEmpty()) {
                        txtReader.close();
                        return fileContent.toString();
                    }

                    fileContent.append(line);

                } else if (line.equals(start)) {
                    read = true;
                }

            }

            txtReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileContent.toString();
    }

}