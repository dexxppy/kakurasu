package Tests;

import Utils.Combinations;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinationsTest {

    @Test
    void testGenerateCombinations() {
        List<List<Integer>> result = Combinations.generateCombinations(3);
        assertNotNull(result);
        assertEquals(8,result.size());

        for (List<Integer> combination:result){
            assertTrue(combination.size()<=3 && combination.size()>=0);
        }
    }
}