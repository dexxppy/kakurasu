package Tests;

import Utils.FileIO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileIOTest {

    @BeforeEach
    public void init() {
        FileIO.writeToFileTxt("testFile1.txt", "");
    }


    @Test
    void appendToFileTxt() {
        String content = "Testowa zawartosc";
        FileIO.appendToFileTxt("testFile1.txt", content);
        assertEquals(content, FileIO.readFileTxt("testFile1.txt"));
    }

    @Test
    void writeToFileTxt() {
        FileIO.writeToFileTxt("testFile2.txt", "Test content");
        assertEquals("Test content", FileIO.readFileTxt("testFile2.txt"));
    }

    @Test
    void readFileTxt() {
        String content = "Test content";
        FileIO.writeToFileTxt("testFile3.txt", content);
        String result = FileIO.readFileTxt("testFile3.txt");
        assertEquals(content, result);
    }

    @Test
    void readFileTxtPart() {
        String content = "Line 1\nLine 2\n Line 3";
        FileIO.writeToFileTxt("testFile4", content);
        String result = FileIO.readFileTxtPart("Line 1", "testFile4");
        assertEquals("Line 2 Line 3", result.trim());
    }

    @AfterEach
    void cleanUp1() {
        File file = new File("testFile1");
        if (file.exists()) {
            file.delete();
        }
    }
}