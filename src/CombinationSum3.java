import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {
    public static class Solution {
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();

            partialCombination(result, new ArrayList<Integer>(), 1, k, n);

            return result;
        }

        private void partialCombination(List<List<Integer>> result, List<Integer> part, int minNumber, int k, int n) {
            if (k == 1) {
                if (n >=minNumber && n <= 9) {
                    result.add(appended(part, n));
                }
                return;
            }
            for (int i = minNumber; i < Math.min(n, 9); ++i) {
                partialCombination(result, appended(part, i), i+1, k-1, n-i);
            }
        }

        private List<Integer> appended(List<Integer> array, int item) {
            List<Integer> newArray = new ArrayList<Integer>(array.size() + 1);
            newArray.addAll(array);
            newArray.add(item);
            return newArray;
        }
    }
}
