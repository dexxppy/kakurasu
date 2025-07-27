package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Combinations {

    public int[] getRandomCombination(int number, int size) {
        if (number == 0)
            return new int[] {};

        ArrayList<Object> res = getAllCombinations(number, size);

        if (res.size() == 1)
            return (int[]) res.get(0);

        Random rand = new Random();
        int randIndex = rand.nextInt(res.size());

        return (int[]) res.get(randIndex);
    }

    public ArrayList<Object> getAllCombinations(int number, int size) {
        ArrayList<Object> combinations = new ArrayList<>();

        for (int i = 1; i < Math.pow(2, size); ++i) {
            ArrayList<Integer> ints = new ArrayList<>();

            String a = Integer.toBinaryString(i);
            int prev = number;

            for (int j = 0; j < a.length(); ++j) {

                if (a.charAt(j) == '1') {
                    int num = size - a.length() + j + 1;

                    ints.add(num);

                    prev -= num;
                }
            }

            if (prev == 0) {
                combinations.add(ints.stream().mapToInt(Integer::intValue).toArray());

            }
        }

        return combinations;
    }

    public static List<List<Integer>> generateCombinations(int number) {
        int[] elements = new int[number];

        for(int i=1; i <= number; i++){
            elements[i-1] = i;
        }

        List<List<Integer>> result = new ArrayList<>();
        generateCombinationsHelper(elements, 0, new ArrayList<>(), result);
        return result;
    }

    private static void generateCombinationsHelper(int[] elements, int index, List<Integer> currentCombination, List<List<Integer>> result) {

        try {

            if (index == elements.length) {
                // Add a copy of the current combination to the result
                result.add(new ArrayList<>(currentCombination));
            } else {
                // Include the element at the current index in the combination
                currentCombination.add(elements[index]);
                generateCombinationsHelper(elements, index + 1, currentCombination, result);

                // Exclude the element at the current index in the combination
                currentCombination.remove(currentCombination.size() - 1);
                generateCombinationsHelper(elements, index + 1, currentCombination, result);
            }

        }catch(Exception ignored){

        }
    }

}
