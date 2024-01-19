package Tests;

import Utils.CombinationsGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CombinationsGeneratorTest {

    @Test
    void generateCombinations() {
        int[] input1 = {1, 2};
        ArrayList<int[]> result1 = CombinationsGenerator.generateCombinations(input1);
        assertEquals(2,result1.size());

        int[] input2 = {1,2,1,5,4};
        ArrayList<int[]> result2 = CombinationsGenerator.generateCombinations(input2);
        assertEquals(40, result2.size());
    }
}