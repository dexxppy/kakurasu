import java.util.ArrayList;
import java.util.Random;

public class Combinations {
    public int[] get_random_combination(int number, int size) {
        if (number == 0)
            return new int[] {};

        ArrayList<Object> res = get_all_combinations(number, size);

        if (res.size() == 1)
            return (int[]) res.get(0);

        Random rand = new Random();
        int rand_index = rand.nextInt(res.size());

        return (int[]) res.get(rand_index);
    }

    public ArrayList<Object> get_all_combinations(int number, int size) {
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
}
