import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class TargetSum extends TestCase {
    public int findTargetSumWays(int[] nums, int S) {
        Map<Integer, Integer> sums = new HashMap<>();

        Map<Integer, Integer> currSums;
        sums.put(0, 1);

        for (int num: nums) {
            currSums = new HashMap<>();

            for (Integer sum: sums.keySet()) {
                int sumCount = sums.get(sum);

                int newSum = sum + num;
                if (currSums.containsKey(newSum)) {
                    currSums.put(newSum, currSums.get(newSum) + sumCount);
                } else {
                    currSums.put(newSum, sumCount);
                }

                newSum = sum - num;
                if (currSums.containsKey(newSum)) {
                    currSums.put(newSum, currSums.get(newSum) + sumCount);
                } else {
                    currSums.put(newSum, sumCount);
                }
            }
            sums = currSums;
        }

        return sums.containsKey(S) ? sums.get(S) : 0;
    }

    public int findTargetSumWays1(int[] nums, int S) {
        int n = nums.length;
        int maxSigns = 1 << n - 1;
        int result = 0;

        int sum;
        int sign;
        int i = 0;
        do {
            sum = 0;
            sign = i;
            for (int num : nums) {
                if (sign % 2 == 0) {
                    sum -= num;
                } else {
                    sum += num;
                }
                sign >>= 1;
            }

            if (Math.abs(sum) == Math.abs(S)) {
                ++result;
            }
            ++i;
        } while (i < maxSigns);

        if (S == 0) {
            return result * 2;
        }
        return result;
    }

    public void test() {
        assertEquals(5, findTargetSumWays(new int[] {1, 1, 1, 1, 1}, 3));
        assertEquals(1, findTargetSumWays(new int[] {1}, 1));
        assertEquals(1, findTargetSumWays(new int[] {1000}, -1000));
        assertEquals(1, findTargetSumWays(new int[] {1, 2}, 1));
        assertEquals(2, findTargetSumWays(new int[] {0, 2}, 2));
        assertEquals(6095, findTargetSumWays(new int[] {42,1,42,35,33,37,26,3,23,29,22,50,34,31,11,28,20,31,32,28}, 2));
        assertEquals(0, findTargetSumWays(new int[0], 1));
        assertEquals(2, findTargetSumWays(new int[] {1,2,1}, 0));
    }
}
