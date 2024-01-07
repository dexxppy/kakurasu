import java.util.ArrayList;


//generate combinations of possible solve, example:
//input: [1,2] output: [[1,1], [1,2]]
//input: [1, 2, 1, 3] output: [[1, 1, 1, 1], [1, 1, 1, 2], [1, 1, 1, 3], [1, 2, 1, 1], [1, 2, 1, 2], [1, 2, 1, 3]]
public class CombinationsGenerator {

    public static ArrayList<int[]> generateCombinations(int[] counts) {
        ArrayList<int[]> result = new ArrayList<>();
        generateCombinationsHelper(counts, 0, new int[counts.length], result);
        return result;
    }

    private static void generateCombinationsHelper(int[] counts, int currentIndex, int[] currentCombination, ArrayList<int[]> result) {
        if (currentIndex == counts.length) {
            result.add(currentCombination.clone());
            return;
        }

        for (int i = 1; i <= counts[currentIndex]; i++) {
            currentCombination[currentIndex] = i;
            generateCombinationsHelper(counts, currentIndex + 1, currentCombination, result);
        }
    }
}
